package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.AyudaEscolarFormConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.Map;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.AyudaEscolarFormConfiguration",
        service = AyudaEscolarFormSettings.class
)
public class AyudaEscolarFormSettings {

    public String startDateViewAyudaEscolarForm() {return configuration.startDateViewAyudaEscolarForm();}

    public String endDateViewAyudaEscolarForm() {
        return configuration.endDateViewAyudaEscolarForm();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(AyudaEscolarFormConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile AyudaEscolarFormConfiguration configuration;

}
