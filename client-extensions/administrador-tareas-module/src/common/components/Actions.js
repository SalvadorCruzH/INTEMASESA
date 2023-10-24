import React from 'react'
import ClayLoadingIndicator from '@clayui/loading-indicator';
import TareasApi from '../services/TareasApi';

let onlyonce = true;

class Actions extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            actions: props.tarea.actions,
            tareaid: props.tarea.id,
            transitions: props.tarea.transitions,
            refresh: props.refresh,
            loading: true,
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
            		<svg
            			class="lexicon-icon lexicon-icon-ellipsis-v"
            			focusable="false"
            			role="presentation"
            		>
            			<use href="/images/icons/icons.svg#ellipsis-v"></use>
            		</svg>
            	</a>
            	<ul aria-labelledby="dropdownAction1" class="dropdown-menu">
            		<li><a class="dropdown-item" data-action="assignToMe" onClick={this.callAction}>Asignar a mi</a></li>
            		{this.state.transitions.map((transition) => {
            		        return(
                                <>
                                    <li><a class="dropdown-item" data-name={transition.name} >{transition.label}</a></li>
                                </>
            		        )

            		})}

            	</ul>
            </div>


        </>);

    }
}

export default Actions;