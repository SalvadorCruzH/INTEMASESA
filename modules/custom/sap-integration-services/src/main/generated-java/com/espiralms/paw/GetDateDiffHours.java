
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
 *         &lt;element name="date1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="date2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="padSLAs_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="padCalendars_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "date1",
    "date2",
    "padSLAsId",
    "padCalendarsId"
})
@XmlRootElement(name = "GetDateDiffHours")
public class GetDateDiffHours {

    protected String date1;
    protected String date2;
    @XmlElement(name = "padSLAs_id")
    protected String padSLAsId;
    @XmlElement(name = "padCalendars_id")
    protected String padCalendarsId;

    /**
     * Obtiene el valor de la propiedad date1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate1() {
        return date1;
    }

    /**
     * Define el valor de la propiedad date1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate1(String value) {
        this.date1 = value;
    }

    /**
     * Obtiene el valor de la propiedad date2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate2() {
        return date2;
    }

    /**
     * Define el valor de la propiedad date2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate2(String value) {
        this.date2 = value;
    }

    /**
     * Obtiene el valor de la propiedad padSLAsId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPadSLAsId() {
        return padSLAsId;
    }

    /**
     * Define el valor de la propiedad padSLAsId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPadSLAsId(String value) {
        this.padSLAsId = value;
    }

    /**
     * Obtiene el valor de la propiedad padCalendarsId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPadCalendarsId() {
        return padCalendarsId;
    }

    /**
     * Define el valor de la propiedad padCalendarsId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPadCalendarsId(String value) {
        this.padCalendarsId = value;
    }

}
