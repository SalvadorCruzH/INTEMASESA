
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
 *         &lt;element name="customField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceElementcode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "customField",
    "sourceElementcode"
})
@XmlRootElement(name = "getSDCustomFieldIdBySource")
public class GetSDCustomFieldIdBySource {

    protected String customField;
    protected int sourceElementcode;

    /**
     * Obtiene el valor de la propiedad customField.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomField() {
        return customField;
    }

    /**
     * Define el valor de la propiedad customField.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomField(String value) {
        this.customField = value;
    }

    /**
     * Obtiene el valor de la propiedad sourceElementcode.
     * 
     */
    public int getSourceElementcode() {
        return sourceElementcode;
    }

    /**
     * Define el valor de la propiedad sourceElementcode.
     * 
     */
    public void setSourceElementcode(int value) {
        this.sourceElementcode = value;
    }

}