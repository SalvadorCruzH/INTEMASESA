import React from 'react';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import TareasApi from '../services/TareasApi';
import {OBJECT_MAPPING,OBJECT_CONSTANTS} from "../js/Constants";

let onlyonce = true;

class Actions extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            assigneePerson:props.tarea.assigneePerson,
            actions: props.tarea.actions,
            tareaid: props.tarea.id,
            transitions: props.tarea.transitions,
            refresh: props.refresh,
            objectReviewed: props.tarea.objectReviewed,
            externalReferenceCode:props.tarea.objectData.externalReferenceCode,
            completed: props.tarea.completed,
            loading: true
        }
        console.log(this);
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
        TareasApi.callAction(action.href,data,this.props.refresh,this.errorHandler);
    }

    changeTransition = (event) => {
     let workflowTaskId = this.state.tareaid;
     let transitionName = event.target.dataset.name;
       let data =  {
          "comment": "",
          "transitionName": "transitionName",
          "workflowTaskId": workflowTaskId
        }
     TareasApi.changeTransition(workflowTaskId,data,this.props.refresh,this.errorHandler);
    }

    errorHandler = (error) => {
        if (onlyonce) {
            Liferay.Util.openToast({
                message: Liferay.Language.get('global.error.config'),
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
        let objectDefinition = OBJECT_MAPPING[objectReviewed.assetType];
        if(objectDefinition){
           let url = themeDisplay.getPortalURL()+OBJECT_CONSTANTS.VIEW_URL.replaceAll("--objectDefinitionId--",objectDefinition.id)
           .replaceAll("--externalReferenceCode--",this.state.externalReferenceCode);
           console.log(url);
            Liferay.Util.openWindow({
                dialog: {
                    destroyOnHide: true,
                    modal: true,
                    after: {
                        render: function(event) {
                            //
                        }
                    }
                },
                id: 'EditInfoIntDialog',
                refreshWindow: window,
                title: 'Consultar',
                uri: url
            });
        }
    }

    render() {

        return(<>
            <div class="dropdown dropdown-action">
            	<a
            		aria-expanded="false"
            		aria-haspopup="true"
            		class="component-action dropdown-toggle"
            		data-toggle="dropdown"
            		href="#1"
            		id="dropdownAction1"
            		role="button"
            	>
            		<i class="fa-solid fa-ellipsis fa-rotate-90 fa-2xl"></i>
            	</a>
            	<ul aria-labelledby="dropdownAction1" class="dropdown-menu">
            	    {!this.state.completed &&
            		    <li><a class="dropdown-item" data-action="assignToMe" onClick={this.callAction}>Asignar a mi</a></li>
            		}
            		<li><a class="dropdown-item"  onClick={this.openModal}>Consultar</a></li>
            		{(this.state.assigneePerson &&  this.state.transitions && this.state.assigneePerson.id == Liferay.ThemeDisplay.getUserId()) &&
                        this.state.transitions.map((transition) => {
                                return(
                                    <>
                                        <li><a class="dropdown-item" data-name={transition.name} onClick={this.changeTransition}>{transition.label}</a></li>
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