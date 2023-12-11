package es.emasesa.intranet.sap.retenciones.exception;

import es.emasesa.intranet.sap.base.exception.SapException;

public class CertificadoRetencionesException extends SapException {

    public CertificadoRetencionesException(String message) {
        super(message);
    }
    public CertificadoRetencionesException(Throwable cause) {
        super(cause);
    }
    public CertificadoRetencionesException(String message, Throwable cause) {
        super(message, cause);
    }
}
