
package juntadeandalucia.cice.pfirma.query.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.JobList;


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
 *         &lt;element name="jobList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}jobList"/>
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
    "jobList"
})
@XmlRootElement(name = "queryJobsResponse")
public class QueryJobsResponse {

    @XmlElement(required = true)
    protected JobList jobList;

    /**
     * Obtiene el valor de la propiedad jobList.
     * 
     * @return
     *     possible object is
     *     {@link JobList }
     *     
     */
    public JobList getJobList() {
        return jobList;
    }

    /**
     * Define el valor de la propiedad jobList.
     * 
     * @param value
     *     allowed object is
     *     {@link JobList }
     *     
     */
    public void setJobList(JobList value) {
        this.jobList = value;
    }

}
