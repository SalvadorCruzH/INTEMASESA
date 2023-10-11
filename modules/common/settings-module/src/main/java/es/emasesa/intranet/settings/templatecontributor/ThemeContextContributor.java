package es.emasesa.intranet.settings.templatecontributor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.NavItem;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import es.emasesa.intranet.base.util.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalService;
import es.emasesa.intranet.settings.osgi.GlobalThemeSettings;
import es.emasesa.intranet.settings.util.CustomGetterCategoryLayout;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.template.TemplateContextContributor;

@Component(
        immediate = true,
        property = {"type=" + TemplateContextContributor.TYPE_GLOBAL},
        service = TemplateContextContributor.class
)
public class ThemeContextContributor implements TemplateContextContributor {

    @Override
    public void prepare(Map<String, Object> contextObjects, HttpServletRequest httpServletRequest) {

        contextObjects.put("global_theme_settings", globalThemeSettings);
        contextObjects.put("customGetterUtil",_customGetter);
        contextObjects.put("customGetterCategoryLayout",customGetterCategoryLayout);
        contextObjects.put("customDateUtil",_customDateUtil);

        List<SiteNavigationMenuItem> mainMenuItems =  _siteNavigationMenuItemLocalService.getSiteNavigationMenuItems(Long.parseLong(globalThemeSettings.mainSiteNavigationMenuId()));
        List<Layout> principalMenu = mainMenuItems.stream().map(menu->_customGetter.getLayout(menu)).collect(Collectors.toList());

        try {
            contextObjects.put("principalMenu", NavItem.fromLayouts(httpServletRequest, principalMenu, ServiceContextThreadLocal.getServiceContext().getThemeDisplay()));

        } catch (PortalException e) {
            LOG.error("Error preparando el ThemeContributor", e);
        }

    }

    @Reference
    GlobalThemeSettings globalThemeSettings;
    @Reference
    CustomGetterUtil _customGetter;
    @Reference
    CustomDateUtil _customDateUtil;
    @Reference
    SiteNavigationMenuItemLocalService _siteNavigationMenuItemLocalService;
    @Reference
    CustomGetterCategoryLayout customGetterCategoryLayout;

    Log LOG = LoggerUtil.getLog(ThemeContextContributor.class);
}
