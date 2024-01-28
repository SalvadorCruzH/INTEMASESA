<#if serviceLocator.findService("es.emasesa.intranet.settings.osgi.GlobalThemeSettings")??>
    <#assign globalThemeSettings = serviceLocator.findService("es.emasesa.intranet.settings.osgi.GlobalThemeSettings")/>
</#if>
<#if serviceLocator.findService("es.emasesa.intranet.settings.osgi.FavoritosServicesSettings")??>
    <#assign favoritosServiceSettings = serviceLocator.findService("es.emasesa.intranet.settings.osgi.FavoritosServicesSettings")/>
</#if>
<#if serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMFieldLocalService")??>
    <#assign ddmFieldLocalService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMFieldLocalService")/>
</#if>

<#assign
    is_omniadmin = permissionChecker.isOmniadmin()

    navigationItems = principalMenu???then(principalMenu, nav_items)
    has_navigation = (navigationItems?size > 0)
/>


