package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.RolesConfiguration",
        name = "es.emasesa.intranet.settings.configuration.RolesConfiguration",
        localization = "content/Language"
)
public interface RolesConfiguration {

    @Meta.AD(
            deflt = "-1",
            name = "administradorRRHH.id",
            description = "administradorRRHH.desc",
            required = false
    )
    long administradorRRHHId();

}