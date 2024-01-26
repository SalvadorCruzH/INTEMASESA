import * as Constants from "../js/Constants";
import {NOTIFICATION_URL} from "../js/Constants";

const NotificationApi = {
    getNotificationsUnRead: (filter,start, end, sort,callback, errorHandler) => {
        if(!sort){
            sort = false;
        }

        LiferayApi.get(
            Constants.oauthUserAgent.CLIENT_ID,
            Constants.oauthUserAgent.URL_DEFAULT_NATIVE + NOTIFICATION_URL.GET + filter +"/"+ start + "/" + end+ "/"+sort,
            callback,
            errorHandler
        );
    },

    markAsRed: (userNotificationEventId, callback, errorHandler) => {
        let defUrl = Constants.NOTIFICATION_URL.MARKASRED;
        defUrl = defUrl.replace("NOTIFICACIONID", userNotificationEventId);
        LiferayApi.put(
            Constants.oauthUserAgent.CLIENT_ID,
            Constants.oauthUserAgent.URL_DEFAULT_NATIVE + defUrl,
            callback,
            errorHandler
        );
    },

    count: (callback, errorHandler) => {
        let defUrl = Constants.NOTIFICATION_URL.COUNT;
        LiferayApi.put(
            Constants.oauthUserAgent.CLIENT_ID,
            Constants.oauthUserAgent.URL_DEFAULT_NATIVE + defUrl,
            callback,
            errorHandler
        );
    }
}

export default NotificationApi;