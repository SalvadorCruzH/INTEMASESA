<header class="ema-formacion__item__header ema-accordion__header">
    <#if (formacionTitle.getData())??>
        <h3 class="ema-formacion__item__title ema-accordion__title">${formacionTitle.getData()}</h3>
    </#if>
    <#if (formacionResumen.getData())??>
        <p class="ema-formacion__item__resumen">${formacionResumen.getData()}</p>
    </#if>
</header>
<button type="button" class="ema-accordion__btn"><span class="sr-only">Ampliar información</span></button>

<div class="ema-formacion__item__content ema-accordion__content">
    <#if (formacionDescripcion.getData())??>
        <div class="ema-formacion__item__descripcion">
            ${formacionDescripcion.getData()}
        </div>
    </#if>

    <#if (formacionDirigido.getData())??>
        <div class="ema-formacion__item__dirigido">
            <h4 class="ema-formacion__item__data-title">¿A quién va dirigido?</h4>
            ${formacionDirigido.getData()}
        </div>
    </#if>

    <div class="ema-formacion__item__fecha">
        <h4 class="ema-formacion__item__data-title">Fechas de la formación</h4>
        <#assign formacionFechas_formacionInicio_Data = getterUtil.getString(formacionFechas.formacionInicio.getData())>
        <#if validator.isNotNull(formacionFechas_formacionInicio_Data)>
            <#assign formacionFechas_formacionInicio_DateObj = dateUtil.parseDate("yyyy-MM-dd", formacionFechas_formacionInicio_Data, locale)>
            <div class="ema-formacion__item__fecha__inicio">
                ${dateUtil.getDate(formacionFechas_formacionInicio_DateObj, "dd MMM yyyy", locale)}
            </div>
        </#if>
        <#assign formacionFechas_formacionFin_Data = getterUtil.getString(formacionFechas.formacionFin.getData())>
        <#if validator.isNotNull(formacionFechas_formacionFin_Data)>
            <#assign formacionFechas_formacionFin_DateObj = dateUtil.parseDate("yyyy-MM-dd", formacionFechas_formacionFin_Data, locale)>
            <div class="ema-formacion__item__fecha__fin">
                ${dateUtil.getDate(formacionFechas_formacionFin_DateObj, "dd MMM yyyy", locale)}
            </div>
        </#if>
    </div>

    <#if (formacionHorario.getData())??>
        <div class="ema-formacion__item__horario">
            <h4 class="ema-formacion__item__data-title">Horarios de la formación</h4>
            ${formacionHorario.getData()}
        </div>
    </#if>

    <#if (formacionLugar.getData())??>
        <div class="ema-formacion__item__lugar">
            <h4 class="ema-formacion__item__data-title">Lugar de la formación</h4>
            ${formacionLugar.getData()}
        </div>
    </#if>

    <#if (formacionPrecio.getData())??>
        <div class="ema-formacion__item__precio">
            <h4 class="ema-formacion__item__data-title">Precio</h4>
            ${formacionPrecio.getData()}
        </div>
    </#if>

    <#if formacionEnlaces.getSiblings()?has_content>
        <div class="ema-formacion__item__enlaces">
            <h4 class="ema-formacion__item__data-title">URL Oficial o enlaces de interés</h4>
            <#list formacionEnlaces.getSiblings() as cur_formacionEnlaces>
                <div class="ema-formacion__item__enlace">
                    <#if (formacionEnlaces.formacionURLTitle.getData())??>
                        <div class="ema-formacion__item__enlace__title">
                            <a href="formacionEnlaces.formacionURL.getData()" title="${formacionEnlaces.formacionURLTitle.getData()}">${formacionEnlaces.formacionURLTitle.getData()}</a>
                        </div>
                    </#if>
                    <#--  <#if (formacionEnlaces.formacionURL.getData())??>
                        <div class="ema-formacion__item__enlace__url">
                            ${formacionEnlaces.formacionURL.getData()}
                        </div>
                    </#if>  -->
                </div>
            </#list>
        </div>
    </#if>

    <div class="ema-formacion__item__documentos">
        <h4 class="ema-formacion__item__data-title">Documentación</h4>
        <a href="${formacionDocumentos.getData()}">
            ${languageUtil.format(locale, "download-x", "Documentos", false)}
        </a>
    </div>

    <#--  ${friendlyURL}  -->
</div>
