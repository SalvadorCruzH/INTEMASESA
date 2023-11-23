import * as Constants from "../js/Constants";

const ObjectApi = {
      getObject: (objectId,objectCallUrl, callback, errorHandler) => {
                LiferayApi.get(
                    Constants.oauthUserAgent.CLIENT_ID,
                    objectCallUrl+"/"+objectId,
                    callback,
                    errorHandler
                );
            }
}

export default ObjectApi;