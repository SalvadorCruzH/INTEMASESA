
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
 *         &lt;element name="GetCIIdByLabelResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "getCIIdByLabelResult"
})
@XmlRootElement(name = "GetCIIdByLabelResponse")
public class GetCIIdByLabelResponse {

    @XmlElement(name = "GetCIIdByLabelResult")
    protected String getCIIdByLabelResult;

    /**
     * Obtiene el valor de la propiedad getCIIdByLabelResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetCIIdByLabelResult() {
        return getCIIdByLabelResult;
    }

    /**
     * Define el valor de la propiedad getCIIdByLabelResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetCIIdByLabelResult(String value) {
        this.getCIIdByLabelResult = value;
    }

}
