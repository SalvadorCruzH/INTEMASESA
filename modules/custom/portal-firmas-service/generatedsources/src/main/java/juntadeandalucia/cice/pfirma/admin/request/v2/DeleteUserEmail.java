
package juntadeandalucia.cice.pfirma.admin.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.UserEmail;


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
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userEmail" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}userEmail"/>
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
    "applicationId",
    "userEmail"
})
@XmlRootElement(name = "deleteUserEmail")
public class DeleteUserEmail {

    @XmlElement(required = true)
    protected String applicationId;
    @XmlElement(required = true)
    protected UserEmail userEmail;

    /**
     * Obtiene el valor de la propiedad applicationId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Define el valor de la propiedad applicationId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * Obtiene el valor de la propiedad userEmail.
     * 
     * @return
     *     possible object is
     *     {@link UserEmail }
     *     
     */
    public UserEmail getUserEmail() {
        return userEmail;
    }

    /**
     * Define el valor de la propiedad userEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link UserEmail }
     *     
     */
    public void setUserEmail(UserEmail value) {
        this.userEmail = value;
    }

}
