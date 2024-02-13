
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
 *         &lt;element name="THistPermisosConducir" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistPercond"/&gt;
 *         &lt;element name="TPermisosConducir" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistPercondL"/&gt;
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
    "tHistPermisosConducir",
    "tPermisosConducir"
})
@XmlRootElement(name = "ZPeEmpleadoHistPerconduResponse")
public class ZPeEmpleadoHistPerconduResponse {

    @XmlElement(name = "THistPermisosConducir", required = true)
    protected TableOfZpeStEmpleadoHistPercond tHistPermisosConducir;
    @XmlElement(name = "TPermisosConducir", required = true)
    protected TableOfZpeStEmpleadoHistPercondL tPermisosConducir;

    /**
     * Obtiene el valor de la propiedad tHistPermisosConducir.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistPercond }
     *     
     */
    public TableOfZpeStEmpleadoHistPercond getTHistPermisosConducir() {
        return tHistPermisosConducir;
    }

    /**
     * Define el valor de la propiedad tHistPermisosConducir.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistPercond }
     *     
     */
    public void setTHistPermisosConducir(TableOfZpeStEmpleadoHistPercond value) {
        this.tHistPermisosConducir = value;
    }

    /**
     * Obtiene el valor de la propiedad tPermisosConducir.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistPercondL }
     *     
     */
    public TableOfZpeStEmpleadoHistPercondL getTPermisosConducir() {
        return tPermisosConducir;
    }

    /**
     * Define el valor de la propiedad tPermisosConducir.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistPercondL }
     *     
     */
    public void setTPermisosConducir(TableOfZpeStEmpleadoHistPercondL value) {
        this.tPermisosConducir = value;
    }

}
