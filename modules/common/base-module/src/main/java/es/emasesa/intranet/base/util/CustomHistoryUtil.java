package es.emasesa.intranet.base.util;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.liferay.portal.kernel.log.Log;

@Component( 
        immediate = true,
        service = CustomHistoryUtil.class
)
public class CustomHistoryUtil {

    public String getHistoryState(long objectId) throws PortalException {
    	ObjectEntry object = _objectEntryLocalService.fetchObjectEntry(objectId);
    	LoggerUtil.info(LOG,"Se obtiene el object a partir de su id");
    	String historico = object.getValues().get("historicoEstado").toString();
    	LoggerUtil.info(LOG,"Se obtiene el historico: " + historico);
         return historico;
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
    
    private static final Log LOG = LogFactoryUtil.getLog(CustomHistoryUtil.class);

}
