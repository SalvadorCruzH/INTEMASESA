
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoHistInnovacio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoHistInnovacio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Begda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Endda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Stext" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Adatanr" type="{urn:sap-com:document:sap:rfc:functions}char32"/&gt;
 *         &lt;element name="Chara" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/&gt;
 *         &lt;element name="Pstext" type="{urn:sap-com:document:sap:soap:functions:mc-style}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoHistInnovacio", propOrder = {
    "begda",
    "endda",
    "stext",
    "adatanr",
    "chara",
    "pstext"
})
public class ZpeStEmpleadoHistInnovacio {

    @XmlElement(name = "Begda", required = true)
    protected String begda;
    @XmlElement(name = "Endda", required = true)
    protected String endda;
    @XmlElement(name = "Stext", required = true)
    protected String stext;
    @XmlElement(name = "Adatanr", required = true)
    protected String adatanr;
    @XmlElement(name = "Chara", required = true)
    protected String chara;
    @XmlElement(name = "Pstext", required = true)
    protected String pstext;

    /**
     * Obtiene el valor de la propiedad begda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBegda() {
        return begda;
    }

    /**
     * Define el valor de la propiedad begda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBegda(String value) {
        this.begda = value;
    }

    /**
     * Obtiene el valor de la propiedad endda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndda() {
        return endda;
    }

    /**
     * Define el valor de la propiedad endda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndda(String value) {
        this.endda = value;
    }

    /**
     * Obtiene el valor de la propiedad stext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStext() {
        return stext;
    }

    /**
     * Define el valor de la propiedad stext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStext(String value) {
        this.stext = value;
    }

    /**
     * Obtiene el valor de la propiedad adatanr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdatanr() {
        return adatanr;
    }

    /**
     * Define el valor de la propiedad adatanr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdatanr(String value) {
        this.adatanr = value;
    }

    /**
     * Obtiene el valor de la propiedad chara.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChara() {
        return chara;
    }

    /**
     * Define el valor de la propiedad chara.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChara(String value) {
        this.chara = value;
    }

    /**
     * Obtiene el valor de la propiedad pstext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPstext() {
        return pstext;
    }

    /**
     * Define el valor de la propiedad pstext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPstext(String value) {
        this.pstext = value;
    }

}
