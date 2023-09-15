<#if entries?has_content>
	<div class="ema-newsADT">
        <div class="container">	
            <ul class="m-listBaseNoStyles ema-newsADT__list">
				<#list entries as curEntry>
					<#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
					<li class="ema-newsADT__li">
                        <@liferay_journal["journal-article"]
                            articleId=journalArticle.getArticleId()
							ddmTemplateKey="EMA-NOTICIA-TARJETA-TEMPLATE"
                            groupId=journalArticle.getGroupId()
                        />
					</li>
				</#list>
			</ul>
        </div>
	</div>
</#if>