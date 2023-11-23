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
	
	@Meta.AD(
            deflt = "",
            name = "posicionIdConsejeroDelegado",
            required = false
    )
    String posicionIdConsejeroDelegado();
	
	@Meta.AD(
            deflt = "",
            name = "direccionResponsableId",
            required = false
    )
    String direccionResponsableId();
	
	@Meta.AD(
            deflt = "",
            name = "subdireccionResponsableId",
            required = false
    )
    String subdireccionResponsableId();
	
	@Meta.AD(
            deflt = "",
            name = "divisionResponsableId",
            required = false
    )
    String divisionResponsableId();
	
	@Meta.AD(
            deflt = "",
            name = "dptoResponsableId",
            required = false
    )
    String dptoResponsableId();
	
	@Meta.AD(
            deflt = "",
            name = "unidadResponsableId",
            required = false
    )
    String unidadResponsableId();
	
	@Meta.AD(
            deflt = "",
            name = "seccionResponsableId",
            required = false
    )
    String seccionResponsableId();
	
	@Meta.AD(
            deflt = "",
            name = "subdirectorRRHHId",
            required = false
    )
    String subdirectorRRHHId();
	
	@Meta.AD(
            deflt = "",
            name = "directorRRHHId",
            required = false
    )
    String directorRRHHId();

}
