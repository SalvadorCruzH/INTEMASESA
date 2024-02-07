
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
 *         &lt;element name="insertDeviceResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "insertDeviceResult"
})
@XmlRootElement(name = "insertDeviceResponse")
public class InsertDeviceResponse {

    protected String insertDeviceResult;

    /**
     * Obtiene el valor de la propiedad insertDeviceResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsertDeviceResult() {
        return insertDeviceResult;
    }

    /**
     * Define el valor de la propiedad insertDeviceResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsertDeviceResult(String value) {
        this.insertDeviceResult = value;
    }

}
