
package juntadeandalucia.cice.pfirma.admin.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
    "newProfile"
})
@XmlRootElement(name = "updateUserProfileResponse")
public class UpdateUserProfileResponse {

    @XmlElement(required = true)
    protected String newProfile;

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
