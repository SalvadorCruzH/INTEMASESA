package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.form;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component(
        immediate = true,
        property = {},
        service = AjaxSearchForm.class
)
public class AccionesFormativasFueraDelPlanFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(AccionesFormativasFueraDelPlanFormImpl.class);

    public static final String CATEGORY_ID = "category-id";

    static {
        DFLT_PROPERTIES.put(CATEGORY_ID, "-1");
    }

    @Override
    public Properties getDefaultProperties() {
        return DFLT_PROPERTIES;
    }

    @Override
    public boolean inProcessAction(ActionRequest actionRequest, ActionResponse response,
                                   AjaxSearchDisplayContext searchDisplayContext) {
        return Boolean.TRUE;
    }

    private static final String VIEW = "/views/solicitudes/accionesformativasfueradelplan/form.jsp";
    private static final String VIEW_RRHH = "/views/solicitudes/accionesformativasfueradelplan/form_rol_rrhh.jsp";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {
        final Map<String, String> config = ajaxSearchUtil.getPropertiesMap(request);
        String roleName = (config.get(AjaxSearchPortletKeys.ROLE_RRHH_CONFIG) != null)? config.get(AjaxSearchPortletKeys.ROLE_RRHH_CONFIG) : "";

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        long groupId = themeDisplay.getScopeGroupId(); // Get current group (site) ID.

        List<Role> siteRoles = _roleLocalService.getUserGroupRoles(user.getUserId(), groupId);

        boolean isRRHHAdmin = siteRoles.stream()
                .anyMatch(role -> roleName.equals(role.getName()));

        if (isRRHHAdmin){
            return VIEW_RRHH;
        } else {
            return VIEW;
        }
    }

    @Reference
    AjaxSearchUtil ajaxSearchUtil;

    @Reference
    private RoleLocalService _roleLocalService;
}
