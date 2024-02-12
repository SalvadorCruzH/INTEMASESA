
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Holder;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.1
 * Generated source version: 3.0
 * 
 */
@WebService(name = "Z_WS_PE_EMPLEADO_DIPLOMA_EVENT", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSPEEMPLEADODIPLOMAEVENT {


    /**
     * 
     * @param exResult
     * @param imPernr
     * @param imIdCurso
     * @param exData
     * @throws ZPeEmpleadoDiplomaEventoException
     */
    @WebMethod(operationName = "ZPeEmpleadoDiplomaEvento", action = "urn:sap-com:document:sap:soap:functions:mc-style:Z_WS_PE_EMPLEADO_DIPLOMA_EVENT:ZPeEmpleadoDiplomaEventoRequest")
    @RequestWrapper(localName = "ZPeEmpleadoDiplomaEvento", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeEmpleadoDiplomaEvento")
    @ResponseWrapper(localName = "ZPeEmpleadoDiplomaEventoResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeEmpleadoDiplomaEventoResponse")
    public void zPeEmpleadoDiplomaEvento(
        @WebParam(name = "ImIdCurso", targetNamespace = "")
        String imIdCurso,
        @WebParam(name = "ImPernr", targetNamespace = "")
        String imPernr,
        @WebParam(name = "ExData", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<byte[]> exData,
        @WebParam(name = "ExResult", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> exResult)
        throws ZPeEmpleadoDiplomaEventoException
    ;

}
