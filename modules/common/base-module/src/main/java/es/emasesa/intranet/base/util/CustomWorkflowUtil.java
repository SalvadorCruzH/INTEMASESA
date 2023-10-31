package es.emasesa.intranet.base.util;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
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

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import es.emasesa.intranet.base.constant.EmasesaConstants;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomWorkflowUtil.class
)
public class CustomWorkflowUtil {
	
	  /**
     * Retrive roles and status object and update object
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
    public void createPDF(Map<String, Serializable> workflowContext) throws IOException, PortalException {

		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		long userId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long groupId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));
		String entryType = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE));
		String fileName = entryType + "_" + classPK;
		String sourceFileName = fileName + ".pdf";
		Map<String, Serializable> objectValues = _objectEntryLocalService.getObjectEntry(classPK).getValues();
		DLFolder folderPDF = dlFolderLocalService.getFolder(groupId, 0, EmasesaConstants.EMASESA_FOLDER_COMPATIBILITY);

		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.setLeading(15f);
		contentStream.newLineAtOffset(25, 750);

		objectValues.forEach((clave, valor) ->{
			try {
				contentStream.showText(clave + ": " + valor.toString());
				contentStream.newLine();
			} catch (IOException e) {
				LOG.error("Se ha producido un error al añadir texto al PDF", e);
			}
		});

		contentStream.endText();
		contentStream.close();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		document.save(byteArrayOutputStream);
		byte[] pdfByte = byteArrayOutputStream.toByteArray();
		InputStream is = new ByteArrayInputStream(pdfByte);
		document.close();

		ServiceContext serviceContext = new ServiceContext();
		dlAppLocalService.addFileEntry(fileName, userId, folderPDF.getRepositoryId(), folderPDF.getFolderId(), sourceFileName, EmasesaConstants.EMASESA_MIMETYPE,
				fileName, sourceFileName, "", "", is, pdfByte.length, null, null, serviceContext);
	}
    
    /**
     * Transform PDF Base64
     * @param pdfDocument
     * 
     * @return String pdfBase64
     * 
     */
    public String pdfBase64(PDDocument pdfDocument) {
   	 String base64PDF = StringPool.BLANK;
   	try {
   		   LoggerUtil.debug(LOG, "Se inicia el proceso para convertir un PDF a base64.");
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           pdfDocument.save(byteArrayOutputStream);
           pdfDocument.close();
           base64PDF = Base64.encodeBase64String(byteArrayOutputStream.toByteArray());

           LoggerUtil.debug(LOG, "Convertido PDF a base64: " + base64PDF);
       } catch (IOException e) {
    	   LoggerUtil.error(LOG, "Error al intenar convertir PDF a base64: " + e.toString());
       }
   	return base64PDF;
   }
    
    /**
     * Change status object
     * @param workflowContext
     * @param estadoObjeto
     * 
     */
    public void setObjectStatusAndUpdate(Map<String, Serializable> workflowContext, String estadoObjeto) throws PortalException {
    	
    	LoggerUtil.debug(LOG,"Cambiando el estado del objecto: " + estadoObjeto);
		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		
		ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
		Map <String,Serializable> map = object.getValues();
    	map.put("estadoObjeto", estadoObjeto);
    	object.setValues(map);
    	
    	updateObject(object);
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
    	
    	LoggerUtil.debug(LOG,"Cambiando el estado del objecto: " + estadoObjeto);
		
		Map <String,Serializable> map = object.getValues();
		map.put("estadoObjeto", estadoObjeto);
		object.setValues(map);
		
    	return object;
    }
    
    
    /**
     * Update History and then update object
     * @param workflowContext
     * @param estadoObjeto
     * @param userId
     * @param rolName
     * 
     */
    public void setObjectHistoryAndUpdate(Map<String, Serializable> workflowContext, String estadoObjeto, long userId, String rolName) {

    	try {
    		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
    		ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
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
			
			updateObject(object);
			
		} catch (PortalException e) {
			LOG.error("Error el actualziar el hit�rico del objecto" + e);
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
			LOG.error("Error el actualziar el hitórico del objecto" + e);
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

	@Reference
	private DLFolderLocalService dlFolderLocalService;

	@Reference
	private DLAppLocalService dlAppLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);
}
