package es.emasesa.intranet.porta.firmas.service.util;


import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.porta.firmas.service.model.PFirmasModifyServices;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {}, service = PortalFirmasUtil.class)
public class PortalFirmasUtil {


    public void enviarPortalFirmas(Map<String, Serializable> workflowContext){

        String subject; // NOMBRE OBJETO + NOMBRE USUARIO
        String reference; // NOMBRE OBJETO
        String documentName; // NOMBRE DOCUMENTO SIGD
        String documentSIGDID; // ID DOCUMENTO SIGD
        List<String> nifs; // NIFs de los firmantes - Obtener quien es el consejero delegado con el servicio SAP de estructura recoger positionId, y obtener el dni de Liferay o del servicio de datos
        String remitterNIF; // NIF del remitente
        String workflowTaskId;

        if(workflowContext.size()>0){
            ObjectEntry objectEntry = _objectEntryLocalService.fetchObjectEntry((long) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            Map<String,Serializable> values = objectEntry.getValues();

            subject = workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE)+ StringPool.BLANK+ values.get(EmasesaConstants.NUMERO_MATRICULA);
            reference = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE));
            documentName = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE))+
                    GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            documentSIGDID="";//GET FROM OBJECT


            //_pFirmasModifyServices.sendSign();
        }
    }

    @Reference
    PFirmasModifyServices _pFirmasModifyServices;
    @Reference
    ObjectEntryLocalService _objectEntryLocalService;

}
