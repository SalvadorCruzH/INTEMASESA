package es.emasesa.intranet.sap.historialTitulacion.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistTitulNiv;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistTitulTit;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistTitulacio;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTTITULACI;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTTITULACI_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.historialTitulacion.exception.HistorialTitulacionException;
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

@org.springframework.stereotype.Component("historTituService")
public class HistorialTitulacionService {


    public JSONObject obtenerHistorialTitulacion(String pernr) throws HistorialTitulacionException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerHistorialTitulacion");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTTITULACI.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            Holder<TableOfZpeStEmpleadoHistTitulacio> tHistTitulacion = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistTitulNiv> tNiveles = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistTitulTit> tTitulos = new Holder<>();

            port.zPeEmpleadoHistTitulacion(pernr, tHistTitulacion, tNiveles, tTitulos);

            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tHistTitulacion.value != null){
                jsonReturn.put("titulacion", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tHistTitulacion.value.getItem())));
            }
            if (tNiveles.value != null){
                jsonReturn.put("niveles", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tNiveles.value.getItem())));
            }
            if (tTitulos.value != null){
                jsonReturn.put("titulos", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tTitulos.value.getItem())));
            }

            LoggerUtil.debug(LOG, "devuelve: " +jsonReturn.toString());
            return jsonReturn;

        } catch (JSONException | ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new HistorialTitulacionException("Error con el WS:" + e.getMessage(), e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] obtenerHistorialTitulacion");
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
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTTITULACI.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.historialTitulacionEndpoint());
            ZWSPEEMPLEADOHISTTITULACI_Service service = new ZWSPEEMPLEADOHISTTITULACI_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADOHISTTITULACI.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de HistorTituService", e);
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADOHISTTITULACI_Service --> " + configuration.historialTitulacionEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] HistorTituService");
        }

    }

    private ZWSPEEMPLEADOHISTTITULACI port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(HistorialTitulacionService.class);

}
