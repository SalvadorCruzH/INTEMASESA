package es.emasesa.intranet.base.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.constant.EmasesaConstants;

@Component(
        immediate = true,
        service = CustomWorkflowBaseUtil.class
)
public class CustomWorkflowBaseUtil {
	    
    /**
     * Retrive roles and status object and update object
     * @param workflowContext
     * @param rolName
     * @param estadoObjeto
     * @param userId
     * 
     */
    public void updateObjectHistoryAndStatus(Map<String, Serializable> workflowContext, String estadoObjeto, long userId, String rolName) {
		if (ServiceContextThreadLocal.getServiceContext() != null) {
			userId = ServiceContextThreadLocal.getServiceContext().getUserId();
		}
        LoggerUtil.debug(LOG,"Cambiando el status y la historico del object. Con el rol : " + rolName + " y el estado: " + estadoObjeto);
		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
      
		ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
		try {
			if(Validator.isNotNull(object)) {
				Map <String,Serializable> map = object.getValues();
				JSONArray jsonArray = JSONFactoryUtil.createJSONArray((String) object.getValues().get(EmasesaConstants.WORKFLOW_HISTORICO));
				JSONObject datosHistorico = JSONFactoryUtil.createJSONObject();
				
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EmasesaConstants.DDMMYYYYHHmmss);
				
				if(userId != 0) {
					datosHistorico.put(EmasesaConstants.WORKFLOW_USER_ID, userId);
	    		}else {
	    			datosHistorico.put(EmasesaConstants.WORKFLOW_USER_ID, StringPool.BLANK);
	    		}
				datosHistorico.put(EmasesaConstants.EMASESA_OBJECT_STATUS, estadoObjeto);
				datosHistorico.put(EmasesaConstants.WORKFLOW_ROL, rolName);
				datosHistorico.put(EmasesaConstants.WORKFLOW_FECHA, now.format(formatter));
				jsonArray.put(datosHistorico);
				
				map.put(EmasesaConstants.EMASESA_OBJECT_HISTORY, jsonArray);
				map.put(EmasesaConstants.EMASESA_OBJECT_STATUS, estadoObjeto);
				
				ServiceContext serviceContext = new ServiceContext();
				_objectEntryLocalService.updateObjectEntry(
							object.getUserId(), object.getObjectEntryId(), map, 
							serviceContext);
				
				LoggerUtil.debug(LOG,"Rol y estado asignado");
			}
		} catch (PortalException e) {
			LOG.error("Error el actualziar el hitórico del objecto" + e);
		}
    }
    
    /**
     * Update field sIGDIds to the object
     * @param workflowContext
     * @param idSIGD
     * @param documentName
     * @param sign
     * 
     */
    public void updateIdsSIGD(Map<String, Serializable> workflowContext, String idSIGD, String documentName, Boolean sign) {
    	
    	LoggerUtil.debug(LOG,"Modificando el ID obtenido por SIGD en el campo del objecto.");
		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(classPK);
		try {
			if(Validator.isNotNull(object)) {
				Map <String,Serializable> map = object.getValues();
				JSONArray jsonArray = JSONFactoryUtil.createJSONArray((String) object.getValues().get(EmasesaConstants.EMASESA_OBJECT_SIGD_IDS));
				JSONObject sigdIDs = JSONFactoryUtil.createJSONObject();
				
				sigdIDs.put(EmasesaConstants.SIGD_DOCUMENT_NAME, documentName);
				sigdIDs.put(EmasesaConstants.SIGD_ID, idSIGD);
				sigdIDs.put(EmasesaConstants.PORTAFIRMAS_SIGN, sign);
				jsonArray.put(sigdIDs);
				
				map.put(EmasesaConstants.EMASESA_OBJECT_SIGD_IDS, jsonArray);
				
				ServiceContext serviceContext = new ServiceContext();
				_objectEntryLocalService.updateObjectEntry(
							object.getUserId(), object.getObjectEntryId(), map, 
							serviceContext);
				
				LoggerUtil.debug(LOG,"Modificado IDs del SIGD");
			}
		} catch (PortalException e) {
			LOG.error("Error el actualziar los IDs del SIGD del objecto" + e);
		}
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
    	map.put(EmasesaConstants.EMASESA_OBJECT_STATUS, estadoObjeto);
    	
    	ServiceContext serviceContext = new ServiceContext();
		_objectEntryLocalService.updateObjectEntry(
				object.getUserId(), object.getObjectEntryId(), map, 
				serviceContext);
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
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray((String) object.getValues().get(EmasesaConstants.WORKFLOW_HISTORICO));
			JSONObject datosHistorico = JSONFactoryUtil.createJSONObject();
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EmasesaConstants.DDMMYYYYHHmmss);
			
			if(userId != 0) {
				datosHistorico.put(EmasesaConstants.WORKFLOW_USER_ID, userId);
    		}else {
    			datosHistorico.put(EmasesaConstants.WORKFLOW_USER_ID, StringPool.BLANK);
    		}
			datosHistorico.put(EmasesaConstants.EMASESA_OBJECT_STATUS, estadoObjeto);
			datosHistorico.put(EmasesaConstants.WORKFLOW_ROL, rolName);
			datosHistorico.put(EmasesaConstants.WORKFLOW_FECHA, now.format(formatter));
			jsonArray.put(datosHistorico);
			
			map.put(EmasesaConstants.EMASESA_OBJECT_HISTORY, jsonArray);
			map.put(EmasesaConstants.EMASESA_OBJECT_STATUS, estadoObjeto);
			
			ServiceContext serviceContext = new ServiceContext();
			_objectEntryLocalService.updateObjectEntry(
					object.getUserId(), object.getObjectEntryId(), map, 
					serviceContext);
			
		} catch (PortalException e) {
			LOG.error("Error el actualziar el hitórico del objecto" + e);
		}
    }
    
    
    
    /**
     * Create PDF
     * @param workflowContext
     * 
     */
    public PDDocument createPDF(Map<String, Serializable> workflowContext) throws IOException, PortalException {
    	
		long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		long userId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long groupId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));
		String entryType = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE));
		String fileName = entryType + "_" + classPK;
		String sourceFileName = fileName + ".pdf";
		Map<String, Serializable> objectValues = _objectEntryLocalService.getObjectEntry(classPK).getValues();
		DLFolder folderPDF = dlFolderLocalService.getFolder(groupId, 0, EmasesaConstants.EMASESA_FOLDER_OBJECTS);

		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.setLeading(15f);
		contentStream.newLineAtOffset(25, 750);
		
		if(Validator.isNotNull(objectValues)) {
			objectValues.forEach((clave, valor) ->{
				try {
					if(Validator.isNotNull(clave) && Validator.isNotNull(valor)) {
						contentStream.showText(clave + ": " + valor.toString());
						contentStream.newLine();
					}
				} catch (IOException e) {
					LOG.error("Se ha producido un error al añadir texto al PDF", e);
				}
			});
		}

		contentStream.endText();
		contentStream.close();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		document.save(byteArrayOutputStream);
		byte[] pdfByte = byteArrayOutputStream.toByteArray();
		InputStream is = new ByteArrayInputStream(pdfByte);

		/*ServiceContext serviceContext = new ServiceContext();
		dlAppLocalService.addFileEntry(fileName, userId, folderPDF.getRepositoryId(), folderPDF.getFolderId(), sourceFileName, EmasesaConstants.EMASESA_MIMETYPE,
				fileName, sourceFileName, "", "", is, pdfByte.length, null, null, serviceContext);*/
		return document;
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
	 * Obtain document base64 by document name
	 * @param workflowContext
	 * @param documentName
	 * 
	 * @return documentBase64
	 *
	 */
	public String getDocumentToLoad(Map<String, Serializable> workflowContext, String documentName){
		long entryClassPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
		long idDocumentoToLoad = 0;
		String documentBase64 = StringPool.BLANK;

		try {
			 LoggerUtil.debug(LOG, "Obteniendo el documento con nombre: " + documentName);
			idDocumentoToLoad = (long) _objectEntryLocalService.getObjectEntry(entryClassPK).getValues().get(documentName);
			LoggerUtil.debug(LOG, "Recuperamos el ID del documento: " + idDocumentoToLoad);
			if (Validator.isNotNull(idDocumentoToLoad) && idDocumentoToLoad != 0) {
				LoggerUtil.debug(LOG, "ID obtenido, buscar el DLFile a partir del ID... " );
				DLFileEntry documentFileEntry = _dlFileEntryLocalService.getDLFileEntry(idDocumentoToLoad);
				LoggerUtil.debug(LOG, "Se recupera el DLFILE... " );
				documentBase64 = convertFileBase64(documentFileEntry);
				LoggerUtil.debug(LOG, "Convertido a Base64 " );
			}else {
				LoggerUtil.debug(LOG, "No hay PDF subido por asesoría juridica");
			}
		} catch (PortalException e) {
			LoggerUtil.error(LOG, "Error al intenar recuperar el documento: " + e.toString());
		}
		return documentBase64;
	}

	/**
	 * Download file
	 * @param fileEntry
	 *
	 */
	private void downloadFile(DLFileEntry fileEntry) throws IOException, PortalException {
		try (InputStream inputStream = fileEntry.getContentStream();
			 FileOutputStream outputStream = new FileOutputStream("C:/Dev/" + fileEntry.getFileName())) {
			byte[] buffer = new byte[4096];
			int len;

			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
		}
		if (fileEntry.getExtension().equalsIgnoreCase("PDF")){
			convertDLFileEntryToPDDocument(fileEntry);
		}else {
			convertFileBase64(fileEntry);
		}
	}

	/**
	 * Convert file to Pddocument
	 * @param fileEntry
	 *
	 */
	public void convertDLFileEntryToPDDocument(DLFileEntry fileEntry) throws PortalException, IOException {
		InputStream inputStream = fileEntry.getContentStream();
		pdfBase64(PDDocument.load(inputStream));
	}

	/**
	 * Convert file to base64
	 * @param fileEntry
	 *
	 */
	public String convertFileBase64(DLFileEntry fileEntry){
		String base64 = StringPool.BLANK;

		try (InputStream inputStream = fileEntry.getContentStream()) {
			byte[] fileContent = inputStream.readAllBytes();
			base64 = java.util.Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException | PortalException e) {
			throw new RuntimeException(e);
		}

		return base64;
	}

	public boolean isAnotherUnfinishedUserObject (long objectEntryId, long userId){
	boolean existsOther = false;
		try {
			long objectDefinitionId = ObjectEntryLocalServiceUtil.getObjectEntry(objectEntryId).getObjectDefinitionId();
			List<ObjectEntry> objects = null;
			DynamicQuery dynamicQuery = ObjectEntryLocalServiceUtil.dynamicQuery();

			Property userIdProperty = PropertyFactoryUtil.forName("userId");
			Property objectDefinitionIdProperty = PropertyFactoryUtil.forName("objectDefinitionId");

			dynamicQuery.add(userIdProperty.eq(userId));
			dynamicQuery.add(objectDefinitionIdProperty.eq(objectDefinitionId));

			objects = ObjectEntryLocalServiceUtil.dynamicQuery(dynamicQuery);

			if (!objects.isEmpty()){
				for(ObjectEntry objectEntry : objects){
					String status = objectEntry.getValues().get("estadoObjeto").toString();
					if (objectEntryId != objectEntry.getObjectEntryId() && !Objects.equals(status, "aceptada") && !Objects.equals(status, "rechazada")) {
						existsOther = true;
					}
				}
			}
		}
		catch (PortalException e) {
			LoggerUtil.error(LOG, e.getMessage());
		}

		return existsOther;
	}


	@Reference
    private ObjectEntryLocalService _objectEntryLocalService;
    
    @Reference
    private RoleLocalService _roleLocalService;

	@Reference
	private DLFolderLocalService dlFolderLocalService;

	@Reference
	private DLAppLocalService dlAppLocalService;

	@Reference
	DLFileEntryLocalService _dlFileEntryLocalService;
	
	private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowBaseUtil.class);
}
