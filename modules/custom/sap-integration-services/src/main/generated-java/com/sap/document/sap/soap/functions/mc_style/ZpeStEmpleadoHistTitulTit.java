
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoHistTitulTit complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoHistTitulTit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Ausbi" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Atext" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoHistTitulTit", propOrder = {
    "ausbi",
    "atext"
})
public class ZpeStEmpleadoHistTitulTit {

    @XmlElement(name = "Ausbi", required = true)
    protected String ausbi;
    @XmlElement(name = "Atext", required = true)
    protected String atext;

    /**
     * Obtiene el valor de la propiedad ausbi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAusbi() {
        return ausbi;
    }

    /**
     * Define el valor de la propiedad ausbi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAusbi(String value) {
        this.ausbi = value;
    }

    /**
     * Obtiene el valor de la propiedad atext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtext() {
        return atext;
    }

    /**
     * Define el valor de la propiedad atext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtext(String value) {
        this.atext = value;
    }

}
