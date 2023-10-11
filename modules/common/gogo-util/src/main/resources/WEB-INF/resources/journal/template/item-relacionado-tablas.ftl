<td class="aside-news" width="33.3333%" style="width: 33.3333%; vertical-align: top;border-left: 7px solid #d6e7f4;border-right: 7px solid #d6e7f4;" align="top">
	<#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != "">
		<img class="news-img" style="max-width: 100%;" src="${imagenPrincipal.imgPrincipal.getData()}" alt="" height="110" width="100%" />
		<#else>
		<img class="news-img" style="max-width: 100%;" src="${images_folder}/logos/imagen-generica-noticia.png" alt="" height="110" width="100%" />
	</#if>
	<#if (title.getData())??>
		<h4 class="news-title" style="margin-top: 5px;font-size: 15px;">
			<a href="${friendlyURL}" title="${title.getData()}" style="color: #0f71ca;text-decoration:none;">${title.getData()}</a>
		</h4>
	</#if>
</td>
