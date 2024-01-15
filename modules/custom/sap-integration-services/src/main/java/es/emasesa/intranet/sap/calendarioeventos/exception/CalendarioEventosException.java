package es.emasesa.intranet.sap.calendarioeventos.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class CalendarioEventosException extends SapException {

    public CalendarioEventosException(String message) {
        super(message);
    }
    public CalendarioEventosException(Throwable cause) {
        super(cause);
    }
    public CalendarioEventosException(String message, Throwable cause) {
        super(message, cause);
    }
}
