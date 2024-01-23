import * as Constants from "../js/Constants";
import {NOTIFICATION_URL} from "../js/Constants";

const NotificationApi = {
      getNotificationsUnRead: (start, end, callback, errorHandler) => {
                LiferayApi.get(
                    Constants.oauthUserAgent.CLIENT_ID,
                    Constants.oauthUserAgent.URL_DEFAULT_NATIVE+NOTIFICATION_URL.GET+start+"/"+end,
                    callback,
                    errorHandler
                );
            },

    markAsRed: (userNotificationEventId, callback, errorHandler) => {
        let defUrl = Constants.NOTIFICATION_URL.MARKASRED;
        defUrl = defUrl.replace("NOTIFICACIONID", userNotificationEventId);
          LiferayApi.put(
            Constants.oauthUserAgent.CLIENT_ID,
            Constants.oauthUserAgent.URL_DEFAULT_NATIVE+defUrl,
            callback,
            errorHandler
        );
    }
}

export default NotificationApi;