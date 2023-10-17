package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.result;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.base.model.AjaxMessage;
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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component(
        immediate = true,
        property = {},
        service = AjaxSearchResult.class
)
public class SolicitudesResultImpl implements AjaxSearchResult {

    private final static Log LOG = LoggerUtil.getLog(SolicitudesResultImpl.class);

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

    private static final String VIEW = "/views/solicitudes/results.jsp";

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
            final String solicitudesId = ajaxSearchDisplayContext.getConfig().getOrDefault(SOLICITUDES_ID, StringPool.BLANK);

            final String disablePaginationStr = ajaxSearchDisplayContext.getConfig().getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO);
            final boolean disablePagination = !Validator.isBlank(disablePaginationStr) && disablePaginationStr.equals("1");

            if (!Validator.isBlank(solicitudesId)) {

                //Date fromDate = ajaxSearchDisplayContext.getDate(FECHA_DESDE);
                //Date toDate = ajaxSearchDisplayContext.getOneMoreDayDate(FECHA_HASTA);

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

        if (Validator.isNotNull(themeDisplay)) {
            searchingCommon.addCompanyIdFilter(searchContext, themeDisplay.getCompanyId());
        } else {
            searchingCommon.addCompanyIdFilter(searchContext, PortalUtil.getDefaultCompanyId());
        }

        String[] sortBy = ajaxSearchDisplayContext.getString("sortby").split(StringPool.DASH);
        if(Validator.isNotNull(sortBy)) {
            if(sortBy[0].equals("date")) {
                searchContext.setSorts(sortFactory.create(Field.MODIFIED_DATE, Sort.INT_TYPE, sortBy[1].equals("asc")?Boolean.FALSE:Boolean.TRUE));
            } else if(sortBy[0].equals("name")) {
                searchContext.setSorts(sortFactory.create("objectDefinitionName", Sort.STRING_TYPE, sortBy[1].equals("asc")?Boolean.FALSE:Boolean.TRUE));
            }
        } else {
            searchContext.setSorts(sortFactory.create("objectDefinitionName", Sort.STRING_TYPE, Boolean.FALSE));
        }

        if (!ajaxSearchDisplayContext.getQueryText().isBlank()) {
            booleanQuery.addTerm("objectDefinitionName", ajaxSearchDisplayContext.getQueryText(), Boolean.TRUE, BooleanClauseOccur.MUST);
        }

        searchingObject.setMustBooleanClauses(searchContext, booleanQuery);

        final Hits hits = searchingObject.searchObjects(solicitudesId.split(","), searchContext);
        final int totalItems = hits.getLength();

        final List<Document> documents = hits.toList();
        JSONObject jsonObject;

        for (Document document : documents) {
            jsonObject = getResultJson(document, themeDisplay);
            if (Validator.isNotNull(jsonObject)) {
                jsonArray.put(jsonObject);
            }
        }

        return totalItems;
    }

    private JSONObject getResultJson(final Document document,
                                     final ThemeDisplay themeDisplay) {
        final JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("nombreObjeto", document.get(themeDisplay.getLocale(), "objectDefinitionName"));

        String fechaDesde = document.get(themeDisplay.getLocale(), "fechaDesde");
        jsonObject.put("fechaDesde", formatDate(fechaDesde));

        String fechaHasta = document.get(themeDisplay.getLocale(), "fechaHasta");
        jsonObject.put("fechaHasta", formatDate(fechaHasta));

        String fechaSolicitud = document.get(themeDisplay.getLocale(), Field.CREATE_DATE);
        jsonObject.put("fechaSolicitud", formatDate(fechaSolicitud));

        jsonObject.put("estado", document.get(themeDisplay.getLocale(), Field.STATUS));


        return jsonObject;
    }

    /**
     * Formatea la fecha de la solicitud
     *
     * @param date con formato YYYYMMDD....
     * @return String con la fecha formateada DD/MM/YYYY
     */
    private String formatDate(String date) {
        if (!date.isBlank()) {
            return date.substring(6, 8) + StringPool.SLASH + date.substring(4, 6) + StringPool.SLASH + date.substring(0, 4);
        } else {
            return StringPool.DASH;
        }
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
    IndexSearcher indexSearcher;

}
