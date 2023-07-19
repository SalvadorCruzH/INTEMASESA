package es.emasesa.intranet.searchframework;

import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.query.Queries;
import es.emasesa.intranet.searchframework.constants.SearchingUtilKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.text.Normalizer;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component(
		immediate = true,
		property = { },
		service = SearchingJournal.class
)
public class SearchingJournal {

	public static final String REGULAR = "regular";
	public static final String PAGINATION_TYPE = "paginationType";
	public static final String PREFFIX_DDM_KEYWORD="ddm__keyword__";
	public static final String DDM_DOUBLEUNDER="__";
	public static final String DDM_FIELD_VALUE = "ddmFieldValue";
	public static final String DDM_FIELD_KEYWORD = "Keyword";
	public static final String DDM_FIELD_TEXT = "Text";
	public static final String DDM_FIELD_TEXT_LOW = DDM_FIELD_TEXT.toLowerCase();
	public static final String DDM_FIELD_KEYWORD_LOW = DDM_FIELD_KEYWORD.toLowerCase();
	public static final String SUFFIX_DDM_NUMBER_SORTABLE="Number_sortable";
	public static final String SUFFIX_DDM_STRING_SORTABLE="String_sortable";
	public static final String DDM_FIELD_ARRAY_DDM_FIELD_NAME = "ddmFieldArray.ddmFieldName";
	public static final String DDM_FIELD_ARRAY = "ddmFieldArray";
	public static final String DDM_FIELD_ARRAY_ = "ddmFieldArray.";
	public static final String DDM__ = "ddm__";

	/** INDEX **/

	public final  Indexer<JournalArticle> getJournalIndexer(){
		return IndexerRegistryUtil.getIndexer(JournalArticle.class);
	}

	public static final String GET_DDM_FIELD_NAME(final String type, final String structureId, final String fieldName, final String languageId){
		final StringBuffer sb = new StringBuffer(DDM__).append(type).append(DDM_DOUBLEUNDER).append(structureId).append(DDM_DOUBLEUNDER)
				.append(fieldName);
		if (!Validator.isBlank(languageId)){
			sb.append(StringPool.UNDERLINE).append(languageId);
		}
		return sb.toString();
	}

	public static final String GET_DDM_FIELD_VALUE_NAME(final String ddmFieldType, final String languageId, final String suffix){
		final StringBuffer sb = new StringBuffer(DDM_FIELD_VALUE).append(ddmFieldType);
		if (!Validator.isBlank(languageId)){
			sb.append(StringPool.UNDERLINE).append(languageId);
		}
		if (!Validator.isBlank(suffix)){
			sb.append(StringPool.UNDERLINE).append(suffix);
		}
		return sb.toString();
	}

	/** Searching **/
	public Hits searchJournalArticle(final SearchContext searchContext) throws SearchException {
		return getJournalIndexer().search(searchContext);
	}

	/** Sorts **/
	public void setSortDisplayDate(final SearchContext searchContext, final boolean descending){
		searchContext.setSorts(SortFactoryUtil.create(Field.DISPLAY_DATE, Sort.INT_TYPE, descending));
	}
	


