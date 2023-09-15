package com.sap.document.sap.soap.functions.mc_style;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.4.4
 * Generated source version: 3.4.4
 *
 */
@WebServiceClient(name = "Z_WS_PE_ACT_JORNADA_NOMINA",
                  wsdlLocation = "META-INF/wsdl/jornadaNomina.wsdl",
                  targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
public class ZWSPEACTJORNADANOMINA_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_ACT_JORNADA_NOMINA");
    public final static QName ZWSPEACTJORNADANOMINASoap12 = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_ACT_JORNADA_NOMINA_soap12");
    public final static QName ZWSPEACTJORNADANOMINA = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_ACT_JORNADA_NOMINA");
    static {
        URL url = ZWSPEACTJORNADANOMINA_Service.class.getResource("META-INF/wsdl/jornadaNomina.wsdl");
        if (url == null) {
            url = ZWSPEACTJORNADANOMINA_Service.class.getClassLoader().getResource("META-INF/wsdl/jornadaNomina.wsdl");
        }
        if (url == null) {
            java.util.logging.Logger.getLogger(ZWSPEACTJORNADANOMINA_Service.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "META-INF/wsdl/jornadaNomina.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ZWSPEACTJORNADANOMINA_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZWSPEACTJORNADANOMINA_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZWSPEACTJORNADANOMINA_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ZWSPEACTJORNADANOMINA_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ZWSPEACTJORNADANOMINA_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ZWSPEACTJORNADANOMINA_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns ZWSPEACTJORNADANOMINA
     */
    @WebEndpoint(name = "Z_WS_PE_ACT_JORNADA_NOMINA_soap12")
    public ZWSPEACTJORNADANOMINA getZWSPEACTJORNADANOMINASoap12() {
        return super.getPort(ZWSPEACTJORNADANOMINASoap12, ZWSPEACTJORNADANOMINA.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSPEACTJORNADANOMINA
     */
    @WebEndpoint(name = "Z_WS_PE_ACT_JORNADA_NOMINA_soap12")
    public ZWSPEACTJORNADANOMINA getZWSPEACTJORNADANOMINASoap12(WebServiceFeature... features) {
        return super.getPort(ZWSPEACTJORNADANOMINASoap12, ZWSPEACTJORNADANOMINA.class, features);
    }


    /**
     *
     * @return
     *     returns ZWSPEACTJORNADANOMINA
     */
    @WebEndpoint(name = "Z_WS_PE_ACT_JORNADA_NOMINA")
    public ZWSPEACTJORNADANOMINA getZWSPEACTJORNADANOMINA() {
        return super.getPort(ZWSPEACTJORNADANOMINA, ZWSPEACTJORNADANOMINA.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSPEACTJORNADANOMINA
     */
    @WebEndpoint(name = "Z_WS_PE_ACT_JORNADA_NOMINA")
    public ZWSPEACTJORNADANOMINA getZWSPEACTJORNADANOMINA(WebServiceFeature... features) {
        return super.getPort(ZWSPEACTJORNADANOMINA, ZWSPEACTJORNADANOMINA.class, features);
    }

}
