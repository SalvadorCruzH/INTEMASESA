package es.emasesa.intranet.portlet.ajaxsearch.constant;

import com.liferay.petra.string.StringPool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AjaxSearchPortletKeys {

	public static final DateFormat DFLT_SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final String SEARCH_RENDER_PARAM_NAME = "searchActionParameters";
	public static final String PORTLET_AJAXSEARCH_FORM =
		"es_camara_intranet_portlet_ajaxsearch_portlet_form_AjaxSearchFormPortlet";
	public static final String PORTLET_AJAXSEARCH_RESULTS =
		"es_camara_intranet_portlet_ajaxsearch_portlet_result_AjaxSearchResultsPortlet";
	public static final String PORTLET_PREFS_SEARCH_IMPL="searchImpl";
	public static final String PORTLET_CURRENT_CONFIG="currentConfig";
	public static final String DEFAULT_JSP_FORM="/search-form.jsp";
	public static final String DEFAULT_JSP_RESULT="/search-results.jsp";
	public static final String DEFAULT_JSP_EMPTY_VIEW="/empty-view.jsp";
	public static final String DEFAULT_JSP_PLEASE_CONFIGURE_VIEW="/please-configure-view.jsp";

	/**
	 * ATTRS/PARAMETERS/JSON/PROPS STANDARIZATION
	 */
	public static final String ATTR_AJAX_FORM_PAGE = "formPage";
	public static final String ATTR_AJAX_RESULTS_PAGE = "resultsPage";
	public static final String ATTR_AJAX_SEARCH_DISPLAY_CONTEXT = "searchDisplayContext";

	public static final String PARAM_ORIGINAL_JSON_OBJECT = "originalJSONObject";
	public static final String PARAM_ACTION_PARAMS = "actionParams";
	public static final String PARAM_QUERY_TEXT = "queryText";
	public static final String PARAM_CURRENT_PAGE = "currentPage";
	public static final int PARAM_CURRENT_PAGE_DFLT = 1;

	public static final String JSON_CURRENT_PAGE = "currentPage";
	public static final String JSON_TOTAL_PAGES = "totalPages";
	public static final String JSON_TOTAL_ITEMS = "totalItems";
	public static final String JSON_ITEMS_PAGE = "itemsPage";
	public static final String JSON_ARRAY_CONTENT = StringPool.CONTENT;
	public static final String JSON_ARRAY_ADDITIONAL_INFO = "additionalInfo";

	public static final String PROP_BASE_PAGESIZE = "pagesize";
	public static final String PROP_BASE_PAGESIZE_DFLT = "9";
	public static final int PROP_BASE_PAGESIZE_DFLT_INT = Integer.parseInt(PROP_BASE_PAGESIZE_DFLT);
	public static final String PROP_BASE_RESULT_URL = "result-relative-url";
	public static final String PROP_BASE_RESULT_URL_DFLT = "/resultados";

	/** XML SAX Reading **/
	public static final String XML_READ_INIT = "/root/dynamic-element[@name='";
	public static final String XML_READ_END = "']/dynamic-content";


}