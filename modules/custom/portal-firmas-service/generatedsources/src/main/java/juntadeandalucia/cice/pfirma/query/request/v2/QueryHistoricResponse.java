
package juntadeandalucia.cice.pfirma.query.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.HistoricList;


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
 *         &lt;element name="historicList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}historicList"/>
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
    "historicList"
})
@XmlRootElement(name = "queryHistoricResponse")
public class QueryHistoricResponse {

    @XmlElement(required = true)
    protected HistoricList historicList;

    /**
     * Obtiene el valor de la propiedad historicList.
     * 
     * @return
     *     possible object is
     *     {@link HistoricList }
     *     
     */
    public HistoricList getHistoricList() {
        return historicList;
    }

    /**
     * Define el valor de la propiedad historicList.
     * 
     * @param value
     *     allowed object is
     *     {@link HistoricList }
     *     
     */
    public void setHistoricList(HistoricList value) {
        this.historicList = value;
    }

}
