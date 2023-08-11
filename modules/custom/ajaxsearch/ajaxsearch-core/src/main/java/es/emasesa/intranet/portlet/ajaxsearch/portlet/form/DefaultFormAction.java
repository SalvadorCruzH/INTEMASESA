package es.emasesa.intranet.portlet.ajaxsearch.portlet.form;

import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContextFactory;
import es.emasesa.intranet.portlet.ajaxsearch.base.CustomAjaxSearchRegister;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_FORM,
                "mvc.command.name=/default/search"
        },
        service = MVCActionCommand.class
)
public class DefaultFormAction extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        final AjaxSearchForm asf = _ajaxSearchUtil.getFormImpl(actionRequest, _customAjaxSearchRegister);
        if (Validator.isNotNull(asf)){
        	_ajaxSearchUtil.addSearchActionAttributes(actionRequest);
            final AjaxSearchDisplayContext ajaxSearchDisplayContext = _ajaxSearchDisplayContextFactory.create(actionRequest);
            asf.inProcessAction(actionRequest, actionResponse, ajaxSearchDisplayContext);
        }
        SessionMessages.add(actionRequest, _portal.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
    }

    private final static Log _log = LoggerUtil.getLog(DefaultFormAction.class);

    @Reference
    protected CustomAjaxSearchRegister _customAjaxSearchRegister;

    @Reference
    private AjaxSearchDisplayContextFactory _ajaxSearchDisplayContextFactory;
	
	@Reference
    AjaxSearchUtil _ajaxSearchUtil;

    @Reference
    Portal _portal;
}
