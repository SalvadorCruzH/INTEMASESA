export const oauthUserAgent = {
    CLIENT_ID: 'id-ab7afa54-9e9d-61d6-7916-fe121a45203b',
    URL_DEFAULT: '/o/c',
    URL_DEFAULT_NATIVE: '/o'
}

export const WORKFLOWTASKS = {

 URL_GET_USER_ROLES: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/assigned-to-user-roles?assigneeId=--userId--`,
 URL_GET_ME : `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/assigned-to-me`,
 URL_GET_USER: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-user/v1.0/user-accounts/--userId--`,
 URL_GET_WORKFLOW_TRANSITION: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/--workflowTaskId--/next-transitions`,
 URL_POST_CHANGE_TRANSITION: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/--workflowTaskId--/change-transition`,
 URL_DEFAULT: `${oauthUserAgent.URL_DEFAULT}`

}

export const OBJECT_MAPPING = {
    Compatibilidad : `${oauthUserAgent.URL_DEFAULT}/compatibilidads`

}

export function getLanguageLocal() {
    return (Liferay.ThemeDisplay.getLanguageId()).replace("_", "-");
}