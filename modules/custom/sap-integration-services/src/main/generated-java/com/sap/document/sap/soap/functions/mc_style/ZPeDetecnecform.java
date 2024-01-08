
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
 *         &lt;element name="DetecnecformConsulta" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZpeStDetecnecformConsulta" minOccurs="0"/&gt;
 *         &lt;element name="DetecnecformInsert" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZhrEDetnecform" minOccurs="0"/&gt;
 *         &lt;element name="PlanFormacion" type="{urn:sap-com:document:sap:rfc:functions}char9" minOccurs="0"/&gt;
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
    "detecnecformConsulta",
    "detecnecformInsert",
    "planFormacion"
})
@XmlRootElement(name = "ZPeDetecnecform")
public class ZPeDetecnecform {

    @XmlElement(name = "DetecnecformConsulta")
    protected ZpeStDetecnecformConsulta detecnecformConsulta;
    @XmlElement(name = "DetecnecformInsert")
    protected ZhrEDetnecform detecnecformInsert;
    @XmlElement(name = "PlanFormacion")
    protected String planFormacion;

    /**
     * Obtiene el valor de la propiedad detecnecformConsulta.
     * 
     * @return
     *     possible object is
     *     {@link ZpeStDetecnecformConsulta }
     *     
     */
    public ZpeStDetecnecformConsulta getDetecnecformConsulta() {
        return detecnecformConsulta;
    }

    /**
     * Define el valor de la propiedad detecnecformConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link ZpeStDetecnecformConsulta }
     *     
     */
    public void setDetecnecformConsulta(ZpeStDetecnecformConsulta value) {
        this.detecnecformConsulta = value;
    }

    /**
     * Obtiene el valor de la propiedad detecnecformInsert.
     * 
     * @return
     *     possible object is
     *     {@link ZhrEDetnecform }
     *     
     */
    public ZhrEDetnecform getDetecnecformInsert() {
        return detecnecformInsert;
    }

    /**
     * Define el valor de la propiedad detecnecformInsert.
     * 
     * @param value
     *     allowed object is
     *     {@link ZhrEDetnecform }
     *     
     */
    public void setDetecnecformInsert(ZhrEDetnecform value) {
        this.detecnecformInsert = value;
    }

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

}
