package es.emasesa.intranet.sap.centros.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class DistanciaCentrosException extends SapException {

    public DistanciaCentrosException(String message) {
        super(message);
    }
    public DistanciaCentrosException(Throwable cause) {
        super(cause);
    }
    public DistanciaCentrosException(String message, Throwable cause) {
        super(message, cause);
    }
}
