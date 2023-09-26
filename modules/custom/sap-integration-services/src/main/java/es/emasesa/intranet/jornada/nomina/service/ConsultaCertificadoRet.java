package es.emasesa.intranet.jornada.nomina.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;

import com.sap.document.sap.soap.functions.mc_style.*;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(immediate = true, property = {}, service = ConsultaCertificadoRet.class)
public class ConsultaCertificadoRet {


   public Bapireturn1 peticionHorasExtras(String idEmpleado, String fechaInicio, String fechaFin, String tipoRetribucion)  {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaInicioDateTime = LocalDateTime.parse("2023-10-15 18:00", formatter);
            LocalDateTime fechaFinDateTime = LocalDateTime.parse("2023-10-15 20:00", formatter);

            return peticionHorasExtras(idEmpleado, fechaInicioDateTime, fechaFinDateTime, tipoRetribucion);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Bapireturn1();
    }

    public Bapireturn1 peticionHorasExtras(String idEmpleado, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipoRetribucion) {
       try {
           ZPeActJornadaNomina zpeStActJornadaNominaParent = getObjectFactory().createZPeActJornadaNomina();
           ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
           zpeStActJornadaNomina.setPernr(idEmpleado);
           zpeStActJornadaNomina.setHeInicio(getXMLGregorianCalendar(fechaInicio));
           zpeStActJornadaNomina.setHeFin(getXMLGregorianCalendar(fechaFin));
           zpeStActJornadaNomina.setHeTipoRetribucion(tipoRetribucion);
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           zpeStActJornadaNomina.setFechaInicio(fechaInicio.format(dtf));
           zpeStActJornadaNominaParent.setTEmpleados(zpeStActJornadaNomina);
           ZPeActJornadaNominaResponse result = getObjectFactory().createZPeActJornadaNominaResponse();

           Bapireturn1 result1 = port.zPeActJornadaNomina(zpeStActJornadaNomina);

           return result1;
       }catch(Exception e){
           e.printStackTrace();
       }

       return null;
    }

    private XMLGregorianCalendar getXMLGregorianCalendar(LocalDateTime localDateTime) throws DatatypeConfigurationException {
        ZoneId zoneId = ZoneId.systemDefault();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar(
                        gregorianCalendar
                );
        /*
        GregorianCalendar gregorianCalendar = GregorianCalendar
                .from(localDateTime.atZone(zoneId));
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar(
                        gregorianCalendar
                );

        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        xmlGregorianCalendar.setTime(localDateTime.getHour(), localDateTime.getMinute(), DatatypeConstants.FIELD_UNDEFINED);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        System.out.println(formato.format(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())));
*/

        return xmlGregorianCalendar;

        /*
        XMLGregorianCalendar gDateFormatted =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(formato.format(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())));
*/
    }


    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }


    @Activate
    @Modified
    public void activate(BundleContext bundleContext, Map<String, Object> properties) throws MalformedURLException {

        /*ServiceReference<Provider> providerServiceReference = bundleContext.getServiceReference(Provider.class);
        if (providerServiceReference == null) {
            ProviderImpl providerImpl = new ProviderImpl();
            Dictionary<String, Object> providerProperties = new Hashtable<>();
            bundleContext.registerService(Provider.class, providerImpl, providerProperties);
        }
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(ZWSPEACTJORNADANOMINA.class);
        factory.setServiceName(ZWSPEACTJORNADANOMINA_Service.SERVICE);
        factory.setAddress("http://tc0002.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");

        Map<String, Object> jaxProperties = factory.getProperties();
        if(jaxProperties==null){
            jaxProperties= new HashMap<String, Object>();
        }

        jaxProperties.put("soap.no.validate.parts", "true");
        //factory.setProperties(jaxProperties);
        factory.getInInterceptors().add(new RequestInInterceptor());
        factory.getOutInterceptors().add(new RequestOutInterceptor());
        //factory.getInInterceptors().add(new ReturnInterceptor());
        factory.getInInterceptors().add(new ReturnInInterceptor());
        String userName = "pe_hrcons"; //TODO: Poner en settings, estan creadas
        String password = "J2iea.117";//TODO: Poner en settings, estan creadas

        ZWSPEACTJORNADANOMINA service = (ZWSPEACTJORNADANOMINA) factory.create();
        BindingProvider bindingProvider = (BindingProvider) service;
        //bindingProvider.getRequestContext().put("set-jaxb-validation-event-handler", "false");
        //bindingProvider.getRequestContext().put("jaxb-validation-event-handler", new ValidationHandler());
        Object jaxWsService = factory.create();
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
        http.getAuthorization().setPassword(password);
        //client.getEndpoint().getInInterceptors().add(new ReturnInterceptor());

*/
        String userName = "pe_hrcons"; //TODO: Poner en settings, estan creadas
        String password = "J2iea.117";//TODO: Poner en settings, estan creadas

        URL url = new URL("http://tc0002.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");

        ZWSPEACTJORNADANOMINA_Service service = new ZWSPEACTJORNADANOMINA_Service(url);
        port = service.getPort(ZWSPEACTJORNADANOMINA.class);

        /*******************UserName & Password ******************************/
        Map<String, Object> requestContext = ((BindingProvider)port).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put("Username", Collections.singletonList(userName));
        headers.put("Password", Collections.singletonList(password));
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        /**********************************************************************/
    }

   private ZWSPEACTJORNADANOMINA port;

}
