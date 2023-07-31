<footer id="footer" role="contentinfo" class="i-footer">
		<div class="i-footer__wrapper">
			<div class="i-footer__logo m-link-accessible-wrapper">
				<img class="i-footer__img" src="${images_folder}/logos/logo-emasesa-white.svg" alt="Logotipo Emasesa">
				<p class="i-footer__name">emplead@s</p>
       	 	</div>
			<div class="i-footer__menuWrapper">
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
				<div class="i-footer__menuLegal">
					<#assign footerNavigationPreferencesMap =
						{
							"displayStyle": "ddmTemplate_NAVBAR-BLANK-JUSTIFIED-FTL",
							"portletSetupPortletDecoratorId": "borderless"
						}
					/>
						<@liferay.navigation_menu
							default_preferences=
							freeMarkerPortletPreferences.getPreferences(footerNavigationPreferencesMap)
							instance_id="footer_legalMenuFooter"
					/>
				</div>
			</div>
		</div>

</footer>