package es.emasesa.intranet.sap.contratosCategorias.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class ContratosCategoriasException extends SapException {

    public ContratosCategoriasException(String message) {
        super(message);
    }
    public ContratosCategoriasException(Throwable cause) {
        super(cause);
    }

    public ContratosCategoriasException(String message, Throwable cause) {
        super(message, cause);
    }
}
