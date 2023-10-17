package es.emasesa.intranet.sap.estructura.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoEstructura;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA_Service;
import com.sap.document.sap.soap.functions.mc_style.ZpeStEmpleadoEstructura;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.estructura.exception.EmpleadoEstructuraException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("empleadoEstructuraService")
public class EmpleadoEstructuraService {

    public JSONObject getEmpleadoEstructura(String pernr) throws EmpleadoEstructuraException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] getEmpleadoEstructura");
        try {
            TableOfZpeStEmpleadoEstructura result = port.zPeEmpleadoEstructura(pernr);
            ZpeStEmpleadoEstructura empleadoEstructura = result.getItem().stream().findFirst().orElse(null);

            return JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerializeDeep(empleadoEstructura));
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new EmpleadoEstructuraException("Error llamando al WS para el pernr "+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] getEmpleadoEstructura");
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando EmpleadoEstructuraService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

        try {
            SapServicesConfiguration configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOESTRUCTURA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            ZWSPEEMPLEADOESTRUCTURA_Service service = new ZWSPEEMPLEADOESTRUCTURA_Service();
            port = service.getPort(ZWSPEEMPLEADOESTRUCTURA.class);

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });

            /*******************UserName & Password ******************************/
            Map<String, Object> requestContext = ((WSBindingProvider) port).getRequestContext();
            WSBindingProvider bp = ((WSBindingProvider) port);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuration.empleadoEstructuraEndpoint());
            Map<String, List<String>> headers = new HashMap<>();
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            /**********************************************************************/

       } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de EmpleadoEstructuraService");
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] EmpleadoEstructuraService");
        }
    }

    protected ZWSPEEMPLEADOESTRUCTURA port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(EmpleadoEstructuraService.class);

}
