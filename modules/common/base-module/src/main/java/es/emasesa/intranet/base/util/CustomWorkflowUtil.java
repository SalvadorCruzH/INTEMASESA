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
        
        try {
        	LoggerUtil.debug(LOG,"Asignando el rol: " + rolName + " y el estado: " + estadoObjeto);
        	
	        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
	        long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	       
        	ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
        	
        	if(Validator.isNotNull(object)) {
            	object = setObjectStatus(object, estadoObjeto);
            	object = setObjectHistory(object.getObjectEntryId(), estadoObjeto, 0, rolName);
            	updateObject(object);
            	
            	Role role = _roleLocalService.getRole(companyId, rolName);
            	roles.add(role);
            	
            	LoggerUtil.debug(LOG,"Rol y estado asignado");
        	}
        } catch (PortalException e) {
            LOG.error("Se ha producido un error al intentar recuperar el rol por el nombre:  " + rolName, e);
        }
        return roles;
    }
    
    
    /**
     * Save Object on SIGD
     * @param workflowContext
     * 
     */
    public void saveObjectOnSIGD(Map<String, Serializable> workflowContext) {
    	
    	  LoggerUtil.debug(LOG, "Guardando el objeto en el gestor documnetal SIGD");
    	  long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
    	  ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
    	  
    	//TODO - Hacer el servicio para mandar el documento el gestor documentar en base64
    }
    
    /**
     * Send object portaFirmas
     * @param workflowContext
     * 
     */
    public void sendObjectPortafirmas(Map<String, Serializable> workflowContext) {
    	
    	  LoggerUtil.debug(LOG, "Envio de objeto a portafirmas");
    	  
    	//TODO - Hacer el servicio para mandar el objecto a portafirmas en formato PDF
    }
    
    
    /**
     * Create PDF
     * @param workflowContext
     * 
     */
    public void createPDF(Map<String, Serializable> workflowContext) {
    	//TODO - Hacer el método de crear un PDF con los valores del object. Pensa en generalizarlo y saber que campos vamos a pintar y que campos no
    }
    
    /**
     * Change status object
     * @param workflowContext
     * @param estadoObjeto
     * @return ObjectEntry
     */
    public void setObjectStatus(Map<String, Serializable> workflowContext, String estadoObjeto) throws PortalException {
    	
    	LoggerUtil.debug(LOG,"Cambiando el estado del objecto: " + estadoObjeto);
		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
		setObjectStatus(object, estadoObjeto);
    }
    
    
    /**
     * Change status object
     * @param object
     * @param estadoObjeto
     * @return ObjectEntry
     */
    public ObjectEntry setObjectStatus(long objectEntryId, String estadoObjeto) {
    	
    	ObjectEntry object = null;
    	try {
    		LoggerUtil.debug(LOG,"Cambiando el estado del objecto: " + estadoObjeto);
    		object = _objectEntryLocalService.getObjectEntry(objectEntryId);
    		setObjectStatus(object, estadoObjeto);
		} catch (PortalException e) {
			LOG.error("Error el actualziar el estado del objecto" + e);
		}
    	return object;
    }
    
    /**
     * Change status object
     * @param object
     * @param estadoObjeto
     * @return ObjectEntry
     */
    public ObjectEntry setObjectStatus(ObjectEntry object, String estadoObjeto) {
    	
    	try {
    		LoggerUtil.debug(LOG,"Cambiando el estado del objecto: " + estadoObjeto);
    		
	    	Map <String,Serializable> map = object.getValues();
	    	map.put("estadoObjeto", estadoObjeto);
	    	
	    	ServiceContext serviceContext = new ServiceContext();
	    	 _objectEntryLocalService.updateObjectEntry(object.getUserId(), object.getObjectEntryId(), map, serviceContext);
	    	
		} catch (PortalException e) {
			LOG.error("Error el actualziar el estado del objecto" + e);
		}
    	return object;
    }
    
    /**
     * Update History
     * @param objectEntryId
     * @param estadoObjeto
     * @param userId
     * @param rolName
     * 
     */
    public ObjectEntry setObjectHistory(long objectEntryId, String estadoObjeto, long userId, String rolName) {
    	
    	ObjectEntry object = null;
		try {
			LoggerUtil.debug(LOG,"Cambiando el histórico del objecto: " + "estado: " + estadoObjeto + " rolName: " + rolName + "userId: " + userId);
			
			object = _objectEntryLocalService.getObjectEntry(objectEntryId);
			setObjectHistory(object, estadoObjeto, userId, rolName);
			
		} catch (PortalException e) {
			e.printStackTrace();
		}
    
    	return  object;
    }
    
    /**
     * Update History
     * @param object
     * @param estadoObjeto
     * @param userId
     * @param rolName
     * 
     */
    public ObjectEntry setObjectHistory(ObjectEntry object, String estadoObjeto, long userId, String rolName) {

    	try {
			Map<String,Serializable> map = object.getValues();
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray((String) object.getValues().get("historicoEstado"));
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
			object.setValues(map);
		} catch (PortalException e) {
			LOG.error("Error el actualziar el hit�rico del objecto" + e);
		}
    	return  object;
    }
    
    /**
     * Update Object
     * @param object
     * 
     */
    public void updateObject(ObjectEntry object) {
    	
    	LoggerUtil.debug(LOG,"Actualizando el objeto.");
    	_objectEntryLocalService.updateObjectEntry(object);
    	LoggerUtil.debug(LOG, "Objeto actualizado");
    	
    }
    
    
    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;
    
    @Reference
    private RoleLocalService _roleLocalService;

    private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);
}
