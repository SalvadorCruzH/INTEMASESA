package es.emasesa.intranet.porta.firmas.service.util;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.porta.firmas.service.model.PFirmasModifyServices;
import juntadeandalucia.cice.pfirma.modify.v2.PfirmaException;

@Component(immediate = true, property = {}, service = PortalFirmasUtil.class)
public class PortalFirmasUtil {


    public void enviarPortalFirmas(Map<String, Serializable> workflowContext,  String documentSIGDID){
    	//TODO  Falta obtener: nifs, remitterNIF y workflowTaskId
    	try {
	        String subject; // NOMBRE OBJETO + NOMBRE USUARIO
	        String reference; // NOMBRE OBJETO
	        String documentName; // NOMBRE DOCUMENTO SIGD
	        List<String> nifs = null; // NIFs de los firmantes - Obtener quien es el consejero delegado con el servicio SAP de estructura recoger positionId, y obtener el dni de Liferay o del servicio de datos
	        String remitterNIF = StringPool.BLANK; // NIF del remitente
	        String workflowTaskId = StringPool.BLANK;
	        LoggerUtil.debug(LOG, "Se procede a enviar el documento al portal firmas." );
	        
	        if(workflowContext.size()>0){
	            ObjectEntry objectEntry = _objectEntryLocalService.fetchObjectEntry((long) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	            Map<String,Serializable> values = objectEntry.getValues();
	            subject = workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE)+ StringPool.BLANK+ values.get(EmasesaConstants.NUMERO_MATRICULA);
	            reference = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE));
	            documentName = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE))+
	                    GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
	            
	            LoggerUtil.debug(LOG, "subject: " + subject);
	            LoggerUtil.debug(LOG, "reference: " + reference);
	            LoggerUtil.debug(LOG, "documentName: " + documentName);
	            LoggerUtil.debug(LOG, "documentSIGDID: " + documentSIGDID);
	            LoggerUtil.debug(LOG, "nifs: " + nifs.toString());
	            LoggerUtil.debug(LOG, "remitterNIF: " + remitterNIF);
	            LoggerUtil.debug(LOG, "workflowTaskId: " + workflowTaskId);
	            
				
	            _pFirmasModifyServices.sendSign(subject, reference, 
							documentName, documentSIGDID, nifs, remitterNIF, workflowTaskId);
	            
	            LoggerUtil.debug(LOG, "Docuemnto enviado a portal firmas." );
	        }
    	} catch (JSONException | PfirmaException e) {
    		LoggerUtil.error(LOG, "Error al enviar a portal firmas: " + e.toString());
		}
    }

    @Reference
    PFirmasModifyServices _pFirmasModifyServices;
    @Reference
    ObjectEntryLocalService _objectEntryLocalService;
    
	private static final Log LOG = LogFactoryUtil.getLog(PortalFirmasUtil.class);

}
