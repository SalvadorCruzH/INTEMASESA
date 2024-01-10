
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
 *         &lt;element name="PlanesFormacion" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZhrPlanesForma"/&gt;
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapireturn1"/&gt;
 *         &lt;element name="SolicitudesCons" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZhrEDetnecform"/&gt;
 *         &lt;element name="TiposEventoPlanformacion" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStDetecnecformTipos"/&gt;
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
    "planesFormacion",
    "_return",
    "solicitudesCons",
    "tiposEventoPlanformacion"
})
@XmlRootElement(name = "ZPeDetecnecformResponse")
public class ZPeDetecnecformResponse {

    @XmlElement(name = "PlanesFormacion", required = true)
    protected TableOfZhrPlanesForma planesFormacion;
    @XmlElement(name = "Return", required = true)
    protected Bapireturn1 _return;
    @XmlElement(name = "SolicitudesCons", required = true)
    protected TableOfZhrEDetnecform solicitudesCons;
    @XmlElement(name = "TiposEventoPlanformacion", required = true)
    protected TableOfZpeStDetecnecformTipos tiposEventoPlanformacion;

    /**
     * Obtiene el valor de la propiedad planesFormacion.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZhrPlanesForma }
     *     
     */
    public TableOfZhrPlanesForma getPlanesFormacion() {
        return planesFormacion;
    }

    /**
     * Define el valor de la propiedad planesFormacion.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZhrPlanesForma }
     *     
     */
    public void setPlanesFormacion(TableOfZhrPlanesForma value) {
        this.planesFormacion = value;
    }

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
     * Obtiene el valor de la propiedad solicitudesCons.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZhrEDetnecform }
     *     
     */
    public TableOfZhrEDetnecform getSolicitudesCons() {
        return solicitudesCons;
    }

    /**
     * Define el valor de la propiedad solicitudesCons.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZhrEDetnecform }
     *     
     */
    public void setSolicitudesCons(TableOfZhrEDetnecform value) {
        this.solicitudesCons = value;
    }

    /**
     * Obtiene el valor de la propiedad tiposEventoPlanformacion.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStDetecnecformTipos }
     *     
     */
    public TableOfZpeStDetecnecformTipos getTiposEventoPlanformacion() {
        return tiposEventoPlanformacion;
    }

    /**
     * Define el valor de la propiedad tiposEventoPlanformacion.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStDetecnecformTipos }
     *     
     */
    public void setTiposEventoPlanformacion(TableOfZpeStDetecnecformTipos value) {
        this.tiposEventoPlanformacion = value;
    }

}
