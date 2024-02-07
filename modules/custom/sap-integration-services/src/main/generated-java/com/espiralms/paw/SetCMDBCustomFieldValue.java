
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
 *         &lt;element name="ci_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="customField_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tech_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "ciId",
    "customFieldId",
    "value",
    "techId"
})
@XmlRootElement(name = "SetCMDBCustomFieldValue")
public class SetCMDBCustomFieldValue {

    @XmlElement(name = "ci_id")
    protected String ciId;
    @XmlElement(name = "customField_id")
    protected String customFieldId;
    protected String value;
    @XmlElement(name = "tech_id")
    protected String techId;

    /**
     * Obtiene el valor de la propiedad ciId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiId() {
        return ciId;
    }

    /**
     * Define el valor de la propiedad ciId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiId(String value) {
        this.ciId = value;
    }

    /**
     * Obtiene el valor de la propiedad customFieldId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomFieldId() {
        return customFieldId;
    }

    /**
     * Define el valor de la propiedad customFieldId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomFieldId(String value) {
        this.customFieldId = value;
    }

    /**
     * Obtiene el valor de la propiedad value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad techId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechId() {
        return techId;
    }

    /**
     * Define el valor de la propiedad techId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechId(String value) {
        this.techId = value;
    }

}
