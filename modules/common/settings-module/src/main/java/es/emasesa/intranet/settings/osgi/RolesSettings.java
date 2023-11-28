package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.BaseConfiguration;
import es.emasesa.intranet.settings.configuration.RolesConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.HashMap;
import java.util.Map;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.RolesConfiguration",
        service = RolesSettings.class
)
public class RolesSettings {

    public long administradorRRHHId(){
        return configuration.administradorRRHHId();
    }
    
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(RolesConfiguration.class, properties);
        keySecret = new HashMap<>();
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
        keySecret.clear();
        keySecret = null;
    }

    private volatile RolesConfiguration configuration;
    private Map<String, String> keySecret;


}
