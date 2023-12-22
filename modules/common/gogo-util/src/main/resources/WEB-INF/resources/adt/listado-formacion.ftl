<#if entries?has_content>
	<div class="ema-formacion">
        <ul class="ema-formacion__items ema-accordion">
            <#list entries as curEntry>
                <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
                <li class="ema-formacion__item ema-accordion__item">
                    <@liferay_journal["journal-article"]
                        articleId=journalArticle.getArticleId()
                        ddmTemplateKey="EMA-FORMACION-TEMPLATE"
                        groupId=journalArticle.getGroupId()
                    />
                </li>
            </#list>
        </ul>
	</div>
</#if>
<script>
    jQuery(function() {
        jQuery('.ema-accordion__btn').on('click', function(){
            jQuery(this).toggleClass('ema-accordion__btn--opened')
            jQuery(this).siblings('.ema-accordion__content').toggleClass('ema-accordion__content--opened')
        })
    })
</script>
