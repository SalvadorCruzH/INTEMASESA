package es.emasesa.intranet.settings.osgi;

import com.liferay.alloy.util.TypeUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.Arrays;
import java.util.Map;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration",
        service = ObjectsTypeSettings.class
)
public class ObjectsTypeSettings {

    public String getType(String objectEntryId) {
        if(objectEntryId.isBlank()){
            return StringPool.DASH;
        }
        if (configuration.permisos().contains(objectEntryId)){
            return "permisos";
        } else if (configuration.marcajes().contains(objectEntryId)) {
            return "marcajes";
        } else if (configuration.horasExtra().contains(objectEntryId)){
            return "horasExtra";
        } else if (configuration.pluses().contains(objectEntryId)){
            return "pluses";
        } else if (configuration.nomina().contains(objectEntryId)){
            return "nomina";
        } else if (configuration.situacionLaboral().contains(objectEntryId)){
            return "situacionLaboral";
        } else {
            return StringPool.DASH;
        }
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(ObjectsTypeConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile ObjectsTypeConfiguration configuration;

}
