
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
 *         &lt;element name="ImCupoDisc" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/&gt;
 *         &lt;element name="ImEsEmpleado" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="ImNumCand" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="ImProcId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="ImVacId" type="{urn:sap-com:document:sap:rfc:functions}numeric8" minOccurs="0"/&gt;
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
    "imCupoDisc",
    "imEsEmpleado",
    "imNumCand",
    "imProcId",
    "imVacId"
})
@XmlRootElement(name = "ZPeProcesosSeleccionInscri")
public class ZPeProcesosSeleccionInscri {

    @XmlElement(name = "ImCupoDisc")
    protected String imCupoDisc;
    @XmlElement(name = "ImEsEmpleado", required = true)
    protected String imEsEmpleado;
    @XmlElement(name = "ImNumCand", required = true)
    protected String imNumCand;
    @XmlElement(name = "ImProcId", required = true)
    protected String imProcId;
    @XmlElement(name = "ImVacId")
    protected String imVacId;

    /**
     * Obtiene el valor de la propiedad imCupoDisc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImCupoDisc() {
        return imCupoDisc;
    }

    /**
     * Define el valor de la propiedad imCupoDisc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImCupoDisc(String value) {
        this.imCupoDisc = value;
    }

    /**
     * Obtiene el valor de la propiedad imEsEmpleado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImEsEmpleado() {
        return imEsEmpleado;
    }

    /**
     * Define el valor de la propiedad imEsEmpleado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImEsEmpleado(String value) {
        this.imEsEmpleado = value;
    }

    /**
     * Obtiene el valor de la propiedad imNumCand.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImNumCand() {
        return imNumCand;
    }

    /**
     * Define el valor de la propiedad imNumCand.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImNumCand(String value) {
        this.imNumCand = value;
    }

    /**
     * Obtiene el valor de la propiedad imProcId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImProcId() {
        return imProcId;
    }

    /**
     * Define el valor de la propiedad imProcId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImProcId(String value) {
        this.imProcId = value;
    }

    /**
     * Obtiene el valor de la propiedad imVacId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImVacId() {
        return imVacId;
    }

    /**
     * Define el valor de la propiedad imVacId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImVacId(String value) {
        this.imVacId = value;
    }

}
