<#if entries?has_content>
	<div class="ema-novedadesADT">
        <div class="container">	
            <ul class="m-listBaseNoStyles ema-novedadesADT__list">
				<#list entries as curEntry>
					<#if curEntry?index == 5>
						<#break>
					</#if>
					<#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
					<li class="ema-novedadesADT__li m-link-accessible-wrapper ${curEntry?index}">
                        <@liferay_journal["journal-article"]
                            articleId=journalArticle.getArticleId()
							ddmTemplateKey="EMA-NOVEDADES-TEMPLATE"
                            groupId=journalArticle.getGroupId()
                        />
					</li>
				</#list>
			</ul>
        </div>
	</div>
</#if>