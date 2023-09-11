package es.emasesa.intranet.portlet.ajaxsearch.portlet.form;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.emasesa",
		"javax.portlet.version=3.0",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AjaxSearch Form",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template="+ AjaxSearchPortletKeys.DEFAULT_JSP_FORM,
		"javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_FORM,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.supported-public-render-parameter=queryText",
		"com.liferay.portlet.ajaxable=true",
		"com.liferay.portlet.action-url-redirect=false",
		"com.liferay.portlet.single-page-application=true",
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.restore-current-view=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.expiration-cache=0"
	},
	service = Portlet.class
)
public class AjaxSearchFormPortlet extends MVCPortlet {
	
}