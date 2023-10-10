
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoDomicilio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoDomicilio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="ClaseId" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="ClaseDesc" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Calle" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Numero" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Portal" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="PisoLetra" type="{urn:sap-com:document:sap:rfc:functions}char6"/&gt;
 *         &lt;element name="CodigoPostal" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Poblacion" type="{urn:sap-com:document:sap:rfc:functions}char25"/&gt;
 *         &lt;element name="ProvinciaId" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="ProvinciaDesc" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
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
@XmlType(name = "ZpeStEmpleadoDomicilio", propOrder = {
    "pernr",
    "claseId",
    "claseDesc",
    "calle",
    "numero",
    "portal",
    "pisoLetra",
    "codigoPostal",
    "poblacion",
    "provinciaId",
    "provinciaDesc",
    "telefono"
})
public class ZpeStEmpleadoDomicilio {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "ClaseId", required = true)
    protected String claseId;
    @XmlElement(name = "ClaseDesc", required = true)
    protected String claseDesc;
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
    @XmlElement(name = "ProvinciaDesc", required = true)
    protected String provinciaDesc;
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
     * Obtiene el valor de la propiedad claseDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseDesc() {
        return claseDesc;
    }

    /**
     * Define el valor de la propiedad claseDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseDesc(String value) {
        this.claseDesc = value;
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
     * Obtiene el valor de la propiedad provinciaDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaDesc() {
        return provinciaDesc;
    }

    /**
     * Define el valor de la propiedad provinciaDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaDesc(String value) {
        this.provinciaDesc = value;
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
