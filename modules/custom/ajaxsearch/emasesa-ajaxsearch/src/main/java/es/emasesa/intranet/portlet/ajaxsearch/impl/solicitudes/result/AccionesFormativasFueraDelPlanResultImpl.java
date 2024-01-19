package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.result;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.*;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.base.model.AjaxMessage;
import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.base.util.CustomJournalUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchJsonModel;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import es.emasesa.intranet.searchframework.SearchingCommon;
import es.emasesa.intranet.searchframework.SearchingJournal;
import es.emasesa.intranet.searchframework.SearchingObject;
import es.emasesa.intranet.settings.osgi.ClientExtensionsSettings;
import es.emasesa.intranet.settings.osgi.ObjectsGroupsSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {},
        service = AjaxSearchResult.class
)
public class AccionesFormativasFueraDelPlanResultImpl implements AjaxSearchResult {

    private final static Log LOG = LoggerUtil.getLog(AccionesFormativasFueraDelPlanResultImpl.class);

    private static final Properties DFLT_PROPERTIES = new Properties();
    public static final String DISABLE_PAGINATION = "disable-pagination";
    public static final String CSS_WRAPPER_CLASS = "css-wrapper-class";
    public static final String SOLICITUDES_ID = "solicitudesId";
    public static final String SOLICITUDES_ID_DFLT = "-1";

    static {
        DFLT_PROPERTIES.put(AjaxSearchPortletKeys.PROP_BASE_PAGESIZE,
                AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT);
        DFLT_PROPERTIES.put(DISABLE_PAGINATION, "0");
        DFLT_PROPERTIES.put(CSS_WRAPPER_CLASS, "");
        DFLT_PROPERTIES.put(SOLICITUDES_ID, SOLICITUDES_ID_DFLT);
    }

    @Override
    public Properties getDefaultProperties() {
        return DFLT_PROPERTIES;
    }

    private static final String VIEW = "/views/solicitudes/accionesformativasfueradelplan/results.jsp";
    private static final String VIEW_RRHH = "/views/solicitudes/accionesformativasfueradelplan/results_rol_rrhh.jsp";

    @Override
    public String getResultsView(PortletRequest request, PortletResponse response) {
        final Map<String, String> config = ajaxSearchUtil.getPropertiesMap(request);
        request.setAttribute("cssWrapperClass", config.getOrDefault(CSS_WRAPPER_CLASS, StringPool.BLANK));
        request.setAttribute("disablePagination", config.getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO));
        boolean isRRHHAdmin = checkIsRole(request);

