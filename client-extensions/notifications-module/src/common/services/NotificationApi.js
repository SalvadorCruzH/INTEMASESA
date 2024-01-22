import * as Constants from "../js/Constants";
import {NOTIFICATION_URL} from "../js/Constants";

const NotificationApi = {
      getNotificationsUnRead: (callback, errorHandler) => {
                LiferayApi.get(
                    Constants.oauthUserAgent.CLIENT_ID,
                    Constants.oauthUserAgent.URL_DEFAULT_NATIVE+NOTIFICATION_URL.GET,
                    callback,
                    errorHandler
                );
            }
}

export default NotificationApi;