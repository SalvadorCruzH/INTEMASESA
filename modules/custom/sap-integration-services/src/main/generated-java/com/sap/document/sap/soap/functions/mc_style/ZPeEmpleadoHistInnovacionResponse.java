
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
 *         &lt;element name="THistInnovacion" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistInnovacio"/&gt;
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
    "tHistInnovacion"
})
@XmlRootElement(name = "ZPeEmpleadoHistInnovacionResponse")
public class ZPeEmpleadoHistInnovacionResponse {

    @XmlElement(name = "THistInnovacion", required = true)
    protected TableOfZpeStEmpleadoHistInnovacio tHistInnovacion;

    /**
     * Obtiene el valor de la propiedad tHistInnovacion.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistInnovacio }
     *     
     */
    public TableOfZpeStEmpleadoHistInnovacio getTHistInnovacion() {
        return tHistInnovacion;
    }

    /**
     * Define el valor de la propiedad tHistInnovacion.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistInnovacio }
     *     
     */
    public void setTHistInnovacion(TableOfZpeStEmpleadoHistInnovacio value) {
        this.tHistInnovacion = value;
    }

}
