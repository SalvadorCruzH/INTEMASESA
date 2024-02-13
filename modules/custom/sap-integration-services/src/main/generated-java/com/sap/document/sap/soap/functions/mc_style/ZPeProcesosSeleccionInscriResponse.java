
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
 *         &lt;element name="ExNumCand" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="ExReturn" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapireturn1"/&gt;
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
    "exNumCand",
    "exReturn"
})
@XmlRootElement(name = "ZPeProcesosSeleccionInscriResponse")
public class ZPeProcesosSeleccionInscriResponse {

    @XmlElement(name = "ExNumCand", required = true)
    protected String exNumCand;
    @XmlElement(name = "ExReturn", required = true)
    protected Bapireturn1 exReturn;

    /**
     * Obtiene el valor de la propiedad exNumCand.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExNumCand() {
        return exNumCand;
    }

    /**
     * Define el valor de la propiedad exNumCand.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExNumCand(String value) {
        this.exNumCand = value;
    }

    /**
     * Obtiene el valor de la propiedad exReturn.
     * 
     * @return
     *     possible object is
     *     {@link Bapireturn1 }
     *     
     */
    public Bapireturn1 getExReturn() {
        return exReturn;
    }

    /**
     * Define el valor de la propiedad exReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapireturn1 }
     *     
     */
    public void setExReturn(Bapireturn1 value) {
        this.exReturn = value;
    }

}
