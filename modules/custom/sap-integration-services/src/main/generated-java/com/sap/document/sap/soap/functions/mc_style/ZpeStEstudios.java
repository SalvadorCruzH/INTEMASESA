
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEstudios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEstudios"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EstudioId" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/&gt;
 *         &lt;element name="EstudioDesc" type="{urn:sap-com:document:sap:rfc:functions}char200"/&gt;
 *         &lt;element name="Importe" type="{urn:sap-com:document:sap:rfc:functions}curr9.2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEstudios", propOrder = {
    "estudioId",
    "estudioDesc",
    "importe"
})
public class ZpeStEstudios {

    @XmlElement(name = "EstudioId", required = true)
    protected String estudioId;
    @XmlElement(name = "EstudioDesc", required = true)
    protected String estudioDesc;
    @XmlElement(name = "Importe", required = true)
    protected BigDecimal importe;

    /**
     * Obtiene el valor de la propiedad estudioId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstudioId() {
        return estudioId;
    }

    /**
     * Define el valor de la propiedad estudioId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstudioId(String value) {
        this.estudioId = value;
    }

    /**
     * Obtiene el valor de la propiedad estudioDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstudioDesc() {
        return estudioDesc;
    }

    /**
     * Define el valor de la propiedad estudioDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstudioDesc(String value) {
        this.estudioDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporte(BigDecimal value) {
        this.importe = value;
    }

}
