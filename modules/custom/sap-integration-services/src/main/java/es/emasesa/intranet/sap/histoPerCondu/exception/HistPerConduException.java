package es.emasesa.intranet.sap.histoPerCondu.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class HistPerConduException extends SapException {

    public HistPerConduException(String message) {
        super(message);
    }
    public HistPerConduException(Throwable cause) {
        super(cause);
    }

    public HistPerConduException(String message, Throwable cause) {
        super(message, cause);
    }
}
