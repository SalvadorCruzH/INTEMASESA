package es.emasesa.intranet.settings.templatecontributor;


import es.emasesa.intranet.base.util.CustomPermissionUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalService;
import es.emasesa.intranet.base.util.CustomDateUtil;
import es.emasesa.intranet.base.util.CustomGetterUtil;
import es.emasesa.intranet.base.util.GroupViewUtil;
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

        /*
        contextObjects.put("global_theme_settings", globalThemeSettings);
        contextObjects.put("customGetterUtil",_customGetter);
        contextObjects.put("customPermissionUtil",_customPermissionUtil);
        contextObjects.put("customDateUtil",_customDateUtil);
        contextObjects.put("groupViewUtil",_groupViewUtil);

        List<SiteNavigationMenuItem> mainMenuItems =  _siteNavigationMenuItemLocalService.getSiteNavigationMenuItems(Long.parseLong(globalThemeSettings.mainSiteNavigationMenuId()));
        List<Layout> principalMenu = mainMenuItems.stream().map(menu->_customGetter.getLayout(menu)).collect(Collectors.toList());

        List<SiteNavigationMenuItem> headerMenuItems =  _siteNavigationMenuItemLocalService.getSiteNavigationMenuItems(Long.parseLong(globalThemeSettings.headerSiteNavigationMenuId()));
        List<Layout> headerMenu = headerMenuItems.stream().map(menu->_customGetter.getLayout(menu)).collect(Collectors.toList());
        try {
            contextObjects.put("principalMenu", NavItem.fromLayouts(httpServletRequest,principalMenu,ServiceContextThreadLocal.getServiceContext().getThemeDisplay()));
            contextObjects.put("headerMenu", NavItem.fromLayouts(httpServletRequest,headerMenu,ServiceContextThreadLocal.getServiceContext().getThemeDisplay()));

        } catch (PortalException e) {

        }
        */

    }

    /*
    @Reference
    GlobalThemeSettings globalThemeSettings;
    @Reference
    CustomGetterUtil _customGetter;
    @Reference
    CustomDateUtil _customDateUtil;
    @Reference
    GroupViewUtil _groupViewUtil;
    @Reference
    CustomPermissionUtil _customPermissionUtil;
    @Reference
    SiteNavigationMenuItemLocalService _siteNavigationMenuItemLocalService;
*/
}
