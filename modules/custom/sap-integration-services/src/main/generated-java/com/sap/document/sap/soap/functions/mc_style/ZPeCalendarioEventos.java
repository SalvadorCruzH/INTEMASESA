
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FechaDesde" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10"/&gt;
 *         &lt;element name="FechaHasta" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10"/&gt;
 *         &lt;element name="IdEmpleado" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8" minOccurs="0"/&gt;
 *         &lt;element name="InscripcionAccion" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1" minOccurs="0"/&gt;
 *         &lt;element name="InscripcionEvento" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fechaDesde",
    "fechaHasta",
    "idEmpleado",
    "inscripcionAccion",
    "inscripcionEvento"
})
@XmlRootElement(name = "ZPeCalendarioEventos")
public class ZPeCalendarioEventos {

    @XmlElement(name = "FechaDesde", required = true)
    protected String fechaDesde;
    @XmlElement(name = "FechaHasta", required = true)
    protected String fechaHasta;
    @XmlElement(name = "IdEmpleado")
    protected String idEmpleado;
    @XmlElement(name = "InscripcionAccion")
    protected String inscripcionAccion;
    @XmlElement(name = "InscripcionEvento")
    protected String inscripcionEvento;

    /**
     * Obtiene el valor de la propiedad fechaDesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Define el valor de la propiedad fechaDesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDesde(String value) {
        this.fechaDesde = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHasta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Define el valor de la propiedad fechaHasta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHasta(String value) {
        this.fechaHasta = value;
    }

    /**
     * Obtiene el valor de la propiedad idEmpleado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Define el valor de la propiedad idEmpleado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEmpleado(String value) {
        this.idEmpleado = value;
    }

    /**
     * Obtiene el valor de la propiedad inscripcionAccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInscripcionAccion() {
        return inscripcionAccion;
    }

    /**
     * Define el valor de la propiedad inscripcionAccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInscripcionAccion(String value) {
        this.inscripcionAccion = value;
    }

    /**
     * Obtiene el valor de la propiedad inscripcionEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInscripcionEvento() {
        return inscripcionEvento;
    }

    /**
     * Define el valor de la propiedad inscripcionEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInscripcionEvento(String value) {
        this.inscripcionEvento = value;
    }

}
