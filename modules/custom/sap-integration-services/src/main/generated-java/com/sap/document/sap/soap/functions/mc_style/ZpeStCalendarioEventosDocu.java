
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStCalendarioEventosDocu complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStCalendarioEventosDocu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EventoId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="DocumentoNombre" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/&gt;
 *         &lt;element name="DocumentoUrl" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStCalendarioEventosDocu", propOrder = {
    "eventoId",
    "documentoNombre",
    "documentoUrl"
})
public class ZpeStCalendarioEventosDocu {

    @XmlElement(name = "EventoId", required = true)
    protected String eventoId;
    @XmlElement(name = "DocumentoNombre", required = true)
    protected String documentoNombre;
    @XmlElement(name = "DocumentoUrl", required = true)
    protected String documentoUrl;

    /**
     * Obtiene el valor de la propiedad eventoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventoId() {
        return eventoId;
    }

    /**
     * Define el valor de la propiedad eventoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventoId(String value) {
        this.eventoId = value;
    }

    /**
     * Obtiene el valor de la propiedad documentoNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoNombre() {
        return documentoNombre;
    }

    /**
     * Define el valor de la propiedad documentoNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoNombre(String value) {
        this.documentoNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad documentoUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoUrl() {
        return documentoUrl;
    }

    /**
     * Define el valor de la propiedad documentoUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoUrl(String value) {
        this.documentoUrl = value;
    }

}
