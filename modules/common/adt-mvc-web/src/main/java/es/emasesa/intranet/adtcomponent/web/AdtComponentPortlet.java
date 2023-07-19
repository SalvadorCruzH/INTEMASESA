package es.emasesa.intranet.adtcomponent.web;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import es.emasesa.intranet.adtcomponent.constants.AdtComponentConstants;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.camara",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Adt Component Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/adtcomponent/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.preferences=classpath:/META-INF/portlet-preferences/default-portlet-preferences.xml",
		"javax.portlet.name=" + AdtComponentConstants.ADT_COMPONENT_PORTLET_KEY
	},
	service = Portlet.class
)
public class AdtComponentPortlet extends MVCPortlet {
}
