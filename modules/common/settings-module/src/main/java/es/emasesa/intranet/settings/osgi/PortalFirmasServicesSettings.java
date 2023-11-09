package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.PortalFirmasServiceConfiguration;
import es.emasesa.intranet.settings.configuration.SPECServiceConfiguration;
import java.util.Map;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.PortalFirmasServiceConfiguration",
        service = PortalFirmasServicesSettings.class
)
public class PortalFirmasServicesSettings {

    public String actionsJSON() {
        return configuration.actionsJSON();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(PortalFirmasServiceConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile PortalFirmasServiceConfiguration configuration;

}
