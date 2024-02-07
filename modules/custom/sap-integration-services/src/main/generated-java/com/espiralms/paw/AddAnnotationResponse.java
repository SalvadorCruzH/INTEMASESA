
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
 *         &lt;element name="addAnnotationResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "addAnnotationResult"
})
@XmlRootElement(name = "addAnnotationResponse")
public class AddAnnotationResponse {

    protected int addAnnotationResult;

    /**
     * Obtiene el valor de la propiedad addAnnotationResult.
     * 
     */
    public int getAddAnnotationResult() {
        return addAnnotationResult;
    }

    /**
     * Define el valor de la propiedad addAnnotationResult.
     * 
     */
    public void setAddAnnotationResult(int value) {
        this.addAnnotationResult = value;
    }

}
