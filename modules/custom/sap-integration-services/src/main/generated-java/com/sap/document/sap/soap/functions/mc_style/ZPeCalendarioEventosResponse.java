
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
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapireturn1"/&gt;
 *         &lt;element name="TContenido" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStCalendarioEventosCoob"/&gt;
 *         &lt;element name="TDocumentos" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStCalendarioEventosDocu"/&gt;
 *         &lt;element name="TEventos" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStCalendarioEventos"/&gt;
 *         &lt;element name="TObjetivo" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStCalendarioEventosCoob"/&gt;
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
    "_return",
    "tContenido",
    "tDocumentos",
    "tEventos",
    "tObjetivo"
})
@XmlRootElement(name = "ZPeCalendarioEventosResponse")
public class ZPeCalendarioEventosResponse {

    @XmlElement(name = "Return", required = true)
    protected Bapireturn1 _return;
    @XmlElement(name = "TContenido", required = true)
    protected TableOfZpeStCalendarioEventosCoob tContenido;
    @XmlElement(name = "TDocumentos", required = true)
    protected TableOfZpeStCalendarioEventosDocu tDocumentos;
    @XmlElement(name = "TEventos", required = true)
    protected TableOfZpeStCalendarioEventos tEventos;
    @XmlElement(name = "TObjetivo", required = true)
    protected TableOfZpeStCalendarioEventosCoob tObjetivo;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link Bapireturn1 }
     *     
     */
    public Bapireturn1 getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapireturn1 }
     *     
     */
    public void setReturn(Bapireturn1 value) {
        this._return = value;
    }

    /**
     * Obtiene el valor de la propiedad tContenido.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStCalendarioEventosCoob }
     *     
     */
    public TableOfZpeStCalendarioEventosCoob getTContenido() {
        return tContenido;
    }

    /**
     * Define el valor de la propiedad tContenido.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStCalendarioEventosCoob }
     *     
     */
    public void setTContenido(TableOfZpeStCalendarioEventosCoob value) {
        this.tContenido = value;
    }

    /**
     * Obtiene el valor de la propiedad tDocumentos.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStCalendarioEventosDocu }
     *     
     */
    public TableOfZpeStCalendarioEventosDocu getTDocumentos() {
        return tDocumentos;
    }

    /**
     * Define el valor de la propiedad tDocumentos.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStCalendarioEventosDocu }
     *     
     */
    public void setTDocumentos(TableOfZpeStCalendarioEventosDocu value) {
        this.tDocumentos = value;
    }

    /**
     * Obtiene el valor de la propiedad tEventos.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStCalendarioEventos }
     *     
     */
    public TableOfZpeStCalendarioEventos getTEventos() {
        return tEventos;
    }

    /**
     * Define el valor de la propiedad tEventos.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStCalendarioEventos }
     *     
     */
    public void setTEventos(TableOfZpeStCalendarioEventos value) {
        this.tEventos = value;
    }

    /**
     * Obtiene el valor de la propiedad tObjetivo.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStCalendarioEventosCoob }
     *     
     */
    public TableOfZpeStCalendarioEventosCoob getTObjetivo() {
        return tObjetivo;
    }

    /**
     * Define el valor de la propiedad tObjetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStCalendarioEventosCoob }
     *     
     */
    public void setTObjetivo(TableOfZpeStCalendarioEventosCoob value) {
        this.tObjetivo = value;
    }

}
