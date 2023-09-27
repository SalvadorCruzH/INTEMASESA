package es.emasesa.liferay.common.taglib.servlet;

import com.liferay.osgi.util.ServiceTrackerFactory;
//import es.emasesa.intranet.jornada.nomina.service.EmpleadoEstructuraServiceImpl;
import es.emasesa.intranet.jornada.nomina.proxy.SapInterfaceService;
import es.emasesa.intranet.jornada.nomina.service.EmpleadoEstructuraServiceImpl;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.util.tracker.ServiceTracker;

import javax.xml.bind.JAXBContext;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Map;

@Component(immediate = true, service = TestService.class)
public class TestService {

    @SuppressWarnings("unchecked")
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
        Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);

        try {
            _serviceTracker = ServiceTrackerFactory.open(
                    FrameworkUtil.getBundle(SapInterfaceService.class), SapInterfaceService.class);
            _serviceTracker.getService();

            Class<?> invocationHandlerClass = SapInterfaceService.class;

            Method method = invocationHandlerClass.getMethod("getEmpleadoEstructuraService");
            EmpleadoEstructuraServiceImpl target = (EmpleadoEstructuraServiceImpl)method.invoke(_serviceTracker.getService());
            target.getEmpleadoEstructura();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static ServiceTracker<SapInterfaceService, SapInterfaceService> _serviceTracker;
}
