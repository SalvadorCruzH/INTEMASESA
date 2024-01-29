<nav class="i-header__userNav">
    <ul class="i-header__userList">
        <li class="i-header__userItem">
            <a href="${site_default_url}" class="i-header__userLink">
                <i class="i-icon i-icon--grey fa-solid fa-house"></i>
                <span class="sr-only"><@liferay.language key='es.emasesa.intranet.common.home'/></span>
            </a>
        </li>
        <#assign userId = theme_display.getUser().userId>
        <#assign customUserNotificationUtil = customUserNotificationUtil.getNotificationsByUserCount(userId) >
        <li class="i-header__userItem">
            <a href="${site_default_url}/inicio/notificaciones" class="i-header__userLink">
                <i class="i-icon i-icon--grey fa-solid fa-bell"></i>
                <span class="sr-only"><@liferay.language key='es.emasesa.intranet.common.notifications'/></span>
                <span class="ema-notif">${customUserNotificationUtil}</span>
            </a>
        </li>
        <li class="i-header__userItem">
            <a class="i-header__userLink" href="#">
                <i class="i-icon i-icon--grey fa-solid fa-envelope"></i>
                <span class="sr-only"><@liferay.language key='es.emasesa.intranet.common.mail'/></span>
            </a>
        </li>
        <li class="i-header__userItem">
            <a href="${site_default_url}/inicio/directorio-del-personal" class="i-header__userLink">
                <i class="i-icon i-icon--grey fa-solid fa-users"></i>
                <span class="sr-only"><@liferay.language key='es.emasesa.intranet.common.user'/></span>
            </a>
        </li>
        <li class="i-header__userItem">
            <button id="submenu-accesos-directos-btn" class="i-header__userLink">
                <i class="i-icon i-icon--grey fa-solid fa-grip"></i>
                <span class="sr-only"><@liferay.language key='es.emasesa.intranet.common.apps'/></span>
            </button>
            <ul class="i-header__userItem__submenu__item__apps">
                <#if favoritosServiceSettings??>
                    <#assign favoritoObjectId = favoritosServiceSettings.objectDefinitionId() >
                    <#if (favoritoObjectId??) && (favoritoObjectId > 0) >
                        <#assign entries = customEmasesaUtil.searchFavoritesJournalsArticleByUserAndDDMStructureKey(themeDisplay, "EMA-ACCESO-DIRECTO", -1, -1, favoritoObjectId) >
                        <#if entries?has_content>
                            <#list entries as curEntry>
                                <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
                                <#assign ddmStructure = journalArticle.getDDMStructure() />
                                <#assign ddmForm = ddmStructure.getDDMForm()/>
                                <#assign ddmFormValues = ddmFieldLocalService.getDDMFormValues(ddmForm, journalArticle.getId()) />
                                <#assign ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues() />
                                <#assign urlJournal = ""
                                    name = ""
                                    icono = "">
                                <#list ddmFormFieldValues as fieldName>
                                    <#if fieldName.getFieldReference() == 'url'>
                                        <#assign urlJournal = fieldName.getValue().getString(locale) />
                                    </#if>
                                    <#if fieldName.getFieldReference() == 'name'>
                                        <#assign name = fieldName.getValue().getString(locale) />
                                    </#if>
                                    <#if fieldName.getFieldReference() == 'icono'>
                                        <#assign icono = fieldName.getValue().getString(locale) />
                                    </#if>
                                </#list>
                                <li class="i-header__userItem__submenu__item__apps__app">
                                    <a href="${urlJournal}" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link" target="_blank">
                                        <img src="${icono}" alt="" data-fileentryid=""/>
                                        <span>${name}</span>
                                    </a>
                                </li>
                            </#list>
                        </#if>
                    </#if>
                </#if>
            </ul>
            <#--  <ul class="i-header__userItem__submenu hide" id="submenu-accesos-directos">
                <li class="i-header__userItem__submenu__item">
                    <h4 class="i-header__userItem__submenu__item__title">Accesos directos</h4>
                    <ul class="i-header__userItem__submenu__item__apps">
                    	<#assign favoritoObjectId = favoritosServiceSettings.objectDefinitionId() >
                        <#if favoritoObjectId != ''>
                            <#assign entries = customEmasesaUtil.searchFavoritesJournalsArticleByUserAndDDMStructureKey(themeDisplay, "EMA-ACCESO-DIRECTO", -1, -1, favoritoObjectId) >
                            <#if entries?has_content>
                                <#list entries as curEntry>
                                    <#assign journalArticle = curEntry.getAssetRenderer().getAssetObject() />
                                    <#assign ddmStructure = journalArticle.getDDMStructure() />
                                    <#assign ddmForm = ddmStructure.getDDMForm()/>
                                    <#assign ddmFormValues = ddmFieldLocalService.getDDMFormValues(ddmForm, journalArticle.getId()) />
                                    <#assign ddmFormFieldValues = ddmFormValues.getDDMFormFieldValues() />
                                    <#assign urlJournal="" name="" icono="" />
                                    <#list ddmFormFieldValues as fieldName>
                                        <#if fieldName.getFieldReference() == 'url'>
                                            <#assign urlJournal = fieldName.getValue().getString(locale) />
                                        </#if>
                                        <#if fieldName.getFieldReference() == 'name'>
                                            <#assign name = fieldName.getValue().getString(locale) />
                                        </#if>
                                        <#if fieldName.getFieldReference() == 'icono'>
                                            <#assign icono = fieldName.getValue().getString(locale) />
                                        </#if>
                                    </#list>
                                    <li class="i-header__userItem__submenu__item__apps__app">
                                        <a href="${urlJournal}" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link" target="_blank">
                                            <img src="${icono.name}" alt="" data-fileentryid=""/>
                                            <span>${name}</span>
                                        </a>
                                    </li>
                                </#list>
                            </#if>
                        </#if>
                    </ul>
                </li>
            </ul>  -->
        </li>
        <li class="i-header__userItem">
            <a href="${site_default_url}/inicio/favoritos" class="i-header__userLink">
                <i class="i-icon i-icon--grey fa-solid fa-heart"></i>
                <span class="sr-only"><@liferay.language key='es.emasesa.intranet.common.favorites'/></span>
            </a>
        </li>
        <li class="i-header__userItem">
            <button id="submenu-usuario-btn" class="i-header__userLink">
                <i class="i-icon i-icon--grey fa-solid fa-user"></i>
                <span class="sr-only"><@liferay.language key='es.emasesa.intranet.common.user'/></span>
            </button>
            <ul class="i-header__userItem__submenu hide" id="submenu-usuario">
                <li class="i-header__userItem__submenu__item">
                    <h4 class="i-header__userItem__submenu__item__title">Mi perfil</h4>
                    <p>${theme_display.getUser().getFullName()}</p>
                    <hr />
                    <h4 class="i-header__userItem__submenu__item__title">Suscripciones</h4>
                    <p><a href="/c/portal/logout" title="Cerrar sesión">Cerrar sesión <i class="fa-solid fa-arrow-right-from-bracket"></i></a></p>
                </li>
            </ul>
        </li>
    </ul>
</nav>
<script>
    const currentLocation = window.location.href
    let sidebarLinks = document.querySelectorAll('.i-header__userLink')
    for (let i = 0; i < sidebarLinks.length; i++) {
        if (sidebarLinks[i].href === currentLocation) {
            sidebarLinks[i].classList.add('selected')
        }
    }
</script>
