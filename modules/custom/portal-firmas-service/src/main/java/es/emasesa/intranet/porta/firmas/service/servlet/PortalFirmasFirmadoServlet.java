package es.emasesa.intranet.porta.firmas.service.servlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;

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

import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.LoggerUtil;

@Component(
        property = {
                "osgi.http.whiteboard.context.path=/",
                "osgi.http.whiteboard.servlet.pattern=/portalfirmas/firmado/*"
        },
        service = Servlet.class
)
public class PortalFirmasFirmadoServlet extends HttpServlet {
    private static final Log LOG = LogFactoryUtil.getLog(PortalFirmasFirmadoServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = ParamUtil.getString(req, "status");
        long workflowTaskId = ParamUtil.getLong(req, EmasesaConstants.WORKFLOW_TASK_ID);
        long companyId = CompanyThreadLocal.getCompanyId();
        LoggerUtil.debug(LOG,"CALLBACK PORTALFIRMAS");
        LoggerUtil.debug(LOG,"workflowTaskId: "+workflowTaskId+" con estado: "+status);
        try {
            WorkflowTask workflowTask = _workflowTaskManager.getWorkflowTask(
                    workflowTaskId);

            if(workflowTask.isCompleted()){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                ServletResponseUtil.write(resp,"Tarea ya completada");
            }

            Map<String, Serializable> workflowContext = getWorkFlowContext(workflowTask,companyId);
            List<String> transitions = getNextTransitions(workflowTask);
            String nextTransition;
            LoggerUtil.debug(LOG,"transitions size: "+transitions.size());

            if(LOG.isDebugEnabled()){

                transitions.forEach(transition -> LoggerUtil.debug(LOG,"transition: "+transition));
            }

            nextTransition = transitions.stream().filter(transition -> transition.equalsIgnoreCase(status)).findFirst()
                    .orElseThrow(() -> new WorkflowException("Transition not found"));
            LoggerUtil.debug(LOG,"nextTransition: "+nextTransition);

            _workflowTaskManager.completeWorkflowTask(companyId, workflowTask.getAssigneeUserId(),
                    workflowTask.getWorkflowTaskId(), nextTransition, "", workflowContext);
        } catch (WorkflowException e) {

            LoggerUtil.error(LOG,e.getMessage());
            LoggerUtil.debug(LOG,e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ServletResponseUtil.write(resp,e.getMessage());
        } catch (PortalException e) {
            LoggerUtil.error(LOG,e.getMessage());
            LoggerUtil.debug(LOG,e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ServletResponseUtil.write(resp,e.getMessage());
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

}
