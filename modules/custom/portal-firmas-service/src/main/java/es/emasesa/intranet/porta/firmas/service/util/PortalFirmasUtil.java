package es.emasesa.intranet.porta.firmas.service.util;


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
        List<String> nifs; // NIFs de los firmantes
        String remitterNIF; // NIF del remitente
        String workflowTaskId;

        //_pFirmasModifyServices.sendSign();

    }

    @Reference
    PFirmasModifyServices _pFirmasModifyServices;
}
