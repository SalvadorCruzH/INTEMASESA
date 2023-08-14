package es.emasesa.intranet.portlet.ajaxsearch.model;

import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.Properties;

public interface AjaxSearchForm {
    public Properties getDefaultProperties();
    public boolean inProcessAction(ActionRequest actionRequest, ActionResponse response, AjaxSearchDisplayContext searchDisplayContext);
    public String getFormView(PortletRequest request, PortletResponse response, AjaxSearchDisplayContext searchDisplayContext);
}
