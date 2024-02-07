package es.emasesa.intranet.settings.osgi;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import es.emasesa.intranet.settings.configuration.FormsConfiguration;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.FormsConfiguration",
        service = FormsSettings.class
)
public class FormsSettings {

    public boolean debugOtp() {
        return configuration.debugOtp();
    }

    public int getTtlOtp() {
        return configuration.ttlOtp();
    }

    public String destinatariosSolicitudesConsultas() {return configuration.destinatariosSolicitudesConsultas();}

    public String tipologiasSolicitudesComunicacion() {return configuration.tipologiasSolicitudesComunicacion();}

    public String titulosSolicitudesComunicacion() {return configuration.titulosSolicitudesComunicacion();}

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(FormsConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile FormsConfiguration configuration;

}
