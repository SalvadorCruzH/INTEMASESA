
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
 *         &lt;element name="ExData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="ExReturn" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="TExterna" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistFormacion"/&gt;
 *         &lt;element name="TImpartida" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistFormacion"/&gt;
 *         &lt;element name="TInterna" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistFormacion"/&gt;
 *         &lt;element name="TPaises" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistFormPais"/&gt;
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
    "exData",
    "exReturn",
    "tExterna",
    "tImpartida",
    "tInterna",
    "tPaises"
})
@XmlRootElement(name = "ZPeEmpleadoHistFormacionResponse")
public class ZPeEmpleadoHistFormacionResponse {

    @XmlElement(name = "ExData", required = true)
    protected byte[] exData;
    @XmlElement(name = "ExReturn")
    protected int exReturn;
    @XmlElement(name = "TExterna", required = true)
    protected TableOfZpeStEmpleadoHistFormacion tExterna;
    @XmlElement(name = "TImpartida", required = true)
    protected TableOfZpeStEmpleadoHistFormacion tImpartida;
    @XmlElement(name = "TInterna", required = true)
    protected TableOfZpeStEmpleadoHistFormacion tInterna;
    @XmlElement(name = "TPaises", required = true)
    protected TableOfZpeStEmpleadoHistFormPais tPaises;

    /**
     * Obtiene el valor de la propiedad exData.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getExData() {
        return exData;
    }

    /**
     * Define el valor de la propiedad exData.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setExData(byte[] value) {
        this.exData = value;
    }

    /**
     * Obtiene el valor de la propiedad exReturn.
     * 
     */
    public int getExReturn() {
        return exReturn;
    }

    /**
     * Define el valor de la propiedad exReturn.
     * 
     */
    public void setExReturn(int value) {
        this.exReturn = value;
    }

    /**
     * Obtiene el valor de la propiedad tExterna.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistFormacion }
     *     
     */
    public TableOfZpeStEmpleadoHistFormacion getTExterna() {
        return tExterna;
    }

    /**
     * Define el valor de la propiedad tExterna.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistFormacion }
     *     
     */
    public void setTExterna(TableOfZpeStEmpleadoHistFormacion value) {
        this.tExterna = value;
    }

    /**
     * Obtiene el valor de la propiedad tImpartida.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistFormacion }
     *     
     */
    public TableOfZpeStEmpleadoHistFormacion getTImpartida() {
        return tImpartida;
    }

    /**
     * Define el valor de la propiedad tImpartida.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistFormacion }
     *     
     */
    public void setTImpartida(TableOfZpeStEmpleadoHistFormacion value) {
        this.tImpartida = value;
    }

    /**
     * Obtiene el valor de la propiedad tInterna.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistFormacion }
     *     
     */
    public TableOfZpeStEmpleadoHistFormacion getTInterna() {
        return tInterna;
    }

    /**
     * Define el valor de la propiedad tInterna.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistFormacion }
     *     
     */
    public void setTInterna(TableOfZpeStEmpleadoHistFormacion value) {
        this.tInterna = value;
    }

    /**
     * Obtiene el valor de la propiedad tPaises.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistFormPais }
     *     
     */
    public TableOfZpeStEmpleadoHistFormPais getTPaises() {
        return tPaises;
    }

    /**
     * Define el valor de la propiedad tPaises.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistFormPais }
     *     
     */
    public void setTPaises(TableOfZpeStEmpleadoHistFormPais value) {
        this.tPaises = value;
    }

}
