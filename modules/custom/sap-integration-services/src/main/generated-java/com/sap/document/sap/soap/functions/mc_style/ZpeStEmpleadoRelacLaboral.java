
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoRelacLaboral complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoRelacLaboral"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="FantigEmpresa" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10"/&gt;
 *         &lt;element name="FantigCategoria" type="{urn:sap-com:document:sap:soap:functions:mc-style}date10"/&gt;
 *         &lt;element name="CategoriaId" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="CategoriaDesc" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="GrupoProfId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char8"/&gt;
 *         &lt;element name="AreaPersoId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char2"/&gt;
 *         &lt;element name="SubgrupoProfId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char2"/&gt;
 *         &lt;element name="ContratoId" type="{urn:sap-com:document:sap:soap:functions:mc-style}char5"/&gt;
 *         &lt;element name="MayorDedicacion" type="{urn:sap-com:document:sap:soap:functions:mc-style}char2"/&gt;
 *         &lt;element name="GrupoSubgrupoDesc" type="{urn:sap-com:document:sap:soap:functions:mc-style}char35"/&gt;
 *         &lt;element name="IrpfCalculado" type="{urn:sap-com:document:sap:rfc:functions}decimal4.2"/&gt;
 *         &lt;element name="IrpfSolicitado" type="{urn:sap-com:document:sap:rfc:functions}decimal4.2"/&gt;
 *         &lt;element name="IrpfAplicado" type="{urn:sap-com:document:sap:rfc:functions}decimal4.2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoRelacLaboral", propOrder = {
    "pernr",
    "fantigEmpresa",
    "fantigCategoria",
    "categoriaId",
    "categoriaDesc",
    "grupoProfId",
    "areaPersoId",
    "subgrupoProfId",
    "contratoId",
    "mayorDedicacion",
    "grupoSubgrupoDesc",
    "irpfCalculado",
    "irpfSolicitado",
    "irpfAplicado"
})
public class ZpeStEmpleadoRelacLaboral {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "FantigEmpresa", required = true)
    protected String fantigEmpresa;
    @XmlElement(name = "FantigCategoria", required = true)
    protected String fantigCategoria;
    @XmlElement(name = "CategoriaId", required = true)
    protected String categoriaId;
    @XmlElement(name = "CategoriaDesc", required = true)
    protected String categoriaDesc;
    @XmlElement(name = "GrupoProfId", required = true)
    protected String grupoProfId;
    @XmlElement(name = "AreaPersoId", required = true)
    protected String areaPersoId;
    @XmlElement(name = "SubgrupoProfId", required = true)
    protected String subgrupoProfId;
    @XmlElement(name = "ContratoId", required = true)
    protected String contratoId;
    @XmlElement(name = "MayorDedicacion", required = true)
    protected String mayorDedicacion;
    @XmlElement(name = "GrupoSubgrupoDesc", required = true)
    protected String grupoSubgrupoDesc;
    @XmlElement(name = "IrpfCalculado", required = true)
    protected BigDecimal irpfCalculado;
    @XmlElement(name = "IrpfSolicitado", required = true)
    protected BigDecimal irpfSolicitado;
    @XmlElement(name = "IrpfAplicado", required = true)
    protected BigDecimal irpfAplicado;

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
     * Obtiene el valor de la propiedad fantigEmpresa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFantigEmpresa() {
        return fantigEmpresa;
    }

    /**
     * Define el valor de la propiedad fantigEmpresa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFantigEmpresa(String value) {
        this.fantigEmpresa = value;
    }

    /**
     * Obtiene el valor de la propiedad fantigCategoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFantigCategoria() {
        return fantigCategoria;
    }

    /**
     * Define el valor de la propiedad fantigCategoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFantigCategoria(String value) {
        this.fantigCategoria = value;
    }

    /**
     * Obtiene el valor de la propiedad categoriaId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaId() {
        return categoriaId;
    }

    /**
     * Define el valor de la propiedad categoriaId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaId(String value) {
        this.categoriaId = value;
    }

    /**
     * Obtiene el valor de la propiedad categoriaDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaDesc() {
        return categoriaDesc;
    }

    /**
     * Define el valor de la propiedad categoriaDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaDesc(String value) {
        this.categoriaDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad grupoProfId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupoProfId() {
        return grupoProfId;
    }

    /**
     * Define el valor de la propiedad grupoProfId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupoProfId(String value) {
        this.grupoProfId = value;
    }

    /**
     * Obtiene el valor de la propiedad areaPersoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaPersoId() {
        return areaPersoId;
    }

    /**
     * Define el valor de la propiedad areaPersoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaPersoId(String value) {
        this.areaPersoId = value;
    }

    /**
     * Obtiene el valor de la propiedad subgrupoProfId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubgrupoProfId() {
        return subgrupoProfId;
    }

    /**
     * Define el valor de la propiedad subgrupoProfId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubgrupoProfId(String value) {
        this.subgrupoProfId = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoId() {
        return contratoId;
    }

    /**
     * Define el valor de la propiedad contratoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoId(String value) {
        this.contratoId = value;
    }

    /**
     * Obtiene el valor de la propiedad mayorDedicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMayorDedicacion() {
        return mayorDedicacion;
    }

    /**
     * Define el valor de la propiedad mayorDedicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMayorDedicacion(String value) {
        this.mayorDedicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad grupoSubgrupoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupoSubgrupoDesc() {
        return grupoSubgrupoDesc;
    }

    /**
     * Define el valor de la propiedad grupoSubgrupoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupoSubgrupoDesc(String value) {
        this.grupoSubgrupoDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad irpfCalculado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIrpfCalculado() {
        return irpfCalculado;
    }

    /**
     * Define el valor de la propiedad irpfCalculado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIrpfCalculado(BigDecimal value) {
        this.irpfCalculado = value;
    }

    /**
     * Obtiene el valor de la propiedad irpfSolicitado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIrpfSolicitado() {
        return irpfSolicitado;
    }

    /**
     * Define el valor de la propiedad irpfSolicitado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIrpfSolicitado(BigDecimal value) {
        this.irpfSolicitado = value;
    }

    /**
     * Obtiene el valor de la propiedad irpfAplicado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIrpfAplicado() {
        return irpfAplicado;
    }

    /**
     * Define el valor de la propiedad irpfAplicado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIrpfAplicado(BigDecimal value) {
        this.irpfAplicado = value;
    }

}
