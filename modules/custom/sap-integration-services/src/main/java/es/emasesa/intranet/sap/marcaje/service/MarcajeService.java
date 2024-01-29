package es.emasesa.intranet.sap.marcaje.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.util.FileUtil;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStMarcajesHistoricoActu;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.marcaje.exception.MarcajeException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import jakarta.xml.ws.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("marcajeService")
public class MarcajeService {

    public JSONArray obtenerMarcajeHistoricoActual(String pernr, String fechaInicio, String fechaFin) throws MarcajeException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerMarcajeHistoricoActual");
        JSONArray data = JSONFactoryUtil.createJSONArray();
        try {
            TableOfZpeStMarcajesHistoricoActu response = port.zPeMarcajesHistoricoActual(fechaFin, fechaInicio, pernr);
            if (response.getItem().size() > 0) {
                data = JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(response.getItem()));
            }

        } catch (JSONException | ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new MarcajeException("Error con el WS:" + e.getMessage(), e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] obtenerMarcajeHistoricoActual");
        }
        return data;
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate(){

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando MarcajeService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEMARCAJESHISTORICOACT.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.marcajeEndpoint());
            ZWSPEMARCAJESHISTORICOACT_Service service = new ZWSPEMARCAJESHISTORICOACT_Service(urlEndpoint);
            port = service.getPort(ZWSPEMARCAJESHISTORICOACT.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de MarcajeService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEMARCAJESHISTORICOACT_Service --> " + configuration.marcajeEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] MarcajeServiceMarcajeService");
        }
    }

    private ZWSPEMARCAJESHISTORICOACT port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(MarcajeService.class);

}
