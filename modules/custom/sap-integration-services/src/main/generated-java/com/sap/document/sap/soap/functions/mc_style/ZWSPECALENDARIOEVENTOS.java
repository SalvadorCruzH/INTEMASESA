
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
@WebService(name = "Z_WS_PE_CALENDARIO_EVENTOS", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSPECALENDARIOEVENTOS {


    /**
     * 
     * @param tContenido
     * @param fechaHasta
     * @param fechaDesde
     * @param idEmpleado
     * @param inscripcionEvento
     * @param inscripcionAccion
     * @param tObjetivo
     * @param tEventos
     * @return
     *     returns com.sap.document.sap.soap.functions.mc_style.Bapireturn1
     */
    @WebMethod(operationName = "ZPeCalendarioEventos", action = "urn:sap-com:document:sap:soap:functions:mc-style:Z_WS_PE_CALENDARIO_EVENTOS:ZPeCalendarioEventosRequest")
    @WebResult(name = "Return", targetNamespace = "")
    @RequestWrapper(localName = "ZPeCalendarioEventos", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeCalendarioEventos")
    @ResponseWrapper(localName = "ZPeCalendarioEventosResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeCalendarioEventosResponse")
    public Bapireturn1 zPeCalendarioEventos(
        @WebParam(name = "FechaDesde", targetNamespace = "")
        String fechaDesde,
        @WebParam(name = "FechaHasta", targetNamespace = "")
        String fechaHasta,
        @WebParam(name = "IdEmpleado", targetNamespace = "")
        String idEmpleado,
        @WebParam(name = "InscripcionAccion", targetNamespace = "")
        String inscripcionAccion,
        @WebParam(name = "InscripcionEvento", targetNamespace = "")
        String inscripcionEvento,
        @WebParam(name = "TContenido", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<TableOfZpeStConteniObjetiEventos> tContenido,
        @WebParam(name = "TEventos", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<TableOfZpeStCalendarioEventos> tEventos,
        @WebParam(name = "TObjetivo", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<TableOfZpeStConteniObjetiEventos> tObjetivo);

}
