import React from 'react'
import TareasApi from '../services/TareasApi'
import Actions from './Actions'
import ClayLoadingIndicator from '@clayui/loading-indicator';
import {OBJECT_MAPPING,oauthUserAgent,WORKFLOWTASKS} from "../js/Constants"

let onlyonce = true;

class TareasModule extends React.Component {
    constructor() {
        super();
        let columns = [];
        columns.push({name: "objectData.numeroDeMatricula",label:Liferay.Language.get("objectData.numeroDeMatricula"), order: "asc"});
        columns.push({name: "objectData.name",label:Liferay.Language.get("objectData.name"), order: "asc"});
        columns.push({name: "objectReviewed.assetType",label:Liferay.Language.get("objectReviewed.assetType"), order: "asc"});
        columns.push({name: "dateCreated",label:Liferay.Language.get("dateCreated"), order: "asc"});
        columns.push({name: "assigneePerson.name",label:Liferay.Language.get("assigneePerson.name"), order: "asc"});
        columns.push({name: "objectData.estadoObjeto",label:Liferay.Language.get("objectData.estadoObjeto"), order: "asc"});
        this.state = {
            tareas: [],
            loading: true,
            columns: columns
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

    addTareaExtraData = (tarea) =>{
            let objectMapping = OBJECT_MAPPING[tarea.objectReviewed.assetType];
            if(objectMapping){
                let urlObject = objectMapping.url;
                let objectId = tarea.objectReviewed.id;
                if(urlObject){

                    let client = LiferayApi.getClient(oauthUserAgent.CLIENT_ID);
                    if (client) {
                        let oAuth2Client = Liferay.OAuth2Client.FromUserAgentApplication(client);
                        let url = urlObject+"/"+objectId+"?fields=numeroDeMatricula%2Cnombre%2CprimerApellido%2CsegundoApellido%2CexternalReferenceCode%2CestadoObjeto";

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
                                tarea.objectData = result;
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

                    this.addTareaExtraData(tarea);

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

    orderBy = (column) => {
        let tareas = this.state.tareas;
        let order =column.order;
        let type = column.name;
        console.log(tareas);
        console.log(column);

        column.order = order === "asc" ? "desc" : "asc";

        if(type === "objectData.numeroDeMatricula"){
           tareas =  tareas.sort((a, b) => {
                 let numeroDeMatriculaA = a.objectData.numeroDeMatricula;
                 let numeroDeMatriculaB = b.objectData.numeroDeMatricula;

                 let result = order === "asc" ? numeroDeMatriculaA.localeCompare(numeroDeMatriculaB) : numeroDeMatriculaB.localeCompare(numeroDeMatriculaA);

                 return result;

            })
        }

        if(type === "objectData.name"){
                   tareas =  tareas.sort((a, b) => {
                     let nameUserA = a.objectData.nombre;
                     let nameUserB = b.objectData.nombre;

                     let result = order === "asc" ? nameUserA.localeCompare(nameUserB) : nameUserB.localeCompare(nameUserA);

                     return result;

                    })
                }
        if(type === "objectReviewed.assetType"){
            tareas = tareas.sort((a, b) => {
                let assetTypeA = a.objectReviewed.assetType;
                let assetTypeB =  b.objectReviewed.assetType;

                let result = order === "asc" ? assetTypeA.localeCompare(assetTypeB) : assetTypeB.localeCompare(assetTypeA);
                return result;
            });
        }

        if(type === "dateCreated"){

             tareas =  tareas.sort((a, b) => {
                let dateCreatedA = a.dateCreated ;
                let dateCreatedB =  b.dateCreated ;

                let result = order === "asc" ? dateCreatedA.localeCompare(dateCreatedB) : dateCreatedB.localeCompare(dateCreatedA);
                return result;

            });
        }
        if(type === "assigneePerson.name"){
           tareas =  tareas.sort((a, b) =>{
                let assigneePersonA = a.assigneePerson ? a.assigneePerson.name : "";
                let assigneePersonB = b.assigneePerson ? b.assigneePerson.name : "";

                let result = order === "asc" ? assigneePersonA.localeCompare(assigneePersonB) : assigneePersonB.localeCompare(assigneePersonA);

                return result;
            });
        }
        
        if(type === "objectData.estadoObjeto"){
            tareas =  tareas.sort((a, b) =>{
                 let estadoObjetoA = a.objectData.estadoObjeto.name;
                 let estadoObjetoB = b.objectData.estadoObjeto.name;

                 let result = order === "asc" ? estadoObjetoA.localeCompare(estadoObjetoB) : estadoObjetoB.localeCompare(estadoObjetoA);

                 return result;
             });
         }


        console.log(tareas);
        this.setState({tareas: tareas});
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
                               {this.state.columns.map((column, i) =>{
                                      return(
                                      <>
                                      <th scope="col"><span class="order"  onClick={()=>this.orderBy(column)}><i class="fa-solid fa-sort fa-lg"></i></span>{column.label}</th>
                                      </>)

                               })}
                                <th scope="col"><i class="fa-solid fa-ellipsis fa-rotate-90 fa-2xl"></i></th>


                               </tr>
                           </thead>
                                  <tbody>
                                      {this.state.tareas.map((tarea, i) =>{
                                         return( <>
                                          <tr>
                                                <td>{tarea.objectData.numeroDeMatricula}</td>
                                                <td>{tarea.objectData.nombre} {tarea.objectData.primerApellido} {tarea.objectData.segundoApellido}</td>
                                                <td>{tarea.objectReviewed.assetType}</td>
                                                <td>{tarea.dateCreated}</td>
                                                <td>{tarea.assigneePerson && tarea.assigneePerson.name}</td>
                                                <td>{tarea.objectData.estadoObjeto.name}</td>
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
