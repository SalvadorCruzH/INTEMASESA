import React from 'react';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import TareasApi from '../services/TareasApi';
import {OBJECT_MAPPING, OBJECT_CONSTANTS, iframeURLChange} from "../js/Constants";

let onlyonce = true;

class Actions extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            assigneePerson: props.tarea.assigneeUserId,
            actions: props.tarea.actions,
            tareaid: props.tarea.workflowTaskId,
            transitions: props.tarea.transitions,
            refresh: props.refresh,
            objectReviewed: props.tarea.attributes.entryType,
            externalReferenceCode: props.tarea.objectData.externalReferenceCode,
            completed: props.tarea.completed,
            configuration: props.configuration,
            loading: true,
            actionExecuted: ""
        }
        console.debug(this);
    }

    componentDidMount() {

    }

    callAction = (event) => {

        let action = this.state.actions[event.target.dataset.action];
        let data = {
            "comment": "",
            "dueDate": new Date().toISOString(),
            "workflowTaskId": 0
        }
        this.setState({
            actionExecuted: action
        })
        TareasApi.callAction(action.href, data, this.callDataCallback, this.errorHandler);
    }

    callDataCallback = (data) => {
        let actionExecuted = this.state.actionExecuted;
        let message = '';
        console.debug(actionExecuted.toString());
        if (actionExecuted && window.objToString(actionExecuted).indexOf('assign-to-me') > 0) {
            message = Liferay.Language.get('es.emasesa.transition.label.assignToMe.success');
        } else if (actionExecuted && window.objToString(actionExecuted).indexOf('Aprobada-enviar-asesor-juridico') >= 0) {
            message = Liferay.Language.get('es.emasesa.transition.label.Aprobada-enviar-asesor-juridico.success');
        }else if (actionExecuted && window.objToString(actionExecuted).indexOf('Rechazar-y-guardar-en-sigd') >= 0) {
            message = Liferay.Language.get('es.emasesa.transition.label.Rechazar-y-guardar-en-sigd.success');
        }else if (actionExecuted && window.objToString(actionExecuted).indexOf('Devolver-a-usuario-para-edicion.success') >= 0) {
            message = Liferay.Language.get('es.emasesa.transition.label.Devolver-a-usuario-para-edicion.success.success');
        }

        if (message.length > 0) {
            Liferay.Util.openToast({
                message: message,
                title: Liferay.Language.get('global.success'),
                toastProps: {
                    autoClose: 5000,
                },
                type: 'success',
            });
        }
        setTimeout(this.props.refresh, 100);
    }


    changeTransition = (event) => {
        let workflowTaskId = this.state.tareaid;
        let transitionName = event.target.dataset.name;
        let data = {
            "comment": "",
            "transitionName": transitionName,
            "workflowTaskId": workflowTaskId
        }
        this.setState({
            actionExecuted: transitionName
        })
        TareasApi.changeTransition(workflowTaskId, data, this.callDataCallback, this.errorHandler);
    }

    assignToMe = (event) => {
        let workflowTaskId = this.state.tareaid;
        let transitionName = event.target.dataset.name;
        let data = {
            "comment": "",
            "dueDate": new Date().toISOString(),
            "workflowTaskId": workflowTaskId
        }
        this.setState({
            actionExecuted: action
        })
        TareasApi.assignToMe(workflowTaskId, data, this.callDataCallback, this.errorHandler);
    }


    errorHandler = (error) => {
        if (onlyonce) {
            Liferay.Util.openToast({
                message: Liferay.Language.get('global.error.config') + " " + error,
                title: Liferay.Language.get('global.error'),
                toastProps: {
                    autoClose: 5000,
                },
                type: 'danger',
            });
        }
        onlyonce = false;
    }

    openModal = (event) => {
        let objectReviewed = this.state.objectReviewed;
        let jsonObjectMapping = {};
        try{
            jsonObjectMapping = JSON.parse(this.state.configuration.objectMapping);
        }catch(e){
            jsonObjectMapping = this.state.configuration.objectMapping;
        }
        let objectMapping = jsonObjectMapping[objectReviewed.assetType];
        if (objectMapping) {
            let display = objectMapping.display;
            display += "?objectEntryId=" + objectReviewed.id;
            display += "&objectType=" + objectReviewed.assetType;
            display += "&mode=1";
            display += "&p_p_state=pop_up";
            let url = themeDisplay.getPortalURL() + display;
            let dialog = Liferay.Util.openWindow({
                cache: false,
                dialog: {
                    destroyOnHide: true,
                    modal: true,
                    cssClass:'dialog-with-footer i-mainWrapper',
                    after: {
                        render: function(event) {},
                        destroy: function(event) {
                        }
                    },
                    toolbars: {
                        footer: [
                            {
                                label: 'Cerrar',
                                cssClass: 'btn-link pull-right btn btn-secondary',
                                on: {
                                    click: function() {
                                        let dialog = Liferay.Util.Window.getById('consultarPeticionDialog_iframe_');
                                        dialog.destroy();
                                    }
                                },
                            }
                        ]
                    }
                },
                dialogIframe: {
                    bodyCssClass: 'dialog-with-footer i-mainWrapper'
                },
                id: 'consultarPeticionDialog',
                refreshWindow: window,
                title: 'Consulta de '+objectReviewed.assetType,
                uri: url
            });

            //window.open(url, "_blank")
        }
    }

    openModalAsesor = (event) => {
        let thatContext = this;
        let objectReviewed = this.state.objectReviewed;
        let jsonObjectMapping = {};
        try{
            jsonObjectMapping = JSON.parse(this.state.configuration.objectMapping);
        }catch(e){
            jsonObjectMapping = this.state.configuration.objectMapping;
        }
        let objectMapping = jsonObjectMapping[objectReviewed.assetType];
        if (objectMapping) {
            let display = objectMapping.asesoriaJuridica;
            display += "?objectEntryId=" + objectReviewed.id;
            display += "&objectType=" + objectReviewed.assetType;
            display += "&mode=2";
            display += "&p_p_state=pop_up";
            let url = themeDisplay.getPortalURL() + display;
            let dialog = Liferay.Util.openWindow({
                cache: false,
                dialog: {
                    destroyOnHide: true,
                    modal: true,
                    cssClass:'dialog-with-footer i-mainWrapper',
                    after: {
                        render: function(event) {},
                        destroy: function(event) {
                        }
                    },
                    toolbars: {
                        footer: [
                            {
                                label: 'Avanzar flujo',
                                id: 'avanzar-flujo-button',
                                cssClass: 'btn-link pull-right btn btn-secondary hide',
                                on: {
                                    click: function() {
                                    }
                                },
                            },
                            {
                                label: 'Cerrar',
                                cssClass: 'btn-link pull-right btn btn-secondary',
                                on: {
                                    click: function() {
                                        let dialog = Liferay.Util.Window.getById('asesorDialog_iframe_');
                                        dialog.destroy();
                                    }
                                },
                            }
                        ]
                    }
                },
                dialogIframe: {
                    bodyCssClass: 'dialog-with-footer i-mainWrapper'
                },
                id: 'asesorDialog',
                refreshWindow: window,
                title: 'Consulta de '+objectReviewed.assetType,
                uri: url
            });

            setTimeout(() => {
                iframeURLChange(document.getElementById("asesorDialog_iframe_"), function (newURL) {
                    let dialog = Liferay.Util.Window.getById('asesorDialog_iframe_');
                    thatContext.changeTransition(event);
                    dialog.destroy();
                })
            }, 4000);
            //window.open(url, "_blank")
        }
    }

    render() {

        return (<>
            <div className="dropdown dropdown-action">
                <a
                    aria-expanded="false"
                    aria-haspopup="true"
                    className="component-action dropdown-toggle"
                    data-toggle="dropdown"
                    href="#1"
                    id="dropdownAction1"
                    role="button"
                >
                    <i className="fa-solid fa-ellipsis fa-rotate-90 fa-2xl"></i>
                </a>
                <ul aria-labelledby="dropdownAction1" className="dropdown-menu">
                    {!this.state.completed &&
                        <li><a className="dropdown-item" data-action="assignToMe"
                               onClick={this.assignToMe}>{Liferay.Language.get("es.emasesa.transition.label.assignToMe")}</a>
                        </li>
                    }
                    <li><a className="dropdown-item"
                           onClick={this.openModal}>{Liferay.Language.get("es.emasesa.transition.label.consultar")}</a>
                    </li>
                    {(this.state.assigneePerson && this.state.transitions && this.state.assigneePerson === Number(Liferay.ThemeDisplay.getUserId()) ) &&
                        this.state.transitions.map((transition) => {
                            console.debug(transition);
                            return (
                                <>
                                    <li>
                                        <>
                                        {transition.name !== 'Mandar-a-portafirmas' ? (
                                            <a className="dropdown-item" data-name={transition.name} onClick={this.changeTransition}>
                                                {window.transitionsLabel[transition.label]?window.transitionsLabel[transition.label]:transition.label}
                                            </a>
                                        ) : (
                                            <a className="dropdown-item" data-name={transition.name} onClick={this.openModalAsesor}>
                                                {window.transitionsLabel[transition.label]?window.transitionsLabel[transition.label]:transition.label}
                                            </a>
                                        )}
                                        </>
                                    </li>
                                </>
                            )
                        })
                    }
                </ul>
            </div>
        </>);

    }
}

export default Actions;