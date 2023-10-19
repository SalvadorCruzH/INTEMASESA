package es.emasesa.intranet.base.util;

import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomHistoryUtil.class
)
public class CustomHistoryUtil {

    public String getHistoryState(long objectId) throws PortalException {
         return (String) _objectEntryLocalService.getObjectEntry(objectId).getValues().get("historicoEstado");
    }

    public String getUserAssigned(long userId) throws PortalException {
       String firstName = _userLocalService.getUser(userId).getFirstName();
       String lastName = _userLocalService.getUser(userId).getLastName();
       return firstName + " " + lastName;
    }

    @Reference
    ObjectEntryLocalService _objectEntryLocalService;
    @Reference
    UserLocalService _userLocalService;

}
