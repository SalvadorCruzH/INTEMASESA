
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStProcesoSeleccionLista complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStProcesoSeleccionLista"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Offid" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Pbdat" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Vldat" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Idtxt" type="{urn:sap-com:document:sap:rfc:functions}char70"/&gt;
 *         &lt;element name="Zzplazas" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/&gt;
 *         &lt;element name="Zzcateg" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="Ctext" type="{urn:sap-com:document:sap:rfc:functions}char32"/&gt;
 *         &lt;element name="Objid" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Stext" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Persa" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Txtpersa" type="{urn:sap-com:document:sap:rfc:functions}char30"/&gt;
 *         &lt;element name="Zztipo" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/&gt;
 *         &lt;element name="ZztipoDesc" type="{urn:sap-com:document:sap:rfc:functions}char32"/&gt;
 *         &lt;element name="ZztipoDesc2" type="{urn:sap-com:document:sap:rfc:functions}char32"/&gt;
 *         &lt;element name="Zzpreso" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Zzcostes" type="{urn:sap-com:document:sap:rfc:functions}curr12.2"/&gt;
 *         &lt;element name="Zzcupodis" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Zzenltemario" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Zzpastemario" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStProcesoSeleccionLista", propOrder = {
    "offid",
    "pbdat",
    "vldat",
    "idtxt",
    "zzplazas",
    "zzcateg",
    "ctext",
    "objid",
    "stext",
    "persa",
    "txtpersa",
    "zztipo",
    "zztipoDesc",
    "zztipoDesc2",
    "zzpreso",
    "zzcostes",
    "zzcupodis",
    "zzenltemario",
    "zzpastemario"
})
public class ZpeStProcesoSeleccionLista {

    @XmlElement(name = "Offid", required = true)
    protected String offid;
    @XmlElement(name = "Pbdat", required = true)
    protected String pbdat;
    @XmlElement(name = "Vldat", required = true)
    protected String vldat;
    @XmlElement(name = "Idtxt", required = true)
    protected String idtxt;
    @XmlElement(name = "Zzplazas", required = true)
    protected String zzplazas;
    @XmlElement(name = "Zzcateg", required = true)
    protected String zzcateg;
    @XmlElement(name = "Ctext", required = true)
    protected String ctext;
    @XmlElement(name = "Objid", required = true)
    protected String objid;
    @XmlElement(name = "Stext", required = true)
    protected String stext;
    @XmlElement(name = "Persa", required = true)
    protected String persa;
    @XmlElement(name = "Txtpersa", required = true)
    protected String txtpersa;
    @XmlElement(name = "Zztipo", required = true)
    protected String zztipo;
    @XmlElement(name = "ZztipoDesc", required = true)
    protected String zztipoDesc;
    @XmlElement(name = "ZztipoDesc2", required = true)
    protected String zztipoDesc2;
    @XmlElement(name = "Zzpreso", required = true)
    protected String zzpreso;
    @XmlElement(name = "Zzcostes", required = true)
    protected BigDecimal zzcostes;
    @XmlElement(name = "Zzcupodis", required = true)
    protected String zzcupodis;
    @XmlElement(name = "Zzenltemario", required = true)
    protected String zzenltemario;
    @XmlElement(name = "Zzpastemario", required = true)
    protected String zzpastemario;

    /**
     * Obtiene el valor de la propiedad offid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffid() {
        return offid;
    }

    /**
     * Define el valor de la propiedad offid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffid(String value) {
        this.offid = value;
    }

    /**
     * Obtiene el valor de la propiedad pbdat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPbdat() {
        return pbdat;
    }

    /**
     * Define el valor de la propiedad pbdat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPbdat(String value) {
        this.pbdat = value;
    }

    /**
     * Obtiene el valor de la propiedad vldat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVldat() {
        return vldat;
    }

    /**
     * Define el valor de la propiedad vldat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVldat(String value) {
        this.vldat = value;
    }

    /**
     * Obtiene el valor de la propiedad idtxt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdtxt() {
        return idtxt;
    }

    /**
     * Define el valor de la propiedad idtxt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdtxt(String value) {
        this.idtxt = value;
    }

    /**
     * Obtiene el valor de la propiedad zzplazas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzplazas() {
        return zzplazas;
    }

    /**
     * Define el valor de la propiedad zzplazas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzplazas(String value) {
        this.zzplazas = value;
    }

    /**
     * Obtiene el valor de la propiedad zzcateg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzcateg() {
        return zzcateg;
    }

    /**
     * Define el valor de la propiedad zzcateg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzcateg(String value) {
        this.zzcateg = value;
    }

    /**
     * Obtiene el valor de la propiedad ctext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtext() {
        return ctext;
    }

    /**
     * Define el valor de la propiedad ctext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtext(String value) {
        this.ctext = value;
    }

    /**
     * Obtiene el valor de la propiedad objid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjid() {
        return objid;
    }

    /**
     * Define el valor de la propiedad objid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjid(String value) {
        this.objid = value;
    }

    /**
     * Obtiene el valor de la propiedad stext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStext() {
        return stext;
    }

    /**
     * Define el valor de la propiedad stext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStext(String value) {
        this.stext = value;
    }

    /**
     * Obtiene el valor de la propiedad persa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersa() {
        return persa;
    }

    /**
     * Define el valor de la propiedad persa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersa(String value) {
        this.persa = value;
    }

    /**
     * Obtiene el valor de la propiedad txtpersa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtpersa() {
        return txtpersa;
    }

    /**
     * Define el valor de la propiedad txtpersa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtpersa(String value) {
        this.txtpersa = value;
    }

    /**
     * Obtiene el valor de la propiedad zztipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZztipo() {
        return zztipo;
    }

    /**
     * Define el valor de la propiedad zztipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZztipo(String value) {
        this.zztipo = value;
    }

    /**
     * Obtiene el valor de la propiedad zztipoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZztipoDesc() {
        return zztipoDesc;
    }

    /**
     * Define el valor de la propiedad zztipoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZztipoDesc(String value) {
        this.zztipoDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad zztipoDesc2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZztipoDesc2() {
        return zztipoDesc2;
    }

    /**
     * Define el valor de la propiedad zztipoDesc2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZztipoDesc2(String value) {
        this.zztipoDesc2 = value;
    }

    /**
     * Obtiene el valor de la propiedad zzpreso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzpreso() {
        return zzpreso;
    }

    /**
     * Define el valor de la propiedad zzpreso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzpreso(String value) {
        this.zzpreso = value;
    }

    /**
     * Obtiene el valor de la propiedad zzcostes.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZzcostes() {
        return zzcostes;
    }

    /**
     * Define el valor de la propiedad zzcostes.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZzcostes(BigDecimal value) {
        this.zzcostes = value;
    }

    /**
     * Obtiene el valor de la propiedad zzcupodis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzcupodis() {
        return zzcupodis;
    }

    /**
     * Define el valor de la propiedad zzcupodis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzcupodis(String value) {
        this.zzcupodis = value;
    }

    /**
     * Obtiene el valor de la propiedad zzenltemario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzenltemario() {
        return zzenltemario;
    }

    /**
     * Define el valor de la propiedad zzenltemario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzenltemario(String value) {
        this.zzenltemario = value;
    }

    /**
     * Obtiene el valor de la propiedad zzpastemario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZzpastemario() {
        return zzpastemario;
    }

    /**
     * Define el valor de la propiedad zzpastemario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZzpastemario(String value) {
        this.zzpastemario = value;
    }

}
