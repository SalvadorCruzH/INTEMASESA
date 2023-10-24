const LiferayApi = {
    get: (clientId, url, callback, errorHandler) => {
        callService(clientId, url, 'GET', null, callback, errorHandler);
    },
    put: (clientId, url, data, callback, errorHandler) => {
        callService(clientId, url, 'PUT', data, callback, errorHandler);
    },
    post: (clientId, url, data, callback, errorHandler) => {
        callService(clientId, url, 'POST', data, callback, errorHandler);
    },

    delete: (clientId, url, data, callback, errorHandler) => {
        callService(clientId, url, 'DELETE', data, callback, errorHandler);
    },

    getClient: (clientId) => {
        return getClient(clientId);
    },

    test: () => {
        console.debug('OK')
    }
}

const getClient = (clientId) =>{
    let client;
    for (let userAgent in Liferay.OAuth2._userAgentApplications) {
        if (Liferay.OAuth2._userAgentApplications[userAgent]["clientId"] === clientId) {
            client = userAgent;
        }
    }
    return client;
}

const callService = (clientId, url, method, data, callback, errorHandler) => {
    try {
        let client;
        for (let userAgent in Liferay.OAuth2._userAgentApplications) {
            if (Liferay.OAuth2._userAgentApplications[userAgent]["clientId"] === clientId) {
                client = userAgent;
            }
        }
        if (client) {
            let oAuth2Client = Liferay.OAuth2Client.FromUserAgentApplication(client);
            const config = {
                method: method,
                timeout: 5000,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/json',
                    'accept': 'application/json',
                    'x-csrf-token': Liferay.authToken
                }
            };
            if (data){
                config.body = JSON.stringify(data);
            }
            oAuth2Client?.fetch(url, config)
                .then((response) => {
                    let result = "";
                    try {
                        result = JSON.parse(JSON.stringify(response));
                    } catch (e) {
                        console.error(e)
                    }
                    if (callback) callback(result);
                })
                .catch((error) => {
                    console.error(error);
                    if (errorHandler){
                        errorHandler(error)
                    }
                });
        }
    } catch (error) {
        console.error(error);
    }
}

const callServiceDEL = (clientId, url, method, data, callback) => {
    try {
        const config = {
            method: method,
            timeout: 5000
        };
        fetch(url, config).then((response) => {
            let result = "";
            try {
                console.log(response);
            } catch (e) {
                console.log(e);
            }
            if (callback) callback(result);

        })
        .catch((error) => console.log(error));
    } catch (error) {
        console.log(error);
    }
}

export default LiferayApi;