
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStCalendarioEventos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStCalendarioEventos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EventoId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="EventoShort" type="{urn:sap-com:document:sap:rfc:functions}char12"/&gt;
 *         &lt;element name="Begda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Endda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Duracion" type="{urn:sap-com:document:sap:rfc:functions}decimal8.2"/&gt;
 *         &lt;element name="EventoDesc" type="{urn:sap-com:document:sap:rfc:functions}char255"/&gt;
 *         &lt;element name="Sede" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Modalidad" type="{urn:sap-com:document:sap:soap:functions:mc-style}char16"/&gt;
 *         &lt;element name="Convocatoria" type="{urn:sap-com:document:sap:soap:functions:mc-style}char16"/&gt;
 *         &lt;element name="Ofertado" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="AsisteElEmpleado" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStCalendarioEventos", propOrder = {
    "eventoId",
    "eventoShort",
    "begda",
    "endda",
    "duracion",
    "eventoDesc",
    "sede",
    "modalidad",
    "convocatoria",
    "ofertado",
    "asisteElEmpleado"
})
public class ZpeStCalendarioEventos {

    @XmlElement(name = "EventoId", required = true)
    protected String eventoId;
    @XmlElement(name = "EventoShort", required = true)
    protected String eventoShort;
    @XmlElement(name = "Begda", required = true)
    protected String begda;
    @XmlElement(name = "Endda", required = true)
    protected String endda;
    @XmlElement(name = "Duracion", required = true)
    protected BigDecimal duracion;
    @XmlElement(name = "EventoDesc", required = true)
    protected String eventoDesc;
    @XmlElement(name = "Sede", required = true)
    protected String sede;
    @XmlElement(name = "Modalidad", required = true)
    protected String modalidad;
    @XmlElement(name = "Convocatoria", required = true)
    protected String convocatoria;
    @XmlElement(name = "Ofertado", required = true)
    protected String ofertado;
    @XmlElement(name = "AsisteElEmpleado", required = true)
    protected String asisteElEmpleado;

    /**
     * Obtiene el valor de la propiedad eventoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventoId() {
        return eventoId;
    }

    /**
     * Define el valor de la propiedad eventoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventoId(String value) {
        this.eventoId = value;
    }

    /**
     * Obtiene el valor de la propiedad eventoShort.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventoShort() {
        return eventoShort;
    }

    /**
     * Define el valor de la propiedad eventoShort.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventoShort(String value) {
        this.eventoShort = value;
    }

    /**
     * Obtiene el valor de la propiedad begda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBegda() {
        return begda;
    }

    /**
     * Define el valor de la propiedad begda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBegda(String value) {
        this.begda = value;
    }

    /**
     * Obtiene el valor de la propiedad endda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndda() {
        return endda;
    }

    /**
     * Define el valor de la propiedad endda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndda(String value) {
        this.endda = value;
    }

    /**
     * Obtiene el valor de la propiedad duracion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDuracion() {
        return duracion;
    }

    /**
     * Define el valor de la propiedad duracion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDuracion(BigDecimal value) {
        this.duracion = value;
    }

    /**
     * Obtiene el valor de la propiedad eventoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventoDesc() {
        return eventoDesc;
    }

    /**
     * Define el valor de la propiedad eventoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventoDesc(String value) {
        this.eventoDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad sede.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSede() {
        return sede;
    }

    /**
     * Define el valor de la propiedad sede.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSede(String value) {
        this.sede = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Define el valor de la propiedad modalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalidad(String value) {
        this.modalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad convocatoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConvocatoria() {
        return convocatoria;
    }

    /**
     * Define el valor de la propiedad convocatoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConvocatoria(String value) {
        this.convocatoria = value;
    }

    /**
     * Obtiene el valor de la propiedad ofertado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfertado() {
        return ofertado;
    }

    /**
     * Define el valor de la propiedad ofertado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfertado(String value) {
        this.ofertado = value;
    }

    /**
     * Obtiene el valor de la propiedad asisteElEmpleado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsisteElEmpleado() {
        return asisteElEmpleado;
    }

    /**
     * Define el valor de la propiedad asisteElEmpleado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsisteElEmpleado(String value) {
        this.asisteElEmpleado = value;
    }

}
