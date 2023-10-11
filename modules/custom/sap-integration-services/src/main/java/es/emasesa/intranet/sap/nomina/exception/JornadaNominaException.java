package es.emasesa.intranet.sap.nomina.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class JornadaNominaException extends SapException {

    public JornadaNominaException(String message) {
        super(message);
    }
    public JornadaNominaException(Throwable cause) {
        super(cause);
    }
    public JornadaNominaException(String message, Throwable cause) {
        super(message, cause);
    }
}
