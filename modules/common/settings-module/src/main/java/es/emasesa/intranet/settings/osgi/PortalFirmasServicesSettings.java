package es.emasesa.intranet.settings.osgi;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import es.emasesa.intranet.settings.configuration.PortalFirmasServiceConfiguration;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.PortalFirmasServiceConfiguration",
        service = PortalFirmasServicesSettings.class
)
public class PortalFirmasServicesSettings {

    public String actionsJSON() {
        return configuration.actionsJSON();
    }
    
    public String posicionIdConsejeroDelegado() {
        return configuration.posicionIdConsejeroDelegado();
    }
    
    public String direccionResponsableId() {
        return configuration.direccionResponsableId();
    }
    
    public String subdireccionResponsableId() {
        return configuration.subdireccionResponsableId();
    }
    
    public String divisionResponsableId() {
        return configuration.divisionResponsableId();
    }
    
    public String dptoResponsableId() {
        return configuration.dptoResponsableId();
    }
    
    public String unidadResponsableId() {
        return configuration.unidadResponsableId();
    }
    
    public String seccionResponsableId() {
        return configuration.seccionResponsableId();
    }
    
    public String subdirectorRRHHId() {
        return configuration.subdirectorRRHHId();
    }
    
    public String directorRRHHId() {
        return configuration.directorRRHHId();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(PortalFirmasServiceConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile PortalFirmasServiceConfiguration configuration;

}
