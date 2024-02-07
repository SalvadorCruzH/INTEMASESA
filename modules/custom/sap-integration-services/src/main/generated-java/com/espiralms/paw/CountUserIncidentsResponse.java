
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
 *         &lt;element name="countUserIncidentsResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "countUserIncidentsResult"
})
@XmlRootElement(name = "countUserIncidentsResponse")
public class CountUserIncidentsResponse {

    protected int countUserIncidentsResult;

    /**
     * Obtiene el valor de la propiedad countUserIncidentsResult.
     * 
     */
    public int getCountUserIncidentsResult() {
        return countUserIncidentsResult;
    }

    /**
     * Define el valor de la propiedad countUserIncidentsResult.
     * 
     */
    public void setCountUserIncidentsResult(int value) {
        this.countUserIncidentsResult = value;
    }

}
