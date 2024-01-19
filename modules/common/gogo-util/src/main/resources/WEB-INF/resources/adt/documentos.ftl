<#assign dlFileEntryLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService") />

<#if entries?has_content>
    <ul class="ema-files">
        <#list entries as curEntry>
            <#assign dlFile = dlFileEntryLocalService.fetchFileEntry(curEntry.classUuid, curEntry.groupId) >

            <li class="ema-files__file">
                <#if curEntry.title?has_content>
                    <h3 class="ema-files__file__title">
                        <a href="${customDLUtil.getDownloadURL(dlFile, themeDisplay)}" target="_blank">${curEntry.title}</a>
                    </h3>
                </#if>
                <p class="ema-files__file__file-name"><strong>Nombre del archivo</strong> ${dlFile.getFileName()}</p>
                <#if curEntry.description?has_content>
                    <p class="ema-files__file__description"><strong>Descripción</strong> ${curEntry.description}</p>
                </#if>
                <#if curEntry.publishDate?has_content>
                    <p class="ema-files__file__publish-date"><strong>Fecha de publicación</strong> ${curEntry.publishDate?string('dd/MM/yyyy')}</p>
                </#if>
                <#if curEntry.expirationDate?has_content>
                    <p class="ema-files__file__expiration-date"><strong>Fecha de vigencia</strong> ${curEntry.expirationDate?string('dd/MM/yyyy')}</p>
                </#if>
            </li>

        </#list>
    </ul>
</#if>
