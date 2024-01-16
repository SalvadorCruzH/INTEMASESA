<#include init />
<#if (linkImg.getData())?? && linkImg.getData() != "">
    <div class="ema-links__link" style="background-image: url('${linkImg.getData()}');">
<#else>
    <div class="ema-links__link" style="background-image: url('${images_folder}/logos/imagen-generica.png')">
</#if>
    <a href="${linkURL.getData()}" title="Ir a ${linkTitle.getData()}" class="btn btn-primary ema-links__link__btn" target="_blank">Saber más</a>

    <#if (linkDescription.getData())?? && linkDescription.getData() != "">
        <div class="ema-links__link__data">
    <#else>
        <div class="ema-links__link__data ema-links__link__data--no-description">
    </#if>
        <#if (linkTitle.getData())??>
            <header class="ema-links__link__header">
                <h3 class="ema-links__link__title">
                    <a href="${linkURL.getData()}" title="Ir a ${linkTitle.getData()}" class="ema-links__link__url" target="_blank">${linkTitle.getData()}</a>
                </h3>
            </header>
        </#if>
        <#if (linkDescription.getData())?? && linkDescription.getData() != "">
            <div class="ema-links__link__description">
                <p>${linkDescription.getData()}</p>
            </div>
        </#if>
    </div>

</div>
