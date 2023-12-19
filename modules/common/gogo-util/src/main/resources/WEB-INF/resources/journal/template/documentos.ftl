<#if (docTitle.getData())??>
    <header class="ema-docsADT__item__header">
        <h4 class="ema-docsADT__item__title">
            <span class="ema-docsADT__item__title__label">${docTitle.getData()}</span>
        </h4>
        <#assign Date88698232_Data = getterUtil.getString(Date88698232.getData())>
        <#if validator.isNotNull(Date88698232_Data)>
            <p class="ema-docsADT__item__date">
                <#assign Date88698232_DateObj = dateUtil.parseDate("yyyy-MM-dd", Date88698232_Data, locale)>
                ${dateUtil.getDate(Date88698232_DateObj, "dd MMM yyyy", locale)}
            </p>
        </#if>
    </header>
</#if>

<#if docAttached.getSiblings()?has_content>
    <div class="ema-docsADT__item__documents">
        <#list docAttached.getSiblings() as cur_docAttached>
            <a href="${cur_docAttached.getData()}" class="ema-docsADT__item__documents__link">Descargar</a>
        </#list>
    </div>
</#if>
