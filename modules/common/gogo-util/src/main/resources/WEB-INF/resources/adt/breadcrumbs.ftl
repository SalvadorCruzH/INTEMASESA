<#if entries?has_content>
    <div class="breadcrumbs" role="nav">
        <ul class="breadcrumbs__items">
            <#list entries as entry>
                <li class="breadcrumbs__item">
                    <#if entry?has_next>
                        <a <#if entry.isBrowsable()>href="${htmlUtil.escapeAttribute(entry.getURL()!'')}"</#if>>
                            <span class="breadcrumbs__item__label">${htmlUtil.escape(entry.getTitle())}</span>
                        </a>
                    <#else>
                        <span class="breadcrumbs__item__label--active breadcrumbs__item__label">${htmlUtil.escape(entry.getTitle())}</span>
                    </#if>
                </li>
            </#list>
        </ul>
    </div>
</#if>
