
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
 *         &lt;element name="ConsejeroId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="DireccionRrhhRespId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="DivisionRrhhRespId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="SubdireccionRrhhRespId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
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
    "consejeroId",
    "direccionRrhhRespId",
    "divisionRrhhRespId",
    "subdireccionRrhhRespId"
})
@XmlRootElement(name = "ZPeCiertosDatosEstructuraResponse")
public class ZPeCiertosDatosEstructuraResponse {

    @XmlElement(name = "ConsejeroId", required = true)
    protected String consejeroId;
    @XmlElement(name = "DireccionRrhhRespId", required = true)
    protected String direccionRrhhRespId;
    @XmlElement(name = "DivisionRrhhRespId", required = true)
    protected String divisionRrhhRespId;
    @XmlElement(name = "SubdireccionRrhhRespId", required = true)
    protected String subdireccionRrhhRespId;

    /**
     * Obtiene el valor de la propiedad consejeroId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsejeroId() {
        return consejeroId;
    }

    /**
     * Define el valor de la propiedad consejeroId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsejeroId(String value) {
        this.consejeroId = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionRrhhRespId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionRrhhRespId() {
        return direccionRrhhRespId;
    }

    /**
     * Define el valor de la propiedad direccionRrhhRespId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionRrhhRespId(String value) {
        this.direccionRrhhRespId = value;
    }

    /**
     * Obtiene el valor de la propiedad divisionRrhhRespId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivisionRrhhRespId() {
        return divisionRrhhRespId;
    }

    /**
     * Define el valor de la propiedad divisionRrhhRespId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivisionRrhhRespId(String value) {
        this.divisionRrhhRespId = value;
    }

    /**
     * Obtiene el valor de la propiedad subdireccionRrhhRespId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubdireccionRrhhRespId() {
        return subdireccionRrhhRespId;
    }

    /**
     * Define el valor de la propiedad subdireccionRrhhRespId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubdireccionRrhhRespId(String value) {
        this.subdireccionRrhhRespId = value;
    }

}
