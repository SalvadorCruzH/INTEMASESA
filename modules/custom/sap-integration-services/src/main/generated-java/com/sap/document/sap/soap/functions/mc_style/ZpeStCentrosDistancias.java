
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStCentrosDistancias complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStCentrosDistancias"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CentroDestinoId" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="CentroDestinoDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="CentroOrigenId" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="CentroOrigenDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Distancia" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStCentrosDistancias", propOrder = {
    "centroDestinoId",
    "centroDestinoDesc",
    "centroOrigenId",
    "centroOrigenDesc",
    "distancia"
})
public class ZpeStCentrosDistancias {

    @XmlElement(name = "CentroDestinoId", required = true)
    protected String centroDestinoId;
    @XmlElement(name = "CentroDestinoDesc", required = true)
    protected String centroDestinoDesc;
    @XmlElement(name = "CentroOrigenId", required = true)
    protected String centroOrigenId;
    @XmlElement(name = "CentroOrigenDesc", required = true)
    protected String centroOrigenDesc;
    @XmlElement(name = "Distancia", required = true)
    protected BigDecimal distancia;

    /**
     * Obtiene el valor de la propiedad centroDestinoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroDestinoId() {
        return centroDestinoId;
    }

    /**
     * Define el valor de la propiedad centroDestinoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroDestinoId(String value) {
        this.centroDestinoId = value;
    }

    /**
     * Obtiene el valor de la propiedad centroDestinoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroDestinoDesc() {
        return centroDestinoDesc;
    }

    /**
     * Define el valor de la propiedad centroDestinoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroDestinoDesc(String value) {
        this.centroDestinoDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad centroOrigenId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroOrigenId() {
        return centroOrigenId;
    }

    /**
     * Define el valor de la propiedad centroOrigenId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroOrigenId(String value) {
        this.centroOrigenId = value;
    }

    /**
     * Obtiene el valor de la propiedad centroOrigenDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroOrigenDesc() {
        return centroOrigenDesc;
    }

    /**
     * Define el valor de la propiedad centroOrigenDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroOrigenDesc(String value) {
        this.centroOrigenDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad distancia.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDistancia() {
        return distancia;
    }

    /**
     * Define el valor de la propiedad distancia.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDistancia(BigDecimal value) {
        this.distancia = value;
    }

}
