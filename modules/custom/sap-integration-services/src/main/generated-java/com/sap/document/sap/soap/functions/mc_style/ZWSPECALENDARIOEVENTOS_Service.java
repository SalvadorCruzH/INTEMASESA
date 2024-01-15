
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
@WebServiceClient(name = "Z_WS_PE_CALENDARIO_EVENTOS", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", wsdlLocation = "file:/Users/vmosuna/Proyectos/Emasesa/git/INTEMASESA/modules/custom/sap-integration-services/src/main/resources/META-INF/wsdl/z_ws_pe_calendario_eventos.wsdl")
public class ZWSPECALENDARIOEVENTOS_Service
    extends Service
{

    private final static URL ZWSPECALENDARIOEVENTOS_WSDL_LOCATION;
    private final static WebServiceException ZWSPECALENDARIOEVENTOS_EXCEPTION;
    private final static QName ZWSPECALENDARIOEVENTOS_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_CALENDARIO_EVENTOS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/vmosuna/Proyectos/Emasesa/git/INTEMASESA/modules/custom/sap-integration-services/src/main/resources/META-INF/wsdl/z_ws_pe_calendario_eventos.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ZWSPECALENDARIOEVENTOS_WSDL_LOCATION = url;
        ZWSPECALENDARIOEVENTOS_EXCEPTION = e;
    }

    public ZWSPECALENDARIOEVENTOS_Service() {
        super(__getWsdlLocation(), ZWSPECALENDARIOEVENTOS_QNAME);
    }

    public ZWSPECALENDARIOEVENTOS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ZWSPECALENDARIOEVENTOS_QNAME, features);
    }

    public ZWSPECALENDARIOEVENTOS_Service(URL wsdlLocation) {
        super(wsdlLocation, ZWSPECALENDARIOEVENTOS_QNAME);
    }

    public ZWSPECALENDARIOEVENTOS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ZWSPECALENDARIOEVENTOS_QNAME, features);
    }

    public ZWSPECALENDARIOEVENTOS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZWSPECALENDARIOEVENTOS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ZWSPECALENDARIOEVENTOS
     */
    @WebEndpoint(name = "Z_WS_PE_CALENDARIO_EVENTOS")
    public ZWSPECALENDARIOEVENTOS getZWSPECALENDARIOEVENTOS() {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_CALENDARIO_EVENTOS"), ZWSPECALENDARIOEVENTOS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSPECALENDARIOEVENTOS
     */
    @WebEndpoint(name = "Z_WS_PE_CALENDARIO_EVENTOS")
    public ZWSPECALENDARIOEVENTOS getZWSPECALENDARIOEVENTOS(WebServiceFeature... features) {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_CALENDARIO_EVENTOS"), ZWSPECALENDARIOEVENTOS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ZWSPECALENDARIOEVENTOS_EXCEPTION!= null) {
            throw ZWSPECALENDARIOEVENTOS_EXCEPTION;
        }
        return ZWSPECALENDARIOEVENTOS_WSDL_LOCATION;
    }

}
