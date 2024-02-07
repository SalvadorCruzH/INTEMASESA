package es.emasesa.intranet.sap.regina.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class ReginaException extends SapException {

    public ReginaException(String message) {
        super(message);
    }
    public ReginaException(Throwable cause) {
        super(cause);
    }
    public ReginaException(String message, Throwable cause) {
        super(message, cause);
    }
}
