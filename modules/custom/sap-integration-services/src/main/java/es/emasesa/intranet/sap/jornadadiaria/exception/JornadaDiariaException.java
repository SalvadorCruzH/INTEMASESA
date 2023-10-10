package es.emasesa.intranet.sap.jornadadiaria.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class JornadaDiariaException extends SapException {

    public JornadaDiariaException(String message) {
        super(message);
    }
    public JornadaDiariaException(Throwable cause) {
        super(cause);
    }

    public JornadaDiariaException(String message, Throwable cause) {
        super(message, cause);
    }
}
