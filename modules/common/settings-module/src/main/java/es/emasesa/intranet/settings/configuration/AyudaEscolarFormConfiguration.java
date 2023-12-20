package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.AyudaEscolarFormConfiguration",
        name = "es.emasesa.intranet.settings.configuration.AyudaEscolarFormConfiguration",
        localization = "content/Language"
)
public interface AyudaEscolarFormConfiguration {

    @Meta.AD(
            deflt = "",
            name = "Fecha de Inicio de visualizacion de la Solicitud",
            required = false
    )
    String startDateViewAyudaEscolarForm();

    @Meta.AD(
            deflt = "",
            name = "Fecha de Finalizacion de visualizacion de la Solicitud",
            required = false
    )
    String endDateViewAyudaEscolarForm();

}