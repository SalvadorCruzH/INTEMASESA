package es.emasesa.intranet.sap.procesoseleccionlista.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class ProcesoSeleccionListaException extends SapException {

    public ProcesoSeleccionListaException(String message) {
        super(message);
    }
    public ProcesoSeleccionListaException(Throwable cause) {
        super(cause);
    }
    public ProcesoSeleccionListaException(String message, Throwable cause) {
        super(message, cause);
    }
}
