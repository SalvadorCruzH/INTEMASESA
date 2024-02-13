
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoHistTitulacio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoHistTitulacio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Begda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Endda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Slart" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Sltxt" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
 *         &lt;element name="Ausbi" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Atext" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Sltp1" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/&gt;
 *         &lt;element name="Frtxt" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Insti" type="{urn:sap-com:document:sap:rfc:functions}char80"/&gt;
 *         &lt;element name="Slabs" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Satxt" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Zzverifi" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoHistTitulacio", propOrder = {
    "begda",
    "endda",
    "slart",
    "sltxt",
    "ausbi",
    "atext",
    "sltp1",
    "frtxt",
    "insti",
    "slabs",
    "satxt",
    "zzverifi"
})
public class ZpeStEmpleadoHistTitulacio {

    @XmlElement(name = "Begda", required = true)
    protected String begda;
    @XmlElement(name = "Endda", required = true)
    protected String endda;
    @XmlElement(name = "Slart", required = true)
    protected String slart;
    @XmlElement(name = "Sltxt", required = true)
    protected String sltxt;
    @XmlElement(name = "Ausbi", required = true)
    protected String ausbi;
    @XmlElement(name = "Atext", required = true)
    protected String atext;
    @XmlElement(name = "Sltp1", required = true)
    protected String sltp1;
    @XmlElement(name = "Frtxt", required = true)
    protected String frtxt;
    @XmlElement(name = "Insti", required = true)
    protected String insti;
    @XmlElement(name = "Slabs", required = true)
    protected String slabs;
    @XmlElement(name = "Satxt", required = true)
    protected String satxt;
    @XmlElement(name = "Zzverifi", required = true)
    protected String zzverifi;

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
     * Obtiene el valor de la propiedad slart.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlart() {
        return slart;
    }

    /**
     * Define el valor de la propiedad slart.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlart(String value) {
        this.slart = value;
    }

    /**
     * Obtiene el valor de la propiedad sltxt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSltxt() {
        return sltxt;
    }

    /**
     * Define el valor de la propiedad sltxt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSltxt(String value) {
        this.sltxt = value;
    }

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

    /**
     * Obtiene el valor de la propiedad sltp1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSltp1() {
        return sltp1;
    }

    /**
     * Define el valor de la propiedad sltp1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSltp1(String value) {
        this.sltp1 = value;
    }

    /**
     * Obtiene el valor de la propiedad frtxt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrtxt() {
        return frtxt;
    }

    /**
     * Define el valor de la propiedad frtxt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrtxt(String value) {
        this.frtxt = value;
    }

    /**
     * Obtiene el valor de la propiedad insti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsti() {
        return insti;
    }

    /**
     * Define el valor de la propiedad insti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsti(String value) {
        this.insti = value;
    }

    /**
     * Obtiene el valor de la propiedad slabs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlabs() {
        return slabs;
    }

    /**
     * Define el valor de la propiedad slabs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlabs(String value) {
        this.slabs = value;
    }

    /**
     * Obtiene el valor de la propiedad satxt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSatxt() {
        return satxt;
    }

    /**
     * Define el valor de la propiedad satxt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSatxt(String value) {
        this.satxt = value;
    }

    /**
     * Obtiene el valor de la propiedad zzverifi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzverifi() {
        return zzverifi;
    }

    /**
     * Define el valor de la propiedad zzverifi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzverifi(String value) {
        this.zzverifi = value;
    }

}
