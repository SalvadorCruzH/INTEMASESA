package es.emasesa.intranet.sap.base.exception;

public class SapCommunicationException extends SapException{

    public SapCommunicationException(String message) {
        super(message);
    }

    public SapCommunicationException(String message, Throwable e) {
        super(message, e);
    }
    public SapCommunicationException(Throwable cause) {
        super(cause);
    }

}
