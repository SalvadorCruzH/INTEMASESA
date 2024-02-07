
package com.espiralms.paw;

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
 *         &lt;element name="incident_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="urgency_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="impact_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "incidentId",
    "urgencyId",
    "impactId"
})
@XmlRootElement(name = "SetUrgentcyAndImpact")
public class SetUrgentcyAndImpact {

    @XmlElement(name = "incident_id")
    protected String incidentId;
    @XmlElement(name = "urgency_id")
    protected String urgencyId;
    @XmlElement(name = "impact_id")
    protected String impactId;

    /**
     * Obtiene el valor de la propiedad incidentId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentId() {
        return incidentId;
    }

    /**
     * Define el valor de la propiedad incidentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentId(String value) {
        this.incidentId = value;
    }

    /**
     * Obtiene el valor de la propiedad urgencyId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrgencyId() {
        return urgencyId;
    }

    /**
     * Define el valor de la propiedad urgencyId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrgencyId(String value) {
        this.urgencyId = value;
    }

    /**
     * Obtiene el valor de la propiedad impactId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpactId() {
        return impactId;
    }

    /**
     * Define el valor de la propiedad impactId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpactId(String value) {
        this.impactId = value;
    }

}
