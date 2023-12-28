package es.emasesa.intranet.sap.empleadoBanco.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class EmpleadoBancoException extends SapException {

    public EmpleadoBancoException(String message) {
        super(message);
    }
    public EmpleadoBancoException(Throwable cause) {
        super(cause);
    }
    public EmpleadoBancoException(String message, Throwable cause) {
        super(message, cause);
    }
}
