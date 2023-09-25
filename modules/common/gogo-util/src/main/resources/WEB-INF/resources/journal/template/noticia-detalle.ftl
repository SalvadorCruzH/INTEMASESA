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


<#assign journalContentId = "jc" + articleId />
<#assign 
		dLFileEntryLocalService = serviceLocator.findService("com.liferay.document.library.kernel.service.DLFileEntryLocalService")
		groupLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.GroupLocalService")
		articleGroup = groupLocalService.fetchGroup(articleGroupId)
	/>

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
                <#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != "">
                    <div class="ema-noticiasDetail__wrapperTexts ema-noticiasDetail__wrapperTexts--img">
                <#else>
                    <div class="ema-noticiasDetail__wrapperTexts">
                </#if>
                <#if (antetitulo.getData())??>
                    <p class="ema-noticiasDetail__antetitulo">${antetitulo.getData()}</p>
                </#if>
                    <h2 class="ema-noticia-tarjeta__titulo">
                        ${title.getData()}
                    </h2>
                    <p class="ema-noticia-tarjeta__fecha">${displaydate?string["dd"]} de ${displaydate?string["MMMM"]} de ${displaydate?string["yyyy"]}</p>
                    <#if (subtitle.getData())??>
                    <h3 class="ema-noticia-tarjeta__subtitulo">${subtitle.getData()}</h3>
                    </#if>
                    <div class="ema-noticia-tarjeta__entradilla">${entradilla.getData()}</div>
                   
                </div>
                <#if (imagenPrincipal.imgPrincipal.getData())?? && imagenPrincipal.imgPrincipal.getData() != "">
                    <div class="ema-noticiasDetail__wrapperImg">
                        <img class="ema-noticia-tarjeta__img" alt="${imagenPrincipal.imgPrincipal.getAttribute("alt")}" data-fileentryid="${imagenPrincipal.imgPrincipal.getAttribute("fileEntryId")}" src="${imagenPrincipal.imgPrincipal.getData()}" />
                    </div>
                </#if>
            </div>
            <div class="ema-noticiasDetail__wrapperBody">
                <#if (cuerpo.getData())??>
                    ${cuerpo.getData()}
                </#if>
                <#if campoUrl.getSiblings()?has_content>
                    <div class="ema-noticiasDetail__listLinksWrapper">
                        <h3 class="ema-noticiasDetail__filesTitle">Enlaces relacionados</h3>
                        <ul class="ema-noticiasDetail__listLinks m-listBaseNoStyles m-list-dashed">
                            <#list campoUrl.getSiblings() as cur_campoUrl>
                                <#if (cur_campoUrl.url.getData())?? && cur_campoUrl.url.getData() != "">
                                <li class="ema-noticiasDetail__listLi">
                                    <a class="ema-noticiasDetail__listLink" href="${cur_campoUrl.url.getData()}">
                                        <#if cur_campoUrl.urlTitle.getData()?? && cur_campoUrl.urlTitle.getData() != "">
                                            ${cur_campoUrl.urlTitle.getData()}
                                        <#else>${cur_campoUrl.url.getData()}
                                        </#if>
                                    </a>
                                </li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                </#if>
                <#if archivosDescargar.getSiblings()?has_content>
                    <div class="ema-noticiasDetail__filesWrapper">
                        <h3 class="ema-noticiasDetail__filesTitle">Documentos relacionados</h3>
                        <ul class="ema-noticiasDetail__filesList m-listBaseNoStyles m-list-dashed">
                            <#list archivosDescargar.getSiblings() as cur_file>
                                <#if (cur_file.getData())?? && cur_file.getData() != "">
                                    <#assign
                                        documentPath = cur_file.getData()?split("/")
                                        uuid = documentPath[5]?substring(0, documentPath[5]?index_of("?"))
                                        groupIdDoc = documentPath[2]
                                        dlFileEntry = dLFileEntryLocalService.fetchDLFileEntryByUuidAndGroupId(uuid,groupIdDoc?number)
                                    />
                                <li class="ema-noticiasDetail__filesLi">
                                    <a class="ema-noticiasDetail__file" href="${cur_file.getData()}" target="_blank">
                                        ${dlFileEntry.getTitle()}<span><@emasesa.docSize doc=cur_file.getData() locale=locale/></span
                                    </a>
                                </li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                </#if>
                <#if imgVideoGaleria.getSiblings()?has_content>
                <div class="ema-noticiasDetail__wrapperGaleria">
                    <h3 class="ema-noticiasDetail__galeriaTitle">Galería Multimedia</h3>
                    <div class="ema-carousel-icons">
						<button aria-label="anterior slide" class="prev ema-slide-btn ema-slide-pre">
                            <i class="i-icon--white fa-solid fa-angle-left"></i>
                        </button>
						<div class="carousel-dots"></div>
        				<button aria-label="siguiente slide" class="next ema-slide-btn ema-slide-next">
                            <i class="i-icon--white fa-solid fa-angle-right"></i>
                        </button>
				    </div>
                    <div class="ema-noticiasDetail__galeria"> 
                        
                        <#if imgVideoGaleria.imgGaleria.getSiblings()?has_content>
                            <#list imgVideoGaleria.imgGaleria.getSiblings() as cur_imgG>
                                <#if (cur_imgG.getData())?? && cur_imgG.getData() != "">
                                    <div class="ema-noticiasDetail__galeriaItem">
                                        <img class="ema-noticiasDetail__imgCarrousel" src="${ cur_imgG.getData()}" alt='${ cur_imgG.getAttribute("alt")}'>
                                    </div>
                                </#if>
                            </#list>
                        </#if>
                        <#if imgVideoGaleria.videoGaleria.getSiblings()?has_content>
                            <#list imgVideoGaleria.videoGaleria.getSiblings() as cur_videoG>
                                <#if (cur_videoG.getData())?? && cur_videoG.getData() != "">
                                    <div class="ema-noticiasDetail__galeriaItem">
                                        <video class="ema-noticiasDetail__videoCarrousel ema-noticiasDetail__imgCarrousel" controls>
                                            <source src="${cur_videoG.getData()}" type="video/mp4">
                                            <source src="${cur_videoG.getData()}" type="video/ogg">
                                            <@liferay.language key="es.emasesa.intranet.common.browser.does.not.support.video.tag"/>
                                        </video>
                                    </div>
                                </#if>
                            </#list>
                        </#if>
                    </div>
                </div>
                </#if>
            </div>
            <#if (autor.getData())?? || (departamento.getData())??>
                <div class="ema-noticiasDetail__footer">
                    <#if (departamento.getData())??>
                        <p><@liferay.language key="es.emasesa.intranet.common.departamento"/>: ${departamento.getData()}</p>
                    </#if>
                    <#if (autor.getData())??>
                        <p><@liferay.language key="es.emasesa.intranet.common.autor"/>: ${autor.getData()}.</p>
                    </#if>
                </div>
            </#if>
            <div class="">
                <@emasesa.header title=title.getData()/>
            </div>    
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {  
	
		$('.ema-noticiasDetail__galeria').slick({
			slidesToShow: 3,
			slidesToScroll: 3,
			dots: true,
            arrows: true,
            appendDots: '.carousel-dots',
            prevArrow: $('.prev'),
			nextArrow: $('.next'),
			cssEase: 'linear', 

			responsive: [
                {
					breakpoint: 1200,
					settings: {
						slidesToShow: 3,
						slidesToScroll: 3
					}
				},
				{
					breakpoint: 992,
					settings: {
						slidesToShow: 2,
						slidesToScroll: 1
					}
				},
				{
					breakpoint: 576,
					settings: {
						slidesToShow: 1,
						slidesToScroll: 1
					}
				}
			]
		});
	});
</script>