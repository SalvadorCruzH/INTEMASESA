package es.emasesa.intranet.base.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationEvent;
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
	
	public List<UserNotificationEvent> getNotificationsByUser(long userId){
		LoggerUtil.debug(LOG,"Obteniendo la lista de notificaciones de un usuario: " + userId);
        return _userNotificationEventLocalService.getArchivedUserNotificationEvents(userId, TYPE_CUSTOM,false);
    }
	
	public int getNotificationsByUserCount(long userId){
		LoggerUtil.debug(LOG,"Obteniendo el numero de notificaciones de un usuario: " + userId);
        return _userNotificationEventLocalService.getArchivedUserNotificationEventsCount(userId, false);
    }

    public void markAsReadNotification(long notificationId){
        UserNotificationEvent not = _userNotificationEventLocalService.fetchUserNotificationEvent(notificationId);
        not.setArchived(true);
        _userNotificationEventLocalService.updateUserNotificationEvent(not);
    }

    public void markAllAsReadNotification(User user) {
        List<UserNotificationEvent> notifications = _userNotificationEventLocalService
                .getArchivedUserNotificationEvents(user.getUserId(),TYPE_CUSTOM,false);
        for(UserNotificationEvent notification:notifications){
            notification.setArchived(true);
            _userNotificationEventLocalService.updateUserNotificationEvent(notification);
        }
    }
    
    @Reference
	private UserNotificationEventLocalService _userNotificationEventLocalService;
}


