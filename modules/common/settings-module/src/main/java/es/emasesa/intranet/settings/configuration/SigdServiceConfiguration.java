package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.SigdServiceConfiguration",
        localization = "content/Language",
        name = "es.emasesa.intranet.settings.configuration.SigdServiceConfiguration"
)
public interface SigdServiceConfiguration {
	
	@Meta.AD(
            deflt = "",
            name = "insertarDocumentoEndPoint",
            required = false
    )
    String insertarDocumentoEndPoint();
	
	@Meta.AD(
            deflt = "",
            name = "obtenerElementoEndPoint",
            required = false
    )
    String obtenerElementoEndPoint();
	
	@Meta.AD(
            deflt = "",
            name = "obtenerContenidoEndPoint",
            required = false
    )
    String obtenerContenidoEndPoint();
	
	@Meta.AD(
            deflt = "",
            name = "usuario",
            required = false
    )
    String getUser();
	
	@Meta.AD(
            deflt = "",
            name = "contraseña",
            required = false
    )
    String getPassword();

}
