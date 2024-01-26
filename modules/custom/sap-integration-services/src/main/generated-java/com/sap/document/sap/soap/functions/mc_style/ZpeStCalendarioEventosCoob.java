
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStCalendarioEventosCoob complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStCalendarioEventosCoob"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EventoId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="TipoLiteral" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Tabseqnr" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/&gt;
 *         &lt;element name="Literal" type="{urn:sap-com:document:sap:rfc:functions}char132"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStCalendarioEventosCoob", propOrder = {
    "eventoId",
    "tipoLiteral",
    "tabseqnr",
    "literal"
})
public class ZpeStCalendarioEventosCoob {

    @XmlElement(name = "EventoId", required = true)
    protected String eventoId;
    @XmlElement(name = "TipoLiteral", required = true)
    protected String tipoLiteral;
    @XmlElement(name = "Tabseqnr", required = true)
    protected String tabseqnr;
    @XmlElement(name = "Literal", required = true)
    protected String literal;

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
     * Obtiene el valor de la propiedad tipoLiteral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoLiteral() {
        return tipoLiteral;
    }

    /**
     * Define el valor de la propiedad tipoLiteral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoLiteral(String value) {
        this.tipoLiteral = value;
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
     * Obtiene el valor de la propiedad literal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Define el valor de la propiedad literal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiteral(String value) {
        this.literal = value;
    }

}
