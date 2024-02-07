
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
 *         &lt;element name="substatus_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="technician_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "substatusId",
    "technicianId"
})
@XmlRootElement(name = "updateIncidentSubstatus")
public class UpdateIncidentSubstatus {

    @XmlElement(name = "incident_id")
    protected String incidentId;
    @XmlElement(name = "substatus_id")
    protected String substatusId;
    @XmlElement(name = "technician_id")
    protected String technicianId;

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
     * Obtiene el valor de la propiedad substatusId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstatusId() {
        return substatusId;
    }

    /**
     * Define el valor de la propiedad substatusId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstatusId(String value) {
        this.substatusId = value;
    }

    /**
     * Obtiene el valor de la propiedad technicianId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicianId() {
        return technicianId;
    }

    /**
     * Define el valor de la propiedad technicianId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicianId(String value) {
        this.technicianId = value;
    }

}
