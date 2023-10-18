<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
<#assign theme_display = themeDisplay />
<#assign images_folder = theme_display.getPathThemeImages() />

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office">
    <head>
        <!--[if !mso]><!-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!--<![endif]-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style type="text/css">
            #outlook a {
                padding: 0;
            }
            body {
                margin: 0;
                padding: 0;
                -webkit-text-size-adjust: 100%;
                -ms-text-size-adjust: 100%;
            }
            table,
            td {
                border-collapse: collapse;
                mso-table-lspace: 0pt;
                mso-table-rspace: 0pt;
            }
            img {
                border: 0;
                height: auto;
                line-height: 100%;
                outline: none;
                text-decoration: none;
                -ms-interpolation-mode: bicubic;
            }
            p {
                display: block;
                margin: 13px 0;
                font-size: 14px !important;
                line-height: 1.15 !important;
            }
        </style>
        <!--[if mso]>
        <noscript>
        <xml>
        <o:OfficeDocumentSettings>
        <o:AllowPNG/>
        <o:PixelsPerInch>96</o:PixelsPerInch>
        </o:OfficeDocumentSettings>
        </xml>
        </noscript>
        <![endif]-->
        <!--[if lte mso 11]>
        <style type="text/css">
        .mj-outlook-group-fix { width:100% !important; }
        </style>
        <![endif]-->

        <!--[if !mso]><!-->
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet" type="text/css">
        <style type="text/css">
            @import url(https://fonts.googleapis.com/css?family=Roboto:300,400,500,700);
        </style>
        <!--<![endif]-->
        <style type="text/css">
            @media only screen and (min-width:480px) {
                .mj-column-per-100 {
                    width: 100% !important;
                    max-width: 100%;
                }
                .mj-column-per-66-6666666667 {
                    width: 66.6666666667% !important;
                    max-width: 66.6666666667%;
                }
                .mj-column-per-33-333333333333336 {
                    width: 33.333333333333336% !important;
                    max-width: 33.333333333333336%;
                }
            }
        </style>
        <style media="screen and (min-width:480px)">
            .moz-text-html .mj-column-per-100 {
                width: 100% !important;
                max-width: 100%;
            }
            .moz-text-html .mj-column-per-66-6666666667 {
                width: 66.6666666667% !important;
                max-width: 66.6666666667%;
            }
            .moz-text-html .mj-column-per-33-333333333333336 {
                width: 33.333333333333336% !important;
                max-width: 33.333333333333336%;
            }
        </style>
        <style type="text/css">
            @media only screen and (max-width:480px) {
                table.mj-full-width-mobile {
                    width: 100% !important;
                }
                td.mj-full-width-mobile {
                    width: auto !important;
                }
            }
        </style>
        <style type="text/css">
        </style>
    </head>
    <body style="word-spacing:normal;background-color:#eef1f7;">
    <span style="word-spacing:normal;background-color:#eef1f7;"></span>
        <div style="background-color:#eef1f7;">
            <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" class="" role="presentation" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
            <div style="margin:0px auto;max-width:600px;">
                <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="direction:ltr;font-size:0px;padding:30px 0;text-align:center;">
                                <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:600px;" ><![endif]-->
                                <div class="mj-column-per-100 mj-outlook-group-fix" style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                                        <tbody>
                                            <tr>
                                                <td style="vertical-align:top;padding:0;">
                                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td align="center" style="font-size:0px;padding:10px 25px;word-break:break-word;">
                                                                    <table border="0" cellpadding="0" cellspacing="0"
                                                                        role="presentation"
                                                                        style="border-collapse:collapse;border-spacing:0px;">
                                                                        <tbody>
                                                                            <tr>
                                                                                <td style="width:300px;">
                                                                                    <img height="auto"
                                                                                        src="${images_folder}/logos/logo-personas.png"
                                                                                        style="border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;"
                                                                                        width="300" />
                                                                                </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--[if mso | IE]></td></tr></table><![endif]-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" role="presentation" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
            <div style="margin:0px auto;max-width:600px;">
                <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="direction:ltr;font-size:0px;padding:0;text-align:center;">
                                <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:600px;" ><![endif]-->
                                <div class="mj-column-per-100 mj-outlook-group-fix"
                                    style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                                        <tbody>
                                            <tr>
                                                <td style="vertical-align:top;padding:0;">
                                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation"
                                                        style="" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td align="center"
                                                                    style="font-size:0px;padding:0;word-break:break-word;">
                                                                    <table border="0" cellpadding="0" cellspacing="0"
                                                                        role="presentation"
                                                                        style="border-collapse:collapse;border-spacing:0px;">
                                                                        <tbody>
                                                                            <tr>
                                                                                <td style="width:600px;">
                                                                                    <#if (boletinHeaderImage.getData())?? && boletinHeaderImage.getData() != "">
                                                                                        <img height="auto"
                                                                                            src="${boletinHeaderImage.getData()}"
                                                                                            style="border:0;border-radius:15px 15px 0 0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;"
                                                                                            width="600" />
                                                                                    </#if>
                                                                                </td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--[if mso | IE]></td></tr></table><![endif]-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" role="presentation" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
            <div style="margin:0px auto;max-width:600px;">
                <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="direction:ltr;font-size:0px;padding:0;text-align:center;">
                                <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:600px;" ><![endif]-->
                                <div class="mj-column-per-100 mj-outlook-group-fix"
                                    style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                                        <tbody>
                                            <tr>
                                                <td style="background-color:#0f71ca;vertical-align:top;padding:10px 0;">
                                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation"
                                                        style="" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td align="left" style="font-size:0px;padding:10px 25px;word-break:break-word;">
                                                                    <#if (boletinTitle.getData())??>
                                                                        <div style="font-family:Roboto, Helvetica, Arial;font-size:27px;font-weight:bold;line-height:1;text-align:left;color:#ffffff;">
                                                                            ${boletinTitle.getData()}
                                                                        </div>
                                                                    </#if>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--[if mso | IE]></td></tr></table><![endif]-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" role="presentation" style="width:600px;" width="600" bgcolor="#ffffff" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
            <div style="background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;">
                <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation"
                    style="background:#ffffff;background-color:#ffffff;width:100%;">
                    <tbody>
                        <tr>
                            <td style="direction:ltr;font-size:0px;padding:0;text-align:center;">
                                <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:600px;" ><![endif]-->
                                <div class="mj-column-per-100 mj-outlook-group-fix"
                                    style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                                        <tbody>
                                            <tr>
                                                <td style="background-color:#d6e7f4;vertical-align:top;padding:0;">
                                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation"
                                                        style="" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td align="left" style="font-size:0px;padding:10px 25px;word-break:break-word;">
                                                                    <#assign boletinFecha_Data = getterUtil.getString(boletinFecha.getData())>
                                                                    <#if validator.isNotNull(boletinFecha_Data)>
                                                                        <#assign boletinFecha_DateObj = dateUtil.parseDate("yyyy-MM-dd", boletinFecha_Data, locale)>
                                                                        <div style="font-family:Roboto, Helvetica, Arial;font-size:14px;font-weight:bold;line-height:1;text-align:left;color:#555555;">
                                                                            ${dateUtil.getDate(boletinFecha_DateObj, "dd", locale)} de ${dateUtil.getDate(boletinFecha_DateObj, "MMMM", locale)} de ${dateUtil.getDate(boletinFecha_DateObj, "yyyy", locale)}
                                                                        </div>
                                                                    </#if>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--[if mso | IE]></td></tr></table><![endif]-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" role="presentation" style="width:600px;" width="600" bgcolor="#ffffff" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
            <div style="background:#ffffff;background-color:#ffffff;margin:0px auto;max-width:600px;">
                <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation"
                    style="background:#ffffff;background-color:#ffffff;width:100%;">
                    <tbody>
                        <tr>
                            <td style="direction:ltr;font-size:0px;padding:0;text-align:center;">
                                <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:600px;" ><![endif]-->
                                <div class="mj-column-per-100 mj-outlook-group-fix"
                                    style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                                        <tbody>
                                            <tr>
                                                <td style="vertical-align:top;padding:15px 0;">
                                                    <table border="0" cellpadding="0" cellspacing="0" role="presentation" style="" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td align="left" style="font-size:0px;padding:10px 25px;word-break:break-word;">
                                                                    <#if (boletinIndice.getData())??>
                                                                        <div style="font-family:Roboto, Helvetica, Arial;font-size:16px;line-height:1.5;text-align:left;color:#000000;">
                                                                            ${boletinIndice.getData()}
                                                                        </div>
                                                                    </#if>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" style="font-size:0px;padding:10px 25px;word-break:break-word;">
                                                                    <p style="border-top:solid 1px #dcdcdc;font-size:1px;margin:0px auto;width:100%;"></p>
                                                                    <!--[if mso | IE]><table align="center" border="0" cellpadding="0" cellspacing="0" style="border-top:solid 1px #dcdcdc;font-size:1px;margin:0px auto;width:550px;" role="presentation" width="550px" ><tr><td style="height:0;line-height:0;"> &nbsp; </td></tr></table><![endif]-->
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td align="left" style="font-size:0px;padding:10px 25px;word-break:break-word;">
                                                                    <div style="font-family:Roboto, Helvetica, Arial;font-size:20px;font-weight:bold;line-height:1;text-align:left;color:#000000;">
                                                                        Actualidad EMASESA
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--[if mso | IE]></td></tr></table><![endif]-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" role="presentation" style="width:600px;" width="600" bgcolor="#d6e7f4" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
            <div style="background:#d6e7f4;background-color:#d6e7f4;margin:0px auto;border-radius:0 0 30px 30px;max-width:600px;">
                <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation"
                    style="background:#d6e7f4;background-color:#d6e7f4;width:100%;border-radius:0 0 30px 30px;">
                    <tbody>
                        <tr>
                            <td style="direction:ltr;font-size:0px;padding:15px 7px;text-align:center;">
                                <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr><td class="" style="vertical-align:top;width:195.33333333333337px;" ><![endif]-->
                                <#if notaPrensa.getSiblings()?has_content>
                                    <#list notaPrensa.getSiblings() as cur_notasPrensa>
                                        <#assign articleId = cur_notasPrensa.noticia.getData() />
                                        <#attempt>
                                            <#assign journalArticle = journalArticleLocalService.getArticle(theme_display.getScopeGroupId(), articleId)/>
                                            <div class="<#if (cur_notasPrensa.columnas.getData())??>${cur_notasPrensa.columnas.getData()}</#if> mj-outlook-group-fix"
                                                style="font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;">
                                                <@liferay_journal["journal-article"]
                                                    articleId=journalArticle.getArticleId()
                                                    ddmTemplateKey="EMA-ITEM-RELACIONADO-TABLAS"
                                                    showTitle=false
                                                    groupId=journalArticle.getGroupId()
                                                />
                                            </div>
                                            <#recover>
                                        </#attempt>
                                    </#list>
                                </#if>
                                <!--[if mso | IE]></td><td class="" style="vertical-align:top;width:195.33333333333337px;" ><![endif]-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--[if mso | IE]></td></tr></table><table align="center" border="0" cellpadding="0" cellspacing="0" class="" role="presentation" style="width:600px;" width="600" ><tr><td style="line-height:0px;font-size:0px;mso-line-height-rule:exactly;"><![endif]-->
            <div style="margin:0px auto;max-width:600px;">
                <table align="center" border="0" cellpadding="0" cellspacing="0" role="presentation" style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="direction:ltr;font-size:0px;padding:60px;text-align:center;">
                                <!--[if mso | IE]><table role="presentation" border="0" cellpadding="0" cellspacing="0"><tr></tr></table><![endif]-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--[if mso | IE]></td></tr></table><![endif]-->
        </div>
    </body>
</html>
