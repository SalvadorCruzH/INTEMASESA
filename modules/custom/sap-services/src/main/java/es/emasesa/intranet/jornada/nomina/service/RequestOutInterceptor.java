package es.emasesa.intranet.jornada.nomina.service;

import java.io.OutputStream;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class RequestOutInterceptor extends LoggingOutInterceptor {

    public static String requestXml = "";

    public RequestOutInterceptor() {
        super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        OutputStream out = message.getContent(OutputStream.class);
        final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(out);
        message.setContent(OutputStream.class, newOut);
        newOut.registerCallback(new LoggingCallback());
    }

    private class LoggingCallback implements CachedOutputStreamCallback {
        public void onFlush(CachedOutputStream cos) {
        }

        public void onClose(CachedOutputStream cos) {
        try {
            StringBuilder builder = new StringBuilder();
            cos.writeCacheTo(builder, limit);
            requestXml = builder.toString();
            System.out.println("Request XML: \n" + requestXml);
       } catch (Exception e) {
       }
       }
    }
}