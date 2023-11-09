package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.PortalFirmasServiceConfiguration",
        localization = "content/Language",
        name = "es.emasesa.intranet.settings.configuration.PortalFirmasServiceConfiguration"
)
public interface PortalFirmasServiceConfiguration {

	@Meta.AD(
            deflt = "",
            name = "actionsJSON",
            required = false
    )
    String actionsJSON();

}
