<nav class="i-header__userNav">
    <ul class="i-header__userList">
        <li class="i-header__userItem selected">
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
            <a class="i-header__userLink" href="">
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
            <ul class="i-header__userItem__submenu hide" id="submenu-accesos-directos">
                <li class="i-header__userItem__submenu__item">
                    <h4 class="i-header__userItem__submenu__item__title">Accesos directos</h4>
                    <ul class="i-header__userItem__submenu__item__apps">
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-boletin-digital.svg" alt="" />
                                <span>Boletín digital</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-buzon-empleado.svg" alt="" />
                                <span>Buzón del empleado</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-biblioteca-digital.svg" alt="" />
                                <span>Biblioteca digital</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-calendario-laboral.svg" alt="" />
                                <span>Calendario laboral</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-control-horario.svg" alt="" />
                                <span>Control horario</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-gestion-salas.svg" alt="" />
                                <span>Gestión de salas</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-mi-formacion.svg" alt="" />
                                <span>Mi formación</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-nomina.svg" alt="" />
                                <span>Mi nómina</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-mis-vacaciones.svg" alt="" />
                                <span>Mis vacaciones</span>
                            </a>
                        </li>
                        <li class="i-header__userItem__submenu__item__apps__app">
                            <a href="#" title="Enlace a la aplicación" class="i-header__userItem__submenu__item__apps__app__link">
                                <img src="/o/emasesa-theme/images/icons/ico-organigrama.svg" alt="" />
                                <span>Organigrama</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
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
                    <p>María de los Ángeles de la Fuente Fernández</p>
                    <hr />
                    <h4 class="i-header__userItem__submenu__item__title">Suscripciones</h4>
                    <p><a href="/c/portal/logout" title="Cerrar sesión">Cerrar sesión <i class="fa-solid fa-arrow-right-from-bracket"></i></a></p>
                </li>
            </ul>
        </li>
    </ul>
</nav>
