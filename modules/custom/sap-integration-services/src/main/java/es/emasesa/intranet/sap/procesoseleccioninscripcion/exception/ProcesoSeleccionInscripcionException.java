package es.emasesa.intranet.sap.procesoseleccioninscripcion.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class ProcesoSeleccionInscripcionException extends SapException {

    public ProcesoSeleccionInscripcionException(String message) {
        super(message);
    }
    public ProcesoSeleccionInscripcionException(Throwable cause) {
        super(cause);
    }
    public ProcesoSeleccionInscripcionException(String message, Throwable cause) {
        super(message, cause);
    }
}
