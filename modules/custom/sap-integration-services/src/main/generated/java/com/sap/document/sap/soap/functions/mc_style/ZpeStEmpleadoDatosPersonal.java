
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoDatosPersonal complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoDatosPersonal"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Nombre" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Apellido1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Apellido2" type="{urn:sap-com:document:sap:rfc:functions}char25"/&gt;
 *         &lt;element name="NifE" type="{urn:sap-com:document:sap:rfc:functions}char9"/&gt;
 *         &lt;element name="GeneroId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="GeneroDesc" type="{urn:sap-com:document:sap:soap:functions:mc-style}char60"/&gt;
 *         &lt;element name="EstadoCivilId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="EstadoCivilDesc" type="{urn:sap-com:document:sap:soap:functions:mc-style}char6"/&gt;
 *         &lt;element name="NumeroHijos" type="{urn:sap-com:document:sap:soap:functions:mc-style}decimal3.0"/&gt;
 *         &lt;element name="FechaNacimiento" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="PoblacionNacimiento" type="{urn:sap-com:document:sap:soap:functions:mc-style}char40"/&gt;
 *         &lt;element name="ProvinciaNacimientoId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char3"/&gt;
 *         &lt;element name="ProvinciaNacimientoDesc" type="{urn:sap-com:document:sap:soap:functions:mc-style}char20"/&gt;
 *         &lt;element name="NacionalidadId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char3"/&gt;
 *         &lt;element name="NacionalidadDesc" type="{urn:sap-com:document:sap:soap:functions:mc-style}char15"/&gt;
 *         &lt;element name="NroSs" type="{urn:sap-com:document:sap:rfc:functions}numeric12"/&gt;
 *         &lt;element name="Usuario" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Email" type="{urn:sap-com:document:sap:rfc:functions}char241"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoDatosPersonal", propOrder = {
    "pernr",
    "nombre",
    "apellido1",
    "apellido2",
    "nifE",
    "generoId",
    "generoDesc",
    "estadoCivilId",
    "estadoCivilDesc",
    "numeroHijos",
    "fechaNacimiento",
    "poblacionNacimiento",
    "provinciaNacimientoId",
    "provinciaNacimientoDesc",
    "nacionalidadId",
    "nacionalidadDesc",
    "nroSs",
    "usuario",
    "email"
})
public class ZpeStEmpleadoDatosPersonal {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
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
    @XmlElement(name = "GeneroDesc", required = true)
    protected String generoDesc;
    @XmlElement(name = "EstadoCivilId", required = true)
    protected String estadoCivilId;
    @XmlElement(name = "EstadoCivilDesc", required = true)
    protected String estadoCivilDesc;
    @XmlElement(name = "NumeroHijos", required = true)
    protected BigDecimal numeroHijos;
    @XmlElement(name = "FechaNacimiento", required = true)
    protected String fechaNacimiento;
    @XmlElement(name = "PoblacionNacimiento", required = true)
    protected String poblacionNacimiento;
    @XmlElement(name = "ProvinciaNacimientoId", required = true)
    protected String provinciaNacimientoId;
    @XmlElement(name = "ProvinciaNacimientoDesc", required = true)
    protected String provinciaNacimientoDesc;
    @XmlElement(name = "NacionalidadId", required = true)
    protected String nacionalidadId;
    @XmlElement(name = "NacionalidadDesc", required = true)
    protected String nacionalidadDesc;
    @XmlElement(name = "NroSs", required = true)
    protected String nroSs;
    @XmlElement(name = "Usuario", required = true)
    protected String usuario;
    @XmlElement(name = "Email", required = true)
    protected String email;

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
     * Obtiene el valor de la propiedad generoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneroDesc() {
        return generoDesc;
    }

    /**
     * Define el valor de la propiedad generoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneroDesc(String value) {
        this.generoDesc = value;
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
     * Obtiene el valor de la propiedad estadoCivilDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCivilDesc() {
        return estadoCivilDesc;
    }

    /**
     * Define el valor de la propiedad estadoCivilDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCivilDesc(String value) {
        this.estadoCivilDesc = value;
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
     * Obtiene el valor de la propiedad provinciaNacimientoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaNacimientoDesc() {
        return provinciaNacimientoDesc;
    }

    /**
     * Define el valor de la propiedad provinciaNacimientoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaNacimientoDesc(String value) {
        this.provinciaNacimientoDesc = value;
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
     * Obtiene el valor de la propiedad nacionalidadDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidadDesc() {
        return nacionalidadDesc;
    }

    /**
     * Define el valor de la propiedad nacionalidadDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidadDesc(String value) {
        this.nacionalidadDesc = value;
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
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
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

}
