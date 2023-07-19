package es.emasesa.intranet.adtcomponent.template;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateVariableGroup;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portlet.display.template.BasePortletDisplayTemplateHandler;
import es.emasesa.intranet.adtcomponent.constants.AdtComponentConstants;
import es.emasesa.intranet.adtcomponent.model.AdtComponent;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + AdtComponentConstants.ADT_COMPONENT_PORTLET_KEY
	},
	service = TemplateHandler.class
)
public class ADTComponentTemplateHandler
	extends BasePortletDisplayTemplateHandler {

	@Override
	public String getClassName() {
		return AdtComponent.class.getName();
	}

	@Override
	public String getName(Locale locale) {
		return AdtComponentConstants.ADT_COMPONENT_TEMPLATE_NAME_DEFAULT;
	}

	@Override
	public String getResourceName() {
		return AdtComponentConstants.ADT_COMPONENT_PORTLET_KEY;
	}

	@Override
	public Map<String, Object> getCustomContextObjects() {
		Map<String, Object> contextObjects = new HashMap<>(1);
//		contextObjects.put("assetPublisherHelper", new AssetPublisherHelper());
		return contextObjects;
	}
	
	@Override
	public Map<String, TemplateVariableGroup> getTemplateVariableGroups(
			long classPK, String language, Locale locale)
		throws Exception {

		// DEFAULT, MAY REQUIRE UPDATE
		Map<String, TemplateVariableGroup> templateVariableGroups =
			super.getTemplateVariableGroups(classPK, language, locale);

		String[] restrictedVariables = getRestrictedVariables(language);

		TemplateVariableGroup assetServicesTemplateVariableGroup =
			new TemplateVariableGroup("component-services", restrictedVariables);

		assetServicesTemplateVariableGroup.setAutocompleteEnabled(false);

		assetServicesTemplateVariableGroup.addServiceLocatorVariables(
			AssetEntryLocalService.class,
			AssetVocabularyLocalService.class,
			AssetCategoryLocalService.class, 
			AssetTagLocalService.class);

		templateVariableGroups.put(
			assetServicesTemplateVariableGroup.getLabel(),
			assetServicesTemplateVariableGroup);

		return templateVariableGroups;
	}


	@Reference
	protected Portal portal;

}