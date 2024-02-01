package es.emasesa.intranet.sap.historForm.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class HistorFormException extends SapException {

    public HistorFormException(String message) {
        super(message);
    }
    public HistorFormException(Throwable cause) {
        super(cause);
    }

    public HistorFormException(String message, Throwable cause) {
        super(message, cause);
    }
}
