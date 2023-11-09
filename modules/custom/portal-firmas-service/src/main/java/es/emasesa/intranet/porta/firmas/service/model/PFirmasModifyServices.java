package es.emasesa.intranet.porta.firmas.service.model;

import com.liferay.portal.kernel.json.JSONException;
import java.util.List;
import juntadeandalucia.cice.pfirma.modify.v2.PfirmaException;

public interface PFirmasModifyServices {

    public void sendSign(String subject, String reference, String documentName, String documentSIGDID, List<String> nifs,
                         String remitterNIF,String workflowTaskId) throws JSONException, PfirmaException;


}
