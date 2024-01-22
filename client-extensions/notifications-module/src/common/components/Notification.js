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
        console.debug(this);
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
                    <img src="/o/emasesa-theme/images/decorative/temp-avatar.png"
                         alt="Avatar temporal"/>
                </div>
                <div
                    className="ema-notifications__item__col ema-notifications__item__col--notification">
                    <h3 className="ema-notifications__item__title">Título de la notificación</h3>
                    <ul>
                        <li><span className="ema-notifications__item__label">Remitente:</span> Admin
                        </li>
                        <li><span className="ema-notifications__item__label">Mensaje:</span> We need
                            to use the Present Simple a lot in English, so it's really important to
                            understand it well. Many students have problems with the form (or how to
                            make it).
                        </li>
                        <li><span className="ema-notifications__item__label">{Liferay.Language.get("notifications.fecha")}:</span> 2 enero
                            2024 | 11:24
                        </li>
                    </ul>
                </div>
            </div>
        </>);

    }
}

export default Notification;