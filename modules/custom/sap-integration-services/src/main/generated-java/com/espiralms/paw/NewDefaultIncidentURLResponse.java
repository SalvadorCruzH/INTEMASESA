
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
 *         &lt;element name="newDefaultIncidentURLResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "newDefaultIncidentURLResult"
})
@XmlRootElement(name = "newDefaultIncidentURLResponse")
public class NewDefaultIncidentURLResponse {

    protected String newDefaultIncidentURLResult;

    /**
     * Obtiene el valor de la propiedad newDefaultIncidentURLResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewDefaultIncidentURLResult() {
        return newDefaultIncidentURLResult;
    }

    /**
     * Define el valor de la propiedad newDefaultIncidentURLResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewDefaultIncidentURLResult(String value) {
        this.newDefaultIncidentURLResult = value;
    }

}
