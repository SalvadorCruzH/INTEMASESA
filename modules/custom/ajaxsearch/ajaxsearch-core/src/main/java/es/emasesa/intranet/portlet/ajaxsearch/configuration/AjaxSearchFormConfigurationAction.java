package es.emasesa.intranet.portlet.ajaxsearch.configuration;

import es.emasesa.intranet.portlet.ajaxsearch.base.CustomAjaxSearchRegister;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.util.CustomPropertiesUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Component(
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = {
                "javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_FORM
        },
        service = ConfigurationAction.class
)
public class AjaxSearchFormConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        super.processAction(portletConfig, actionRequest, actionResponse);
        final PortletPreferences prefs = actionRequest.getPreferences();

        prefs.setValue(AjaxSearchPortletKeys.PORTLET_PREFS_SEARCH_IMPL,
                actionRequest.getActionParameters().getValue(AjaxSearchPortletKeys.PORTLET_PREFS_SEARCH_IMPL));

        final String configs = actionRequest.getActionParameters().getValue(AjaxSearchPortletKeys.PORTLET_CURRENT_CONFIG);

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        if (!_customPropertiesUtil.addToCachedPortletPreference(
        		StringPool.UNDERLINE + ParamUtil.getString(actionRequest, "portletResource") + StringPool.UNDERLINE, themeDisplay.getLayout(),
        		prefs, AjaxSearchPortletKeys.PORTLET_CURRENT_CONFIG, configs)) {
            SessionErrors.add(actionRequest, "error");
        }

        prefs.store();
    }

    @Override
    public String getJspPath(HttpServletRequest httpServletRequest) {
        return "/configuration.jsp";
    }

    @Override
    public void include(PortletConfig portletConfig, HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) throws Exception {
        httpServletRequest.setAttribute("currentAjaxImpls", _customAjaxSearchRegister.getFormValues());
        httpServletRequest.setAttribute("customPropertiesUtil", new CustomPropertiesUtilWrapper());
        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    public class CustomPropertiesUtilWrapper {
        public String getStringFromProperties(Properties properties){
            return CustomPropertiesUtil.getStringFromProperties(properties);
        }
    }

    @Reference
    CustomAjaxSearchRegister _customAjaxSearchRegister;
    
    @Reference
    CustomPropertiesUtil _customPropertiesUtil;
}
