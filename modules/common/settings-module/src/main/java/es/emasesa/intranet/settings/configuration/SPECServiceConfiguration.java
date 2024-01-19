package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.SPECServiceConfiguration",
        localization = "content/Language",
        name = "es.emasesa.intranet.settings.configuration.SPECServiceConfiguration"
)
public interface SPECServiceConfiguration {

	@Meta.AD(
            deflt = "",
            name = "urlDBSpec",
            required = false
    )
    String urlDBSpec();
	
	@Meta.AD(
            deflt = "",
            name = "usuario",
            required = false
    )
    String user();
	
	@Meta.AD(
            deflt = "",
            name = "contraseña",
            required = false
    )
    String password();

    @Meta.AD(
            deflt = "",
            name = "query",
            required = false
    )
    String query();

    @Meta.AD(
            deflt = "",
            name = "subQueryNotScreen",
            required = false
    )
    String subQueryNotScreen();
}
