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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskInstanceIdComparator;
import com.liferay.portal.workflow.kaleo.exception.NoSuchInstanceException;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import es.emasesa.intranet.rest.constant.EmasesaRestConstant;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
			SimpleDateFormat formato = new SimpleDateFormat("dd MMMMMMMMMM yyyy | HH:mm");
			long companyId = CompanyThreadLocal.getCompanyId();
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
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			for(UserNotificationEvent userNotificationEvent: userNotificationEventList){
				JSONObject userNotificationEventJson = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(userNotificationEvent));
				JSONObject payload = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());
				try {
					String userName = "";
					try {
						userName = _userLocalService.fetchUser(payload.getLong("userId")).getFullName();
					}catch (Exception e){
						userName = "N/D";
					}
					userNotificationEventJson.put("payload", payload);
					userNotificationEventJson.put("userName", userName);
					userNotificationEventJson.put("timestamp", formato.format(new Date(userNotificationEvent.getTimestamp())));
					WorkflowInstance workflowInstance =
							WorkflowInstanceManagerUtil.getWorkflowInstance(
									companyId, payload.getLong("workflowInstanceId"));
					userNotificationEventJson.put("context", workflowInstance.getCurrentWorkflowNodes().get(0).getLabel(LocaleThreadLocal.getDefaultLocale()));
				}catch(Exception e){
				}
				jsonArray.put(userNotificationEventJson);
			}
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

	@GET
	@Path("/count")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUserNotificationsCount(@Context HttpServletRequest request) {
		try {
			User user = PermissionThreadLocal.getPermissionChecker().getUser();

			Long notificationsCount = (Long)_customCacheSingleUtil.get("NOTIFICATIONS_COUNT"+user.getUserId());
			if(notificationsCount == null) {
				DSLQuery queryCount = DSLQueryFactoryUtil
						.count()
						.from(UserNotificationEventTable.INSTANCE)
						.where(UserNotificationEventTable.INSTANCE.type.eq("com_liferay_portal_workflow_task_web_portlet_MyWorkflowTaskPortlet")
								.and(UserNotificationEventTable.INSTANCE.actionRequired.eq(false)
										.and(UserNotificationEventTable.INSTANCE.userId.eq(user.getUserId()))));

				int userNotificationEventCount = _userNotificationEventLocalService.dslQueryCount(queryCount);
				notificationsCount = (long)userNotificationEventCount;
				_customCacheSingleUtil.put("NOTIFICATIONS_COUNT"+user.getUserId(), notificationsCount, CustomCacheSingleUtil.TTL_10_MIN);
			}
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("count", notificationsCount);
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

	@Reference
	CustomCacheSingleUtil _customCacheSingleUtil;

	private static final Log LOG = LogFactoryUtil.getLog(RestEmasesaNotificacionesApplication.class);

	@Reference
	UserNotificationEventLocalService _userNotificationEventLocalService;

	@Reference
	UserLocalService _userLocalService;

}