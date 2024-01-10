package es.emasesa.intranet.sap.necesidadesFormacion.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.util.Validator;
import com.sap.document.sap.soap.functions.mc_style.*;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.necesidadesFormacion.exception.NecesidadesFormacionException;
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

@org.springframework.stereotype.Component("necesidadesFormacionService")
public class NecesidadesFormacionService {

    public JSONObject getPlanesFormacion(String planFormacion) throws NecesidadesFormacionException, SapCommunicationException {
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEDETECNECFORM.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            ZpeStDetecnecformConsulta detecnecformConsulta = new ZpeStDetecnecformConsulta();
            ZhrEDetnecform detecnecformInsert = new ZhrEDetnecform();

            detecnecformConsulta.setPlanFormacion(planFormacion);

            Holder<TableOfZhrPlanesForma> planesFormacion = new Holder<>();
            Holder<TableOfZhrEDetnecform> solicitudesCons = new Holder<>();
            Holder<TableOfZpeStDetecnecformTipos> tiposEventoPlanformacion = new Holder<>();

            port.zPeDetecnecform(detecnecformConsulta, detecnecformInsert, planFormacion, planesFormacion, solicitudesCons ,tiposEventoPlanformacion);
            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (planesFormacion.value != null){
                jsonReturn.put("planesFormacion", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(planesFormacion.value.getItem())));
            }
            if (solicitudesCons.value != null){
                jsonReturn.put("solicitudesCons", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(solicitudesCons.value.getItem())));
            }
            if (tiposEventoPlanformacion.value != null){
                jsonReturn.put("tiposEventoPlanformacion", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tiposEventoPlanformacion.value.getItem())));
            }
            return jsonReturn;
        } catch (ServerSOAPFaultException | JSONException e) {
            throw new NecesidadesFormacionException("Error llamando al WS para el origen o parseando datos"+ planFormacion, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] getPlanesFormacion");
        }
    }

    public JSONObject getNecesidadFormativa(String pernr, String planFormacion, String numFormuladaPor) throws NecesidadesFormacionException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEDETECNECFORM.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            ZpeStDetecnecformConsulta detecnecformConsulta = new ZpeStDetecnecformConsulta();
            ZhrEDetnecform detecnecformInsert = new ZhrEDetnecform();

            if(!Validator.isBlank(numFormuladaPor)) {
                detecnecformConsulta.setSolicitante(pernr);
                detecnecformConsulta.setPlanFormacion(planFormacion);
                detecnecformConsulta.setNumFormuladaPor(numFormuladaPor);
            }
            Holder<TableOfZhrPlanesForma> planesFormacion = new Holder<>();
            Holder<TableOfZhrEDetnecform> solicitudesCons = new Holder<>();
            Holder<TableOfZpeStDetecnecformTipos> tiposEventoPlanformacion = new Holder<>();

            port.zPeDetecnecform(detecnecformConsulta, detecnecformInsert, planFormacion, planesFormacion, solicitudesCons ,tiposEventoPlanformacion);
            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (planesFormacion.value != null){
                jsonReturn.put("planesFormacion", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(planesFormacion.value.getItem())));
            }
            if (solicitudesCons.value != null){
                jsonReturn.put("solicitudesCons", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(solicitudesCons.value.getItem())));
            }
            if (tiposEventoPlanformacion.value != null){
                jsonReturn.put("tiposEventoPlanformacion", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tiposEventoPlanformacion.value.getItem())));
            }
            return jsonReturn;
        } catch (ServerSOAPFaultException | JSONException e) {
            throw new NecesidadesFormacionException("Error llamando al WS para el origen o parseando datos"+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] necesidadesFormacion");
        }
    }

    public JSONObject saveNecesidadFormativa(String pernr, String planFormacion, String numFormuladaPor) throws NecesidadesFormacionException, SapCommunicationException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEDETECNECFORM.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            ZpeStDetecnecformConsulta detecnecformConsulta = new ZpeStDetecnecformConsulta();
            ZhrEDetnecform detecnecformInsert = new ZhrEDetnecform();
            detecnecformConsulta.setPlanFormacion(planFormacion);
            detecnecformConsulta.setSolicitante(pernr);
            detecnecformConsulta.setNumFormuladaPor(numFormuladaPor);

            Holder<TableOfZhrPlanesForma> planesFormacion = new Holder<>();
            Holder<TableOfZhrEDetnecform> solicitudesCons = new Holder<>();
            Holder<TableOfZpeStDetecnecformTipos> tiposEventoPlanformacion = new Holder<>();

            port.zPeDetecnecform(detecnecformConsulta, detecnecformInsert, planFormacion, planesFormacion, solicitudesCons ,tiposEventoPlanformacion);
            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (planesFormacion.value != null){
                jsonReturn.put("planesFormacion", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(planesFormacion.value.getItem())));
            }
            if (solicitudesCons.value != null){
                jsonReturn.put("solicitudesCons", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(solicitudesCons.value.getItem())));
            }
            if (tiposEventoPlanformacion.value != null){
                jsonReturn.put("tiposEventoPlanformacion", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tiposEventoPlanformacion.value.getItem())));
            }
            return jsonReturn;
        } catch (ServerSOAPFaultException | JSONException e) {
            throw new NecesidadesFormacionException("Error llamando al WS para el origen o parseando datos"+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] necesidadesFormacion");
        }
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando necesidadesFormacionService");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEDETECNECFORM.class.getClassLoader();
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
                URL urlEndpoint = new URL(configuration.necesidadesFormacionEndpoint());
            }catch (MalformedURLException e) {
                    if (LOG.isInfoEnabled()) {
                        LOG.info("Error en el WSDL de ZWSPEDETECNECFORM --> " + configuration.necesidadesFormacionEndpoint());
                    }
                }
            ZWSPEDETECNECFORM_Service service = new ZWSPEDETECNECFORM_Service();
            port = service.getPort(ZWSPEDETECNECFORM.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/

        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de necesidadesFormacionService");
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] necesidadesFormacionService");
        }
    }

    protected ZWSPEDETECNECFORM port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(NecesidadesFormacionService.class);
}
