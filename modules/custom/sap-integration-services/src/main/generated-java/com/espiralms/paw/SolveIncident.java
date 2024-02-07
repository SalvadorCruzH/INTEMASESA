
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
 *         &lt;element name="pawSvcAuthUsers_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="solution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "pawSvcAuthUsersId",
    "solution"
})
@XmlRootElement(name = "SolveIncident")
public class SolveIncident {

    @XmlElement(name = "incident_id")
    protected String incidentId;
    @XmlElement(name = "pawSvcAuthUsers_id")
    protected String pawSvcAuthUsersId;
    protected String solution;

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
     * Obtiene el valor de la propiedad pawSvcAuthUsersId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPawSvcAuthUsersId() {
        return pawSvcAuthUsersId;
    }

    /**
     * Define el valor de la propiedad pawSvcAuthUsersId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPawSvcAuthUsersId(String value) {
        this.pawSvcAuthUsersId = value;
    }

    /**
     * Obtiene el valor de la propiedad solution.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Define el valor de la propiedad solution.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolution(String value) {
        this.solution = value;
    }

}
