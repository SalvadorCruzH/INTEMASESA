<#assign journalId = .vars['reserved-article-id'].data />
<#assign groupId = requestMap["attributes"]["appGroupId"]!""/>
<#assign apps = applicationsUtil.getMyFavoritesPlain() />

<#assign classz = "${themeDisplay.getPortletDisplay().getId()}_markNoFav aplication-unfavorite-icon" />

<#if  apps?contains(journalId)>
    <#assign classz = "${themeDisplay.getPortletDisplay().getId()}_markFav aplication-favorite-icon" />

</#if>


<li class="aplications-modal-item">
    <span class="${classz}" data-journalid="${journalId}" data-groupid="${groupId}"></span>
    <img alt="${appIcon.getAttribute("alt")}" data-fileentryid="${appIcon.getAttribute("fileEntryId")}" src="${appIcon.getData()}" class="aplication-icon" />
    <a class="aplication-name" href="${url.getData()}">${.vars['reserved-article-title'].data}</a>
</li>