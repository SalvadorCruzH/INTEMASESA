package es.emasesa.intranet.sap.subordinados.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStSubordinados;
import com.sap.document.sap.soap.functions.mc_style.ZWSPESUBORDINADOS;
import com.sap.document.sap.soap.functions.mc_style.ZWSPESUBORDINADOS_Service;
import com.sap.document.sap.soap.functions.mc_style.ZpeStSubordinados;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.subordinados.exception.SubordinadosException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("subordinadosService")
public class SubordinadosService {

    public JSONArray getSubordinados(String directorioTodos, String pernr) throws SubordinadosException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] getSubordinados");
        try {
            TableOfZpeStSubordinados serviceResult = port.zPeSubordinados(directorioTodos,pernr);

            List<ZpeStSubordinados> subordinados = serviceResult.getItem();

            return JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(subordinados));
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new SubordinadosException("Error llamando al WS para el pernr "+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] getSubordinados");
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando SubordinadosService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPESUBORDINADOS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.empleadoDatosPersonalesEndpoint());
            ZWSPESUBORDINADOS_Service service = new ZWSPESUBORDINADOS_Service();
            port = service.getPort(ZWSPESUBORDINADOS.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/

        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de SubordinadosService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPESUBORDINADOS_Service --> " + configuration.subordinadosEndpoint());
            }
        }finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] SubordinadosService");
        }
    }

    protected ZWSPESUBORDINADOS port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(SubordinadosService.class);

}
