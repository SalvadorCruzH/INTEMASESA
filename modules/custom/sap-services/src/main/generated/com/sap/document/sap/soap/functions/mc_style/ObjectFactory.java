
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sap.document.sap.soap.functions.mc_style package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Return_QNAME = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "Return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sap.document.sap.soap.functions.mc_style
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bapireturn1 }
     * 
     */
    public Bapireturn1 createBapireturn1() {
        return new Bapireturn1();
    }

    /**
     * Create an instance of {@link ZPeActJornadaNomina }
     * 
     */
    public ZPeActJornadaNomina createZPeActJornadaNomina() {
        return new ZPeActJornadaNomina();
    }

    /**
     * Create an instance of {@link ZpeStActJornadaNomina }
     * 
     */
    public ZpeStActJornadaNomina createZpeStActJornadaNomina() {
        return new ZpeStActJornadaNomina();
    }

    /**
     * Create an instance of {@link ZPeActJornadaNominaResponse }
     * 
     */
    public ZPeActJornadaNominaResponse createZPeActJornadaNominaResponse() {
        return new ZPeActJornadaNominaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bapireturn1 }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Bapireturn1 }{@code >}
     */
    @XmlElementDecl(namespace = "urn:sap-com:document:sap:soap:functions:mc-style", name = "Return")
    public JAXBElement<Bapireturn1> createReturn(Bapireturn1 value) {
        return new JAXBElement<Bapireturn1>(_Return_QNAME, Bapireturn1 .class, null, value);
    }

}
