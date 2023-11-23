package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.BaseConfiguration;
import es.emasesa.intranet.settings.configuration.ClientExtensionsConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.HashMap;
import java.util.Map;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.ClientExtensionsConfiguration",
        service = ClientExtensionsSettings.class
)
public class ClientExtensionsSettings {

    public long guestGroupId(){
        return configuration.guestGroupId();
    }
    public String objectMapping(){
        return configuration.objectMapping();
    }
    
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(ClientExtensionsConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile ClientExtensionsConfiguration configuration;

}
