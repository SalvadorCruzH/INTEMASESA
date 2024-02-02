package es.emasesa.intranet.lifecycle.login.post;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import org.apache.logging.log4j.ThreadContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "key=login.events.post"
        },
        service = LifecycleAction.class
)
public class EmasesaIntranetPostLogin extends Action {

    @Override
    public void run(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ActionException {

        try {
            User user = _portal.getUser(httpServletRequest);
            LoggerUtil.debug(LOG, "login.event.post, userId : " + user.getScreenName());

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("pernr", user.getScreenName());
            jsonObject.put("companyId", CompanyThreadLocal.getCompanyId());

            updateUserSapIntegration(CompanyThreadLocal.getCompanyId(), user.getScreenName());

            LoggerUtil.debug(LOG, "Enviado mensaje para actualización para: " + user.getScreenName());
        } catch (PortalException e) {
            LoggerUtil.error(LOG, "Se ha producido un error recuperando el usuario en el login", e);
        }
    }

    private void updateUserSapIntegration(long companyId, String screenName) {

        if (!Validator.isNumber(screenName) && !screenName.contains("admin") && !screenName.contains("asesor") && !screenName.contains("jefe")) {

            ThreadContext.push(screenName);
            LoggerUtil.debug(LOG, "Se procede a actualizar el usuario con user id " + screenName);
            JSONObject employerData = _sapServices.getDatosEmpleadoAndDomicilio(screenName);
            JSONObject jobData = _sapServices.getEmpleadoEstructura(employerData.getString("pernr"));
            LoggerUtil.debug(LOG, "Datos de empleado :" + employerData);
            User user = _userLocalService.fetchUserByScreenName(companyId, screenName);
            PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();
            if ((permissionChecker == null) || (permissionChecker.getUserId() != user.getUserId())) {
                permissionChecker = PermissionCheckerFactoryUtil.create(user);
                PermissionThreadLocal.setPermissionChecker(permissionChecker);
            }

            if (jobData != null && employerData != null && employerData.getJSONObject("datosDomicilio") != null) {
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
                expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_MATRICULA, employerData.getString("pernr", StringConstants.EMPTY));
                expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_DEPARTAMENTO, jobData.getString("dptoDesc", StringConstants.EMPTY));
                expandoAttributes.put(EmasesaConstants.EMASESA_EXPANDO_PUESTOTRABAJO, jobData.getString("posicionDesc", StringConstants.EMPTY));

                user.getExpandoBridge().setAttributes(expandoAttributes, false);

                LOG.debug("Usuario user id " + screenName + " actualizado");
            }
        } else {
            LoggerUtil.debug(LOG, "El usuario no es numérico " + screenName);
        }
        ThreadContext.clearAll();
    }

    @Reference
    private SapServicesUtil _sapServices;
    @Reference
    private UserLocalService _userLocalService;
    @Reference
    protected Portal _portal;
    @Reference
    private MessageBus _messageBus;
    private static final Log LOG = LogFactoryUtil.getLog(EmasesaIntranetPostLogin.class);
}
