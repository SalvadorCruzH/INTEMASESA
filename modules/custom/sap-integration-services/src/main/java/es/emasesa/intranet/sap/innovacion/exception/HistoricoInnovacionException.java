package es.emasesa.intranet.sap.innovacion.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class HistoricoInnovacionException extends SapException {

    public HistoricoInnovacionException(String message) {
        super(message);
    }
    public HistoricoInnovacionException(Throwable cause) {
        super(cause);
    }

    public HistoricoInnovacionException(String message, Throwable cause) {
        super(message, cause);
    }
}
