export const oauthUserAgent = {
    CLIENT_ID: 'id-ab4b5594-7e2e-4bdb-bd47-f9c4c3175111',
    URL_DEFAULT: '/o/c',
    URL_DEFAULT_NATIVE: '/o'
}

export const CONFIGURATION_BASE = {
    URL_DEFAULT: '/o/emasesa/v1.0/intranet/configuration/',
}

export const OBJECT_MAPPING = {
    Compatibilidad : { url: `${oauthUserAgent.URL_DEFAULT}/compatibilidads`,id:39213},
    Excedencia : { url: `${oauthUserAgent.URL_DEFAULT}/excedenciases`,id:35707},
    Flexibilidad : { url: `${oauthUserAgent.URL_DEFAULT}/flexibilidads`,id:38910},
    Licencias : { url: `${oauthUserAgent.URL_DEFAULT}/licenciases`,id:34848},
    Reduccion : { url: `${oauthUserAgent.URL_DEFAULT}/reduccions`,id:36560},
    Turnos : { url: `${oauthUserAgent.URL_DEFAULT}/turnoses`,id:34910},
}


export function getLanguageLocal() {
    return (Liferay.ThemeDisplay.getLanguageId()).replace("_", "-");
}