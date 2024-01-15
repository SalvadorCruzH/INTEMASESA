<#if entries?has_content>
    <div class="ema-links">
        <#list entries as curEntry>
            <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
            <@liferay_journal["journal-article"]
                articleId=journalArticle.getArticleId()
                ddmTemplateKey="EMA-ENLACES-INTERES"
                groupId=journalArticle.getGroupId()
            />
        </#list>
    </div>
    <#--  /.ema-links  -->
    <script>
        jQuery('.ema-links__link').prependTo('.ema-links')
    </script>
</#if>
