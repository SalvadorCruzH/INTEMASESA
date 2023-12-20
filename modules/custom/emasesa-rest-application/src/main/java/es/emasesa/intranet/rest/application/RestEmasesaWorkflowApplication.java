package es.emasesa.intranet.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.rest.constant.EmasesaRestConstant;
import es.emasesa.intranet.service.util.SapServicesUtil;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + EmasesaRestConstant.APPLICATION_BASE_URL+EmasesaRestConstant.WORKFLOW_TASK_BASE,
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=WorkflowTask.Rest"
	},
	service = Application.class
)
public class RestEmasesaWorkflowApplication extends Application {
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/{assetType}/{completed}/{byRole}/{start}/{end}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getWorkflowTasksAssigned(@Context HttpServletRequest request,@PathParam("assetType") String assetType,
									 @PathParam("completed") boolean completed,@PathParam("byRole") boolean byRole,@PathParam("start") int start,
									 @PathParam("end") int end) {

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		try {
			JSONArray result = getTasks(assetType, completed, byRole, start, end, serviceContext);

			return Response
					.status(Response.Status.OK)
					.entity(result.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Error obteniendo tareas", e);
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}

	@GET
	@Path("/{completed}/{byRole}/{start}/{end}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAllWorkflowTasksAssigned(@Context HttpServletRequest request,
											 @PathParam("completed") boolean completed,@PathParam("byRole") boolean byRole,@PathParam("start") int start,
											 @PathParam("end") int end) {

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		try {
			JSONArray result = getTasks("",completed, byRole, start, end, serviceContext);

			return Response
					.status(Response.Status.OK)
					.entity(result.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Error obteniendo tareas", e);
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}


	private JSONArray getTasks(String assetType, boolean completed, boolean byRole, int start, int end, ServiceContext serviceContext) throws PortalException {
		User user = _userLocalService.getUser(serviceContext.getUserId());
		Long[] asigneeIds = new Long[]{user.getUserId()};
		String[] assetTypes = new String[]{assetType};
		JSONArray result = JSONFactoryUtil.createJSONArray();

		List<WorkflowTask> tasks = _workflowTaskManager.search(serviceContext.getCompanyId(), user.getUserId(), null,
				null, assetTypes, null, null, asigneeIds, null,
				null, completed, byRole, null, null, false,
				start, end,null);

		for(WorkflowTask task:tasks){
			JSONObject jsonTask = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(task));

			jsonTask.put("attributes", JSONFactoryUtil.createJSONObject(
					JSONFactoryUtil.looseSerialize(task.getOptionalAttributes())));

			if(user.getUserId() == task.getAssigneeUserId()){
				jsonTask.put("assigneePersonName",user.getFullName());
			}else{
				User assigneeUser = _userLocalService.getUser(task.getAssigneeUserId());
				jsonTask.put("assigneePersonName",assigneeUser.getFullName());
			}

			result.put(jsonTask);

		}
		return result;
	}


	@GET
	@Path("/check/{roleId}/{workflowtaskId}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response checkUserAndRole(@Context HttpServletRequest request,@PathParam("userId") long userId,
									 @PathParam("roleId") long roleId,@PathParam("workflowtaskId") long workflowtaskId) {

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		boolean result = false;
		try {
			User user = _userLocalService.getUser(serviceContext.getUserId());
			WorkflowTask task = _workflowTaskManager.fetchWorkflowTask(workflowtaskId);

			result = task.getAssigneeUserId() == serviceContext.getUserId();

			String jsonArray = "";
			return Response
					.status(Response.Status.OK)
					.entity(Boolean.toString(result))
					.build();
		} catch (Exception e) {
			LOG.error("Error obteniendo la configuración inicial", e);
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}

	private static final Log LOG = LogFactoryUtil.getLog(RestEmasesaWorkflowApplication.class);
	@Reference
	protected SapServicesUtil _sapServicesUtil;
	@Reference
	protected WorkflowTaskManager _workflowTaskManager;
	@Reference
	protected UserLocalService _userLocalService;
}