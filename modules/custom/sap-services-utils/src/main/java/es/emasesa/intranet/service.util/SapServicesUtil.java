package es.emasesa.intranet.service.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosDomicilioException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosPersonalesException;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosDomicilioService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosPersonalesService;
import es.emasesa.intranet.sap.marcaje.exception.MarcajeException;
import es.emasesa.intranet.sap.marcaje.service.MarcajeService;
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

    public JSONArray getResumenAnual(long userId, String anno) {

        User user = _userLocalService.fetchUser(userId);
        JSONArray resumenAnual = JSONFactoryUtil.createJSONArray();
        if (Validator.isNotNull(user)) {
            resumenAnual = getResumenAnual(user.getScreenName(), anno);
        }
        return resumenAnual;
    }

    public JSONArray getResumenAnual(User user, String anno) {

        return getResumenAnual(user.getScreenName(), anno);
    }

    public JSONArray getResumenAnual(String pernr, String anno) {

        JSONArray resumenAnual = JSONFactoryUtil.createJSONArray();
        try {
            resumenAnual = resumenAnualService.obtenerResumenAnual(pernr, anno);
        } catch (MarcajeException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(), e);
        }

        return resumenAnual;
    }

    public JSONArray getHistoricoActual(long userId, String fechaInicio, String fechaFin) {
        JSONArray marcajeHistoricoActual = JSONFactoryUtil.createJSONArray();

        try {
            User user = _userLocalService.getUser(userId);
            marcajeHistoricoActual = getHistoricoActual(user.getScreenName(), fechaInicio, fechaFin);
        } catch (PortalException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(), e);
        }

        return marcajeHistoricoActual;

    }

    public JSONArray getHistoricoActual(User user, String fechaInicio, String fechaFin) {
        JSONArray marcajeHistoricoActual = JSONFactoryUtil.createJSONArray();

        marcajeHistoricoActual = getHistoricoActual(user.getScreenName(), fechaInicio, fechaFin);
        return marcajeHistoricoActual;

    }

    public JSONArray getHistoricoActual(String pernr, String fechaInicio, String fechaFin) {
        JSONArray marcajeHistoricoActual = JSONFactoryUtil.createJSONArray();

        try {
            marcajeHistoricoActual = marcajeService.obtenerMarcajeHistoricoActual(pernr, fechaInicio, fechaFin);
        } catch (MarcajeException e) {
            LOG.error(e.getMessage());
        }

        return marcajeHistoricoActual;

    }

    public JSONObject getDatosEmpleado(String pernr) {
        JSONObject datosEmpleado = JSONFactoryUtil.createJSONObject();

        try {
            datosEmpleado = empleadoDatosPersonalesService.getEmpleadoDatosPersonales(pernr);
        } catch (EmpleadoDatosPersonalesException e) {
            LOG.error(e.getMessage(), e);
        }

        return datosEmpleado;
    }

    public JSONObject getDatosEmpleadoDomicilio(String pernr) {

        JSONObject datosEmpleado = JSONFactoryUtil.createJSONObject();

        try {
            datosEmpleado = empleadoDatosDomicilioService.getEmpleadoDatosDomicilio(pernr);
        } catch (EmpleadoDatosDomicilioException e) {
            LOG.error(e.getMessage(), e);
        }

        return datosEmpleado;
    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {

        CustomServiceTracker<MarcajeService> marcajeServiceCustomServiceTracker = new CustomServiceTracker<>(MarcajeService.class, "getMarcajeService");
        CustomServiceTracker<ResumenAnualService> resumenAnualServiceCustomServiceTracker = new CustomServiceTracker<>(ResumenAnualService.class, "getResumenAnualService");
        CustomServiceTracker<EmpleadoDatosPersonalesService> empleadoDatosPersonalesServiceCustomService = new CustomServiceTracker<>(EmpleadoDatosPersonalesService.class, "getEmpleadoDatosPersonalesService");
        CustomServiceTracker<EmpleadoDatosDomicilioService> empleadoDatosPersonalesDomicilioServiceCustomService = new CustomServiceTracker<>(EmpleadoDatosDomicilioService.class, "getEmpleadoDatosPersonalesService");

        try {

            this.marcajeService = marcajeServiceCustomServiceTracker.getService();
            this.resumenAnualService = resumenAnualServiceCustomServiceTracker.getService();
            this.empleadoDatosPersonalesService = empleadoDatosPersonalesServiceCustomService.getService();
            this.empleadoDatosDomicilioService = empleadoDatosPersonalesDomicilioServiceCustomService.getService();

        } catch (InterruptedException e) {
            LOG.error("Se ha producido un error levantando el CustomTracker de Spring");
            throw new RuntimeException(e);
        }
    }


    @Reference
    private UserLocalService _userLocalService;
    private MarcajeService marcajeService;
    private ResumenAnualService resumenAnualService;
    private EmpleadoDatosPersonalesService empleadoDatosPersonalesService;
    private EmpleadoDatosDomicilioService empleadoDatosDomicilioService;

    private static final Log LOG = LogFactoryUtil.getLog(SapServicesUtil.class);

}
