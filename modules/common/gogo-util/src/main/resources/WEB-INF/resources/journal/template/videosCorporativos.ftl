<div class="ema-videosCorporativos__main">
    <#if (titulo.getData())?? && titulo.getData()?has_content>
        <div class="ema-videosCorporativos__titleContainer <#if video.getData()?has_content && !(enlaceVideo.getData()?has_content)>upload</#if>">
            <h3 class="ema-videosCorporativos__title"> ${titulo.getData()}</h3>
        </div>
    </#if>
    <#if (descripcion.getData())?? && descripcion.getData()?has_content>
        <div class="ema-videosCorporativos__descriptionContainer">
            ${descripcion.getData()}
        </div>
    </#if>
    <#if video.getData()?? && video.getData()?has_content>
        <div class="ema-videosCorporativos__videoContainer">
            <button class="ema-videosCorporativos__icon" id="${themeDisplay.getPortletDisplay().getId()}_buttonVideo"><i class="fa-regular fa-circle-play fa-2xl"></i></button>
            <video class="ema-videosCorporativos__video" id="${themeDisplay.getPortletDisplay().getId()}_video">
                <source src="${video.getData()}" type="video/mp4">
            </video>
        </div>
    <#elseif (enlaceVideo.getData())?? && enlaceVideo.getData()?has_content>
        <div class="ema-videosCorporativos__enlaceContainer">
            <iframe class="ema-videosCorporativos__enlace"  src="${enlaceVideo.getData()}" title="YouTube video player" allow="autoplay; encrypted-media;" allowfullscreen></iframe>
        </div>
    </#if>
</div>

