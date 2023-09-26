<div class="ema-tarjeta-colores">
    <#if tarjetaConjunto.getSiblings()?has_content>
        <#list tarjetaConjunto.getSiblings() as tConjunto>
            <#if (tConjunto.color.getData())??>
                <#assign colorClass = "${tConjunto.color.getData()}" />
            </#if>
            <div class="ema-tarjeta-colores__box m-link-accessible-wrapper ${colorClass}">
                <#if (tConjunto.image.getData())?? && tConjunto.image.getData() != "">
                    <div class="ema-tarjeta-colores__image">
                        <img alt="${tConjunto.image.getAttribute("alt")}"
                             data-fileentryid="${tConjunto.image.getAttribute("fileEntryId")}"
                             src="${tConjunto.image.getData()}"/>
                    </div>
                <#elseif (tConjunto.selectorIcono.getData())??>
                    <div class="ema-tarjeta-colores__icon">
                        <i class="fa-solid fa-${tConjunto.selectorIcono.getData()}"></i>
                    </div>
                </#if>
                <#if (tConjunto.titulo.getData())??>
                    <div class="ema-tarjeta-colores__title">
                        <#if tConjunto.enlace.getData()??>
                            <a href="${tConjunto.enlace.getData()}" class="ema-tarjeta-colores__link"></a>
                        </#if>
                        <h3>${tConjunto.titulo.getData()}</h3>
                    </div>
                </#if>
                <#if (tConjunto.description.getData())??>
                    <div class="ema-tarjeta-colores__description">
                        <span>${tConjunto.description.getData()}</span>
                    </div>
                </#if>
            </div>
        </#list>
    </#if>
</div>

