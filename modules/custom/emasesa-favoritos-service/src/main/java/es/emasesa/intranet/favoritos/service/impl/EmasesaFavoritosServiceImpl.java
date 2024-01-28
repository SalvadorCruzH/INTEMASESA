package es.emasesa.intranet.favoritos.service.impl;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.favoritos.service.EmasesaFavoritosService;
import es.emasesa.intranet.favoritos.service.util.EmasesaFavoritosUtil;
import es.emasesa.intranet.settings.configuration.FavoritosConfiguration;

@Component(
        configurationPid="es.emasesa.intranet.settings.configuration.FavoritosConfiguration",
        immediate = true,
        property = {
        },
        service = EmasesaFavoritosService.class
)
public class EmasesaFavoritosServiceImpl implements EmasesaFavoritosService{

	 @Override
	    public boolean addFav(String classPK, long assetEntryClassId, long groupId, String title, String url, String fileExtension, String ddmStructureKey) throws PortalException {

	        PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
	        if(!permissionChecker.isCheckGuest()){
	            throw new PortalException("El usuario debe estar logado");
	        }

	        long objectEntryId = _emasesaFavoritosUtil.searchObjectByFieldAndUserId(_configuration.objectDefinitionId(), permissionChecker.getUserId(), ""+classPK);
	        if(objectEntryId > 0){
	            return true;
	        }

	        User user = permissionChecker.getUser();

	        Map<String, Serializable> params = new HashMap<>();
	        params.put("assetEntryId", classPK);
	        params.put("classNameId", assetEntryClassId);
	        params.put("title", title);
	        params.put("url", url);
	        params.put("assetEntryGroupId",groupId);
	        params.put("r_user_userId", user.getUserId());
	        params.put("ddmStructureKey", ddmStructureKey);
	        if(!Validator.isBlank(fileExtension)) {
	            params.put("fileExtension", fileExtension);
	        }
	        ObjectEntry objectEntry = _objectEntryLocalService.addObjectEntry(user.getUserId(), groupId, Long.valueOf(_configuration.objectDefinitionId()), params, ServiceContextThreadLocal.getServiceContext());
	        _objectEntryLocalService.updateAsset(user.getUserId(), objectEntry, new long[0], new String[0], new long[0], null);
	        return true;
	    }

	    @Override
	    public boolean deleteFav(String classPK) throws PortalException {

	        PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
	        if(!permissionChecker.isCheckGuest()){
	            throw new PortalException("El usuario debe estar logado");
	        }

	        long objectEntryId = _emasesaFavoritosUtil.searchObjectByFieldAndUserId(_configuration.objectDefinitionId(), permissionChecker.getUserId(), ""+classPK);
	        if(objectEntryId == 0){
	            return true;
	        }
	        _objectEntryLocalService.deleteObjectEntry(objectEntryId);
	        return true;
	    }

	    @Override
	    public boolean isFav(String classPK) throws PortalException {

	        if(Validator.isBlank(_configuration.objectDefinitionId())){
	            throw new PortalException("Es necesario configurar el objectDefinitionId");
	        }
	        PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();

	        return _emasesaFavoritosUtil.searchObjectByFieldAndUserId(_configuration.objectDefinitionId(), permissionChecker.getUserId(), ""+classPK) > 0?true: false ;
	    }
	    
