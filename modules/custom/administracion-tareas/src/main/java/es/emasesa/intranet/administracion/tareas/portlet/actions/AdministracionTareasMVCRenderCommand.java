package es.emasesa.intranet.administracion.tareas.portlet.actions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import es.emasesa.intranet.administracion.tareas.constants.AdministracionTareasPortletKeys;
import es.emasesa.intranet.base.util.LoggerUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdministracionTareasPortletKeys.ADMINISTRACIONTAREAS,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class

)
public class AdministracionTareasMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        PortletSession session = renderRequest.getPortletSession();
        List<WorkflowTask> listWorkFlowTaskRole = new ArrayList<>();
        List<WorkflowTask> listWorkFlowTaskUser = new ArrayList<>();
        Set<String> listSelectorOptions = new HashSet<>();
        try {
            listWorkFlowTaskRole = getWorkflowTasksAssignedRole(renderRequest);
            listWorkFlowTaskUser = getWorkflowTasksAssignedUser(renderRequest);
            listSelectorOptions = getFillSelectorOptions(listWorkFlowTaskRole, listWorkFlowTaskUser);
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
        }

        session.setAttribute("listWorkFlowTaskRole", listWorkFlowTaskRole, PortletSession.APPLICATION_SCOPE);
        session.setAttribute("listWorkFlowTaskUser", listWorkFlowTaskUser, PortletSession.APPLICATION_SCOPE);
        renderRequest.setAttribute("listSelectorOptions", listSelectorOptions);
        renderRequest.setAttribute("listWorkFlowTaskRole", listWorkFlowTaskRole);
        renderRequest.setAttribute("listWorkFlowTaskUser", listWorkFlowTaskUser);
        return "/view.jsp";
    }

    public List<WorkflowTask> getWorkflowTasksAssignedRole(RenderRequest renderRequest) throws PortalException {
        long companyId = PortalUtil.getCompanyId(renderRequest);
        long roleId = _roleLocalService.getRole(companyId, AdministracionTareasPortletKeys.ROLADMINRRHH).getRoleId();
        List<WorkflowTask> listWorkFlowTaskRole = _workflowTaskManager.getWorkflowTasksByRole(companyId, roleId, false, -1, -1, null);

        return listWorkFlowTaskRole;
    }

    public List<WorkflowTask> getWorkflowTasksAssignedUser(RenderRequest renderRequest) throws PortalException {
        long companyId = PortalUtil.getCompanyId(renderRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long userIdLoguer = themeDisplay.getUserId();
        List<WorkflowTask> listWorkFlowTaskUser = _workflowTaskManager.getWorkflowTasksByUser(companyId, userIdLoguer, false, -1, -1, null);

        return listWorkFlowTaskUser;
    }

    public Set<String> getFillSelectorOptions(List<WorkflowTask> listWorkFlowTaskRole, List<WorkflowTask> listWorkFlowTaskUser) {

        Set<String> selectOptions = new HashSet<>();

        for (WorkflowTask optionSelector : listWorkFlowTaskRole){
            selectOptions.add((String) optionSelector.getOptionalAttributes().get("entryType"));
        }
        for (WorkflowTask optionSelector : listWorkFlowTaskUser){
            selectOptions.add((String) optionSelector.getOptionalAttributes().get("entryType"));
        }

        return selectOptions;
    }


    @Reference
    private RoleLocalService _roleLocalService;

    @Reference
    private WorkflowTaskManager _workflowTaskManager;

    private static final Log LOG = LogFactoryUtil.getLog(MVCRenderCommand.class);
}
