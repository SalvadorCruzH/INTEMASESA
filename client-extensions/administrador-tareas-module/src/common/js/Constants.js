export const oauthUserAgent = {
    CLIENT_ID: 'id-ab4b5594-7e2e-4bdb-bd47-f9c4c3175111',
    URL_DEFAULT: '/o/c',
    URL_DEFAULT_NATIVE: '/o'
}

export const WORKFLOWTASKS = {

 URL_GET_USER_ROLES: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/assigned-to-user-roles?assigneeId=--userId--&pageSize=100`,
 URL_GET_ME : `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/assigned-to-me?pageSize=100`,
 URL_GET_USER: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-user/v1.0/user-accounts/--userId--`,
 URL_GET_WORKFLOW_TRANSITION: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/--workflowTaskId--/next-transitions`,
 URL_POST_CHANGE_TRANSITION: `${oauthUserAgent.URL_DEFAULT_NATIVE}/headless-admin-workflow/v1.0/workflow-tasks/--workflowTaskId--/change-transition`,
 URL_DEFAULT: `${oauthUserAgent.URL_DEFAULT}`

}

export const OBJECT_CONSTANTS = {
    VIEW_URL: '/group/guest/~/control_panel/manage?p_p_id=com_liferay_object_web_internal_object_definitions_portlet_ObjectDefinitionsPortlet_--objectDefinitionId--&p_p_lifecycle=0&p_p_state=pop_up&p_p_mode=view&_com_liferay_object_web_internal_object_definitions_portlet_ObjectDefinitionsPortlet_--objectDefinitionId--_objectDefinitionId=--objectDefinitionId--&_com_liferay_object_web_internal_object_definitions_portlet_ObjectDefinitionsPortlet_--objectDefinitionId--_mvcRenderCommandName=%2Fobject_entries%2Fedit_object_entry&_com_liferay_object_web_internal_object_definitions_portlet_ObjectDefinitionsPortlet_--objectDefinitionId--_externalReferenceCode=--externalReferenceCode--'
}

export function getLanguageLocal() {
    return (Liferay.ThemeDisplay.getLanguageId()).replace("_", "-");
}