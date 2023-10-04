package es.emasesa.intranet.sap.jornadadiaria.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class MarcajeException extends SapException {

    public MarcajeException(String message) {
        super(message);
    }
    public MarcajeException(Throwable cause) {
        super(cause);
    }
}
