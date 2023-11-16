<#include "${templatesPath}/NAVIGATION-MACRO-FTL" />

<#if !entries?has_content>
	<#if themeDisplay.isSignedIn()>
		<div class="alert alert-info">
			<@liferay.language key="there-are-no-menu-items-to-display" />
		</div>
	</#if>
<#else>
	<#assign
		portletDisplay = themeDisplay.getPortletDisplay()

		navbarId = "navbar_" + portletDisplay.getId()
	/>
	<div id="${navbarId}">
		<#assign navItems = entries />

		<#list navItems as navItem>
			<#assign showChildrenNavItems = (displayDepth != 1) && navItem.hasBrowsableChildren() />

			<#if navItems.isBrowsable() || showChildrenNavItems>
				<div class="btn-wrapper">
					<a class="btn btn-primary pe-none" href='${navItem.getURL()}'>
						<span class="text-truncate"><@liferay.language key='es.emasesa.intranet.common.menu.create-solicitud' /></span>
					</a>
				</div>
			</#if>
		</#list>
	</div>
</#if>