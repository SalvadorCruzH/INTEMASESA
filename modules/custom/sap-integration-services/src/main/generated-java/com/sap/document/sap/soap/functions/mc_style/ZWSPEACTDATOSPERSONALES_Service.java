
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
@WebServiceClient(name = "Z_WS_PE_ACT_DATOS_PERSONALES", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", wsdlLocation = "file:/Users/stejeros/Developer/source/emasesa/INTEMASESA/modules/custom/sap-integration-services/src/main/resources/META-INF/wsdl/z_ws_pe_act_datos_personales.wsdl")
public class ZWSPEACTDATOSPERSONALES_Service
    extends Service
{

    private final static URL ZWSPEACTDATOSPERSONALES_WSDL_LOCATION;
    private final static WebServiceException ZWSPEACTDATOSPERSONALES_EXCEPTION;
    private final static QName ZWSPEACTDATOSPERSONALES_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_ACT_DATOS_PERSONALES");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/stejeros/Developer/source/emasesa/INTEMASESA/modules/custom/sap-integration-services/src/main/resources/META-INF/wsdl/z_ws_pe_act_datos_personales.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ZWSPEACTDATOSPERSONALES_WSDL_LOCATION = url;
        ZWSPEACTDATOSPERSONALES_EXCEPTION = e;
    }

    public ZWSPEACTDATOSPERSONALES_Service() {
        super(__getWsdlLocation(), ZWSPEACTDATOSPERSONALES_QNAME);
    }

    public ZWSPEACTDATOSPERSONALES_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ZWSPEACTDATOSPERSONALES_QNAME, features);
    }

    public ZWSPEACTDATOSPERSONALES_Service(URL wsdlLocation) {
        super(wsdlLocation, ZWSPEACTDATOSPERSONALES_QNAME);
    }

    public ZWSPEACTDATOSPERSONALES_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ZWSPEACTDATOSPERSONALES_QNAME, features);
    }

    public ZWSPEACTDATOSPERSONALES_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZWSPEACTDATOSPERSONALES_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ZWSPEACTDATOSPERSONALES
     */
    @WebEndpoint(name = "Z_WS_PE_ACT_DATOS_PERSONALES")
    public ZWSPEACTDATOSPERSONALES getZWSPEACTDATOSPERSONALES() {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_ACT_DATOS_PERSONALES"), ZWSPEACTDATOSPERSONALES.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZWSPEACTDATOSPERSONALES
     */
    @WebEndpoint(name = "Z_WS_PE_ACT_DATOS_PERSONALES")
    public ZWSPEACTDATOSPERSONALES getZWSPEACTDATOSPERSONALES(WebServiceFeature... features) {
        return super.getPort(new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Z_WS_PE_ACT_DATOS_PERSONALES"), ZWSPEACTDATOSPERSONALES.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ZWSPEACTDATOSPERSONALES_EXCEPTION!= null) {
            throw ZWSPEACTDATOSPERSONALES_EXCEPTION;
        }
        return ZWSPEACTDATOSPERSONALES_WSDL_LOCATION;
    }

}
