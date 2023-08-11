
package juntadeandalucia.cice.pfirma.modify.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.SignerList;


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
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="signerList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}signerList"/>
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
    "requestId",
    "signerList"
})
@XmlRootElement(name = "deleteSigners")
public class DeleteSigners {

    @XmlElement(required = true)
    protected String requestId;
    @XmlElement(required = true)
    protected SignerList signerList;

    /**
     * Obtiene el valor de la propiedad requestId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Define el valor de la propiedad requestId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Obtiene el valor de la propiedad signerList.
     * 
     * @return
     *     possible object is
     *     {@link SignerList }
     *     
     */
    public SignerList getSignerList() {
        return signerList;
    }

    /**
     * Define el valor de la propiedad signerList.
     * 
     * @param value
     *     allowed object is
     *     {@link SignerList }
     *     
     */
    public void setSignerList(SignerList value) {
        this.signerList = value;
    }

}
