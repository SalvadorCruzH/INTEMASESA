import React from 'react'
import TareasApi from '../services/TareasApi'
import Actions from './Actions'
import ClayLoadingIndicator from '@clayui/loading-indicator';
import {OBJECT_MAPPING,oauthUserAgent,WORKFLOWTASKS} from "../js/Constants"

let onlyonce = true;

class TareasModule extends React.Component {
    constructor() {
        super()
        this.state = {
            tareas: [],
            loading: true,
            itemsDropDownLocal: []
        }
        console.log(this);
    }

    componentDidMount() {

        this.loadDependencies();
    }

    loadDependencies = async () => {
    this.state.tareas =[];
     this.setState({loading:true});
                setTimeout(() => {
                    TareasApi.getWorkflowTasksMe(
                        Liferay.ThemeDisplay.getScopeGroupId(),
                        this.setTasks,
                        this.errorHandler
                    )
                    TareasApi.getWorkflowTasksByUserRole(
                        Liferay.ThemeDisplay.getScopeGroupId(),
                        this.setTasks,
                        this.errorHandler
                    )

                }, 100)
            }


    addTareaUser = (tarea) =>{
            let urlObject = OBJECT_MAPPING[tarea.objectReviewed.assetType];
            let objectId = tarea.objectReviewed.id;
            if(urlObject){

                let client = LiferayApi.getClient(oauthUserAgent.CLIENT_ID);
                if (client) {
                    let oAuth2Client = Liferay.OAuth2Client.FromUserAgentApplication(client);
                    let url = urlObject+"/"+objectId+"?fields=numeroDeMatricula%2Cnombre%2CprimerApellido%2CsegundoApellido";

                    const config = {
                        method: 'GET',
                        timeout: 5000,
                        dataType: "json",
                        headers: {
                            'Content-Type': 'application/json',
                            'accept': 'application/json',
                            'x-csrf-token': Liferay.authToken
                        }
                    };
                oAuth2Client?.fetch(url, config)
                    .then((response) => {
                        let result = "";
                        try {
                            result = JSON.parse(JSON.stringify(response));
                            tarea.requestUser = result;
                            this.addTareaTransition(tarea);

                        } catch (e) {
                            console.error(e)
                        }
                    })
                    .catch((error) => {
                        console.error(error);
                    });
                }
            }
    }

    addTareaTransition = (tarea) =>{
                let workflowTaskId = tarea.id;
                    let client = LiferayApi.getClient(oauthUserAgent.CLIENT_ID);
                    if (client) {
                        let oAuth2Client = Liferay.OAuth2Client.FromUserAgentApplication(client);
                        let url = WORKFLOWTASKS.URL_GET_WORKFLOW_TRANSITION.replace("--workflowTaskId--", workflowTaskId);
                        const config = {
                            method: 'GET',
                            timeout: 5000,
                            dataType: "json",
                            headers: {
                                'Content-Type': 'application/json',
                                'accept': 'application/json',
                                'x-csrf-token': Liferay.authToken
                            }
                        };
                        oAuth2Client?.fetch(url, config)
                        .then((response) => {
                            let result = "";
                            try {
                                result = JSON.parse(JSON.stringify(response));
                                if(result.totalCount > 0){

                                     tarea.transitions =  result.items;

                                }

                                this.setState(previousState =>({
                                    tareas: previousState.tareas.concat(tarea)
                                }));

                            } catch (e) {
                                console.error(e)
                            }
                        })
                        .catch((error) => {
                            console.error(error);
                        });
                    }
        }


    setTasks = (result) => {
        if (result && result.items != null && result.items.length > 0) {
                result.items.forEach((tarea) => {

                    this.addTareaUser(tarea);

                });

        }
        this.setState({loading:false});

    }

    getUser = (assigneePerson) => {
        if (assigneePerson) {
            TareasApi.getUser(
                assigneePerson.id,
                this.setUser,
                this.errorHandler
            )
        }
    }

    setUser = (result) => {
        this.setState({
            [result.id]: result
        });
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

    errorHandlerUpdate = (error) => {
        if (onlyonce) {
            Liferay.Util.openToast({
                message: Liferay.Language.get('where.error.save'),
                title: Liferay.Language.get('global.error'),
                toastProps: {
                    autoClose: 5000,
                },
                type: 'danger',
            });
        }
        onlyonce = false;
    }

    setHandlerReturnFooter = (result) => {
        this.setState({
            loading: false
        });
        Liferay.Util.openToast({
            message: Liferay.Language.get('global.ok.default'),
            title: Liferay.Language.get('global.success'),
            toastProps: {
                autoClose: 5000,
            },
            type: 'success',
        });

    }

    handleSubmit = e => {
        e.preventDefault();
        this.setState({
            loading: true
        });

    }

    render() {

        return (
            <div>
                {(this.state.loading) ? (<ClayLoadingIndicator displayType="primary" size="lg"/>):(
                    <>
                        <table id="table-id" class="ema-table last">
                           <caption class="sr-only">Sumario de la tabla</caption>
                           <thead>
                               <tr>
                                   <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Matrícula</th>
                                   <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Nombre</th>
                                   <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Tipo</th>
                                   <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Fecha solicitud</th>
                                   <th scope="col"><i class="fa-solid fa-sort fa-lg"></i>Asignado a:</th>
                                   <th scope="col"><i class="fa-solid fa-ellipsis fa-rotate-90 fa-2xl"></i></th>
                               </tr>
                           </thead>
                                  <tbody>
                                      {this.state.tareas.map((tarea, i) =>{
                                         return( <>
                                          <tr>
                                                <td>{tarea.requestUser.numeroDeMatricula}</td>
                                                <td>{tarea.requestUser.nombre} {tarea.requestUser.primerApellido} {tarea.requestUser.segundoApellido}</td>
                                                <td>{tarea.objectReviewed.assetType}</td>
                                                <td>{tarea.dateCreated}</td>
                                                <td>{tarea.assigneePerson && tarea.assigneePerson.name}</td>
                                                <td><Actions tarea={tarea} refresh={this.loadDependencies} /></td>
                                            </tr>
                                          </>
                                          )
                                      })}
                               </tbody>
                       </table>
                    </>
                 )}
            </div>
        )
    }
}

export default TareasModule
