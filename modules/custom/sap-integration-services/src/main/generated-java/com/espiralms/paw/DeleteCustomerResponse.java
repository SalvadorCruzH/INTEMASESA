
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
 *         &lt;element name="deleteCustomerResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "deleteCustomerResult"
})
@XmlRootElement(name = "deleteCustomerResponse")
public class DeleteCustomerResponse {

    protected int deleteCustomerResult;

    /**
     * Obtiene el valor de la propiedad deleteCustomerResult.
     * 
     */
    public int getDeleteCustomerResult() {
        return deleteCustomerResult;
    }

    /**
     * Define el valor de la propiedad deleteCustomerResult.
     * 
     */
    public void setDeleteCustomerResult(int value) {
        this.deleteCustomerResult = value;
    }

}
