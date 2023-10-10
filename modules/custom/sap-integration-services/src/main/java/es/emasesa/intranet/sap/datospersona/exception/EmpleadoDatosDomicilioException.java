package es.emasesa.intranet.sap.datospersona.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class EmpleadoDatosDomicilioException extends SapException {

    public EmpleadoDatosDomicilioException(String message) {
        super(message);
    }
    public EmpleadoDatosDomicilioException(Throwable cause) {
        super(cause);
    }
    public EmpleadoDatosDomicilioException(String message, Throwable cause) {
        super(message, cause);
    }
}