	/**
	 * Auxiliar method to add title query to main booleanQuery passed
	 * @param title String
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addTitleFilter(ThemeDisplay themeDisplay, String title, BooleanQuery booleanQuery) throws ParseException {
		
		if(title != null && !StringPool.BLANK.equals(title)) {
			BooleanQuery booleanQueryStructure = new BooleanQueryImpl();
			
			// Normalize title, in order to replace char with tilde to corresponding without it
			String normalizedTitle = Normalizer.normalize(title, Normalizer.Form.NFD);
			normalizedTitle = normalizedTitle.replaceAll("[\\p{InCombiningDiacriticalMarks}\\*\\?]", "");
			
			// Localized "title" field name
			final String fieldName = Field.TITLE + StringPool.UNDERLINE + themeDisplay.getLocale();
			
			// Using wildcard Queries in order to allow full text search over 'title' field
			WildcardQuery wildcardKeywordQuery = new WildcardQueryImpl(fieldName, "*" + normalizedTitle + "*");
			booleanQueryStructure.add(wildcardKeywordQuery, BooleanClauseOccur.SHOULD);
			booleanQueryStructure.addTerm(fieldName, normalizedTitle, true, BooleanClauseOccur.SHOULD);
			
			booleanQuery.add(booleanQueryStructure, BooleanClauseOccur.MUST);
		}
		
	}

	/**
	 * Auxiliar method to add structure filter query (if ddmStructureKey passed is not null or blank) to main booleanQuery passed
	 * @param ddmStructureKey String
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addStructureFilter(final String ddmStructureKey, final BooleanQuery booleanQuery) throws ParseException {
		addStructureFilter(ddmStructureKey, booleanQuery, BooleanClauseOccur.MUST);
	}
	public void addStructureFilter(String ddmStructureKey, BooleanQuery booleanQuery, BooleanClauseOccur occur) throws ParseException {
		if(!Validator.isBlank(ddmStructureKey)) {
			booleanQuery.addTerm("ddmStructureKey", ddmStructureKey, Boolean.FALSE, occur);
		}
	}
	public void addStructureFilter(final List<String> ddmStructureKeys, final BooleanQuery booleanQuery) throws ParseException {
		final BooleanQuery structureBQ = new BooleanQueryImpl();
		for (String key: ddmStructureKeys) {
			addStructureFilter(key, structureBQ, BooleanClauseOccur.SHOULD);
		}

		booleanQuery.add(structureBQ, BooleanClauseOccur.MUST);
	}
	

	/**
	 * Auxiliar method to add articleId filter query (if articleId passed is not null or blank) to main booleanQuery passed
	 * @param articleId String
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addArticleIdFilter(final String articleId, final BooleanQuery booleanQuery) throws ParseException {
		addStructureFilter(articleId, booleanQuery, BooleanClauseOccur.MUST);
	}
	public void addArticleIdFilter(String articleId, BooleanQuery booleanQuery, BooleanClauseOccur occur) throws ParseException {
		if(!Validator.isBlank(articleId)) {
			booleanQuery.addTerm("articleId", articleId, Boolean.FALSE, occur);
		}
	}
	public void addArticleIdFilter(final List<String> articleIds, final BooleanQuery booleanQuery) throws ParseException {
		final BooleanQuery articleIdBQ = new BooleanQueryImpl();
		for (String articleId: articleIds) {
			addArticleIdFilter(articleId, articleIdBQ, BooleanClauseOccur.SHOULD);
		}

		booleanQuery.add(articleIdBQ, BooleanClauseOccur.MUST);
	}

	/**
	 * Auxiliar method to add Field Filter for new index settings
	 */
	public void addReqTermFieldFilter(final String fullFieldName, final String ddmFieldType, final String value, final BooleanQuery booleanQuery) throws ParseException {
		final BooleanQuery fieldQuery = new BooleanQueryImpl();
		fieldQuery.addRequiredTerm(DDM_FIELD_ARRAY_DDM_FIELD_NAME, fullFieldName);
		fieldQuery.addRequiredTerm(DDM_FIELD_ARRAY_ +ddmFieldType, value);
		final NestedQuery ddmFieldArrayQuery = new NestedQuery(DDM_FIELD_ARRAY, fieldQuery);
		booleanQuery.add(ddmFieldArrayQuery, BooleanClauseOccur.MUST);
	}

	/**
	 * Auxiliar method to add Field Filter for new index settings
	 * Multiple fields and/or languages
	 */
	public void addReqTermFieldFilter(final String fullFieldName[], final String ddmFieldType[], final String value, final BooleanQuery booleanQuery) throws ParseException {
		if (fullFieldName.length > 0 && fullFieldName.length == ddmFieldType.length){
			final BooleanQuery fullFieldQuery = new BooleanQueryImpl();
			for (int i=0; i<fullFieldName.length; i++){
				final BooleanQuery partialFieldQuery = new BooleanQueryImpl();
				partialFieldQuery.addRequiredTerm(DDM_FIELD_ARRAY_DDM_FIELD_NAME, fullFieldName[i]);
				partialFieldQuery.addRequiredTerm(DDM_FIELD_ARRAY_ +ddmFieldType[i], value);
				final NestedQuery partialDDMFieldArrayQuery = new NestedQuery(DDM_FIELD_ARRAY, partialFieldQuery);
				fullFieldQuery.add(partialDDMFieldArrayQuery, BooleanClauseOccur.SHOULD);
			}

			booleanQuery.add(fullFieldQuery, BooleanClauseOccur.MUST);
		}
	}

