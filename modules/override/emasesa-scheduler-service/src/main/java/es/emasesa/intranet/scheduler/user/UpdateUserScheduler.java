package es.emasesa.intranet.scheduler.user;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;
import java.util.Map;


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
        if(!Validator.isNumber(pernr)) {
            LoggerUtil.debug(LOG, "Se procede a actualizar el usuario con user id " + pernr);
            JSONObject employerData = _sapServices.getDatosEmpleadoAndDomicilio(pernr);
            long companyId = jsonObject.getLong("companyId");
            LoggerUtil.debug(LOG, "Datos de empleado :" + employerData);
            User user = _userLocalService.fetchUserByScreenName(companyId, pernr);
            Map<String, Serializable> expandoAttributes = user.getExpandoBridge().getAttributes();

            JSONObject addressData = employerData.getJSONObject("datosDomicilio");
            String domicilio = addressData.getString("claseDesc", StringConstants.EMPTY) + " " +
                    addressData.getString("calle", StringConstants.EMPTY) + " " +
                    addressData.getString("numero", StringConstants.EMPTY) + " " +
                    addressData.getString("pisoLetra", StringConstants.EMPTY);

            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_NIF, employerData.getString("nifE", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_DOMICILIO, domicilio);
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_LOCALIDAD, addressData.getString("poblacion", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_PROVINCIA, addressData.getString("provinciaDesc", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_TELEFONO, addressData.getString("telefono", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_APELLIDO1, employerData.getString("apellido1", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_APELLIDO2, employerData.getString("apellido2", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_USUARIO, employerData.getString("usuario", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_NOMBRE, employerData.getString("nombre", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_CP, addressData.getString("codigoPostal", StringConstants.EMPTY));
            expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_MATRICULA, employerData.getString("perser", StringConstants.EMPTY));
            
            user.getExpandoBridge().setAttributes(expandoAttributes, false);
            LOG.debug("Usuario user id "+jsonObject.get("pernr") +" actualizado");
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
