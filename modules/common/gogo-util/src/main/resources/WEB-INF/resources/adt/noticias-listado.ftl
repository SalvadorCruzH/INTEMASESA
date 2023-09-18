<#if entries?has_content>
	<div class="ema-newsADT">
        <div class="container">	
            <ul class="m-listBaseNoStyles ema-newsADT__list">
				<#list entries as curEntry>
					<#if curEntry?index == 3>
						<#break>
					</#if>
					<#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
					<li class="ema-newsADT__li m-link-accessible-wrapper">
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