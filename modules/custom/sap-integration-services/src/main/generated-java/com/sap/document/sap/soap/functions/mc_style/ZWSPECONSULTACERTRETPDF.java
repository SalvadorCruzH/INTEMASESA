
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
@WebService(name = "Z_WS_PE_CONSULTA_CERT_RET_PDF", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSPECONSULTACERTRETPDF {


    /**
     * 
     * @param exResult
     * @param imPernr
     * @param exData
     * @param imEjercicio
     */
    @WebMethod(operationName = "ZPeConsultaCertRetPdf", action = "urn:sap-com:document:sap:soap:functions:mc-style:Z_WS_PE_CONSULTA_CERT_RET_PDF:ZPeConsultaCertRetPdfRequest")
    @RequestWrapper(localName = "ZPeConsultaCertRetPdf", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeConsultaCertRetPdf")
    @ResponseWrapper(localName = "ZPeConsultaCertRetPdfResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.sap.document.sap.soap.functions.mc_style.ZPeConsultaCertRetPdfResponse")
    public void zPeConsultaCertRetPdf(
        @WebParam(name = "ImEjercicio", targetNamespace = "")
        String imEjercicio,
        @WebParam(name = "ImPernr", targetNamespace = "")
        String imPernr,
        @WebParam(name = "ExData", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<byte[]> exData,
        @WebParam(name = "ExResult", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Integer> exResult);

}
