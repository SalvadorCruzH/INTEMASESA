package es.emasesa.intranet.sap.subordinados.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class SubordinadosException extends SapException {

    public SubordinadosException(String message) {
        super(message);
    }
    public SubordinadosException(Throwable cause) {
        super(cause);
    }
    public SubordinadosException(String message, Throwable cause) {
        super(message, cause);
    }
}
