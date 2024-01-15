
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
 *         &lt;element name="TContenido" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStConteniObjetiEventos"/&gt;
 *         &lt;element name="TEventos" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStCalendarioEventos"/&gt;
 *         &lt;element name="TObjetivo" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStConteniObjetiEventos"/&gt;
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
    "tContenido",
    "tEventos",
    "tObjetivo"
})
@XmlRootElement(name = "ZPeCalendarioEventosResponse")
public class ZPeCalendarioEventosResponse {

    @XmlElement(name = "TContenido", required = true)
    protected TableOfZpeStConteniObjetiEventos tContenido;
    @XmlElement(name = "TEventos", required = true)
    protected TableOfZpeStCalendarioEventos tEventos;
    @XmlElement(name = "TObjetivo", required = true)
    protected TableOfZpeStConteniObjetiEventos tObjetivo;

    /**
     * Obtiene el valor de la propiedad tContenido.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStConteniObjetiEventos }
     *     
     */
    public TableOfZpeStConteniObjetiEventos getTContenido() {
        return tContenido;
    }

    /**
     * Define el valor de la propiedad tContenido.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStConteniObjetiEventos }
     *     
     */
    public void setTContenido(TableOfZpeStConteniObjetiEventos value) {
        this.tContenido = value;
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
     *     {@link TableOfZpeStConteniObjetiEventos }
     *     
     */
    public TableOfZpeStConteniObjetiEventos getTObjetivo() {
        return tObjetivo;
    }

    /**
     * Define el valor de la propiedad tObjetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStConteniObjetiEventos }
     *     
     */
    public void setTObjetivo(TableOfZpeStConteniObjetiEventos value) {
        this.tObjetivo = value;
    }

}
