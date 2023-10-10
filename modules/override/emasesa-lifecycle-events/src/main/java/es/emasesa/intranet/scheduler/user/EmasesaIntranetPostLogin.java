package es.emasesa.intranet.scheduler.user;

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
import com.liferay.portal.kernel.util.Portal;
import es.emasesa.intranet.base.util.LoggerUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

            Message message = new Message();
            message.setPayload(jsonObject);
            message.setDestinationName("emasesa/UpdateUser");
            _messageBus.sendMessage("emasesa/UpdateUser", message);
            LoggerUtil.debug(LOG, "Enviado mensaje para actualización para: " + user.getScreenName());
        } catch (PortalException e) {
            LoggerUtil.error(LOG, "Se ha producido un error recuperando el usuario en el login", e);
        }
    }

    @Reference
    protected Portal _portal;
    @Reference
    private MessageBus _messageBus;
    private static final Log LOG = LogFactoryUtil.getLog(EmasesaIntranetPostLogin.class);
}
