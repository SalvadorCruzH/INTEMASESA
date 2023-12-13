package es.emasesa.intranet.sap.subordinados.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class CiertosDatosEstructuraException extends SapException {

    public CiertosDatosEstructuraException(String message) {
        super(message);
    }
    public CiertosDatosEstructuraException(Throwable cause) {
        super(cause);
    }
    public CiertosDatosEstructuraException(String message, Throwable cause) {
        super(message, cause);
    }
}
