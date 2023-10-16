
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoJornadaResume complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoJornadaResume"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Inper" type="{urn:sap-com:document:sap:rfc:functions}char6"/&gt;
 *         &lt;element name="Bezug" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10"/&gt;
 *         &lt;element name="Trabajadas" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="Dedicacion" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="DedicacionFestivo" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="HorasExtrasComputo" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="Vacaciones" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoJornadaResume", propOrder = {
    "inper",
    "bezug",
    "trabajadas",
    "dedicacion",
    "dedicacionFestivo",
    "horasExtrasComputo",
    "vacaciones"
})
public class ZpeStEmpleadoJornadaResume {

    @XmlElement(name = "Inper", required = true)
    protected String inper;
    @XmlElement(name = "Bezug", required = true)
    protected String bezug;
    @XmlElement(name = "Trabajadas", required = true)
    protected BigDecimal trabajadas;
    @XmlElement(name = "Dedicacion", required = true)
    protected BigDecimal dedicacion;
    @XmlElement(name = "DedicacionFestivo", required = true)
    protected BigDecimal dedicacionFestivo;
    @XmlElement(name = "HorasExtrasComputo", required = true)
    protected BigDecimal horasExtrasComputo;
    @XmlElement(name = "Vacaciones", required = true)
    protected BigDecimal vacaciones;

    /**
     * Obtiene el valor de la propiedad inper.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInper() {
        return inper;
    }

    /**
     * Define el valor de la propiedad inper.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInper(String value) {
        this.inper = value;
    }

    /**
     * Obtiene el valor de la propiedad bezug.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBezug() {
        return bezug;
    }

    /**
     * Define el valor de la propiedad bezug.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBezug(String value) {
        this.bezug = value;
    }

    /**
     * Obtiene el valor de la propiedad trabajadas.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTrabajadas() {
        return trabajadas;
    }

    /**
     * Define el valor de la propiedad trabajadas.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTrabajadas(BigDecimal value) {
        this.trabajadas = value;
    }

    /**
     * Obtiene el valor de la propiedad dedicacion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDedicacion() {
        return dedicacion;
    }

    /**
     * Define el valor de la propiedad dedicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDedicacion(BigDecimal value) {
        this.dedicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad dedicacionFestivo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDedicacionFestivo() {
        return dedicacionFestivo;
    }

    /**
     * Define el valor de la propiedad dedicacionFestivo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDedicacionFestivo(BigDecimal value) {
        this.dedicacionFestivo = value;
    }

    /**
     * Obtiene el valor de la propiedad horasExtrasComputo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHorasExtrasComputo() {
        return horasExtrasComputo;
    }

    /**
     * Define el valor de la propiedad horasExtrasComputo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHorasExtrasComputo(BigDecimal value) {
        this.horasExtrasComputo = value;
    }

    /**
     * Obtiene el valor de la propiedad vacaciones.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVacaciones() {
        return vacaciones;
    }

    /**
     * Define el valor de la propiedad vacaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVacaciones(BigDecimal value) {
        this.vacaciones = value;
    }

}
