
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
 *         &lt;element name="setSDCustomFieldValueResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "setSDCustomFieldValueResult"
})
@XmlRootElement(name = "setSDCustomFieldValueResponse")
public class SetSDCustomFieldValueResponse {

    protected int setSDCustomFieldValueResult;

    /**
     * Obtiene el valor de la propiedad setSDCustomFieldValueResult.
     * 
     */
    public int getSetSDCustomFieldValueResult() {
        return setSDCustomFieldValueResult;
    }

    /**
     * Define el valor de la propiedad setSDCustomFieldValueResult.
     * 
     */
    public void setSetSDCustomFieldValueResult(int value) {
        this.setSDCustomFieldValueResult = value;
    }

}
