
package juntadeandalucia.cice.pfirma.type.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para userEmail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="userEmail">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:juntadeandalucia:cice:pfirma:type:v2.0}userJob">
 *       &lt;sequence>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="notificar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userEmail", propOrder = {
    "email",
    "notificar"
})
public class UserEmail
    extends UserJob
{

    @XmlElement(required = true, nillable = true)
    protected String email;
    @XmlElement(required = true, nillable = true)
    protected String notificar;

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad notificar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificar() {
        return notificar;
    }

    /**
     * Define el valor de la propiedad notificar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificar(String value) {
        this.notificar = value;
    }

}
