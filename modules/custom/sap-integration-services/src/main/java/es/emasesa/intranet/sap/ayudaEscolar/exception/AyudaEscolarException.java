package es.emasesa.intranet.sap.ayudaEscolar.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class AyudaEscolarException extends SapException {

    public AyudaEscolarException(String message) {
        super(message);
    }
    public AyudaEscolarException(Throwable cause) {
        super(cause);
    }
    public AyudaEscolarException(String message, Throwable cause) {
        super(message, cause);
    }
}
