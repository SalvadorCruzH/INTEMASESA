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
            name: "matricula_String",
            label: Liferay.Language.get("objectData.numeroDeMatricula"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "fullName_String",
            label: Liferay.Language.get("objectData.name"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "entryType_String",
            label: Liferay.Language.get("objectReviewed.assetType"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "createDate_Number",
            label: Liferay.Language.get("dateCreated"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "assigneePersonName_String",
            label: Liferay.Language.get("assigneePerson.name"),
            order: "asc",
            icon: "fa-solid fa-sort fa-lg"
        });
        columns.push({
            name: "estadoObjeto_String",
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
            start: 0,
            end: 10,
            delta: 10,
            showCompleted: false,
            typeList: "",
            hasMore: true,
            columnSelected :
                {
                    name: "createDate_Number",
                    label: Liferay.Language.get("dateCreated"),
                    order: "asc",
                    icon: "fa-solid fa-sort fa-lg"
                }

        }
        console.debug(this);
    }

    componentDidMount() {
        this.setState({loading: true});
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

    getAssignedToMeButton = (event) => {
        console.debug("getAssignedToMeButton start");
        this.setState({loading: true});
        this.setState({tareas:[],start: 0, end: 10, typeList: "toMe"});
        this.getAssignedToMe();

        console.debug("getAssignedToMeButton end");
    }

    getAssignedToMe = () => {
        console.debug("getAssignedToMe start");
        
        this.setState({
            view: 1
        });

        console.log(this.state.columnSelected);
        let queryText = document.getElementById("queryText").value;
        setTimeout(() => {
            TareasApi.getWorkflowTask("", this.state.showCompleted, false, this.state.start, this.state.end, this.state.columnSelected, this.setTasks, this.errorHandler, queryText);
        }, 100)
        console.debug("getAssignedToMe end");

    }

    getAssignedToUserRolButton = (event) => {
        console.debug("getAssignedToUserRolButton start");
        this.setState({loading: true});
        this.setState({tareas:[],start: 0, end: 10, typeList: "toUserRol"});
        this.getAssignedToUserRol();
        console.debug("getAssignedToUserRolButton end");
    }

    getAssignedToUserRol = () => {
        console.debug("getAssignedToUserRol start");
        this.setState({
            view: 2
        });
        this.setState({loading: true});
        this.setState({start: 0, end: 10});
        let queryText = document.getElementById("queryText").value;
        setTimeout(() => {
            TareasApi.getWorkflowTask("", this.state.showCompleted, true, this.state.start, this.state.end, this.state.columnSelected, this.setTasks, this.errorHandler, queryText);
        }, 100)
        console.debug("getAssignedToUserRol end");
    }

    getTasksUser = () => {
        if (this.state.view === 1) {
            this.getAssignedToMe();
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

    showMore = () => {
        this.getTasksUser();
    }

    addTareaExtraData = (tarea) => {
        var jsonObjectMapping = {};
        try {
            jsonObjectMapping = JSON.parse(this.state.configuration.objectMapping);
        } catch (e) {
            jsonObjectMapping = this.state.configuration.objectMapping;
        }
        let objectMapping = jsonObjectMapping[tarea.entryType];
        if (objectMapping) {
            let urlObject = objectMapping.url;
            let objectId = tarea.entryClassPK;
            if (urlObject) {
                let client = LiferayApi.getClient(oauthUserAgent.CLIENT_ID);
                if (client) {
                    let oAuth2Client = Liferay.OAuth2Client.FromUserAgentApplication(client);
                    let url = oauthUserAgent.URL_DEFAULT + "/" + urlObject + "/" + objectId + "?fields=numeroDeMatricula%2Cnombre%2CprimerApellido%2CsegundoApellido%2CexternalReferenceCode%2CestadoObjeto";
                    console.log(url);
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
                                console.log("addTareaExtraData", tarea.objectData);
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

        if (result && result.tasks && result.tasks.length > 0) {
            result.tasks.forEach((tarea, index, result) => {
                this.addTareaExtraData(tarea);
                if (index === 0) {
                    this.setState({loading: false});
                }
            });
            this.setState({start: start + delta, end: end + delta});
            this.setState({hasMore: result.hasMore});
        } else {
            this.setState({loading: false});
        }
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


    setLoading = (loadingVar) => {
        this.setState({loading: loadingVar});
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

        console.debug(column);
        this.state.columns.forEach(function (col){
            if(col.name === column.name){
                col.order = (column.order === "asc")?'desc':'asc';
            }
        });
        this.setState({columnSelected: column});
        if(this.state.typeList === '' || this.state.typeList === 'toMe'){
            this.getAssignedToMeButton()
        }else{
            this.getAssignedToUserRolButton();
        }
/*

        let tareas = this.state.tareas;


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
                let assetTypeA = a.entryType;
                let assetTypeB = b.entryType;

                let result = order === "asc" ? assetTypeA.localeCompare(assetTypeB) : assetTypeB.localeCompare(assetTypeA);
                return result;
            });
        }

        if (type === "createDate") {

            tareas = tareas.sort((a, b) => {
                let createDateA = a.createDate;
                let createDateB = b.createDate;

                let result = order === "asc" ? createDateA.localeCompare(createDateB) : createDateB.localeCompare(createDateA);
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
        this.setState({tareas: tareas});*/
    }

    showCompletedTask = () => {
        this.setState({showCompleted: !this.state.showCompleted});
        this.setState({tareas:[],start: 0, end: 10, typeList: "toMe"});
        this.getTasksUser();
    }

    filtrarTareas = (event) => {
        console.debug("filtrarTareas start");
        if (this.state.typeList === 'toMe') {
            this.getAssignedToMeButton();
        } else if (this.state.typeList === 'toUserRol') {
            this.getAssignedToUserRolButton();
        } else {
            this.getAssignedToMeButton();
        }
        console.debug("filtrarTareas middle");
    }
    render() {

        return (
            <div>
                <input type="text" id="queryText" name="search" placeholder="Buscar tarea" aria-label="Buscar tarea"></input>
                <button id="searchButton" aria-label="Buscar" onClick={this.filtrarTareas}>Buscar</button>

                <div className="button-holder btn-wrapper btn-wrapper--inline">
                    <a href id="toMe" className="btn btn-primary" aria-label="Asignadas a mi"
                       onClick={this.getAssignedToMeButton}
                       aria-selected={this.state.view === 1}>{Liferay.Language.get('admin.task.assign.toMe')}</a>
                    <a href id="toRole" className="btn btn-primary" aria-label="Asignadas a mi rol"
                       onClick={this.getAssignedToUserRolButton}
                       aria-selected={this.state.view === 2}>{Liferay.Language.get('admin.task.assign.toRol')}</a>
                </div>
                {this.state.typeList !== 'toUserRol' && (
                    <div className="filter-wrapper">
                        <label for="showCompleted">
                            <input type="checkbox" id="showCompleted" name="showCompleted" value="showCompleted"
                                   onClick={this.showCompletedTask} aria-selected={this.state.showCompleted}/>
                            <span>{Liferay.Language.get("show.completed.tasks")}</span>
                        </label>
                    </div>
                )}
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
                        {(this.state.loading) ? (<ClayLoadingIndicator displayType="primary" size="lg"/>) : (<>
                            {this.state.tareas.length != 0 ? (<>
                                    <tbody>
                                    {[...new Set(this.state.tareas.map(tarea => tarea.entryClassPK))].map((uniqueEntryClassPK, i) => {
                                        const tarea = this.state.tareas.find(t => t.entryClassPK === uniqueEntryClassPK);
                                        console.debug('Tarea --> ' + i)
                                        console.debug(tarea);
                                        return (<>
                                                <tr data-objectId={tarea.entryClassPK}
                                                    data-workflowTaskId={tarea.workflowTaskId}>
                                                    <td>{tarea.matricula}</td>
                                                    <td>{tarea.fullName}</td>
                                                    <td>{tarea.entryType}</td>
                                                    <td>{window.timestampToDdMmYyyy(tarea.createDate)}</td>
                                                    <td>{tarea.assigneePersonName && tarea.assigneePersonName}</td>
                                                    <td>{tarea.objectData.estadoObjeto.name}</td>
                                                    <td><Actions tarea={tarea} configuration={this.state.configuration}
                                                                 refresh={this.loadDependencies}
                                                                 loading={this.setLoading}/></td>

                                                </tr>
                                            </>
                                        )
                                    })}
                                    </tbody>
                                </>
                            ) : (
                                <tbody>
                                <tr>
                                    <td colSpan={7} className="empty">
                                        <div
                                            className="alert alert-info">{Liferay.Language.get('admin.task.no.task')}</div>
                                    </td>
                                </tr>
                                </tbody>
                            )}
                        </>)}
                    </table>
                    {this.state.hasMore &&
                        <>
                            <div className="button-holder">
                                <a href id="more" className="btn btn-primary" aria-label="Mostrar más" aria-disabled="true"
                                   onClick={this.showMore}>{Liferay.Language.get('task.show.more')}</a>
                            </div>
                        </>
                    }

                </div>
                {this.state.tareas.length ? (
                    <div className="recuento-tareas">
                        <span>Se han encontrado {this.state.tareas.length} tareas</span>
                    </div>
                ) : (
                    <div className="recuento-tareas">
                        <span>No se han encontrado tareas</span>
                    </div>
                )}
            </div>
        )
    }
}

export default TareasModule
