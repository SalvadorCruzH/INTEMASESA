
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStDetecnecformTipos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStDetecnecformTipos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TipoObjid" type="{urn:sap-com:document:sap:rfc:functions}char45"/&gt;
 *         &lt;element name="TipoStext" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="GrupoObjid" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="GrupoStext" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStDetecnecformTipos", propOrder = {
    "tipoObjid",
    "tipoStext",
    "grupoObjid",
    "grupoStext"
})
public class ZpeStDetecnecformTipos {

    @XmlElement(name = "TipoObjid", required = true)
    protected String tipoObjid;
    @XmlElement(name = "TipoStext", required = true)
    protected String tipoStext;
    @XmlElement(name = "GrupoObjid", required = true)
    protected String grupoObjid;
    @XmlElement(name = "GrupoStext", required = true)
    protected String grupoStext;

    /**
     * Obtiene el valor de la propiedad tipoObjid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoObjid() {
        return tipoObjid;
    }

    /**
     * Define el valor de la propiedad tipoObjid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoObjid(String value) {
        this.tipoObjid = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoStext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoStext() {
        return tipoStext;
    }

    /**
     * Define el valor de la propiedad tipoStext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoStext(String value) {
        this.tipoStext = value;
    }

    /**
     * Obtiene el valor de la propiedad grupoObjid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupoObjid() {
        return grupoObjid;
    }

    /**
     * Define el valor de la propiedad grupoObjid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupoObjid(String value) {
        this.grupoObjid = value;
    }

    /**
     * Obtiene el valor de la propiedad grupoStext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupoStext() {
        return grupoStext;
    }

    /**
     * Define el valor de la propiedad grupoStext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupoStext(String value) {
        this.grupoStext = value;
    }

}
