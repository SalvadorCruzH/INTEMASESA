package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import es.emasesa.intranet.settings.configuration.FavoritosConfiguration;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.FavoritosConfiguration",
        service = FavoritosServicesSettings.class
)
public class FavoritosServicesSettings {
	
	 public String objectDefinitionId() {
	    return configuration.objectDefinitionId();
	 }	
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		configuration = ConfigurableUtil.createConfigurable(FavoritosConfiguration.class, properties);
	}
	
	@Deactivate
	protected void deactivate() {
	    configuration = null;
	}

	private volatile FavoritosConfiguration configuration;
}
