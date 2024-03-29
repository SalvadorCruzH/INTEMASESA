
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
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
@WebService(name = "Z_WS_PE_DETECNECFORM", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSPEDETECNECFORM {


    /**
     * 
     * @param tiposEventoPlanformacion
     * @param detecnecformConsulta
     * @param planFormacion
     * @param solicitudesCons
     * @param planesFormacion
     * @param detecnecformInsert
     * @return
     *     returns com.sap.document.sap.soap.functions.mc_style.Bapireturn1
     */
    @WebMethod(operationName = "ZPeDetecnecform", action = "urn:sap-com:document:sap:soap:functions:mc-style:Z_WS_PE_DETECNECFORM:ZPeDetecnecformRequest")
    @WebResult(name = "Return", targetNamespace = "")
    @RequestWrapper(localName = "ZPeDetecnecform", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeDetecnecform")
    @ResponseWrapper(localName = "ZPeDetecnecformResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeDetecnecformResponse")
    public Bapireturn1 zPeDetecnecform(
        @WebParam(name = "DetecnecformConsulta", targetNamespace = "")
        ZpeStDetecnecformConsulta detecnecformConsulta,
        @WebParam(name = "DetecnecformInsert", targetNamespace = "")
        ZhrEDetnecform detecnecformInsert,
        @WebParam(name = "PlanFormacion", targetNamespace = "")
        String planFormacion,
        @WebParam(name = "PlanesFormacion", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<TableOfZhrPlanesForma> planesFormacion,
        @WebParam(name = "SolicitudesCons", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<TableOfZhrEDetnecform> solicitudesCons,
        @WebParam(name = "TiposEventoPlanformacion", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<TableOfZpeStDetecnecformTipos> tiposEventoPlanformacion);

}
