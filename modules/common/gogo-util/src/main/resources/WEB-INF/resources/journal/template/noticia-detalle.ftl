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

<div class="ema-noticiasDetail">
    <div class="container ">
        <div class="ema-noticia-tarjeta">
            <#if categorias?has_content>
            <div class="ema-noticia-tarjeta__categoriasContainer">
                <ul class="ema-noticia-tarjeta__categorias m-listBaseNoStyles m-listBase">
                    <#list categorias as category>
                        <li class="ema-noticia-tarjeta__categoria">
                            ${category.getName()}<#if category?index+1 < categorias?size>,&nbsp;</#if>
                        </li>
                    </#list>
                </ul>
            </div>
            </#if>
            <div class="ema-noticiasDetail__wrapperTitleImg">
                <div class="ema-noticiasDetail__wrapperTexts">
                <#if (antetitulo.getData())??>
                    <p class="ema-noticiasDetail__antetitulo">${antetitulo.getData()}</p>
                </#if>
                    <h2 class="ema-noticia-tarjeta__titulo">
                        ${title.getData()}
                    </h2>
                    <p class="ema-noticia-tarjeta__fecha">${displaydate?string["dd"]} de ${displaydate?string["MMMM"]} de ${displaydate?string["yyyy"]}</p>
                    <h3 class="ema-noticia-tarjeta__subtitulo">${subtitle.getData()}</h3>
                </div>
                <div class="ema-noticiasDetail__wrapperImg">
                <#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != "">
                    <img class="ema-noticia-tarjeta__img" alt="${imagenPrincipal.imgPrincipal.getAttribute("alt")}" data-fileentryid="${imagenPrincipal.imgPrincipal.getAttribute("fileEntryId")}" src="${imagenPrincipal.imgPrincipal.getData()}" />
                </#if>
                </div>
            </div>
            <div class="ema-noticiasDetail__wrapperBody">
                <#if (cuerpo.getData())??>
                    ${cuerpo.getData()}
                </#if>
                <div class="">
                </div>
                <#if campoUrl.getSiblings()?has_content>
                    <ul>
                    <#list campoUrl.getSiblings() as cur_campoUrl>
                        <#if (cur_campoUrl.url.getData())?? && cur_campoUrl.url.getData() != "">
                        <li>
                            <a href="${cur_campoUrl.url.getData()}">
                                <#if cur_campoUrl.urlTitle.getData()?? && cur_campoUrl.urlTitle.getData() != "">
                                    ${cur_campoUrl.urlTitle.getData()}
                                <#else>${cur_campoUrl.url.getData()}
                                </#if>
                            </a>
                        </li>
                        </#if>
                    </#list>
                    </ul>
                </#if>
            </div>
        </div>
    </div>
</div>
