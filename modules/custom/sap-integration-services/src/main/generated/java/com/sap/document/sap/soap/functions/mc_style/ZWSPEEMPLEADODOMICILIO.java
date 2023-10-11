
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.1
 * Generated source version: 3.0
 * 
 */
@WebService(name = "Z_WS_PE_EMPLEADO_DOMICILIO", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSPEEMPLEADODOMICILIO {


    /**
     * 
     * @param pernr
     * @return
     *     returns com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoDomicilio
     */
    @WebMethod(operationName = "ZPeEmpleadoDomicilio", action = "urn:sap-com:document:sap:soap:functions:mc-style:Z_WS_PE_EMPLEADO_DOMICILIO:ZPeEmpleadoDomicilioRequest")
    @WebResult(name = "TEmpleados", targetNamespace = "")
    @RequestWrapper(localName = "ZPeEmpleadoDomicilio", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeEmpleadoDomicilio")
    @ResponseWrapper(localName = "ZPeEmpleadoDomicilioResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeEmpleadoDomicilioResponse")
    public TableOfZpeStEmpleadoDomicilio zPeEmpleadoDomicilio(
        @WebParam(name = "Pernr", targetNamespace = "")
        String pernr);

}
