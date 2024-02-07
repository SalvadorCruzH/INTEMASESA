
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
 *         &lt;element name="CloseIncidentResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "closeIncidentResult"
})
@XmlRootElement(name = "CloseIncidentResponse")
public class CloseIncidentResponse {

    @XmlElement(name = "CloseIncidentResult")
    protected int closeIncidentResult;

    /**
     * Obtiene el valor de la propiedad closeIncidentResult.
     * 
     */
    public int getCloseIncidentResult() {
        return closeIncidentResult;
    }

    /**
     * Define el valor de la propiedad closeIncidentResult.
     * 
     */
    public void setCloseIncidentResult(int value) {
        this.closeIncidentResult = value;
    }

}
