
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStDetecnecformConsulta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStDetecnecformConsulta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PlanFormacion" type="{urn:sap-com:document:sap:rfc:functions}char9"/&gt;
 *         &lt;element name="NumFormuladaPor" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Estado" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
 *         &lt;element name="Solicitante" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStDetecnecformConsulta", propOrder = {
    "planFormacion",
    "numFormuladaPor",
    "estado",
    "solicitante"
})
public class ZpeStDetecnecformConsulta {

    @XmlElement(name = "PlanFormacion", required = true)
    protected String planFormacion;
    @XmlElement(name = "NumFormuladaPor", required = true)
    protected String numFormuladaPor;
    @XmlElement(name = "Estado", required = true)
    protected String estado;
    @XmlElement(name = "Solicitante", required = true)
    protected String solicitante;

    /**
     * Obtiene el valor de la propiedad planFormacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanFormacion() {
        return planFormacion;
    }

    /**
     * Define el valor de la propiedad planFormacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanFormacion(String value) {
        this.planFormacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numFormuladaPor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumFormuladaPor() {
        return numFormuladaPor;
    }

    /**
     * Define el valor de la propiedad numFormuladaPor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumFormuladaPor(String value) {
        this.numFormuladaPor = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad solicitante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * Define el valor de la propiedad solicitante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolicitante(String value) {
        this.solicitante = value;
    }

}
