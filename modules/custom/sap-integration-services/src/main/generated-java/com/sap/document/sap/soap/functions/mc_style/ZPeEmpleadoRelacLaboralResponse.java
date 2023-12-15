
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
 *         &lt;element name="TEmpleados" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoRelacLaboral"/&gt;
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
    "tEmpleados"
})
@XmlRootElement(name = "ZPeEmpleadoRelacLaboralResponse")
public class ZPeEmpleadoRelacLaboralResponse {

    @XmlElement(name = "TEmpleados", required = true)
    protected TableOfZpeStEmpleadoRelacLaboral tEmpleados;

    /**
     * Obtiene el valor de la propiedad tEmpleados.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoRelacLaboral }
     *     
     */
    public TableOfZpeStEmpleadoRelacLaboral getTEmpleados() {
        return tEmpleados;
    }

    /**
     * Define el valor de la propiedad tEmpleados.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoRelacLaboral }
     *     
     */
    public void setTEmpleados(TableOfZpeStEmpleadoRelacLaboral value) {
        this.tEmpleados = value;
    }

}
