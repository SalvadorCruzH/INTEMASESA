package es.emasesa.intranet.sap.datospersona.service;

import com.liferay.petra.string.StringUtil;
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
import es.emasesa.intranet.sap.base.logging.LogInterceptor;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosPersonalesException;
import es.emasesa.intranet.sap.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

import java.net.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import jakarta.xml.ws.handler.Handler;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("empleadoDatosPersonalesService")
public class EmpleadoDatosPersonalesService {

    public JSONObject getEmpleadoDatosPersonales(String pernr) throws EmpleadoDatosPersonalesException, SapCommunicationException {

        LoggerUtil.debug(LOG, "[B] getEmpleadoDatosPersonales");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADODATOSPERSONA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            TableOfZpeStEmpleadoDatosPersonal serviceResult = new TableOfZpeStEmpleadoDatosPersonal();
            if(Validator.isNumber(pernr)){
                serviceResult = port.zPeEmpleadoDatosPersonales(pernr, "");
            }else{
                serviceResult = port.zPeEmpleadoDatosPersonales("", pernr);
            }
            ZpeStEmpleadoDatosPersonal empleadoDatosPersonal = serviceResult.getItem().stream().findFirst().orElse(null);
            return JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerializeDeep(empleadoDatosPersonal));
        } catch (JSONException | ServerSOAPFaultException e) {
            throw new EmpleadoDatosPersonalesException("Error llamando al WS para el pernr "+ pernr, e);
        } catch (ClientTransportException e) {
            throw new SapCommunicationException("Error llamando al WS, error de comunicación ", e);
        } finally {
            LoggerUtil.debug(LOG, "[E] getEmpleadoDatosPersonales");
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[I] Activando EmpleadoDatosPersonalesService");
        }
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

        SapServicesConfiguration configuration = null;
        try {
            configuration = sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADODATOSPERSONA.class.getClassLoader();
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
            ZWSPEEMPLEADODATOSPERSONA_Service service = new ZWSPEEMPLEADODATOSPERSONA_Service(urlEndpoint);
            port = service.getPort(ZWSPEEMPLEADODATOSPERSONA.class);

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
                LOG.info("Se ha producido un error instanciando el servicio de EmpleadoDatosPersonalesService");
            }
        } catch (MalformedURLException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Error en el WSDL de ZWSPEEMPLEADODATOSPERSONA_Service --> " + configuration.empleadoDatosPersonalesEndpoint());
            }
        }finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] EmpleadoDatosPersonalesService");
        }
    }

    protected ZWSPEEMPLEADODATOSPERSONA port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

    private static final Log LOG = LogFactoryUtil.getLog(EmpleadoDatosPersonalesService.class);

}
