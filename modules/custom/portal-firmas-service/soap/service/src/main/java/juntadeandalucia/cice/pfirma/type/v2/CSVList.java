
package juntadeandalucia.cice.pfirma.type.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CSVList complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CSVList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CSV" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}CSV" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CSVList", propOrder = {
    "csv"
})
public class CSVList {

    @XmlElement(name = "CSV")
    protected List<CSV> csv;

    /**
     * Gets the value of the csv property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the csv property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCSV().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CSV }
     * 
     * 
     */
    public List<CSV> getCSV() {
        if (csv == null) {
            csv = new ArrayList<CSV>();
        }
        return this.csv;
    }

}
