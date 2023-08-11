
package juntadeandalucia.cice.pfirma.query.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.UserList;


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
 *         &lt;element name="userList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}userList"/>
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
    "userList"
})
@XmlRootElement(name = "queryUsersAppResponse")
public class QueryUsersAppResponse {

    @XmlElement(required = true)
    protected UserList userList;

    /**
     * Obtiene el valor de la propiedad userList.
     * 
     * @return
     *     possible object is
     *     {@link UserList }
     *     
     */
    public UserList getUserList() {
        return userList;
    }

    /**
     * Define el valor de la propiedad userList.
     * 
     * @param value
     *     allowed object is
     *     {@link UserList }
     *     
     */
    public void setUserList(UserList value) {
        this.userList = value;
    }

}
