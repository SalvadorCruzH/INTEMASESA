import * as Constants from "../js/Constants";
import {WORKFLOWTASKS, WORKFLOWTASK_BASE} from "../js/Constants";


const TareasApi = {
    getWorkflowTask: (assetType, completed, byRole, start, end, columnSelected, callback, errorHandler, queryText) => {
        let url = WORKFLOWTASK_BASE.URL_DEFAULT;
        if (assetType !== "") {
            url = url + assetType + "/" + completed + "/" + byRole + "/" + start + "/" + end + "/"+columnSelected.name+ "/"+columnSelected.order+"/" + queryText;

        } else {
            url = url + completed + "/" + byRole + "/" + start + "/" + end+ "/"+columnSelected.name+ "/"+columnSelected.order+ "/" + queryText;
        }

        LiferayApi.get(
            Constants.oauthUserAgent.CLIENT_ID,
            url,
            callback,
            errorHandler
        );

    },

    getWorkflowTasksMe: (scope, callback, errorHandler) => {
        LiferayApi.get(
            Constants.oauthUserAgent.CLIENT_ID,
            WORKFLOWTASKS.URL_GET_ME,
            callback,
            errorHandler
        );
    },

    getWorkflowTasksByUserRole: (scope, callback, errorHandler) => {
        LiferayApi.get(
            Constants.oauthUserAgent.CLIENT_ID,
            WORKFLOWTASKS.URL_GET_USER_ROLES.replace("--userId--", Liferay.ThemeDisplay.getUserId()),
            callback,
            errorHandler
        );
    },

    getObject: (objectId, objectCallUrl, callback, errorHandler) => {
        LiferayApi.get(
            Constants.oauthUserAgent.CLIENT_ID,
            WORKFLOWTASKS.URL_DEFAULT + objectCallUrl + "/" + objectId + "?fields=numeroDeMatricula%2Cnombre%2CprimerApellido%2CsegundoApellido",
            callback,
            errorHandler
        );
    },

    changeTransition: (workflowTaskId, data, callback, errorHandler) => {
        LiferayApi.post(
            Constants.oauthUserAgent.CLIENT_ID,
            WORKFLOWTASKS.URL_POST_CHANGE_TRANSITION.replace("--workflowTaskId--", workflowTaskId),
            data,
            callback,
            errorHandler
        );
    },
    assignToMe: (workflowTaskId, data, callback, errorHandler) => {
        LiferayApi.post(
            Constants.oauthUserAgent.CLIENT_ID,
            WORKFLOWTASKS.URL_POST_ASSIGN_TO_ME.replace("--workflowTaskId--", workflowTaskId),
            data,
            callback,
            errorHandler
        );
    },

    callAction: (actionUrl, data, callback, errorHandler) => {
        let urlAct = new URL(actionUrl);
        LiferayApi.post(
            Constants.oauthUserAgent.CLIENT_ID,
            urlAct.pathname,
            data,
            callback,
            errorHandler
        );
    }

}

export default TareasApi;