	    public Hits searchFavoritesByUserAndType(ThemeDisplay themeDisplay, String classNameId, int start, int end) throws ParseException, SearchException {

	        BooleanQuery query = new BooleanQueryImpl();
	        MatchQuery objectDefinitionQuery = new MatchQuery("objectDefinitionId", _configuration.objectDefinitionId());
	        MatchQuery statusbyUserIdQuery = new MatchQuery("statusByUserId", "" + themeDisplay.getUserId());
	        MatchQuery objectEntryContentQuery = new MatchQuery("objectEntryContent", ""+classNameId);

	        query.add(objectDefinitionQuery, BooleanClauseOccur.MUST.getName());
	        query.add(statusbyUserIdQuery, BooleanClauseOccur.MUST.getName());
	        query.add(objectEntryContentQuery, BooleanClauseOccur.MUST.getName());

	        SearchContext searchContext = new SearchContext();
	        searchContext.setCompanyId(themeDisplay.getCompanyId());
	        searchContext.setGroupIds(null);
	        searchContext.setUserId(themeDisplay.getUserId());
	        searchContext.setAttribute(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
	        searchContext.setSorts(new Sort(Field.MODIFIED_DATE, true));
	        searchContext.setStart(start);
	        searchContext.setEnd(end);

	        return  IndexSearcherHelperUtil.search(searchContext, query);

	    }

	    public Hits searchFavoritesJournalsArticleByUserAndDDMStructureKey(ThemeDisplay themeDisplay, String ddmStructureKey,int start,int end) throws ParseException, SearchException {

	        BooleanQuery query = new BooleanQueryImpl();
	        MatchQuery objectDefinitionQuery = new MatchQuery("objectDefinitionId", _configuration.objectDefinitionId());
	        MatchQuery statusbyUserIdQuery = new MatchQuery("statusByUserId", "" + themeDisplay.getUserId());
	        MatchQuery objectEntryContentQuery = new MatchQuery("objectEntryContent", ddmStructureKey);

	        query.add(objectDefinitionQuery, BooleanClauseOccur.MUST.getName());
	        query.add(statusbyUserIdQuery, BooleanClauseOccur.MUST.getName());
	        query.add(objectEntryContentQuery, BooleanClauseOccur.MUST.getName());

	        SearchContext searchContext = new SearchContext();
	        searchContext.setCompanyId(themeDisplay.getCompanyId());
	        searchContext.setGroupIds(null);
	        searchContext.setUserId(themeDisplay.getUserId());
	        searchContext.setAttribute(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
	        searchContext.setSorts(new Sort(Field.MODIFIED_DATE, true));
	        searchContext.setStart(start);
	        searchContext.setEnd(end);

	        return  IndexSearcherHelperUtil.search(searchContext, query);
	    }

	    public boolean deleteFavs(String classPK) throws PortalException {

	        PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
	        if(!permissionChecker.isCheckGuest()){
	            throw new PortalException("El usuario debe estar logado");
	        }

	        List<Long> objectEntryIdList = _emasesaFavoritosUtil.searchObjectByField(_configuration.objectDefinitionId(), ""+classPK);
	        if(objectEntryIdList.size() > 0){
	           for(Long objectEntryId: objectEntryIdList){
	               _objectEntryLocalService.deleteObjectEntry(objectEntryId);
	           }
	        }

	        return true;
	    }
	    
	    @Override
		public boolean addEnlace(String classPK, long assetEntryClassId, long groupId, String title, String url,
				String ddmStructureKey) throws PortalException {
	    	
	    	LOG.debug("Entra en el metodo addEnlaces");
	    	 PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
		        if(!permissionChecker.isCheckGuest()){
		            throw new PortalException("El usuario debe estar logado");
		        }

		        long objectEntryId = _emasesaFavoritosUtil.searchObjectByFieldAndUserId(_configuration.objectEnlaceDefinitionId(), permissionChecker.getUserId(), ""+classPK);
		        
		        if(objectEntryId > 0){
		        	LOG.debug("Se obtiene el objectEntryId: " + objectEntryId);
		        	ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(objectEntryId);
		        	
		        	//Añadir enlaces al JSON enlaces del object
		        	if(Validator.isNotNull(object)) {
		        		LOG.debug("Se encuentra el object: " + objectEntryId);
		        		Map <String,Serializable> map = object.getValues();
		        		
		        	    JSONArray jsonArray;
		        	    if (map.containsKey("enlaces")) {
		        	    	LOG.debug("Obtener el JSONArray actual ");
		        	        jsonArray = JSONFactoryUtil.createJSONArray((String) map.get("enlaces"));
		        	    } else {
		        	    	LOG.debug("Crear uno nuevo si no existe");
		        	        jsonArray = JSONFactoryUtil.createJSONArray();
		        	    }
		        	    
		        		jsonArray.put(_emasesaFavoritosUtil.generateJSONEnlacesFavoritos(title, url));
		        		LOG.debug("jsonArray: " + jsonArray);
		        		
		        		map.put("enlaces", jsonArray);
		        		ServiceContext serviceContext = new ServiceContext();
						_objectEntryLocalService.updateObjectEntry(
									object.getUserId(), object.getObjectEntryId(), map, 
									serviceContext);
		        	}else {
		        		LOG.debug("No se encuentra el object: " + objectEntryId);
		        	}
		            return true;
		        }
		        
		        LOG.debug("El usuario no tiene enlaces, se procede a añadirlos.");
		        User user = permissionChecker.getUser();

		        Map<String, Serializable> params = new HashMap<>();
		        params.put("assetEntryId", classPK);
		        params.put("classNameId", assetEntryClassId);
		        params.put("title", title);
		        params.put("url", url);
		        params.put("assetEntryGroupId",groupId);
		        params.put("r_userEnlace_userId", user.getUserId());
		        params.put("ddmStructureKey", ddmStructureKey);
		        params.put("enlaces", _emasesaFavoritosUtil.generateJSONArrayEnlacesFavoritos(title, url));
		        
		        LOG.debug("Creando el nuevo object.");
		       
		        ObjectEntry objectEntry = _objectEntryLocalService.addObjectEntry(user.getUserId(), groupId, Long.valueOf(_configuration.objectEnlaceDefinitionId()), params, ServiceContextThreadLocal.getServiceContext());
		        LOG.debug("Actualizando el asset.");
		        _objectEntryLocalService.updateAsset(user.getUserId(), objectEntry, new long[0], new String[0], new long[0], null);
		        return true;
		}

		@Override
		public boolean deleteEnlace(String classPK, String idEnlace) throws PortalException {
			
			LOG.debug("Entra en el metodo deleteEnlace");
			 PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
		        if(!permissionChecker.isCheckGuest()){
		            throw new PortalException("El usuario debe estar logado");
		        }

		        long objectEntryId = _emasesaFavoritosUtil.searchObjectByFieldAndUserId(_configuration.objectEnlaceDefinitionId(), permissionChecker.getUserId(), ""+classPK);
		        if(objectEntryId == 0){
		        	LOG.debug("No existe el object" );
		            return true;
		        }else {
		        	LOG.debug("Se obtiene el objectEntryId: " + objectEntryId);
		        	ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(objectEntryId);
		        	
		        	if(Validator.isNotNull(object)) {
		        		LOG.debug("Se obtiene el object" );
		        		Map <String,Serializable> map = object.getValues();
		        		JSONArray jsonArray = JSONFactoryUtil.createJSONArray((String) object.getValues().get("enlaces"));
		        		LOG.debug("jsonArray: " + jsonArray.toString());
		        		
		        		if(Validator.isNotNull(jsonArray) && jsonArray.length() > 0) {
		        			LOG.debug("Crear una nueva matriz JSON para almacenar los enlaces filtrados");
			        	    JSONArray filteredArray = JSONFactoryUtil.createJSONArray();
			        	    LOG.debug("Iterar sobre los elementos de la matriz y agregar solo los que no coinciden con el enlaceId");
			        	    for (int i = 0; i < jsonArray.length(); i++) {
			        	        JSONObject enlace = jsonArray.getJSONObject(i);
			        	        if (!String.valueOf(enlace.get("id")).equals(String.valueOf(idEnlace))) {
			        	            filteredArray.put(enlace);
			        	        }
			        	    }
			        	    LOG.debug("Actualizar el mapa con la nueva matriz JSON filtrada: " + filteredArray.toString());
			        	    map.put("enlaces", filteredArray.toString());
			        	    LOG.debug("Actualizar el objeto de entrada");
			        	    
			        	    ServiceContext serviceContext = new ServiceContext();
			        	    _objectEntryLocalService.updateObjectEntry(
			        	            object.getUserId(), object.getObjectEntryId(), map, serviceContext);
		        			
		        		}else {
		        			LOG.debug("Enlaces vacios");
		        		}
		        	}
		            return true;
		        }
		}

		@Override
		public boolean editEnlace(String classPK, String idEnlace, String title, String url) throws PortalException {
		    PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
		    if (!permissionChecker.isCheckGuest()) {
		        throw new PortalException("El usuario debe estar logado");
		    }

		    long objectEntryId = _emasesaFavoritosUtil.searchObjectByFieldAndUserId(_configuration.objectEnlaceDefinitionId(), permissionChecker.getUserId(), "" + classPK);
		    if (objectEntryId == 0) {
		        return true;
		    } else {
		        ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(objectEntryId);
		        // Añadir enlaces al JSON enlaces del object
		        if (Validator.isNotNull(object)) {
		            Map<String, Serializable> map = object.getValues();
		            JSONArray jsonArray = JSONFactoryUtil.createJSONArray((String) object.getValues().get("enlaces"));

		            if (Validator.isNotNull(jsonArray) && jsonArray.length() > 0) {
		                // Iterar sobre los elementos de la matriz y buscar el enlace a editar
		                for (int i = 0; i < jsonArray.length(); i++) {
		                    JSONObject enlace = jsonArray.getJSONObject(i);
		                    // Convertir el ID del enlace del JSON a String para la comparación
		                    String jsonEnlaceId = String.valueOf(enlace.get("id"));

		                    if (jsonEnlaceId.equals(idEnlace)) {
		                        // Encontramos el enlace a editar
		                        // Realizar las modificaciones necesarias
		                        enlace.put("title", title);
		                        enlace.put("url", url);

		                        // Actualizar el mapa con la matriz JSON modificada
		                        map.put("enlaces", jsonArray.toString());

		                        // Actualizar el objeto de entrada
		                        ServiceContext serviceContext = new ServiceContext();
		                        _objectEntryLocalService.updateObjectEntry(
		                                object.getUserId(), object.getObjectEntryId(), map, serviceContext);

		                        return true;
		                    }
		                }
		            } else {
		                LOG.debug("Enlaces vacíos");
		            }
		        }
		        // No se encontró el enlace a editar
		        return false;
		    }
		}

	    @Activate
	    @Modified
	    protected void activate(Map<String, Object> properties) {

	        LOG.debug("Activate FavoritesService");

	        _configuration = ConfigurableUtil.createConfigurable(
	                FavoritosConfiguration.class, properties);
	    }

	    @Reference
	    protected EmasesaFavoritosUtil _emasesaFavoritosUtil;
	    @Reference
	    protected ObjectEntryLocalService _objectEntryLocalService;

	    @Reference
	    protected ObjectDefinitionLocalService _objectDefinitionLocalService;

	    @Reference
	    protected SearchRequestBuilderFactory _searchRequestBuilderFactory;

	    private volatile FavoritosConfiguration _configuration;
	    private static final Log LOG = LoggerUtil.getLog(EmasesaFavoritosServiceImpl.class);
		
}
