package es.emasesa.intranet.sap.historForm.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistFormPais;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistFormacion;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTFORMACIO;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTFORMACIO_Service;
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
import org.apache.commons.codec.binary.Base64;
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


    public JSONObject obtenerHistorialFormacion(String pernr, String eventoId) throws HistorFormException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerHistorialFormacion");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTFORMACIO.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            Holder<TableOfZpeStEmpleadoHistFormacion> tExterna = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistFormacion> tImpartida = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistFormacion> tInterna = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistFormPais> tPais = new Holder<>();
            Holder<byte[]> exData = new Holder<>();
            Holder<Integer> exReturn = new Holder<>();

            port.zPeEmpleadoHistFormacion(eventoId, pernr, exData, exReturn, tExterna, tImpartida, tInterna, tPais);

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
            if (tPais.value != null){
                jsonReturn.put("pais", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tPais.value.getItem())));
            }
            if (exData.value != null){
                String decoded = Base64.encodeBase64String(exData.value);
                jsonReturn.put("data", decoded);
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
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTFORMACIO.class.getClassLoader();
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
            ZWSPEEMPLEADOHISTFORMACIO_Service service = new ZWSPEEMPLEADOHISTFORMACIO_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADOHISTFORMACIO.class);

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

    private ZWSPEEMPLEADOHISTFORMACIO port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(HistorFormService.class);

}
