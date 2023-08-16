package es.emasesa.intranet.portlet.ajaxsearch.impl.news.result;

import java.util.*;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import es.emasesa.intranet.base.constant.LongConstants;
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
public class NewsResultImpl implements AjaxSearchResult {

	private final static Log LOG = LoggerUtil.getLog(NewsResultImpl.class);

	private static final Properties DFLT_PROPERTIES = new Properties();

	public static final String TEMPLATE_KEY = "template-key";
	public static final String STRUCTURE_KEY = "structure-key";
	public static final String FORCE_GROUP_ID = "force-group-id";
	public static final String CSS_WRAPPER_CLASS = "css-wrapper-class";
	//public static final String ONLY_SITE_CONTENT = "only-site-content";
	//public static final String INCLUDE_CHILDSITE_CONTENT = "only-site-content--include-subsites";
	public static final String DISABLE_PAGINATION = "disable-pagination";


	static {
		DFLT_PROPERTIES.put(AjaxSearchPortletKeys.PROP_BASE_PAGESIZE,
							AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT);

		DFLT_PROPERTIES.put(TEMPLATE_KEY, "EMA-NOTA-PRENSA-TARJETA-TEMPLATE");
		DFLT_PROPERTIES.put(STRUCTURE_KEY, "EMA-NOTA-PRENSA");
		DFLT_PROPERTIES.put(CSS_WRAPPER_CLASS, "");
		DFLT_PROPERTIES.put(DISABLE_PAGINATION, "0");
		//DFLT_PROPERTIES.put(FORCE_GROUP_ID, "-1");
		//DFLT_PROPERTIES.put(ONLY_SITE_CONTENT, "1");
		//DFLT_PROPERTIES.put(INCLUDE_CHILDSITE_CONTENT, "0");
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
			final String templateKey = ajaxSearchDisplayContext.getConfig().getOrDefault(TEMPLATE_KEY, StringPool.BLANK);
			final String structureKey = ajaxSearchDisplayContext.getConfig().getOrDefault(STRUCTURE_KEY, StringPool.BLANK);

			final String disablePaginationStr = ajaxSearchDisplayContext.getConfig().getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO);
			final boolean disablePagination = !Validator.isBlank(disablePaginationStr) && disablePaginationStr.equals("1");

			if(!Validator.isBlank(templateKey) && !Validator.isBlank(structureKey)) {
				//final boolean onlySiteContent = ajaxSearchDisplayContext.getConfig().getOrDefault(ONLY_SITE_CONTENT, StringConstants.ZERO).equals(StringConstants.ONE);
				//final boolean includeChildSiteContent = ajaxSearchDisplayContext.getConfig().getOrDefault(INCLUDE_CHILDSITE_CONTENT, StringConstants.ONE).equals(StringConstants.ONE);
				//final long forceGroupId = GetterUtil.getLong(ajaxSearchDisplayContext.getConfig().getOrDefault(FORCE_GROUP_ID, StringConstants.MINUS_ONE), LongConstants.MINUS_ONE);

				Date fromDate = ajaxSearchDisplayContext.getDate("fechaDesde");
				Date toDate = ajaxSearchDisplayContext.getOneMoreDayDate("fechaHasta");

				totalItems = performSearchAndParse(request,
					response,
					ajaxSearchDisplayContext,
					structureKey,
					templateKey,
					fromDate,
					toDate,
					currentPage,
					pageSize,
					//forceGroupId,
					disablePagination,
					jsonArray);
			}

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
									   final String structureKey,
									   final String templateKey,
									   final Date fromDate,
									   final Date toDate,
									   final int currentPage,
									   final int pageSize,
									   //final long forceGroupId,
									   final boolean disablePagination,
									   final JSONArray jsonArray) throws ParseException, SearchException {

		final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
		final int end = disablePagination ? pageSize : (currentPage * pageSize);

		final SearchContext searchContext = searchingCommon.createPaginatedSearchContext(start, end);
		final BooleanQuery booleanQuery = searchingCommon.createEmptyBooleanQuery();

		//searchingCommon.addGroupIdFilter(themeDisplay, onlySiteContent, includeChildSiteContent, searchContext, booleanQuery);
		if (Validator.isNotNull(themeDisplay)) {
			searchingCommon.addCompanyIdFilter(searchContext, themeDisplay.getCompanyId());
			//searchingCommon.addGroupIdFilter(searchContext, (forceGroupId>0)?forceGroupId:themeDisplay.getScopeGroupId());
		} else {
			searchingCommon.addCompanyIdFilter(searchContext, PortalUtil.getDefaultCompanyId());
			/*if (forceGroupId>0){
				searchingCommon.addGroupIdFilter(searchContext, forceGroupId);
			}*/
		}
		List<String> categories = Arrays.asList(ajaxSearchDisplayContext.getStringValues("categories"));
		searchingJournal.addCategoriesFilter(categories, booleanQuery, Boolean.FALSE);
		searchingJournal.addStructureFilter(Collections.singletonList(structureKey), booleanQuery);

		searchContext.setKeywords(ajaxSearchDisplayContext.getQueryText());

		searchingJournal.addDisplayDateFilter(fromDate, toDate, booleanQuery);

		searchingJournal.setSortDisplayDate(searchContext, Boolean.TRUE);

		searchingCommon.setMustBooleanClauses(searchContext, booleanQuery);

		final Hits hits = searchingJournal.searchJournalArticle(searchContext);
		final int totalItems = hits.getLength();

		final List<Document> documents = hits.toList();
		JSONObject jsonObject = null;

		for (Document document : documents) {
			jsonObject = getResultJson(document, request, response, templateKey, themeDisplay);
			if (Validator.isNotNull(jsonObject)) {
				jsonArray.put(jsonObject);
			}
		}

		return totalItems;
	}

	private final JSONObject getResultJson(final Document document,
										   final PortletRequest request,
										   final PortletResponse response,
										   final String templateKey,
										   final ThemeDisplay themeDisplay) {
		final Locale locale = request.getLocale();
		final JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		final JournalArticle article =
				journalArticleLocalService.fetchLatestArticle(
						GetterUtil.getLong(document.get(locale, Field.GROUP_ID)),
						document.get(locale, Field.ARTICLE_ID),
						WorkflowConstants.STATUS_APPROVED);

		if (Validator.isNotNull(article)) {
			final String html = customJournalUtil.getHTMLFromJournalArticle(
					article,
					themeDisplay,
					request,
					response,
					templateKey
			);

			jsonObject.put("html", html);

		}

		return jsonObject;
	}

	private static final String VIEW = "/views/news/results.jsp";

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
}
