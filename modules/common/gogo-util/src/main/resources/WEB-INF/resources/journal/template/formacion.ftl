<header class="ema-formacion__item__header ema-accordion__header">
    <#if (formacionTitle.getData())??>
        <h3 class="ema-formacion__item__title ema-accordion__title">${formacionTitle.getData()}</h3>
    </#if>
</header>
<button type="button" class="ema-accordion__btn"><span class="sr-only">Ampliar información</span></button>

<div class="ema-formacion__item__content ema-accordion__content">
    <#if (formacionResumen.getData())??>
        ${formacionResumen.getData()}
    </#if>
    <br />
    <#if (formacionDescripcion.getData())??>
        ${formacionDescripcion.getData()}
    </#if>
    <br />
    <#if (formacionDirigido.getData())??>
        ${formacionDirigido.getData()}
    </#if>
    <br />
    <#assign formacionFechas_formacionInicio_Data = getterUtil.getString(formacionFechas.formacionInicio.getData())>
    <#if validator.isNotNull(formacionFechas_formacionInicio_Data)>
        <#assign formacionFechas_formacionInicio_DateObj = dateUtil.parseDate("yyyy-MM-dd", formacionFechas_formacionInicio_Data, locale)>
        ${dateUtil.getDate(formacionFechas_formacionInicio_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}
    </#if>
    <br />
    <#assign formacionFechas_formacionFin_Data = getterUtil.getString(formacionFechas.formacionFin.getData())>
    <#if validator.isNotNull(formacionFechas_formacionFin_Data)>
        <#assign formacionFechas_formacionFin_DateObj = dateUtil.parseDate("yyyy-MM-dd", formacionFechas_formacionFin_Data, locale)>
        ${dateUtil.getDate(formacionFechas_formacionFin_DateObj, "dd MMM yyyy - HH:mm:ss", locale)}
    </#if>
    <br />
    <#if (formacionHorario.getData())??>
        ${formacionHorario.getData()}
    </#if>
    <br />
    <#if (formacionLugar.getData())??>
        ${formacionLugar.getData()}
    </#if>
    <br />
    <#if (formacionPrecio.getData())??>
        ${formacionPrecio.getData()}
    </#if>
    <br />
    <#if formacionEnlaces.getSiblings()?has_content>
        <#list formacionEnlaces.getSiblings() as cur_formacionEnlaces>
            <#if (formacionEnlaces.formacionURLTitle.getData())??>
                ${formacionEnlaces.formacionURLTitle.getData()}
            </#if>

            <#if (formacionEnlaces.formacionURL.getData())??>
                ${formacionEnlaces.formacionURL.getData()}
            </#if>
        </#list>
    </#if>
    <br />
    <a href="${formacionDocumentos.getData()}">
        ${languageUtil.format(locale, "download-x", "Documentos", false)}
    </a>
    <br />
    ${friendlyURL}
</div>
