package es.emasesa.intranet.portlet.ajaxsearch.portlet.results;

import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContextFactory;
import es.emasesa.intranet.portlet.ajaxsearch.base.CustomAjaxSearchRegister;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.LoggerUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.PortletParameters;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_RESULTS,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class DefaultResultsRender implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        final AjaxSearchResult asr = _ajaxSearchUtil.getResultImpl(renderRequest, _customAjaxSearchRegister);
        final String returnedPage;
        if (Validator.isNotNull(asr)) {
            final AjaxSearchDisplayContext ajaxSearchDisplayContext = _ajaxSearchDisplayContextFactory.create(
                    renderRequest);
            renderRequest.setAttribute(
                    AjaxSearchPortletKeys.ATTR_AJAX_SEARCH_DISPLAY_CONTEXT,
                    ajaxSearchDisplayContext);
            renderRequest.setAttribute(
                    AjaxSearchPortletKeys.ATTR_AJAX_RESULTS_PAGE,
                    asr.getResultsView(renderRequest, renderResponse));
            returnedPage = AjaxSearchPortletKeys.DEFAULT_JSP_RESULT;
        } else {
            returnedPage = AjaxSearchPortletKeys.DEFAULT_JSP_PLEASE_CONFIGURE_VIEW;
        }
        //renderResponse.reset();
        HttpServletRequest servletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
    	if(!Constants.EDIT.equals(servletRequest.getParameter("p_l_mode"))) {
    		renderResponse.reset();
    	}
        return returnedPage;
    }

    @Reference
    protected CustomAjaxSearchRegister _customAjaxSearchRegister;

    @Reference
    private AjaxSearchDisplayContextFactory _ajaxSearchDisplayContextFactory;

	@Reference
    AjaxSearchUtil _ajaxSearchUtil;

    @Reference
    Portal _portal;

    private final static Log _log = LoggerUtil.getLog(DefaultResultsRender.class);

}
