package es.emasesa.intranet.sap.centros.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.*;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.centros.exception.DistanciaCentrosException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.xml.ws.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;

@org.springframework.stereotype.Component("distanciaCentrosService")
public class DistanciaCentrosService {

    public JSONArray getDistanciaEntreCentros(String origen, String destino) throws DistanciaCentrosException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPECENTROSDISTANCIAS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            TableOfZpeStCentrosDistancias serviceResult = port.zPeCentrosDistancias(destino,origen);
            return JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(serviceResult.getItem()));
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new DistanciaCentrosException("Error llamando al WS para el origen "+ origen, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] getCertificadoRetenciones");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando distanciaCentrosService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPECENTROSDISTANCIAS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.distanciaCentrosEndpoint());
            ZWSPECENTROSDISTANCIAS_Service service = new ZWSPECENTROSDISTANCIAS_Service(urlEndpoint);
            port = service.getPort(ZWSPECENTROSDISTANCIAS.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            List<Handler> handlerChain =  bp.getBinding().getHandlerChain();
            handlerChain.add(new LogInterceptor());
            bp.getBinding().setHandlerChain(handlerChain);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/
            LOG.info(this.getClass().getName() +" cargado correctamente");
        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de DistanciaCentrosService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPECENTROSDISTANCIAS --> " + configuration.certificadoRetencionesEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] distanciaCentrosService");
        }
    }

    protected ZWSPECENTROSDISTANCIAS port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(DistanciaCentrosService.class);
}
