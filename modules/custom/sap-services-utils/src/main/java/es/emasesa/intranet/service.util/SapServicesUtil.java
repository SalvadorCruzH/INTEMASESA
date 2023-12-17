package es.emasesa.intranet.service.util;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;

import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.sap.ayudaEscolar.service.AyudaEscolarService;
import es.emasesa.intranet.sap.centros.exception.DistanciaCentrosException;
import es.emasesa.intranet.sap.centros.service.DistanciaCentrosService;
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
import es.emasesa.intranet.sap.relacionLaboral.exception.RelacionLaboralException;
import es.emasesa.intranet.sap.relacionLaboral.service.RelacionLaboralService;
import es.emasesa.intranet.sap.resumenanual.exception.ResumenAnualException;
import es.emasesa.intranet.sap.resumenanual.service.ResumenAnualService;

import es.emasesa.intranet.sap.retenciones.service.CertificadoRetencionesService;
import es.emasesa.intranet.sap.subordinados.exception.SubordinadosException;
import es.emasesa.intranet.sap.subordinados.service.CiertosDatosEstructuraService;
import es.emasesa.intranet.sap.subordinados.service.SubordinadosService;

import java.io.Serializable;
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
            datosEmpleado.put("datosDomicilio", _empleadoDatosDomicilioService.getEmpleadoDatosDomicilio(datosEmpleado.getString("pernr")));
        } catch (SapCommunicationException | EmpleadoDatosPersonalesException | EmpleadoDatosDomicilioException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getDatosEmpleadoAndDomicilio " + pernr);
            }
        }

        return datosEmpleado;
    }

    public JSONArray getDistanciaCentros(String origen, String destino) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getDistanciaCentros " + origen + " - "+ destino);
        }
        JSONArray distanciaCentros = JSONFactoryUtil.createJSONArray();

        try {
            if(_distanciaCentrosService == null){
                activate(null);
            }

            distanciaCentros = _distanciaCentrosService.getDistanciaEntreCentros(origen, destino);
            if(!origen.equals("") && !destino.equals("")){

            }
        } catch (SapCommunicationException | DistanciaCentrosException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getDistanciaCentros " + origen);
            }
        }

        return distanciaCentros;
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
    
    /**
     * Llamada al servicio de empleado-prestamos para obtener datos de prestamo de un usuario.
     *
     * @param User
     * @return JSONArray
     */
    public JSONArray getEmpleadoPrestamos(User user) {

        if(LOG.isDebugEnabled()){
            LOG.debug("[B] getempleadoPrestamos " + user.getUserId());
        }
        JSONArray datosEmpleadoPrestamos = JSONFactoryUtil.createJSONArray();
        String pernr = StringPool.BLANK;
        try {
			//TODO: Intentar coger la matricula del m�todo de la clase expando. El servicio viene a null
	//		pernr = customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), "matricula");
			pernr = _expandoValueLocalService.getData(user.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "matricula", user.getUserId(), StringPool.BLANK);
			LOG.debug("Obtenida matrícula del usuario que hace la petición" + pernr);
			if(_empleadoPrestamsoService == null){
                activate(null);
            }
            datosEmpleadoPrestamos = _empleadoPrestamsoService.obtenerPrestamoEmpleados(pernr, StringPool.BLANK);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (EmpleadoPrestamosException e) {
            throw new RuntimeException(e);
        } catch (PortalException e) {
        	LOG.error("Error al intentar obtener la matricula del campo expando para getRelacionLaboralService", e);
		} finally {
            if(LOG.isDebugEnabled()){
                LOG.debug("[E] getempleadoPrestamos " + user.getUserId());
            }
        }
        return datosEmpleadoPrestamos;
    }
    
    /**
     * Llamada al servicio de empleado-relc-laboral para obtener datos laborales de un usuario.
     *
     * @param User
     * @return JSONObject
     */
	 public JSONObject getEmpleadoRelacionLaboral(User user) {
		 if(LOG.isDebugEnabled()){
		       LOG.debug("[B] getRelacionLaboralService " + user.getUserId());
		     }
			JSONObject datosEmpleadoRelacionLaboral = JSONFactoryUtil.createJSONObject();
		try {
			//TODO: Intentar coger la matricula del m�todo de la clase expando. El servicio viene a null
	//		String pernr = customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), "matricula");
			String pernr = _expandoValueLocalService.getData(user.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "matricula", user.getUserId(), StringPool.BLANK);
			LOG.debug("Obtenida matrícula del usuario que hace la petición" + pernr);
			
			 try {
		            if(_relacionLaboralService == null){
		                activate(null);
		            }
		            datosEmpleadoRelacionLaboral = _relacionLaboralService.getRelacionLaboralEmpleado(pernr);
		            LOG.debug("Obtenida la relacion laboral del empleado" + datosEmpleadoRelacionLaboral.toJSONString());
		        } catch (SapCommunicationException e) {
		            LOG.error(e.getMessage(), e);
		        } catch (RelacionLaboralException e) {
		            throw new RuntimeException(e);
		        } finally {
		            if(LOG.isDebugEnabled()){
		                LOG.debug("[E] getempleadoPrestamos " + pernr);
		            }
		        }
		}catch (Exception e) {
			LOG.error("Error al intentar obtener la matricula del campo expando para getRelacionLaboralService", e);
		}
		 return datosEmpleadoRelacionLaboral;
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
            CustomServiceTracker<CertificadoRetencionesService> certificadoRetencionesServiceTracker = new CustomServiceTracker<>(CertificadoRetencionesService.class, "getCertificadoRetencionesService");
            CustomServiceTracker<DistanciaCentrosService> distanciaCentrosServiceTracker = new CustomServiceTracker<>(DistanciaCentrosService.class, "getDistanciaCentrosService");
            CustomServiceTracker<AyudaEscolarService> ayudaEscolarServiceTracker = new CustomServiceTracker<>(AyudaEscolarService.class, "getAyudaEscolarService");
            CustomServiceTracker<CiertosDatosEstructuraService> ciertosDatosEstructuraServiceTracker = new CustomServiceTracker<>(CiertosDatosEstructuraService.class, "getCiertosDatosEstructuraService");
            CustomServiceTracker<RelacionLaboralService> relacionLaboralServiceTracker = new CustomServiceTracker<>(RelacionLaboralService.class, "getRelacionLaboralService");
            CustomServiceTracker<CustomExpandoUtil> customExpandoUtilTracker = new CustomServiceTracker<>(CustomExpandoUtil.class, "getCustomExpandoUtil");

            this._marcajeService = marcajeServiceCustomServiceTracker.getService();
            this._resumenAnualService = resumenAnualServiceCustomServiceTracker.getService();
            this._empleadoDatosPersonalesService = empleadoDatosPersonalesServiceCustomService.getService();
            this._empleadoDatosDomicilioService = empleadoDatosPersonalesDomicilioServiceCustomService.getService();
            this._jornadaDiariaService = jornadaDiariaServiceCustomService.getService();
            this._subordinadosService = subordinadosServiceTracker.getService();
            this._empleadoPrestamsoService = empleadoPrestamosServiceTracker.getService();
            this._certificadoRetencionesService = certificadoRetencionesServiceTracker.getService();
            this._distanciaCentrosService = distanciaCentrosServiceTracker.getService();
            this._ayudaEscolarService = ayudaEscolarServiceTracker.getService();
            this._ciertosDatosEstructuraService = ciertosDatosEstructuraServiceTracker.getService();
            this._relacionLaboralService = relacionLaboralServiceTracker.getService();
            this.customExpandoUtil = customExpandoUtilTracker.getService();
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
            } */
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
    private CertificadoRetencionesService _certificadoRetencionesService;
    private AyudaEscolarService _ayudaEscolarService;
    private DistanciaCentrosService _distanciaCentrosService;
    private CiertosDatosEstructuraService _ciertosDatosEstructuraService;
    private RelacionLaboralService _relacionLaboralService;
    private CustomExpandoUtil customExpandoUtil;
    @Reference
    private ExpandoValueLocalService _expandoValueLocalService;
    private static final Log LOG = LogFactoryUtil.getLog(SapServicesUtil.class);
}