	/**
	 * Auxiliar method to add classTypeId filter query (if classTypeId passed is not null or blank) to main booleanQuery passed
	 * @param classTypeId String
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addClassTypeIdFilter(String classTypeId, BooleanQuery booleanQuery) throws ParseException {
		addClassTypeIdFilter(classTypeId, booleanQuery, BooleanClauseOccur.MUST);
	}

	public void addClassTypeIdFilter(String classTypeId, BooleanQuery booleanQuery, BooleanClauseOccur booleanClauseOccur) throws ParseException {
		if(!Validator.isBlank(classTypeId)) {
			booleanQuery.addTerm(Field.CLASS_TYPE_ID, classTypeId, Boolean.FALSE, booleanClauseOccur);
		}
	}

	/**
	 * Auxiliar method to add only approved
	 */
	private final String WORKFLOW_APPROVED = String.valueOf(WorkflowConstants.STATUS_APPROVED);
	public void addOnlyApprovedFilter(BooleanQuery booleanQuery) throws ParseException {
		final BooleanQuery boolQ = new BooleanQueryImpl();
		boolQ.addTerm(Field.STATUS, WORKFLOW_APPROVED);
		booleanQuery.add(boolQ, BooleanClauseOccur.MUST);
	}

	/**
	 * Auxiliar method to add range numeric filter
	 */
	public void addRangeNumericTerm(final String indexFieldName,
										   final long startVal,
										   final long endVal,
										   BooleanQuery booleanQuery) throws ParseException {
		addRangeNumericTerm(indexFieldName, startVal, endVal, booleanQuery, BooleanClauseOccur.MUST);
	}

	public void addRangeNumericTerm(final String indexFieldName,
										   final long startVal,
										   final long endVal,
										   BooleanQuery booleanQuery, BooleanClauseOccur occur) throws ParseException {
		final BooleanQuery booleanQueryRange = new BooleanQueryImpl();
		final long fixedStartVal = (startVal>0)?startVal:0, fixedEndVal = (endVal>0)?endVal:Long.MAX_VALUE;
		booleanQueryRange.addRangeTerm(indexFieldName, fixedStartVal, fixedEndVal);
		booleanQuery.add(booleanQueryRange, occur);
	}

