package es.emasesa.intranet.sap.diplomaEventos.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistPercond;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistPercondL;
import com.sap.document.sap.soap.functions.mc_style.ZPeEmpleadoDiplomaEventoException;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADODIPLOMAEVENT;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADODIPLOMAEVENT_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.diplomaEventos.exception.DiplomaEventosException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.handler.Handler;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;

@org.springframework.stereotype.Component("diplomaEventosService")
public class DiplomaEventosService {


    public JSONObject obtenerDiplomaEventos(String pernr, String eventoId) throws DiplomaEventosException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerDiplomaEventos");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADODIPLOMAEVENT.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            Holder<byte[]> exData = new Holder<>();
            Holder<Integer> exResult = new Holder<>();

            port.zPeEmpleadoDiplomaEvento(eventoId, pernr, exData, exResult);

            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (exData.value != null){
                String decoded = Base64.encodeBase64String(exData.value);
                jsonReturn.put("diploma", decoded);
            }

            LoggerUtil.debug(LOG, "devuelve: " +jsonReturn.toString());
            return jsonReturn;

        } catch (ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new DiplomaEventosException("Error con el WS:" + e.getMessage(), e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } catch (ZPeEmpleadoDiplomaEventoException e) {
            throw new RuntimeException(e);
        } finally {
            LoggerUtil.debug(LOG, "[E] obtenerDiplomaEventos");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando DiplomaEventos");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADODIPLOMAEVENT.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.diplomaEventosEndpoint());
            ZWSPEEMPLEADODIPLOMAEVENT_Service service = new ZWSPEEMPLEADODIPLOMAEVENT_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADODIPLOMAEVENT.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de DiplomaEventosService", e);
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADODIPLOMAEVENT_Service --> " + configuration.diplomaEventosEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] DiplomaEventosService");
        }

    }

    private ZWSPEEMPLEADODIPLOMAEVENT port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(DiplomaEventosService.class);

}
