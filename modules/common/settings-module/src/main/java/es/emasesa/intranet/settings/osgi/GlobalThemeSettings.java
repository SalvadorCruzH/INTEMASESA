package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.GlobalThemeConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.GlobalThemeConfiguration",
        service = GlobalThemeSettings.class
)
public class GlobalThemeSettings {

    public String mainSiteNavigationMenuId() {
        return configuration.mainSiteNavigationMenuId();
    }

    public String lastModifiedCategoryId() {
        return configuration.lastModifiedCategoryId();
    }
    public long eventosCalendarioCategoryId() {
        return configuration.eventosCalendarioCategoryId();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(GlobalThemeConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile GlobalThemeConfiguration configuration;

}
