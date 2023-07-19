package es.emasesa.intranet.settings.util;

import com.liferay.portal.kernel.log.Log;
import es.emasesa.intranet.base.util.LoggerUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

public class CustomSettingsReloadUtil {

    public static final void restarBundle(final ComponentContext componentContext, final String childComponentName) {
        ServiceReference<?>[] serviceReferences;
        final BundleContext bundleContext = componentContext.getBundleContext();
        try {
            serviceReferences = bundleContext.getServiceReferences((String) null, "(component.name=" + childComponentName + ")");

            if (serviceReferences != null && serviceReferences.length > 0) {
                final ServiceReference<?> first =  serviceReferences[0];
                LoggerUtil.info(LOG, "Restarting Bundle with component "+childComponentName);
                final Bundle bundle = first.getBundle();
                bundle.stop();
                bundle.start();
            }
        } catch (Exception e) {
            // Manejo de errores
        }
    }

    private static final Log LOG = LoggerUtil.getLog(CustomSettingsReloadUtil.class);

}