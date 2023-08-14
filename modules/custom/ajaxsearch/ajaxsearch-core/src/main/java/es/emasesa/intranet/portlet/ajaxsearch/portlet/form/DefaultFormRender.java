package es.emasesa.intranet.portlet.ajaxsearch.portlet.form;

import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContextFactory;
import es.emasesa.intranet.portlet.ajaxsearch.base.CustomAjaxSearchRegister;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_FORM,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class DefaultFormRender implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        final AjaxSearchForm asf = _ajaxSearchUtil.getFormImpl(renderRequest, _customAjaxSearchRegister);
        final String returnedPage;
        if (Validator.isNotNull(asf)) {
            final AjaxSearchDisplayContext ajaxSearchDisplayContext = _ajaxSearchDisplayContextFactory.create(renderRequest);
            renderRequest.setAttribute(AjaxSearchPortletKeys.ATTR_AJAX_SEARCH_DISPLAY_CONTEXT,
                    ajaxSearchDisplayContext);
            renderRequest.setAttribute(AjaxSearchPortletKeys.ATTR_AJAX_FORM_PAGE,
                    asf.getFormView(renderRequest, renderResponse, ajaxSearchDisplayContext));
            returnedPage = AjaxSearchPortletKeys.DEFAULT_JSP_FORM;
        } else {
            returnedPage = AjaxSearchPortletKeys.DEFAULT_JSP_PLEASE_CONFIGURE_VIEW;
        }

        return returnedPage;
    }

    private final static Log _log = LoggerUtil.getLog(DefaultFormRender.class);

    @Reference
    protected CustomAjaxSearchRegister _customAjaxSearchRegister;

    @Reference
    private AjaxSearchDisplayContextFactory _ajaxSearchDisplayContextFactory;
	
	@Reference
    AjaxSearchUtil _ajaxSearchUtil;

}
