
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
 *         &lt;element name="ci1_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ci2_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rel_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="impact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "ci1Id",
    "ci2Id",
    "relId",
    "impact",
    "techId"
})
@XmlRootElement(name = "CreateCIsRel")
public class CreateCIsRel {

    @XmlElement(name = "ci1_id")
    protected String ci1Id;
    @XmlElement(name = "ci2_id")
    protected String ci2Id;
    @XmlElement(name = "rel_id")
    protected String relId;
    protected String impact;
    @XmlElement(name = "tech_id")
    protected String techId;

    /**
     * Obtiene el valor de la propiedad ci1Id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCi1Id() {
        return ci1Id;
    }

    /**
     * Define el valor de la propiedad ci1Id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCi1Id(String value) {
        this.ci1Id = value;
    }

    /**
     * Obtiene el valor de la propiedad ci2Id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCi2Id() {
        return ci2Id;
    }

    /**
     * Define el valor de la propiedad ci2Id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCi2Id(String value) {
        this.ci2Id = value;
    }

    /**
     * Obtiene el valor de la propiedad relId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelId() {
        return relId;
    }

    /**
     * Define el valor de la propiedad relId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelId(String value) {
        this.relId = value;
    }

    /**
     * Obtiene el valor de la propiedad impact.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImpact() {
        return impact;
    }

    /**
     * Define el valor de la propiedad impact.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImpact(String value) {
        this.impact = value;
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
