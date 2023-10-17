package es.emasesa.intranet.base.util;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomWorkflowUtil.class
)
public class CustomWorkflowUtil {
	
	  /**
     * Retrive roles and status object
     * @param workflowContext
     * @param rolName
     * @param status
     * @return
     */
    public List<Role> assignWorkflowRoleAndStatus(Map<String, Serializable> workflowContext, String rolName, String estadoObjeto) {
    	
        List<Role> roles = new ArrayList<>();
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        
        try {
	        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
	        long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	       
        	ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
        	
        	if(Validator.isNotNull(object)) {        		
            	object.setStatus(1);
            	 //TODO - pensar como hacer para no actualizar el objeto tantas veces y que a la vez sea todo genérico para reutilizar metodos
            	changeObjectStatus(object, estadoObjeto);
            	updateObjectHistory(object.getObjectEntryId(), estadoObjeto, 0, rolName);
            	
            	Role role = _roleLocalService.getRole(companyId, rolName);
            	roles.add(role);
        	}
        } catch (PortalException e) {
            LOG.error("Se ha producido un error al intentar recuperar el rol por el nombre:  " + rolName, e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }
        return roles;
    }
    
    /**
     * Change status object
     * @param object
     * @param estadoObjeto
     * @return ObjectEntry
     */
    public void changeObjectStatus(ObjectEntry object, String estadoObjeto) {
    	try {
	    	Map <String,Serializable> map = object.getValues();
	    	map.put("estadoObjeto", estadoObjeto);
	    	
	    	ServiceContext serviceContext = new ServiceContext();
	    	 _objectEntryLocalService.updateObjectEntry(object.getUserId(), object.getObjectEntryId(), map, serviceContext);
	    	
		} catch (PortalException e) {
			LOG.error("Error el actualziar el estado del objecto" + e);
		}
    }
    
    /**
     * Update History
     * @param objectEntryId
     * @param estadoObjeto
     * @param userId
     * @param rolName
     * 
     */
    public void updateObjectHistory(long objectEntryId, String estadoObjeto, long userId, String rolName) {
    	
    	try {
		
    		ObjectEntry o = _objectEntryLocalService.getObjectEntry(objectEntryId);
			Map<String,Serializable> map = o.getValues();
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray((String) o.getValues().get("historicoEstado"));
			JSONObject datosHistorico = JSONFactoryUtil.createJSONObject();
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			
			if(userId != 0) {
				datosHistorico.put("userId", userId);
    		}else {
    			datosHistorico.put("userId", "");
    		}
			datosHistorico.put("estadoObjeto", estadoObjeto);
			datosHistorico.put("rolName", rolName);
			datosHistorico.put("fechaCambioEstado", now.format(formatter));
			jsonArray.put(datosHistorico);
			
			map.put("historicoEstado", jsonArray);
			
			ServiceContext serviceContext = new ServiceContext();
			_objectEntryLocalService.updateObjectEntry(
					o.getUserId(), o.getObjectEntryId(), map, 
					serviceContext);
		
		} catch (PortalException e) {
			LOG.error("Error el actualziar el hitórico del objecto" + e);
		}
    }
    
    
    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;
    
    @Reference
    private RoleLocalService _roleLocalService;

    private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);
}
