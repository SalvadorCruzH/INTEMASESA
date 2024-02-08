
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
 *         &lt;element name="THistContrCategDesglose" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistContrCat"/&gt;
 *         &lt;element name="THistContrCategResumen" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoHistContrCat"/&gt;
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
    "tHistContrCategDesglose",
    "tHistContrCategResumen"
})
@XmlRootElement(name = "ZPeEmpleadoHistContrCategResponse")
public class ZPeEmpleadoHistContrCategResponse {

    @XmlElement(name = "THistContrCategDesglose", required = true)
    protected TableOfZpeStEmpleadoHistContrCat tHistContrCategDesglose;
    @XmlElement(name = "THistContrCategResumen", required = true)
    protected TableOfZpeStEmpleadoHistContrCat tHistContrCategResumen;

    /**
     * Obtiene el valor de la propiedad tHistContrCategDesglose.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistContrCat }
     *     
     */
    public TableOfZpeStEmpleadoHistContrCat getTHistContrCategDesglose() {
        return tHistContrCategDesglose;
    }

    /**
     * Define el valor de la propiedad tHistContrCategDesglose.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistContrCat }
     *     
     */
    public void setTHistContrCategDesglose(TableOfZpeStEmpleadoHistContrCat value) {
        this.tHistContrCategDesglose = value;
    }

    /**
     * Obtiene el valor de la propiedad tHistContrCategResumen.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoHistContrCat }
     *     
     */
    public TableOfZpeStEmpleadoHistContrCat getTHistContrCategResumen() {
        return tHistContrCategResumen;
    }

    /**
     * Define el valor de la propiedad tHistContrCategResumen.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoHistContrCat }
     *     
     */
    public void setTHistContrCategResumen(TableOfZpeStEmpleadoHistContrCat value) {
        this.tHistContrCategResumen = value;
    }

}
