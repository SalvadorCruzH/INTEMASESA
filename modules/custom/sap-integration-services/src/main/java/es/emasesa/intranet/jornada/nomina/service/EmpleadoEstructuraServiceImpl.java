package es.emasesa.intranet.jornada.nomina.service;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoEstructura;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEEMPLEADOESTRUCTURA_Service;
import com.sap.document.sap.soap.functions.mc_style.ZpeStEmpleadoEstructura;
import com.sun.xml.ws.developer.WSBindingProvider;
import es.emasesa.intranet.jornada.nomina.base.AbstractSelfRegisteringService;

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

@org.springframework.stereotype.Component("empleadoEstructuraService")
public class EmpleadoEstructuraServiceImpl {

    public JSONObject getEmpleadoEstructura(String pernr) {

        return null;
    }

    @PostConstruct
    public JSONObject getEmpleadoEstructura() {

        String pernr = "1002982";

        try {

            activate();
            JSONObject jsonResult = JSONFactoryUtil.createJSONObject();
            TableOfZpeStEmpleadoEstructura result = port.zPeEmpleadoEstructura(pernr);
            ZpeStEmpleadoEstructura empleadoEstructura = result.getItem().stream().findFirst().orElse(null);

            jsonResult = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerializeDeep(empleadoEstructura));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }


    public void activate() throws MalformedURLException {

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = ZWSPEEMPLEADOESTRUCTURA.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

            String userName = "pe_hrcons"; //TODO: Poner en settings, estan creadas
            String password = "J2iea.117";//TODO: Poner en settings, estan creadas

            URL url = new URL("http://tc0002.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");

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
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://tc0002.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            //headers.put("Username", userName);
            //headers.put("Password", password);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userName);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            /**********************************************************************/

            /*JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

            factory.setServiceClass(ZWSPEEMPLEADOESTRUCTURA.class);
            factory.setServiceName(ZWSPEEMPLEADOESTRUCTURA_Service.ZWSPEEMPLEADOESTRUCTURA_QNAME);
            factory.setAddress("http://tc0002.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");

            Map<String, Object> jaxProperties = factory.getProperties();
            if (jaxProperties == null) {
                jaxProperties = new HashMap<String, Object>();
            }*/

            //jaxProperties.put("soap.no.validate.parts", "true");
            //factory.setProperties(jaxProperties);
            //factory.getInInterceptors().add(new RequestInInterceptor());
            //factory.getOutInterceptors().add(new RequestOutInterceptor());
            //factory.getInInterceptors().add(new ReturnInterceptor());
            //factory.getInInterceptors().add(new ReturnInInterceptor());
            //String userName = "pe_hrcons"; //TODO: Poner en settings, estan creadas
            //String password = "J2iea.117";//TODO: Poner en settings, estan creadas
            // ZWSPEEMPLEADOESTRUCTURA service = (ZWSPEEMPLEADOESTRUCTURA) factory.create();
            // BindingProvider bindingProvider = (BindingProvider) service;
            //bindingProvider.getRequestContext().put("set-jaxb-validation-event-handler", "false");
            //bindingProvider.getRequestContext().put("jaxb-validation-event-handler", new ValidationHandler());
            /*Object jaxWsService = factory.create();
            Map<String, Object> requestContext = bindingProvider.getRequestContext();
            Map<String, List<String>> requestHeaders = new HashMap<String, List<String>>();
            requestHeaders.put(BindingProvider.USERNAME_PROPERTY, Collections.singletonList(userName));
            requestHeaders.put(BindingProvider.PASSWORD_PROPERTY, Collections.singletonList(password));
            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);

            Client client = ClientProxy.getClient(service);
            HTTPConduit http = (HTTPConduit) client.getConduit();
            http.getAuthorization().setAuthorizationType(HttpAuthHeader.AUTH_TYPE_BASIC);
            http.getAuthorization().setAuthorization("true");
            http.getAuthorization().setUserName(userName);
            http.getAuthorization().setPassword(password);*/


            TableOfZpeStEmpleadoEstructura tbe = port.zPeEmpleadoEstructura("1002982");

            tbe.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }

    }

    protected ZWSPEEMPLEADOESTRUCTURA port;

}
