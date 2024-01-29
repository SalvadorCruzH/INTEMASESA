
<a href="${url.getData()}" title="${name.getData()}">
       <#if (icono.getData())?? && icono.getData() != "">
	          <img alt="${icono.getAttribute("alt")}" data-fileentryid="${icono.getAttribute("fileEntryId")}" src="${icono.getData()}" />
        </#if>
				<#if (name.getData())??>
	${name.getData()}
</#if>
</a>