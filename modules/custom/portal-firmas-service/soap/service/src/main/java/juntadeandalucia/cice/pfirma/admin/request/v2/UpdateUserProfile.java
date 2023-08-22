
package juntadeandalucia.cice.pfirma.admin.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.User;


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
 *         &lt;element name="user" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}user"/>
 *         &lt;element name="oldProfile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newProfile" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "user",
    "oldProfile",
    "newProfile"
})
@XmlRootElement(name = "updateUserProfile")
public class UpdateUserProfile {

    @XmlElement(required = true)
    protected String applicationId;
    @XmlElement(required = true)
    protected User user;
    @XmlElement(required = true)
    protected String oldProfile;
    @XmlElement(required = true)
    protected String newProfile;

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
     * Obtiene el valor de la propiedad user.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Define el valor de la propiedad user.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Obtiene el valor de la propiedad oldProfile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldProfile() {
        return oldProfile;
    }

    /**
     * Define el valor de la propiedad oldProfile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldProfile(String value) {
        this.oldProfile = value;
    }

    /**
     * Obtiene el valor de la propiedad newProfile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewProfile() {
        return newProfile;
    }

    /**
     * Define el valor de la propiedad newProfile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewProfile(String value) {
        this.newProfile = value;
    }

}
