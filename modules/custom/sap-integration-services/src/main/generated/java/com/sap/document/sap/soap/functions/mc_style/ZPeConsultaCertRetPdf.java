
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ImPernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="ImVariante" type="{urn:sap-com:document:sap:rfc:functions}char14" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "imPernr",
    "imVariante"
})
@XmlRootElement(name = "ZPeConsultaCertRetPdf")
public class ZPeConsultaCertRetPdf {

    @XmlElement(name = "ImPernr", required = true)
    protected String imPernr;
    @XmlElement(name = "ImVariante")
    protected String imVariante;

    /**
     * Obtiene el valor de la propiedad imPernr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImPernr() {
        return imPernr;
    }

    /**
     * Define el valor de la propiedad imPernr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImPernr(String value) {
        this.imPernr = value;
    }

    /**
     * Obtiene el valor de la propiedad imVariante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImVariante() {
        return imVariante;
    }

    /**
     * Define el valor de la propiedad imVariante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImVariante(String value) {
        this.imVariante = value;
    }

}
