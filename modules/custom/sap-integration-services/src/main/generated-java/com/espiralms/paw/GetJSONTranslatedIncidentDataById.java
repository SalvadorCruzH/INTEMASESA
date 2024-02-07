
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
 *         &lt;element name="incidentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lang" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "incidentId",
    "lang"
})
@XmlRootElement(name = "getJSONTranslatedIncidentDataById")
public class GetJSONTranslatedIncidentDataById {

    protected String incidentId;
    protected String lang;

    /**
     * Obtiene el valor de la propiedad incidentId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncidentId() {
        return incidentId;
    }

    /**
     * Define el valor de la propiedad incidentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncidentId(String value) {
        this.incidentId = value;
    }

    /**
     * Obtiene el valor de la propiedad lang.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Define el valor de la propiedad lang.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

}
