import React from 'react';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import NotificationApi from "../services/NotificationApi";

var onlyonce = true;
var actionExecuted = "";
class Notification extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            configuration: props.configuration,
            notification: props.notification,
            refresh: props.refresh
        }
    }

    componentDidMount() {

    }

    markAsRead = () => {
        console.log(this.state.notification)
        NotificationApi.markAsRed(this.state.notification.userNotificationEventId, this.callDataCallback, this.callDataCallback);
    }

    callDataCallback = (data) => {
        let message = "La operación se ha realizado correctamente";
        Liferay.Util.openToast({
            message: message,
            title: Liferay.Language.get('global.success'),
            toastProps: {
                autoClose: 5000,
            },
            type: 'success',
        });
        setTimeout(this.state.refresh, 100);
    }

    errorHandler = (error) => {

        Liferay.Util.openToast({
            message: 'Se ha producido un error marcando como leído',
            title: Liferay.Language.get('global.error'),
            toastProps: {
                autoClose: 5000,
            },
            type: 'danger',
        });

    }
    render() {

        return (<>
            <div className="ema-notifications__item ema-notifications__item--unread">
                <div
                    className="form-row ema-notifications__item__col ema-notifications__item__col--check">
                    <input id="chk_Seleccionar" name="chk_Seleccionar" type="checkbox"
                           aria-labelledby="Seleccionar-ariaLabel" className={"checkBoxRead"} value={this.state.notification.userNotificationEventId}/>
                    <label htmlFor="chk_Seleccionar" id="Seleccionar-ariaLabel"
                           className="sr-only">{Liferay.Language.get("notifications.seleccionar")}</label>
                </div>
                <div
                    className="ema-notifications__item__col ema-notifications__item__col--user-avatar">
                    <img src={"/image"+this.state.notification.imageURL}
                         alt="Avatar temporal"/>
                </div>
                <div
                    className="ema-notifications__item__col ema-notifications__item__col--notification">
                    <h3 className="ema-notifications__item__title">{this.state.notification.payload.entryType}</h3>
                    <ul>
                        <li><span
                            className="ema-notifications__item__label">Remitente:</span> {this.state.notification.userName}
                        </li>
                        <li><span
                            className="ema-notifications__item__label">Mensaje:</span> {this.state.notification.payload.notificationMessage}
                        </li>
                        <li><span
                            className="ema-notifications__item__label">{Liferay.Language.get("notifications.fecha")}:</span> {this.state.notification.timestamp}
                        </li>
                    </ul>
                    <p>
                        <button className="btn btn-primary ema-notifications__item__btn" type="button" data-event={this.state.notification.userNotificationEventId} onClick={this.markAsRead}><i
                            className="fa-regular fa-eye"></i> {Liferay.Language.get("notifications.mark.read")}
                        </button>
                    </p>
                </div>
            </div>
        </>);

    }
}

export default Notification;