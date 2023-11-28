<#include "${templatesPath}/NAVIGATION-MACRO-FTL" />
<#if !entries?has_content>
	<#if themeDisplay.isSignedIn()>
		<div class="alert alert-info">
			<@liferay.language key="there-are-no-menu-items-to-display" />
		</div>
	</#if>
<#else>
	<#assign
		portletDisplay=themeDisplay.getPortletDisplay()
		navbarId="navbar_" + portletDisplay.getId() />
	<section class="ema-menu-nueva-solicitud">
		<div class="container">
			<form method="post">
				<div class="form-row form-row--inline">
					<div class="d-flex align-items-center ema-menu-title"><i class="fa-solid fa-file-pen"></i><h3><@liferay.language key='es.emasesa.intranet.common.menu.new-solicitud' /></h3></div>
					
					<label for="sel_solicitud" id="seleccione-solicitud" class="sr-only"><@liferay.language key='es.emasesa.intranet.common.menu.select-solicitud' /></label>
					<select id="sel_solicitud" name="sel_solicitud" aria-labelledby="seleccione-solicitud">
						<option value=''>
							<@liferay.language key='es.emasesa.intranet.common.menu.select-solicitud' />
						</option>
						<#assign navItems=entries />
						<#list navItems as navItem>
							<#if navItem.isBrowsable()>
								<#if navItem.hasBrowsableChildren()>
									<#assign childNavItem=navItem.getChildren()>
									<#list childNavItem as childItem>
										<option value='${childItem.getURL()}'>
											${childItem.getName()}
										</option>
									</#list>
								</#if>
							</#if>
						</#list>
					</select>
					<div class="btn-wrapper">
						<a href id="ir-a-solicitud" class="btn btn-primary pe-none" aria-label="Consultar" aria-disabled="true"><@liferay.language key='es.emasesa.intranet.common.menu.create-solicitud' /></a>
					</div>
				</div>
			</form>
		</div>
	</section>
	<script>
		$(document).ready(function(){
			$('#sel_solicitud').change(function(){
				var url = $(this).val();
				if(url == ''){
					$('#ir-a-solicitud').attr('href', "#");
					$('#ir-a-solicitud').addClass('pe-none');
					$('#ir-a-solicitud').attr('aria-disabled', true);
					$('#ir-a-solicitud').attr('disabled',true);
				}else{
					$('#ir-a-solicitud').attr('href', url);
					$('#ir-a-solicitud').removeClass('pe-none');
					$('#ir-a-solicitud').attr('aria-disabled', false);
					$('#ir-a-solicitud').attr('disabled',false);
				}
			});
		});

	</script>
</#if>