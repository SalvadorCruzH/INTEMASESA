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
                    <#if (cur_formacionEnlaces.formacionURLTitle.getData())??>
                        <div class="ema-formacion__item__enlace__title">
                            <a href="cur_formacionEnlaces.formacionURL.getData()" title="${cur_formacionEnlaces.formacionURLTitle.getData()}">${cur_formacionEnlaces.formacionURLTitle.getData()}</a>
                        </div>
                    </#if>
                </div>
            </#list>
        </div>
    </#if>

    <#if formacionDocumentos.getSiblings()?has_content>
        <div class="ema-formacion__item__documentos">
            <h4 class="ema-formacion__item__data-title">Documentación</h4>
            <ul class="ema-formacion__item__documentos__list">
                <#list formacionDocumentos.getSiblings() as cur_formacionDocumentos>
                    <#assign docSplit = cur_formacionDocumentos.getData()?split("/") />
                    <li class="ema-formacion__item__documentos__item">
                        <a href="${cur_formacionDocumentos.getData()}" class="ema-formacion__item__documentos__link" title="Descarga el documento" target="_blank">${docSplit[4]}</a>
                    </li>
                </#list>
            </ul>
        </div>
    </#if>

</div>
