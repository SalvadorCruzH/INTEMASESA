
package com.sap.document.sap.soap.functions.mc_style;

import com.sap.document.sap.rfc.functions.ZPESTINSAYUDAESCOLAR;
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
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="TInsAyudaEscolar" type="{urn:sap-com:document:sap:rfc:functions}ZPE_ST_INS_AYUDA_ESCOLAR"/&gt;
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
    "pernr",
    "tInsAyudaEscolar"
})
@XmlRootElement(name = "ZPeAyudaEscolar")
public class ZPeAyudaEscolar {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "TInsAyudaEscolar", required = true)
    protected ZPESTINSAYUDAESCOLAR tInsAyudaEscolar;

    /**
     * Obtiene el valor de la propiedad pernr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPernr() {
        return pernr;
    }

    /**
     * Define el valor de la propiedad pernr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPernr(String value) {
        this.pernr = value;
    }

    /**
     * Obtiene el valor de la propiedad tInsAyudaEscolar.
     * 
     * @return
     *     possible object is
     *     {@link ZPESTINSAYUDAESCOLAR }
     *     
     */
    public ZPESTINSAYUDAESCOLAR getTInsAyudaEscolar() {
        return tInsAyudaEscolar;
    }

    /**
     * Define el valor de la propiedad tInsAyudaEscolar.
     * 
     * @param value
     *     allowed object is
     *     {@link ZPESTINSAYUDAESCOLAR }
     *     
     */
    public void setTInsAyudaEscolar(ZPESTINSAYUDAESCOLAR value) {
        this.tInsAyudaEscolar = value;
    }

}
