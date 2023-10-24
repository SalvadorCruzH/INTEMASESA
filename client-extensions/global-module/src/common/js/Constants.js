export const oauthUserAgent = {
    CLIENT_ID: 'id-ab7afa54-9e9d-61d6-7916-fe121a45203b',
    URL_DEFAULT: '/o/c',
    URL_DEFAULT_NATIVE: '/o'
}



export function getLanguageLocal() {
    return (Liferay.ThemeDisplay.getLanguageId()).replace("_", "-");
}