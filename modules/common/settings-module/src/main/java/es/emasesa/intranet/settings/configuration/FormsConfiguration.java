package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

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

}