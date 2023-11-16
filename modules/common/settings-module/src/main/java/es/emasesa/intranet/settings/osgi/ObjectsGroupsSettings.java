package es.emasesa.intranet.settings.osgi;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import es.emasesa.intranet.settings.configuration.ObjectsGroupsConfiguration;
import es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component(
        immediate = true,
        configurationPid = "es.emasesa.intranet.settings.configuration.ObjectsGroupsConfiguration",
        service = ObjectsGroupsSettings.class
)
public class ObjectsGroupsSettings {

    public String getMiControlHorario() {
        return configuration.miControlHorario();
    }
    public String getMiJornada() {
        return configuration.miJornada();
    }
    public String getMiSituacionLaboral() {
        return configuration.miSituacionLaboral();
    }
    public String getMiNomina() {
        return configuration.miNomina();
    }
    public String getMiPlanDeFormacion() {
        return configuration.miPlanDeFormacion();
    }
    public String getSuplencias() {
        return configuration.suplencias();
    }
    public String getReservaDeSalas() {
        return configuration.reservaDeSalas();
    }
    public String getAsistenciasTecnicas() {
        return configuration.asistenciasTecnicas();
    }
    public String getAll(){
        return Stream.of(
                    configuration.miControlHorario(),
                    configuration.miJornada(),
                    configuration.miSituacionLaboral(),
                    configuration.miNomina(),
                    configuration.miPlanDeFormacion(),
                    configuration.suplencias(),
                    configuration.reservaDeSalas(),
                    configuration.asistenciasTecnicas())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(","));
    }

    public String getSolicitudesIds(String solicitudesIds) {
        switch (solicitudesIds) {
            case "todos":
                return getAll();
            case "miControlHorario":
                return getMiControlHorario();
            case "miJornada":
                return getMiJornada();
            case "miSituacionLaboral":
                return getMiSituacionLaboral();
            case "miNomina":
                return getMiNomina();
            case "miPlanDeFormacion":
                return getMiPlanDeFormacion();
            case "suplencias":
                return getSuplencias();
            case "reservaDeSalas":
                return getReservaDeSalas();
            case "asistenciasTecnicas":
                return getAsistenciasTecnicas();
            default:
                return solicitudesIds;
        }
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = ConfigurableUtil.createConfigurable(ObjectsGroupsConfiguration.class, properties);
    }

    @Deactivate
    protected void deactivate() {
        configuration = null;
    }

    private volatile ObjectsGroupsConfiguration configuration;

}
