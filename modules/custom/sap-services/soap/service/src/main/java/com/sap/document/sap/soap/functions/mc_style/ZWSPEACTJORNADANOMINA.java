
package com.sap.document.sap.soap.functions.mc_style;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Z_WS_PE_ACT_JORNADA_NOMINA", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSPEACTJORNADANOMINA {


    /**
     * 
     * @param tEmpleados
     * @return
     *     returns com.sap.document.sap.soap.functions.mc_style.Bapireturn1
     */
    @WebMethod(operationName = "ZPeActJornadaNomina", action = "urn:sap-com:document:sap:soap:functions:mc-style:Z_WS_PE_ACT_JORNADA_NOMINA:ZPeActJornadaNominaRequest")
    @WebResult(name = "Return", targetNamespace = "")
    @RequestWrapper(localName = "ZPeActJornadaNomina", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeActJornadaNomina")
    @ResponseWrapper(localName = "ZPeActJornadaNominaResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeActJornadaNominaResponse")
    public Bapireturn1 zPeActJornadaNomina(
        @WebParam(name = "TEmpleados", targetNamespace = "")
        ZpeStActJornadaNomina tEmpleados);

}
