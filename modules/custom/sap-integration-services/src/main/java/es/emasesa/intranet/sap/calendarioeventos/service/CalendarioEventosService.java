package es.emasesa.intranet.sap.calendarioeventos.service;

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
import es.emasesa.intranet.sap.ayudaEscolar.service.AyudaEscolarService;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.calendarioeventos.exception.CalendarioEventosException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.jws.WebParam;
import jakarta.xml.ws.Holder;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("calendarioEventosService")
public class CalendarioEventosService {

    public JSONObject getCalendarioEventos(String pernr, String fechaDesde, String fechaHasta) throws CalendarioEventosException, SapCommunicationException {
        LoggerUtil.debug(LOG, "[I] calendarioEventos");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPECALENDARIOEVENTOS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);


            Holder<TableOfZpeStCalendarioEventosCoob> tContenido = new Holder<>();
            Holder<TableOfZpeStCalendarioEventosDocu> tDocumentos = new Holder<>();
            Holder<TableOfZpeStCalendarioEventos> tEventos = new Holder<>();
            Holder<TableOfZpeStCalendarioEventosCoob> tObjetivo = new Holder<>();


            String inscripcionAccion = "";
            String inscripcionEvento = "";

            port.zPeCalendarioEventos(fechaDesde, fechaHasta, pernr, inscripcionAccion, inscripcionEvento, tContenido, tDocumentos, tEventos, tObjetivo);

            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tContenido.value != null){
                jsonReturn.put("contenido", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tContenido.value.getItem())));
            }
            if (tEventos.value != null){
                jsonReturn.put("eventos", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tEventos.value.getItem())));
            }
            if (tObjetivo.value != null){
                jsonReturn.put("objetivo", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tObjetivo.value.getItem())));
            }
            if (tDocumentos.value != null){
                jsonReturn.put("documento", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tDocumentos.value.getItem())));
            }
            return jsonReturn;
        } catch (ServerSOAPFaultException | JSONException e) {
            throw new CalendarioEventosException("Error llamando al WS para el origen o parseando datos", e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] calendarioEventos");
        }
    }

    public JSONObject inscribirCalendarioEventos(String pernr, String fechaDesde, String fechaHasta, String inscripcionAccion, String inscripcionEvento) throws CalendarioEventosException, SapCommunicationException {
        LoggerUtil.debug(LOG, "[I] calendarioEventos");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPECALENDARIOEVENTOS.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            Holder<TableOfZpeStConteniObjetiEventos> tConteniObjetiEventos = new Holder<>();
            Holder<TableOfZpeStCalendarioEventos> tCalendarioEventos = new Holder<>();
            Holder<TableOfZpeStConteniObjetiEventos> tConteniObjetiEventos2 = new Holder<>();

            port.zPeCalendarioEventos(fechaDesde, fechaHasta, pernr, inscripcionAccion, inscripcionEvento, tConteniObjetiEventos, tCalendarioEventos, tConteniObjetiEventos2);

            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tConteniObjetiEventos.value != null){
                jsonReturn.put("contenido", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tConteniObjetiEventos.value.getItem())));
            }
            if (tCalendarioEventos.value != null){
                jsonReturn.put("eventos", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tCalendarioEventos.value.getItem())));
            }
            if (tConteniObjetiEventos2.value != null){
                jsonReturn.put("objetivo", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tConteniObjetiEventos2.value.getItem())));
            }
            return jsonReturn;
        } catch (ServerSOAPFaultException | JSONException e) {
            throw new CalendarioEventosException("Error llamando al WS para el origen o parseando datos", e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
            LoggerUtil.debug(LOG, "[E] calendarioEventos");
        }
    }


    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando calendarioEventos");
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
            URL urlEndpoint = new URL(configuration.calendarioEventosEndpoint());
            ZWSPECALENDARIOEVENTOS_Service service = new ZWSPECALENDARIOEVENTOS_Service(urlEndpoint);
            port = service.getPort(ZWSPECALENDARIOEVENTOS.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/
            LOG.info(this.getClass().getName() +" cargado correctamente");
        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de calendarioEventosServices");
            }
        }  catch (MalformedURLException e){
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPECALENDARIOEVENTOS --> " + configuration.calendarioEventosEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] calendarioEventos");
        }
    }

    protected ZWSPECALENDARIOEVENTOS port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(CalendarioEventosService.class);
}
