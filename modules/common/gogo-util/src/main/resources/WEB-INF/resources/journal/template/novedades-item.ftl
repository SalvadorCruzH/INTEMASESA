<div class="ema-novedad-item">
    <div class="ema-novedad-item__imgWrap">
        <img alt="" class="ema-novedad-item__img" src='<#if (img.getData())?? && img.getData() != "" >${img.getData()}<#else>${themeDisplay.getPathThemeImages()}/logos/logo-emasesa-color.svg</#if>'/>  
    </div>
    <div class="ema-novedad-item__texts">
        <span class="ema-novedad-item__icon"><i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right"></i></span>
        <div class="ema-novedad-item__wrapLinkDes">
            <h3 class="ema-18 ema-novedad-item__title">
                <#if (urlLink.getData()?? && urlLink.getData() != "")>
                    <a href="${urlLink.getData()}" class="ema-novedad-item__link">
                        ${title.getData()}
                    </a>
                <#else>
                    ${title.getData()}
                </#if>
            </h3>
            <#if (description.getData())??>
                <div class="ema-novedad-item__description">
                    ${description.getData()}
                </div>
            </#if>
        </div>
    </div>
</div>