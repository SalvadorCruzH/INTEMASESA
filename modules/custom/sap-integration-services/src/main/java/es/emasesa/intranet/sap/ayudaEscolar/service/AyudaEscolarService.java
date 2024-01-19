package es.emasesa.intranet.sap.ayudaEscolar.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.rfc.functions.ZPESTINSAYUDAESCOLAR;
import com.sap.document.sap.soap.functions.mc_style.*;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.ayudaEscolar.exception.AyudaEscolarException;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
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

@org.springframework.stereotype.Component("ayudaEscolarService")
public class AyudaEscolarService {

    public JSONObject saveAyudaEscolar(String pernr, String centro, String estudioId, String estudioNivel,
                                       String famNumerosa, String numero, String tipoId, String famMonoParental, String comentario) throws AyudaEscolarException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEAYUDAESCOLAR.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            ZPESTINSAYUDAESCOLAR tInsAyudaEscolar = new ZPESTINSAYUDAESCOLAR();
            tInsAyudaEscolar.setCENTRO(centro);
            tInsAyudaEscolar.setESTUDIOID(estudioId);
            tInsAyudaEscolar.setESTUDIONIVEL(estudioNivel);
            tInsAyudaEscolar.setFAMNUMEROSA(famNumerosa);
            tInsAyudaEscolar.setNUMERO(numero);
            tInsAyudaEscolar.setPERNR(pernr);
            tInsAyudaEscolar.setTIPOID(tipoId);
            tInsAyudaEscolar.setFAMMONOPARENTAL(famMonoParental);
            tInsAyudaEscolar.setCOMENTARIO(comentario);
            Holder<TableOfZpeStAyudasSolicitadas> tAyudasSolicitadas = new Holder<>();
            Holder<TableOfZpeStBeneficiarios> tBeneficiarios = new Holder<>();
            Holder<TableOfZpeStEstudios> tEstudios = new Holder<>();

            port.zPeAyudaEscolar(pernr, tInsAyudaEscolar, tAyudasSolicitadas, tBeneficiarios ,tEstudios);
            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tAyudasSolicitadas.value != null){
                jsonReturn.put("ayudasSolicitadas", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tAyudasSolicitadas.value.getItem())));
            }
            if (tBeneficiarios.value != null){
                jsonReturn.put("beneficiarios", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tBeneficiarios.value.getItem())));
            }
            if (tEstudios.value != null){
                jsonReturn.put("estudios", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tEstudios.value.getItem())));
            }
            return jsonReturn;
        } catch (ServerSOAPFaultException | JSONException e) {
            throw new AyudaEscolarException("Error llamando al WS para el origen o parseando datos"+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] solicitarAyudaEscolar");
        }
    }

    public JSONObject getAyudaEscolar(String pernr) throws AyudaEscolarException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEAYUDAESCOLAR.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            ZPESTINSAYUDAESCOLAR tInsAyudaEscolar = new ZPESTINSAYUDAESCOLAR();
            Holder<TableOfZpeStAyudasSolicitadas> tAyudasSolicitadas = new Holder<>();
            Holder<TableOfZpeStBeneficiarios> tBeneficiarios = new Holder<>();
            Holder<TableOfZpeStEstudios> tEstudios = new Holder<>();

            port.zPeAyudaEscolar(pernr, tInsAyudaEscolar, tAyudasSolicitadas, tBeneficiarios ,tEstudios);
            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tAyudasSolicitadas.value != null){
                jsonReturn.put("ayudasSolicitadas", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tAyudasSolicitadas.value.getItem())));
            }
            if (tBeneficiarios.value != null){
                jsonReturn.put("beneficiarios", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tBeneficiarios.value.getItem())));
            }
            if (tEstudios.value != null){
                jsonReturn.put("estudios", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tEstudios.value.getItem())));
            }
            return jsonReturn;
        } catch (ServerSOAPFaultException | JSONException e) {
            throw new AyudaEscolarException("Error llamando al WS para el origen o parseando datos"+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
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
            URL urlEndpoint = new URL(configuration.ayudaEscolarEndpoint());
            ZWSPEAYUDAESCOLAR_Service service = new ZWSPEAYUDAESCOLAR_Service(urlEndpoint);
            port = service.getPort(ZWSPEAYUDAESCOLAR.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/
            LOG.info(this.getClass().getName() +" cargado correctamente");
        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de ayudaEscolarService");
            }
        }  catch (MalformedURLException e){
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEAYUDAESCOLAR --> " + configuration.ayudaEscolarEndpoint());
            }
        } finally {
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
