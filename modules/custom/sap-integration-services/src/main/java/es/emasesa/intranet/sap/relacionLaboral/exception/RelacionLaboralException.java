package es.emasesa.intranet.sap.relacionLaboral.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class RelacionLaboralException extends SapException {

    public RelacionLaboralException(String message) {
        super(message);
    }
    public RelacionLaboralException(Throwable cause) {
        super(cause);
    }
    public RelacionLaboralException(String message, Throwable cause) {
        super(message, cause);
    }
}
