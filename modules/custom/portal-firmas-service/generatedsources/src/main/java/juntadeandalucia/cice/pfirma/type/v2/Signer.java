
package juntadeandalucia.cice.pfirma.type.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para signer complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="signer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userJob" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}userJob"/>
 *         &lt;element name="state" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}state" minOccurs="0"/>
 *         &lt;element name="fstate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signer", propOrder = {
    "userJob",
    "state",
    "fstate"
})
public class Signer {

    @XmlElement(required = true)
    protected UserJob userJob;
    @XmlElementRef(name = "state", type = JAXBElement.class, required = false)
    protected JAXBElement<State> state;
    @XmlElementRef(name = "fstate", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> fstate;

    /**
     * Obtiene el valor de la propiedad userJob.
     * 
     * @return
     *     possible object is
     *     {@link UserJob }
     *     
     */
    public UserJob getUserJob() {
        return userJob;
    }

    /**
     * Define el valor de la propiedad userJob.
     * 
     * @param value
     *     allowed object is
     *     {@link UserJob }
     *     
     */
    public void setUserJob(UserJob value) {
        this.userJob = value;
    }

    /**
     * Obtiene el valor de la propiedad state.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link State }{@code >}
     *     
     */
    public JAXBElement<State> getState() {
        return state;
    }

    /**
     * Define el valor de la propiedad state.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link State }{@code >}
     *     
     */
    public void setState(JAXBElement<State> value) {
        this.state = value;
    }

    /**
     * Obtiene el valor de la propiedad fstate.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFstate() {
        return fstate;
    }

    /**
     * Define el valor de la propiedad fstate.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFstate(JAXBElement<XMLGregorianCalendar> value) {
        this.fstate = value;
    }

}
