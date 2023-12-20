import React from 'react'
import TareasApi from '../services/TareasApi'
import Actions from './Actions.js'
import ClayLoadingIndicator from '@clayui/loading-indicator';
import {OBJECT_MAPPING, oauthUserAgent, WORKFLOWTASKS} from "../js/Constants"

let onlyonce = true;

class TareasModule extends React.Component {

    constructor() {
        super();
        let columns = [];
        columns.push({
            name: "objectData.numeroDeMatricula",
            label: Liferay.Language.get("objectData.numeroDeMatricula"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "objectData.name",
            label: Liferay.Language.get("objectData.name"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "attributes.entryType",
            label: Liferay.Language.get("objectReviewed.assetType"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "dateCreated",
            label: Liferay.Language.get("dateCreated"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "assigneePersonName",
            label: Liferay.Language.get("assigneePerson.name"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "objectData.estadoObjeto",
            label: Liferay.Language.get("objectData.estadoObjeto"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });

        columns.push({
            name: "actions",
            label: "",
            order: "asc",
            icon: "fa-solid fa-ellipsis fa-rotate-90 fa-2xl"
        });

        this.state = {
            tareas: [],
            loading: true,
            columns: columns,
            view: 1,
            configuration: {},
            start:0,
            end:10,
            delta:10,
            showCompleted:false
        }
        console.debug(this);
    }

    componentDidMount() {

        this.loadDependencies();
    }

    loadDependencies = async () => {
        this.state.tareas = [];
        this.setState({loading: true});
        setTimeout(() => {
            EmasesaApi.getConfiguration(this.loadConfiguration, this.errorHandler)

            this.getTasksUser();

        }, 100)
    }

    getAssignedToMe = () => {

        this.setState({
           view: 1
        });
        setTimeout(() => {
             TareasApi.getWorkflowTask("",this.state.showCompleted,false,this.state.start,this.state.end,this.setTasks, this.errorHandler);
        }, 100)

    }

    getAssignedToUserRol = () => {

        this.setState({
          view: 2
        });
        setTimeout(() => {
               TareasApi.getWorkflowTask("",this.state.showCompleted,true,this.state.start,this.state.end,this.setTasks, this.errorHandler);
         }, 100)


    }

    getTasksUser = () => {
        if (this.state.view == 1) {
            this.getAssignedToMe();
        } else {
            this.getAssignedToUserRol();
        }
    }

    loadConfiguration = (result) => {
        if (result) {
            console.debug("configurationData");
            console.debug(result);
            this.setState({
                configuration: result
            });
        }
    }

    showMore = () =>{

        this.getTasksUser();
    }

    addTareaExtraData = (tarea) => {
        var jsonObjectMapping = {};
        try{
            jsonObjectMapping = JSON.parse(this.state.configuration.objectMapping);
        }catch(e){
            jsonObjectMapping = this.state.configuration.objectMapping;
        }
        console.debug(jsonObjectMapping);
        let objectMapping = jsonObjectMapping[tarea.attributes.entryType];
        if (objectMapping) {
            let urlObject = objectMapping.url;
            let objectId = tarea.attributes.entryClassPK;
            if (urlObject) {

                let client = LiferayApi.getClient(oauthUserAgent.CLIENT_ID);
                if (client) {
                    let oAuth2Client = Liferay.OAuth2Client.FromUserAgentApplication(client);
                    let url = oauthUserAgent.URL_DEFAULT + "/" + urlObject + "/" + objectId + "?fields=numeroDeMatricula%2Cnombre%2CprimerApellido%2CsegundoApellido%2CexternalReferenceCode%2CestadoObjeto";

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
                                console.debug(result);
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

    addTareaTransition = (tarea) => {
        let workflowTaskId = tarea.workflowTaskId;
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
                        console.debug(result);
                        if (result.totalCount > 0) {

                            tarea.transitions = result.items;

                        }
                        this.setState(previousState => ({
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
        let delta = this.state.delta;
        let start = this.state.start;
        let end = this.state.end;

        this.setState({
            tareas: []
        });
        if (result && result != null && result.length > 0) {
            result.forEach((tarea) => {
                this.addTareaExtraData(tarea);
            });
            this.setState({start:start+delta,end:end+delta});
        }

        this.setState({loading: false});

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
        let order = column.order;
        let type = column.name;
        console.debug(tareas);
        console.debug(column);

        column.order = order === "asc" ? "desc" : "asc";

        if (type === "objectData.numeroDeMatricula") {
            tareas = tareas.sort((a, b) => {
                let numeroDeMatriculaA = a.objectData.numeroDeMatricula;
                let numeroDeMatriculaB = b.objectData.numeroDeMatricula;

                let result = order === "asc" ? numeroDeMatriculaA.localeCompare(numeroDeMatriculaB) : numeroDeMatriculaB.localeCompare(numeroDeMatriculaA);

                return result;

            })
        }

        if (type === "objectData.name") {
            tareas = tareas.sort((a, b) => {
                let nameUserA = a.objectData.nombre;
                let nameUserB = b.objectData.nombre;

                let result = order === "asc" ? nameUserA.localeCompare(nameUserB) : nameUserB.localeCompare(nameUserA);

                return result;

            })
        }

        if (type === "attributes.entryType") {
            tareas = tareas.sort((a, b) => {
                let assetTypeA = a.attributes.entryType;
                let assetTypeB = b.attributes.entryType;

                let result = order === "asc" ? assetTypeA.localeCompare(assetTypeB) : assetTypeB.localeCompare(assetTypeA);
                return result;
            });
        }

        if (type === "dateCreated") {

            tareas = tareas.sort((a, b) => {
                let dateCreatedA = a.dateCreated;
                let dateCreatedB = b.dateCreated;

                let result = order === "asc" ? dateCreatedA.localeCompare(dateCreatedB) : dateCreatedB.localeCompare(dateCreatedA);
                return result;

            });
        }

        if (type === "assigneePersonName") {
            tareas = tareas.sort((a, b) => {
                let assigneePersonA = a.assigneePersonName ? a.assigneePersonName : "";
                let assigneePersonB = b.assigneePersonName ? b.assigneePersonName : "";

                let result = order === "asc" ? assigneePersonA.localeCompare(assigneePersonB) : assigneePersonB.localeCompare(assigneePersonA);

                return result;
            });
        }

        if (type === "objectData.estadoObjeto") {
            tareas = tareas.sort((a, b) => {
                let estadoObjetoA = a.objectData.estadoObjeto.name;
                let estadoObjetoB = b.objectData.estadoObjeto.name;

                let result = order === "asc" ? estadoObjetoA.localeCompare(estadoObjetoB) : estadoObjetoB.localeCompare(estadoObjetoA);

                return result;
            });
        }

        console.debug(tareas);
        this.setState({tareas: tareas});
    }

    showCompletedTask = () =>{
        this.setState({showCompleted:!this.state.showCompleted});

        this.getTasksUser();

    }

    render() {

        return (
            <div>
                {(this.state.loading) ? (<ClayLoadingIndicator displayType="primary" size="lg"/>) : (<>

                        <div className="button-holder btn-wrapper btn-wrapper--inline">
                            <a href id="toMe" className="btn btn-primary" aria-label="Asignadas a mi"
                                onClick={this.getAssignedToMe} aria-selected={this.state.view == 1}>{Liferay.Language.get('admin.task.assign.toMe')}</a>
                            <a href id="toRole" className="btn btn-primary" aria-label="Asignadas a mi rol"
                                onClick={this.getAssignedToUserRol} aria-selected={this.state.view == 2}>{Liferay.Language.get('admin.task.assign.toRol')}</a>
                        </div>

                        <div className="filter-wrapper">

                            <label for="showCompleted">
                                <input type="checkbox" id="showCompleted" name="showCompleted" value="showCompleted"
                                onClick={this.showCompletedTask}  aria-selected={this.state.showCompleted} />
                                <span>{Liferay.Language.get("show.completed.tasks")}</span>
                            </label>
                        </div>

                        <div className="ema-table-wrapper">
                            <table id="table-id" className="ema-table last">
                                <caption className="sr-only">{Liferay.Language.get('admin.task.summary')}</caption>
                                <thead>
                                <tr>
                                    {this.state.columns.map((column, i) => {
                                        return (
                                            <>
                                                <th scope="col"><span className="order" onClick={() => this.orderBy(column)}><i
                                                    className={column.icon}></i></span>{column.label}</th>
                                            </>)
                                    })}
                                </tr>
                                </thead>
                                {this.state.tareas.length != 0 ? (<>
                                        <tbody>
                                        {this.state.tareas.map((tarea, i) => {
                                            console.debug('Tarea'+i)
                                            console.debug(tarea);
                                            return (<>
                                                    <tr data-objectId={tarea.attributes.entryClassPK} data-workflowTaskId={tarea.workflowTaskId}>
                                                        <td>{tarea.objectData.numeroDeMatricula}</td>
                                                        <td>{tarea.objectData.nombre} {tarea.objectData.primerApellido} {tarea.objectData.segundoApellido}</td>
                                                        <td>{tarea.attributes.entryType}</td>
                                                        <td>{window.timestampToDdMmYyyy(tarea.createDate)}</td>
                                                        <td>{tarea.assigneePersonName && tarea.assigneePersonName}</td>
                                                        <td>{tarea.objectData.estadoObjeto.name}</td>
                                                        <td><Actions tarea={tarea} configuration={this.state.configuration}
                                                                     refresh={this.loadDependencies}/></td>
                                                    </tr>
                                                </>
                                            )
                                        })}
                                        </tbody>
                                    </>
                                ) : (<div className="alert alert-info" role="alert">
                                    <tbody>
                                        <tr data-objectId="{tarea.id}">
                                            <td colSpan={7} className="empty"><div className="alert alert-info">{Liferay.Language.get('admin.task.no.task')}</div></td>
                                        </tr>
                                    </tbody>
                                </div>)}
                            </table>
                            <div class="button-holder">
                                <a href id="more" class="btn btn-primary" aria-label="Mostrar más" aria-disabled="true" onClick={this.showMore}>Mostrar más</a>
                            </div>
                        </div>
                    </>
                )}
            </div>
        )
    }
}

export default TareasModule
