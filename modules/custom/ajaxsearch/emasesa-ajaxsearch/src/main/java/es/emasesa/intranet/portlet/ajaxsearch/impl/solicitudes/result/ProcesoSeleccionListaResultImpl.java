package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.result;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
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
import es.emasesa.intranet.service.util.SapServicesUtil;
import es.emasesa.intranet.settings.osgi.ClientExtensionsSettings;
import es.emasesa.intranet.settings.osgi.ObjectsGroupsSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {},
        service = AjaxSearchResult.class
)
public class ProcesoSeleccionListaResultImpl implements AjaxSearchResult {

    private final static Log LOG = LoggerUtil.getLog(ProcesoSeleccionListaResultImpl.class);

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

    private static final String VIEW = "/views/solicitudes/procesoseleccionlista/results.jsp";

    @Override
    public String getResultsView(PortletRequest request, PortletResponse response) {
        final Map<String, String> config = ajaxSearchUtil.getPropertiesMap(request);
        request.setAttribute("cssWrapperClass", config.getOrDefault(CSS_WRAPPER_CLASS, StringPool.BLANK));
        request.setAttribute("disablePagination", config.getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO));

        return VIEW;
    }

    public AjaxMessage filterResults(PortletRequest request,
                                     PortletResponse response,
                                     AjaxSearchDisplayContext ajaxSearchDisplayContext) {
        try {
            long totalItems = 0;
            final int currentPage = ajaxSearchDisplayContext.getCurrentPage();
            final int pageSize = ajaxSearchDisplayContext.getPageSize();
            final JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

            final String disablePaginationStr = ajaxSearchDisplayContext.getConfig().getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO);
            final boolean disablePagination = !Validator.isBlank(disablePaginationStr) && disablePaginationStr.equals("1");

            totalItems = performSearchAndParse(request,
                    response,
                    ajaxSearchDisplayContext,
                    currentPage,
                    pageSize,
                    disablePagination,
                    jsonArray);

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
                                       final int currentPage,
                                       final int pageSize,
                                       final boolean disablePagination,
                                       JSONArray jsonArray) throws ParseException, SearchException {
        final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        int totalItems = 0;
        final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
        final int end = disablePagination ? pageSize : (currentPage * pageSize);
        JSONArray results = _sapServicesUtil.getProcesoSeleccionLista();
        JSONArray resultsFiltered;
        String[] tiposProcesos = ParamUtil.getParameterValues(request, AjaxSearchPortletKeys.TIPO_PROCESO_FILTER);
        JSONObject jsonObject;

        resultsFiltered = JSONFactoryUtil.createJSONArray();
        for (Object object : results) {
            if (object instanceof JSONObject) {
                jsonObject = getResultJson((JSONObject) object, themeDisplay);
                if (Validator.isNotNull(jsonObject) && tiposProcesos.length>0 && jsonObject.get(AjaxSearchPortletKeys.TIPO_PROCESO).equals(tiposProcesos[0])) {
                    resultsFiltered.put(jsonObject);
                } else if (Validator.isNotNull(jsonObject) && tiposProcesos.length == 0 ){
                    resultsFiltered.put(jsonObject);
                }
            }
        }
        for (int i = start; i < end; i++) {
            Object object = resultsFiltered.get(i);
            if (object instanceof JSONObject) {
                if (Validator.isNotNull(object)) {
                    jsonArray.put(object);
                }
            }
        }
        totalItems = resultsFiltered.length();

        return totalItems;
    }

    private JSONObject getResultJson(final JSONObject jsonObject,
                                     final ThemeDisplay themeDisplay) {
        final JSONObject result = jsonFactory.createJSONObject();

        String nombreProceso = jsonObject.getString("idtxt");
        result.put(AjaxSearchPortletKeys.ASUNTO, nombreProceso);

        String fechaFinProceso = jsonObject.getString("zzpreso");
        LocalDate date = LocalDate.parse(fechaFinProceso);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        result.put(AjaxSearchPortletKeys.FECHA_FIN_PROCESO, formattedDate);

        String numPlazas = jsonObject.getString("zzplazas");
        result.put(AjaxSearchPortletKeys.NUMERO_PLAZAS, numPlazas);

        String tipoProceso = jsonObject.getString("zztipoDesc2");
        result.put(AjaxSearchPortletKeys.TIPO_PROCESO, tipoProceso);

        return result;
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
    ObjectDefinitionLocalService _objectDefinitionLocalService;

    @Reference
    SapServicesUtil _sapServicesUtil;
}
