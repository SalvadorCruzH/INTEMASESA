
package com.sap.document.sap.soap.functions.mc_style;

import java.math.BigDecimal;
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
 *         &lt;element name="ComputoConFuturo" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="ComputoSinFuturo" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="ComputoSinFuturoAnnoAnteri" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="ContingenteVacaciones" type="{urn:sap-com:document:sap:rfc:functions}decimal7.2"/&gt;
 *         &lt;element name="TEmpleados" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEmpleadoJornadaResume"/&gt;
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
    "computoConFuturo",
    "computoSinFuturo",
    "computoSinFuturoAnnoAnteri",
    "contingenteVacaciones",
    "tEmpleados"
})
@XmlRootElement(name = "ZPeEmpleadoJornadaResumenResponse")
public class ZPeEmpleadoJornadaResumenResponse {

    @XmlElement(name = "ComputoConFuturo", required = true)
    protected BigDecimal computoConFuturo;
    @XmlElement(name = "ComputoSinFuturo", required = true)
    protected BigDecimal computoSinFuturo;
    @XmlElement(name = "ComputoSinFuturoAnnoAnteri", required = true)
    protected BigDecimal computoSinFuturoAnnoAnteri;
    @XmlElement(name = "ContingenteVacaciones", required = true)
    protected BigDecimal contingenteVacaciones;
    @XmlElement(name = "TEmpleados", required = true)
    protected TableOfZpeStEmpleadoJornadaResume tEmpleados;

    /**
     * Obtiene el valor de la propiedad computoConFuturo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getComputoConFuturo() {
        return computoConFuturo;
    }

    /**
     * Define el valor de la propiedad computoConFuturo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setComputoConFuturo(BigDecimal value) {
        this.computoConFuturo = value;
    }

    /**
     * Obtiene el valor de la propiedad computoSinFuturo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getComputoSinFuturo() {
        return computoSinFuturo;
    }

    /**
     * Define el valor de la propiedad computoSinFuturo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setComputoSinFuturo(BigDecimal value) {
        this.computoSinFuturo = value;
    }

    /**
     * Obtiene el valor de la propiedad computoSinFuturoAnnoAnteri.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getComputoSinFuturoAnnoAnteri() {
        return computoSinFuturoAnnoAnteri;
    }

    /**
     * Define el valor de la propiedad computoSinFuturoAnnoAnteri.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setComputoSinFuturoAnnoAnteri(BigDecimal value) {
        this.computoSinFuturoAnnoAnteri = value;
    }

    /**
     * Obtiene el valor de la propiedad contingenteVacaciones.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContingenteVacaciones() {
        return contingenteVacaciones;
    }

    /**
     * Define el valor de la propiedad contingenteVacaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContingenteVacaciones(BigDecimal value) {
        this.contingenteVacaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad tEmpleados.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEmpleadoJornadaResume }
     *     
     */
    public TableOfZpeStEmpleadoJornadaResume getTEmpleados() {
        return tEmpleados;
    }

    /**
     * Define el valor de la propiedad tEmpleados.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEmpleadoJornadaResume }
     *     
     */
    public void setTEmpleados(TableOfZpeStEmpleadoJornadaResume value) {
        this.tEmpleados = value;
    }

}
