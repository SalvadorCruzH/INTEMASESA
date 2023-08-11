
package juntadeandalucia.cice.pfirma.query.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.DetailedJobList;


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
 *         &lt;element name="detailedJobList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}detailedJobList"/>
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
    "detailedJobList"
})
@XmlRootElement(name = "queryDetailedJobsUserResponse")
public class QueryDetailedJobsUserResponse {

    @XmlElement(required = true)
    protected DetailedJobList detailedJobList;

    /**
     * Obtiene el valor de la propiedad detailedJobList.
     * 
     * @return
     *     possible object is
     *     {@link DetailedJobList }
     *     
     */
    public DetailedJobList getDetailedJobList() {
        return detailedJobList;
    }

    /**
     * Define el valor de la propiedad detailedJobList.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailedJobList }
     *     
     */
    public void setDetailedJobList(DetailedJobList value) {
        this.detailedJobList = value;
    }

}
