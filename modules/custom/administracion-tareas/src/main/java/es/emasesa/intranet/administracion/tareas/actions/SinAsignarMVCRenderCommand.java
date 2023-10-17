package es.emasesa.intranet.administracion.tareas.actions;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import es.emasesa.intranet.administracion.tareas.constants.AdministracionTareasPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdministracionTareasPortletKeys.ADMINISTRACIONTAREAS,
                "mvc.command.name=/administrarTareasSinAsignar"
        },
        service = MVCRenderCommand.class

)
public class SinAsignarMVCRenderCommand implements MVCRenderCommand {
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        PortletSession session = renderRequest.getPortletSession();
        String select = ParamUtil.getString(renderRequest, "sel_solicitud");
        List<WorkflowTask> listAllWorkflowTask = new ArrayList<>();

        List<WorkflowTask> listWorkFlowTaskRole = (List<WorkflowTask>) session.getAttribute("listWorkFlowTaskRole", PortletSession.APPLICATION_SCOPE);
        List<WorkflowTask> listWorkFlowTaskUser = (List<WorkflowTask>) session.getAttribute("listWorkFlowTaskUser", PortletSession.APPLICATION_SCOPE);
        try {
            listAllWorkflowTask = getAllTask(listWorkFlowTaskRole, listWorkFlowTaskUser);
        } catch (PortalException e) {
            LOG.error("Se produjo un error a la hora de obtener las tareas", e);
        }

        renderRequest.setAttribute("select", select);
        renderRequest.setAttribute("listAllWorkflowTask", listAllWorkflowTask);

        return "/tareasSinAsignar.jsp";
    }

    public List<WorkflowTask> getAllTask(List<WorkflowTask> listWorkFlowTaskRole, List<WorkflowTask> listWorkFlowTaskUser) throws PortalException {
        List<WorkflowTask> listAllWorkflowTask = new ArrayList<>();
        listAllWorkflowTask.addAll(listWorkFlowTaskRole);
        listAllWorkflowTask.addAll(listWorkFlowTaskUser);

        return listAllWorkflowTask;
    }

    private static final Log LOG = LogFactoryUtil.getLog(MVCRenderCommand.class);
}
