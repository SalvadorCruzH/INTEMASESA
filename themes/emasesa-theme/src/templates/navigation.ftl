<nav class="i-mainNavigation" aria-label="<@liferay.language key="site-pages" />" id="navigation" role="navigation">
	<ul class="i-mainNavigation__ul m-listBase m-dFlexSBCenter" role="menubar">
		<#list navigationItems as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_css_class = "i-mainNavigation__li"
				nav_item_layout = nav_item.getLayout()
			/>
			<#if nav_item.hasChildren()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class = "i-mainNavigation__li children"
				/>
			</#if>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_css_class = "i-mainNavigation__li selected"

				/>
				<#if nav_item.hasChildren()>
					<#assign
						nav_item_attr_has_popup = "aria-haspopup='true'"
						nav_item_css_class = "i-mainNavigation__li selected children"
					/>
				</#if>
			</#if>


			<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">

				<#if nav_item.hasChildren()>
					<a class="i-mainNavigation__link" ${nav_item_attr_has_popup} href="javascript:void(0)" ${nav_item.getTarget()} role="menuitem"><span class="i-mainNavigation__span"><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>
					<div class="i-mainNavigation__submenuContainer">
						<div class="i-mainNavigation__submenuWrapper">
							<div class="i-mainNavigation__innerWrapper">
								<ul class="i-mainNavigation__submenuUl m-listBaseNoStyles m-listBaseNoStyles--pm3" role="menu">
									<#list nav_item.getChildren() as nav_child>
										<#assign
											nav_child_css_class = "i-mainNavigation__submenuLi"
										/>

										<#if nav_child.isSelected()>
											<#assign
												nav_child_css_class = "i-mainNavigation__submenuLi selected"
											/>
										</#if>

										<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
											<a class="i-mainNavigation__link2" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">
												<i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right mr-1"></i>
												${nav_child.getName()}
											</a>
											<#if nav_child.hasChildren()>
												<ul class="child-menu i-mainNavigation__submenuUl2 m-listBaseNoStyles m-listBaseNoStyles--pm3" role="menu">
													<#list nav_child.getChildren() as nav_child2>
														<#assign
															nav_child2_css_class = "i-navMainMobile__li2Level"
														/>

														<#if nav_child2.isSelected()>
															<#assign
																nav_child2_css_class = "i-navMainMobile__child2 selected"
															/>
														</#if>

														<li class="${nav_child2_css_class}" id="layout_${nav_child2.getLayoutId()}" role="presentation">
															<a class="i-mainNavigation__link2Level" href="${nav_child2.getURL()}" ${nav_child2.getTarget()} role="menuitem">
																<i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right mr-1"></i>
																${nav_child2.getName()}
															</a>
														</li>
													</#list>
												</ul>
											</#if>
										</li>
									</#list>
								</ul>
								<div class="i-wrapperRight">
									<div class="i-wrapperBR i-wrapperBR--blue">
									<h2 class="i-title i-title--18 i-title--black">Hemos actualizado</h2>
									<ul class="m-listBaseNoStyles m-listBaseNoStyles--pm3">
										<#if customGetterCategoryLayout.getDescendantsByCategory(nav_item_layout,
																	global_theme_settings.lastModifiedCategoryId())??>
											<#assign lastModified = customGetterCategoryLayout.getDescendantsByCategory(nav_item_layout,
																	global_theme_settings.lastModifiedCategoryId()) >
											<#if lastModified?has_content>
												<#list lastModified as nav_child>
														<#if nav_child?index == 4>
															<#break>
														</#if>
														<li>
															<a href="${nav_child.friendlyURL}" class="i-mainNavigation__link2Level">
																<i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right mr-1"></i>
																${nav_child.name}
															</a>
														</li>
												</#list>
											</#if>
										</#if>
									</ul>
									<h2 class="i-title i-title--18 i-title--black">Lo más visto</h2>
									<ul class="m-listBaseNoStyles m-listBaseNoStyles--pm3">
										<li>
											<a href="/" class="i-mainNavigation__link2Level">
												<i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right mr-1"></i>
												Noticias Coorporativas
											</a>
										</li>
										<li>
											<a href="/" class="i-mainNavigation__link2Level">
												<i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right mr-1"></i>
												Boletín digital
											</a>
										</li>
										<li>
											<a href="/" class="i-mainNavigation__link2Level">
												<i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right mr-1"></i>
												Petición de ayuda escolar
											</a>
										</li>
										<li>
											<a href="/" class="i-mainNavigation__link2Level">
												<i class="i-icon i-icon--black fa-xs fa-solid fa-angle-right mr-1"></i>
												Buzón empleados
											</a>
										</li>
									</ul>
									</div>
									<a href="/consultas" class="btn btn-secondary mt-4 ema-20">
										<i class="i-icon i-icon--black fa-lg fa-regular fa-comment mr-3"></i>
										<@liferay.language key='es.emasesa.intranet.common.consultas'/>
									</a>
								</div>
							</div>
						</div>
					</div>
				<#else>
					<a class="i-mainNavigation__link" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>
				</#if>
			</li>
		</#list>
	</ul>
</nav>