package es.emasesa.intranet.sap.diplomaEventos.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class DiplomaEventosException extends SapException {

    public DiplomaEventosException(String message) {
        super(message);
    }
    public DiplomaEventosException(Throwable cause) {
        super(cause);
    }

    public DiplomaEventosException(String message, Throwable cause) {
        super(message, cause);
    }
}
