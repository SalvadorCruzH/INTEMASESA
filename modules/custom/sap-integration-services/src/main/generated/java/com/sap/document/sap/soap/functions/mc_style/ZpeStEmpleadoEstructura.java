
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoEstructura complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoEstructura"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="UnidadOrgId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="UnidadOrgDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="SeccionId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="SeccionDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="UnidadId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="UnidadDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="DptoId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="DptoDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="DivisionId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="DivisionDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="SubdireccionId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="SubdireccionDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="DireccionId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="DireccionDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="CentroId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char4"/&gt;
 *         &lt;element name="CentroDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="PosicionId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="PosicionDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="ResponsableId" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="EsResponsable" type="{urn:sap-com:document:sap:soap:functions:mc-style}char1"/&gt;
 *         &lt;element name="FuncionId" type="{urn:sap-com:document:sap:soap:functions:mc-style}numeric8"/&gt;
 *         &lt;element name="FuncionDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoEstructura", propOrder = {
    "pernr",
    "unidadOrgId",
    "unidadOrgDesc",
    "seccionId",
    "seccionDesc",
    "unidadId",
    "unidadDesc",
    "dptoId",
    "dptoDesc",
    "divisionId",
    "divisionDesc",
    "subdireccionId",
    "subdireccionDesc",
    "direccionId",
    "direccionDesc",
    "centroId",
    "centroDesc",
    "posicionId",
    "posicionDesc",
    "responsableId",
    "esResponsable",
    "funcionId",
    "funcionDesc"
})
public class ZpeStEmpleadoEstructura {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "UnidadOrgId", required = true)
    protected String unidadOrgId;
    @XmlElement(name = "UnidadOrgDesc", required = true)
    protected String unidadOrgDesc;
    @XmlElement(name = "SeccionId", required = true)
    protected String seccionId;
    @XmlElement(name = "SeccionDesc", required = true)
    protected String seccionDesc;
    @XmlElement(name = "UnidadId", required = true)
    protected String unidadId;
    @XmlElement(name = "UnidadDesc", required = true)
    protected String unidadDesc;
    @XmlElement(name = "DptoId", required = true)
    protected String dptoId;
    @XmlElement(name = "DptoDesc", required = true)
    protected String dptoDesc;
    @XmlElement(name = "DivisionId", required = true)
    protected String divisionId;
    @XmlElement(name = "DivisionDesc", required = true)
    protected String divisionDesc;
    @XmlElement(name = "SubdireccionId", required = true)
    protected String subdireccionId;
    @XmlElement(name = "SubdireccionDesc", required = true)
    protected String subdireccionDesc;
    @XmlElement(name = "DireccionId", required = true)
    protected String direccionId;
    @XmlElement(name = "DireccionDesc", required = true)
    protected String direccionDesc;
    @XmlElement(name = "CentroId", required = true)
    protected String centroId;
    @XmlElement(name = "CentroDesc", required = true)
    protected String centroDesc;
    @XmlElement(name = "PosicionId", required = true)
    protected String posicionId;
    @XmlElement(name = "PosicionDesc", required = true)
    protected String posicionDesc;
    @XmlElement(name = "ResponsableId", required = true)
    protected String responsableId;
    @XmlElement(name = "EsResponsable", required = true)
    protected String esResponsable;
    @XmlElement(name = "FuncionId", required = true)
    protected String funcionId;
    @XmlElement(name = "FuncionDesc", required = true)
    protected String funcionDesc;

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
     * Obtiene el valor de la propiedad unidadOrgId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadOrgId() {
        return unidadOrgId;
    }

    /**
     * Define el valor de la propiedad unidadOrgId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadOrgId(String value) {
        this.unidadOrgId = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadOrgDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadOrgDesc() {
        return unidadOrgDesc;
    }

    /**
     * Define el valor de la propiedad unidadOrgDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadOrgDesc(String value) {
        this.unidadOrgDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad seccionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeccionId() {
        return seccionId;
    }

    /**
     * Define el valor de la propiedad seccionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeccionId(String value) {
        this.seccionId = value;
    }

    /**
     * Obtiene el valor de la propiedad seccionDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeccionDesc() {
        return seccionDesc;
    }

    /**
     * Define el valor de la propiedad seccionDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeccionDesc(String value) {
        this.seccionDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadId() {
        return unidadId;
    }

    /**
     * Define el valor de la propiedad unidadId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadId(String value) {
        this.unidadId = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadDesc() {
        return unidadDesc;
    }

    /**
     * Define el valor de la propiedad unidadDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadDesc(String value) {
        this.unidadDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad dptoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDptoId() {
        return dptoId;
    }

    /**
     * Define el valor de la propiedad dptoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDptoId(String value) {
        this.dptoId = value;
    }

    /**
     * Obtiene el valor de la propiedad dptoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDptoDesc() {
        return dptoDesc;
    }

    /**
     * Define el valor de la propiedad dptoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDptoDesc(String value) {
        this.dptoDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad divisionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivisionId() {
        return divisionId;
    }

    /**
     * Define el valor de la propiedad divisionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivisionId(String value) {
        this.divisionId = value;
    }

    /**
     * Obtiene el valor de la propiedad divisionDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivisionDesc() {
        return divisionDesc;
    }

    /**
     * Define el valor de la propiedad divisionDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivisionDesc(String value) {
        this.divisionDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad subdireccionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubdireccionId() {
        return subdireccionId;
    }

    /**
     * Define el valor de la propiedad subdireccionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubdireccionId(String value) {
        this.subdireccionId = value;
    }

    /**
     * Obtiene el valor de la propiedad subdireccionDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubdireccionDesc() {
        return subdireccionDesc;
    }

    /**
     * Define el valor de la propiedad subdireccionDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubdireccionDesc(String value) {
        this.subdireccionDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionId() {
        return direccionId;
    }

    /**
     * Define el valor de la propiedad direccionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionId(String value) {
        this.direccionId = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionDesc() {
        return direccionDesc;
    }

    /**
     * Define el valor de la propiedad direccionDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionDesc(String value) {
        this.direccionDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad centroId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroId() {
        return centroId;
    }

    /**
     * Define el valor de la propiedad centroId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroId(String value) {
        this.centroId = value;
    }

    /**
     * Obtiene el valor de la propiedad centroDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCentroDesc() {
        return centroDesc;
    }

    /**
     * Define el valor de la propiedad centroDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCentroDesc(String value) {
        this.centroDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad posicionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosicionId() {
        return posicionId;
    }

    /**
     * Define el valor de la propiedad posicionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosicionId(String value) {
        this.posicionId = value;
    }

    /**
     * Obtiene el valor de la propiedad posicionDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosicionDesc() {
        return posicionDesc;
    }

    /**
     * Define el valor de la propiedad posicionDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosicionDesc(String value) {
        this.posicionDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad responsableId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsableId() {
        return responsableId;
    }

    /**
     * Define el valor de la propiedad responsableId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsableId(String value) {
        this.responsableId = value;
    }

    /**
     * Obtiene el valor de la propiedad esResponsable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsResponsable() {
        return esResponsable;
    }

    /**
     * Define el valor de la propiedad esResponsable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsResponsable(String value) {
        this.esResponsable = value;
    }

    /**
     * Obtiene el valor de la propiedad funcionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionId() {
        return funcionId;
    }

    /**
     * Define el valor de la propiedad funcionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionId(String value) {
        this.funcionId = value;
    }

    /**
     * Obtiene el valor de la propiedad funcionDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuncionDesc() {
        return funcionDesc;
    }

    /**
     * Define el valor de la propiedad funcionDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuncionDesc(String value) {
        this.funcionDesc = value;
    }

}
