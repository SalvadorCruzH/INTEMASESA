package es.emasesa.intranet.jornada.nomina.service;

import es.emasesa.intranet.jornada.nomina.generated.Bapireturn1;
import es.emasesa.intranet.jornada.nomina.generated.ObjectFactory;
import es.emasesa.intranet.jornada.nomina.generated.ZPeActJornadaNominaResponse;
import es.emasesa.intranet.jornada.nomina.generated.ZWSPEACTJORNADANOMINA;
import es.emasesa.intranet.jornada.nomina.generated.ZWSPEACTJORNADANOMINA_Service;
import es.emasesa.intranet.jornada.nomina.generated.ZpeStActJornadaNomina;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.spi.Provider;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.spi.ProviderImpl;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.auth.HttpAuthHeader;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(immediate = true, property = {}, service = ConsultaCertificadoRet.class)
public class ConsultaCertificadoRet {


    public Bapireturn1 peticionHorasExtras(String idEmpleado, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipoRetribucion) throws DatatypeConfigurationException {
        ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();
        zpeStActJornadaNomina.setPernr(idEmpleado);
        zpeStActJornadaNomina.setHeInicio(getXMLGregorianCalendar(fechaInicio));
        zpeStActJornadaNomina.setHeFin(getXMLGregorianCalendar(fechaFin));
        zpeStActJornadaNomina.setHeTipoRetribucion(tipoRetribucion);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        zpeStActJornadaNomina.setFechaInicio(fechaInicio.format(dtf));
        ZPeActJornadaNominaResponse result= getObjectFactory().createZPeActJornadaNominaResponse();
        result.setReturn(port.zPeActJornadaNomina(zpeStActJornadaNomina));

        return result.getReturn();


    }

    private XMLGregorianCalendar getXMLGregorianCalendar(LocalDateTime localDateTime) throws DatatypeConfigurationException {
        ZoneId zoneId = ZoneId.systemDefault();
        GregorianCalendar gregorianCalendar = GregorianCalendar
                .from(localDateTime.atZone(zoneId));
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar(
                        gregorianCalendar
                );
        return xmlGregorianCalendar;
    }


    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }



    @Activate
    @Modified
    public void activate(BundleContext bundleContext, Map<String, Object> properties) {

        ServiceReference<Provider> providerServiceReference = bundleContext.getServiceReference(Provider.class);
        if(providerServiceReference == null) {
            ProviderImpl providerImpl = new ProviderImpl();
            Dictionary<String, Object> providerProperties = new Hashtable<>();
            bundleContext.registerService(Provider.class, providerImpl, providerProperties);
        }
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();


        factory.setServiceClass(ZWSPEACTJORNADANOMINA.class);
        factory.setServiceName(ZWSPEACTJORNADANOMINA_Service.SERVICE);
        factory.setAddress("http://tc0002.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");


        factory.getInInterceptors().add(new RequestInInterceptor());
        factory.getOutInterceptors().add(new RequestOutInterceptor());

        String userName = "pe_hrcons"; //TODO: Poner en settings, estan creadas
        String password = "J2iea.117";//TODO: Poner en settings, estan creadas

        ZWSPEACTJORNADANOMINA service=(ZWSPEACTJORNADANOMINA) factory.create();
        BindingProvider bindingProvider = (BindingProvider) service;
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

        this.port = service;
    }

    private ZWSPEACTJORNADANOMINA port;

}
