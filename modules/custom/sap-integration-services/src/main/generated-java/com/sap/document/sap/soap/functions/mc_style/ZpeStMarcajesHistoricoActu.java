
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStMarcajesHistoricoActu complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStMarcajesHistoricoActu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Fecha" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Ent1" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="Sal1" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="Ent2" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="Sal2" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="Ent3" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="Sal3" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="Ent4" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="Sal4" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStMarcajesHistoricoActu", propOrder = {
    "pernr",
    "fecha",
    "ent1",
    "sal1",
    "ent2",
    "sal2",
    "ent3",
    "sal3",
    "ent4",
    "sal4"
})
public class ZpeStMarcajesHistoricoActu {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "Fecha", required = true)
    protected String fecha;
    @XmlElement(name = "Ent1", required = true)
    protected String ent1;
    @XmlElement(name = "Sal1", required = true)
    protected String sal1;
    @XmlElement(name = "Ent2", required = true)
    protected String ent2;
    @XmlElement(name = "Sal2", required = true)
    protected String sal2;
    @XmlElement(name = "Ent3", required = true)
    protected String ent3;
    @XmlElement(name = "Sal3", required = true)
    protected String sal3;
    @XmlElement(name = "Ent4", required = true)
    protected String ent4;
    @XmlElement(name = "Sal4", required = true)
    protected String sal4;

    /**
     * Obtiene el valor de la propiedad pernr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPernr() {
        return pernr;
    }

    /**
     * Define el valor de la propiedad pernr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPernr(String value) {
        this.pernr = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad ent1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnt1() {
        return ent1;
    }

    /**
     * Define el valor de la propiedad ent1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnt1(String value) {
        this.ent1 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSal1() {
        return sal1;
    }

    /**
     * Define el valor de la propiedad sal1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSal1(String value) {
        this.sal1 = value;
    }

    /**
     * Obtiene el valor de la propiedad ent2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnt2() {
        return ent2;
    }

    /**
     * Define el valor de la propiedad ent2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnt2(String value) {
        this.ent2 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSal2() {
        return sal2;
    }

    /**
     * Define el valor de la propiedad sal2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSal2(String value) {
        this.sal2 = value;
    }

    /**
     * Obtiene el valor de la propiedad ent3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnt3() {
        return ent3;
    }

    /**
     * Define el valor de la propiedad ent3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnt3(String value) {
        this.ent3 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSal3() {
        return sal3;
    }

    /**
     * Define el valor de la propiedad sal3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSal3(String value) {
        this.sal3 = value;
    }

    /**
     * Obtiene el valor de la propiedad ent4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnt4() {
        return ent4;
    }

    /**
     * Define el valor de la propiedad ent4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnt4(String value) {
        this.ent4 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSal4() {
        return sal4;
    }

    /**
     * Define el valor de la propiedad sal4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSal4(String value) {
        this.sal4 = value;
    }

}
