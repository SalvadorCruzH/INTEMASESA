<#if entries?has_content>
	<#assign  journalArticleLocalService=serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService") />
	<div class="ema-docsADT">
        <ul class="ema-docsADT__items">
            <#list entries as curEntry>
                <#assign journalArticle=journalArticleLocalService.getLatestArticle(curEntry.getClassPK())>
                <li class="ema-docsADT__item">
                    <@liferay_journal["journal-article"]
                        articleId=journalArticle.getArticleId()
                        ddmTemplateKey="EMA-DOCUMENTOS-TEMPLATE"
                        groupId=journalArticle.getGroupId()
                    />
                </li>
            </#list>
        </ul>
	</div>
</#if>
