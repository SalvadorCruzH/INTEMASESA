package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.DNFFormConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.DNFFormConfiguration",
        service = DNFFormSettings.class
)
public class DNFFormSettings {

    public String startDateViewDNFForm() {return configuration.startDateViewDNFForm();}

    public String endDateViewDNFForm() {
        return configuration.endDateViewDNFForm();
    }

    public long deptFormacionId(){
        return configuration.deptFormacionId();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(DNFFormConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile DNFFormConfiguration configuration;

}
