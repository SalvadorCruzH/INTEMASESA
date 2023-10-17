package es.emasesa.intranet.administracion.tareas.portlet.actions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService;
import es.emasesa.intranet.administracion.tareas.constants.AdministracionTareasPortletKeys;
import es.emasesa.intranet.base.util.LoggerUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdministracionTareasPortletKeys.ADMINISTRACIONTAREAS,
                "mvc.command.name=/assignmentTask"
        },
        service = MVCResourceCommand.class
)
public class asignarTareasMVCResourceCommand implements MVCResourceCommand{
    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse){
        long workflowTaskId = ParamUtil.getLong(resourceRequest, "workflowTask");
        WorkflowTask workflowTask = null;
        try {
            workflowTask = _workflowTaskManager.getWorkflowTask(workflowTaskId);
        } catch (WorkflowException e) {
            LoggerUtil.error(LOG, e);
        }
        getAssignWorkflowTaskToUser(resourceRequest, workflowTask);

        return false;
    }

    public WorkflowTask getAssignWorkflowTaskToUser(ResourceRequest resourceRequest, WorkflowTask workflowTask){
        WorkflowTask assignedTask = null;
        long companyId = Long.parseLong((String) workflowTask.getOptionalAttributes().get("companyId"));
        long workflowTaskId = workflowTask.getWorkflowTaskId();
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long assigneeUserId = themeDisplay.getUserId();
        String comment = (String) workflowTask.getOptionalAttributes().get("taskComments");
        Date dueDate = workflowTask.getDueDate();
        Map<String, Serializable> workflowContext = null;
//        Serializable serviceContextSerializable = workflowTask.getOptionalAttributes().get("serviceContext");

        try {
            assignedTask = _workflowTaskManager.assignWorkflowTaskToUser(
                    companyId,
                    assigneeUserId,
                    workflowTaskId,
                    assigneeUserId,
                    comment,
                    dueDate,
                    workflowContext
            );
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
        }

        return assignedTask;
    }

    private final static Log LOG = LoggerUtil.getLog(MVCResourceCommand.class);

    @Reference
    WorkflowTaskManager _workflowTaskManager;

    @Reference
    KaleoTaskAssignmentInstanceLocalService _kaleoTaskAssignmentInstanceLocalService;
    @Reference
    KaleoTaskInstanceTokenLocalService _kaleoTaskInstanceTokenLocalService;
}
