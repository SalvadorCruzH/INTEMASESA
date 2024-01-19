package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.SPECServiceConfiguration;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;
import java.util.Map;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.SPECServiceConfiguration",
        service = SPECServicesSettings.class
)
public class SPECServicesSettings {

    public String user() {
        return configuration.user();
    }

    public String password() {
        return configuration.password();
    }

    public String urlDBSpec() {
        return configuration.urlDBSpec();
    }
    public String query() {
        return configuration.query();
    }
    public String subQueryNotScreen() {
        return configuration.subQueryNotScreen();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(SPECServiceConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile SPECServiceConfiguration configuration;

}
