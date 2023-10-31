package es.emasesa.intranet.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "emasesa-base",
        scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
        id = "es.emasesa.intranet.settings.configuration.CustomWorkflowTasksConfiguration",
        name = "es.emasesa.intranet.settings.configuration.CustomWorkflowTasksConfiguration",
        localization = "content/Language"
)
public interface CustomWorkflowTasksConfiguration {

    @Meta.AD(
            deflt = "-1",
            name = "apiKey",
            required = false
    )
    String apiKey();

    @Meta.AD(
            deflt = "",
            name = "objectMapping",
            required = false
    )
    String objectMapping();
}