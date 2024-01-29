package es.emasesa.intranet.sap.base.logging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.util.Set;

public class LogInterceptor implements SOAPHandler<SOAPMessageContext> {

    private static final Log LOG = LogFactoryUtil.getLog(LogInterceptor.class);

    @Override
    public boolean handleMessage(final SOAPMessageContext context) {
        final SOAPMessage msg = context.getMessage();
        final boolean request = ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();
        if (request) { // This is a request message.
            logMessage(msg);
        } else { // This is the response message
            logMessage(msg);
        }
        return true;
    }

    @Override
    public boolean handleFault(final SOAPMessageContext context) {
        logMessage(context.getMessage());
        return true;
    }

    private void logMessage(final SOAPMessage msg) {
        if(LOG.isDebugEnabled()) {
            try {
                // Write the message to the output stream
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                msg.writeTo(baos);
                LOG.info(baos.toString());
                baos.close();
            } catch (final Exception e) {
                LOG.error("Caught exception: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void close(final MessageContext context) {
        // Not required for logging
    }

    @Override
    public Set<QName> getHeaders() {
        // Not required for logging
        return null;
    }
}