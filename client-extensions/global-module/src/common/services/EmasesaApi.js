import * as Constants from "../js/Constants";
import {CONFIGURATION_BASE} from "../js/Constants";
import LiferayApi from "./LiferayApi";

const EmasesaApi = {

    getConfiguration: (companyId, callback, errorHandler) => {
        LiferayApi.get(Constants.oauthUserAgent.CLIENT_ID,
            CONFIGURATION_BASE.URL_DEFAULT+companyId,
            callback, errorHandler);
    },

    test: () => {
        console.debug('OK')
    }

}
export default EmasesaApi;