package es.emasesa.intranet.sap.resumenanual.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class ResumenAnualException extends SapException {

    public ResumenAnualException(String message) {
        super(message);
    }
    public ResumenAnualException(Throwable cause) {
        super(cause);
    }
}
