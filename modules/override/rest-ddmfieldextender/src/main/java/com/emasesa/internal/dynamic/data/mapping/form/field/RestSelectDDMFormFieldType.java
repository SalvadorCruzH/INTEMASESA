package com.emasesa.internal.dynamic.data.mapping.form.field;

import com.liferay.dynamic.data.mapping.form.field.type.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.form.field.type.DDMFormFieldTypeSettings;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
			"ddm.form.field.type.display.order:Integer=10",
			"ddm.form.field.type.group=customized",
			"ddm.form.field.type.icon=select",
			"ddm.form.field.type.label=RestSelect",
			"ddm.form.field.type.name=RestSelect",
			"ddm.form.field.type.scope=journal",
			"ddm.form.field.type.js.class.name=Liferay.DDM.Field.Select",
			"ddm.form.field.type.js.module=dddmstructure",

	},
	service = DDMFormFieldType.class
)
public class RestSelectDDMFormFieldType extends BaseDDMFormFieldType {

	@Override
	public String getModuleName() {
		return _npmResolver.resolveModuleName(
			"ddmstructure/js/Select.es");
	}

	@Override
	public String getName() {
		return "RestSelect";
	}

	@Override
	public boolean isCustomDDMFormFieldType() {
		return true;
	}

	@Override
	public Class<? extends DDMFormFieldTypeSettings>
	getDDMFormFieldTypeSettings() {

		return RestSelectDDMFormFieldTypeSettings.class;
	}


	@Reference
	private NPMResolver _npmResolver;

}