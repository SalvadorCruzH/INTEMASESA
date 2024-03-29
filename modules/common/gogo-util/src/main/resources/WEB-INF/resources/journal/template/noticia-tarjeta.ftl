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

    <div class="ema-noticia-tarjeta__imgContainer" style="background-image:url('<#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != '' >${imagenPrincipal.imgPrincipal.getData()}<#else>${themeDisplay.getPathThemeImages()}/logos/imagen-generica-noticia.png</#if>');">
        <img alt="" class="ema-noticia-tarjeta__img" src='<#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != "" >${imagenPrincipal.imgPrincipal.getData()}<#else>${themeDisplay.getPathThemeImages()}/logos/imagen-generica-noticia.png</#if>'/>  
    </div>
    <div class="ema-noticia-tarjeta__contenido">
        <#if categorias?has_content>
        <div class="ema-noticia-tarjeta__categoriasContainer">
            <p class="ema-noticia-tarjeta__categorias m-listBaseNoStyles ">
                <#list categorias as category>
                    <span class="ema-noticia-tarjeta__categoria">
                        ${category.getName()}<#if category?index+1 < categorias?size>,</#if>
                    </span>
                </#list>
            </p>
        </div>
        </#if>
        <div class="ema-noticia-tarjeta__wrapperTexts">
            <p class="ema-noticia-tarjeta__fecha">${displaydate?string["dd"]} de ${displaydate?string["MMMM"]} de ${displaydate?string["yyyy"]}</p>
            <h3 class="ema-noticia-tarjeta__titulo">
                <a class="ema-noticia-tarjeta__link" href="${viewURL}">${title.getData()}</a>
            </h3>
            <div class="ema-noticia-tarjeta__subtitulo">${entradilla.getData()}</div>
        </div>
    </div>
</div>