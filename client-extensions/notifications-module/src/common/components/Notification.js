import React from 'react';
import ClayLoadingIndicator from '@clayui/loading-indicator';

var onlyonce = true;
var actionExecuted = "";
class Notification extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            configuration: props.configuration,
            notification: props.notification
        }
    }

    componentDidMount() {

    }

    render() {

        return (<>
            <div className="ema-notifications__item ema-notifications__item--unread">
                <div
                    className="form-row ema-notifications__item__col ema-notifications__item__col--check">
                    <input id="chk_Seleccionar" name="chk_Seleccionar" type="checkbox"
                           aria-labelledby="Seleccionar-ariaLabel"/>
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
                        <li><span className="ema-notifications__item__label">Remitente:</span> {this.state.notification.userName}
                        </li>
                        <li><span className="ema-notifications__item__label">Mensaje:</span> {this.state.notification.payload.notificationMessage}
                        </li>
                        <li><span className="ema-notifications__item__label">{Liferay.Language.get("notifications.fecha")}:</span> {this.state.notification.timestamp}
                        </li>
                    </ul>
                </div>
            </div>
        </>);

    }
}

export default Notification;