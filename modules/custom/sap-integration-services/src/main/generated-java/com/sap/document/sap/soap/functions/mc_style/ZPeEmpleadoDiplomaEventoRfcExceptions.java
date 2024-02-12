
package com.sap.document.sap.soap.functions.mc_style;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ZPeEmpleadoDiplomaEvento.RfcExceptions.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="ZPeEmpleadoDiplomaEvento.RfcExceptions"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ErrorSpool"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ZPeEmpleadoDiplomaEvento.RfcExceptions")
@XmlEnum
public enum ZPeEmpleadoDiplomaEventoRfcExceptions {

    @XmlEnumValue("ErrorSpool")
    ERROR_SPOOL("ErrorSpool");
    private final String value;

    ZPeEmpleadoDiplomaEventoRfcExceptions(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ZPeEmpleadoDiplomaEventoRfcExceptions fromValue(String v) {
        for (ZPeEmpleadoDiplomaEventoRfcExceptions c: ZPeEmpleadoDiplomaEventoRfcExceptions.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
