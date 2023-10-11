package es.emasesa.intranet.sap.aop;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sun.xml.ws.client.ClientTransportException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

@Aspect
@Component
public class EmasesaSapErrorInterceptor {

    @AfterThrowing(pointcut = "execution(* com.sun.xml.ws.transport.http.client.HttpClientTransport.*(..))", throwing = "ex")
    public void errorInterceptor(SocketTimeoutException ex) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Error Message Interceptor started");
        }

        // DO SOMETHING HERE WITH EX
        LOG.error( ex.getCause().getMessage());

        if (LOG.isDebugEnabled()) {
            LOG.debug("Error Message Interceptor finished.");
        }
    }

    @AfterThrowing(pointcut = "execution(* com.sun.xml.ws.transport.http.client.HttpClientTransport.*(..))", throwing = "ex")
    public void errorInterceptor(ConnectException ex) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Error Message Interceptor started");
        }

        // DO SOMETHING HERE WITH EX
        LOG.error( ex.getCause().getMessage());


        if (LOG.isDebugEnabled()) {
            LOG.debug("Error Message Interceptor finished.");
        }
    }

    @AfterThrowing(pointcut = "execution(* com.sun.xml.ws.transport.http.client.HttpClientTransport.*(..))", throwing = "ex")
    public void errorInterceptor(ClientTransportException ex) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Error Message Interceptor started");
        }

        // DO SOMETHING HERE WITH EX
        LOG.error( ex.getCause().getMessage());


        if (LOG.isDebugEnabled()) {
            LOG.debug("Error Message Interceptor finished.");
        }
    }

    private static final Log LOG = LogFactoryUtil.getLog(EmasesaSapErrorInterceptor.class);
}
