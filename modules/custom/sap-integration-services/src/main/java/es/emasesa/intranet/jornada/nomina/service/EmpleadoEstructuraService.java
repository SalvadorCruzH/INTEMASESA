package es.emasesa.intranet.jornada.nomina.service;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoEstructura;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA_Service;
import com.sap.document.sap.soap.functions.mc_style.ZpeStEmpleadoEstructura;
import com.sun.xml.ws.developer.WSBindingProvider;
import es.emasesa.intranet.jornada.nomina.base.AbstractSelfRegisteringService;

import es.emasesa.intranet.jornada.nomina.util.SapConfigurationUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import org.osgi.service.component.annotations.Component;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Component("empleadoEstructuraService")
public class EmpleadoEstructuraService {

    public JSONObject getEmpleadoEstructura(String pernr) throws JSONException {

        TableOfZpeStEmpleadoEstructura result = port.zPeEmpleadoEstructura(pernr);
        JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
        ZpeStEmpleadoEstructura empleadoEstructura = result.getItem().stream().findFirst().orElse(null);

        jsonResult = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerializeDeep(empleadoEstructura));

        return jsonResult;
    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @PostConstruct
    public void activate(){

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();

        try {
            SapServicesConfiguration configuration =  sapConfigurationUtil.getConfiguration();
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOESTRUCTURA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = configuration.userPrompt(); //"pe_hrcons"; //TODO: Poner en settings, estan creadas
            String password = configuration.passwordPrompt(); //"J2iea.117";//TODO: Poner en settings, estan creadas

            URL url = new URL(configuration.empleadoEstructuraEndpoint());

            ZWSPEEMPLEADOESTRUCTURA_Service service = new ZWSPEEMPLEADOESTRUCTURA_Service();
            port = service.getPort(ZWSPEEMPLEADOESTRUCTURA.class);

            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });

            /*******************UserName & Password ******************************/
            Map<String, Object> requestContext = ((WSBindingProvider) port).getRequestContext();
            WSBindingProvider bp = ((WSBindingProvider) port);
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuration.empleadoEstructuraEndpoint());
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            /**********************************************************************/


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

    }

    protected ZWSPEEMPLEADOESTRUCTURA port;

    @Autowired
    SapConfigurationUtil sapConfigurationUtil;

}
