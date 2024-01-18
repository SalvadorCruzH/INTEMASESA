package es.emasesa.intranet.favoritos.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component(
        immediate = true,
        property = {
        },
        service = EmasesaFavoritosUtil.class
)
public class EmasesaFavoritosUtil {
	public long searchObjectByFieldAndUserId(String objectDefinitionId, long userId, String fieldValue) throws PortalException {

        List<SearchHit> resultHits = searchObjectByFieldAndUserIdInternal(objectDefinitionId, userId, fieldValue);
        if(resultHits.size() > 0){
            Document d = resultHits.get(0).getDocument();
            Optional findAssetEntryId = d.getValues("nestedFieldArray").stream().map(this::toMap)
                    .filter(m -> m.get("fieldName").equals("assetEntryId") && m.get("value_long").equals(""+fieldValue))
                    .findAny();
            return (findAssetEntryId.isPresent()) ? d.getLong("entryClassPK"):0;
        }

        return 0;
    }


    public List<Long> searchObjectByField(String objectDefinitionId, String fieldValue) throws PortalException {

        List<SearchHit> resultHits = searchObjectByFieldAndUserIdInternal(objectDefinitionId, -1, fieldValue);
        List<Long> listaRetorno = new ArrayList<>();
        if(resultHits.size() > 0){
            Document d = resultHits.get(0).getDocument();
            Optional findAssetEntryId = d.getValues("nestedFieldArray").stream().map(this::toMap)
                    .filter(m -> m.get("fieldName").equals("assetEntryId") && m.get("value_long").equals(""+fieldValue))
                    .findAny();
            if(findAssetEntryId.isPresent()){
                listaRetorno.add(d.getLong("entryClassPK"));
            }
        }
        return listaRetorno;
    }

    private List<SearchHit> searchObjectByFieldAndUserIdInternal(String objectDefinitionId, long userId, String fieldValue) throws PortalException{

        SearchContext searchContext = new SearchContext();
        searchContext.setCompanyId(CompanyThreadLocal.getCompanyId());
        searchContext.setStart(-1);
        searchContext.setEnd(10);

        _searchRequestBuilderFactory.builder(
                searchContext
        ).fetchSource(
                true
        ).build();

        BooleanQuery query = new BooleanQueryImpl();

        MatchQuery objectDefinitionQuery = new MatchQuery("objectDefinitionId", objectDefinitionId);
        if(userId > 0) {
            MatchQuery statusbyUserIdQuery = new MatchQuery("statusByUserId", "" + userId);
            query.add(statusbyUserIdQuery, BooleanClauseOccur.MUST.getName());
        }
        TermQuery objectEntryContentQuery = new TermQueryImpl("objectEntryContent", ""+fieldValue);

        query.add(objectDefinitionQuery, BooleanClauseOccur.MUST.getName());
        query.add(objectEntryContentQuery, BooleanClauseOccur.MUST.getName());

        /* Execute search */
        IndexSearcherHelperUtil.search(searchContext, query);

        SearchResponse searchResponse = (SearchResponse)searchContext.getAttribute("search.response");

        return searchResponse.getSearchHits().getSearchHits();
    }

    private Map<String, String> toMap(Object nestedFieldArray) {
        return (Map<String, String>) nestedFieldArray;
    }

    @Reference
    protected SearchRequestBuilderFactory _searchRequestBuilderFactory;
}
