
package juntadeandalucia.cice.pfirma.type.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para requestSign complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="requestSign">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fentry" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fstart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fexpiration" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="signType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentSignList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}documentSignList"/>
 *         &lt;element name="signLineList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}signLineList"/>
 *         &lt;element name="remitterList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}remitterList" minOccurs="0"/>
 *         &lt;element name="parameterList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}parameterList" minOccurs="0"/>
 *         &lt;element name="noticeList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}noticeList" minOccurs="0"/>
 *         &lt;element name="actionList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}actionList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestSign", propOrder = {
    "identifier",
    "subject",
    "fentry",
    "fstart",
    "fexpiration",
    "reference",
    "text",
    "signType",
    "application",
    "documentSignList",
    "signLineList",
    "remitterList",
    "parameterList",
    "noticeList",
    "actionList"
})
public class RequestSign {

    @XmlElementRef(name = "identifier", type = JAXBElement.class, required = false)
    protected JAXBElement<String> identifier;
    @XmlElementRef(name = "subject", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subject;
    @XmlElementRef(name = "fentry", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> fentry;
    @XmlElementRef(name = "fstart", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> fstart;
    @XmlElementRef(name = "fexpiration", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> fexpiration;
    @XmlElementRef(name = "reference", type = JAXBElement.class, required = false)
    protected JAXBElement<String> reference;
    @XmlElementRef(name = "text", type = JAXBElement.class, required = false)
    protected JAXBElement<String> text;
    @XmlElementRef(name = "signType", type = JAXBElement.class, required = false)
    protected JAXBElement<String> signType;
    @XmlElementRef(name = "application", type = JAXBElement.class, required = false)
    protected JAXBElement<String> application;
    @XmlElement(required = true)
    protected DocumentSignList documentSignList;
    @XmlElement(required = true)
    protected SignLineList signLineList;
    @XmlElementRef(name = "remitterList", type = JAXBElement.class, required = false)
    protected JAXBElement<RemitterList> remitterList;
    @XmlElementRef(name = "parameterList", type = JAXBElement.class, required = false)
    protected JAXBElement<ParameterList> parameterList;
    @XmlElementRef(name = "noticeList", type = JAXBElement.class, required = false)
    protected JAXBElement<NoticeList> noticeList;
    @XmlElementRef(name = "actionList", type = JAXBElement.class, required = false)
    protected JAXBElement<ActionList> actionList;

    /**
     * Obtiene el valor de la propiedad identifier.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdentifier() {
        return identifier;
    }

    /**
     * Define el valor de la propiedad identifier.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdentifier(JAXBElement<String> value) {
        this.identifier = value;
    }

    /**
     * Obtiene el valor de la propiedad subject.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubject() {
        return subject;
    }

    /**
     * Define el valor de la propiedad subject.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubject(JAXBElement<String> value) {
        this.subject = value;
    }

    /**
     * Obtiene el valor de la propiedad fentry.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFentry() {
        return fentry;
    }

    /**
     * Define el valor de la propiedad fentry.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFentry(JAXBElement<XMLGregorianCalendar> value) {
        this.fentry = value;
    }

    /**
     * Obtiene el valor de la propiedad fstart.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFstart() {
        return fstart;
    }

    /**
     * Define el valor de la propiedad fstart.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFstart(JAXBElement<XMLGregorianCalendar> value) {
        this.fstart = value;
    }

    /**
     * Obtiene el valor de la propiedad fexpiration.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFexpiration() {
        return fexpiration;
    }

    /**
     * Define el valor de la propiedad fexpiration.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFexpiration(JAXBElement<XMLGregorianCalendar> value) {
        this.fexpiration = value;
    }

    /**
     * Obtiene el valor de la propiedad reference.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReference() {
        return reference;
    }

    /**
     * Define el valor de la propiedad reference.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReference(JAXBElement<String> value) {
        this.reference = value;
    }

    /**
     * Obtiene el valor de la propiedad text.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText() {
        return text;
    }

    /**
     * Define el valor de la propiedad text.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText(JAXBElement<String> value) {
        this.text = value;
    }

    /**
     * Obtiene el valor de la propiedad signType.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignType() {
        return signType;
    }

    /**
     * Define el valor de la propiedad signType.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignType(JAXBElement<String> value) {
        this.signType = value;
    }

    /**
     * Obtiene el valor de la propiedad application.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApplication() {
        return application;
    }

    /**
     * Define el valor de la propiedad application.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApplication(JAXBElement<String> value) {
        this.application = value;
    }

    /**
     * Obtiene el valor de la propiedad documentSignList.
     * 
     * @return
     *     possible object is
     *     {@link DocumentSignList }
     *     
     */
    public DocumentSignList getDocumentSignList() {
        return documentSignList;
    }

    /**
     * Define el valor de la propiedad documentSignList.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentSignList }
     *     
     */
    public void setDocumentSignList(DocumentSignList value) {
        this.documentSignList = value;
    }

    /**
     * Obtiene el valor de la propiedad signLineList.
     * 
     * @return
     *     possible object is
     *     {@link SignLineList }
     *     
     */
    public SignLineList getSignLineList() {
        return signLineList;
    }

    /**
     * Define el valor de la propiedad signLineList.
     * 
     * @param value
     *     allowed object is
     *     {@link SignLineList }
     *     
     */
    public void setSignLineList(SignLineList value) {
        this.signLineList = value;
    }

    /**
     * Obtiene el valor de la propiedad remitterList.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemitterList }{@code >}
     *     
     */
    public JAXBElement<RemitterList> getRemitterList() {
        return remitterList;
    }

    /**
     * Define el valor de la propiedad remitterList.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemitterList }{@code >}
     *     
     */
    public void setRemitterList(JAXBElement<RemitterList> value) {
        this.remitterList = value;
    }

    /**
     * Obtiene el valor de la propiedad parameterList.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ParameterList }{@code >}
     *     
     */
    public JAXBElement<ParameterList> getParameterList() {
        return parameterList;
    }

    /**
     * Define el valor de la propiedad parameterList.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ParameterList }{@code >}
     *     
     */
    public void setParameterList(JAXBElement<ParameterList> value) {
        this.parameterList = value;
    }

    /**
     * Obtiene el valor de la propiedad noticeList.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link NoticeList }{@code >}
     *     
     */
    public JAXBElement<NoticeList> getNoticeList() {
        return noticeList;
    }

    /**
     * Define el valor de la propiedad noticeList.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link NoticeList }{@code >}
     *     
     */
    public void setNoticeList(JAXBElement<NoticeList> value) {
        this.noticeList = value;
    }

    /**
     * Obtiene el valor de la propiedad actionList.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActionList }{@code >}
     *     
     */
    public JAXBElement<ActionList> getActionList() {
        return actionList;
    }

    /**
     * Define el valor de la propiedad actionList.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActionList }{@code >}
     *     
     */
    public void setActionList(JAXBElement<ActionList> value) {
        this.actionList = value;
    }

}
