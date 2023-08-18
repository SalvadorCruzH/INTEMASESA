package es.emasesa.intranet.portal.firmas.service;

import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.portal.firmas.model.PFirmasModifyServices;
import javax.xml.ws.Holder;
import juntadeandalucia.cice.pfirma.modify.v2.ModifyService;
import juntadeandalucia.cice.pfirma.modify.v2.ModifyServiceService;
import juntadeandalucia.cice.pfirma.modify.v2.PfirmaException;
import juntadeandalucia.cice.pfirma.type.v2.ObjectFactory;
import juntadeandalucia.cice.pfirma.type.v2.Request;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {}, service = PFirmasModifyServices.class)
public class PFirmasModifyServicesImpl implements PFirmasModifyServices {

    @Override
    public void sendRequest(Request request) throws PfirmaException {
        ModifyService modifyService = _getService();
        Holder<String> requestHash = new Holder<>();
        requestHash.value = modifyService.createRequest(request);
        modifyService.sendRequest(requestHash);

    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }
    private ModifyService _getService(){

        if(Validator.isNull(_modifyService)){
            ModifyServiceService modifyServiceService = new ModifyServiceService();
            _modifyService = modifyServiceService.getModifyServicePort();
        }


        return _modifyService;

    }

    private ModifyService _modifyService;
}
