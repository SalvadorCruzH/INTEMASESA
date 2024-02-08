
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZpeStEmpleadoHistContrCat complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZpeStEmpleadoHistContrCat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Ename" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Begda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Endda" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="DuracionDias" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="Orgeh" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="DesOrgeh" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Plans" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="DesPlans" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Werks" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Btrtl" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Persg" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Persk" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Abkrs" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Ansvh" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Kokrs" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Kostl" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Idcon" type="{urn:sap-com:document:sap:rfc:functions}char5"/&gt;
 *         &lt;element name="ContratoDesc" type="{urn:sap-com:document:sap:rfc:functions}char60"/&gt;
 *         &lt;element name="Berkt" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="CategoriaDesc" type="{urn:sap-com:document:sap:rfc:functions}char32"/&gt;
 *         &lt;element name="Trfgr" type="{urn:sap-com:document:sap:rfc:functions}char8"/&gt;
 *         &lt;element name="Trfst" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Stat2" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpeStEmpleadoHistContrCat", propOrder = {
    "pernr",
    "ename",
    "begda",
    "endda",
    "duracionDias",
    "orgeh",
    "desOrgeh",
    "plans",
    "desPlans",
    "werks",
    "btrtl",
    "persg",
    "persk",
    "abkrs",
    "ansvh",
    "kokrs",
    "kostl",
    "idcon",
    "contratoDesc",
    "berkt",
    "categoriaDesc",
    "trfgr",
    "trfst",
    "stat2"
})
public class ZpeStEmpleadoHistContrCat {

    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "Ename", required = true)
    protected String ename;
    @XmlElement(name = "Begda", required = true)
    protected String begda;
    @XmlElement(name = "Endda", required = true)
    protected String endda;
    @XmlElement(name = "DuracionDias", required = true)
    protected BigDecimal duracionDias;
    @XmlElement(name = "Orgeh", required = true)
    protected String orgeh;
    @XmlElement(name = "DesOrgeh", required = true)
    protected String desOrgeh;
    @XmlElement(name = "Plans", required = true)
    protected String plans;
    @XmlElement(name = "DesPlans", required = true)
    protected String desPlans;
    @XmlElement(name = "Werks", required = true)
    protected String werks;
    @XmlElement(name = "Btrtl", required = true)
    protected String btrtl;
    @XmlElement(name = "Persg", required = true)
    protected String persg;
    @XmlElement(name = "Persk", required = true)
    protected String persk;
    @XmlElement(name = "Abkrs", required = true)
    protected String abkrs;
    @XmlElement(name = "Ansvh", required = true)
    protected String ansvh;
    @XmlElement(name = "Kokrs", required = true)
    protected String kokrs;
    @XmlElement(name = "Kostl", required = true)
    protected String kostl;
    @XmlElement(name = "Idcon", required = true)
    protected String idcon;
    @XmlElement(name = "ContratoDesc", required = true)
    protected String contratoDesc;
    @XmlElement(name = "Berkt", required = true)
    protected String berkt;
    @XmlElement(name = "CategoriaDesc", required = true)
    protected String categoriaDesc;
    @XmlElement(name = "Trfgr", required = true)
    protected String trfgr;
    @XmlElement(name = "Trfst", required = true)
    protected String trfst;
    @XmlElement(name = "Stat2", required = true)
    protected String stat2;

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
     * Obtiene el valor de la propiedad ename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEname() {
        return ename;
    }

    /**
     * Define el valor de la propiedad ename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEname(String value) {
        this.ename = value;
    }

    /**
     * Obtiene el valor de la propiedad begda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBegda() {
        return begda;
    }

    /**
     * Define el valor de la propiedad begda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBegda(String value) {
        this.begda = value;
    }

    /**
     * Obtiene el valor de la propiedad endda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndda() {
        return endda;
    }

    /**
     * Define el valor de la propiedad endda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndda(String value) {
        this.endda = value;
    }

    /**
     * Obtiene el valor de la propiedad duracionDias.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDuracionDias() {
        return duracionDias;
    }

    /**
     * Define el valor de la propiedad duracionDias.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDuracionDias(BigDecimal value) {
        this.duracionDias = value;
    }

    /**
     * Obtiene el valor de la propiedad orgeh.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgeh() {
        return orgeh;
    }

    /**
     * Define el valor de la propiedad orgeh.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgeh(String value) {
        this.orgeh = value;
    }

    /**
     * Obtiene el valor de la propiedad desOrgeh.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesOrgeh() {
        return desOrgeh;
    }

    /**
     * Define el valor de la propiedad desOrgeh.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesOrgeh(String value) {
        this.desOrgeh = value;
    }

    /**
     * Obtiene el valor de la propiedad plans.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlans() {
        return plans;
    }

    /**
     * Define el valor de la propiedad plans.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlans(String value) {
        this.plans = value;
    }

    /**
     * Obtiene el valor de la propiedad desPlans.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesPlans() {
        return desPlans;
    }

    /**
     * Define el valor de la propiedad desPlans.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesPlans(String value) {
        this.desPlans = value;
    }

    /**
     * Obtiene el valor de la propiedad werks.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWerks() {
        return werks;
    }

    /**
     * Define el valor de la propiedad werks.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWerks(String value) {
        this.werks = value;
    }

    /**
     * Obtiene el valor de la propiedad btrtl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBtrtl() {
        return btrtl;
    }

    /**
     * Define el valor de la propiedad btrtl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBtrtl(String value) {
        this.btrtl = value;
    }

    /**
     * Obtiene el valor de la propiedad persg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersg() {
        return persg;
    }

    /**
     * Define el valor de la propiedad persg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersg(String value) {
        this.persg = value;
    }

    /**
     * Obtiene el valor de la propiedad persk.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersk() {
        return persk;
    }

    /**
     * Define el valor de la propiedad persk.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersk(String value) {
        this.persk = value;
    }

    /**
     * Obtiene el valor de la propiedad abkrs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbkrs() {
        return abkrs;
    }

    /**
     * Define el valor de la propiedad abkrs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbkrs(String value) {
        this.abkrs = value;
    }

    /**
     * Obtiene el valor de la propiedad ansvh.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnsvh() {
        return ansvh;
    }

    /**
     * Define el valor de la propiedad ansvh.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnsvh(String value) {
        this.ansvh = value;
    }

    /**
     * Obtiene el valor de la propiedad kokrs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKokrs() {
        return kokrs;
    }

    /**
     * Define el valor de la propiedad kokrs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKokrs(String value) {
        this.kokrs = value;
    }

    /**
     * Obtiene el valor de la propiedad kostl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKostl() {
        return kostl;
    }

    /**
     * Define el valor de la propiedad kostl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKostl(String value) {
        this.kostl = value;
    }

    /**
     * Obtiene el valor de la propiedad idcon.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdcon() {
        return idcon;
    }

    /**
     * Define el valor de la propiedad idcon.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdcon(String value) {
        this.idcon = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoDesc() {
        return contratoDesc;
    }

    /**
     * Define el valor de la propiedad contratoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoDesc(String value) {
        this.contratoDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad berkt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBerkt() {
        return berkt;
    }

    /**
     * Define el valor de la propiedad berkt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBerkt(String value) {
        this.berkt = value;
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
     * Obtiene el valor de la propiedad trfgr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrfgr() {
        return trfgr;
    }

    /**
     * Define el valor de la propiedad trfgr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrfgr(String value) {
        this.trfgr = value;
    }

    /**
     * Obtiene el valor de la propiedad trfst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrfst() {
        return trfst;
    }

    /**
     * Define el valor de la propiedad trfst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrfst(String value) {
        this.trfst = value;
    }

    /**
     * Obtiene el valor de la propiedad stat2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStat2() {
        return stat2;
    }

    /**
     * Define el valor de la propiedad stat2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStat2(String value) {
        this.stat2 = value;
    }

}
