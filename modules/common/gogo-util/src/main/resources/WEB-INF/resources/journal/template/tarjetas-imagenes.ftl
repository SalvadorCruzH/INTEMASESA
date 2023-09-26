<div class="ema-tarjetas-imagenes">
    <#if tarjetas.getSiblings()?has_content>
        <#list tarjetas.getSiblings() as tConjunto>
            <#if (tConjunto.selectorColor.getData())??>
                <#assign colorClass = "${tConjunto.selectorColor.getData()}" />
            </#if>
            <div class="ema-tarjetas-imagenes__box m-link-accessible-wrapper ${colorClass}">
                <#if (tConjunto.image.getData())?? && tConjunto.image.getData() != "">
                    <div class="ema-tarjetas-imagenes__image">
                        <img alt="${tConjunto.image.getAttribute("alt")}"
                             data-fileentryid="${tConjunto.image.getAttribute("fileEntryId")}"
                             src="${tConjunto.image.getData()}"/>
                    </div>
                </#if>
                <div class="ema-tarjetas-imagenes__textContent">
                    <#if (tConjunto.title.getData())??>
                        <div class="ema-tarjetas-imagenes__title">
                            <#if tConjunto.link.getData()??>
                                <a href="${tConjunto.link.getData()}" class="ema-tarjetas-imagenes__link"></a>
                            </#if>
                            <h3>${tConjunto.title.getData()}</h3>
                        </div>
                    </#if>
                    <#if (tConjunto.description.getData())??>
                        <div class="ema-tarjetas-imagenes__description">
                            <span>${tConjunto.description.getData()}</span>
                        </div>
                    </#if>
                </div>
            </div>
        </#list>
    </#if>
</div>