        if (isRRHHAdmin){
            return VIEW_RRHH;
        } else {
            return VIEW;
        }
    }

    public AjaxMessage filterResults(PortletRequest request,
                                     PortletResponse response,
                                     AjaxSearchDisplayContext ajaxSearchDisplayContext) {
        try {
            long totalItems = 0;
            final int currentPage = ajaxSearchDisplayContext.getCurrentPage();
            final int pageSize = ajaxSearchDisplayContext.getPageSize();
            final JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            final String solicitudesId = objectsGroupsSettings.getSolicitudesIds(ajaxSearchDisplayContext.getConfig().getOrDefault(SOLICITUDES_ID, StringPool.BLANK));

            final String disablePaginationStr = ajaxSearchDisplayContext.getConfig().getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO);
            final boolean disablePagination = !Validator.isBlank(disablePaginationStr) && disablePaginationStr.equals("1");

            if (!Validator.isBlank(solicitudesId)) {
                totalItems = performSearchAndParse(request,
                        response,
                        ajaxSearchDisplayContext,
                        solicitudesId,
                        currentPage,
                        pageSize,
                        disablePagination,
                        jsonArray);
            }

            final int totalPages = (((int) totalItems + pageSize - 1) / pageSize);
            final AjaxSearchJsonModel ajaxSearchJsonModel = new AjaxSearchJsonModel(
                    currentPage,
                    totalPages,
                    totalItems,
                    (disablePagination ? 0 : pageSize),
                    jsonArray);
            return new AjaxMessage(
                    AjaxMessage.STATUS.OK,
                    AjaxMessage.MESSAGES_DEFAULT.OK,
                    ajaxSearchJsonModel.getJsonObject());

        } catch (ParseException | SearchException e) {
            LOG.info("Error At search in indexes: " + e.getLocalizedMessage());
            final AjaxSearchJsonModel ajaxSearchJsonModel = new AjaxSearchJsonModel(0, 0, 0L, 0, JSONFactoryUtil.createJSONArray());
            return new AjaxMessage(
                    AjaxMessage.STATUS.ERROR_GENERAL,
                    AjaxMessage.MESSAGES_DEFAULT.ERROR_GENERAL,
                    ajaxSearchJsonModel.getJsonObject());
        }
    }

    private long performSearchAndParse(final PortletRequest request,
                                       final PortletResponse response,
                                       final AjaxSearchDisplayContext ajaxSearchDisplayContext,
                                       final String solicitudesId,
                                       final int currentPage,
                                       final int pageSize,
                                       final boolean disablePagination,
                                       final JSONArray jsonArray) throws ParseException, SearchException {
        final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
        final int end = disablePagination ? pageSize : (currentPage * pageSize);

        final SearchContext searchContext = searchingCommon.createPaginatedSearchContext(start, end);
        final BooleanQuery booleanQuery = searchingCommon.createEmptyBooleanQuery();

        int totalItems = 0;
        boolean isRRHHAdmin = checkIsRole(request);

        if (Validator.isNotNull(themeDisplay)) {
            searchingCommon.addCompanyIdFilter(searchContext, themeDisplay.getCompanyId());
        } else {
            searchingCommon.addCompanyIdFilter(searchContext, PortalUtil.getDefaultCompanyId());
        }

        QueryConfig queryConfig = searchContext.getQueryConfig();
        queryConfig.addSelectedFieldNames(Field.ANY);

        if (!ajaxSearchDisplayContext.getQueryText().isBlank()) {
            booleanQuery.addTerm(AjaxSearchPortletKeys.OBJECT_DEFINITION_NAME, ajaxSearchDisplayContext.getQueryText(), Boolean.TRUE, BooleanClauseOccur.MUST);
        }

        searchingObject.setMustBooleanClauses(searchContext, booleanQuery);

        List<Document> documents = new ArrayList<>();
        if (isRRHHAdmin){
            documents = searchingObject.searchObjectsAllUsers(solicitudesId.split(","), searchContext);
            Date fechaDesde = ParamUtil.getDate(request, AjaxSearchPortletKeys.FECHA_DESDE, AjaxSearchPortletKeys.DFLT_SIMPLE_DATE_FORMAT);
            Date fechaHasta = ParamUtil.getDate(request, AjaxSearchPortletKeys.FECHA_HASTA, AjaxSearchPortletKeys.DFLT_SIMPLE_DATE_FORMAT);
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaHasta);
            cal.add(Calendar.DATE, 1);
            Date fechaHastaValid = cal.getTime();
            String matricula = ParamUtil.getString(request, AjaxSearchPortletKeys.MATRICULA);
            String nombre = ParamUtil.getString(request, AjaxSearchPortletKeys.NOMBRE);
            String[] estado = ParamUtil.getParameterValues(request, AjaxSearchPortletKeys.ESTADO);
            Date now = new Date();

            if (!fechaDesde.equals(now)) {
                documents = (documents != null) ? documents.stream().filter(document -> {
                    try {
                       Date date = document.getDate(Field.CREATE_DATE);
                        return date.after(fechaDesde);
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()) : new ArrayList<>();
            }

            if (!fechaHasta.equals(now)) {
                documents = (documents != null) ? documents.stream().filter(document -> {
                    try {
                        Date date = document.getDate(Field.CREATE_DATE);
                        return date.before(fechaHastaValid);
                    } catch (java.text.ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()) : new ArrayList<>();
            }

            if (!matricula.isBlank()) {
                documents = (documents != null) ? documents.stream().filter(document -> {
                    Map<String, String> content =  searchingObject.getObjectEntryContent(document.get(AjaxSearchPortletKeys.FIELD_OBJECT_ENTRY_CONTENT));
                    String matriculaValue = content.get(AjaxSearchPortletKeys.MATRICULA);
                    return matriculaValue != null && matriculaValue.toLowerCase().contains(matricula.toLowerCase());
                }).collect(Collectors.toList()) : new ArrayList<>();
            }

            if (!nombre.isBlank()) {
                documents = (documents != null) ? documents.stream().filter(document -> {
                    Map<String, String> content =  searchingObject.getObjectEntryContent(document.get(AjaxSearchPortletKeys.FIELD_OBJECT_ENTRY_CONTENT));
                    String nombreValue = content.get(AjaxSearchPortletKeys.NOMBRE);
                    return nombreValue != null && nombreValue.toLowerCase().contains(nombre.toLowerCase());
                }).collect(Collectors.toList()) : new ArrayList<>();
            }

            if (estado.length>0){
                documents = (documents != null) ? documents.stream().filter(document -> {
                    Map<String, String> content =  searchingObject.getObjectEntryContent(document.get(AjaxSearchPortletKeys.FIELD_OBJECT_ENTRY_CONTENT));
                    String statusObject = content.get(AjaxSearchPortletKeys.ESTADO_OBJETO);
                    return Arrays.asList(estado).contains(statusObject);
                }).collect(Collectors.toList()) : new ArrayList<>();
            }
        } else {
            documents = searchingObject.searchObjects(solicitudesId.split(","), searchContext);
        }

        totalItems = documents.size();

        String[] sortBy = ajaxSearchDisplayContext.getString("sortby").split(StringPool.DASH);
        if (Validator.isNotNull(sortBy)) {
            if (sortBy[0].equals("date")) {
                sortDocuments(documents, themeDisplay.getLocale(), Field.CREATE_DATE, sortBy[1].equals("asc") ? Boolean.FALSE : Boolean.TRUE);
            } else if (sortBy[0].equals("name")) {
                sortDocuments(documents, themeDisplay.getLocale(), AjaxSearchPortletKeys.OBJECT_DEFINITION_NAME, sortBy[1].equals("asc") ? Boolean.FALSE : Boolean.TRUE);
            }
        } else {
            sortDocuments(documents, themeDisplay.getLocale(), AjaxSearchPortletKeys.OBJECT_DEFINITION_NAME, sortBy[1].equals("asc") ? Boolean.FALSE : Boolean.TRUE);
        }
        JSONObject jsonObject;

        for (Document document : documents) {
            jsonObject = getResultJson(document, themeDisplay);
            if (Validator.isNotNull(jsonObject)) {
                jsonArray.put(jsonObject);
            }
        }

        return totalItems;
    }

    private void sortDocuments(List<Document> documents, Locale locale, String sortingField, Boolean descending) {
        documents.sort((Document d1, Document d2) -> {
            String fieldDocument1 = d1.get(locale, sortingField);
            String fieldDocument2 = d2.get(locale, sortingField);
            if (descending) {
                return fieldDocument2.compareTo(fieldDocument1);
            } else {
                return fieldDocument1.compareTo(fieldDocument2);
            }
        });
    }

    private JSONObject getResultJson(final Document document,
                                     final ThemeDisplay themeDisplay) {
        final JSONObject jsonObject = jsonFactory.createJSONObject();

        String fechaEnvio = document.get(themeDisplay.getLocale(), Field.CREATE_DATE);
        jsonObject.put(AjaxSearchPortletKeys.FECHA_ENVIO, ajaxSearchUtil.formatDate(fechaEnvio));

        Long objectClassPK = Long.parseLong(document.get(Field.ENTRY_CLASS_PK));
        ObjectEntry objectEntry = ajaxSearchUtil.getObject(objectClassPK);
        if (objectEntry != null) {
            String asunto = document.get(themeDisplay.getLocale(), AjaxSearchPortletKeys.OBJECT_DEFINITION_NAME);
            jsonObject.put(AjaxSearchPortletKeys.ASUNTO, asunto);

            String fechaActividad;
            if (Validator.isNotNull(objectEntry.getValues().get(AjaxSearchPortletKeys.FECHA_ACTIVIDAD))){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = formatter.parse(objectEntry.getValues().get(AjaxSearchPortletKeys.FECHA_ACTIVIDAD).toString());
                    fechaActividad = formatter2.format(date);
                } catch (java.text.ParseException e) {
                    throw new RuntimeException(e);
                }

            } else {
                fechaActividad = StringPool.DASH;
            }

            jsonObject.put(AjaxSearchPortletKeys.FECHA_ACTIVIDAD, fechaActividad);

            String numeroEmpleadosALosQueSolicita = objectEntry.getValues().getOrDefault(AjaxSearchPortletKeys.NUMERO_EMPLEADOS, StringPool.DASH).toString();
            jsonObject.put(AjaxSearchPortletKeys.NUMERO_EMPLEADOS, numeroEmpleadosALosQueSolicita);

            String denominacion = objectEntry.getValues().getOrDefault(AjaxSearchPortletKeys.DENOMINACION_FORMACION, StringPool.DASH).toString();
            jsonObject.put(AjaxSearchPortletKeys.DENOMINACION_FORMACION, denominacion);

            String tipoRetribucion = objectEntry.getValues().getOrDefault(AjaxSearchPortletKeys.TIPO_RETRIBUCION, StringPool.DASH).toString();
            jsonObject.put(AjaxSearchPortletKeys.TIPO_RETRIBUCION, tipoRetribucion);

            User user = themeDisplay.getUser();
            _customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), EmasesaConstants.EMASESA_EXPANDO_NOMBRE);
            String nombreCompleto = _customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), EmasesaConstants.EMASESA_EXPANDO_NOMBRE) + " "
                    + _customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), EmasesaConstants.EMASESA_EXPANDO_APELLIDO1) + " "
                    + _customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), EmasesaConstants.EMASESA_EXPANDO_APELLIDO2);
            jsonObject.put(AjaxSearchPortletKeys.NOMBRE_COMPLETO, nombreCompleto);

            String email = themeDisplay.getUser().getEmailAddress();
            jsonObject.put(AjaxSearchPortletKeys.EMAIL, email);

            jsonObject.put(AjaxSearchPortletKeys.FIELD_OBJECT_ENTRY_ID, objectEntry.getObjectEntryId());

            String estadoObjeto = objectEntry.getValues().getOrDefault(AjaxSearchPortletKeys.ESTADO_OBJETO, StringPool.BLANK).toString();
            if (!estadoObjeto.equals(StringPool.BLANK)) {
                if (!estadoObjeto.equals("pendienteRRHH")){
                    jsonObject.put(AjaxSearchPortletKeys.IS_HIDDEN, "hide");
                }
                jsonObject.put(AjaxSearchPortletKeys.ESTADO, LanguageUtil.get(themeDisplay.getLocale(), estadoObjeto));
                switch (estadoObjeto) {
                    case "aceptada":
                        jsonObject.put(AjaxSearchPortletKeys.ESTADO_CODE, "success");
                        jsonObject.put(AjaxSearchPortletKeys.ES_EDITABLE_POR_USUARIO, StringPool.BLANK);
                        break;
                    case "rechazada":
                        jsonObject.put(AjaxSearchPortletKeys.ESTADO_CODE, "danger");
                        jsonObject.put(AjaxSearchPortletKeys.ES_EDITABLE_POR_USUARIO, StringPool.BLANK);
                        break;
                    case "devueltoAUsuarioSolicitante":
                        jsonObject.put(AjaxSearchPortletKeys.ESTADO_CODE, "pending");
                        jsonObject.put(AjaxSearchPortletKeys.ES_EDITABLE_POR_USUARIO, "esEditable");
                        break;
                    default:
                        jsonObject.put(AjaxSearchPortletKeys.ESTADO_CODE, "pending");
                        jsonObject.put(AjaxSearchPortletKeys.ES_EDITABLE_POR_USUARIO, StringPool.BLANK);
                        break;
                }

            } else {
                jsonObject.put(AjaxSearchPortletKeys.ESTADO, LanguageUtil.get(themeDisplay.getLocale(), "unknown"));
                jsonObject.put(AjaxSearchPortletKeys.ESTADO_CODE, "unknown");
            }
            String externalReferenceCode = objectEntry.getExternalReferenceCode();
            try {
                String objectDefinitionName = document.get(themeDisplay.getLocale(), AjaxSearchPortletKeys.OBJECT_DEFINITION_NAME);
                String objectName = JSONFactoryUtil.createJSONObject(clientExtensionsSettings.objectNames()).getString(objectDefinitionName, objectDefinitionName);
                jsonObject.put(AjaxSearchPortletKeys.NOMBRE_OBJETO, objectName);
                String json = clientExtensionsSettings.objectMapping();
                JSONObject jsonObject1 = JSONFactoryUtil.createJSONObject(json);
                String stringObject = jsonObject1.getString(objectName);
                if(!stringObject.equals(StringPool.BLANK)){
                    String display = JSONFactoryUtil.createJSONObject(stringObject).getString("display");
                    String objectContextPath = _objectDefinitionLocalService.getObjectDefinition(objectEntry.getObjectDefinitionId()).getRESTContextPath();
                    jsonObject.put(AjaxSearchPortletKeys.URL_VISUALIZAR, ajaxSearchUtil.formatViewUrl(String.valueOf(objectClassPK), objectName, display, themeDisplay.getPortalURL()));
                    jsonObject.put(AjaxSearchPortletKeys.URL_EDITAR, ajaxSearchUtil.formatEditUrl(String.valueOf(objectClassPK), objectName, display, themeDisplay.getPortalURL()));
                    jsonObject.put(AjaxSearchPortletKeys.URL_ELIMINAR, ajaxSearchUtil.formatDeleteUrl(themeDisplay.getPortalURL(), externalReferenceCode, String.valueOf(themeDisplay.getScopeGroupId()), objectContextPath));

                }
            } catch (PortalException e) {
                LoggerUtil.error(LOG, e);
            }
        }
        return jsonObject;
    }

    private boolean checkIsRole(PortletRequest request ){
        final Map<String, String> config = ajaxSearchUtil.getPropertiesMap(request);
        String roleName = (config.get(AjaxSearchPortletKeys.ROLE_RRHH_CONFIG) != null)? config.get(AjaxSearchPortletKeys.ROLE_RRHH_CONFIG) : "";

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        long groupId = themeDisplay.getScopeGroupId();

        List<Role> siteRoles = _roleLocalService.getUserGroupRoles(user.getUserId(), groupId);

       return siteRoles.stream().anyMatch(role -> roleName.equals(role.getName()));
    }


    @Reference
    AjaxSearchUtil ajaxSearchUtil;

    @Reference
    CustomJournalUtil customJournalUtil;

    @Reference
    SearchingJournal searchingJournal;

    @Reference
    SearchingCommon searchingCommon;

    @Reference
    SearchingObject searchingObject;

    @Reference
    protected SortFactory sortFactory;

    @Reference
    ObjectEntryLocalService objectEntryLocalService;

    @Reference
    JSONFactory jsonFactory;

    @Reference
    IndexSearcher indexSearcher;

    @Reference
    ObjectsGroupsSettings objectsGroupsSettings;

    @Reference
    ClientExtensionsSettings clientExtensionsSettings;

    @Reference
    private RoleLocalService _roleLocalService;

    @Reference
    private CustomExpandoUtil _customExpandoUtil;

    @Reference
    ObjectDefinitionLocalService _objectDefinitionLocalService;
}
