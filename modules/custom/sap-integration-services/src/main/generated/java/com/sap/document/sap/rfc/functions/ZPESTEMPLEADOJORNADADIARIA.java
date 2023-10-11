
package com.sap.document.sap.rfc.functions;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZPE_ST_EMPLEADO_JORNADA_DIARIA complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZPE_ST_EMPLEADO_JORNADA_DIARIA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DATUM" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="TPROG" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="TTEXT" type="{urn:sap-com:document:sap:rfc:functions}char15"/&gt;
 *         &lt;element name="SOBEG" type="{urn:sap-com:document:sap:soap:functions:mc-style}time"/&gt;
 *         &lt;element name="SOEND" type="{urn:sap-com:document:sap:soap:functions:mc-style}time"/&gt;
 *         &lt;element name="STDAZ" type="{urn:sap-com:document:sap:rfc:functions}decimal5.2"/&gt;
 *         &lt;element name="HTRAB" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="SEMAFORO" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="AWART" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="ATEXT" type="{urn:sap-com:document:sap:rfc:functions}char25"/&gt;
 *         &lt;element name="HDEXC" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="HEXTC" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="HEXND" type="{urn:sap-com:document:sap:rfc:functions}decimal5.2"/&gt;
 *         &lt;element name="HEXNN" type="{urn:sap-com:document:sap:rfc:functions}decimal5.2"/&gt;
 *         &lt;element name="ERRTY" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="ERROR" type="{urn:sap-com:document:sap:soap:functions:mc-style}char2"/&gt;
 *         &lt;element name="ETEXT" type="{urn:sap-com:document:sap:soap:functions:mc-style}char40"/&gt;
 *         &lt;element name="MESTY" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="ENT1" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="SAL1" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="ENT2" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="SAL2" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="ENT3" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="SAL3" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="ENT4" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="SAL4" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZPE_ST_EMPLEADO_JORNADA_DIARIA", propOrder = {
    "datum",
    "tprog",
    "ttext",
    "sobeg",
    "soend",
    "stdaz",
    "htrab",
    "semaforo",
    "awart",
    "atext",
    "hdexc",
    "hextc",
    "hexnd",
    "hexnn",
    "errty",
    "error",
    "etext",
    "mesty",
    "ent1",
    "sal1",
    "ent2",
    "sal2",
    "ent3",
    "sal3",
    "ent4",
    "sal4"
})
public class ZPESTEMPLEADOJORNADADIARIA {

