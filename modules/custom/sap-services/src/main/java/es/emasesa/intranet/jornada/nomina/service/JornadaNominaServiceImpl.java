package es.emasesa.intranet.jornada.nomina.service;

import com.sap.document.sap.soap.functions.mc_style.Bapireturn1;
import com.sap.document.sap.soap.functions.mc_style.ObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEACTJORNADANOMINA;
import com.sap.document.sap.soap.functions.mc_style.ZWSPEACTJORNADANOMINA_Service;
import com.sap.document.sap.soap.functions.mc_style.ZpeStActJornadaNomina;
import es.emasesa.intranet.jornada.nomina.model.JornadaNominaService;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Dictionary;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.spi.Provider;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.spi.ProviderImpl;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.auth.HttpAuthHeader;
import org.apache.cxf.transport.http.auth.WSDLGetAuthenticatorInterceptor;
import org.apache.cxf.ws.policy.EndpointPolicyImpl;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(immediate = true, property = {}, service = JornadaNominaService.class)
public class JornadaNominaServiceImpl implements JornadaNominaService {
    private URL wsdlURL;

    public Bapireturn1 peticionHorasExtras(String idEmpleado, LocalDateTime fechaInicio, LocalDateTime fechaFin, String tipoRetribucion) throws DatatypeConfigurationException {
        ZWSPEACTJORNADANOMINA port = getPort();
        ZpeStActJornadaNomina zpeStActJornadaNomina = getObjectFactory().createZpeStActJornadaNomina();

        zpeStActJornadaNomina.setPernr(idEmpleado);
        zpeStActJornadaNomina.setHeInicio(getXMLGregorianCalendar(fechaInicio));
        zpeStActJornadaNomina.setHeFin(getXMLGregorianCalendar(fechaFin));
        zpeStActJornadaNomina.setHeTipoRetribucion(tipoRetribucion);

        return port.zPeActJornadaNomina(zpeStActJornadaNomina);


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

        this.wsdlURL = getClass().getClassLoader().getResource("META-INF/wsdl/jornadaNomina.wsdl");
        this._zWSPEACTJORNADANOMINA_Service = new ZWSPEACTJORNADANOMINA_Service(wsdlURL);
    }

    public ZWSPEACTJORNADANOMINA getPort(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.getInInterceptors().add(new MyLogInterceptor());
        factory.getOutInterceptors().add(new MyLogInterceptor());

        factory.setServiceClass(ZWSPEACTJORNADANOMINA.class);
        factory.setAddress("http://descom.desevilla.org:8010/sap/bc/srt/rfc/sap/z_ws_pe_act_jornada_nomina/010/z_ws_pe_act_jornada_nomina/z_ws_pe_act_jornada_nomina");

        ZWSPEACTJORNADANOMINA info = (ZWSPEACTJORNADANOMINA) factory.create();
        String userName = "pe_hrcons"; //TODO: Poner en settings, estan creadas
        String password = "J2iea.117";//TODO: Poner en settings, estan creadas
        Client client = ClientProxy.getClient(info);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        http.getAuthorization().setAuthorizationType(HttpAuthHeader.AUTH_TYPE_BASIC);
        http.getAuthorization().setAuthorization("true");
        http.getAuthorization().setUserName(userName);
        http.getAuthorization().setPassword(password);

        return info;

    }


    private ZWSPEACTJORNADANOMINA_Service _zWSPEACTJORNADANOMINA_Service;
}
