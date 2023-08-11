
package juntadeandalucia.cice.pfirma.query.request.v2;

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
    "documentTypeList"
})
@XmlRootElement(name = "queryDocumentTypesResponse")
public class QueryDocumentTypesResponse {

    @XmlElement(required = true)
    protected DocumentTypeList documentTypeList;

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
