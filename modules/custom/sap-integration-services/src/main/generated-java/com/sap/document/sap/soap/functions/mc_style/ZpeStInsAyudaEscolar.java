
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStInsAyudaEscolar complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStInsAyudaEscolar"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="TipoId" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Numero" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="EstudioId" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/&gt;
 *         &lt;element name="EstudioNivel" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/&gt;
 *         &lt;element name="Centro" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="Comentario" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="FamNumerosa" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="FamMonoparental" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Fecha" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStInsAyudaEscolar", propOrder = {
    "pernr",
    "tipoId",
    "numero",
    "estudioId",
    "estudioNivel",
    "centro",
    "comentario",
    "famNumerosa",
    "famMonoparental",
    "fecha"
})
public class ZpeStInsAyudaEscolar {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "TipoId", required = true)
    protected String tipoId;
    @XmlElement(name = "Numero", required = true)
    protected String numero;
    @XmlElement(name = "EstudioId", required = true)
    protected String estudioId;
    @XmlElement(name = "EstudioNivel", required = true)
    protected String estudioNivel;
    @XmlElement(name = "Centro", required = true)
    protected String centro;
    @XmlElement(name = "Comentario", required = true)
    protected String comentario;
    @XmlElement(name = "FamNumerosa", required = true)
    protected String famNumerosa;
    @XmlElement(name = "FamMonoparental", required = true)
    protected String famMonoparental;
    @XmlElement(name = "Fecha", required = true)
    protected String fecha;

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
     * Obtiene el valor de la propiedad tipoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoId() {
        return tipoId;
    }

    /**
     * Define el valor de la propiedad tipoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoId(String value) {
        this.tipoId = value;
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
     * Obtiene el valor de la propiedad estudioId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstudioId() {
        return estudioId;
    }

    /**
     * Define el valor de la propiedad estudioId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstudioId(String value) {
        this.estudioId = value;
    }

    /**
     * Obtiene el valor de la propiedad estudioNivel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstudioNivel() {
        return estudioNivel;
    }

    /**
     * Define el valor de la propiedad estudioNivel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstudioNivel(String value) {
        this.estudioNivel = value;
    }

    /**
     * Obtiene el valor de la propiedad centro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentro() {
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
    public void setCentro(String value) {
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
    public String getComentario() {
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
    public void setComentario(String value) {
        this.comentario = value;
    }

    /**
     * Obtiene el valor de la propiedad famNumerosa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamNumerosa() {
        return famNumerosa;
    }

    /**
     * Define el valor de la propiedad famNumerosa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamNumerosa(String value) {
        this.famNumerosa = value;
    }

    /**
     * Obtiene el valor de la propiedad famMonoparental.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamMonoparental() {
        return famMonoparental;
    }

    /**
     * Define el valor de la propiedad famMonoparental.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamMonoparental(String value) {
        this.famMonoparental = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

}
