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

}