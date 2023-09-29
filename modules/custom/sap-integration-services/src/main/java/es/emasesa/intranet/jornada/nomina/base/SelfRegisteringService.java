package es.emasesa.intranet.jornada.nomina.base;

import com.liferay.petra.reflect.AnnotationLocator;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ModuleFrameworkPropsValues;
import com.liferay.portal.kernel.util.ProxyUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public interface SelfRegisteringService extends InitializingBean, BeanNameAware {

    String getBeanName();

    void setServiceRegistration(final ServiceRegistration serviceRegistration);

    default void afterPropertiesSet() throws Exception {
        registerSelf();
    }

    default void registerSelf() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        setServiceRegistration(registerService(getBeanName(), this));
    }

    default ServiceRegistration registerService(final String beanName,
                                                final Object service) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Bundle bundle = FrameworkUtil.getBundle(service.getClass());
        BundleContext bundleContext = bundle.getBundleContext();

        Class<?> clazz = service.getClass();

        if (ProxyUtil.isProxyClass(clazz)) {
            InvocationHandler invocationHandler = ProxyUtil.getInvocationHandler(service);
            Class<?> invocationHandlerClass = invocationHandler.getClass();

            Method method = invocationHandlerClass.getMethod("getTarget");
            Object target = method.invoke(invocationHandler);
            clazz = target.getClass();
        }

        OSGiBeanProperties osgiBeanProperties = AnnotationLocator.locate(clazz,
                OSGiBeanProperties.class);
        Set<String> names = OSGiBeanProperties.Service.interfaceNames(service,
                osgiBeanProperties, ModuleFrameworkPropsValues.MODULE_FRAMEWORK_SERVICES_IGNORED_INTERFACES);

        if (names.isEmpty()) {
            return null;
        }
        HashMapDictionary<String, Object> properties =
                HashMapDictionaryBuilder.<String, Object>put("bean.id", beanName).put(
                        "origin.bundle.symbolic.name",
                        () -> {
                            return bundle.getSymbolicName();
                        }
                ).build();

        if (osgiBeanProperties != null) {
            properties.putAll(OSGiBeanProperties.Convert.toMap(osgiBeanProperties));
        }

        return bundleContext.registerService(names.toArray(new String[0]),
                service, properties);
    }
}