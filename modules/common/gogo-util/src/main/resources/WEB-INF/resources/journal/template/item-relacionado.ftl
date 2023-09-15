<#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != "">
	<div class="boletin__related__item__header" style="background-image: url('${imagenPrincipal.imgPrincipal.getData()}')">
		<img class="boletin__related__item-img" alt="${imagenPrincipal.imgPrincipal.getAttribute('alt')}" data-fileentryid="${imagenPrincipal.imgPrincipal.getAttribute('fileEntryId')}" src="${imagenPrincipal.imgPrincipal.getData()}" />
	</div>
	<#else>
	<div class="boletin__related__item__header boletin__related__item__header--no-image" style="background-image: url('${images_folder}/logos/logo-emasesa-color.svg')">
		<img class="boletin__related__item-img boletin__related__item-img--no-image" src="${images_folder}/logos/logo-emasesa-color.svg" alt="" />
	</div>
</#if>
<#if (title.getData())??>
	<h3 class="boletin__title-related"><a href="${friendlyURL}" title="${title.getData()}">${title.getData()}</a></h3>
</#if>
<#if (entradilla.getData())??>
	<div class="boletin__related__date">
		${entradilla.getData()?truncate(150, '&hellip;')}
	</div>
</#if>
