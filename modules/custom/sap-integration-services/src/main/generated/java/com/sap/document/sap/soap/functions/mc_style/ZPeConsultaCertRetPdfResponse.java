
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
 *         &lt;element name="ExData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="ExResult" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "exData",
    "exResult"
})
@XmlRootElement(name = "ZPeConsultaCertRetPdfResponse")
public class ZPeConsultaCertRetPdfResponse {

    @XmlElement(name = "ExData", required = true)
    protected byte[] exData;
    @XmlElement(name = "ExResult")
    protected int exResult;

    /**
     * Obtiene el valor de la propiedad exData.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getExData() {
        return exData;
    }

    /**
     * Define el valor de la propiedad exData.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setExData(byte[] value) {
        this.exData = value;
    }

    /**
     * Obtiene el valor de la propiedad exResult.
     * 
     */
    public int getExResult() {
        return exResult;
    }

    /**
     * Define el valor de la propiedad exResult.
     * 
     */
    public void setExResult(int value) {
        this.exResult = value;
    }

}
