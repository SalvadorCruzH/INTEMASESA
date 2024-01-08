
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZhrEDetnecform complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ZhrEDetnecform"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/&gt;
 *         &lt;element name="NumSolicitud" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="NumFormuladaPor" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="FormuladaPor" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Fecha" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="NumSolicitante" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Solicitante" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Posicion" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="UniOrg" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Denominacion" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Otras" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Transversal" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Clave" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="RequerimientosTec" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="AdaptacionNueTec" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="NuevasFunciones" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="NormativaPsc" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="CumplimientoObj" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="MejoraComGes" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="ContenidosSenSoc" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Objetivo" type="{urn:sap-com:document:sap:rfc:functions}char255"/&gt;
 *         &lt;element name="Resultados" type="{urn:sap-com:document:sap:rfc:functions}char255"/&gt;
 *         &lt;element name="Propuesta" type="{urn:sap-com:document:sap:rfc:functions}char255"/&gt;
 *         &lt;element name="Asistente01" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente02" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente03" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente04" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente05" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente06" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente07" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente08" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente09" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Asistente10" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="NumEmpleados" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/&gt;
 *         &lt;element name="Duracion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Valoracion" type="{urn:sap-com:document:sap:rfc:functions}decimal10.2"/&gt;
 *         &lt;element name="AnioImparticion1" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="AnioImparticion2" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="FechaImparticion" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="Cuatrimestre1" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Cuatrimestre2" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Cuatrimestre3" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Medios" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Interno" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Externo" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Docente1" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="Docente2" type="{urn:sap-com:document:sap:rfc:functions}char50"/&gt;
 *         &lt;element name="Observaciones" type="{urn:sap-com:document:sap:rfc:functions}char255"/&gt;
 *         &lt;element name="FechaHoraEntrevista" type="{urn:sap-com:document:sap:rfc:functions}char16"/&gt;
 *         &lt;element name="Autorizada" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="FechaAprobacion" type="{urn:sap-com:document:sap:rfc:functions}date10"/&gt;
 *         &lt;element name="ObservacionesFormacion" type="{urn:sap-com:document:sap:rfc:functions}char255"/&gt;
 *         &lt;element name="TecnicoFormacion" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Origen" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="ClaveFormacion" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="PlanFormacion" type="{urn:sap-com:document:sap:rfc:functions}char9"/&gt;
 *         &lt;element name="IdUniOrg" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/&gt;
 *         &lt;element name="Estado" type="{urn:sap-com:document:sap:rfc:functions}char20"/&gt;
 *         &lt;element name="DenegadaDirec" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="NumActividad" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/&gt;
 *         &lt;element name="Obligatoriedad" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Modalidad" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZhrEDetnecform", propOrder = {
    "mandt",
    "numSolicitud",
    "numFormuladaPor",
    "formuladaPor",
    "fecha",
    "numSolicitante",
    "solicitante",
    "posicion",
    "uniOrg",
    "denominacion",
    "otras",
    "transversal",
    "clave",
    "requerimientosTec",
    "adaptacionNueTec",
    "nuevasFunciones",
    "normativaPsc",
    "cumplimientoObj",
    "mejoraComGes",
    "contenidosSenSoc",
    "objetivo",
    "resultados",
    "propuesta",
    "asistente01",
    "asistente02",
    "asistente03",
    "asistente04",
    "asistente05",
    "asistente06",
    "asistente07",
    "asistente08",
    "asistente09",
    "asistente10",
    "numEmpleados",
    "duracion",
    "valoracion",
    "anioImparticion1",
    "anioImparticion2",
    "fechaImparticion",
    "cuatrimestre1",
    "cuatrimestre2",
    "cuatrimestre3",
    "medios",
    "interno",
    "externo",
    "docente1",
    "docente2",
    "observaciones",
    "fechaHoraEntrevista",
    "autorizada",
    "fechaAprobacion",
    "observacionesFormacion",
    "tecnicoFormacion",
    "origen",
    "claveFormacion",
    "planFormacion",
    "idUniOrg",
    "estado",
    "denegadaDirec",
    "numActividad",
    "obligatoriedad",
    "modalidad"
})
public class ZhrEDetnecform {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "NumSolicitud", required = true)
    protected String numSolicitud;
    @XmlElement(name = "NumFormuladaPor", required = true)
    protected String numFormuladaPor;
    @XmlElement(name = "FormuladaPor", required = true)
    protected String formuladaPor;
    @XmlElement(name = "Fecha", required = true)
    protected String fecha;
    @XmlElement(name = "NumSolicitante", required = true)
    protected String numSolicitante;
    @XmlElement(name = "Solicitante", required = true)
    protected String solicitante;
    @XmlElement(name = "Posicion", required = true)
    protected String posicion;
    @XmlElement(name = "UniOrg", required = true)
    protected String uniOrg;
    @XmlElement(name = "Denominacion", required = true)
    protected String denominacion;
    @XmlElement(name = "Otras", required = true)
    protected String otras;
    @XmlElement(name = "Transversal", required = true)
    protected String transversal;
    @XmlElement(name = "Clave", required = true)
    protected String clave;
    @XmlElement(name = "RequerimientosTec", required = true)
    protected String requerimientosTec;
    @XmlElement(name = "AdaptacionNueTec", required = true)
    protected String adaptacionNueTec;
    @XmlElement(name = "NuevasFunciones", required = true)
    protected String nuevasFunciones;
    @XmlElement(name = "NormativaPsc", required = true)
    protected String normativaPsc;
    @XmlElement(name = "CumplimientoObj", required = true)
    protected String cumplimientoObj;
    @XmlElement(name = "MejoraComGes", required = true)
    protected String mejoraComGes;
    @XmlElement(name = "ContenidosSenSoc", required = true)
    protected String contenidosSenSoc;
    @XmlElement(name = "Objetivo", required = true)
    protected String objetivo;
    @XmlElement(name = "Resultados", required = true)
    protected String resultados;
    @XmlElement(name = "Propuesta", required = true)
    protected String propuesta;
    @XmlElement(name = "Asistente01", required = true)
    protected String asistente01;
    @XmlElement(name = "Asistente02", required = true)
    protected String asistente02;
    @XmlElement(name = "Asistente03", required = true)
    protected String asistente03;
    @XmlElement(name = "Asistente04", required = true)
    protected String asistente04;
    @XmlElement(name = "Asistente05", required = true)
    protected String asistente05;
    @XmlElement(name = "Asistente06", required = true)
    protected String asistente06;
    @XmlElement(name = "Asistente07", required = true)
    protected String asistente07;
    @XmlElement(name = "Asistente08", required = true)
    protected String asistente08;
    @XmlElement(name = "Asistente09", required = true)
    protected String asistente09;
    @XmlElement(name = "Asistente10", required = true)
    protected String asistente10;
    @XmlElement(name = "NumEmpleados", required = true)
    protected String numEmpleados;
    @XmlElement(name = "Duracion")
    protected int duracion;
    @XmlElement(name = "Valoracion", required = true)
    protected BigDecimal valoracion;
    @XmlElement(name = "AnioImparticion1", required = true)
    protected String anioImparticion1;
    @XmlElement(name = "AnioImparticion2", required = true)
    protected String anioImparticion2;
    @XmlElement(name = "FechaImparticion", required = true)
    protected String fechaImparticion;
    @XmlElement(name = "Cuatrimestre1", required = true)
    protected String cuatrimestre1;
    @XmlElement(name = "Cuatrimestre2", required = true)
    protected String cuatrimestre2;
    @XmlElement(name = "Cuatrimestre3", required = true)
    protected String cuatrimestre3;
    @XmlElement(name = "Medios", required = true)
    protected String medios;
    @XmlElement(name = "Interno", required = true)
    protected String interno;
    @XmlElement(name = "Externo", required = true)
    protected String externo;
    @XmlElement(name = "Docente1", required = true)
    protected String docente1;
    @XmlElement(name = "Docente2", required = true)
    protected String docente2;
    @XmlElement(name = "Observaciones", required = true)
    protected String observaciones;
    @XmlElement(name = "FechaHoraEntrevista", required = true)
    protected String fechaHoraEntrevista;
    @XmlElement(name = "Autorizada", required = true)
    protected String autorizada;
    @XmlElement(name = "FechaAprobacion", required = true)
    protected String fechaAprobacion;
    @XmlElement(name = "ObservacionesFormacion", required = true)
    protected String observacionesFormacion;
    @XmlElement(name = "TecnicoFormacion", required = true)
    protected String tecnicoFormacion;
    @XmlElement(name = "Origen", required = true)
    protected String origen;
    @XmlElement(name = "ClaveFormacion", required = true)
    protected String claveFormacion;
    @XmlElement(name = "PlanFormacion", required = true)
    protected String planFormacion;
    @XmlElement(name = "IdUniOrg", required = true)
    protected String idUniOrg;
    @XmlElement(name = "Estado", required = true)
    protected String estado;
    @XmlElement(name = "DenegadaDirec", required = true)
    protected String denegadaDirec;
    @XmlElement(name = "NumActividad", required = true)
    protected String numActividad;
    @XmlElement(name = "Obligatoriedad", required = true)
    protected String obligatoriedad;
    @XmlElement(name = "Modalidad", required = true)
    protected String modalidad;

    /**
     * Obtiene el valor de la propiedad mandt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandt() {
        return mandt;
    }

    /**
     * Define el valor de la propiedad mandt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandt(String value) {
        this.mandt = value;
    }

    /**
     * Obtiene el valor de la propiedad numSolicitud.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumSolicitud() {
        return numSolicitud;
    }

    /**
     * Define el valor de la propiedad numSolicitud.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumSolicitud(String value) {
        this.numSolicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad numFormuladaPor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumFormuladaPor() {
        return numFormuladaPor;
    }

    /**
     * Define el valor de la propiedad numFormuladaPor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumFormuladaPor(String value) {
        this.numFormuladaPor = value;
    }

    /**
     * Obtiene el valor de la propiedad formuladaPor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormuladaPor() {
        return formuladaPor;
    }

    /**
     * Define el valor de la propiedad formuladaPor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormuladaPor(String value) {
        this.formuladaPor = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad numSolicitante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumSolicitante() {
        return numSolicitante;
    }

    /**
     * Define el valor de la propiedad numSolicitante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumSolicitante(String value) {
        this.numSolicitante = value;
    }

    /**
     * Obtiene el valor de la propiedad solicitante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * Define el valor de la propiedad solicitante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolicitante(String value) {
        this.solicitante = value;
    }

    /**
     * Obtiene el valor de la propiedad posicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * Define el valor de la propiedad posicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosicion(String value) {
        this.posicion = value;
    }

    /**
     * Obtiene el valor de la propiedad uniOrg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniOrg() {
        return uniOrg;
    }

    /**
     * Define el valor de la propiedad uniOrg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniOrg(String value) {
        this.uniOrg = value;
    }

    /**
     * Obtiene el valor de la propiedad denominacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * Define el valor de la propiedad denominacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominacion(String value) {
        this.denominacion = value;
    }

    /**
     * Obtiene el valor de la propiedad otras.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtras() {
        return otras;
    }

    /**
     * Define el valor de la propiedad otras.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtras(String value) {
        this.otras = value;
    }

    /**
     * Obtiene el valor de la propiedad transversal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransversal() {
        return transversal;
    }

    /**
     * Define el valor de la propiedad transversal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransversal(String value) {
        this.transversal = value;
    }

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClave() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClave(String value) {
        this.clave = value;
    }

    /**
     * Obtiene el valor de la propiedad requerimientosTec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequerimientosTec() {
        return requerimientosTec;
    }

    /**
     * Define el valor de la propiedad requerimientosTec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequerimientosTec(String value) {
        this.requerimientosTec = value;
    }

    /**
     * Obtiene el valor de la propiedad adaptacionNueTec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdaptacionNueTec() {
        return adaptacionNueTec;
    }

    /**
     * Define el valor de la propiedad adaptacionNueTec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdaptacionNueTec(String value) {
        this.adaptacionNueTec = value;
    }

    /**
     * Obtiene el valor de la propiedad nuevasFunciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuevasFunciones() {
        return nuevasFunciones;
    }

    /**
     * Define el valor de la propiedad nuevasFunciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuevasFunciones(String value) {
        this.nuevasFunciones = value;
    }

    /**
     * Obtiene el valor de la propiedad normativaPsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormativaPsc() {
        return normativaPsc;
    }

    /**
     * Define el valor de la propiedad normativaPsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormativaPsc(String value) {
        this.normativaPsc = value;
    }

    /**
     * Obtiene el valor de la propiedad cumplimientoObj.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCumplimientoObj() {
        return cumplimientoObj;
    }

    /**
     * Define el valor de la propiedad cumplimientoObj.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCumplimientoObj(String value) {
        this.cumplimientoObj = value;
    }

    /**
     * Obtiene el valor de la propiedad mejoraComGes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMejoraComGes() {
        return mejoraComGes;
    }

    /**
     * Define el valor de la propiedad mejoraComGes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMejoraComGes(String value) {
        this.mejoraComGes = value;
    }

    /**
     * Obtiene el valor de la propiedad contenidosSenSoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContenidosSenSoc() {
        return contenidosSenSoc;
    }

    /**
     * Define el valor de la propiedad contenidosSenSoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContenidosSenSoc(String value) {
        this.contenidosSenSoc = value;
    }

    /**
     * Obtiene el valor de la propiedad objetivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * Define el valor de la propiedad objetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjetivo(String value) {
        this.objetivo = value;
    }

    /**
     * Obtiene el valor de la propiedad resultados.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultados() {
        return resultados;
    }

    /**
     * Define el valor de la propiedad resultados.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultados(String value) {
        this.resultados = value;
    }

    /**
     * Obtiene el valor de la propiedad propuesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropuesta() {
        return propuesta;
    }

    /**
     * Define el valor de la propiedad propuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropuesta(String value) {
        this.propuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente01.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente01() {
        return asistente01;
    }

    /**
     * Define el valor de la propiedad asistente01.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente01(String value) {
        this.asistente01 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente02.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente02() {
        return asistente02;
    }

    /**
     * Define el valor de la propiedad asistente02.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente02(String value) {
        this.asistente02 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente03.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente03() {
        return asistente03;
    }

    /**
     * Define el valor de la propiedad asistente03.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente03(String value) {
        this.asistente03 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente04.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente04() {
        return asistente04;
    }

    /**
     * Define el valor de la propiedad asistente04.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente04(String value) {
        this.asistente04 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente05.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente05() {
        return asistente05;
    }

    /**
     * Define el valor de la propiedad asistente05.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente05(String value) {
        this.asistente05 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente06.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente06() {
        return asistente06;
    }

    /**
     * Define el valor de la propiedad asistente06.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente06(String value) {
        this.asistente06 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente07.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente07() {
        return asistente07;
    }

    /**
     * Define el valor de la propiedad asistente07.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente07(String value) {
        this.asistente07 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente08.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente08() {
        return asistente08;
    }

    /**
     * Define el valor de la propiedad asistente08.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente08(String value) {
        this.asistente08 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente09.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente09() {
        return asistente09;
    }

    /**
     * Define el valor de la propiedad asistente09.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente09(String value) {
        this.asistente09 = value;
    }

    /**
     * Obtiene el valor de la propiedad asistente10.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsistente10() {
        return asistente10;
    }

    /**
     * Define el valor de la propiedad asistente10.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsistente10(String value) {
        this.asistente10 = value;
    }

    /**
     * Obtiene el valor de la propiedad numEmpleados.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumEmpleados() {
        return numEmpleados;
    }

    /**
     * Define el valor de la propiedad numEmpleados.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumEmpleados(String value) {
        this.numEmpleados = value;
    }

    /**
     * Obtiene el valor de la propiedad duracion.
     * 
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Define el valor de la propiedad duracion.
     * 
     */
    public void setDuracion(int value) {
        this.duracion = value;
    }

    /**
     * Obtiene el valor de la propiedad valoracion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValoracion() {
        return valoracion;
    }

    /**
     * Define el valor de la propiedad valoracion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValoracion(BigDecimal value) {
        this.valoracion = value;
    }

    /**
     * Obtiene el valor de la propiedad anioImparticion1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnioImparticion1() {
        return anioImparticion1;
    }

    /**
     * Define el valor de la propiedad anioImparticion1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnioImparticion1(String value) {
        this.anioImparticion1 = value;
    }

    /**
     * Obtiene el valor de la propiedad anioImparticion2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnioImparticion2() {
        return anioImparticion2;
    }

    /**
     * Define el valor de la propiedad anioImparticion2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnioImparticion2(String value) {
        this.anioImparticion2 = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaImparticion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaImparticion() {
        return fechaImparticion;
    }

    /**
     * Define el valor de la propiedad fechaImparticion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaImparticion(String value) {
        this.fechaImparticion = value;
    }

    /**
     * Obtiene el valor de la propiedad cuatrimestre1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuatrimestre1() {
        return cuatrimestre1;
    }

    /**
     * Define el valor de la propiedad cuatrimestre1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuatrimestre1(String value) {
        this.cuatrimestre1 = value;
    }

    /**
     * Obtiene el valor de la propiedad cuatrimestre2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuatrimestre2() {
        return cuatrimestre2;
    }

    /**
     * Define el valor de la propiedad cuatrimestre2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuatrimestre2(String value) {
        this.cuatrimestre2 = value;
    }

    /**
     * Obtiene el valor de la propiedad cuatrimestre3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuatrimestre3() {
        return cuatrimestre3;
    }

    /**
     * Define el valor de la propiedad cuatrimestre3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuatrimestre3(String value) {
        this.cuatrimestre3 = value;
    }

    /**
     * Obtiene el valor de la propiedad medios.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedios() {
        return medios;
    }

    /**
     * Define el valor de la propiedad medios.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedios(String value) {
        this.medios = value;
    }

    /**
     * Obtiene el valor de la propiedad interno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterno() {
        return interno;
    }

    /**
     * Define el valor de la propiedad interno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterno(String value) {
        this.interno = value;
    }

    /**
     * Obtiene el valor de la propiedad externo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExterno() {
        return externo;
    }

    /**
     * Define el valor de la propiedad externo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExterno(String value) {
        this.externo = value;
    }

    /**
     * Obtiene el valor de la propiedad docente1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocente1() {
        return docente1;
    }

    /**
     * Define el valor de la propiedad docente1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocente1(String value) {
        this.docente1 = value;
    }

    /**
     * Obtiene el valor de la propiedad docente2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocente2() {
        return docente2;
    }

    /**
     * Define el valor de la propiedad docente2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocente2(String value) {
        this.docente2 = value;
    }

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaHoraEntrevista.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHoraEntrevista() {
        return fechaHoraEntrevista;
    }

    /**
     * Define el valor de la propiedad fechaHoraEntrevista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHoraEntrevista(String value) {
        this.fechaHoraEntrevista = value;
    }

    /**
     * Obtiene el valor de la propiedad autorizada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutorizada() {
        return autorizada;
    }

    /**
     * Define el valor de la propiedad autorizada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutorizada(String value) {
        this.autorizada = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAprobacion() {
        return fechaAprobacion;
    }

    /**
     * Define el valor de la propiedad fechaAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAprobacion(String value) {
        this.fechaAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad observacionesFormacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacionesFormacion() {
        return observacionesFormacion;
    }

    /**
     * Define el valor de la propiedad observacionesFormacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacionesFormacion(String value) {
        this.observacionesFormacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tecnicoFormacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTecnicoFormacion() {
        return tecnicoFormacion;
    }

    /**
     * Define el valor de la propiedad tecnicoFormacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTecnicoFormacion(String value) {
        this.tecnicoFormacion = value;
    }

    /**
     * Obtiene el valor de la propiedad origen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Define el valor de la propiedad origen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigen(String value) {
        this.origen = value;
    }

    /**
     * Obtiene el valor de la propiedad claveFormacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveFormacion() {
        return claveFormacion;
    }

    /**
     * Define el valor de la propiedad claveFormacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveFormacion(String value) {
        this.claveFormacion = value;
    }

    /**
     * Obtiene el valor de la propiedad planFormacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanFormacion() {
        return planFormacion;
    }

    /**
     * Define el valor de la propiedad planFormacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanFormacion(String value) {
        this.planFormacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idUniOrg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdUniOrg() {
        return idUniOrg;
    }

    /**
     * Define el valor de la propiedad idUniOrg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUniOrg(String value) {
        this.idUniOrg = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad denegadaDirec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenegadaDirec() {
        return denegadaDirec;
    }

    /**
     * Define el valor de la propiedad denegadaDirec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenegadaDirec(String value) {
        this.denegadaDirec = value;
    }

    /**
     * Obtiene el valor de la propiedad numActividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumActividad() {
        return numActividad;
    }

    /**
     * Define el valor de la propiedad numActividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumActividad(String value) {
        this.numActividad = value;
    }

    /**
     * Obtiene el valor de la propiedad obligatoriedad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObligatoriedad() {
        return obligatoriedad;
    }

    /**
     * Define el valor de la propiedad obligatoriedad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObligatoriedad(String value) {
        this.obligatoriedad = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Define el valor de la propiedad modalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalidad(String value) {
        this.modalidad = value;
    }

}
