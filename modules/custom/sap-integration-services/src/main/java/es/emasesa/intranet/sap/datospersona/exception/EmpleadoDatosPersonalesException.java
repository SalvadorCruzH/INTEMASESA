package es.emasesa.intranet.sap.datospersona.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class EmpleadoDatosPersonalesException extends SapException {

    public EmpleadoDatosPersonalesException(String message) {
        super(message);
    }
    public EmpleadoDatosPersonalesException(Throwable cause) {
        super(cause);
    }
}
