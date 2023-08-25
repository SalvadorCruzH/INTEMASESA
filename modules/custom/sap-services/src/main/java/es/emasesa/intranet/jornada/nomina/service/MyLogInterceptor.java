package es.emasesa.intranet.jornada.nomina.service;

import com.liferay.portal.kernel.util.Validator;
import java.io.OutputStream;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class MyLogInterceptor extends LoggingOutInterceptor {

    public MyLogInterceptor() {
        super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        OutputStream out = message.getContent(OutputStream.class);
        if(Validator.isNotNull(out)){
            final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(out);
            message.setContent(OutputStream.class, newOut);
            newOut.registerCallback(new LoggingCallback());
        }

    }

    public class LoggingCallback implements CachedOutputStreamCallback {
        public void onFlush(CachedOutputStream cos) {
        }

        public void onClose(CachedOutputStream cos) {
            try {
                StringBuilder builder = new StringBuilder();
                if(Validator.isNotNull(cos)){
                    cos.writeCacheTo(builder, limit);
                    // here comes my xml:
                    String soapXml = builder.toString();
                    System.out.println(soapXml);
                }

            } catch (Exception e) {
            }
        }
    }
}