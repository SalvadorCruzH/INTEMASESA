package es.emasesa.intranet.sap.procesoseleccioninscripcion.service;

import com.liferay.portal.kernel.json.JSONArray;
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
import es.emasesa.intranet.sap.procesoseleccioninscripcion.exception.ProcesoSeleccionInscripcionException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;

@org.springframework.stereotype.Component("procesoSeleccionInscripcionService")
public class ProcesoSeleccionInscripcionService {

    public void setProcesoSeleccionInscripcion( String imCupoDisc, String imEsEmpleado, String imNumCand, String imProcId, String imVacId) throws ProcesoSeleccionInscripcionException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEPROCESOSSELECCIONINS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            Holder<String> exNumCard = new Holder<>();
            Holder<Bapireturn1> exReturn = new Holder<>();
            port.zPeProcesosSeleccionInscri(imCupoDisc,imEsEmpleado,imNumCand,imProcId, imVacId, exNumCard, exReturn);
        } catch (ServerSOAPFaultException e) {
            throw new ProcesoSeleccionInscripcionException("Error llamando al WS para el origen ", e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] setProcesoSeleccionInscripcion");
        }
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando procesoSeleccionInscripcionService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEPROCESOSSELECCIONINS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.procesoSeleccionInscripcionEndpoint());
            ZWSPEPROCESOSSELECCIONINS_Service service = new ZWSPEPROCESOSSELECCIONINS_Service(urlEndpoint);
            port = service.getPort(ZWSPEPROCESOSSELECCIONINS.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de procesoSeleccionInscripcionService");
            }
        }  catch (MalformedURLException e){
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEPROCESOSSELECCIONINS --> " + configuration.procesoSeleccionInscripcionEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] procesoSeleccionInscripcionService");
        }
    }

    protected ZWSPEPROCESOSSELECCIONINS port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(ProcesoSeleccionInscripcionService.class);
}
