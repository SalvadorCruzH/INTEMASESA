package es.emasesa.intranet.portlet.ajaxsearch.portlet.results;

import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.camara",
		"javax.portlet.version=3.0",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AjaxSearch",
		"javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.init-param.view-template="+ AjaxSearchPortletKeys.DEFAULT_JSP_RESULT,
		"javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_RESULTS,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.single-page-application=true",
		"com.liferay.portlet.ajaxable=true",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.restore-current-view=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.expiration-cache=0"
	},
	service = Portlet.class
)
public class AjaxSearchResultsPortlet extends MVCPortlet {

}