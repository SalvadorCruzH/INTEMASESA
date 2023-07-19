package es.emasesa.intranet.base.util;

import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import es.emasesa.intranet.base.constant.CamaraConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomCamaraUtil.class
)
public class CustomCamaraUtil {

	private final static Log LOG = LoggerUtil.getLog(CustomCamaraUtil.class);

	public User getCamaraAdmin(final long companyId) throws PortalException {
		return userLocalService.fetchUserByScreenName(companyId, CamaraConstants.CAMARA_ADMIN_SCREENNAME);
	}

	@Reference
	UserLocalService userLocalService;

	@Reference
	ObjectEntryLocalService objectEntryLocalService;

	@Reference
	Portal portal;
}
