package es.emasesa.intranet.sap.resumenanual.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoJornadaResume;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStMarcajesHistoricoActu;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOJORNADARESUM;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOJORNADARESUM_Service;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEMARCAJESHISTORICOACT_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.estructura.exception.EmpleadoEstructuraException;
import es.emasesa.intranet.sap.marcaje.exception.MarcajeException;
import es.emasesa.intranet.sap.resumenanual.exception.ResumenAnualException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.jws.WebParam;
import jakarta.xml.ws.Holder;
import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("resumenAnual")
public class ResumenAnualService {


    public JSONArray obtenerResumenAnual(String pernr, String anno) throws ResumenAnualException, SapCommunicationException {
        JSONArray data = JSONFactoryUtil.createJSONArray();

        Holder<BigDecimal> computoConFuturo = new Holder<>();
        Holder<BigDecimal> computoSinFuturo= new Holder<>();;
        Holder<BigDecimal> computoSinFuturoAnnoAnteri= new Holder<>();;
        Holder<BigDecimal> contingenteVacaciones= new Holder<>();;
        Holder<TableOfZpeStEmpleadoJornadaResume> tEmpleados= new Holder<>();;
        try {
            port.zPeEmpleadoJornadaResumen(anno,pernr,computoConFuturo,
                    computoSinFuturo,computoSinFuturoAnnoAnteri,contingenteVacaciones,tEmpleados);
            if(!tEmpleados.value.getItem().isEmpty()){
                data = JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tEmpleados.value.getItem()));
                for(int i=0;i<data.length();i++){
                    JSONObject jsonObject = data.getJSONObject(i);
                    jsonObject.put("contingenteVacaciones",contingenteVacaciones.value);
                    jsonObject.put("computoConFuturo",computoConFuturo.value);
                    jsonObject.put("computoSinFuturo",computoSinFuturo.value);
                    jsonObject.put("computoSinFuturoAnnoAnteri",computoSinFuturoAnnoAnteri.value);

                }
            }

        }catch (JSONException | ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new ResumenAnualException("Error con el WS:" + e.getMessage(), e);
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
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando ResumenAnualService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOJORNADARESUM.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            URL urlEndpoint = new URL(configuration.marcajeEndpoint());
            ZWSPEEMPLEADOJORNADARESUM_Service service = new ZWSPEEMPLEADOJORNADARESUM_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADOJORNADARESUM.class);

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


        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de ResumenAnualService");
            }
        }catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADOJORNADARESUM_Service --> " + configuration.marcajeEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] ResumenAnualService");
        }
    }

    private ZWSPEEMPLEADOJORNADARESUM port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(ResumenAnualService.class);

}
