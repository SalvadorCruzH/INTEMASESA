import React from 'react';
import LiferayApi from "../services/LiferayApi";
import EmasesaApi from "../services/EmasesaApi"
import * as Constants from "../js/Constants";

class GlobalModule extends React.Component {

    constructor() {
        super();
        console.debug("Cargando módulo general");
        window.LiferayApi = LiferayApi;
        window.EmasesaApi = EmasesaApi;
        window.GlobalExtensionConstants = Constants;
        window.simulateGlobalClick = function (elem) {
            // Create our event (with options)
            var evt = new MouseEvent('click', {
                bubbles: true,
                cancelable: true,
                view: window
            });
            // If cancelled, don't dispatch our event
            var canceled = !elem.dispatchEvent(evt);
            evt.stopPropagation()
        };
        LiferayApi.test();

        this.state = {
        };
    }

    render() {
        return (
            <>
            </>
        )
    }
}

export default GlobalModule;
