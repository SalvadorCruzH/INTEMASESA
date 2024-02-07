
package com.espiralms.paw;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="newIncidentWithTechnicianResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "newIncidentWithTechnicianResult"
})
@XmlRootElement(name = "newIncidentWithTechnicianResponse")
public class NewIncidentWithTechnicianResponse {

    protected String newIncidentWithTechnicianResult;

    /**
     * Obtiene el valor de la propiedad newIncidentWithTechnicianResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewIncidentWithTechnicianResult() {
        return newIncidentWithTechnicianResult;
    }

    /**
     * Define el valor de la propiedad newIncidentWithTechnicianResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewIncidentWithTechnicianResult(String value) {
        this.newIncidentWithTechnicianResult = value;
    }

}
