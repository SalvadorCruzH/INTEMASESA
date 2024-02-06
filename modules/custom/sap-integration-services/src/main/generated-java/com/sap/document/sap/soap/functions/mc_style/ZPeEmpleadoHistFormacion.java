
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
 *         &lt;element name="EventoId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8" minOccurs="0"/&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
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
    "eventoId",
    "pernr"
})
@XmlRootElement(name = "ZPeEmpleadoHistFormacion")
public class ZPeEmpleadoHistFormacion {

    @XmlElement(name = "EventoId")
    protected String eventoId;
    @XmlElement(name = "Pernr", required = true)
    protected String pernr;

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
