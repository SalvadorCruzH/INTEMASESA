
package com.espiralms.paw;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetDateDiffHoursResult" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getDateDiffHoursResult"
})
@XmlRootElement(name = "GetDateDiffHoursResponse")
public class GetDateDiffHoursResponse {

    @XmlElement(name = "GetDateDiffHoursResult")
    protected double getDateDiffHoursResult;

    /**
     * Obtiene el valor de la propiedad getDateDiffHoursResult.
     * 
     */
    public double getGetDateDiffHoursResult() {
        return getDateDiffHoursResult;
    }

    /**
     * Define el valor de la propiedad getDateDiffHoursResult.
     * 
     */
    public void setGetDateDiffHoursResult(double value) {
        this.getDateDiffHoursResult = value;
    }

}
