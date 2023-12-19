import * as Constants from "../js/Constants";
import {WORKFLOWTASKS} from "../js/Constants";


const TareasApi = {
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

        getObject: (objectId,objectCallUrl, callback, errorHandler) => {
            LiferayApi.get(
                Constants.oauthUserAgent.CLIENT_ID,
                WORKFLOWTASKS.URL_DEFAULT+objectCallUrl+"/"+objectId+"?fields=numeroDeMatricula%2Cnombre%2CprimerApellido%2CsegundoApellido",
                callback,
                errorHandler
            );
        },

        changeTransition: (workflowTaskId,data,callback, errorHandler) => {
             LiferayApi.post(
                Constants.oauthUserAgent.CLIENT_ID,
                WORKFLOWTASKS.URL_POST_CHANGE_TRANSITION.replace("--workflowTaskId--", workflowTaskId),
                data,
                callback,
                errorHandler
            );
        },

        callAction: (actionUrl, data,callback, errorHandler) => {
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