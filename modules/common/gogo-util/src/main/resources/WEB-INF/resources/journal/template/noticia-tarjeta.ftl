<#assign journalResourceService = serviceLocator.findService("com.liferay.journal.service.JournalArticleResourceLocalService")/>
<#assign assetCategoryService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryService")/>
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")/>

<#assign articleId = .vars['reserved-article-id'].data />
<#assign classPK = journalResourceService.getArticleResourcePrimKey(groupId, articleId) />
<#assign categorias = assetCategoryService.getCategories("com.liferay.journal.model.JournalArticle", classPK) />

<#assign displaydate = .vars['reserved-article-display-date'].data>
<#assign originalLocale = .locale>
<#setting locale = localeUtil.getDefault()>
<#assign displaydate = displaydate?datetime("EEE, d MMM yyyy HH:mm:ss Z")>
<#assign locale = originalLocale>

<#assign
        groupLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")
        articleId = .vars['reserved-article-id'].data
        articleUrlTitle = .vars['reserved-article-url-title'].data
        articleGroup = groupLocalService.fetchGroup(articleGroupId)
        viewURL = "/web"+articleGroup.getFriendlyURL()+"/-/"+articleUrlTitle
    />

<div class="ema-noticia-tarjeta">
    <div class="ema-noticia-tarjeta__imgContainer">
        <#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != "">
            <img class="ema-noticia-tarjeta__img" alt="${imagenPrincipal.imgPrincipal.getAttribute("alt")}" data-fileentryid="${imagenPrincipal.imgPrincipal.getAttribute("fileEntryId")}" src="${imagenPrincipal.imgPrincipal.getData()}" />
        </#if>
    </div>
    <div class="ema-noticia-tarjeta__contenido">
        <div class="ema-noticia-tarjeta__categoriasContainer">
            <p class="ema-noticia-tarjeta__categorias">
                <#list categorias as category>
                    <span class="ema-noticia-tarjeta__categoria">
                        ${category.getName()}<#if category?index+1 < categorias?size>,</#if>
                    </span>
                </#list>
            </p>
        </div>
        <p class="ema-noticia-tarjeta__fecha">${displaydate?string["dd"]} de ${displaydate?string["MMMM"]} de ${displaydate?string["yyyy"]}</p>
        <h3 class="ema-noticia-tarjeta__titulo">${title.getData()}</h3>
        <h4 class="ema-noticia-tarjeta__subtitulo">${subtitle.getData()}</h4>
    </div>
</div>