package es.emasesa.intranet.searchframework;

import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import org.osgi.service.component.annotations.Component;

import com.liferay.object.model.ObjectEntry;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.NestedQuery;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

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
		return indexerRegistry.getIndexer("com.liferay.object.model.ObjectDefinition#" + objectDefinitionId);
	}


	/** Searching **/
	public Hits searchObject(final String objectDefinitionId, final SearchContext searchContext) throws SearchException {
		return getObjectIndexer(objectDefinitionId).search(searchContext);
	}

	/** Searching **/
	public List<Document> searchObjects(String[] objectDefinitionIds, SearchContext searchContext) throws SearchException, ParseException {

		List<Document> listDocuments = new ArrayList<>();

		for (String objectDefinitionId : objectDefinitionIds) {
			BooleanQuery query = new BooleanQueryImpl();
			MatchQuery entryClassNameQuery = new MatchQuery("entryClassName", "com.liferay.object.model.ObjectDefinition#" + objectDefinitionId);
			query.add(entryClassNameQuery, BooleanClauseOccur.MUST.getName());
			MatchQuery userIdQuery = new MatchQuery("userId", "" + ServiceContextThreadLocal.getServiceContext().getUserId());
			query.add(userIdQuery, BooleanClauseOccur.MUST.getName());
			Hits hits = indexSearcherHelper.search(searchContext, query);
			List<Document> hitsList = hits.toList();
            listDocuments.addAll(hitsList);
		}
		return listDocuments;
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

	/** boolean clauses, create more if needed **/
	public void setMustBooleanClauses(final SearchContext searchContext, final Query query){
		BooleanClause<Query> booleanClause = BooleanClauseFactoryUtil.create(query, BooleanClauseOccur.MUST.getName());
		searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});
	}

	@Reference
	IndexSearcherHelper indexSearcherHelper;

	@Reference
	IndexerRegistry indexerRegistry;
}
