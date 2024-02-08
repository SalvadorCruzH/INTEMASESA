package es.emasesa.intranet.sap.contratosCategorias.service;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistContrCat;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistFormPais;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoHistFormacion;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTCONTRCA;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOHISTCONTRCA_Service;
import com.sun.xml.ws.client.ClientTransportException;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.contratosCategorias.exception.ContratosCategoriasException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.List;

@org.springframework.stereotype.Component("contratosCategoriasService")
public class ContratosCategoriasService {


    public JSONObject obtenerContratosCategorias(String pernr) throws ContratosCategoriasException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] obtenerContratos");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTCONTRCA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            Holder<TableOfZpeStEmpleadoHistContrCat> tHistContrCategDesglose = new Holder<>();
            Holder<TableOfZpeStEmpleadoHistContrCat> tHistContrCategResumen = new Holder<>();

            port.zPeEmpleadoHistContrCateg(pernr, tHistContrCategDesglose, tHistContrCategResumen);


            JSONObject jsonReturn = JSONFactoryUtil.createJSONObject();
            if (tHistContrCategDesglose.value != null){
                jsonReturn.put("desglose", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tHistContrCategDesglose.value.getItem())));
            }
            if (tHistContrCategResumen.value != null){
                jsonReturn.put("resumen", JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerializeDeep(tHistContrCategResumen.value.getItem())));
            }

            LoggerUtil.debug(LOG, "devuelve: " +jsonReturn.toString());
            return jsonReturn;

        } catch (JSONException | ServerSOAPFaultException e) {
            LOG.error(e.getMessage());
            throw new ContratosCategoriasException("Error con el WS:" + e.getMessage(), e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] obtenerContratosCategoriasn");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando ContratosCategorias");
        }

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOHISTCONTRCA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt();
            String password = configuration.passwordPrompt();

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });
            URL urlEndpoint = new URL(configuration.contratosCategoriasEndpoint());
            ZWSPEEMPLEADOHISTCONTRCA_Service service = new ZWSPEEMPLEADOHISTCONTRCA_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADOHISTCONTRCA.class);

            /*******************UserName & Password ******************************/
            WSBindingProvider bp = ((WSBindingProvider) port);
            List<Handler> handlerChain =  bp.getBinding().getHandlerChain();
            handlerChain.add(new LogInterceptor());
            bp.getBinding().setHandlerChain(handlerChain);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            /**********************************************************************/
            LOG.info(this.getClass().getName() +" cargado correctamente");
        } catch (ConfigurationException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Se ha producido un error instanciando el servicio de ContratosCategoriasService", e);
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADOHISTCONTRCA_Service --> " + configuration.contratosCategoriasEndpoint());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] ContratosCategoriasService");
        }

    }

    private ZWSPEEMPLEADOHISTCONTRCA port;
    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(ContratosCategoriasService.class);

}
