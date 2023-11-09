package es.emasesa.intranet.porta.firmas.service.servlet;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.CustomWorkflowUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.porta.firmas.service.impl.PFirmasModifyServicesImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        property = {
                "osgi.http.whiteboard.context.path=/",
                "osgi.http.whiteboard.servlet.pattern=/portalfirmas/firmado/*"
        },
        service = Servlet.class
)
public class PortalFirmasFirmadoServlet extends HttpServlet {
    private static final Log LOG = LogFactoryUtil.getLog(PFirmasModifyServicesImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = ParamUtil.getString(req, "status");
        long workflowTaskId = ParamUtil.getLong(req, EmasesaConstants.WORKFLOW_TASK_ID);
        long companyId = CompanyThreadLocal.getCompanyId();
        try {
            WorkflowTask workflowTask = _workflowTaskManager.getWorkflowTask(
                    workflowTaskId);
            Map<String, Serializable> workflowContext = getWorkFlowContext(workflowTask,companyId);
            List<String> transitions = getNextTransitions(workflowTask);
            String nextTransition;

            nextTransition = transitions.stream().filter(transition -> transition.equalsIgnoreCase(status)).findFirst()
                    .orElseThrow(() -> new WorkflowException("Transition not found"));

            _workflowTaskManager.completeWorkflowTask(companyId, workflowTask.getAssigneeUserId(),
                    workflowTask.getWorkflowTaskId(), nextTransition, "", workflowContext);
        } catch (WorkflowException e) {
            LoggerUtil.error(LOG,e.getMessage());
            LoggerUtil.debug(LOG,e);
        } catch (PortalException e) {
            LoggerUtil.error(LOG,e.getMessage());
            LoggerUtil.debug(LOG,e);
        }

        super.doGet(req, resp);
    }

    private List<String> getNextTransitions(WorkflowTask workflowTask) throws WorkflowException {


        return _workflowTaskManager.getNextTransitionNames(workflowTask.getAssigneeUserId(),workflowTask.getWorkflowTaskId());
    }

    public Map<String, Serializable> getWorkFlowContext(WorkflowTask workflowTask, long companyId) throws WorkflowException {

        WorkflowInstance workflowInstance =
                WorkflowInstanceManagerUtil.getWorkflowInstance(
                        companyId, workflowTask.getWorkflowInstanceId());

        return workflowInstance.getWorkflowContext();

    }

    @Reference
    WorkflowTaskManager _workflowTaskManager;
    @Reference
    CustomWorkflowUtil _customWorkflowUtil;

}
