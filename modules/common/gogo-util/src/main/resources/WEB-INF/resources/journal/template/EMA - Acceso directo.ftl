<span class="ema-favoritos__item__label">
<#if (icono.getData())?? && icono.getData() != "">
	<img alt="${icono.getAttribute("alt")}" data-fileentryid="${icono.getAttribute("fileEntryId")}" src="${icono.getData()}" />
	<#if (name.getData())??>
	    ${name.getData()}
  </#if>
</#if>
</span>