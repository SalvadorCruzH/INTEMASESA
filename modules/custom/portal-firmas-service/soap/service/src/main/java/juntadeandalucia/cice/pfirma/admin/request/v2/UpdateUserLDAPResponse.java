
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
 *         &lt;element name="ldap" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "ldap"
})
@XmlRootElement(name = "updateUserLDAPResponse")
public class UpdateUserLDAPResponse {

    @XmlElement(required = true)
    protected String ldap;

    /**
     * Obtiene el valor de la propiedad ldap.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdap() {
        return ldap;
    }

    /**
     * Define el valor de la propiedad ldap.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdap(String value) {
        this.ldap = value;
    }

}