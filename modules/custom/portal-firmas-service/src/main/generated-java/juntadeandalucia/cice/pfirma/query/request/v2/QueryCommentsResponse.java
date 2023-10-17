
package juntadeandalucia.cice.pfirma.query.request.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import juntadeandalucia.cice.pfirma.type.v2.CommentList;


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
 *         &lt;element name="commentList" type="{urn:juntadeandalucia:cice:pfirma:type:v2.0}commentList"/>
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
    "commentList"
})
@XmlRootElement(name = "queryCommentsResponse")
public class QueryCommentsResponse {

    @XmlElement(required = true)
    protected CommentList commentList;

    /**
     * Obtiene el valor de la propiedad commentList.
     * 
     * @return
     *     possible object is
     *     {@link CommentList }
     *     
     */
    public CommentList getCommentList() {
        return commentList;
    }

    /**
     * Define el valor de la propiedad commentList.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentList }
     *     
     */
    public void setCommentList(CommentList value) {
        this.commentList = value;
    }

}
