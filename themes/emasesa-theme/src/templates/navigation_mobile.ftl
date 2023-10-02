<nav class="i-navMainMobile" aria-label="<@liferay.language key="site-pages" />" id="navigation" role="navigation">
	<ul class="i-navMainMobile__ul m-listBaseNoStyles" role="menubar">
		<#list nav_items as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_css_class = "i-navMainMobile__li"
				nav_item_layout = nav_item.getLayout()
			/>
			<#if nav_item.hasChildren()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class = "i-navMainMobile__li children"
				/>
			</#if>

			<#if nav_item.isSelected()> 
				<#assign
					nav_item_css_class = "i-navMainMobile__li selected"
					
				/>
				<#if nav_item.hasChildren()>
					<#assign
						nav_item_attr_has_popup = "aria-haspopup='true'"
						nav_item_css_class = "i-navMainMobile__li selected children"
					/>
				</#if>
			</#if>
			

			<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
				
				<#if nav_item.hasChildren()>
					<a class="i-navMainMobile__link" ${nav_item_attr_has_popup} href="javascript:void(0)" ${nav_item.getTarget()} role="menuitem">
                        <span class="i-navMainMobile__span">${nav_item.getName()}</span>
                        <i class="fa-solid fa-angle-right fa-xs mr-1"></i>
                    </a>
						<ul class="i-navMainMobile__submenuUl m-listBaseNoStyles" role="menu">
							<#list nav_item.getChildren() as nav_child>
										<#assign
											nav_child_css_class = "i-navMainMobile__submenuLi"
										/>
										<#if nav_child.isSelected()>
											<#assign
												nav_child_css_class = "i-navMainMobile__submenuLi selected"
											/>	
										</#if>

										<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
											<a class="i-navMainMobile__link2" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
															
											<#if nav_child.hasChildren()>
												<ul class="child-menu i-navMainMobile__submenuUl2 m-listBaseNoStyles" role="menu">
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
															<i class="fa-solid fa-chevron-right fa-xs mr-1"></i>
															<a class="i-navMainMobile__link2Level" href="${nav_child2.getURL()}" ${nav_child2.getTarget()} role="menuitem">${nav_child2.getName()}</a>
														</li>
													</#list>
												</ul>
											</#if>
										</li>
							</#list>
						</ul>
				<#else>
					<a class="i-navMainMobile__link" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
                        <span class="i-navMainMobile__span">${nav_item.getName()}</span>
                        <i class="fa-solid fa-angle-right fa-xs mr-1"></i>
                    </a>
				</#if>
			</li>
		</#list>
	</ul>
</nav>