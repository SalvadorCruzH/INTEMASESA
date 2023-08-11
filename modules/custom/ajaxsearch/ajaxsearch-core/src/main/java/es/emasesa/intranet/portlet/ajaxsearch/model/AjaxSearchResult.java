package es.emasesa.intranet.portlet.ajaxsearch.model;

import es.emasesa.intranet.base.model.AjaxMessage;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.Properties;

public interface AjaxSearchResult {
    public Properties getDefaultProperties();
    public AjaxMessage filterResults(PortletRequest request, PortletResponse response, AjaxSearchDisplayContext ajaxSearchDisplayContext);
    public String getResultsView(PortletRequest request, PortletResponse response);
}