    @XmlElement(name = "DATUM", required = true)
    protected String datum;
    @XmlElement(name = "TPROG", required = true)
    protected String tprog;
    @XmlElement(name = "TTEXT", required = true)
    protected String ttext;
    @XmlElement(name = "SOBEG", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar sobeg;
    @XmlElement(name = "SOEND", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar soend;
    @XmlElement(name = "STDAZ", required = true)
    protected BigDecimal stdaz;
    @XmlElement(name = "HTRAB", required = true)
    protected BigDecimal htrab;
    @XmlElement(name = "SEMAFORO", required = true)
    protected String semaforo;
    @XmlElement(name = "AWART", required = true)
    protected String awart;
    @XmlElement(name = "ATEXT", required = true)
    protected String atext;
    @XmlElement(name = "HDEXC", required = true)
    protected BigDecimal hdexc;
    @XmlElement(name = "HEXTC", required = true)
    protected BigDecimal hextc;
    @XmlElement(name = "HEXND", required = true)
    protected BigDecimal hexnd;
    @XmlElement(name = "HEXNN", required = true)
    protected BigDecimal hexnn;
    @XmlElement(name = "ERRTY", required = true)
    protected String errty;
    @XmlElement(name = "ERROR", required = true)
    protected String error;
    @XmlElement(name = "ETEXT", required = true)
    protected String etext;
    @XmlElement(name = "MESTY", required = true)
    protected String mesty;
    @XmlElement(name = "ENT1", required = true)
    protected String ent1;
    @XmlElement(name = "SAL1", required = true)
    protected String sal1;
    @XmlElement(name = "ENT2", required = true)
    protected String ent2;
    @XmlElement(name = "SAL2", required = true)
    protected String sal2;
    @XmlElement(name = "ENT3", required = true)
    protected String ent3;
    @XmlElement(name = "SAL3", required = true)
    protected String sal3;
    @XmlElement(name = "ENT4", required = true)
    protected String ent4;
    @XmlElement(name = "SAL4", required = true)
    protected String sal4;

    /**
     * Obtiene el valor de la propiedad datum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATUM() {
        return datum;
    }

    /**
     * Define el valor de la propiedad datum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATUM(String value) {
        this.datum = value;
    }

    /**
     * Obtiene el valor de la propiedad tprog.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPROG() {
        return tprog;
    }

    /**
     * Define el valor de la propiedad tprog.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPROG(String value) {
        this.tprog = value;
    }

    /**
     * Obtiene el valor de la propiedad ttext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTTEXT() {
        return ttext;
    }

    /**
     * Define el valor de la propiedad ttext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTTEXT(String value) {
        this.ttext = value;
    }

    /**
     * Obtiene el valor de la propiedad sobeg.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSOBEG() {
        return sobeg;
    }

    /**
     * Define el valor de la propiedad sobeg.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSOBEG(XMLGregorianCalendar value) {
        this.sobeg = value;
    }

    /**
     * Obtiene el valor de la propiedad soend.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSOEND() {
        return soend;
    }

    /**
     * Define el valor de la propiedad soend.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSOEND(XMLGregorianCalendar value) {
        this.soend = value;
    }

    /**
     * Obtiene el valor de la propiedad stdaz.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSTDAZ() {
        return stdaz;
    }

    /**
     * Define el valor de la propiedad stdaz.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSTDAZ(BigDecimal value) {
        this.stdaz = value;
    }

    /**
     * Obtiene el valor de la propiedad htrab.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHTRAB() {
        return htrab;
    }

    /**
     * Define el valor de la propiedad htrab.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHTRAB(BigDecimal value) {
        this.htrab = value;
    }

    /**
     * Obtiene el valor de la propiedad semaforo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEMAFORO() {
        return semaforo;
    }

    /**
     * Define el valor de la propiedad semaforo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEMAFORO(String value) {
        this.semaforo = value;
    }

    /**
     * Obtiene el valor de la propiedad awart.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAWART() {
        return awart;
    }

    /**
     * Define el valor de la propiedad awart.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAWART(String value) {
        this.awart = value;
    }

    /**
     * Obtiene el valor de la propiedad atext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getATEXT() {
        return atext;
    }

    /**
     * Define el valor de la propiedad atext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setATEXT(String value) {
        this.atext = value;
    }

    /**
     * Obtiene el valor de la propiedad hdexc.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHDEXC() {
        return hdexc;
    }

    /**
     * Define el valor de la propiedad hdexc.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHDEXC(BigDecimal value) {
        this.hdexc = value;
    }

    /**
     * Obtiene el valor de la propiedad hextc.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHEXTC() {
        return hextc;
    }

    /**
     * Define el valor de la propiedad hextc.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHEXTC(BigDecimal value) {
        this.hextc = value;
    }

    /**
     * Obtiene el valor de la propiedad hexnd.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHEXND() {
        return hexnd;
    }

    /**
     * Define el valor de la propiedad hexnd.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHEXND(BigDecimal value) {
        this.hexnd = value;
    }

    /**
     * Obtiene el valor de la propiedad hexnn.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHEXNN() {
        return hexnn;
    }

    /**
     * Define el valor de la propiedad hexnn.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHEXNN(BigDecimal value) {
        this.hexnn = value;
    }

    /**
     * Obtiene el valor de la propiedad errty.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERRTY() {
        return errty;
    }

    /**
     * Define el valor de la propiedad errty.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERRTY(String value) {
        this.errty = value;
    }

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERROR() {
        return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERROR(String value) {
        this.error = value;
    }

    /**
     * Obtiene el valor de la propiedad etext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETEXT() {
        return etext;
    }

    /**
     * Define el valor de la propiedad etext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETEXT(String value) {
        this.etext = value;
    }

    /**
     * Obtiene el valor de la propiedad mesty.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMESTY() {
        return mesty;
    }

    /**
     * Define el valor de la propiedad mesty.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMESTY(String value) {
        this.mesty = value;
    }

    /**
     * Obtiene el valor de la propiedad ent1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENT1() {
        return ent1;
    }

    /**
     * Define el valor de la propiedad ent1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENT1(String value) {
        this.ent1 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSAL1() {
        return sal1;
    }

    /**
     * Define el valor de la propiedad sal1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSAL1(String value) {
        this.sal1 = value;
    }

    /**
     * Obtiene el valor de la propiedad ent2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENT2() {
        return ent2;
    }

    /**
     * Define el valor de la propiedad ent2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENT2(String value) {
        this.ent2 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSAL2() {
        return sal2;
    }

    /**
     * Define el valor de la propiedad sal2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSAL2(String value) {
        this.sal2 = value;
    }

    /**
     * Obtiene el valor de la propiedad ent3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENT3() {
        return ent3;
    }

    /**
     * Define el valor de la propiedad ent3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENT3(String value) {
        this.ent3 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSAL3() {
        return sal3;
    }

    /**
     * Define el valor de la propiedad sal3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSAL3(String value) {
        this.sal3 = value;
    }

    /**
     * Obtiene el valor de la propiedad ent4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENT4() {
        return ent4;
    }

    /**
     * Define el valor de la propiedad ent4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENT4(String value) {
        this.ent4 = value;
    }

    /**
     * Obtiene el valor de la propiedad sal4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSAL4() {
        return sal4;
    }

    /**
     * Define el valor de la propiedad sal4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSAL4(String value) {
        this.sal4 = value;
    }

}
