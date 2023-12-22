
package com.sap.document.sap.rfc.functions;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZPE_ST_INS_AYUDA_ESCOLAR complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZPE_ST_INS_AYUDA_ESCOLAR"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PERNR" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="TIPO_ID" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="NUMERO" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="ESTUDIO_ID" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/&gt;
 *         &lt;element name="ESTUDIO_NIVEL" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/&gt;
 *         &lt;element name="CENTRO" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="COMENTARIO" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="FAM_NUMEROSA" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="FAM_MONOPARENTAL" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZPE_ST_INS_AYUDA_ESCOLAR", propOrder = {
    "pernr",
    "tipoid",
    "numero",
    "estudioid",
    "estudionivel",
    "centro",
    "comentario",
    "famnumerosa",
    "fammonoparental"
})
public class ZPESTINSAYUDAESCOLAR {

    @XmlElement(name = "PERNR", required = true)
    protected String pernr;
    @XmlElement(name = "TIPO_ID", required = true)
    protected String tipoid;
    @XmlElement(name = "NUMERO", required = true)
    protected String numero;
    @XmlElement(name = "ESTUDIO_ID", required = true)
    protected String estudioid;
    @XmlElement(name = "ESTUDIO_NIVEL", required = true)
    protected String estudionivel;
    @XmlElement(name = "CENTRO", required = true)
    protected String centro;
    @XmlElement(name = "COMENTARIO", required = true)
    protected String comentario;
    @XmlElement(name = "FAM_NUMEROSA", required = true)
    protected String famnumerosa;
    @XmlElement(name = "FAM_MONOPARENTAL", required = true)
    protected String fammonoparental;

    /**
     * Obtiene el valor de la propiedad pernr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPERNR() {
        return pernr;
    }

    /**
     * Define el valor de la propiedad pernr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPERNR(String value) {
        this.pernr = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOID() {
        return tipoid;
    }

    /**
     * Define el valor de la propiedad tipoid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOID(String value) {
        this.tipoid = value;
    }

    /**
     * Obtiene el valor de la propiedad numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMERO() {
        return numero;
    }

    /**
     * Define el valor de la propiedad numero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMERO(String value) {
        this.numero = value;
    }

    /**
     * Obtiene el valor de la propiedad estudioid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTUDIOID() {
        return estudioid;
    }

    /**
     * Define el valor de la propiedad estudioid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTUDIOID(String value) {
        this.estudioid = value;
    }

    /**
     * Obtiene el valor de la propiedad estudionivel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTUDIONIVEL() {
        return estudionivel;
    }

    /**
     * Define el valor de la propiedad estudionivel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTUDIONIVEL(String value) {
        this.estudionivel = value;
    }

    /**
     * Obtiene el valor de la propiedad centro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCENTRO() {
        return centro;
    }

    /**
     * Define el valor de la propiedad centro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCENTRO(String value) {
        this.centro = value;
    }

    /**
     * Obtiene el valor de la propiedad comentario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMENTARIO() {
        return comentario;
    }

    /**
     * Define el valor de la propiedad comentario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMENTARIO(String value) {
        this.comentario = value;
    }

    /**
     * Obtiene el valor de la propiedad famnumerosa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFAMNUMEROSA() {
        return famnumerosa;
    }

    /**
     * Define el valor de la propiedad famnumerosa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFAMNUMEROSA(String value) {
        this.famnumerosa = value;
    }

    /**
     * Obtiene el valor de la propiedad fammonoparental.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFAMMONOPARENTAL() {
        return fammonoparental;
    }

    /**
     * Define el valor de la propiedad fammonoparental.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFAMMONOPARENTAL(String value) {
        this.fammonoparental = value;
    }

}
