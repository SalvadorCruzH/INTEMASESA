
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
 *         &lt;element name="ImFin" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="ImInicio" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="ImPernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
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
    "imFin",
    "imInicio",
    "imPernr"
})
@XmlRootElement(name = "ZPeEmpleadoJornadaDiaria")
public class ZPeEmpleadoJornadaDiaria {

    @XmlElement(name = "ImFin", required = true)
    protected String imFin;
    @XmlElement(name = "ImInicio", required = true)
    protected String imInicio;
    @XmlElement(name = "ImPernr", required = true)
    protected String imPernr;

    /**
     * Obtiene el valor de la propiedad imFin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImFin() {
        return imFin;
    }

    /**
     * Define el valor de la propiedad imFin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImFin(String value) {
        this.imFin = value;
    }

    /**
     * Obtiene el valor de la propiedad imInicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImInicio() {
        return imInicio;
    }

    /**
     * Define el valor de la propiedad imInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImInicio(String value) {
        this.imInicio = value;
    }

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

}