	/**
	 * Auxiliar method to add categories filter queries to main booleanQuery passed
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addCategoriesFilter(List<String> categoryIds, BooleanQuery booleanQuery, boolean optionalCategories) throws ParseException {
		
		// Configure categories filter
		if(Validator.isNotNull(categoryIds)) {
			if (optionalCategories) {
				BooleanQuery catQuery =	new BooleanQueryImpl();
				for(String categoryId:categoryIds){
					catQuery.addTerm(Field.ASSET_CATEGORY_IDS, categoryId, Boolean.FALSE, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(catQuery, BooleanClauseOccur.MUST);
				/**
				 for(String categoryId:categoryIds){
				 BooleanQuery catQuery2 = new BooleanQueryImpl();
				 catQuery2.addTerm(Field.ASSET_CATEGORY_IDS, categoryId, Boolean.FALSE, BooleanClauseOccur.MUST);
				 catQuery.add(catQuery2, BooleanClauseOccur.SHOULD);
				 }
				 booleanQuery.add(catQuery, BooleanClauseOccur.MUST);
				 **/
			} else {
				for(String categoryId:categoryIds){
					booleanQuery.addTerm(Field.ASSET_CATEGORY_IDS, categoryId, Boolean.FALSE, BooleanClauseOccur.MUST);
				}
			}
		}
	}


	/**
	 * Auxiliar method to add categories filter queries to main booleanQuery passed
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addCategoriesFilter(List<List<String>> categoryIdsByVoc, BooleanQuery booleanQuery, boolean optionalCategories, boolean orVocabularyLevel) throws ParseException {
		
		// Configure categories filter
		if(Validator.isNotNull(categoryIdsByVoc)) {
			if (orVocabularyLevel) {
				List<String> categoryIds = 
						categoryIdsByVoc.stream()
					        .flatMap(List::stream)
					        .collect(Collectors.toList());
				addCategoriesFilter(categoryIds, booleanQuery, optionalCategories);
			} else {
				BooleanQuery vocQuery =	new BooleanQueryImpl();
				for(List<String> categoryIds:categoryIdsByVoc){
					addCategoriesFilter(categoryIds, vocQuery, optionalCategories);
				}
				booleanQuery.add(vocQuery, BooleanClauseOccur.MUST);
			}
		}
	}

	/**
	 * Auxiliar method to add categories filter queries to main booleanQuery passed
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addVocabularyFilter(String vocabularyId, BooleanQuery booleanQuery) throws ParseException {
		// Configure vocabulary filter
		if(Validator.isNotNull(vocabularyId)) {
				booleanQuery.addTerm(Field.ASSET_VOCABULARY_ID, vocabularyId, false, BooleanClauseOccur.MUST);
		}
	}
	
	/**
	 * Auxiliar method to add display date filter by range
	 * @param dateFrom Date
	 * @param dateTo Date
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException 
	 */
	public void addDisplayDateFilter(Date dateFrom, Date dateTo, BooleanQuery booleanQuery) throws ParseException {
		final Long startDate = Validator.isNotNull(dateFrom)?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateFrom)).longValue():SearchingUtilKeys.LONG_INDEX_MIN_DATE;
		final Long endDate = (Validator.isNotNull(dateTo))?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateTo)).longValue():SearchingUtilKeys.LONG_INDEX_MAX_DATE;
		// Define display date range
		 BooleanQuery booleanQueryDate = new BooleanQueryImpl();
		 booleanQueryDate.addRangeTerm(Field.DISPLAY_DATE, startDate, endDate);
		 booleanQuery.add(booleanQueryDate, BooleanClauseOccur.MUST);
	}

	/**
	 * Auxiliar method to add display date filter by range (2 ranges)
	 * @param dateFrom Date
	 * @param dateTo Date
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addDisplayDateFilterRangeByRange(final Date dateFrom, final Date dateTo,
											final String filterDateStartField, final String filterDateEndField,
											final BooleanQuery booleanQuery) throws ParseException {

		if (Validator.isNotNull(dateFrom) || Validator.isNotNull(dateTo)) {
			final Long startDate = Validator.isNotNull(dateFrom)?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateFrom)).longValue():SearchingUtilKeys.LONG_INDEX_MIN_DATE;
			final Long endDate = (Validator.isNotNull(dateTo))?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateTo)).longValue()-1:SearchingUtilKeys.LONG_INDEX_MAX_DATE;

			final BooleanQuery booleanQueryDate = new BooleanQueryImpl();

			// Define display date range
			//final BooleanQuery booleanQueryDate1 = new BooleanQueryImpl();
			//final BooleanQuery booleanQueryDate2 = new BooleanQueryImpl();
			//booleanQueryDate1.addRangeTerm(filterDateStartField, startDate, endDate);
			//booleanQueryDate2.addRangeTerm(filterDateEndField, startDate, endDate);

			final BooleanQuery booleanQueryDate3 = new BooleanQueryImpl();
			if (Validator.isNotNull(dateTo)) {
				final BooleanQuery booleanQueryDate3_1 = new BooleanQueryImpl();
				booleanQueryDate3_1.addRangeTerm(filterDateStartField, SearchingUtilKeys.LONG_INDEX_MIN_DATE, endDate);
				booleanQueryDate.add(booleanQueryDate3_1, BooleanClauseOccur.MUST);
			}
			if (Validator.isNotNull(dateFrom)) {
				final BooleanQuery booleanQueryDate3_2 = new BooleanQueryImpl();
				booleanQueryDate3_2.addRangeTerm(filterDateEndField, startDate, SearchingUtilKeys.LONG_INDEX_MAX_DATE);
				booleanQueryDate.add(booleanQueryDate3_2, BooleanClauseOccur.MUST);
			}

			//booleanQueryDate.add(booleanQueryDate1, BooleanClauseOccur.SHOULD);
			//booleanQueryDate.add(booleanQueryDate2, BooleanClauseOccur.SHOULD);
			booleanQueryDate.add(booleanQueryDate3, BooleanClauseOccur.SHOULD);
			booleanQuery.add(booleanQueryDate, BooleanClauseOccur.MUST);
		}
	}

	public static final String getIndexStringSortable(final String fieldName, final String ddmStructureId){
		final StringBuffer sb = new StringBuffer(PREFFIX_DDM_KEYWORD).append(ddmStructureId).append(DDM_DOUBLEUNDER).append(fieldName);
		return sb.toString();
	}

	public void addDateFilterSortable(final Date dateFrom, final Date dateTo,
						   final String dateFieldName,
						   final BooleanQuery booleanQuery) throws ParseException {

		Date fromDate = Validator.isNotNull(dateFrom)?dateFrom:new Date(Long.MIN_VALUE);
		Date toDate = Validator.isNotNull(dateTo)?dateTo:new Date(Long.MAX_VALUE);

		final BooleanQuery rangeQuery = new BooleanQueryImpl();
		rangeQuery.addRangeTerm(dateFieldName, fromDate.getTime(), toDate.getTime());

		booleanQuery.add(rangeQuery, BooleanClauseOccur.MUST);
	}

	@Reference
	protected Queries queries;
}
