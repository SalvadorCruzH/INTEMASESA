package es.emasesa.intranet.rest.application;

import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.model.UserNotificationEventTable;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import es.emasesa.intranet.rest.constant.EmasesaRestConstant;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + EmasesaRestConstant.APPLICATION_BASE_URL+EmasesaRestConstant.NOTIFICACIONES_BASE,
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Notificaciones.Rest"
	},
	service = Application.class
)
public class RestEmasesaNotificacionesApplication extends Application {
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUserNotifications(@Context HttpServletRequest request) {

		try {
			User user = PermissionThreadLocal.getPermissionChecker().getUser();

			DSLQuery query = DSLQueryFactoryUtil
					.select()
					.from(UserNotificationEventTable.INSTANCE)
					.where(UserNotificationEventTable.INSTANCE.type.eq("com_liferay_portal_workflow_task_web_portlet_MyWorkflowTaskPortlet")
							.and(UserNotificationEventTable.INSTANCE.actionRequired.eq(false)
									.and(UserNotificationEventTable.INSTANCE.userId.eq(user.getUserId())))).limit(0, 10);

			DSLQuery queryCount = DSLQueryFactoryUtil
					.count()
					.from(UserNotificationEventTable.INSTANCE)
					.where(UserNotificationEventTable.INSTANCE.type.eq("com_liferay_portal_workflow_task_web_portlet_MyWorkflowTaskPortlet")
							.and(UserNotificationEventTable.INSTANCE.actionRequired.eq(false)
									.and(UserNotificationEventTable.INSTANCE.userId.eq(user.getUserId()))));

			List<UserNotificationEvent> userNotificationEventList = _userNotificationEventLocalService.dslQuery(query);
			int  userNotificationEventCount = _userNotificationEventLocalService.dslQueryCount(queryCount);
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("count", userNotificationEventCount);
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray(JSONFactoryUtil.looseSerialize(userNotificationEventList));
			jsonObject.put("notifications", jsonArray);
			return Response
					.status(Response.Status.OK)
					.entity(jsonObject.toString())
					.build();
		} catch (Exception e) {
			LOG.error("Error obteniendo las notificaciones", e);
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}

	private static final Log LOG = LogFactoryUtil.getLog(RestEmasesaNotificacionesApplication.class);

	@Reference
	UserNotificationEventLocalService _userNotificationEventLocalService;

}