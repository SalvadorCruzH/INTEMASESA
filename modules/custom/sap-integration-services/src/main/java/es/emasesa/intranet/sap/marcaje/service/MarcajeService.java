package es.emasesa.intranet.sap.marcaje.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStMarcajesHistoricoActu;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.jornadadiaria.exception.JornadaDiariaException;
import es.emasesa.intranet.sap.marcaje.exception.MarcajeException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.soap.SoapFaultException;

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
        try {
            SapServicesConfiguration configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEMARCAJESHISTORICOACT.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            ZWSPEMARCAJESHISTORICOACT_Service service = new ZWSPEMARCAJESHISTORICOACT_Service();
            port = service.getPort(ZWSPEMARCAJESHISTORICOACT.class);

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });

            /*******************UserName & Password ******************************/
            Map<String, Object> requestContext = ((WSBindingProvider) port).getRequestContext();
            WSBindingProvider bp = ((WSBindingProvider) port);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuration.marcajeEndpoint());
            Map<String, List<String>> headers = new HashMap<>();
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            /**********************************************************************/

        } catch (Exception e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de MarcajeService");
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
