package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.BaseConfiguration",
        name = "es.emasesa.intranet.settings.configuration.BaseConfiguration",
        localization = "content/Language"
)
public interface BaseConfiguration {

    @Meta.AD(
            deflt = "-1",
            name = "com.isplife.settings.configuration.BaseConfiguration.archive-dl-type-id",
            required = false
    )
    long fileTypeId();

}