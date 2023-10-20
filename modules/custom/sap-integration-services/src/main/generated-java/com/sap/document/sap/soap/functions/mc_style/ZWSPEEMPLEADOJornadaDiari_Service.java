
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.ws.*;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.1
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "Z_WS_PE_EMPLEADO_JORNADA_DIARI", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", wsdlLocation = "classpath:META-INF/wsdl/z_ws_pe_empleado_jornada_diari.wsdl")
public class ZWSPEEMPLEADOJornadaDiari_Service
    extends Service
{

    private final static URL ZWSPEEMPLEADOjornadadiari_WSDL_LOCATIONURL;
    private final static WebServiceException ZWSPEEMPLEADOjornadadiari_EXCEPTION;
    private final static QName ZWSPEEMPLEADOjornadadiari_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_EMPLEADO_JORNADA_DIARI");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("classpath:META-INF/wsdl/z_ws_pe_empleado_jornada_diari.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ZWSPEEMPLEADOjornadadiari_WSDL_LOCATIONURL = url;
        ZWSPEEMPLEADOjornadadiari_EXCEPTION = e;
    }

    public ZWSPEEMPLEADOJornadaDiari_Service() {
        super(__getWsdlLocation(), ZWSPEEMPLEADOjornadadiari_QNAME);
    }

    public ZWSPEEMPLEADOJornadaDiari_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ZWSPEEMPLEADOjornadadiari_QNAME, features);
    }

    public ZWSPEEMPLEADOJornadaDiari_Service(URL wsdlLocation) {
        super(wsdlLocation, ZWSPEEMPLEADOjornadadiari_QNAME);
    }

    public ZWSPEEMPLEADOJornadaDiari_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ZWSPEEMPLEADOjornadadiari_QNAME, features);
    }

    public ZWSPEEMPLEADOJornadaDiari_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZWSPEEMPLEADOJornadaDiari_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ZWSPEEMPLEADOJornadaDiari
     */
    @WebEndpoint(name = "Z_WS_PE_EMPLEADO_JORNADA_DIARI")
    public ZWSPEEMPLEADOJornadaDiari getZWSPEEMPLEADOJornadaDiari() {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_EMPLEADO_jornada_diari"), ZWSPEEMPLEADOJornadaDiari.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSPEEMPLEADOJornadaDiari
     */
    @WebEndpoint(name = "Z_WS_PE_EMPLEADO_jornada_diari")
    public ZWSPEEMPLEADOJornadaDiari getZWSPEEMPLEADOJornadaDiari(WebServiceFeature... features) {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_EMPLEADO_jornada_diari"), ZWSPEEMPLEADOJornadaDiari.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ZWSPEEMPLEADOjornadadiari_EXCEPTION!= null) {
            throw ZWSPEEMPLEADOjornadadiari_EXCEPTION;
        }
        return ZWSPEEMPLEADOjornadadiari_WSDL_LOCATIONURL;
    }

}
