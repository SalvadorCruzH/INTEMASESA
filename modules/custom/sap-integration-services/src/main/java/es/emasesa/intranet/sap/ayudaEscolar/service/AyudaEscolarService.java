package es.emasesa.intranet.sap.ayudaEscolar.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.*;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.ayudaEscolar.exception.AyudaEscolarException;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.centros.exception.DistanciaCentrosException;
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

@org.springframework.stereotype.Component("ayudaEscolarService")
public class AyudaEscolarService {

    public Bapireturn1 getAyudaEscolar(String pernr) throws AyudaEscolarException, SapCommunicationException {

        try {
            Holder<TableOfZpeStAyudasSolicitadas> tAyudasSolicitadas = new Holder<>();
            Holder<TableOfZpeStBeneficiarios> tBeneficiarios = new Holder<>();
            Holder<TableOfZpeStEstudios> tEstudios = new Holder<>();
            Holder<TableOfZpeStInsAyudaEscolar> tInsAyudaEscolar = new Holder<>();

            Bapireturn1 serviceResult = port.zPeAyudaEscolar(pernr, tAyudasSolicitadas ,tBeneficiarios ,tEstudios, tInsAyudaEscolar);
            return serviceResult;
        } catch (ServerSOAPFaultException e) {
            throw new AyudaEscolarException("Error llamando al WS para el origen "+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] solicitarAyudaEscolar");
        }
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando ayudaEscolarService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEAYUDAESCOLAR.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            try {
                URL urlEndpoint = new URL(configuration.ayudaEscolarEndpoint());
            }catch (MalformedURLException e) {
                    if (LOG.isInfoEnabled()) {
                        LOG.info("Error en el WSDL de ZWSPEAYUDAESCOLAR --> " + configuration.ayudaEscolarEndpoint());
                    }
                }
            ZWSPEAYUDAESCOLAR_Service service = new ZWSPEAYUDAESCOLAR_Service();
            port = service.getPort(ZWSPEAYUDAESCOLAR.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/

        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de ayudaEscolarService");
            }
        }  finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] ayudaEscolarService");
        }
    }

    protected ZWSPEAYUDAESCOLAR port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(AyudaEscolarService.class);
}
