package es.emasesa.intranet.searchframework;

import org.osgi.service.component.annotations.Component;

import com.liferay.object.model.ObjectEntry;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.NestedQuery;

@Component(
		immediate = true,
		property = { },
		service = SearchingObject.class
)
public class SearchingObject {
	public static final long COMPANY_LEVEL_GROUP_ID=0;
	public static final String NESTED_FIELD_ARRAY_FIELD_NAME = "nestedFieldArray.fieldName";
	public static final String NESTED_FIELD_ARRAY = "nestedFieldArray";
	public static final String NESTED_FIELD_ARRAY_ = "nestedFieldArray.";
	public static final String VALUE_LONG = "value_long";
	public static final String VALUE_KEYWORD_LOWERCASE = "value_keyword_lowercase";

	/** INDEX **/

	public final  Indexer<ObjectEntry> getObjectIndexer(final String objectDefinitionId){
		return IndexerRegistryUtil.getIndexer("com.liferay.object.model.ObjectDefinition#" + objectDefinitionId);
	}


	/** Searching **/
	public Hits searchObject(final String objectDefinitionId, final SearchContext searchContext) throws SearchException {
		return getObjectIndexer(objectDefinitionId).search(searchContext);
	}
	
	public Hits searchObject(final String objectDefinitionId, final SearchContext searchContext, String... fieldFilter) throws SearchException {
		return getObjectIndexer(objectDefinitionId).search(searchContext, fieldFilter);
	}

	/** Sorts **/
	public void setSortPublishDate(final SearchContext searchContext, final boolean descending){
		searchContext.setSorts(SortFactoryUtil.create(Field.PUBLISH_DATE, Sort.INT_TYPE, descending));
	}
	
	public void addObjectFieldFilter(final String fieldName, final String fieldValueName, final String fieldValue, final BooleanQuery booleanQuery) throws ParseException {
		BooleanQuery booleanQueryFieldValue = new BooleanQueryImpl();
        booleanQueryFieldValue.addRequiredTerm(NESTED_FIELD_ARRAY_+fieldValueName, fieldValue);

        addObjectFieldBooleanQuery(fieldName, booleanQueryFieldValue, booleanQuery);
	}
	
	public void addObjectFieldFilter(final String fieldName, final String fieldValueName, final long fieldValue, final BooleanQuery booleanQuery) throws ParseException {
		BooleanQuery booleanQueryFieldValue = new BooleanQueryImpl();
		booleanQueryFieldValue.addRequiredTerm(NESTED_FIELD_ARRAY_+fieldValueName, fieldValue);
		
        addObjectFieldBooleanQuery(fieldName, booleanQueryFieldValue, booleanQuery);
	}
	
	private void addObjectFieldBooleanQuery(final String fieldName, final BooleanQuery booleanQueryFieldValue, final BooleanQuery booleanQuery) throws ParseException {

		booleanQueryFieldValue.addRequiredTerm(NESTED_FIELD_ARRAY_FIELD_NAME, fieldName);
		final NestedQuery ddmFieldArrayQuery = new NestedQuery(NESTED_FIELD_ARRAY, booleanQueryFieldValue);
		booleanQuery.add(ddmFieldArrayQuery, BooleanClauseOccur.MUST);

	}



}
