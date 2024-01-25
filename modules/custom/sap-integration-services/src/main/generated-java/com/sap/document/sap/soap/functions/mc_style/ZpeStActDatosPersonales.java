
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStActDatosPersonales complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStActDatosPersonales"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="FechaInicio" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Nombre" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Apellido1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Apellido2" type="{urn:sap-com:document:sap:rfc:functions}char25"/&gt;
 *         &lt;element name="NifE" type="{urn:sap-com:document:sap:rfc:functions}char9"/&gt;
 *         &lt;element name="GeneroId" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="EstadoCivilId" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="NumeroHijos" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/&gt;
 *         &lt;element name="FechaNacimiento" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="PoblacionNacimiento" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="ProvinciaNacimientoId" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="NacionalidadId" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="NroSs" type="{urn:sap-com:document:sap:rfc:functions}numeric12"/&gt;
 *         &lt;element name="Email" type="{urn:sap-com:document:sap:rfc:functions}char241"/&gt;
 *         &lt;element name="ClaseId" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Calle" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Numero" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Portal" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="PisoLetra" type="{urn:sap-com:document:sap:rfc:functions}char6"/&gt;
 *         &lt;element name="CodigoPostal" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Poblacion" type="{urn:sap-com:document:sap:rfc:functions}char25"/&gt;
 *         &lt;element name="ProvinciaId" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="Telefono" type="{urn:sap-com:document:sap:rfc:functions}char14"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStActDatosPersonales", propOrder = {
    "pernr",
    "fechaInicio",
    "nombre",
    "apellido1",
    "apellido2",
    "nifE",
    "generoId",
    "estadoCivilId",
    "numeroHijos",
    "fechaNacimiento",
    "poblacionNacimiento",
    "provinciaNacimientoId",
    "nacionalidadId",
    "nroSs",
    "email",
    "claseId",
    "calle",
    "numero",
    "portal",
    "pisoLetra",
    "codigoPostal",
    "poblacion",
    "provinciaId",
    "telefono"
})
public class ZpeStActDatosPersonales {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "FechaInicio", required = true)
    protected String fechaInicio;
    @XmlElement(name = "Nombre", required = true)
    protected String nombre;
    @XmlElement(name = "Apellido1", required = true)
    protected String apellido1;
    @XmlElement(name = "Apellido2", required = true)
    protected String apellido2;
    @XmlElement(name = "NifE", required = true)
    protected String nifE;
    @XmlElement(name = "GeneroId", required = true)
    protected String generoId;
    @XmlElement(name = "EstadoCivilId", required = true)
    protected String estadoCivilId;
    @XmlElement(name = "NumeroHijos", required = true)
    protected BigDecimal numeroHijos;
    @XmlElement(name = "FechaNacimiento", required = true)
    protected String fechaNacimiento;
    @XmlElement(name = "PoblacionNacimiento", required = true)
    protected String poblacionNacimiento;
    @XmlElement(name = "ProvinciaNacimientoId", required = true)
    protected String provinciaNacimientoId;
    @XmlElement(name = "NacionalidadId", required = true)
    protected String nacionalidadId;
    @XmlElement(name = "NroSs", required = true)
    protected String nroSs;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "ClaseId", required = true)
    protected String claseId;
    @XmlElement(name = "Calle", required = true)
    protected String calle;
    @XmlElement(name = "Numero", required = true)
    protected String numero;
    @XmlElement(name = "Portal", required = true)
    protected String portal;
    @XmlElement(name = "PisoLetra", required = true)
    protected String pisoLetra;
    @XmlElement(name = "CodigoPostal", required = true)
    protected String codigoPostal;
    @XmlElement(name = "Poblacion", required = true)
    protected String poblacion;
    @XmlElement(name = "ProvinciaId", required = true)
    protected String provinciaId;
    @XmlElement(name = "Telefono", required = true)
    protected String telefono;

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
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Define el valor de la propiedad apellido1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido1(String value) {
        this.apellido1 = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Define el valor de la propiedad apellido2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido2(String value) {
        this.apellido2 = value;
    }

    /**
     * Obtiene el valor de la propiedad nifE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNifE() {
        return nifE;
    }

    /**
     * Define el valor de la propiedad nifE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNifE(String value) {
        this.nifE = value;
    }

    /**
     * Obtiene el valor de la propiedad generoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneroId() {
        return generoId;
    }

    /**
     * Define el valor de la propiedad generoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneroId(String value) {
        this.generoId = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoCivilId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCivilId() {
        return estadoCivilId;
    }

    /**
     * Define el valor de la propiedad estadoCivilId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCivilId(String value) {
        this.estadoCivilId = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroHijos.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumeroHijos() {
        return numeroHijos;
    }

    /**
     * Define el valor de la propiedad numeroHijos.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumeroHijos(BigDecimal value) {
        this.numeroHijos = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNacimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Define el valor de la propiedad fechaNacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad poblacionNacimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoblacionNacimiento() {
        return poblacionNacimiento;
    }

    /**
     * Define el valor de la propiedad poblacionNacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoblacionNacimiento(String value) {
        this.poblacionNacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad provinciaNacimientoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaNacimientoId() {
        return provinciaNacimientoId;
    }

    /**
     * Define el valor de la propiedad provinciaNacimientoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaNacimientoId(String value) {
        this.provinciaNacimientoId = value;
    }

    /**
     * Obtiene el valor de la propiedad nacionalidadId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidadId() {
        return nacionalidadId;
    }

    /**
     * Define el valor de la propiedad nacionalidadId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidadId(String value) {
        this.nacionalidadId = value;
    }

    /**
     * Obtiene el valor de la propiedad nroSs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroSs() {
        return nroSs;
    }

    /**
     * Define el valor de la propiedad nroSs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroSs(String value) {
        this.nroSs = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad claseId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseId() {
        return claseId;
    }

    /**
     * Define el valor de la propiedad claseId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseId(String value) {
        this.claseId = value;
    }

    /**
     * Obtiene el valor de la propiedad calle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Define el valor de la propiedad calle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Obtiene el valor de la propiedad numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
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
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Obtiene el valor de la propiedad portal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortal() {
        return portal;
    }

    /**
     * Define el valor de la propiedad portal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortal(String value) {
        this.portal = value;
    }

    /**
     * Obtiene el valor de la propiedad pisoLetra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPisoLetra() {
        return pisoLetra;
    }

    /**
     * Define el valor de la propiedad pisoLetra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPisoLetra(String value) {
        this.pisoLetra = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoPostal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Define el valor de la propiedad codigoPostal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPostal(String value) {
        this.codigoPostal = value;
    }

    /**
     * Obtiene el valor de la propiedad poblacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Define el valor de la propiedad poblacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoblacion(String value) {
        this.poblacion = value;
    }

    /**
     * Obtiene el valor de la propiedad provinciaId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaId() {
        return provinciaId;
    }

    /**
     * Define el valor de la propiedad provinciaId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaId(String value) {
        this.provinciaId = value;
    }

    /**
     * Obtiene el valor de la propiedad telefono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Define el valor de la propiedad telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

}
