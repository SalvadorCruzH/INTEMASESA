package es.emasesa.intranet.sap.datospersona.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class EmpleadoActDatosPersonalesException extends SapException {

    public EmpleadoActDatosPersonalesException(String message) {
        super(message);
    }
    public EmpleadoActDatosPersonalesException(Throwable cause) {
        super(cause);
    }
    public EmpleadoActDatosPersonalesException(String message, Throwable cause) {
        super(message, cause);
    }
}
