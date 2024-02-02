package es.emasesa.intranet.settings.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.FormsConfiguration",
        name = "es.emasesa.intranet.settings.configuration.FormsConfiguration",
        localization = "content/Language"
)
public interface FormsConfiguration {

    @Meta.AD(
            deflt = "false",
            name = "Activar Debug OTP",
            required = false
    )
    boolean debugOtp();

    @Meta.AD(
            deflt = "0",
            name = "Tiempo de Vida de la OTP (segundos)",
            required = false
    )
    int ttlOtp();

    @Meta.AD(
            deflt = "",
            name = "Destinatarios de las Solicitudes de Consultas",
            required = false
    )
    String destinatariosSolicitudesConsultas();

    @Meta.AD(
            deflt = "",
            name = "Tipologia de las Solicitudes de Comunicacion",
            required = false
    )
    String tipologiasSolicitudesComunicacion();

    @Meta.AD(
            deflt = "",
            name = "Titulos de las Solicitudes de Comunicacion",
            required = false
    )
    String titulosSolicitudesComunicacion();
}