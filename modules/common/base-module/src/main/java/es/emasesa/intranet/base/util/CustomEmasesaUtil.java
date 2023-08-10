package es.emasesa.intranet.base.util;

import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomEmasesaUtil.class
)
public class CustomEmasesaUtil {

	private final static Log LOG = LoggerUtil.getLog(CustomEmasesaUtil.class);

	public User getEmasesaAdmin(final long companyId) throws PortalException {
		return userLocalService.fetchUserByScreenName(companyId, EmasesaConstants.EMASESA_ADMIN_SCREENNAME);
	}

	@Reference
	UserLocalService userLocalService;

	@Reference
	ObjectEntryLocalService objectEntryLocalService;

	@Reference
	Portal portal;
}
