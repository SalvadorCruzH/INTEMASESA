package com.emasesa.internal.dynamic.data.mapping.form.field;

import com.liferay.dynamic.data.mapping.annotations.DDMForm;
import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;

@DDMForm
@DDMFormLayout(
        paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
        value = {
                @DDMFormLayoutPage(
                        title = "%basic",
                        value = {
                                @DDMFormLayoutRow(
                                        {
                                                @DDMFormLayoutColumn(
                                                        size = 12,
                                                        value = {
                                                                "label", "required", "tip"
                                                        }
                                                )
                                        }
                                )
                        }
                ),
                @DDMFormLayoutPage(
                        title = "%advanced",
                        value = {
                                @DDMFormLayoutRow(
                                        {
                                                @DDMFormLayoutColumn(
                                                        size = 12,
                                                        value = {
                                                                "restUrl", "restKey", "restValue", "dataType", "name", "showLabel",
                                                                "repeatable", "type", "validation",
                                                                "visibilityExpression",
                                                        }
                                                )
                                        }
                                )
                        }
                )
        }
)
public interface RestSelectDDMFormFieldTypeSettings
        extends DefaultDDMFormFieldTypeSettings {

    @DDMFormField(
            label = "%referencia del campo",
            type = "text"
    )
    public String name();

    @DDMFormField(
            label = "%restUrl",
            predefinedValue = "/o/ddmselect/get-journal-by-structure/global/EMA-NOTICIA/20119",
            type = "text"
    )
    public String restUrl();

    @DDMFormField(
            label = "%restKey",
            predefinedValue = "k",
            type = "text"
    )
    public String restKey();

    @DDMFormField(
            label = "%restValue",
            predefinedValue = "v",
            type = "text"
    )
    public String restValue();


}
