<#if serviceLocator.findService("es.emasesa.intranet.settings.osgi.GlobalThemeSettings")??>
    <#assign globalThemeSettings = serviceLocator.findService("es.emasesa.intranet.settings.osgi.GlobalThemeSettings")/>
</#if>

<#assign
    is_omniadmin = permissionChecker.isOmniadmin()

    navigationItems = principalMenu???then(principalMenu, nav_items)

/>


