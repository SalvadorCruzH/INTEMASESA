package es.emasesa.intranet.sap.historForm.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistform;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTFORM;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTFORM_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.historForm.exception.HistorFormException;
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

@org.springframework.stereotype.Component("historFormService")
public class HistorFormService {


    public JSONObject obtenerHistorialFormacion(String pernr) throws HistorFormException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerHistorialFormacion");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTFORM.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            Holder<TableOfZpeStEmpleadoHistform> tExterna = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistform> tImpartida = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistform> tInterna = new Holder<>();

            port.zPeEmpleadoHistform(pernr, tExterna, tImpartida, tInterna);
            LoggerUtil.debug(LOG, "Trae datos del WS correctamente: " +tExterna.value + " - " + tImpartida.value + " - " + tInterna.value);
            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tExterna.value != null){
                jsonReturn.put("externa", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tExterna.value.getItem())));
            }
            if (tImpartida.value != null){
                jsonReturn.put("impartida", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tImpartida.value.getItem())));
            }
            if (tInterna.value != null){
                jsonReturn.put("interna", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tInterna.value.getItem())));
            }
            LoggerUtil.debug(LOG, "devuelve: " +jsonReturn.toString());
            return jsonReturn;

        } catch (JSONException | ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new HistorFormException("Error con el WS:" + e.getMessage(), e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] obtenerHistorialFormacion");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando HistorialFormacion");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTFORM.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.historFormEndpoint());
            ZWSPEEMPLEADOHISTFORM_Service service = new ZWSPEEMPLEADOHISTFORM_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADOHISTFORM.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de HistorFormService", e);
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADOHISTFORM_Service --> " + configuration.historFormEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] HistorFormService");
        }

    }

    private ZWSPEEMPLEADOHISTFORM port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(HistorFormService.class);

}
