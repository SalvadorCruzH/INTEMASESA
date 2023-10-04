package es.emasesa.intranet.service.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.sap.marcaje.exception.MarcajeException;
import es.emasesa.intranet.sap.marcaje.service.MarcajeService;
import es.emasesa.intranet.sap.nomina.service.JornadaNominaService;
import es.emasesa.intranet.sap.proxy.SapInterfaceService;
import es.emasesa.intranet.sap.resumenanual.service.ResumenAnualService;
import java.util.Map;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = SapServicesUtil.class
)
public class SapServicesUtil {

   /* public void sendPeticionHorasExtras(long userId,String fechaInicio,String fechaFin,String tipoRetribucion){
        try {

            User user = _userLocalService.getUser(userId);
            jornadaNominaService.peticionHorasExtras(user.getScreenName(),fechaInicio,fechaFin,tipoRetribucion);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

    }*/
   public JSONArray getResumenAnual(long userId,String anno){
        User user = _userLocalService.fetchUser(userId);
        JSONArray array = JSONFactoryUtil.createJSONArray();
        if(Validator.isNotNull(user)){
            array = getResumenAnual(user.getScreenName(),anno);
        }
       return array;
   }
    public JSONArray getResumenAnual(User user,String anno){

        return getResumenAnual(user.getScreenName(),anno);
    }

    public  JSONArray getResumenAnual(String pernr, String anno){

        JSONArray array = JSONFactoryUtil.createJSONArray();
        try {
            array =  resumenAnualService.obtenerResumenAnual(pernr,anno);
        } catch (MarcajeException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(),e);
        }

        return array;
    }
    public JSONArray getHistoricoActual(long userId,String fechaInicio,String fechaFin){
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        try {
            User user = _userLocalService.getUser(userId);
            jsonArray =getHistoricoActual(user.getScreenName(),fechaInicio,fechaFin);
        } catch (PortalException e) {
            LOG.error(e.getMessage());
        }

        return jsonArray;

    }
    public JSONArray getHistoricoActual(User user,String fechaInicio,String fechaFin){
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

            jsonArray = getHistoricoActual(user.getScreenName(),fechaInicio,fechaFin);

        return jsonArray;

    }
    public JSONArray getHistoricoActual(String pernr,String fechaInicio,String fechaFin){
       JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        try {
            jsonArray = marcajeService.obtenerMarcajeHistoricoActual(pernr,fechaInicio,fechaFin);
        } catch (MarcajeException e) {
           LOG.error(e.getMessage());
        }

        return jsonArray;

    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
       // CustomServiceTracker<JornadaNominaService> jornadaNominaServiceCustomServiceTracker = new CustomServiceTracker<>(JornadaNominaService.class, "getEmpleadoEstructuraService");
        CustomServiceTracker<MarcajeService> marcajeServiceCustomServiceTracker = new CustomServiceTracker<>(MarcajeService.class, "getMarcajeService");
        CustomServiceTracker<ResumenAnualService> resumenAnualServiceCustomServiceTracker = new CustomServiceTracker<>(ResumenAnualService.class, "getResumenAnualService");

        try {
           // this.jornadaNominaService = jornadaNominaServiceCustomServiceTracker.getService();
            this.marcajeService = marcajeServiceCustomServiceTracker.getService();
            this.resumenAnualService = resumenAnualServiceCustomServiceTracker.getService();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Reference
    private UserLocalService _userLocalService;
    @Reference
    SapInterfaceService _sapService;
   // private JornadaNominaService jornadaNominaService;
    private MarcajeService marcajeService;
    private ResumenAnualService resumenAnualService;

    private static final Log LOG = LogFactoryUtil.getLog(SapServicesUtil.class);

}
