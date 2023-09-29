package es.emasesa.intranet.searchframework;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.searchframework.constants.SearchingUtilKeys;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
		immediate = true,
		property = { },
		service = SearchingDocuments.class
)
public class SearchingDocuments {

	/** INDEX **/
	public final  Indexer<DLFileEntry> getIndexer(){
		return IndexerRegistryUtil.getIndexer(DLFileEntry.class);
	}

	/** Searching **/
	public Hits search(final SearchContext searchContext) throws SearchException {
		return getIndexer().search(searchContext);
	}

	/**
	 *
	 * @param fileEntryTypeId
	 * @param booleanQuery
	 * @throws ParseException
	 */
	public void addFileTypeFilter(final long fileEntryTypeId, final BooleanQuery booleanQuery, final BooleanClauseOccur occur) throws ParseException {
		booleanQuery.addTerm("fileEntryTypeId", String.valueOf(fileEntryTypeId), Boolean.FALSE, occur);
		booleanQuery.addTerm("classTypeId", String.valueOf(fileEntryTypeId), Boolean.FALSE, occur);
	}

	/** Sorts **/
	public void setSortDisplayDate(final SearchContext searchContext, final boolean descending){
		searchContext.setSorts(SortFactoryUtil.create(Field.DISPLAY_DATE, Sort.INT_TYPE, descending));
	}

	/**
	 * Auxiliar method to add display date filter by range
	 * @param dateFrom Date
	 * @param dateTo Date
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addPublishDateFilter(Date dateFrom, Date dateTo, BooleanQuery booleanQuery) throws ParseException {
		final Long startDate = Validator.isNotNull(dateFrom)?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateFrom)).longValue():SearchingUtilKeys.LONG_INDEX_MIN_DATE;
		final Long endDate = (Validator.isNotNull(dateTo))?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateTo)).longValue():SearchingUtilKeys.LONG_INDEX_MAX_DATE;
		// Define display date range
		BooleanQuery booleanQueryDate = new BooleanQueryImpl();
		booleanQueryDate.addRangeTerm(Field.PUBLISH_DATE, startDate, endDate);
		booleanQuery.add(booleanQueryDate, BooleanClauseOccur.MUST);
	}
	/**
	 * Auxiliar method to add modified date filter by range
	 * @param dateFrom Date
	 * @param dateTo Date
	 * @param booleanQuery BooleanQuery
	 * @throws ParseException
	 */
	public void addModifiedDateFilter(Date dateFrom, Date dateTo, BooleanQuery booleanQuery) throws ParseException {
		final Long startDate = Validator.isNotNull(dateFrom)?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateFrom)).longValue():SearchingUtilKeys.LONG_INDEX_MIN_DATE;
		final Long endDate = (Validator.isNotNull(dateTo))?Long.valueOf(SearchingUtilKeys.INDEX_DATE_FORMAT.format(dateTo)).longValue():SearchingUtilKeys.LONG_INDEX_MAX_DATE;
		// Define modified date range
		BooleanQuery booleanQueryDate = new BooleanQueryImpl();
		booleanQueryDate.addRangeTerm(Field.MODIFIED_DATE, startDate, endDate);
		booleanQuery.add(booleanQueryDate, BooleanClauseOccur.MUST);
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
			} else {
				for(String categoryId:categoryIds){
					booleanQuery.addTerm(Field.ASSET_CATEGORY_IDS, categoryId, Boolean.FALSE, BooleanClauseOccur.MUST);
				}
			}
		}
	}


}
