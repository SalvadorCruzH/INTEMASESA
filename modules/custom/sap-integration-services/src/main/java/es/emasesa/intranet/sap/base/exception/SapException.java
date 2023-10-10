package es.emasesa.intranet.sap.base.exception;

public class SapException extends Exception{

    public SapException(String message) {
        super(message);
    }

    public SapException(String message, Throwable e) {
        super(message, e);
    }
    public SapException(Throwable cause) {
        super(cause);
    }

}
