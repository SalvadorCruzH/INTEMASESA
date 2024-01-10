package es.emasesa.intranet.sap.necesidadesFormacion.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class NecesidadesFormacionException extends SapException {

    public NecesidadesFormacionException(String message) {
        super(message);
    }
    public NecesidadesFormacionException(Throwable cause) {
        super(cause);
    }
    public NecesidadesFormacionException(String message, Throwable cause) {
        super(message, cause);
    }
}
