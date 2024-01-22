package es.emasesa.intranet.base.util;

import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.model.UserNotificationEventTable;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomUserNotificationUtil.class
)
public class CustomUserNotificationUtil {

    public static final int TYPE_CUSTOM = 10009;
    private final static Log LOG = LoggerUtil.getLog(CustomUserNotificationUtil.class);

    public List<UserNotificationEvent> getNotificationsByUser(long userId) {
        LoggerUtil.debug(LOG, "Obteniendo la lista de notificaciones de un usuario: " + userId);
        return _userNotificationEventLocalService.getArchivedUserNotificationEvents(userId, TYPE_CUSTOM, false);
    }

    public long getNotificationsByUserCount(long userId) {
        LoggerUtil.debug(LOG, "Obteniendo el numero de notificaciones de un usuario: " + userId);
        Long notificationsCount = (Long) _customCacheSingleUtil.get("NOTIFICATIONS_COUNT" + userId);
        if (notificationsCount == null) {
            DSLQuery queryCount = DSLQueryFactoryUtil
                    .count()
                    .from(UserNotificationEventTable.INSTANCE)
                    .where(UserNotificationEventTable.INSTANCE.type.eq("com_liferay_portal_workflow_task_web_portlet_MyWorkflowTaskPortlet")
                            .and(UserNotificationEventTable.INSTANCE.actionRequired.eq(false)
                                    .and(UserNotificationEventTable.INSTANCE.userId.eq(userId))));

            int userNotificationEventCount = _userNotificationEventLocalService.dslQueryCount(queryCount);
            notificationsCount = (long) userNotificationEventCount;
            _customCacheSingleUtil.put("NOTIFICATIONS_COUNT"+userId, notificationsCount, CustomCacheSingleUtil.TTL_10_MIN);
        }
        return notificationsCount;
    }

    public void markAsReadNotification(long notificationId) {
        UserNotificationEvent not = _userNotificationEventLocalService.fetchUserNotificationEvent(notificationId);
        not.setArchived(true);
        _userNotificationEventLocalService.updateUserNotificationEvent(not);
    }

    public void markAllAsReadNotification(User user) {
        List<UserNotificationEvent> notifications = _userNotificationEventLocalService
                .getArchivedUserNotificationEvents(user.getUserId(), TYPE_CUSTOM, false);
        for (UserNotificationEvent notification : notifications) {
            notification.setArchived(true);
            _userNotificationEventLocalService.updateUserNotificationEvent(notification);
        }
    }

    @Reference
    CustomCacheSingleUtil _customCacheSingleUtil;
    @Reference
    private UserNotificationEventLocalService _userNotificationEventLocalService;
}


