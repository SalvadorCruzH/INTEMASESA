<footer id="footer" role="contentinfo" class="i-footer">
	<div class="container">
		<div class="i-footer__wrapper">
			<div class="i-footer__menu">
				<#assign footerNavigationPreferencesMap =
					{
						"displayStyle": "ddmTemplate_NAVBAR-BLANK-JUSTIFIED-FTL",
						"portletSetupPortletDecoratorId": "borderless"
					}
				/>
					<@liferay.navigation_menu
						default_preferences=
						freeMarkerPortletPreferences.getPreferences(footerNavigationPreferencesMap)
						instance_id="footer_menuFooter"
				/>
			</div>
		</div>
		<div class="i-footer__legal">
			<p class="i-footer__pLegal">Portal del Empleado de Emasesa Metropolitana</p>
		</div>
	</div>
</footer>