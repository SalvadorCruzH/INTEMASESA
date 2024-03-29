package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import java.util.Map;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.SapServicesConfiguration",
        service = SapServicesSettings.class
)
public class SapServicesSettings {

    public String userPrompt() {
        return configuration.userPrompt();
    }

    public String passwordPrompt() {
        return configuration.passwordPrompt();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(SapServicesConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile SapServicesConfiguration configuration;

}
