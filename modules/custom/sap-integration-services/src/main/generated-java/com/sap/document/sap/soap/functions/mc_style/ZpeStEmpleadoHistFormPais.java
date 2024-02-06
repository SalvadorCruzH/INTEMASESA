
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoHistFormPais complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoHistFormPais"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PaisId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char3"/&gt;
 *         &lt;element name="PaisDesc" type="{urn:sap-com:document:sap:soap:functions:mc-style}char50"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoHistFormPais", propOrder = {
    "paisId",
    "paisDesc"
})
public class ZpeStEmpleadoHistFormPais {

    @XmlElement(name = "PaisId", required = true)
    protected String paisId;
    @XmlElement(name = "PaisDesc", required = true)
    protected String paisDesc;

    /**
     * Obtiene el valor de la propiedad paisId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisId() {
        return paisId;
    }

    /**
     * Define el valor de la propiedad paisId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisId(String value) {
        this.paisId = value;
    }

    /**
     * Obtiene el valor de la propiedad paisDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisDesc() {
        return paisDesc;
    }

    /**
     * Define el valor de la propiedad paisDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisDesc(String value) {
        this.paisDesc = value;
    }

}
