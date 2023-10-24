import React from 'react';
import LiferayApi from "../services/LiferayApi";

class GlobalModule extends React.Component {

    constructor() {
        super();
        console.debug("Cargando módulo general");
        window.LiferayApi = LiferayApi;
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
