<div class="em-hola hola-portlet ema-wrapper ema-wrapper--x2">
    <div class="ema-wrapper__item">
        <div class="hola-portlet__user">
            <div class="hola-portlet__user__inner">
                <div class="hola-portlet__user__welcome">
                    <h3 class="hola-portlet__title">
                        <span class="hola-portlet__user__important" data-lfr-editable-id="greetings" data-lfr-editable-type="text">Hola,</span>
                        <span class="hola-portlet__user__name">${themeDisplay.getUser().firstName}</span>
                    </h3>
                    <p class="hola-portlet__user__checks">Último fichaje: <strong>8:33</strong> (30/110/2023)</p>
                    <!-- /.hola-portlet__user__checks -->
                </div>
                <!-- /.hola-portlet__user__welcome -->
                <ul class="hola-portlet__user__shortcuts">
                    <li class="hola-portlet__user__shortcuts__item hola-portlet__user__shortcuts__item--notificaciones">
                        <a href="/" title="Enlace directo"><span class="sr-only">Notificaciones</span></a>
                    </li>
                    <li class="hola-portlet__user__shortcuts__item hola-portlet__user__shortcuts__item--favoritos">
                        <a href="/" title="Enlace directo"><span class="sr-only">Favoritos</span></a>
                    </li>
                    <li class="hola-portlet__user__shortcuts__item hola-portlet__user__shortcuts__item--gestiones">
                        <a href="/" title="Enlace directo"><span class="sr-only">Gestiones</span></a>
                    </li>
                </ul>
                <!-- /.hola-portlet__user__shortcuts -->
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
            </div>
            <!-- /.hola-portlet__user__inner -->
        </div>
        <!-- /.hola-portlet__user -->
    </div>
    <!-- /.ema-wrapper__item -->
</div>
