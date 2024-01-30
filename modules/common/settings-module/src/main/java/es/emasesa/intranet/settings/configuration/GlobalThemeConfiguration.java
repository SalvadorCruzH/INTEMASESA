package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.GlobalThemeConfiguration",
        name = "es.emasesa.intranet.settings.configuration.GlobalThemeConfiguration",
        localization = "content/Language"
)
public interface GlobalThemeConfiguration {

    @Meta.AD(
            deflt = "-1",
            name = "mainSiteNavigationMenuId",
            required = false
    )
    String mainSiteNavigationMenuId();

    @Meta.AD(
            deflt = "-1",
            name = "lastModifiedCategoryId",
            required = false
    )
    String lastModifiedCategoryId();

    @Meta.AD(
            deflt = "-1",
            name = "eventosCalendarioCategoryId",
            required = false
    )
    long eventosCalendarioCategoryId();
}