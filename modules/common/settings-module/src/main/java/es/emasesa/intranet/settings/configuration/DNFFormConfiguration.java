package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.DNFFormConfiguration",
        name = "es.emasesa.intranet.settings.configuration.DNFFormConfiguration",
        localization = "content/Language"
)
public interface DNFFormConfiguration {

    @Meta.AD(
            deflt = "",
            name = "Fecha de Inicio de visualizacion de la Solicitud",
            required = false
    )
    String startDateViewDNFForm();

    @Meta.AD(
            deflt = "",
            name = "Fecha de Finalizacion de visualizacion de la Solicitud",
            required = false
    )
    String endDateViewDNFForm();

    @Meta.AD(
            deflt = "-1",
            name = "Departamento de formacion",
            description = "roleId del departamento de formacion",
            required = false
    )
    long deptFormacionId();

}