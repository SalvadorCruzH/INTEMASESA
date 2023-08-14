package es.emasesa.intranet.portlet.ajaxsearch.base;

import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;

/**
 * Factory components to create AjaxSearchDisplayContext instances.
 * @author dvegap
 *
 */
@Component(
        immediate = true,
        property = { },
        service = AjaxSearchDisplayContextFactory.class
)
public class AjaxSearchDisplayContextFactory {

	public AjaxSearchDisplayContext create(PortletRequest request) {
        return new AjaxSearchDisplayContext(request, _ajaxSearchUtil.getPropertiesMap(request));
	}
	
	@Reference
	AjaxSearchUtil _ajaxSearchUtil;
}
