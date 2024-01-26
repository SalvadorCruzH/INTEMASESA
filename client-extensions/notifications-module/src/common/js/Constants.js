export const oauthUserAgent = {
    CLIENT_ID: 'id-ab4b5594-7e2e-4bdb-bd47-f9c4c3175111',
    URL_DEFAULT: '/o/c/',
    URL_DEFAULT_NATIVE: '/o'
}

export const NOTIFICATION_URL = {
    GET : '/emasesa/v1.0/intranet/notificaciones/',
    COUNT : '/emasesa/v1.0/intranet/notificaciones/count',
    MARKASRED: '/headless-user-notification/v1.0/user-notifications/NOTIFICACIONID/read'
}

export function getLanguageLocal() {
    return (Liferay.ThemeDisplay.getLanguageId()).replace("_", "-");
}