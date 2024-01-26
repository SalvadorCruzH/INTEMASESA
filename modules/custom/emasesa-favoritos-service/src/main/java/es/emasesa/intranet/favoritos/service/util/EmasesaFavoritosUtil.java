package es.emasesa.intranet.favoritos.service.util;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.settings.configuration.FavoritosConfiguration;

@Component(
		configurationPid="es.emasesa.intranet.settings.configuration.FavoritosConfiguration",
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
    
    public String getEnlacesFavoritosForUser(User user) {
    	String enlaces = StringPool.BLANK;
    	try {
    	    PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
	        if(!permissionChecker.isCheckGuest()){
	            throw new PortalException("El usuario debe estar logado");
	        }
			long objectEntryByUser = searchObjectByFieldAndUserId(_configuration.objectEnlaceDefinitionId(), user.getUserId(), ""+0);
			if(objectEntryByUser > 0){
				ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(objectEntryByUser);
	        	//Añadir enlaces al JSON enlaces del object
	        	if(Validator.isNotNull(object)) {
	        		enlaces = object.getValues().get("enlaces").toString();
			    }
			}
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return enlaces;
    }
    
    public JSONObject generateJSONEnlacesFavoritos(String title, String url) {
    	JSONObject jsonData = JSONFactoryUtil.createJSONObject();
        jsonData.put("title", title);
        jsonData.put("url", url);
        jsonData.put("id", getRandomId());
        
        return jsonData;
    }
    
    private static int getRandomId() {
        return (int) (Math.random() * 1000000);
    }

    private Map<String, String> toMap(Object nestedFieldArray) {
        return (Map<String, String>) nestedFieldArray;
    }
    
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {

        LOG.debug("Activate FavoritesService");

        _configuration = ConfigurableUtil.createConfigurable(
                FavoritosConfiguration.class, properties);
    }
    
    private static final Log LOG = LoggerUtil.getLog(EmasesaFavoritosUtil.class);

    @Reference
    protected SearchRequestBuilderFactory _searchRequestBuilderFactory;
    
    @Reference
    protected ObjectEntryLocalService _objectEntryLocalService;
    
    private volatile FavoritosConfiguration _configuration;
}
