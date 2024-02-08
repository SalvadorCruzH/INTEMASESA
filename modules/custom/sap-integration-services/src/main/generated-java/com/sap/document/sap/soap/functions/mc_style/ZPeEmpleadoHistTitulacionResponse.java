
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
 *         &lt;element name="THistTitulacion" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistTitulacio"/&gt;
 *         &lt;element name="TNiveles" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistTitulNiv"/&gt;
 *         &lt;element name="TTitulos" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistTitulTit"/&gt;
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
    "tHistTitulacion",
    "tNiveles",
    "tTitulos"
})
@XmlRootElement(name = "ZPeEmpleadoHistTitulacionResponse")
public class ZPeEmpleadoHistTitulacionResponse {

    @XmlElement(name = "THistTitulacion", required = true)
    protected TableOfZpeStEmpleadoHistTitulacio tHistTitulacion;
    @XmlElement(name = "TNiveles", required = true)
    protected TableOfZpeStEmpleadoHistTitulNiv tNiveles;
    @XmlElement(name = "TTitulos", required = true)
    protected TableOfZpeStEmpleadoHistTitulTit tTitulos;

    /**
     * Obtiene el valor de la propiedad tHistTitulacion.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistTitulacio }
     *     
     */
    public TableOfZpeStEmpleadoHistTitulacio getTHistTitulacion() {
        return tHistTitulacion;
    }

    /**
     * Define el valor de la propiedad tHistTitulacion.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistTitulacio }
     *     
     */
    public void setTHistTitulacion(TableOfZpeStEmpleadoHistTitulacio value) {
        this.tHistTitulacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tNiveles.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistTitulNiv }
     *     
     */
    public TableOfZpeStEmpleadoHistTitulNiv getTNiveles() {
        return tNiveles;
    }

    /**
     * Define el valor de la propiedad tNiveles.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistTitulNiv }
     *     
     */
    public void setTNiveles(TableOfZpeStEmpleadoHistTitulNiv value) {
        this.tNiveles = value;
    }

    /**
     * Obtiene el valor de la propiedad tTitulos.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistTitulTit }
     *     
     */
    public TableOfZpeStEmpleadoHistTitulTit getTTitulos() {
        return tTitulos;
    }

    /**
     * Define el valor de la propiedad tTitulos.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistTitulTit }
     *     
     */
    public void setTTitulos(TableOfZpeStEmpleadoHistTitulTit value) {
        this.tTitulos = value;
    }

}
