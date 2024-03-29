
package com.sap.document.sap.soap.functions.mc_style;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.1
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "Z_WS_PE_EMPLEADO_HISTFORM", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", wsdlLocation = "classpath:META-INF/wsdl/z_ws_pe_empleado_histform.wsdl")
public class ZWSPEEMPLEADOHISTFORM_Service
    extends Service
{

    private final static URL ZWSPEEMPLEADOHISTFORM_WSDL_LOCATION;
    private final static WebServiceException ZWSPEEMPLEADOHISTFORM_EXCEPTION;
    private final static QName ZWSPEEMPLEADOHISTFORM_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_EMPLEADO_HISTFORM");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("classpath:META-INF/wsdl/z_ws_pe_empleado_histform.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ZWSPEEMPLEADOHISTFORM_WSDL_LOCATION = url;
        ZWSPEEMPLEADOHISTFORM_EXCEPTION = e;
    }

    public ZWSPEEMPLEADOHISTFORM_Service() {
        super(__getWsdlLocation(), ZWSPEEMPLEADOHISTFORM_QNAME);
    }

    public ZWSPEEMPLEADOHISTFORM_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ZWSPEEMPLEADOHISTFORM_QNAME, features);
    }

    public ZWSPEEMPLEADOHISTFORM_Service(URL wsdlLocation) {
        super(wsdlLocation, ZWSPEEMPLEADOHISTFORM_QNAME);
    }

    public ZWSPEEMPLEADOHISTFORM_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ZWSPEEMPLEADOHISTFORM_QNAME, features);
    }

    public ZWSPEEMPLEADOHISTFORM_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZWSPEEMPLEADOHISTFORM_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ZWSPEEMPLEADOHISTFORM
     */
    @WebEndpoint(name = "Z_WS_PE_EMPLEADO_HISTFORM")
    public ZWSPEEMPLEADOHISTFORM getZWSPEEMPLEADOHISTFORM() {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_EMPLEADO_HISTFORM"), ZWSPEEMPLEADOHISTFORM.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSPEEMPLEADOHISTFORM
     */
    @WebEndpoint(name = "Z_WS_PE_EMPLEADO_HISTFORM")
    public ZWSPEEMPLEADOHISTFORM getZWSPEEMPLEADOHISTFORM(WebServiceFeature... features) {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_EMPLEADO_HISTFORM"), ZWSPEEMPLEADOHISTFORM.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ZWSPEEMPLEADOHISTFORM_EXCEPTION!= null) {
            throw ZWSPEEMPLEADOHISTFORM_EXCEPTION;
        }
        return ZWSPEEMPLEADOHISTFORM_WSDL_LOCATION;
    }

}
