
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
 *         &lt;element name="ToProcesos" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStProcesoSeleccionLista"/&gt;
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
    "toProcesos"
})
@XmlRootElement(name = "ZPeProcesosSeleccionListaResponse")
public class ZPeProcesosSeleccionListaResponse {

    @XmlElement(name = "ToProcesos", required = true)
    protected TableOfZpeStProcesoSeleccionLista toProcesos;

    /**
     * Obtiene el valor de la propiedad toProcesos.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStProcesoSeleccionLista }
     *     
     */
    public TableOfZpeStProcesoSeleccionLista getToProcesos() {
        return toProcesos;
    }

    /**
     * Define el valor de la propiedad toProcesos.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStProcesoSeleccionLista }
     *     
     */
    public void setToProcesos(TableOfZpeStProcesoSeleccionLista value) {
        this.toProcesos = value;
    }

}
