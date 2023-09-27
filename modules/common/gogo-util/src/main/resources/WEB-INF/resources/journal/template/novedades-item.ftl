<div class="ema-novedad-item">
    <#if (img.getData())?? && img.getData() != "">  
        <div class="ema-novedad-item__imgWrap">
            <img class="ema-novedad-item__img" alt="${img.getAttribute("alt")}" data-fileentryid="${img.getAttribute("fileEntryId")}" src="${img.getData()}" />
        </div>
    </#if>
    <div class="ema-novedad-item__texts">
        <span class="ema-novedad-item__icon"><i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right"></i></span>
        <div class="ema-novedad-item__wrapLinkDes">
            <h3 class="ema-18">
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