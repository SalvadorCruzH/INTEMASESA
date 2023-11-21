import React from 'react';
import LiferayApi from "../services/LiferayApi";
import EmasesaApi from "../services/EmasesaApi"

class GlobalModule extends React.Component {

    constructor() {
        super();
        console.debug("Cargando módulo general");
        window.LiferayApi = LiferayApi;
        window.EmasesaApi = EmasesaApi;
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
