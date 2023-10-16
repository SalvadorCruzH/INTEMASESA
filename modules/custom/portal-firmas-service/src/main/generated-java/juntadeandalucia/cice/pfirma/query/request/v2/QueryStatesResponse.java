
package juntadeandalucia.cice.pfirma.query.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.StateList;


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
 *         &lt;element name="stateList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}stateList"/>
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
    "stateList"
})
@XmlRootElement(name = "queryStatesResponse")
public class QueryStatesResponse {

    @XmlElement(required = true)
    protected StateList stateList;

    /**
     * Obtiene el valor de la propiedad stateList.
     * 
     * @return
     *     possible object is
     *     {@link StateList }
     *     
     */
    public StateList getStateList() {
        return stateList;
    }

    /**
     * Define el valor de la propiedad stateList.
     * 
     * @param value
     *     allowed object is
     *     {@link StateList }
     *     
     */
    public void setStateList(StateList value) {
        this.stateList = value;
    }

}
