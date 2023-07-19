package es.emasesa.intranet.searchframework;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.search.*;
import org.osgi.service.component.annotations.Component;

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



}
