
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
 *         &lt;element name="ImTipo" type="{urn:sap-com:document:sap:rfc:functions}numeric2" minOccurs="0"/&gt;
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
    "imTipo"
})
@XmlRootElement(name = "ZPeProcesosSeleccionLista")
public class ZPeProcesosSeleccionLista {

    @XmlElement(name = "ImTipo")
    protected String imTipo;

    /**
     * Obtiene el valor de la propiedad imTipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImTipo() {
        return imTipo;
    }

    /**
     * Define el valor de la propiedad imTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImTipo(String value) {
        this.imTipo = value;
    }

}
