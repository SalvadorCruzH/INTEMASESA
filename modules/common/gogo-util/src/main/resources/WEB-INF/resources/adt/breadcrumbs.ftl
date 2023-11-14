<#if entries?has_content>
    <nav class="breadcrumbs">
        <ul class="breadcrumbs__items">
            <#list entries as entry>
                <li class="breadcrumbs__item">
                    <#if entry?has_next>
                        <a <#if entry.isBrowsable()>href="${htmlUtil.escapeAttribute(entry.getURL()!'')}"</#if>>
                            <span class="breadcrumb-text-truncate">${htmlUtil.escape(entry.getTitle())}</span>
                        </a>
                    <#else>
                        <span class="active breadcrumb-text-truncate">${htmlUtil.escape(entry.getTitle())}</span>
                    </#if>
                </li>
            </#list>
        </ul>
    </nav>
</#if>
