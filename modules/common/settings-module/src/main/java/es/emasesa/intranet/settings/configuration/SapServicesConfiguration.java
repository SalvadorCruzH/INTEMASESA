package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.SapServicesConfiguration",
        name = "es.emasesa.intranet.settings.configuration.SapServicesConfiguration",
        localization = "content/Language"
)
public interface SapServicesConfiguration {


    @Meta.AD(
            deflt = "",
            name = "userPrompt",
            required = false
    )
    String userPrompt();

    @Meta.AD(
            deflt = "",
            name = "passwordPrompt",
            required = false
    )
    String passwordPrompt();

    @Meta.AD(
            deflt = "",
            name = "empleadoEstructuraEndpoint",
            required = false
    )
    String empleadoEstructuraEndpoint();

    @Meta.AD(
            deflt = "",
            name = "jornadaNominaEndpoint",
            required = false
    )
    String jornadaNominaEndpoint();
    @Meta.AD(
            deflt = "",
            name = "MarcajeEndpoint",
            required = false
    )
    String marcajeEndpoint();

    @Meta.AD(
            deflt = "",
            name = "JornadaDiariaEndpoint",
            required = false
    )
    String jornadaDiariaEndpoint();

    @Meta.AD(
            deflt = "",
            name = "EmpleadoDatosPersonalesEndpoint",
            required = false
    )
    String empleadoDatosPersonalesEndpoint();

    @Meta.AD(
            deflt = "",
            name = "EmpleadoDatosDomicilioEndpoint",
            required = false
    )
    String empleadoDatosDomicilioEndpoint();
}