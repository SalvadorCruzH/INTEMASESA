
package juntadeandalucia.cice.pfirma.modify.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.RequestSign;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestSign" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}requestSign"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestSign"
})
@XmlRootElement(name = "createRequestFromSign")
public class CreateRequestFromSign {

    @XmlElement(required = true)
    protected RequestSign requestSign;

    /**
     * Obtiene el valor de la propiedad requestSign.
     * 
     * @return
     *     possible object is
     *     {@link RequestSign }
     *     
     */
    public RequestSign getRequestSign() {
        return requestSign;
    }

    /**
     * Define el valor de la propiedad requestSign.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestSign }
     *     
     */
    public void setRequestSign(RequestSign value) {
        this.requestSign = value;
    }

}
