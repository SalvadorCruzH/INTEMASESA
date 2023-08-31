<header class="i-header" id="banner" role="banner">
	<div class="container-fluid">
		<div class="i-header__wrapper">
			<button id="i-menuButton" aria-label="Menú" class="i-header__btnMenu btn m-btn--noSytles">
				<i class="fa-solid fa-bars fa-lg"></i>
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
			<button class="i-header__btnAdmin btn btn-primary">
				<i class="fa-regular fa-bell mr-2"></i>
				<@liferay.language key='es.emasesa.intranet.common.administrar-tareas' />
			</button>
		</div>
		<nav class="i-header__userNav">
			<ul class="i-header__userList">
				<li class="i-header__userItem selected" class="i-header__userItem selected">
					<a href="" class="i-header__userLink">
						<i class="i-icon i-icon--grey fa-solid fa-house"></i>
						<span class="sr-only">Home</span>
					</a>
				</li>
				<li class="i-header__userItem">
					<a href="" class="i-header__userLink">
						<i class="i-icon i-icon--grey fa-solid fa-bell"></i>
						<span class="sr-only">Notificaciones</span>
					</a>
				</li>
				<li class="i-header__userItem">
					<a class="i-header__userLink" href="">
						<i class="i-icon i-icon--grey fa-solid fa-envelope"></i>
						<span class="sr-only">Correo</span>
					</a>
				</li>
				<li class="i-header__userItem">
					<a href="" class="i-header__userLink">
						<i class="i-icon i-icon--grey fa-solid fa-users"></i>
						<span class="sr-only">Correo</span>
					</a>
				</li>
				<li class="i-header__userItem">
					<a href="" class="i-header__userLink">
						<i class="i-icon i-icon--grey fa-solid fa-grip"></i>
						<span class="sr-only">Correo</span>
					</a>
				</li>
				<li class="i-header__userItem">
					<a href="" class="i-header__userLink">
						<i class="i-icon i-icon--grey fa-solid fa-heart"></i>
						<span class="sr-only">Correo</span>
					</a>
				</li>
				<li class="i-header__userItem">
					<a href="" class="i-header__userLink">
						<i class="i-icon i-icon--grey fa-solid fa-user"></i>
						<span class="sr-only">Correo</span>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</header>
<div id="i-menuMobile" class="i-menuMobile hidden visuallyhidden">
    <div class="i-menuMobile__wrapper">
		<div class="">
			<div class="i-menuMobile__btnWrapper">
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
				<button id="i-menuMobile__closeButton" aria-label="<@liferay.language key='close' />" class="btn m-btn--noSytles">
					<i class="fa-solid fa-xmark fa-2xl"></i>
				</button>
			</div>
			<#if has_navigation && is_setup_complete>
				<div class="i-menuMobile__navWrapper">
					<#include "${full_templates_path}/navigation_mobile.ftl" />
				</div>
        	</#if>
        </div>
		<button class="i-header__btnAdmin btn btn-primary">
			<i class="fa-regular fa-bell mr-2"></i>
			<@liferay.language key='es.emasesa.intranet.common.administrar-tareas' />
		</button>
    </div>
</div>