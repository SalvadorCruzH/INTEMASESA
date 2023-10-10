package es.emasesa.intranet.sap.marcaje.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class MarcajeException extends SapException {

    public MarcajeException(String message) {super(message);}
    public MarcajeException(Throwable cause) {
        super(cause);
    }
    public MarcajeException(String message, Throwable cause) {
        super(message, cause);
    }
}
