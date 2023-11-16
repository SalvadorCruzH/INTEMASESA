package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.ObjectsGroupsConfiguration",
        name = "es.emasesa.intranet.settings.configuration.ObjectsGroupsConfiguration",
        localization = "content/Language"
)
public interface ObjectsGroupsConfiguration {

    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.miControlHorario",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String miControlHorario();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.miJornada",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String miJornada();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.miSituacionLaboral",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String miSituacionLaboral();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.miNomina",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String miNomina();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.miPlanDeFormacion",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String miPlanDeFormacion();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.suplencias",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String suplencias();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.reservaDeSalas",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String reservaDeSalas();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.objectsGroupsConfiguration.asistenciasTecnicas",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String asistenciasTecnicas();

}