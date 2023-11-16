package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration",
        name = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration",
        localization = "content/Language"
)
public interface ObjectsTypeConfiguration {

    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration.permisos",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String permisos();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration.marcajes",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String marcajes();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration.horasExtra",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String horasExtra();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration.pluses",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String pluses();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration.nomina",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String nomina();
    @Meta.AD(
            deflt = "",
            name = "es.emasesa.intranet.settings.configuration.ObjectsTypeConfiguration.situacionLaboral",
            description = "objectDefinitionId1,objectDefinitionId2,...  (ej: 12344,56781)",
            required = false
    )
    String situacionLaboral();

}