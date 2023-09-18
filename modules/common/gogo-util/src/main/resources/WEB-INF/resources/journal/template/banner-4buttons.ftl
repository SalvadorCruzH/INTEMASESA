<div class="ema-bannerButtons">
    <#if (imagen.getData())?? && imagen.getData() != "">
        <div class="ema-bannerButtons__imgContent">
            <img class="ema-bannerButtons__img" alt="${imagen.getAttribute("alt")}" data-fileentryid="${imagen.getAttribute("fileEntryId")}"
                 src="${imagen.getData()}"/>
        </div>
    </#if>
    <div class="ema-bannerButtons__textContent">
        <#if (antetitulo.getData())?? && antetitulo.getData()?has_content>
            <div class="ema-bannerButtons__pretitle">
                <h3 class="ema-bannerButtons__pretitle_h3">${antetitulo.getData()}</h3>
            </div>
        </#if>
        <#if (titulo.getData())?? && titulo.getData()?has_content>
            <div class="ema-bannerButtons__title">
                <h4 class="ema-bannerButtons__title_h4">${titulo.getData()}</h4>
            </div>
        </#if>
        <#if (descripcion.getData())?? && descripcion.getData()?has_content>
            <div class="ema-bannerButtons__description">
                <p class="ema-bannerButtons__description_p">${descripcion.getData()}</p>
            </div>
        </#if>
        <#if conjuntoBoton.getSiblings()?has_content>
            <div class="ema-bannerButtons__contentButtons">
                <#list conjuntoBoton.getSiblings() as cButtons>
                    <#if cButtons.textoBoton.getData()?has_content && cButtons.urlBoton.getData()?has_content>
                        <a class="ema-bannerButtons__contentButtons_a" href="${cButtons.urlBoton.getData()}">
                            <div class="ema-bannerButtons__button">
                                <p class="ema-bannerButtons__button_p">
                                    <#if (cButtons.seleccionIcono.getData())?? && cButtons.seleccionIcono.getData()?has_content>
                                        <i class="fa-solid fa-${cButtons.seleccionIcono.getData()}"></i>
                                    </#if>
                                    ${cButtons.textoBoton.getData()}
                                </p>
                            </div>
                        </a>
                    </#if>
                </#list>
            </div>
        </#if>
    </div>
</div>




