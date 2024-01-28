package es.emasesa.intranet.settings.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.FavoritosConfiguration",
        localization = "content/Language",
        name = "es.emasesa.intranet.settings.configuration.FavoritosConfiguration"
)
public interface FavoritosConfiguration {
	
	@Meta.AD(
            deflt = "",
            name = "objectId del objeto favorito",
            required = false
    )
    String objectDefinitionId();
	
	@Meta.AD(
            deflt = "",
            name = "objectId del objeto enlace favorito",
            required = false
    )
    String objectEnlaceDefinitionId();
	
	@Meta.AD(
            deflt = "",
            name = "objectId del objeto favorito portada",
            required = false
    )
    String objectPortadaDefinitionId();
	
}

