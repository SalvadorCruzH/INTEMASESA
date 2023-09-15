package es.emasesa.intranet.jornada.nomina.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import java.io.IOException;
import java.io.InputStream;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

public class RequestInInterceptor extends LoggingInInterceptor{
private static Log logger = LogFactoryUtil.getLog(RequestInInterceptor.class);
public static String requestXml = "";

public RequestInInterceptor() {
    super(Phase.RECEIVE);
}

@Override
public void handleMessage(Message message) throws Fault {
	InputStream is = message.getContent(InputStream.class);
    
	  CachedOutputStream bos = new CachedOutputStream();
      try {
		IOUtils.copy(is, bos);
		 bos.flush();
	      message.setContent(InputStream.class, bos.getInputStream());
	      is.close();
	      bos.registerCallback(new LoggingCallback());
	      bos.close();
	} catch (IOException e) {
		logger.error(e.getMessage(),e);
	}
     
}

private class LoggingCallback implements CachedOutputStreamCallback {
    public void onFlush(CachedOutputStream cos) {
    }

    public void onClose(CachedOutputStream cos) {
    try {
        StringBuilder builder = new StringBuilder();
        cos.writeCacheTo(builder, limit);
        requestXml = builder.toString();
        System.out.println("Response XML: \n" + requestXml);
   } catch (Exception e) {
   }
   }
}

}
