
package com.sap.document.sap.rfc.functions;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TABLE_OF_ZPE_ST_EMPLEADO_JORNADA_DIARIA complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TABLE_OF_ZPE_ST_EMPLEADO_JORNADA_DIARIA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="item" type="{urn:sap-com:document:sap:rfc:functions}ZPE_ST_EMPLEADO_JORNADA_DIARIA" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TABLE_OF_ZPE_ST_EMPLEADO_JORNADA_DIARIA", propOrder = {
    "item"
})
public class TABLEOFZPESTEMPLEADOJORNADADIARIA {

    protected List<ZPESTEMPLEADOJORNADADIARIA> item;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZPESTEMPLEADOJORNADADIARIA }
     * 
     * 
     */
    public List<ZPESTEMPLEADOJORNADADIARIA> getItem() {
        if (item == null) {
            item = new ArrayList<ZPESTEMPLEADOJORNADADIARIA>();
        }
        return this.item;
    }

}
