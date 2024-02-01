
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoHistform complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoHistform"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Inicio" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Objid" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Tabseqnr" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/&gt;
 *         &lt;element name="Curso" type="{urn:sap-com:document:sap:soap:functions:mc-style}char80"/&gt;
 *         &lt;element name="Fin" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Duracion" type="{urn:sap-com:document:sap:rfc:functions}decimal8.2"/&gt;
 *         &lt;element name="Titulo" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoHistform", propOrder = {
    "inicio",
    "objid",
    "tabseqnr",
    "curso",
    "fin",
    "duracion",
    "titulo"
})
public class ZpeStEmpleadoHistform {

    @XmlElement(name = "Inicio", required = true)
    protected String inicio;
    @XmlElement(name = "Objid", required = true)
    protected String objid;
    @XmlElement(name = "Tabseqnr", required = true)
    protected String tabseqnr;
    @XmlElement(name = "Curso", required = true)
    protected String curso;
    @XmlElement(name = "Fin", required = true)
    protected String fin;
    @XmlElement(name = "Duracion", required = true)
    protected BigDecimal duracion;
    @XmlElement(name = "Titulo", required = true)
    protected String titulo;

    /**
     * Obtiene el valor de la propiedad inicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * Define el valor de la propiedad inicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInicio(String value) {
        this.inicio = value;
    }

    /**
     * Obtiene el valor de la propiedad objid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjid() {
        return objid;
    }

    /**
     * Define el valor de la propiedad objid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjid(String value) {
        this.objid = value;
    }

    /**
     * Obtiene el valor de la propiedad tabseqnr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabseqnr() {
        return tabseqnr;
    }

    /**
     * Define el valor de la propiedad tabseqnr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabseqnr(String value) {
        this.tabseqnr = value;
    }

    /**
     * Obtiene el valor de la propiedad curso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Define el valor de la propiedad curso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurso(String value) {
        this.curso = value;
    }

    /**
     * Obtiene el valor de la propiedad fin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFin() {
        return fin;
    }

    /**
     * Define el valor de la propiedad fin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFin(String value) {
        this.fin = value;
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
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

}
