<#if entries?has_content>
    <div class="ema-related-documents">
        <#list entries as entry>
            <#assign
                entry = entry
                assetRenderer = entry.getAssetRenderer()
                entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale))
                viewURL = assetRenderer.getURLDownload(themeDisplay)
            />
            <div class="ema-related-documents__item">
                <h4 class="ema-related-documents__title"><a href="${viewURL}" target="_blank" title="Descargar '${entryTitle}'">${entryTitle}</a></h4>
                <a href="${viewURL}" class="btn btn-primary ema-related-documents__download" title="Descargar '${entryTitle}'">Descargar</a>
            </div>
        </#list>
    </div>
</#if>
