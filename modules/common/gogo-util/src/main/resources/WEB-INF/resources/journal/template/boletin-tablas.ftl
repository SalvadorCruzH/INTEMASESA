<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign theme_display = themeDisplay />
<#assign images_folder = theme_display.getPathThemeImages() />

<table class="main-table" cellspacing="0" cellpadding="0" style="background-color: #eef1f7;width: 70%;max-width: 576px;margin: 30px auto;font-family: Arial, Helvetica, sans-serif;">
    <thead>
        <tr>
            <th class="top-header" style="border-top: 15px solid #eef1f7;border-bottom: 15px solid #eef1f7;vertical-align: middle;">
                <img class="logo-emasesa" style="vertical-align: middle;max-width: 100%;" src="${images_folder}/logos/logo-emasesa.png" alt="Logotipo EMASESA" /> <span class="naming-emasesa" style="vertical-align: middle;font-size: 30px;"><@liferay.language key='es.emasesa.intranet.common.personas'/></span>
            </th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td class="title-cell" style="background-color: #0f71ca;color: white;position: relative;">
                <img class="corner-top-left" src="${images_folder}/decorative/corner-top-left.png" alt="" style="position:absolute;top:0;left:0;" />
                <img class="corner-top-right" src="${images_folder}/decorative/corner-top-right.png" alt="" style="position:absolute;top:0;right:-1px;" />
                <#if (boletinHeaderImage.getData())?? && boletinHeaderImage.getData() != "">
                    <img class="cabecera-boletin" style="max-width: 100%;" src="${boletinHeaderImage.getData()}" alt="" />
                </#if>
                <table cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="main-title" style="border-right: 15px solid #0f71ca;border-left: 15px solid #0f71ca;">
                            <#if (boletinTitle.getData())??>
                                <h2>Este es el título del primer boletín EMASESA</h2>
                            </#if>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="main-content-wrapper" style="border: 15px solid white;background-color: white;">
                <table cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <#assign boletinFecha_Data = getterUtil.getString(boletinFecha.getData())>
                            <#if validator.isNotNull(boletinFecha_Data)>
                                <#assign boletinFecha_DateObj = dateUtil.parseDate("yyyy-MM-dd", boletinFecha_Data, locale)>
                                <p class="date-p" style="padding: 10px 20px;background-color: #d6e7f4;float: left;width: fit-content;">${dateUtil.getDate(boletinFecha_DateObj, "dd", locale)} de ${dateUtil.getDate(boletinFecha_DateObj, "MMMM", locale)} de ${dateUtil.getDate(boletinFecha_DateObj, "yyyy", locale)}</p>
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <#if (boletinIndice.getData())??>
                                ${boletinIndice.getData()}
                            </#if>
                        </td>
                    </tr>
                    <#if notaPrensa.getSiblings()?has_content>
                        <tr>
                            <td class="subtitle" style="border-top: 1px solid #dcdcdc;">
                                <h3>Actualidad EMASESA</h3>
                            </td>
                        </tr>
                    </#if>
                </table>
            </td>
        </tr>
        <tr>
            <td class="bottom-content" style="background-color: #d6e7f4;border-top: 15px solid #d6e7f4;border-left: 7px solid #d6e7f4;border-right: 7px solid #d6e7f4;position: relative;">
                <table cellspacing="0" cellpadding="0">
                    <#if notaPrensa.getSiblings()?has_content>
                        <tr>
                            <#list notaPrensa.getSiblings() as cur_notasPrensa>
                                <#assign webContentData = jsonFactoryUtil.createJSONObject(cur_notasPrensa.boletinNotaPrensa.getData()) />
                                <#assign relatedAssetEntry = assetEntryLocalService.getEntry(webContentData.assetEntryId?number) />
                                <#assign assetRenderer = relatedAssetEntry.getAssetRenderer() journalArticle = assetRenderer.getAssetObject() />
                                <@liferay_journal["journal-article"]
                                    articleId=journalArticle.getArticleId()
                                    ddmTemplateKey="EMA-ITEM-RELACIONADO-TABLAS"
                                    showTitle=false
                                    groupId=journalArticle.getGroupId()
                                />
                            </#list>
                        </tr>
                    </#if>
                </table>
                <img class="corner-bottom-left" src="${images_folder}/decorative/corner-bottom-left.png" alt="" style="position:absolute;bottom:0;left:-7px;" />
                <img class="corner-bottom-right" src="${images_folder}/decorative/corner-bottom-right.png" alt="" style="position:absolute;bottom:0;right:-8px;" />
            </td>
        </tr>
    </tbody>
</table>
<style>
    body {
        background-color: #eef1f7;
    }
    a {
        color: #0f71ca;
        text-decoration: none;
    }
    .main-table {
        background-color: #eef1f7;
        width: 70%;
        max-width: 576px;
        margin: 30px auto;
        font-family: Arial, Helvetica, sans-serif;
    }
    .top-header {
        border-top: 15px solid #eef1f7;
        border-bottom: 15px solid #eef1f7;
        vertical-align: middle;
    }
    .logo-emasesa {
        vertical-align: middle;
        max-width: 100%;
    }
    .naming-emasesa {
        vertical-align: middle;
        font-size: 30px;
    }
    .title-cell {
        background-color: #0f71ca;
        color: white;
        position: relative;
    }
    .corner-top-left {
        position:absolute;
        top:0;
        left:0;
    }
    .corner-top-right {
        position:absolute;
        top:0;
        right:-1px;
    }
    .corner-bottom-left {
        position:absolute;
        bottom:0;
        left:-7px;
    }
    .corner-bottom-right {
        position:absolute;
        bottom:0;
        right:-8px;
    }
    .cabecera-boletin {
        max-width: 100%;
    }
    .main-title {
        border-right: 15px solid #0f71ca;
        border-left: 15px solid #0f71ca;
    }
    .main-content-wrapper {
        border: 15px solid white;
        background-color: white;
    }
    .date-p {
        padding: 10px 20px;
        background-color: #d6e7f4;
        float: left;
        width: fit-content;
    }
    .subtitle {
        border-top: 1px solid #dcdcdc;
    }
    .bottom-content {
        background-color: #d6e7f4;
        border-top: 15px solid #d6e7f4;
        border-left: 7px solid #d6e7f4;
        border-right: 7px solid #d6e7f4;
        position: relative;
    }
    .aside-news {
        width: 33.3333%;
        vertical-align: top;
        border-left: 7px solid #d6e7f4;
        border-right: 7px solid #d6e7f4;
    }
    .news-img {
        max-width: 100%;
    }
    .news-title {
        margin-top: 5px;
        font-size: 15px;
    }
</style>
