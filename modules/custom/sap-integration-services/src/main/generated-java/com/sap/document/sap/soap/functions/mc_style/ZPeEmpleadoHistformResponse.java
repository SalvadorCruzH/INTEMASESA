
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
 *         &lt;element name="TExterna" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistform"/&gt;
 *         &lt;element name="TImpartida" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistform"/&gt;
 *         &lt;element name="TInterna" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistform"/&gt;
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
    "tExterna",
    "tImpartida",
    "tInterna"
})
@XmlRootElement(name = "ZPeEmpleadoHistformResponse")
public class ZPeEmpleadoHistformResponse {

    @XmlElement(name = "TExterna", required = true)
    protected TableOfZpeStEmpleadoHistform tExterna;
    @XmlElement(name = "TImpartida", required = true)
    protected TableOfZpeStEmpleadoHistform tImpartida;
    @XmlElement(name = "TInterna", required = true)
    protected TableOfZpeStEmpleadoHistform tInterna;

    /**
     * Obtiene el valor de la propiedad tExterna.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistform }
     *     
     */
    public TableOfZpeStEmpleadoHistform getTExterna() {
        return tExterna;
    }

    /**
     * Define el valor de la propiedad tExterna.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistform }
     *     
     */
    public void setTExterna(TableOfZpeStEmpleadoHistform value) {
        this.tExterna = value;
    }

    /**
     * Obtiene el valor de la propiedad tImpartida.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistform }
     *     
     */
    public TableOfZpeStEmpleadoHistform getTImpartida() {
        return tImpartida;
    }

    /**
     * Define el valor de la propiedad tImpartida.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistform }
     *     
     */
    public void setTImpartida(TableOfZpeStEmpleadoHistform value) {
        this.tImpartida = value;
    }

    /**
     * Obtiene el valor de la propiedad tInterna.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistform }
     *     
     */
    public TableOfZpeStEmpleadoHistform getTInterna() {
        return tInterna;
    }

    /**
     * Define el valor de la propiedad tInterna.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistform }
     *     
     */
    public void setTInterna(TableOfZpeStEmpleadoHistform value) {
        this.tInterna = value;
    }

}
