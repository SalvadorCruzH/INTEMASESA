<#assign groupId = themeDisplay.getScopeGroupId()>
<#assign dlFileService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") >

<#if entries?has_content>
    <#list entries as curEntry>
        <#assign uuid = "${curEntry.classUuid}" />
        <#assign otro = dlFileService.getFileEntryByUuidAndGroupId(uuid, groupId) />
        <#assign type = "${otro.fileName}"/>
        <#assign folderId = "${otro.treePath}">
        <#assign document = "/documents/${groupId}${folderId}${curEntry.title}"/>
        <div class="d-flex justify-content-center p-4">
            <#if groupId?has_content && folderId?has_content && type?has_content>
                <a href="${document}" download="${type}" >${type}</a>
            <#else>
                <h3>No hay archivos para mostrar</h3>
            </#if>
        </div>
    </#list>
<#else>
    <h3>No hay archivos para mostrar</h3>
</#if>
