
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
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}Bapireturn1"/&gt;
 *         &lt;element name="TAyudasSolicitadas" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStAyudasSolicitadas"/&gt;
 *         &lt;element name="TBeneficiarios" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStBeneficiarios"/&gt;
 *         &lt;element name="TEstudios" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStEstudios"/&gt;
 *         &lt;element name="TInsAyudaEscolar" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpeStInsAyudaEscolar"/&gt;
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
    "_return",
    "tAyudasSolicitadas",
    "tBeneficiarios",
    "tEstudios",
    "tInsAyudaEscolar"
})
@XmlRootElement(name = "ZPeAyudaEscolarResponse")
public class ZPeAyudaEscolarResponse {

    @XmlElement(name = "Return", required = true)
    protected Bapireturn1 _return;
    @XmlElement(name = "TAyudasSolicitadas", required = true)
    protected TableOfZpeStAyudasSolicitadas tAyudasSolicitadas;
    @XmlElement(name = "TBeneficiarios", required = true)
    protected TableOfZpeStBeneficiarios tBeneficiarios;
    @XmlElement(name = "TEstudios", required = true)
    protected TableOfZpeStEstudios tEstudios;
    @XmlElement(name = "TInsAyudaEscolar", required = true)
    protected TableOfZpeStInsAyudaEscolar tInsAyudaEscolar;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link Bapireturn1 }
     *     
     */
    public Bapireturn1 getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link Bapireturn1 }
     *     
     */
    public void setReturn(Bapireturn1 value) {
        this._return = value;
    }

    /**
     * Obtiene el valor de la propiedad tAyudasSolicitadas.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStAyudasSolicitadas }
     *     
     */
    public TableOfZpeStAyudasSolicitadas getTAyudasSolicitadas() {
        return tAyudasSolicitadas;
    }

    /**
     * Define el valor de la propiedad tAyudasSolicitadas.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStAyudasSolicitadas }
     *     
     */
    public void setTAyudasSolicitadas(TableOfZpeStAyudasSolicitadas value) {
        this.tAyudasSolicitadas = value;
    }

    /**
     * Obtiene el valor de la propiedad tBeneficiarios.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStBeneficiarios }
     *     
     */
    public TableOfZpeStBeneficiarios getTBeneficiarios() {
        return tBeneficiarios;
    }

    /**
     * Define el valor de la propiedad tBeneficiarios.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStBeneficiarios }
     *     
     */
    public void setTBeneficiarios(TableOfZpeStBeneficiarios value) {
        this.tBeneficiarios = value;
    }

    /**
     * Obtiene el valor de la propiedad tEstudios.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStEstudios }
     *     
     */
    public TableOfZpeStEstudios getTEstudios() {
        return tEstudios;
    }

    /**
     * Define el valor de la propiedad tEstudios.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStEstudios }
     *     
     */
    public void setTEstudios(TableOfZpeStEstudios value) {
        this.tEstudios = value;
    }

    /**
     * Obtiene el valor de la propiedad tInsAyudaEscolar.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpeStInsAyudaEscolar }
     *     
     */
    public TableOfZpeStInsAyudaEscolar getTInsAyudaEscolar() {
        return tInsAyudaEscolar;
    }

    /**
     * Define el valor de la propiedad tInsAyudaEscolar.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpeStInsAyudaEscolar }
     *     
     */
    public void setTInsAyudaEscolar(TableOfZpeStInsAyudaEscolar value) {
        this.tInsAyudaEscolar = value;
    }

}
