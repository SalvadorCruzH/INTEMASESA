package es.emasesa.intranet.sap.subordinados.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.*;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.subordinados.exception.CiertosDatosEstructuraException;
import es.emasesa.intranet.sap.subordinados.exception.SubordinadosException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.jws.WebParam;
import jakarta.xml.ws.Holder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;

@org.springframework.stereotype.Component("ciertosDatosEstructuraService")
public class CiertosDatosEstructuraService {

    public JSONObject getCiertosDatosEstructura() throws CiertosDatosEstructuraException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] getCiertosDatosEstructura");
        try {
            Holder<String> consejeroId = new Holder<>();
            Holder<String> direccionRrhhRespId = new Holder<>();
            Holder<String> divisionRrhhRespId = new Holder<>();
            Holder<String> subdireccionRrhhRespId = new Holder<>();

            port.zPeCiertosDatosEstructura(consejeroId, direccionRrhhRespId, divisionRrhhRespId, subdireccionRrhhRespId);

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("consejeroId", consejeroId.value);
            jsonObject.put("direccionRrhhRespId", direccionRrhhRespId.value);
            jsonObject.put("divisionRrhhRespId", divisionRrhhRespId.value);
            jsonObject.put("subdireccionRrhhRespId", subdireccionRrhhRespId.value);

            return jsonObject;
        } catch (ServerSOAPFaultException e) {
            throw new CiertosDatosEstructuraException("Error llamando al WS para el getCiertosDatosEstructura " , e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] getCiertosDatosEstructura");
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando ciertosDatosEstructuraService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPECIERTOSDATOSESTRUCTU.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.ciertosDatosEstructuraEndpoint());
            ZWSPECIERTOSDATOSESTRUCTU_Service service = new ZWSPECIERTOSDATOSESTRUCTU_Service(urlEndpoint);
            port = service.getPort(ZWSPECIERTOSDATOSESTRUCTU.class);

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
                LOG.info("Error en el WSDL de ZWSPECIERTOSDATOSESTRUCTU_Service --> " + configuration.ciertosDatosEstructuraEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] ciertosDatosEstructuraService");
        }
    }

    protected ZWSPECIERTOSDATOSESTRUCTU port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(CiertosDatosEstructuraService.class);

}
