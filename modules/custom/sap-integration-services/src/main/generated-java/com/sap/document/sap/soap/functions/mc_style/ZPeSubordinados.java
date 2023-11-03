
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
 *         &lt;element name="DirectosTodos" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
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
    "directosTodos",
    "pernr"
})
@XmlRootElement(name = "ZPeSubordinados")
public class ZPeSubordinados {

    @XmlElement(name = "DirectosTodos", required = true)
    protected String directosTodos;
    @XmlElement(name = "Pernr", required = true)
    protected String pernr;

    /**
     * Obtiene el valor de la propiedad directosTodos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectosTodos() {
        return directosTodos;
    }

    /**
     * Define el valor de la propiedad directosTodos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectosTodos(String value) {
        this.directosTodos = value;
    }

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

}
