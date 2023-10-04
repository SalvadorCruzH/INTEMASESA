package com.emasesa.internal.dynamic.data.mapping.form.field;

import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTemplateContextContributor;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.render.DDMFormFieldRenderingContext;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
        property = "ddm.form.field.type.name=RestSelect",
        service = DDMFormFieldTemplateContextContributor.class
)
public class RestSelectDDMFormFieldTemplateContextContributor
        implements DDMFormFieldTemplateContextContributor {

    @Override
    public Map<String, Object> getParameters(
            DDMFormField ddmFormField,
            DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", (String)ddmFormFieldRenderingContext.getProperties().get("name"));
        parameters.put("restUrl", (String)ddmFormField.getProperty("restUrl"));
        parameters.put("restKey", (String)ddmFormField.getProperty("restKey"));
        parameters.put("restValue", (String)ddmFormField.getProperty("restValue"));

        return parameters;
    }

}
