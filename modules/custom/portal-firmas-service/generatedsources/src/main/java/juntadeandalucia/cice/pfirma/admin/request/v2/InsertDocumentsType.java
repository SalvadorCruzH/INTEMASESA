
package juntadeandalucia.cice.pfirma.admin.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.DocumentTypeList;


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
 *         &lt;element name="documentTypeList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}documentTypeList"/>
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
    "documentTypeList"
})
@XmlRootElement(name = "insertDocumentsType")
public class InsertDocumentsType {

    @XmlElement(required = true)
    protected String applicationId;
    @XmlElement(required = true)
    protected DocumentTypeList documentTypeList;

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
     * Obtiene el valor de la propiedad documentTypeList.
     * 
     * @return
     *     possible object is
     *     {@link DocumentTypeList }
     *     
     */
    public DocumentTypeList getDocumentTypeList() {
        return documentTypeList;
    }

    /**
     * Define el valor de la propiedad documentTypeList.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentTypeList }
     *     
     */
    public void setDocumentTypeList(DocumentTypeList value) {
        this.documentTypeList = value;
    }

}
