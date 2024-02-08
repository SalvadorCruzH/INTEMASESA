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
        window.transitionsLabel = [];
        window.transitionsLabel["Devolver-a-usuario-para-edicion"] = Liferay.Language.get("es.emasesa.transition.label.Devolver-a-usuario-para-edicion");
        window.transitionsLabel["Rechazar-y-guardar-en-sigd"] = Liferay.Language.get("es.emasesa.transition.label.Rechazar-y-guardar-en-sigd");
        window.transitionsLabel["Aprobada-enviar-asesor-juridico"] = Liferay.Language.get("es.emasesa.transition.label.Aprobada-enviar-asesor-juridico");
        window.transitionsLabel["assignToMe"] = Liferay.Language.get("es.emasesa.transition.label.assignToMe");
        window.transitionsLabel["consultar"] = Liferay.Language.get("es.emasesa.transition.label.consultar");
        window.transitionsLabel["Mandar-a-portafirmas"] = Liferay.Language.get("es.emasesa.transition.label.Mandar-a-portafirmas");
        window.transitionsLabel["aceptar"] = Liferay.Language.get("es.emasesa.transition.label.aceptar");
        window.transitionsLabel["rechazar"] = Liferay.Language.get("es.emasesa.transition.label.rechazar");
        window.transitionsLabel["portafirma-director-RRHH"] = Liferay.Language.get("es.emasesa.transition.label.portafirma-director-RRHH");
        window.transitionsLabel["reenviar-solicitud"] = Liferay.Language.get("es.emasesa.transition.label.reenviar-solicitud");
        window.transitionsLabel["rechazarSolicitudResponsable"] = Liferay.Language.get("es.emasesa.transition.label.rechazarSolicitudResponsable");
        window.transitionsLabel["aprobarSolicitudResponsable"] = Liferay.Language.get("es.emasesa.transition.label.aprobarSolicitudResponsable");
        window.transitionsLabel["Rechazar-peticion"] = Liferay.Language.get("es.emasesa.transition.label.Rechazar-peticion");
        window.transitionsLabel["Rechazar-peticion-1"] = Liferay.Language.get("es.emasesa.transition.label.Rechazar-peticion-1");
        window.transitionsLabel["Rechazar-peticion-2"] = Liferay.Language.get("es.emasesa.transition.label.Rechazar-peticion-2");
        window.transitionsLabel["Rechazar-peticion-3"] = Liferay.Language.get("es.emasesa.transition.label.Rechazar-peticion-3");
        window.transitionsLabel["Rechazar-peticion-4"] = Liferay.Language.get("es.emasesa.transition.label.Rechazar-peticion-4");
        window.transitionsLabel["Rechazar-peticion-5"] = Liferay.Language.get("es.emasesa.transition.label.Rechazar-peticion-5");
        window.objToString = function(obj) {
            var str = '';
            for (var p in obj) {
                if (Object.prototype.hasOwnProperty.call(obj, p)) {
                    str += p + '::' + obj[p] + '\n';
                }
            }
            return str;
        }
        window.formatDateToDdMmYyyy = function (dateString) {
            const date = new Date(dateString);
            const day = String(date.getDate()).padStart(2, '0');
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const year = date.getFullYear();
            return `${day}/${month}/${year}`;
        };

        window.formatDateToDdMmYyyyHhMMss = function (dateString) {
            const date = new Date(dateString);
            const day = String(date.getDate()).padStart(2, '0');
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const year = date.getFullYear();
            const hour = String(date.getHours()).padStart(2, '0');
            const minutes = String(date.getMinutes()).padStart(2, '0');
            return `${day}/${month}/${year} - ${hour}:${minutes}`;
        };

        window.timestampToDdMmYyyy = function (timestamp) {
            const date = new Date(timestamp);
            return window.formatDateToDdMmYyyyHhMMss(date);
        }

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
