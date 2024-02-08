package es.emasesa.intranet.sap.historialTitulacion.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class HistorialTitulacionException extends SapException {

    public HistorialTitulacionException(String message) {
        super(message);
    }
    public HistorialTitulacionException(Throwable cause) {
        super(cause);
    }

    public HistorialTitulacionException(String message, Throwable cause) {
        super(message, cause);
    }
}
