
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Bapireturn1 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Bapireturn1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Type" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="Id" type="{urn:sap-com:document:sap:soap:functions:mc-style}char20"/&gt;
 *         &lt;element name="Number" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric3"/&gt;
 *         &lt;element name="Message" type="{urn:sap-com:document:sap:soap:functions:mc-style}char220"/&gt;
 *         &lt;element name="LogNo" type="{urn:sap-com:document:sap:soap:functions:mc-style}char20"/&gt;
 *         &lt;element name="LogMsgNo" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric6"/&gt;
 *         &lt;element name="MessageV1" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/&gt;
 *         &lt;element name="MessageV2" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/&gt;
 *         &lt;element name="MessageV3" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/&gt;
 *         &lt;element name="MessageV4" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bapireturn1", propOrder = {
    "type",
    "id",
    "number",
    "message",
    "logNo",
    "logMsgNo",
    "messageV1",
    "messageV2",
    "messageV3",
    "messageV4"
})
public class Bapireturn1 {

    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "Number", required = true)
    protected String number;
    @XmlElement(name = "Message", required = true)
    protected String message;
    @XmlElement(name = "LogNo", required = true)
    protected String logNo;
    @XmlElement(name = "LogMsgNo", required = true)
    protected String logMsgNo;
    @XmlElement(name = "MessageV1", required = true)
    protected String messageV1;
    @XmlElement(name = "MessageV2", required = true)
    protected String messageV2;
    @XmlElement(name = "MessageV3", required = true)
    protected String messageV3;
    @XmlElement(name = "MessageV4", required = true)
    protected String messageV4;

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad number.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Define el valor de la propiedad number.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Obtiene el valor de la propiedad message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define el valor de la propiedad message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Obtiene el valor de la propiedad logNo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogNo() {
        return logNo;
    }

    /**
     * Define el valor de la propiedad logNo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogNo(String value) {
        this.logNo = value;
    }

    /**
     * Obtiene el valor de la propiedad logMsgNo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogMsgNo() {
        return logMsgNo;
    }

    /**
     * Define el valor de la propiedad logMsgNo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogMsgNo(String value) {
        this.logMsgNo = value;
    }

    /**
     * Obtiene el valor de la propiedad messageV1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageV1() {
        return messageV1;
    }

    /**
     * Define el valor de la propiedad messageV1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageV1(String value) {
        this.messageV1 = value;
    }

    /**
     * Obtiene el valor de la propiedad messageV2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageV2() {
        return messageV2;
    }

    /**
     * Define el valor de la propiedad messageV2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageV2(String value) {
        this.messageV2 = value;
    }

    /**
     * Obtiene el valor de la propiedad messageV3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageV3() {
        return messageV3;
    }

    /**
     * Define el valor de la propiedad messageV3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageV3(String value) {
        this.messageV3 = value;
    }

    /**
     * Obtiene el valor de la propiedad messageV4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageV4() {
        return messageV4;
    }

    /**
     * Define el valor de la propiedad messageV4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageV4(String value) {
        this.messageV4 = value;
    }

}
