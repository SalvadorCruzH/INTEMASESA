package es.emasesa.intranet.sap.estructura.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class EmpleadoEstructuraException extends SapException {

    public EmpleadoEstructuraException(String message) {
        super(message);
    }
    public EmpleadoEstructuraException(Throwable cause) {
        super(cause);
    }
}
