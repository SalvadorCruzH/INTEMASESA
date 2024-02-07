
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
 *         &lt;element name="status_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "statusId",
    "techId"
})
@XmlRootElement(name = "SetCMDBCIStatus")
public class SetCMDBCIStatus {

    @XmlElement(name = "ci_id")
    protected String ciId;
    @XmlElement(name = "status_id")
    protected String statusId;
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
     * Obtiene el valor de la propiedad statusId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * Define el valor de la propiedad statusId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusId(String value) {
        this.statusId = value;
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
