<#assign custonNameSpace = "fav"+random.nextInt()/>
<@emasesa.favoritosPortadaInitScript custonNameSpace "p_p_id_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_evwh_"/>
<#if entries?has_content>
	 <div class="ema-color-box ema-color-box--white ema-favoritos__box">
          <div class="ema-favoritos__content">
             <ul class="ema-favoritos__items">
				<#list entries as curEntry>
					
					 <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
					  <li class="ema-favoritos__item ema-favoritos__item--add">
						
                        <@liferay_journal["journal-article"]
                            articleId=journalArticle.getArticleId()
							               ddmTemplateKey="EMA-ACCESO-DIRECTO-TEMPLATE"
                            groupId=journalArticle.getGroupId()
                        />
							<@emasesa.accesosDirectosPortada themeDisplay.getUserId() themeDisplay.getScopeGroupId() curEntry.getClassPK() curEntry.getClassNameId() custonNameSpace journalArticle.getTitle() journalArticle.getUrlTitle() "" journalArticle.getDDMStructureKey()/>
					</li>
				</#list>
			</ul>
   </div>
	</div>
</#if>
<@emasesa.favoritosPortadaEndScript custonNameSpace/>