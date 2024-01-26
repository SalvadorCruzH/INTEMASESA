<#assign dlFileEntryLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") />
<#assign classNameLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.ClassNameLocalService") />
<#assign custonNameSpace = "fav"+random.nextInt()/>
<@emasesa.favoritosInitScript custonNameSpace "p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_yeum_"/>

<div class="ema-color-box ema-color-box--green ema-favoritos__box">
	<header class="ema-favoritos__header">
                <h3 class="ema-favoritos__title">Mis documentos</h3>
</header>
	<div class="ema-favoritos__content">
    <ul class="ema-favoritos__items">
     <#if entries?has_content>
			  <#list entries as entry>
				   <#if entries?has_content>
						 <#assign
					             fileEntry = dlFileEntryLocalService.getFileEntry(entry.getClassPK())
				               fileVersion = fileEntry.getLatestFileVersion(true)
					             viewURL = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + fileEntry.getGroupId() + "/" + fileEntry.getFolderId() + "/" + htmlUtil.escape(fileEntry.getTitle()) + "/" + fileEntry.getUuid() + "?version=" + fileVersion.getVersion()
					             viewThumbnail = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + fileEntry.getGroupId() + "/" + fileEntry.getFolderId() + "/" + htmlUtil.escape(fileEntry.getFileName()) + "/" + fileEntry.getUuid() + "?version=" + fileVersion.getVersion() + "&documentThumbnail=1"
				               extension = fileEntry.getExtension()
											 classNameId = classNameLocalService.getClassNameId("com.liferay.document.library.kernel.model.DLFileEntry")
											 title = fileEntry.getTitle()
											/>
					     <li class="ema-favoritos__item ema-favoritos__item--${extension}">
								  <span class="ema-favoritos__item__label"><a href="${viewURL}">${title}</a></span>
								 <@emasesa.documentosFavoritos themeDisplay.getUserId() themeDisplay.getScopeGroupId() entry.getClassPK() classNameId custonNameSpace title "URL" extension ""/>
               </li>
           </#if>
		   </#list>
	  <#else> 
			 <span class="ema-favoritos__item__label">No tiene favoritos.</span>
    </#if>
    </ul>
 </div>
</div>
<@emasesa.favoritosEndScript custonNameSpace/>