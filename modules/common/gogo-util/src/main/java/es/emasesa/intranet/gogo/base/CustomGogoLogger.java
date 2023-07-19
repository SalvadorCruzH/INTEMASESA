package es.emasesa.intranet.gogo.base;


public class CustomGogoLogger {
    /**
     * Este código se invoca de forma especial, a través de la consola gogo por lo que el log se realiza con println.
     * @param what
     */
    @SuppressWarnings("squid:S106")
    public static final void gogoPrintln(final String what){
        gogoPrintln(what, Boolean.TRUE);
    }

    @SuppressWarnings("squid:S106")
    public static final void gogoPrintln(final String what, final boolean ifWhat){
        if (ifWhat) {
            System.out.println(what);
        }
    }
}
