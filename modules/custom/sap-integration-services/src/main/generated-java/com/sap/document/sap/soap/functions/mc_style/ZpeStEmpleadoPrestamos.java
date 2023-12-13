
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoPrestamos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoPrestamos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="FiniVivienda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="FfinVivienda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="ImporteVivienda" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="MaximoAdquisicion" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="MaximoReforma" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="FiniVacaciones" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="FfinVacaciones" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="FiniPrestamoVacaciones" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="FfinPrestamoVacaciones" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="SalarioBase" type="{urn:sap-com:document:sap:soap:functions:mc-style}curr13.2"/&gt;
 *         &lt;element name="Antiguedad" type="{urn:sap-com:document:sap:soap:functions:mc-style}curr13.2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoPrestamos", propOrder = {
    "pernr",
    "finiVivienda",
    "ffinVivienda",
    "importeVivienda",
    "maximoAdquisicion",
    "maximoReforma",
    "finiVacaciones",
    "ffinVacaciones",
    "finiPrestamoVacaciones",
    "ffinPrestamoVacaciones",
    "salarioBase",
    "antiguedad"
})
public class ZpeStEmpleadoPrestamos {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "FiniVivienda", required = true)
    protected String finiVivienda;
    @XmlElement(name = "FfinVivienda", required = true)
    protected String ffinVivienda;
    @XmlElement(name = "ImporteVivienda", required = true)
    protected BigDecimal importeVivienda;
    @XmlElement(name = "MaximoAdquisicion", required = true)
    protected BigDecimal maximoAdquisicion;
    @XmlElement(name = "MaximoReforma", required = true)
    protected BigDecimal maximoReforma;
    @XmlElement(name = "FiniVacaciones", required = true)
    protected String finiVacaciones;
    @XmlElement(name = "FfinVacaciones", required = true)
    protected String ffinVacaciones;
    @XmlElement(name = "FiniPrestamoVacaciones", required = true)
    protected String finiPrestamoVacaciones;
    @XmlElement(name = "FfinPrestamoVacaciones", required = true)
    protected String ffinPrestamoVacaciones;
    @XmlElement(name = "SalarioBase", required = true)
    protected BigDecimal salarioBase;
    @XmlElement(name = "Antiguedad", required = true)
    protected BigDecimal antiguedad;

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
     * Obtiene el valor de la propiedad finiVivienda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiniVivienda() {
        return finiVivienda;
    }

    /**
     * Define el valor de la propiedad finiVivienda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiniVivienda(String value) {
        this.finiVivienda = value;
    }

    /**
     * Obtiene el valor de la propiedad ffinVivienda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFfinVivienda() {
        return ffinVivienda;
    }

    /**
     * Define el valor de la propiedad ffinVivienda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFfinVivienda(String value) {
        this.ffinVivienda = value;
    }

    /**
     * Obtiene el valor de la propiedad importeVivienda.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteVivienda() {
        return importeVivienda;
    }

    /**
     * Define el valor de la propiedad importeVivienda.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteVivienda(BigDecimal value) {
        this.importeVivienda = value;
    }

    /**
     * Obtiene el valor de la propiedad maximoAdquisicion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximoAdquisicion() {
        return maximoAdquisicion;
    }

    /**
     * Define el valor de la propiedad maximoAdquisicion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximoAdquisicion(BigDecimal value) {
        this.maximoAdquisicion = value;
    }

    /**
     * Obtiene el valor de la propiedad maximoReforma.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximoReforma() {
        return maximoReforma;
    }

    /**
     * Define el valor de la propiedad maximoReforma.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximoReforma(BigDecimal value) {
        this.maximoReforma = value;
    }

    /**
     * Obtiene el valor de la propiedad finiVacaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiniVacaciones() {
        return finiVacaciones;
    }

    /**
     * Define el valor de la propiedad finiVacaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiniVacaciones(String value) {
        this.finiVacaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad ffinVacaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFfinVacaciones() {
        return ffinVacaciones;
    }

    /**
     * Define el valor de la propiedad ffinVacaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFfinVacaciones(String value) {
        this.ffinVacaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad finiPrestamoVacaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiniPrestamoVacaciones() {
        return finiPrestamoVacaciones;
    }

    /**
     * Define el valor de la propiedad finiPrestamoVacaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiniPrestamoVacaciones(String value) {
        this.finiPrestamoVacaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad ffinPrestamoVacaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFfinPrestamoVacaciones() {
        return ffinPrestamoVacaciones;
    }

    /**
     * Define el valor de la propiedad ffinPrestamoVacaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFfinPrestamoVacaciones(String value) {
        this.ffinPrestamoVacaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad salarioBase.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    /**
     * Define el valor de la propiedad salarioBase.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSalarioBase(BigDecimal value) {
        this.salarioBase = value;
    }

    /**
     * Obtiene el valor de la propiedad antiguedad.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAntiguedad() {
        return antiguedad;
    }

    /**
     * Define el valor de la propiedad antiguedad.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAntiguedad(BigDecimal value) {
        this.antiguedad = value;
    }

}
