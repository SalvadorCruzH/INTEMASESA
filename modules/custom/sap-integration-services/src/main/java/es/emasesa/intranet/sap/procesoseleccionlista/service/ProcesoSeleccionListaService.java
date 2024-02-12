package es.emasesa.intranet.sap.procesoseleccionlista.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import es.emasesa.intranet.sap.procesoseleccionlista.exception.ProcesoSeleccionListaException;
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

@org.springframework.stereotype.Component("procesoSeleccionListaService")
public class ProcesoSeleccionListaService {

    public JSONArray getListadoSolicitudes(String tipo) throws ProcesoSeleccionListaException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEPROCESOSSELECCIONLIS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            TableOfZpeStProcesoSeleccionLista tProcesoSeleccionLista = port.zPeProcesosSeleccionLista(tipo);
            return JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tProcesoSeleccionLista.getItem()));
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new ProcesoSeleccionListaException("Error llamando al WS para el origen "+ tipo, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] getListadoSolicitudes");
        }
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando procesoSeleccionListaService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEPROCESOSSELECCIONLIS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.procesoSeleccionListaEndpoint());
            ZWSPEPROCESOSSELECCIONLIS_Service service = new ZWSPEPROCESOSSELECCIONLIS_Service(urlEndpoint);
            port = service.getPort(ZWSPEPROCESOSSELECCIONLIS.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de procesoSeleccionListaService");
            }
        }  catch (MalformedURLException e){
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEPROCESOSSELECCIONLIS --> " + configuration.procesoSeleccionListaEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] procesoSeleccionListaService");
        }
    }

    protected ZWSPEPROCESOSSELECCIONLIS port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(ProcesoSeleccionListaService.class);
}
