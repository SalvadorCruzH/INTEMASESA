package es.emasesa.intranet.portlet.ajaxsearch.impl.documents.result;

import java.util.*;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.liferay.asset.kernel.model.AssetCategory;
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
import es.emasesa.intranet.searchframework.SearchingDocuments;
import es.emasesa.intranet.searchframework.SearchingJournal;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
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
public class DocumentsResultImpl implements AjaxSearchResult {

	private final static Log LOG = LoggerUtil.getLog(DocumentsResultImpl.class);

	public static final String FILE_TYPE_ID = "file-type-id";
	public static final String FILE_TYPE_ID_DFLT = "-1";
	private static final Properties DFLT_PROPERTIES = new Properties();


	static {
		DFLT_PROPERTIES.put(AjaxSearchPortletKeys.PROP_BASE_PAGESIZE,
				AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT);
		DFLT_PROPERTIES.put(FILE_TYPE_ID, FILE_TYPE_ID_DFLT);
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
			final long totalItems;
			final int currentPage = ajaxSearchDisplayContext.getCurrentPage();
			final int pageSize = ajaxSearchDisplayContext.getPageSize();
			final JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			totalItems = performSearchAndParse(
					request,
					response,
					ajaxSearchDisplayContext,
					currentPage,
					pageSize,
					jsonArray);

			final int totalPages = (((int) totalItems + pageSize - 1) / pageSize);

			return new AjaxMessage(
					AjaxMessage.STATUS.OK,
					AjaxMessage.MESSAGES_DEFAULT.OK,
					new AjaxSearchJsonModel(
							currentPage,
							totalPages,
							totalItems,
							pageSize,
							jsonArray).getJsonObject());

		} catch (ParseException | SearchException e) {
			if(LOG.isInfoEnabled()) {
				LOG.info("Error At search in indexes: " + e.getLocalizedMessage());
			}
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
									   final JSONArray jsonArray) throws ParseException, SearchException {

		final ThemeDisplay themeDisplay = ajaxSearchDisplayContext.getThemeDisplay();

		final int start = ((currentPage - 1) * pageSize);
		final int end = (currentPage * pageSize);

		final SearchContext searchContext = searchingCommon.createPaginatedSearchContext(start, end);
		final BooleanQuery booleanQuery = searchingCommon.createEmptyBooleanQuery();

		if (Validator.isNull(themeDisplay)) {
			searchingCommon.addCompanyIdFilter(searchContext, PortalUtil.getDefaultCompanyId());
		} else {
			searchingCommon.addCompanyIdFilter(searchContext, themeDisplay.getCompanyId());
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.addSelectedFieldNames(Field.ANY);


		Date fromDate = ajaxSearchDisplayContext.getDate("fechaDesde");
		Date toDate = ajaxSearchDisplayContext.getOneMoreDayDate("fechaHasta");
		searchingDocuments.addPublishDateFilter(fromDate, toDate, booleanQuery);

		// Set sort
		String[] sortBy = ajaxSearchDisplayContext.getString("sortby").split(StringPool.DASH);

		if(Validator.isNotNull(sortBy)) {
			if(sortBy[0].equals("date")) {
				searchContext.setSorts(sortFactory.create(Field.PUBLISH_DATE, sortBy[1].equals("asc")?Boolean.FALSE:Boolean.TRUE));
			} else if(sortBy[0].equals("name")) {
				searchContext.setSorts(sortFactory.create(Field.TITLE, sortBy[1].equals("asc")?Boolean.FALSE:Boolean.TRUE));
			}
		} else {
			searchContext.setSorts(sortFactory.create(Field.TITLE, Boolean.FALSE));
		}


		List<String> categories = Arrays.asList(ajaxSearchDisplayContext.getStringValues("catSelected"));
		List<String> subcategories = Arrays.asList(ajaxSearchDisplayContext.getStringValues("subCatSelected"));
		List<String> searchCategories;
		if(!categories.isEmpty()){
			if(subcategories.isEmpty()) {
				AssetCategory category = assetCategoryLocalService.fetchAssetCategory(Long.parseLong(categories.get(0)));
				if(!assetCategoryLocalService.getChildCategories(category.getCategoryId()).isEmpty()){
					final List<String> auxSearchCategories = new ArrayList<>();
					assetCategoryLocalService.getChildCategories(category.getCategoryId()).forEach(subcategory -> auxSearchCategories.add(String.valueOf(subcategory.getCategoryId())));
					searchCategories = auxSearchCategories;
				} else {
					searchCategories = categories;
				}
			} else {
				searchCategories = subcategories;
			}
			searchingDocuments.addCategoriesFilter(searchCategories, booleanQuery, Boolean.TRUE);
		}


		long fileTypeId = GetterUtil.getLong(ajaxSearchDisplayContext.getConfig().getOrDefault(FILE_TYPE_ID, FILE_TYPE_ID_DFLT));

		searchingDocuments.addFileTypeFilter(fileTypeId, booleanQuery, BooleanClauseOccur.MUST);

		searchingCommon.setMustBooleanClauses(searchContext, booleanQuery);

		final Hits hits = searchingDocuments.search(searchContext);

		final int totalItems = hits.getLength();
		final List<Document> documents = hits.toList();
		JSONObject jsonObject = null;
		// Fill data to build template
		for (Document document : documents) {
			jsonObject = getResultJson(document, themeDisplay);
			if(Validator.isNotNull(jsonObject)) {
				jsonArray.put(jsonObject);
			}
		}

		return totalItems;
	}

	private final JSONObject getResultJson(final Document document,
										   final ThemeDisplay themeDisplay) {
		final JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		try {
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(GetterUtil.getLong(document.getField("entryClassPK").getValue(), 0));
			jsonObject.put("docSize", fileEntry.getSize());
			jsonObject.put("docUrl", dlurlHelper.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, Boolean.FALSE, Boolean.FALSE));

		} catch (PortalException e) {
			throw new RuntimeException(e);
		}
		jsonObject.put("docName", document.get(themeDisplay.getLocale(), "title"));
		String date = document.get(themeDisplay.getLocale(), "publishDate");
		String dateFormatted = date.substring(6, 8) + "/" + date.substring(4, 6) + "/" + date.substring(0, 4);
		jsonObject.put("publishDate", dateFormatted);
		String expirationDate = document.get(themeDisplay.getLocale(), "expirationDate");
		String expirationDateFormatted = expirationDate.substring(6, 8) + "/" + expirationDate.substring(4, 6) + "/" + expirationDate.substring(0, 4);
		jsonObject.put("expirationDate", expirationDateFormatted);
		AssetCategory category = null;
		try {
			category = assetCategoryLocalService.getCategory(Long.parseLong(document.get(themeDisplay.getLocale(), "assetCategoryIds")));
		} catch (PortalException e) {
			throw new RuntimeException(e);
		}

		if(category.getParentCategoryId() != 0) {
			AssetCategory parentCategory = assetCategoryLocalService.fetchAssetCategory(category.getParentCategoryId());
			jsonObject.put("category", parentCategory.getTitle(themeDisplay.getLocale()) + StringPool.SPACE + StringPool.GREATER_THAN + StringPool.SPACE + category.getTitle(themeDisplay.getLocale()));
		} else {
			jsonObject.put("category", category.getTitle(themeDisplay.getLocale()));
		}

		return jsonObject;
	}

	private static final String VIEW = "/views/documents/results.jsp";

	@Override
	public String getResultsView(PortletRequest request, PortletResponse response) {
		return VIEW;
	}

	@Reference
	JournalArticleLocalService journalArticleLocalService;

	@Reference
	AjaxSearchUtil ajaxSearchUtil;

	@Reference
	AssetEntryLocalService assetEntryLocalService;

	@Reference
	AssetCategoryLocalService assetCategoryLocalService;

	@Reference
	DDMStructureLocalService ddmStructureLocalService;

	@Reference
	XMLDocumentUtil xmlDocumentUtil;

	@Reference
	CustomJournalUtil customJournalUtil;

	@Reference
	SearchingDocuments searchingDocuments;

	@Reference
	SearchingCommon searchingCommon;


	@Reference
	protected SortFactory sortFactory;

	@Reference
	DLURLHelper dlurlHelper;
}
