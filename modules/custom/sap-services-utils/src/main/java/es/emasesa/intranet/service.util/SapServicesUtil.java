package es.emasesa.intranet.service.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;
import es.emasesa.intranet.sap.nomina.service.JornadaNominaService;
import es.emasesa.intranet.sap.proxy.SapInterfaceService;
import java.util.Map;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomWorkflowUtil.class
)
public class SapServicesUtil {

    public void sendPeticionHorasExtras(long userId,String fechaInicio,String fechaFin,String tipoRetribucion){
        try {
            User user = _userLocalService.getUser(userId);
            jornadaNominaService.peticionHorasExtras(user.getScreenName(),fechaInicio,fechaFin,tipoRetribucion);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        CustomServiceTracker<JornadaNominaService> service = new CustomServiceTracker<>(JornadaNominaService.class, "getEmpleadoEstructuraService");

        try {
            this.jornadaNominaService = service.getService();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Reference
    private UserLocalService _userLocalService;
    @Reference
    SapInterfaceService _sapService;
    private JornadaNominaService jornadaNominaService;

    private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);

}
