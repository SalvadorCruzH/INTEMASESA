package es.emasesa.intranet.portlet.ajaxsearch.impl.resumenanual.result;

import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import java.util.*;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchJsonModel;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import es.emasesa.intranet.searchframework.SearchingCommon;
import es.emasesa.intranet.searchframework.SearchingJournal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.model.AjaxMessage;
import es.emasesa.intranet.base.util.CustomJournalUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.base.util.XMLDocumentUtil;
@Component(
        immediate = true,
        property = { },
        service = AjaxSearchResult.class
)
public class ResumenAnualResultImpl implements AjaxSearchResult {

	private final static Log LOG = LoggerUtil.getLog(ResumenAnualResultImpl.class);

	private static final Properties DFLT_PROPERTIES = new Properties();

	public static final String TEMPLATE_KEY = "template-key";
	public static final String STRUCTURE_KEY = "structure-key";
	public static final String CSS_WRAPPER_CLASS = "css-wrapper-class";
	public static final String DISABLE_PAGINATION = "disable-pagination";
	private static final String CAT_SELECTED = "catSelected";
	private static final String FECHA_DESDE = "fechaDesde";
	private static final String FECHA_HASTA = "fechaHasta";



	static {
		DFLT_PROPERTIES.put(AjaxSearchPortletKeys.PROP_BASE_PAGESIZE,
							AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT);

		DFLT_PROPERTIES.put(TEMPLATE_KEY, "EMA-NOTICIA-TARJETA-TEMPLATE");
		DFLT_PROPERTIES.put(STRUCTURE_KEY, "EMA-NOTICIA");
		DFLT_PROPERTIES.put(CSS_WRAPPER_CLASS, "");
		DFLT_PROPERTIES.put(DISABLE_PAGINATION, "0");
	}

	@Override
	public Properties getDefaultProperties() {
		return DFLT_PROPERTIES;
	}

	@Override
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

				String year = ajaxSearchDisplayContext.getString("year");

				totalItems = performSearchAndParse(request,
					response,
					ajaxSearchDisplayContext,
						year,
					currentPage,
					pageSize,
					disablePagination,
					jsonArray);

			final int totalPages = (((int) totalItems + pageSize - 1) / pageSize);
			final AjaxSearchJsonModel ajaxSearchJsonModel = new AjaxSearchJsonModel(
					currentPage,
					totalPages,
					totalItems,
					(disablePagination? 0: pageSize),
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

	private static final Date NULL_DATE=null;
	private static final List<String> EMPTY_LIST=new ArrayList<>();

	private long performSearchAndParse(final PortletRequest request,
									   final PortletResponse response,
									   final AjaxSearchDisplayContext ajaxSearchDisplayContext,
									   final String year,
									   final int currentPage,
									   final int pageSize,
									   final boolean disablePagination,
									   final JSONArray jsonArray) throws ParseException, SearchException {

		final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String cacheKey = "resumenAnual"+year+themeDisplay.getUser().getUserId();
		JSONArray array;
		int totalItems = 0;
		if(Validator.isNotNull(year)){
			Object object = _cache.get(cacheKey);

			if(Validator.isNotNull(object) && ((JSONArray) object).length()>0){
				array = (JSONArray) object;

			}else{
				array = _sapServicesUtil.getResumenAnual(themeDisplay.getUser(),year);
				_cache.put(cacheKey,array,86400);

			}

			final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
			int count = (currentPage * pageSize) > array.length() ? array.length() : (currentPage * pageSize);
			final int end = disablePagination ? pageSize : count;

			totalItems = array.length();
			List<JSONObject> listJson = new ArrayList<>();
			for(int i = 0;i<array.length();i++){

				listJson.add(array.getJSONObject(i));
			}

			listJson.subList(start,end).stream().forEach(j->jsonArray.put(j));
		}
		return totalItems;
	}


	private static final String VIEW = "/views/resumenanual/results.jsp";

	@Override
	public String getResultsView(PortletRequest request, PortletResponse response) {
		final Map<String, String> config = ajaxSearchUtil.getPropertiesMap(request);
		request.setAttribute("cssWrapperClass", config.getOrDefault(CSS_WRAPPER_CLASS, StringPool.BLANK));
		request.setAttribute("disablePagination", config.getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO));
		return VIEW;
	}

	@Reference
	JournalArticleLocalService journalArticleLocalService;

	@Reference
	AjaxSearchUtil ajaxSearchUtil;

	@Reference
	XMLDocumentUtil xmlDocumentUtil;

	@Reference
	CustomJournalUtil customJournalUtil;

	@Reference
	SearchingJournal searchingJournal;

	@Reference
	SearchingCommon searchingCommon;

	@Reference
	SapServicesUtil _sapServicesUtil;
	@Reference
	CustomCacheSingleUtil _cache;
}