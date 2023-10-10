package es.emasesa.intranet.scheduler.user;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component (
        immediate = true,
        property = "destination.name=emasesa/UpdateUser",
        service = MessageListener.class
)
public class UpdateUserScheduler implements MessageListener  {

    @Override
    public void receive(Message message)  {

        JSONObject jsonObject = (JSONObject) message.getPayload();
        String pernr = jsonObject.getString("pernr");
        if(Validator.isNumber(pernr)) {
            LoggerUtil.debug(LOG, "Se procede a actualizar el usuario con user id " + pernr);
            JSONObject employerData = _sapServices.getDatosEmpleado(pernr);
            JSONObject addressData = _sapServices.getDatosEmpleadoDomicilio(pernr);
            long companyId = jsonObject.getLong("companyId");
            LoggerUtil.debug(LOG, "Datos de empleado :" + employerData);
            LoggerUtil.debug(LOG, "Datos de domicilio :" + addressData);
            User user = _userLocalService.fetchUserByScreenName(companyId, pernr);
            user.getExpandoBridge().setAttribute(EmasesaConstants.EMASESA_EXPANDO_NIF, employerData.getString("nifE"));
            //user.getExpandoBridge().setAttribute(EmasesaConstants.EMASESA_EXPANDO_DOMICILIO,);
            //user.getExpandoBridge().setAttribute(EmasesaConstants.EMASESA_EXPANDO_DOMICILIO, employerData.getString("domicilio"));
            //user.getExpandoBridge().setAttribute(EmasesaConstants.EMASESA_EXPANDO_LOCALIDAD, employerData.getString("localidad"));
            //user.getExpandoBridge().setAttribute(EmasesaConstants.EMASESA_EXPANDO_CP, employerData.getString("codigoPostal"));
            //user.getExpandoBridge().setAttribute(EmasesaConstants.EMASESA_EXPANDO_PROVINCIA, employerData.getString("provincia"));
            //user.getExpandoBridge().setAttribute(EmasesaConstants.EMASESA_EXPANDO_TELEFONO, employerData.getString("telefono"));
            LOG.info("Usuario user id "+jsonObject.get("pernr") +" actualizado");
        }else{
            LoggerUtil.debug(LOG, "El usuario no es numérico "+ pernr);
        }

    }

    @Reference
    private UserLocalService _userLocalService;

    @Reference
    private SapServicesUtil _sapServices;

    private static final Log LOG = LogFactoryUtil.getLog(UpdateUserScheduler.class);
}
