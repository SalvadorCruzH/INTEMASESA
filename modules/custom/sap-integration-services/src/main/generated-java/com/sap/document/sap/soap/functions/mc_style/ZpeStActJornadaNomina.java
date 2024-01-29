
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStActJornadaNomina complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStActJornadaNomina"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="FechaInicio" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Iban" type="{urn:sap-com:document:sap:rfc:functions}char34"/&gt;
 *         &lt;element name="IrpfSolicitado" type="{urn:sap-com:document:sap:rfc:functions}decimal4.2"/&gt;
 *         &lt;element name="HeInicio" type="{urn:sap-com:document:sap:rfc:functions}time"/&gt;
 *         &lt;element name="HeFin" type="{urn:sap-com:document:sap:rfc:functions}time"/&gt;
 *         &lt;element name="HeTipoRetribucion" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="PlusCcnomina" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="PlusUnidades" type="{urn:sap-com:document:sap:soap:functions:mc-style}decimal7.2"/&gt;
 *         &lt;element name="MarcajeHora" type="{urn:sap-com:document:sap:rfc:functions}time"/&gt;
 *         &lt;element name="MarcajeMotivo" type="{urn:sap-com:document:sap:soap:functions:mc-style}char4"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStActJornadaNomina", propOrder = {
    "pernr",
    "fechaInicio",
    "iban",
    "irpfSolicitado",
    "heInicio",
    "heFin",
    "heTipoRetribucion",
    "plusCcnomina",
    "plusUnidades",
    "marcajeHora",
    "marcajeMotivo"
})
public class ZpeStActJornadaNomina {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "FechaInicio", required = true)
    protected String fechaInicio;
    @XmlElement(name = "Iban", required = true)
    protected String iban;
    @XmlElement(name = "IrpfSolicitado", required = true)
    protected BigDecimal irpfSolicitado;
    @XmlElement(name = "HeInicio", required = true)
    @XmlSchemaType(name = "time")
    protected String heInicio;
    @XmlElement(name = "HeFin", required = true)
    @XmlSchemaType(name = "time")
    protected String heFin;
    @XmlElement(name = "HeTipoRetribucion", required = true)
    protected String heTipoRetribucion;
    @XmlElement(name = "PlusCcnomina", required = true)
    protected String plusCcnomina;
    @XmlElement(name = "PlusUnidades", required = true)
    protected BigDecimal plusUnidades;
    @XmlElement(name = "MarcajeHora", required = true)
    @XmlSchemaType(name = "time")
    protected String marcajeHora;
    @XmlElement(name = "MarcajeMotivo", required = true)
    protected String marcajeMotivo;

    /**
     * Obtiene el valor de la propiedad pernr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPernr() {
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
    public void setPernr(String value) {
        this.pernr = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Define el valor de la propiedad fechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicio(String value) {
        this.fechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad iban.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * Define el valor de la propiedad iban.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * Obtiene el valor de la propiedad irpfSolicitado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIrpfSolicitado() {
        return irpfSolicitado;
    }

    /**
     * Define el valor de la propiedad irpfSolicitado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIrpfSolicitado(BigDecimal value) {
        this.irpfSolicitado = value;
    }

    /**
     * Obtiene el valor de la propiedad heInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getHeInicio() {
        return heInicio;
    }

    //TODO: he cambiado HeInicio de XMLGregorianCalendar a String HH:mm:ss
    /**
     * Define el valor de la propiedad heInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHeInicio(String value) {
        this.heInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad heFin.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getHeFin() {
        return heFin;
    }

    //TODO: he cambiado HeFin de XMLGregorianCalendar a String HH:mm:ss
    /**
     * Define el valor de la propiedad heFin.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setHeFin(String value) {
        this.heFin = value;
    }

    /**
     * Obtiene el valor de la propiedad heTipoRetribucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeTipoRetribucion() {
        return heTipoRetribucion;
    }

    /**
     * Define el valor de la propiedad heTipoRetribucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeTipoRetribucion(String value) {
        this.heTipoRetribucion = value;
    }

    /**
     * Obtiene el valor de la propiedad plusCcnomina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlusCcnomina() {
        return plusCcnomina;
    }

    /**
     * Define el valor de la propiedad plusCcnomina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlusCcnomina(String value) {
        this.plusCcnomina = value;
    }

    /**
     * Obtiene el valor de la propiedad plusUnidades.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPlusUnidades() {
        return plusUnidades;
    }

    /**
     * Define el valor de la propiedad plusUnidades.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPlusUnidades(BigDecimal value) {
        this.plusUnidades = value;
    }


    /**
     * Obtiene el valor de la propiedad marcajeHora.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getMarcajeHora() {
        return marcajeHora;
    }
        //TODO: he cambiado marcajeHora de XMLGregorianCalendar a String HH:mm:ss
    /**
     * Define el valor de la propiedad marcajeHora.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMarcajeHora(String value) {
        this.marcajeHora = value;
    }

    /**
     * Obtiene el valor de la propiedad marcajeMotivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcajeMotivo() {
        return marcajeMotivo;
    }

    /**
     * Define el valor de la propiedad marcajeMotivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcajeMotivo(String value) {
        this.marcajeMotivo = value;
    }

}
