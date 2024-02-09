package es.emasesa.intranet.sap.histoPerCondu.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistPercond;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistPercondL;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTPERCONDU;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTPERCONDU_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.histoPerCondu.exception.HistPerConduException;
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

@org.springframework.stereotype.Component("histPerConduService")
public class HistPerConduService {


    public JSONObject obtenerHistPerCondu(String pernr) throws HistPerConduException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerHistPerCondu");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTPERCONDU.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            Holder<TableOfZpeStEmpleadoHistPercond> tHistPermisosConducir = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistPercondL> tPermisosConducir = new Holder<>();

            port.zPeEmpleadoHistPercondu(pernr, tHistPermisosConducir, tPermisosConducir);

            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tHistPermisosConducir.value != null){
                jsonReturn.put("historial", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tHistPermisosConducir.value.getItem())));
            }
            if (tPermisosConducir.value != null){
                jsonReturn.put("permisos", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tPermisosConducir.value.getItem())));
            }

            LoggerUtil.debug(LOG, "devuelve: " +jsonReturn.toString());
            return jsonReturn;

        } catch (JSONException | ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new HistPerConduException("Error con el WS:" + e.getMessage(), e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] obtenerHistPerCondu");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando HistPerCondu");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTPERCONDU.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.histPerConduEndpoint());
            ZWSPEEMPLEADOHISTPERCONDU_Service service = new ZWSPEEMPLEADOHISTPERCONDU_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADOHISTPERCONDU.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de histPerConduService", e);
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADOHISTFORM_Service --> " + configuration.histPerConduEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] histPerConduService");
        }

    }

    private ZWSPEEMPLEADOHISTPERCONDU port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(HistPerConduService.class);

}
