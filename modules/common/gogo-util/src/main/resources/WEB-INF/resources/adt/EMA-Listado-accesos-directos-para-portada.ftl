<#if entries?has_content>
          <div class=hola-portlet__user__inner">
             <ul class="hola-portlet__user__options">
				<#list entries as curEntry>
					
					 <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
					  <li class="hola-portlet__user__options__item">
						
                        <@liferay_journal["journal-article"]
                            articleId=journalArticle.getArticleId()
							               ddmTemplateKey="EMA-ACCESO-DIRECTO-PORTADA-TEMPLATE"
                            groupId=journalArticle.getGroupId()
                        />
							
					</li>
				</#list>
			</ul>
   </div>

</#if>