package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.ClientExtensionsConfiguration",
        name = "es.emasesa.intranet.settings.configuration.ClientExtensionsConfiguration",
        localization = "content/Language"
)
public interface ClientExtensionsConfiguration {

    @Meta.AD(
            deflt = "-1",
            name = "guestGroupId",
            required = false,
            description = "guestGroupId.desc"
    )
    long guestGroupId();

    @Meta.AD(
            deflt = "",
            name = "objectMapping.name",
            required = false,
            description = "objectMapping.desc"
    )
    String objectMapping();

    @Meta.AD(
            deflt = "",
            name = "objectNames.name",
            required = false,
            description = "objectNames.desc"
    )
    String objectNames();

}