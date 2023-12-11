
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
 *         &lt;element name="TCentrosDistancias" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStCentrosDistancias"/&gt;
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
    "tCentrosDistancias"
})
@XmlRootElement(name = "ZPeCentrosDistanciasResponse")
public class ZPeCentrosDistanciasResponse {

    @XmlElement(name = "TCentrosDistancias", required = true)
    protected TableOfZpeStCentrosDistancias tCentrosDistancias;

    /**
     * Obtiene el valor de la propiedad tCentrosDistancias.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStCentrosDistancias }
     *     
     */
    public TableOfZpeStCentrosDistancias getTCentrosDistancias() {
        return tCentrosDistancias;
    }

    /**
     * Define el valor de la propiedad tCentrosDistancias.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStCentrosDistancias }
     *     
     */
    public void setTCentrosDistancias(TableOfZpeStCentrosDistancias value) {
        this.tCentrosDistancias = value;
    }

}
