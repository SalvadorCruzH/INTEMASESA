
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
@WebService(name = "Z_WS_PE_EMPLEADO_DATOS_PERSONA", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSPEEMPLEADODATOSPERSONA {


    /**
     * 
     * @param pernr
     * @param usrid
     * @return
     *     returns com.sap.document.sap.soap.functions.mc_style.TableOfZpeStEmpleadoDatosPersonal
     */
    @WebMethod(operationName = "ZPeEmpleadoDatosPersonales", action = "urn:sap-com:document:sap:soap:functions:mc-style:Z_WS_PE_EMPLEADO_DATOS_PERSONA:ZPeEmpleadoDatosPersonalesRequest")
    @WebResult(name = "TEmpleados", targetNamespace = "")
    @RequestWrapper(localName = "ZPeEmpleadoDatosPersonales", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeEmpleadoDatosPersonales")
    @ResponseWrapper(localName = "ZPeEmpleadoDatosPersonalesResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeEmpleadoDatosPersonalesResponse")
    public TableOfZpeStEmpleadoDatosPersonal zPeEmpleadoDatosPersonales(
        @WebParam(name = "Pernr", targetNamespace = "")
        String pernr,
        @WebParam(name = "Usrid", targetNamespace = "")
        String usrid);

}
