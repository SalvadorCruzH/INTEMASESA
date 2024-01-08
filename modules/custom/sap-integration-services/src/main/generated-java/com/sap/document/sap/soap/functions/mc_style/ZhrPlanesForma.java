
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZhrPlanesForma complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZhrPlanesForma"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/&gt;
 *         &lt;element name="Zzplanform" type="{urn:sap-com:document:sap:rfc:functions}char9"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZhrPlanesForma", propOrder = {
    "mandt",
    "zzplanform"
})
public class ZhrPlanesForma {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "Zzplanform", required = true)
    protected String zzplanform;

    /**
     * Obtiene el valor de la propiedad mandt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandt() {
        return mandt;
    }

    /**
     * Define el valor de la propiedad mandt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandt(String value) {
        this.mandt = value;
    }

    /**
     * Obtiene el valor de la propiedad zzplanform.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzplanform() {
        return zzplanform;
    }

    /**
     * Define el valor de la propiedad zzplanform.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzplanform(String value) {
        this.zzplanform = value;
    }

}
