package es.emasesa.intranet.portlet.ajaxsearch.portlet.results;

import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContextFactory;
import es.emasesa.intranet.portlet.ajaxsearch.base.CustomAjaxSearchRegister;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.Portal;
import es.emasesa.intranet.base.model.AjaxMessage;
import es.emasesa.intranet.base.util.LoggerUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.PortletParameters;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_RESULTS,
                "mvc.command.name=/default/get-data"
        },
        service = MVCResourceCommand.class
)
public class DefaultResultsResource implements MVCResourceCommand {


    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {
        try {
            final AjaxSearchResult asr = _ajaxSearchUtil.getResultImpl(resourceRequest, _customAjaxSearchRegister);
            //final PortletParameters pp = (resourceRequest.getResourceParameters().size()==0)?resourceRequest.getRenderParameters():resourceRequest.getResourceParameters();
            final AjaxSearchDisplayContext ajaxSearchDisplayContext = _ajaxSearchDisplayContextFactory.create(
                    resourceRequest);
            final AjaxMessage ajaxMessage = asr.filterResults(
                    resourceRequest,
                    resourceResponse,
                    ajaxSearchDisplayContext);
            resourceResponse.getWriter().write(ajaxMessage.getJsonString());
        }catch (Exception e) {
            _log.error(e, e);
        }
        return Boolean.TRUE;
    }

    @Reference
    protected CustomAjaxSearchRegister _customAjaxSearchRegister;

    @Reference
    private AjaxSearchDisplayContextFactory _ajaxSearchDisplayContextFactory;
	
	@Reference
    AjaxSearchUtil _ajaxSearchUtil;

    @Reference
    Portal _portal;

    private final static Log _log = LoggerUtil.getLog(DefaultResultsResource.class);


}
