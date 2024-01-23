package es.emasesa.intranet.sap.retenciones.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ZWSPECONSULTACERTRETPDF;
import com.sap.document.sap.soap.functions.mc_style.ZWSPECONSULTACERTRETPDF_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.retenciones.exception.CertificadoRetencionesException;
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

import org.apache.commons.codec.binary.Base64;

@org.springframework.stereotype.Component("certificadoRetencionesService")
public class CertificadoRetencionesService {

    public JSONObject getCertificadoRetenciones(String pernr, String imVariante) throws CertificadoRetencionesException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Holder<byte[]> out = new Holder<>();
            Holder<Integer> exResult = new Holder<>();
            ClassLoader objectFactoryClassLoader = ZWSPECONSULTACERTRETPDF.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            port.zPeConsultaCertRetPdf(imVariante, pernr , out, exResult);
            JSONObject certificado = JSONFactoryUtil.createJSONObject("");
            //String decoded = new String(Base64.getDecoder().decode(out.value));

            if (exResult.value != 2) {
                String decoded = Base64.encodeBase64String(out.value);
                certificado.put("valor", decoded);
                String pdfName = "Certificado_" + imVariante + ".pdf";
                certificado.put("pdf", pdfName);
                String resultado = "<a href=\"javascript:void(0);\" class=\"ema-boton-descargar\" onclick=\"descargarPDF('#valor#', '"+pdfName+"')\">\n" +
                        "                        <i class=\"fa-solid fa-download\"></i> Descargar\n" +
                        "                    </a>";
                certificado.put("resultado", resultado);
            } else {
                String resultado = "No hay certificados.";
                certificado.put("resultado", resultado);
            }
            certificado.put("ano", imVariante);
            return certificado;
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new CertificadoRetencionesException("Error llamando al WS para el pernr "+ pernr, e);
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
            ClassLoader objectFactoryClassLoader = ZWSPECONSULTACERTRETPDF.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.certificadoRetencionesEndpoint());
            ZWSPECONSULTACERTRETPDF_Service service = new ZWSPECONSULTACERTRETPDF_Service(urlEndpoint);
            port = service.getPort(ZWSPECONSULTACERTRETPDF.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/
            LOG.info(this.getClass().getName() +" cargado correctamente");
        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de CertificadoRetencionesService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPECONSULTACERTRETPDF_Service --> " + configuration.certificadoRetencionesEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] certificadoRetencionesService");
        }
    }

    protected ZWSPECONSULTACERTRETPDF port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(CertificadoRetencionesService.class);
}
