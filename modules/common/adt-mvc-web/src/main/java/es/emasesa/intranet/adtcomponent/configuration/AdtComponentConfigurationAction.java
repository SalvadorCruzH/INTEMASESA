package es.emasesa.intranet.adtcomponent.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import es.emasesa.intranet.adtcomponent.constants.AdtComponentConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import javax.servlet.http.HttpServletRequest;

@Component(
	configurationPolicy = ConfigurationPolicy.OPTIONAL, 
	immediate = true,
	property = {
			"javax.portlet.name="+ AdtComponentConstants.ADT_COMPONENT_PORTLET_KEY
	},
	service = ConfigurationAction.class
)
public class AdtComponentConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		return "/adtcomponent/configuration.jsp";
	}

}