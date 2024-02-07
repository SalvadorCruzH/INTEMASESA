
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
 *         &lt;element name="customer_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tradeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CIF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="businessAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "customerId",
    "name",
    "tradeName",
    "cif",
    "businessAddress",
    "description"
})
@XmlRootElement(name = "updateCustomer")
public class UpdateCustomer {

    @XmlElement(name = "customer_id")
    protected String customerId;
    protected String name;
    protected String tradeName;
    @XmlElement(name = "CIF")
    protected String cif;
    protected String businessAddress;
    protected String description;

    /**
     * Obtiene el valor de la propiedad customerId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Define el valor de la propiedad customerId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad tradeName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeName() {
        return tradeName;
    }

    /**
     * Define el valor de la propiedad tradeName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeName(String value) {
        this.tradeName = value;
    }

    /**
     * Obtiene el valor de la propiedad cif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIF() {
        return cif;
    }

    /**
     * Define el valor de la propiedad cif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIF(String value) {
        this.cif = value;
    }

    /**
     * Obtiene el valor de la propiedad businessAddress.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessAddress() {
        return businessAddress;
    }

    /**
     * Define el valor de la propiedad businessAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessAddress(String value) {
        this.businessAddress = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
