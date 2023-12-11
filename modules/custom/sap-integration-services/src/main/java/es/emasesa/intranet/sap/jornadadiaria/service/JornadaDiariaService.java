package es.emasesa.intranet.sap.jornadadiaria.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.rfc.functions.TABLEOFZPESTEMPLEADOJORNADADIARIA;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOJORNADADIARI;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOJORNADADIARI_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.jornadadiaria.exception.JornadaDiariaException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("jornadaDiariaService")
public class JornadaDiariaService {


    public JSONArray obtenerJornadaDiaria(String pernr, String fechaInicio, String fechaFin) throws JornadaDiariaException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerJornadaDiaria");
        JSONArray data = JSONFactoryUtil.createJSONArray();
        try {
            TABLEOFZPESTEMPLEADOJORNADADIARIA response = port.zPeEmpleadoJornadaDiaria(fechaFin, fechaInicio, pernr);
            if (!response.getItem().isEmpty()) {
                data = JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(response.getItem()));
            }
        } catch (JSONException | ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new JornadaDiariaException("Error con el WS:" + e.getMessage(), e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] obtenerJornadaDiaria");
        }
        return data;
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando JornadaDiariaService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOJORNADADIARI.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.jornadaDiariaEndpoint());
            ZWSPEEMPLEADOJORNADADIARI_Service service = new ZWSPEEMPLEADOJORNADADIARI_Service();
            port = service.getPort(ZWSPEEMPLEADOJORNADADIARI.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/


        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de JornadaDiariaService", e);
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADOJornadaDiari_Service --> " + configuration.jornadaDiariaEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] JornadaDiariaService");
        }

    }

    private ZWSPEEMPLEADOJORNADADIARI port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(JornadaDiariaService.class);

}
