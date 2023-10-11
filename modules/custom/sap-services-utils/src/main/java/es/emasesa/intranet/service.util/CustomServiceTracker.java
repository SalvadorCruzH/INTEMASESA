package es.emasesa.intranet.service.util;

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import es.emasesa.intranet.sap.proxy.SapInterfaceService;

import java.lang.reflect.Method;

import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.tracker.ServiceTracker;

@Component(
        immediate = true,
        property = {
        },
        service = CustomServiceTracker.class
)
public class CustomServiceTracker<T> {

    T service;

    public CustomServiceTracker() {
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CustomServiceTracker(Class<T> type, String methodName) {

        try {
            _serviceTracker = ServiceTrackerFactory.open(
                    FrameworkUtil.getBundle(SapInterfaceService.class), SapInterfaceService.class);
            _serviceTracker.getService();

            Class<?> invocationHandlerClass = SapInterfaceService.class;

            Method method = invocationHandlerClass.getMethod(methodName);
            this.service = (T) method.invoke(_serviceTracker.getService());

        } catch (Exception e) {
            LOG.debug("Error activando servicios, seguramente sea un problema de redeploy", e);
        }
    }

    public T getService() throws InterruptedException {
        return service;
    }

    private static final Log LOG = LogFactoryUtil.getLog(CustomServiceTracker.class);

    private static ServiceTracker<SapInterfaceService, SapInterfaceService> _serviceTracker;
}
