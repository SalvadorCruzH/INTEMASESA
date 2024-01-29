<div class="ema-wrapper__item">
        <div class="hola-portlet__agenda">
            <h3 class="hola-portlet__title">Agenda</h3>
            <h4 class="hola-portlet__subtitle">Eventos corporativos</h4>
            <div class="hola-portlet__agenda__items">
							
							<#list entries as curEntry>
								<#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
									<@liferay_journal["journal-article"]
										articleId=journalArticle.getArticleId()
													   ddmTemplateKey="EMA-EVENTO-PORTADA"
										groupId=journalArticle.getGroupId()
									/>
							</#list>
                <!-- /.hola-portlet__agenda__item -->
                <p class="hola-portlet__more-items"><a href="/" title="Ver todos los eventos">Todos los eventos</a></p>
            </div>
            <!-- /.hola-portlet__agenda__items -->
        </div>
    </div>
    <!-- /.ema-wrapper__item -->