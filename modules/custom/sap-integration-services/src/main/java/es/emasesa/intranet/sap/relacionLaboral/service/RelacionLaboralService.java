package es.emasesa.intranet.sap.relacionLaboral.service;

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
import es.emasesa.intranet.sap.relacionLaboral.exception.RelacionLaboralException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.xml.ws.Holder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Base64;

@org.springframework.stereotype.Component("relacionLaboralService")
public class RelacionLaboralService {

    public JSONObject getRelacionLaboralEmpleado(String pernr) throws RelacionLaboralException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Holder<byte[]> out = new Holder<>();
            Holder<Integer> exResult = new Holder<>();
            ClassLoader objectFactoryClassLoader = ZWSPECONSULTACERTRETPDF.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            TableOfZpeStEmpleadoRelacLaboral serviceResult = port.zPeEmpleadoRelacLaboral(pernr);
            ZpeStEmpleadoRelacLaboral empleadoRelacionLaboral = serviceResult.getItem().stream().findFirst().orElse(null);
            return JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerializeDeep(empleadoRelacionLaboral));
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new RelacionLaboralException("Error llamando al WS para el pernr "+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] getCertificadoRetenciones");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando certificadoRetencionesService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADORELACLABORAL.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });

            URL urlEndpoint = new URL(configuration.relacionLaboralEndpoint());
            ZWSPEEMPLEADORELACLABORAL_Service service = new ZWSPEEMPLEADORELACLABORAL_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADORELACLABORAL.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/

        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de CertificadoRetencionesService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADORELACLABORAL --> " + configuration.relacionLaboralEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] certificadoRetencionesService");
        }
    }

    protected ZWSPEEMPLEADORELACLABORAL port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(RelacionLaboralService.class);
}
