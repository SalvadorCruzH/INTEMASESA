<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign theme_display = themeDisplay />
<#assign images_folder = theme_display.getPathThemeImages() />

<div class="boletin">
    <header class="boletin__header">
        <img class="boletin__header__logo" src="${images_folder}/logos/logo-emasesa-color.svg" alt="" />
        <h2 class="boletin__header__owner"><@liferay.language key='es.emasesa.intranet.common.personas'/></h2>
    </header>
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

            <#if notasPrensa.getSiblings()?has_content>
                <h3 class="boletin__related__title">Más actualidad</h3>
                <div class="boletin__related">
                    <#list notasPrensa.getSiblings() as cur_notasPrensa>
                        <#assign webContentData = jsonFactoryUtil.createJSONObject(cur_notasPrensa.boletinNotaPrensa.getData()) />
                        <#assign relatedAssetEntry = assetEntryLocalService.getEntry(webContentData.assetEntryId?number) />
                        <#assign assetRenderer = relatedAssetEntry.getAssetRenderer() journalArticle = assetRenderer.getAssetObject() />

                        <div class="boletin__related__item <#if (cur_notasPrensa.columnas.getData())??>${cur_notasPrensa.columnas.getData()}</#if>">
                            <@liferay_journal["journal-article"]
                                articleId=journalArticle.getArticleId()
                                ddmTemplateKey="EMA-ITEM-RELACIONADO"
                                showTitle=false
                                groupId=journalArticle.getGroupId()
                            />
                        </div>
                    </#list>
                </div>
            </#if>
        </div>
    </div>
</div>
