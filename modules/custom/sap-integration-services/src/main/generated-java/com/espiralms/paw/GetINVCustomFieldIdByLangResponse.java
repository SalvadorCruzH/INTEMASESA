
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
 *         &lt;element name="getINVCustomFieldIdByLangResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "getINVCustomFieldIdByLangResult"
})
@XmlRootElement(name = "getINVCustomFieldIdByLangResponse")
public class GetINVCustomFieldIdByLangResponse {

    protected String getINVCustomFieldIdByLangResult;

    /**
     * Obtiene el valor de la propiedad getINVCustomFieldIdByLangResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetINVCustomFieldIdByLangResult() {
        return getINVCustomFieldIdByLangResult;
    }

    /**
     * Define el valor de la propiedad getINVCustomFieldIdByLangResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetINVCustomFieldIdByLangResult(String value) {
        this.getINVCustomFieldIdByLangResult = value;
    }

}
