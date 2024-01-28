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
            tasksCount : null,
            hasMore : true,
            loading: true,
            start: 0,
            end: 10,
            delta: 10,
            filterSelected: "-",
            sort: false
        }

        console.debug("Cargando módulo notificaciones");
        EmasesaApi.getConfiguration(this.loadConfiguration, this.errorHandler)
    }

    loadNotificationsRefresh = () => {
        this.loadNotifications(0, 10);
    }

    loadNotifications = (start, end) => {
        this.setState({loading: true});
        console.debug(this.state.filterSelected);
        if(start !== undefined ){
            NotificationApi.getNotificationsUnRead(this.state.filterSelected, start, end, this.state.sort, this.buildNotifications, this.errorHandler);
        }else{
            NotificationApi.getNotificationsUnRead(this.state.filterSelected, this.state.start, this.state.end, this.state.sort, this.buildNotifications, this.errorHandler);
        }

    }

    showLess = () => {
        let start = this.state.start;
        let end = this.state.end;
        start = start - this.state.delta;
        end = end - this.state.delta
        if(start <= 0){
            this.setState( {
                start: 0,
                end: 10
            });
        }else{
            this.setState( {
                start: start,
                end: end
            });
        }
        this.loadNotifications(start, end)
    }

    showMore = () => {
        let start = this.state.start;
        let end = this.state.end;
        start = start + this.state.delta;
        end = end + this.state.delta
        this.setState( {
            start: start,
            end: end
        });
        this.loadNotifications(start, end)
    }

    buildNotifications = (result) => {

        if(result.notifications && result.notifications.length > 0){
            console.debug(result.notifications);
            this.setState( {
                notifications: result.notifications,
                notificationsCount: result.count,
                hasMore: result.noMore
            });
            if(this.state.filterSelected === '-'){
                document.querySelector(".ema-notif").textContent = result.count;
            }
            this.setState({loading: false});
        }else{
            this.setState( {
                notifications: [],
                loading: false
            });
        }
    }

    loadFilter = (target) => {
        console.debug(target.target.value);
        this.setState({filterSelected: target.target.value}, function (){
            this.loadNotificationsRefresh()
        });
    }

    loadSort = (target) => {
        console.debug(target.target.dataset.sort);
        this.setState({sort: target.target.dataset.sort}, function (){
            this.loadNotificationsRefresh()
        });
        if(target.target.dataset.sort === 'false'){
            document.getElementById('sortButton').dataset.sort = 'true';
        }else{
            document.getElementById('sortButton').dataset.sort = 'false';
        }
    }

    loadConfiguration = (result) => {
        console.debug("configurationData");
        console.debug(result);
        if (result) {
            this.setState({
                configuration: result
            });
            Object.keys(result.objectMapping).forEach(function(k){
                console.log(k + ' - ' + result.objectMapping[k]);
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

    markAllAsReadButton  = (e) => {
        let msg = Liferay.Language.get("notifications.markAll.confirm");
        if (confirm(msg)) {
            let arraySelected = [];
            document.querySelectorAll('input.checkBoxRead').forEach(checkbox => {
                arraySelected.push(checkbox.value);
            });
            console.log(arraySelected);
        }
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
                                    aria-selected="false">{Liferay.Language.get("notifications.task.list")} {this.state.tasksCount ? "("+this.state.tasksCount+")": ""}
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
                                            aria-labelledby="Filtrarpor-ariaLabel" onChange={(x) => this.loadFilter(x)}>
                                        <option value="-">{Liferay.Language.get("notifications.filtrar")}</option>
                                        {(this.state.configuration && this.state.configuration.objectMapping)? (
                                                Object.keys(this.state.configuration.objectMapping).map(v => {
                                                    return (<option value={v}>{v}</option>)
                                                })
                                            )
                                            : <></>
                                        }
                                    </select>
                                </div>
                                <div className="form-row notification-filters__submit">
                                    <i className="fa-solid fa-arrow-right-arrow-left"></i> <input type="button" id="sortButton" onClick={x => this.loadSort(x)}
                                                                                                  data-sort="true"
                                                                                                  value={Liferay.Language.get("notifications.ordenar")}
                                                                                                  aria-label={Liferay.Language.get("notifications.ordernar.list}")}/>
                                </div>
                                <button className="btn btn-primary ema-notifications__item__btn" type="button" onClick={this.markAllAsReadButton}><i
                                    className="fa-regular fa-eye"></i> {Liferay.Language.get("notifications.markAll")}
                                </button>
                            </form>

                            <div className="ema-notifications__items">
                                {(this.state.loading) ? (<ClayLoadingIndicator displayType="primary" size="lg"/>) : (<>
                                    {this.state.notifications && this.state.notifications.length != 0 ? (<>
                                            {this.state.notifications.map((notification, i) => {
                                                return (<><Notification notification={notification}
                                                                        configuration={this.state.configuration}
                                                                        refresh={this.loadNotificationsRefresh}
                                                                        loading={this.setLoading}/>
                                                    </>
                                                )
                                            })}
                                        </>
                                    ) : (<>
                                            <div>No se han encontado elementos</div>
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
                                                                <button type="button" onClick={this.showLess} aria-label={Liferay.Language.get("notifications.go.before")} disabled={this.state.start === 0}><i
                                                                    className="fa-solid fa-chevron-left fa-xs"></i>{Liferay.Language.get("notifications.before")}
                                                                </button>
                                                            </li>
                                                            <li className="paginationjs-next J-paginationjs-next m-link-accessible-wrapper linkify"
                                                                data-num="2" aria-label="Página siguiente">
                                                                <button type="button" onClick={this.showMore} disabled={this.state.hasMore}
                                                                        aria-label={Liferay.Language.get("notifications.go.next")}>{Liferay.Language.get("notifications.next")}<i
                                                                    className="fa-solid fa-chevron-right fa-xs"></i>
                                                                </button>
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
                             aria-labelledby="second-tab">{Liferay.Language.get("notifications.task.list")}
                        </div>
                    </div>

                </div>
            </>
        )
    }
}

export default NotificationsModule;
