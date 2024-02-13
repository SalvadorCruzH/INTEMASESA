
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoHistPercondL complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoHistPercondL"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Objid" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Stext" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoHistPercondL", propOrder = {
    "objid",
    "stext"
})
public class ZpeStEmpleadoHistPercondL {

    @XmlElement(name = "Objid", required = true)
    protected String objid;
    @XmlElement(name = "Stext", required = true)
    protected String stext;

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
     * Obtiene el valor de la propiedad stext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStext() {
        return stext;
    }

    /**
     * Define el valor de la propiedad stext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStext(String value) {
        this.stext = value;
    }

}
