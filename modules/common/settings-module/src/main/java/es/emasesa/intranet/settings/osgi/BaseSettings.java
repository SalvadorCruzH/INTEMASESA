package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.BaseConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.HashMap;
import java.util.Map;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.BaseConfiguration",
        service = BaseSettings.class
)
public class BaseSettings {

    public long fileTypeId(){
        return configuration.fileTypeId();
    }

    public long idListaEstadosSolicitudes(){
        return configuration.idListaEstadosSolicitudes();
    }
    
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(BaseConfiguration.class, properties);
        keySecret = new HashMap<>();
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
        keySecret.clear();
        keySecret = null;
    }

    private volatile BaseConfiguration configuration;
    private Map<String, String> keySecret;


}
