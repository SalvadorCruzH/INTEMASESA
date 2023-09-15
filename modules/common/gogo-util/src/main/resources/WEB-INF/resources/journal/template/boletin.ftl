<div class="boletin">
    <div class="boletin__inner">
        <#if (boletinHeaderImage.getData())?? && boletinHeaderImage.getData() != "">
            <div class="boletin__image-wrapper">
                <img class="boletin__image" alt="${boletinHeaderImage.getAttribute('alt')}" data-fileentryid="${boletinHeaderImage.getAttribute('fileEntryId')}" src="${boletinHeaderImage.getData()}" />
            </div>
        </#if>
        <div class="boletin__content">
            <#assign boletinFecha_Data = getterUtil.getString(boletinFecha.getData())>
            <#if validator.isNotNull(boletinFecha_Data)>
                <#assign boletinFecha_DateObj = dateUtil.parseDate("yyyy-MM-dd", boletinFecha_Data, locale)>
                <div class="boletin__date">
                    <span class="boletin__date-day">${dateUtil.getDate(boletinFecha_DateObj, "dd", locale)}</span> de <span class="boletin__date-month">${dateUtil.getDate(boletinFecha_DateObj, "MMMM", locale)}</span> de <span class="boletin__date-year">${dateUtil.getDate(boletinFecha_DateObj, "yyyy", locale)}</span>
                </div>
            </#if>
            <#if (boletinTitle.getData())??>
                <h2 class="boletin__title">${boletinTitle.getData()}</h2>
            </#if>
            <#if (boletinIndice.getData())??>
                <div class="boletin__contenido">
                    ${boletinIndice.getData()}
                </div>
            </#if>
            <#if boletinNotaPrensa.getSiblings()?has_content>
                <#list boletinNotaPrensa.getSiblings() as cur_boletinNotaPrensa>
                    <#assign webContentData = jsonFactoryUtil.createJSONObject(cur_boletinNotaPrensa.getData()) />
                    <#if webContentData?? && webContentData.title??>
                        <a href="${cur_boletinNotaPrensa.getFriendlyUrl()}">
                            ${webContentData.title}
                        </a>
                    </#if>
                </#list>
            </#if>
        </div>
    </div>
</div>
