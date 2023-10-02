<section id="content" class="content">
	<h2 class="hide-accessible sr-only" role="heading" aria-level="1">${htmlUtil.escape(the_title)}</h2>

	<#if selectable>
		<@liferay_util["include"] page=content_include />
	<#else>
		${portletDisplay.recycle()}

		${portletDisplay.setTitle(the_title)}

	    <@liferay_theme["wrap-portlet"] page="portlet.ftl">
			<@liferay_util["include"] page=content_include />
		</@>
	</#if>
</section>
