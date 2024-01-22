import React from 'react';
import ObjectApi from "../services/ObjectApi";
import * as Constants from "../js/Constants";
import ClayLoadingIndicator from '@clayui/loading-indicator';
import NotificationApi from "../services/NotificationApi";
import Notification from "./Notification";

class NotificationsModule extends React.Component {
    constructor() {
        super();
        this.state = {
            configuration: null,
            mode: 1,
            notifications: [],
            notificationsCount : null,
            loading: true
        }

        console.debug("Cargando módulo notificaciones");
        EmasesaApi.getConfiguration(this.loadConfiguration, this.errorHandler)
    }

    loadNotifications = () => {
        NotificationApi.getNotificationsUnRead(this.buildNotifications, this.errorHandler);
    }

    buildNotifications = (result) => {

        console.log(result.notifications);
        this.setState( {
            notifications: result.notifications
        });
        this.setState({loading: false});
        console.log(this.state.notifications);
    }

    setObject = (object) => {

    }

    loadConfiguration = (result) => {
        console.debug("configurationData");
        console.debug(result);
        if (result) {
            this.setState({
                configuration: result
            });
            this.loadNotifications();
        }
    }

    errorHandler = (error) => {

        Liferay.Util.openToast({
            message: Liferay.Language.get('global.error.config'),
            title: Liferay.Language.get('global.error'),
            toastProps: {
                autoClose: 5000,
            },
            type: 'danger',
        });

    }

    markAllAsRead  = (e) => {

        console.debug(e);
        if (e.target.value == 'Check All' || e.target.value == 'on') {
            document.querySelectorAll('input.checkBoxRead').forEach(checkbox => {
                checkbox.checked = true;
            });
            e.target.value = 'Uncheck All';
        } else {
            document.querySelectorAll('input.checkBoxRead').forEach(checkbox => {
                checkbox.checked = false;
            });
            e.target.value = 'Check All';
        }
    }

    setLoading = (loadingVar) => {
        this.setState({loading: loadingVar});
    }

    render() {
        return (
            <>
                <div className="ema-notifications">

                    <ul className="nav nav-tabs ema-tabs" id="ema-tabs" role="tablist">
                        <li className="nav-item ema-tabs__tab" role="presentation">
                            <button className="nav-link  ema-tabs__tab__btn active" id="first-tab" data-toggle="tab"
                                    data-target="#first" type="button" role="tab" aria-controls="first"
                                    aria-selected="true">{Liferay.Language.get("notifications.list")} {this.state.notificationsCount  ? "("+this.state.notificationsCount+")": ""}
                            </button>
                        </li>
                        <li className="nav-item ema-tabs__tab" role="presentation">
                            <button className="nav-link  ema-tabs__tab__btn" id="second-tab" data-toggle="tab"
                                    data-target="#second" type="button" role="tab" aria-controls="second"
                                    aria-selected="false">{Liferay.Language.get("notifications.task.list")} {this.state.notificationsCount ? "("+this.state.notificationsCount+")": ""}
                            </button>
                        </li>
                    </ul>
                    <div className="tab-content ema-tabs__tab-content" id="myTabContent">
                        <div className="tab-pane  ema-tabs__tab-panel fade show active" id="first" role="tabpanel"
                             aria-labelledby="first-tab">

                            <form method="post" id="notification-filters" className="notification-filters">
                                <div className="form-row">
                                    <input id="chk_Seleccionartodo" name="chk_Seleccionartodo" type="checkbox"
                                           aria-labelledby="Seleccionartodo-ariaLabel"
                                           onClick={(e) => this.markAllAsRead(e)}/>
                                    <label htmlFor="chk_Seleccionartodo" id="Seleccionartodo-ariaLabel"
                                           className="sr-only">{Liferay.Language.get("notifications.select.all")}</label>
                                </div>
                                <div className="form-row">
                                    <label htmlFor="sel_Filtrarpor" id="Filtrarpor-ariaLabel" className="sr-only">Filtrar
                                        por</label>
                                    <select id="sel_Filtrarpor" name="sel_Filtrarpor"
                                            aria-labelledby="Filtrarpor-ariaLabel">
                                        <option value="">{Liferay.Language.get("notifications.filtrar")}</option>
                                        <option value="Fecha">Fecha</option>
                                        <option value="Urgencia">Urgencia</option>
                                        <option value="Departamento">Departamento</option>
                                    </select>
                                </div>
                                <div className="form-row notification-filters__submit">
                                    <i className="fa-solid fa-arrow-right-arrow-left"></i> <input type="submit"
                                                                                                  value={Liferay.Language.get("notifications.ordenar")}
                                                                                                  aria-label={Liferay.Language.get("notifications.ordernar.list}")}/>
                                </div>
                                <button className="btn btn-primary ema-notifications__item__btn" type="button"><i
                                    className="fa-regular fa-eye"></i> Marcar todo como leído
                                </button>
                            </form>

                            <div className="ema-notifications__items">
                                {(this.state.loading) ? (<ClayLoadingIndicator displayType="primary" size="lg"/>) : (<>
                                    {this.state.notifications && this.state.notifications.length != 0 ? (<>
                                            {this.state.notifications.map((notification, i) => {
                                                console.log(notification)
                                                return (<><Notification notification={notification}
                                                                        configuration={this.state.configuration}
                                                                        refresh={this.loadNotifications}
                                                                        loading={this.setLoading}/>
                                                    </>
                                                )
                                            })}
                                        </>
                                    ) : (<>
                                        </>
                                    )}
                                    <div className="results-pagination-wrapper">
                                        <div className="results-pagination-select-container">

                                        </div>
                                        <section>

                                            <div id="emasesa_pagination" className="pagination">
                                                <div className="paginationjs">
                                                    <nav className="paginationjs-pages" aria-label="Pagination">
                                                        <ul>
                                                            <li className="paginationjs-prev disabled m-link-accessible-wrapper linkify">
                                                                <a href="#"><i
                                                                    className="fa-solid fa-chevron-left fa-xs"></i>Anterior</a>
                                                            </li>
                                                            <li className="paginationjs-next J-paginationjs-next m-link-accessible-wrapper linkify"
                                                                data-num="2" aria-label="Página siguiente">
                                                                <a href="#" aria-label="Ir a página 2">Siguiente<i
                                                                    className="fa-solid fa-chevron-right fa-xs"></i></a>
                                                            </li>
                                                        </ul>
                                                    </nav>
                                                </div>
                                            </div>
                                        </section>
                                    </div>
                                </>)}
                            </div>

                        </div>
                        <div className="tab-pane  ema-tabs__tab-panel fade" id="second" role="tabpanel"
                             aria-labelledby="second-tab">Lista de Peticiones
                        </div>
                    </div>

                </div>
            </>
        )
    }
}

export default NotificationsModule;
