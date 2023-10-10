package es.emasesa.intranet.sap.datospersona.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sap.document.sap.soap.functions.mc_style.*;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosDomicilioException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Component("empleadoDatosPersonalesDomicilioService")
public class EmpleadoDatosDomicilioService {

    public JSONObject getEmpleadoDatosDomicilio(String pernr) throws EmpleadoDatosDomicilioException {

        try {
            TableOfZpeStEmpleadoDomicilio serviceResult = port.zPeEmpleadoDomicilio(pernr);
            ZpeStEmpleadoDomicilio empleadoDatosDomicilio = serviceResult.getItem().stream().findFirst().orElse(null);

            return JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerializeDeep(empleadoDatosDomicilio));
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new EmpleadoDatosDomicilioException("Error llamando al WS para el pernr "+ pernr, e);
        }
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando empleadoDatosPersonalesDomicilioService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

        try {
            SapServicesConfiguration configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADODOMICILIO.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            ZWSPEEMPLEADODOMICILIO_Service service = new ZWSPEEMPLEADODOMICILIO_Service();
            port = service.getPort(ZWSPEEMPLEADODOMICILIO.class);

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });

            /*******************UserName & Password ******************************/
            Map<String, Object> requestContext = ((WSBindingProvider) port).getRequestContext();
            WSBindingProvider bp = ((WSBindingProvider) port);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuration.empleadoDatosPersonalesEndpoint());
            Map<String, List<String>> headers = new HashMap<>();
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            /**********************************************************************/

        } catch (Exception e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de EmpleadoDatosPersonalesService");
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] EmpleadoDatosPersonalesService");
        }
    }

    protected ZWSPEEMPLEADODOMICILIO port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(EmpleadoDatosDomicilioService.class);

}
