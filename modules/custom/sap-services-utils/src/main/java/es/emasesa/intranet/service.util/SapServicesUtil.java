package es.emasesa.intranet.service.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.sap.empleadoPrestamos.service.EmpleadoPrestamosService;
import es.emasesa.intranet.sap.empleadoPrestamos.exception.EmpleadoPrestamosException;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosDomicilioException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosPersonalesException;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosDomicilioService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosPersonalesService;
import es.emasesa.intranet.sap.jornadadiaria.exception.JornadaDiariaException;
import es.emasesa.intranet.sap.jornadadiaria.service.JornadaDiariaService;
import es.emasesa.intranet.sap.marcaje.exception.MarcajeException;
import es.emasesa.intranet.sap.marcaje.service.MarcajeService;
import es.emasesa.intranet.sap.proxy.SapInterfaceService;
import es.emasesa.intranet.sap.resumenanual.exception.ResumenAnualException;
import es.emasesa.intranet.sap.resumenanual.service.ResumenAnualService;

import es.emasesa.intranet.sap.subordinados.exception.SubordinadosException;
import es.emasesa.intranet.sap.subordinados.service.SubordinadosService;
import java.util.Map;

import org.osgi.service.component.annotations.*;

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
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            resumenAnual = _resumenAnualService.obtenerResumenAnual(pernr, anno);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        } catch (SapCommunicationException | ResumenAnualException e) {
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
            if(_marcajeService == null){
                activate(null);
            }
            marcajeHistoricoActual = _marcajeService.obtenerMarcajeHistoricoActual(pernr, fechaInicio, fechaFin);
        } catch (SapCommunicationException | MarcajeException e) {
            LOG.error(e.getMessage());
        }

        return marcajeHistoricoActual;

    }

    public JSONObject getDatosEmpleado(String pernr) {
        JSONObject datosEmpleado = JSONFactoryUtil.createJSONObject();

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getDatosEmpleado " + pernr);
        }
        try {
            if(_empleadoDatosPersonalesService == null){
                activate(null);
            }
            datosEmpleado = _empleadoDatosPersonalesService.getEmpleadoDatosPersonales(pernr);
        } catch (SapCommunicationException | EmpleadoDatosPersonalesException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getDatosEmpleado " + pernr);
            }
        }

        return datosEmpleado;
    }

    public JSONObject getDatosEmpleadoDomicilio(String pernr) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getDatosEmpleadoDomicilio " + pernr);
        }
        JSONObject datosEmpleado = JSONFactoryUtil.createJSONObject();
        try {

            datosEmpleado = _empleadoDatosDomicilioService.getEmpleadoDatosDomicilio(pernr);
        } catch (SapCommunicationException | EmpleadoDatosDomicilioException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getDatosEmpleadoDomicilio " + pernr);
            }
        }

        return datosEmpleado;
    }

    public JSONObject getDatosEmpleadoAndDomicilio(String pernr) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getDatosEmpleadoAndDomicilio " + pernr);
        }
        JSONObject datosEmpleado = JSONFactoryUtil.createJSONObject();

        try {
            if(_empleadoDatosPersonalesService == null){
                activate(null);
            }

            datosEmpleado = _empleadoDatosPersonalesService.getEmpleadoDatosPersonales(pernr);
            datosEmpleado.put("datosDomicilio", _empleadoDatosDomicilioService.getEmpleadoDatosDomicilio(datosEmpleado.getString("perser")));
        } catch (SapCommunicationException | EmpleadoDatosPersonalesException | EmpleadoDatosDomicilioException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getDatosEmpleadoAndDomicilio " + pernr);
            }
        }

        return datosEmpleado;
    }

    public JSONArray getMarcajeHistoricoActual(String pernr, String fechaInicio, String fechaFin) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getMarcajeHistoricoActual " + pernr);
        }
        JSONArray datosMarcajeHistorico = JSONFactoryUtil.createJSONArray();

        try {
            if(_marcajeService == null){
                activate(null);
            }
            datosMarcajeHistorico = _marcajeService.obtenerMarcajeHistoricoActual(pernr, fechaInicio, fechaFin);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (MarcajeException e) {
            throw new RuntimeException(e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getMarcajeHistoricoActual " + pernr);
            }
        }
        return datosMarcajeHistorico;
    }

    public JSONArray getJornadaDiaria(User user,String fechaInicio,String fechaFin){

        return getJornadaDiaria(user.getScreenName(),fechaInicio,fechaFin);
    }

    public JSONArray getJornadaDiaria(String pernr, String fechaInicio, String fechaFin) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getJornadaDiaria " + pernr);
        }
        JSONArray datosJornadaDiaria = JSONFactoryUtil.createJSONArray();

        try {
            if(_jornadaDiariaService == null){
                activate(null);
            }
            datosJornadaDiaria = _jornadaDiariaService.obtenerJornadaDiaria(pernr, fechaInicio, fechaFin);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (JornadaDiariaException e) {
            throw new RuntimeException(e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getJornadaDiaria " + pernr);
            }
        }
        return datosJornadaDiaria;
    }

    public JSONArray getSubordinados(User user,String directorioOTodos) {

        return getSubordinados(user.getScreenName(),directorioOTodos);
    }

    public JSONArray getSubordinados(String pernr,String directorioOTodos) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getSubordinados " + pernr);
        }
        JSONArray datosSubordinados = JSONFactoryUtil.createJSONArray();

        try {
            if(_subordinadosService == null){
                activate(null);
            }
            datosSubordinados = _subordinadosService.getSubordinados(directorioOTodos,pernr);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (SubordinadosException e) {
            throw new RuntimeException(e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getSubordinados " + pernr);
            }
        }
        return datosSubordinados;
    }

    public JSONArray getempleadoPrestamos(String pernr, String fechaInicio) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getempleadoPrestamos " + pernr);
        }
        JSONArray datosEmpleadoPrestamos = JSONFactoryUtil.createJSONArray();

        try {
            if(_empleadoPrestamsoService == null){
                activate(null);
            }
            datosEmpleadoPrestamos = _empleadoPrestamsoService.obtenerPrestamoEmpleados(pernr, fechaInicio);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (EmpleadoPrestamosException e) {
            throw new RuntimeException(e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getempleadoPrestamos " + pernr);
            }
        }
        return datosEmpleadoPrestamos;
    }



    @Activate
    protected void activate(Map<String, Object> properties) {

        try {
            CustomServiceTracker<MarcajeService> marcajeServiceCustomServiceTracker = new CustomServiceTracker<>(MarcajeService.class, "getMarcajeService");
            CustomServiceTracker<ResumenAnualService> resumenAnualServiceCustomServiceTracker = new CustomServiceTracker<>(ResumenAnualService.class, "getResumenAnualService");
            CustomServiceTracker<EmpleadoDatosPersonalesService> empleadoDatosPersonalesServiceCustomService = new CustomServiceTracker<>(EmpleadoDatosPersonalesService.class, "getEmpleadoDatosPersonalesService");
            CustomServiceTracker<EmpleadoDatosDomicilioService> empleadoDatosPersonalesDomicilioServiceCustomService = new CustomServiceTracker<>(EmpleadoDatosDomicilioService.class, "getEmpleadoDatosDomicilioService");
            CustomServiceTracker<JornadaDiariaService> jornadaDiariaServiceCustomService = new CustomServiceTracker<>(JornadaDiariaService.class, "getJornadaDiariaService");
            CustomServiceTracker<SubordinadosService> subordinadosServiceTracker = new CustomServiceTracker<>(SubordinadosService.class, "getSubordinadosService");
            CustomServiceTracker<EmpleadoPrestamosService> empleadoPrestamosServiceTracker = new CustomServiceTracker<>(EmpleadoPrestamosService.class, "getEmpleadoPrestamosService");

            this._marcajeService = marcajeServiceCustomServiceTracker.getService();
            this._resumenAnualService = resumenAnualServiceCustomServiceTracker.getService();
            this._empleadoDatosPersonalesService = empleadoDatosPersonalesServiceCustomService.getService();
            this._empleadoDatosDomicilioService = empleadoDatosPersonalesDomicilioServiceCustomService.getService();
            this._jornadaDiariaService = jornadaDiariaServiceCustomService.getService();
            this._subordinadosService = subordinadosServiceTracker.getService();
            this._empleadoPrestamsoService = empleadoPrestamosServiceTracker.getService();

            /*if(_jornadaDiariaService != null) {
                JSONArray jornadaDiaria = _jornadaDiariaService.obtenerJornadaDiaria("1002982", "2022-09-10", "2022-10-10");
                if (jornadaDiaria != null && jornadaDiaria.length() > 0) {
                    LOG.info("jornadaDiariaService OK");
                }

                JSONArray marcaje = _marcajeService.obtenerMarcajeHistoricoActual("1002982", "2022-09-10", "2022-10-10");
                if (marcaje != null && marcaje.length() > 0) {
                    LOG.info("jornadaDiariaService OK");
                }
                JSONObject addressData = _empleadoDatosDomicilioService.getEmpleadoDatosDomicilio("1002982");
                if (addressData != null) {
                    LOG.info("empleadoDatosDomicilioService OK ");
                }
                JSONObject personalData = _empleadoDatosPersonalesService.getEmpleadoDatosPersonales("1002982");
                if (personalData != null) {
                    LOG.info("empleadoDatosPersonalesService OK");
                }
                JSONArray resumenAnualData = _resumenAnualService.obtenerResumenAnual("1002982", "2022");
                if (resumenAnualData != null) {
                    LOG.info("resumenAnualService OK");
                }
            }*/
        } catch (InterruptedException e) {
            LOG.error("Se ha producido un error levantando el CustomTracker de Spring");
            throw new RuntimeException(e);
       /*} catch (SapException e) {
            LOG.info("Se ha producido un error validando los servicios en el arranque", e);
        */} catch (NullPointerException e) {
            LOG.info("Se ha producido un error accediendo a los servicios");
        }
    }

    @Reference
    private UserLocalService _userLocalService;
    private MarcajeService _marcajeService;
    private ResumenAnualService _resumenAnualService;
    private EmpleadoDatosPersonalesService _empleadoDatosPersonalesService;
    private EmpleadoDatosDomicilioService _empleadoDatosDomicilioService;
    private JornadaDiariaService _jornadaDiariaService;
    private SubordinadosService _subordinadosService;
    private EmpleadoPrestamosService _empleadoPrestamsoService;
    private static final Log LOG = LogFactoryUtil.getLog(SapServicesUtil.class);
}
