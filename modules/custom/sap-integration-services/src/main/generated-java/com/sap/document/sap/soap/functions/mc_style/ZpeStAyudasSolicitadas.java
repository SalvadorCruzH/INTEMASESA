
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStAyudasSolicitadas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStAyudasSolicitadas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="TipoId" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="TipoDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Numero" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Nombre" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Apellido1" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Apellido2" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="EstudioDesc" type="{urn:sap-com:document:sap:rfc:functions}char200"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStAyudasSolicitadas", propOrder = {
    "pernr",
    "tipoId",
    "tipoDesc",
    "numero",
    "nombre",
    "apellido1",
    "apellido2",
    "estudioDesc"
})
public class ZpeStAyudasSolicitadas {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "TipoId", required = true)
    protected String tipoId;
    @XmlElement(name = "TipoDesc", required = true)
    protected String tipoDesc;
    @XmlElement(name = "Numero", required = true)
    protected String numero;
    @XmlElement(name = "Nombre", required = true)
    protected String nombre;
    @XmlElement(name = "Apellido1", required = true)
    protected String apellido1;
    @XmlElement(name = "Apellido2", required = true)
    protected String apellido2;
    @XmlElement(name = "EstudioDesc", required = true)
    protected String estudioDesc;

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
     * Obtiene el valor de la propiedad tipoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDesc() {
        return tipoDesc;
    }

    /**
     * Define el valor de la propiedad tipoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDesc(String value) {
        this.tipoDesc = value;
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
     * Obtiene el valor de la propiedad estudioDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstudioDesc() {
        return estudioDesc;
    }

    /**
     * Define el valor de la propiedad estudioDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstudioDesc(String value) {
        this.estudioDesc = value;
    }

}
