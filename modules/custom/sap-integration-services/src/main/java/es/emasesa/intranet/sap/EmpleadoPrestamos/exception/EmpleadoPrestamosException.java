package es.emasesa.intranet.sap.empleadoPrestamos.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class EmpleadoPrestamosException extends SapException {

    public EmpleadoPrestamosException(String message) {
        super(message);
    }
    public EmpleadoPrestamosException(Throwable cause) {
        super(cause);
    }
    public EmpleadoPrestamosException(String message, Throwable cause) {
        super(message, cause);
    }
}
