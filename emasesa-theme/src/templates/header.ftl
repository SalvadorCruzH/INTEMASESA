<header class="i-header" id="banner" role="banner">
	<div class="container-fluid">
		<div class="i-header__wrapper">
		<button class="i-header__btnMenu btn m-btn--noSytles">
			<i class="fa-solid fa-bars"></i>
		</button>
			<div id="heading">
				<div aria-level="1" class="i-header__logo site-title" role="heading">
					<a class="i-header__logoLink" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						<img class="i-logo__img i-footer__img" src="${images_folder}/logos/logo-emasesa-color.svg" alt="Logotipo Emasesa">
						<span class="i-logo__name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
							emplead@s
						</span> 
					</a>
				</div>
			</div>
			<div class="i-header__navSearch">
				<#if has_navigation && is_setup_complete>
					<#include "${full_templates_path}/navigation.ftl" />
				</#if>
				<button class="btn m-btn--noSytles"aria-label="Abrir busqueda"><i class="fa-solid fa-magnifying-glass fa-lg"></i></button>
			</div>
			<button class="i-header__btnAdmin btn btn-primary"><i class="fa-regular fa-bell mr-2"></i><@liferay.language key='es.emasesa.intranet.common.administrar-tareas' /></button>
		</div>
	</div>
</header>