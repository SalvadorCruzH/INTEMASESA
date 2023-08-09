<nav class="i-mainNavigation" aria-label="<@liferay.language key="site-pages" />" id="navigation" role="navigation">
	<ul class="i-mainNavigation__ul m-listBase m-dFlexSBCenter" role="menubar">
		<#list nav_items as nav_item>
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
					<a class="i-mainNavigation__link" ${nav_item_attr_has_popup} href="javascript:void(0)" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>
					<div class="i-mainNavigation__submenuContainer">
						<div class="i-mainNavigation__submenuWrapper">
							<button id="i-closeButton" aria-label="<@liferay.language key='close' />" class="btn flex-shrink-0 i-btn i-btn--close" type="button">
								<svg class="lexicon-icon i-close"><use xlink:href="${images_folder}/lexicon/icons.svg#times" /></svg>
							</button>
							<div class="i-mainNavigation__innerWrapper">
								<ul class="i-mainNavigation__submenuUl m-listBaseNoStyles" role="menu">
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
											<a class="i-mainNavigation__link2" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
															<#--  2º level descomentar para 2 y 3 nivel  -->
											<#if nav_child.hasChildren()>
												<ul class="child-menu i-mainNavigation__submenuUl2" role="menu">
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
															<a class="i-mainNavigation__link2Level" href="${nav_child2.getURL()}" ${nav_child2.getTarget()} role="menuitem">${nav_child2.getName()}</a>
														</li>
													</#list>
												</ul>
											</#if>
										</li>
									</#list>
								</ul>
								<div class="i-wrapperBR i-wrapperBR--blue">
									<h2 class="i-title i-title--18 i-title--black">Hemos actualizado</h2>
									<ul>
										<li>Información General y Prestaciones</li>
										<li>Ofertas especiales para el colectivo de Emasesa</li>
										<li>Sistemas de gestión ambiental</li>
										<li>Sistemas de Gestión de la energía</li>
									</ul>
									<h2 class="i-title i-title--18 i-title--black">Lo más visto</h2>
									<ul>
										<li>Noticias Coorporativas</li>
										<li>Boletín digital</li>
										<li>Petición de ayuda escolar</li>
										<li>Buzón empleados</li>
									</ul>
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