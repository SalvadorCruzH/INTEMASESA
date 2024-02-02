package es.emasesa.intranet.service.util;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.template.ServiceLocator;

import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.sap.ayudaEscolar.exception.AyudaEscolarException;
import es.emasesa.intranet.sap.ayudaEscolar.service.AyudaEscolarService;
import es.emasesa.intranet.sap.calendarioeventos.exception.CalendarioEventosException;
import es.emasesa.intranet.sap.calendarioeventos.service.CalendarioEventosService;
import es.emasesa.intranet.sap.centros.exception.DistanciaCentrosException;
import es.emasesa.intranet.sap.centros.service.DistanciaCentrosService;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoActDatosPersonalesException;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoActDatosPersonalesService;
import es.emasesa.intranet.sap.empleadoBanco.exception.EmpleadoBancoException;
import es.emasesa.intranet.sap.empleadoBanco.service.EmpleadoBancoService;
import es.emasesa.intranet.sap.empleadoPrestamos.service.EmpleadoPrestamosService;
import es.emasesa.intranet.sap.empleadoPrestamos.exception.EmpleadoPrestamosException;
import es.emasesa.intranet.sap.base.exception.SapCommunicationException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosDomicilioException;
import es.emasesa.intranet.sap.datospersona.exception.EmpleadoDatosPersonalesException;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosDomicilioService;
import es.emasesa.intranet.sap.datospersona.service.EmpleadoDatosPersonalesService;
import es.emasesa.intranet.sap.estructura.exception.EmpleadoEstructuraException;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;
import es.emasesa.intranet.sap.historForm.exception.HistorFormException;
import es.emasesa.intranet.sap.historForm.service.HistorFormService;
import es.emasesa.intranet.sap.jornadadiaria.exception.JornadaDiariaException;
import es.emasesa.intranet.sap.jornadadiaria.service.JornadaDiariaService;
import es.emasesa.intranet.sap.marcaje.exception.MarcajeException;
import es.emasesa.intranet.sap.marcaje.service.MarcajeService;
import es.emasesa.intranet.sap.necesidadesFormacion.exception.NecesidadesFormacionException;
import es.emasesa.intranet.sap.necesidadesFormacion.service.NecesidadesFormacionService;
import es.emasesa.intranet.sap.proxy.SapInterfaceService;
import es.emasesa.intranet.sap.relacionLaboral.exception.RelacionLaboralException;
import es.emasesa.intranet.sap.relacionLaboral.service.RelacionLaboralService;
import es.emasesa.intranet.sap.resumenanual.exception.ResumenAnualException;
import es.emasesa.intranet.sap.resumenanual.service.ResumenAnualService;

import es.emasesa.intranet.sap.retenciones.exception.CertificadoRetencionesException;
import es.emasesa.intranet.sap.retenciones.service.CertificadoRetencionesService;
import es.emasesa.intranet.sap.subordinados.exception.SubordinadosException;
import es.emasesa.intranet.sap.ciertosdatos.service.CiertosDatosEstructuraService;
import es.emasesa.intranet.sap.subordinados.service.SubordinadosService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.ThreadContext;
import org.osgi.service.component.annotations.*;

@Component(
        immediate = true,
        service = SapServicesUtil.class
)
public class SapServicesUtil {

    public JSONArray getResumenAnual(long userId, String anno) {

        User user = _userLocalService.fetchUser(userId);
        ThreadContext.push(user.getScreenName());
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
            if (_resumenAnualService == null) {
                activate(null);
            }
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            resumenAnual = _resumenAnualService.obtenerResumenAnual(pernr, anno);

        } catch (SapCommunicationException | ResumenAnualException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(), e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return resumenAnual;
    }

    public JSONObject getRetenciones(User user, String anno) {

        return getRetenciones(user.getScreenName(), anno);
    }

    public JSONObject getRetenciones(String pernr, String anno) {

        ThreadContext.push(pernr);
        JSONObject retenciones = JSONFactoryUtil.createJSONObject();
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            if (_certificadoRetencionesService == null) {
                activate(null);
            }
            retenciones = _certificadoRetencionesService.getCertificadoRetenciones(pernr, anno);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        } catch (SapCommunicationException | CertificadoRetencionesException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(), e);
        }

        return retenciones;
    }

    public JSONArray getHistoricoActual(long userId, String fechaInicio, String fechaFin) {
        JSONArray marcajeHistoricoActual = JSONFactoryUtil.createJSONArray();

        try {
            User user = _userLocalService.getUser(userId);
            ThreadContext.push(user.getScreenName());
            marcajeHistoricoActual = getHistoricoActual(user.getScreenName(), fechaInicio, fechaFin);
        } catch (PortalException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(), e);
        }

        return marcajeHistoricoActual;

    }

    public JSONArray getHistoricoActual(User user, String fechaInicio, String fechaFin) {
        JSONArray marcajeHistoricoActual = JSONFactoryUtil.createJSONArray();
        ThreadContext.push(user.getScreenName());
        marcajeHistoricoActual = getHistoricoActual(user.getScreenName(), fechaInicio, fechaFin);
        return marcajeHistoricoActual;

    }

    public JSONArray getHistoricoActual(String pernr, String fechaInicio, String fechaFin) {
        JSONArray marcajeHistoricoActual = JSONFactoryUtil.createJSONArray();
        ThreadContext.push(pernr);
        try {
            if (_marcajeService == null) {
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
        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getDatosEmpleado " + pernr);
        }
        try {
            if (_empleadoDatosPersonalesService == null) {
                activate(null);
            }
            datosEmpleado = _empleadoDatosPersonalesService.getEmpleadoDatosPersonales(pernr);
        } catch (SapCommunicationException | EmpleadoDatosPersonalesException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getDatosEmpleado " + pernr);
            }
        }

        return datosEmpleado;
    }

    public JSONObject getDatosEmpleadoDomicilio(String pernr) {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getDatosEmpleadoDomicilio " + pernr);
        }
        JSONObject datosEmpleado = JSONFactoryUtil.createJSONObject();
        try {

            datosEmpleado = _empleadoDatosDomicilioService.getEmpleadoDatosDomicilio(pernr);
        } catch (SapCommunicationException | EmpleadoDatosDomicilioException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getDatosEmpleadoDomicilio " + pernr);
            }
        }

        return datosEmpleado;
    }

    public JSONObject getDatosEmpleadoAndDomicilio(String pernr) {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getDatosEmpleadoAndDomicilio " + pernr);
        }
        JSONObject datosEmpleado = JSONFactoryUtil.createJSONObject();

        try {
            if (_empleadoDatosPersonalesService == null) {
                activate(null);
            }

            datosEmpleado = _empleadoDatosPersonalesService.getEmpleadoDatosPersonales(pernr);
            datosEmpleado.put("datosDomicilio", _empleadoDatosDomicilioService.getEmpleadoDatosDomicilio(datosEmpleado.getString("pernr")));
        } catch (SapCommunicationException | EmpleadoDatosPersonalesException | EmpleadoDatosDomicilioException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getDatosEmpleadoAndDomicilio " + pernr);
            }
        }

        return datosEmpleado;
    }

    public JSONArray getDistanciaCentros(String origen, String destino) {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getDistanciaCentros " + origen + " - " + destino);
        }
        JSONArray distanciaCentros = JSONFactoryUtil.createJSONArray();

        try {
            if (_distanciaCentrosService == null) {
                activate(null);
            }

            distanciaCentros = _distanciaCentrosService.getDistanciaEntreCentros(origen, destino);
            if (!origen.equals("") && !destino.equals("")) {

            }
        } catch (SapCommunicationException | DistanciaCentrosException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getDistanciaCentros " + origen);
            }
        }

        return distanciaCentros;
    }

    public JSONArray getMarcajeHistoricoActual(String pernr, String fechaInicio, String fechaFin) {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getMarcajeHistoricoActual " + pernr);
        }
        JSONArray datosMarcajeHistorico = JSONFactoryUtil.createJSONArray();

        try {
            if (_marcajeService == null) {
                activate(null);
            }
            datosMarcajeHistorico = _marcajeService.obtenerMarcajeHistoricoActual(pernr, fechaInicio, fechaFin);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (MarcajeException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getMarcajeHistoricoActual " + pernr);
            }
        }
        return datosMarcajeHistorico;
    }

    public JSONArray getJornadaDiaria(User user, String fechaInicio, String fechaFin) {
        ThreadContext.push(user.getScreenName());
        return getJornadaDiaria(user.getScreenName(), fechaInicio, fechaFin);
    }

    public JSONArray getJornadaDiaria(String pernr, String fechaInicio, String fechaFin) {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getJornadaDiaria " + pernr);
        }
        JSONArray datosJornadaDiaria = JSONFactoryUtil.createJSONArray();

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            if (_jornadaDiariaService == null) {
                activate(null);
            }
            datosJornadaDiaria = _jornadaDiariaService.obtenerJornadaDiaria(pernr, fechaInicio, fechaFin);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (JornadaDiariaException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getJornadaDiaria " + pernr);
            }
        }
        return datosJornadaDiaria;
    }

    public JSONArray getSubordinados(User user, String directorioOTodos) {

        ThreadContext.push(user.getScreenName());
        return getSubordinados(user.getScreenName(), directorioOTodos);
    }

    public JSONArray getSubordinados(String pernr, String directorioOTodos) {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getSubordinados " + pernr);
        }
        JSONArray datosSubordinados = JSONFactoryUtil.createJSONArray();

        try {
            if (_subordinadosService == null) {
                activate(null);
            }
            datosSubordinados = _subordinadosService.getSubordinados(directorioOTodos, pernr);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (SubordinadosException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getSubordinados " + pernr);
            }
        }
        return datosSubordinados;
    }

    public JSONArray getEmpleadoPrestamos(String pernr, String fechaInicio) {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getempleadoPrestamos " + pernr);
        }
        JSONArray datosEmpleadoPrestamos = JSONFactoryUtil.createJSONArray();

        try {
            if (_empleadoPrestamsoService == null) {
                activate(null);
            }
            datosEmpleadoPrestamos = _empleadoPrestamsoService.obtenerPrestamoEmpleados(pernr, fechaInicio);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (EmpleadoPrestamosException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getempleadoPrestamos " + pernr);
            }
        }
        return datosEmpleadoPrestamos;
    }

    /**
     * Llamada al servicio de empleado-prestamos para obtener datos de prestamo de un usuario.
     *
     * @param user
     * @return JSONArray
     */
    public JSONArray getEmpleadoPrestamos(User user) {

        ThreadContext.push(user.getScreenName());
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getempleadoPrestamos " + user.getUserId());
        }
        JSONArray datosEmpleadoPrestamos = JSONFactoryUtil.createJSONArray();
        String pernr = StringPool.BLANK;
        try {
            if (_empleadoPrestamsoService == null || _customExpandoUtil == null) {
                activate(null);
            }
            if (Validator.isNotNull(user)) {
                LOG.debug("Obteniendo los datos del usuario...");
                pernr = _customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), "matricula");
                LOG.debug("Obtenida matrícula del usuario que hace la petición" + pernr);
                datosEmpleadoPrestamos = _empleadoPrestamsoService.obtenerPrestamoEmpleados(pernr, StringPool.BLANK);
            }
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (EmpleadoPrestamosException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getempleadoPrestamos " + user.getUserId());
            }
        }
        return datosEmpleadoPrestamos;
    }

    /**
     * Llamada al servicio de empleado-relc-laboral para obtener datos laborales de un usuario.
     *
     * @param user
     * @return JSONObject
     */
    public JSONObject getEmpleadoRelacionLaboral(User user) {

        ThreadContext.push(user.getScreenName());
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getRelacionLaboralService " + user.getUserId());
        }
        JSONObject datosEmpleadoRelacionLaboral = JSONFactoryUtil.createJSONObject();
        String pernr = StringPool.BLANK;
        try {
            if (_relacionLaboralService == null || _customExpandoUtil == null) {
                activate(null);
            }
            if (Validator.isNotNull(user)) {
                LOG.debug("Obteniendo los datos del usuario...");
                pernr = _customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), "matricula");
                LOG.debug("Obtenida matrícula del usuario que hace la petición" + pernr);
                datosEmpleadoRelacionLaboral = _relacionLaboralService.getRelacionLaboralEmpleado(pernr);
                LOG.debug("Obtenida la relacion laboral del empleado" + datosEmpleadoRelacionLaboral.toJSONString());
            }
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (RelacionLaboralException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getempleadoPrestamos " + pernr);
            }
        }
        return datosEmpleadoRelacionLaboral;
    }

    /**
     * Llamada al servicio de empleado-banco para obtener datos de banco de un usuario.
     *
     * @param user
     * @return JSONArray
     */
    public JSONArray getEmpleadoBanco(User user) {

        ThreadContext.push(user.getScreenName());
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getEmpleadoBanco " + user.getUserId());
        }
        JSONArray datosEmpleadoBanco = JSONFactoryUtil.createJSONArray();
        String pernr = StringPool.BLANK;
        try {
            if (_empleadoBancoService == null || _customExpandoUtil == null) {
                activate(null);
            }
            if (Validator.isNotNull(user)) {
                LOG.debug("Obteniendo los datos del usuario...");
                pernr = _customExpandoUtil.getDataValueByUser(user.getUserId(), user.getCompanyId(), "matricula");
                LOG.debug("Obtenida matrícula del usuario que hace la petición" + pernr);
                datosEmpleadoBanco = _empleadoBancoService.getEmpleadoBanco(pernr);
            }
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (EmpleadoBancoException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getEmpleadoBanco " + user.getUserId());
            }
        }
        return datosEmpleadoBanco;
    }

    public JSONArray getImporteAnticipo(JSONArray datosEmpleados) {
        JSONArray datosCalculadosArray = JSONFactoryUtil.createJSONArray();
        LOG.debug("[E] getImporteAnticipo " + datosEmpleados.toString());
        JSONObject primerObjeto = datosEmpleados.getJSONObject(0);

        double salarioBase = primerObjeto.getDouble("salarioBase");
        double antiguedad = primerObjeto.getDouble("antiguedad");

        double importeAnticipo = (salarioBase + antiguedad) * 30;
        double reintegroMensual = importeAnticipo / 12;

        double redondeoImporteAnticipo = Math.round(importeAnticipo * 100.0) / 100.0;
        double redondeoReintegroMensual = Math.round(reintegroMensual * 100.0) / 100.0;

        JSONObject datosCalculados = JSONFactoryUtil.createJSONObject();
        datosCalculados.put("importeAnticipo", redondeoImporteAnticipo);
        datosCalculados.put("reintegroMensual", redondeoReintegroMensual);
        LOG.debug("[E] este metodo devuelve " + datosCalculadosArray.toString());
        datosCalculadosArray.put(datosCalculados);

        return datosCalculadosArray;
    }

    public JSONObject getAyudaEscolar(User user) throws PortalException {

        ThreadContext.push(user.getScreenName());
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getAyudaEscolar " + user.getUserId());
        }
        JSONObject datosAyudaEscolar = JSONFactoryUtil.createJSONObject();
        String pernr = StringPool.BLANK;

        pernr = _expandoValueLocalService.getData(user.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "matricula", user.getUserId(), StringPool.BLANK);
        LOG.debug("Obtenida matrícula del usuario que hace la petición" + pernr);

        try {
            if (_ayudaEscolarService == null) {
                activate(null);
            }
            datosAyudaEscolar = (JSONObject) _ayudaEscolarService.getAyudaEscolar(pernr);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (AyudaEscolarException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getAyudaEscolar " + pernr);
            }
        }
        return datosAyudaEscolar;
    }

    public void saveAyudaEscolar(Map<String, Serializable> workflowContext) throws PortalException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] saveAyudaEscolar ");
        }
        long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
        LOG.debug("Obtenido classPK la petición" + classPK);

        Map<String, Serializable> objectValues = _objectEntryLocalService.getObjectEntry(classPK).getValues();
        String pernr = (String) objectValues.get("numeroDeMatricula");
        ThreadContext.push(pernr);
        String centro = (String) objectValues.get("centroDeEstudios");
        String estudioId = (String) objectValues.get("estudios");
        String estudioNivel = (String) objectValues.get("numero");
        String famNumerosa = (String) objectValues.get("familiaNumerosa");
        String famMonoparental = (String) objectValues.get("familiaMonoparental");
        String numero = (String) objectValues.get("numero");
        String tipoId = (String) objectValues.get("beneficiariosDeLaAyuda");
        String comentarios = (String) objectValues.get("comentarios");

        try {
            if (_ayudaEscolarService == null) {
                activate(null);
            }
            _ayudaEscolarService.saveAyudaEscolar(pernr, centro, estudioId, estudioNivel, famNumerosa, numero, tipoId, famMonoparental, comentarios);
        } catch (SapCommunicationException e) {
            LOG.error(e.getMessage(), e);
        } catch (AyudaEscolarException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] finalizada saveAyudaEscolar para el usuario con matrícula " + pernr);
            }
        }
    }

    public JSONObject getPlanesFormacion(String planFormacion) throws NecesidadesFormacionException, SapCommunicationException {

        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getPlanesFormacion " + planFormacion);
        }
        JSONObject datosNecesidadesFormacion = JSONFactoryUtil.createJSONObject();

        LOG.debug("dentro de getPlanesFormacion con fecha de peticion " + planFormacion);

        try {
            if (_necesidadesFormacionService == null) {
                activate(null);
            }
            datosNecesidadesFormacion = (JSONObject) _necesidadesFormacionService.getPlanesFormacion(planFormacion);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getPlanesFormacion " + planFormacion);
            }
        }
        return datosNecesidadesFormacion;
    }

    public JSONObject getCalendarioEventos(String pernr, String fechaDesde, String fechaHasta) throws CalendarioEventosException, SapCommunicationException {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getCalendarioEventos ");
        }
        JSONObject calendarioEventos = JSONFactoryUtil.createJSONObject();
        try {
            if (_calendarioEventosService == null) {
                activate(null);
            }
            LOG.debug("vamos a llamar al servicio de calendarioEventos con los parametros: " + pernr + " " + fechaDesde + " " + fechaHasta);
            calendarioEventos = _calendarioEventosService.getCalendarioEventos(pernr, fechaDesde, fechaHasta);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getCalendarioEventos " + calendarioEventos);
            }
        }
        return calendarioEventos;
    }

    public void manejarInscripcionFormacion(Map<String, Object> workflowContext) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("[A] manejarInscripcionFormacion ");
        }
        try {
            if (_calendarioEventosService == null) {
                activate(null);
            }
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            LOG.debug("Obtenido classPK la petición" + classPK);

            Map<String, Serializable> objectValues = _objectEntryLocalService.getObjectEntry(classPK).getValues();
            String pernr = (String) objectValues.get("numeroDeMatricula");
            ThreadContext.push(pernr);
            String eventoId = (String) objectValues.get("idDelEvento");
            String accion = (String) objectValues.get("accionAlEvento");
            String fecha = "";

            _calendarioEventosService.inscribirCalendarioEventos(pernr, fecha, fecha, accion, eventoId);

        } catch (PortalException e) {
            LOG.debug("[B] manejarInscripcionFormacion al obtener objeto");
        } catch (CalendarioEventosException e) {
            LOG.debug("[C] manejarInscripcionFormacion al comunicar con calendario service");
        } catch (SapCommunicationException e) {
            LOG.debug("[C] manejarInscripcionFormacion al comunicar con SAP");
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[D] manejarInscripcionFormacion, finalizada ");
            }
        }
    }

    public void setDatosPersonales(Map<String, Object> workflowContext) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("[A] setDatosPersonales ");
        }
        try {
            if (_empleadoActDatosPersonalesService == null) {
                activate(null);
            }
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            LOG.debug("Obtenido classPK la petición" + classPK);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, Serializable> objectValues = _objectEntryLocalService.getObjectEntry(classPK).getValues();

            String pernr = (String) objectValues.get("matricula");
            ThreadContext.push(pernr);
            String nombre = (String) objectValues.get("nombre");
            String apellido1 = (String) objectValues.get("apellido1");
            String apellido2 = (String) objectValues.get("apellido2");
            String calle = (String) objectValues.get("calle");
            Date fechaInicioDate = _objectEntryLocalService.getObjectEntry(classPK).getCreateDate();
            String fechaInicio = formatter.format(fechaInicioDate);
            String email = (String) objectValues.get("email");
            String claseId = StringPool.BLANK;
            String codigoPostal = (String) objectValues.get("codigoPostal");
            String estadoCivilId = StringPool.BLANK;
            String nifE = StringPool.BLANK;
            String generoId = StringPool.BLANK;
            BigDecimal numeroHijos = BigDecimal.ZERO;
            Date fechaNacimientoDate = (Date) objectValues.get("fechaDeNacimiento");
            String fechaNacimiento = formatter.format(fechaNacimientoDate);
            String poblacionNacimiento = StringPool.BLANK;
            String provinciaNacimientoId = StringPool.BLANK;
            String nacionalidadId = StringPool.BLANK;
            String nroSs = StringPool.BLANK;
            String numero = String.valueOf(objectValues.get("numero"));
            String portal = (String) objectValues.get("portal");
            String pisoLetra = (String) objectValues.get("pisoYLetra");
            String poblacion = (String) objectValues.get("poblacion");
            String provinciaId = (String) objectValues.get("provincia");
            String telefono = (String) objectValues.get("telefono");


            _empleadoActDatosPersonalesService.actEmpleadoDatosPersonales(
                    pernr,
                    nombre,
                    apellido1,
                    apellido2,
                    calle,
                    fechaInicio,
                    email,
                    claseId,
                    codigoPostal,
                    estadoCivilId,
                    nifE,
                    generoId,
                    numeroHijos,
                    fechaNacimiento,
                    poblacionNacimiento,
                    provinciaNacimientoId,
                    nacionalidadId,
                    nroSs,
                    numero,
                    portal,
                    pisoLetra,
                    poblacion,
                    provinciaId,
                    telefono
            );
        } catch (PortalException e) {
            LOG.debug("[B] setDatosPersonales al obtener objeto");
        } catch (EmpleadoActDatosPersonalesException e) {
            LOG.debug("[C] setDatosPersonales al comunicar con Datos Personales service");
        } catch (SapCommunicationException e) {
            LOG.debug("[C] setDatosPersonales al comunicar con SAP");
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[D] setDatosPersonales, finalizada ");
            }
        }
    }

    public JSONObject getEmpleadoEstructura(String pernr) {

        ThreadContext.push(pernr);
        if (LOG.isDebugEnabled()) {
            LOG.debug("[B] getEmpleadoEstructura ");
        }
        JSONObject empleadoEstructura = JSONFactoryUtil.createJSONObject();
        try {
            if (_empleadoEstructuraService == null) {
                activate(null);
            }
            LOG.debug("Llamada a getEmpleadoEstructura con pernr: " + pernr);
            empleadoEstructura = _empleadoEstructuraService.getEmpleadoEstructura(pernr);
        } catch (EmpleadoEstructuraException | SapCommunicationException e) {
            throw new RuntimeException(e);
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getEmpleadoEstructura " + empleadoEstructura);
            }
        }
        return empleadoEstructura;
    }

    public String getResponsableFromEstructuraEmpleado(String pernr, String responsable) {

        ThreadContext.push(pernr);
        String responsableEstructura = StringPool.BLANK;
        if (LOG.isDebugEnabled()) {
            LOG.debug("[E] getResponsableFromEstructuraEmpleado");
        }
        JSONObject empleadoEstructura;
        if (_empleadoEstructuraService == null) {
            activate(null);
        }
        empleadoEstructura = getEmpleadoEstructura(pernr);
        if (Validator.isNotNull(empleadoEstructura)) {
            responsableEstructura = empleadoEstructura.getString(responsable);
            if (LOG.isDebugEnabled()) {
                LOG.debug("[E] getResponsableFromEstructuraEmpleado " + responsableEstructura);
            }
        }
        return responsableEstructura;
    }

    public JSONObject getHistorialFormacion(String pernr){
        if (LOG.isDebugEnabled()) {
            LOG.debug("[A] getHistorialFormacion ");
        }
        JSONObject ret_historFormService = JSONFactoryUtil.createJSONObject();
        try {
            if (_historFormService == null) {
                activate(null);
            }
            LOG.debug("Obtenido matricula: " + pernr);

            ret_historFormService = _historFormService.obtenerHistorialFormacion(pernr);

        } catch (SapCommunicationException e) {
            LOG.debug("[C] getHistorialFormacion al comunicar con SAP " + e.getMessage());
        } catch (HistorFormException e) {
            LOG.debug("[E] getHistorialFormacion al obtener el historial " + e.getMessage());
        } finally {
            if (LOG.isDebugEnabled()) {
                LOG.debug("[D] getHistorialFormacion, finalizada " + ret_historFormService.toString());
            }
        }
        return ret_historFormService;
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
            CustomServiceTracker<EmpleadoBancoService> empleadoBancoServiceTracker = new CustomServiceTracker<>(EmpleadoBancoService.class, "getEmpleadoBancoService");
            CustomServiceTracker<NecesidadesFormacionService> necesidadesFormacionServiceTracker = new CustomServiceTracker<>(NecesidadesFormacionService.class, "getNecesidadesFormacionService");
            CustomServiceTracker<CalendarioEventosService> calendarioEventosServiceTracker = new CustomServiceTracker<>(CalendarioEventosService.class, "getCalendarioEventosService");
            CustomServiceTracker<EmpleadoActDatosPersonalesService> empleadoActDatosPersonalesServiceServiceTracker = new CustomServiceTracker<>(EmpleadoActDatosPersonalesService.class, "getEmpleadoActDatosPersonalesService");
            CustomServiceTracker<EmpleadoEstructuraService> empleadoEstructuraServiceTracker = new CustomServiceTracker<>(EmpleadoEstructuraService.class, "getEmpleadoEstructuraService");
            CustomServiceTracker<HistorFormService> historFormServiceTracker = new CustomServiceTracker<>(HistorFormService.class, "getHistorFormService");


            this._customExpandoUtil = (CustomExpandoUtil) ServiceLocator.getInstance().findService("es.emasesa.intranet.base.util.CustomExpandoUtil");
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
            this._empleadoBancoService = empleadoBancoServiceTracker.getService();
            this._necesidadesFormacionService = necesidadesFormacionServiceTracker.getService();
            this._calendarioEventosService = calendarioEventosServiceTracker.getService();
            this._empleadoActDatosPersonalesService = empleadoActDatosPersonalesServiceServiceTracker.getService();
            this._empleadoEstructuraService = empleadoEstructuraServiceTracker.getService();
            this._historFormService = historFormServiceTracker.getService();
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
            LoggerUtil.info(LOG, "Error arrancando servicios", e);
       /*} catch (SapException e) {
            LOG.info("Se ha producido un error validando los servicios en el arranque", e);
        */
        } catch (NullPointerException e) {
            LOG.info("Se ha producido un error accediendo a los servicios");
        }
    }

    @Reference
    private UserLocalService _userLocalService;
    @Reference
    private ObjectEntryLocalService _objectEntryLocalService;
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
    private EmpleadoBancoService _empleadoBancoService;
    private RelacionLaboralService _relacionLaboralService;
    private NecesidadesFormacionService _necesidadesFormacionService;
    private CalendarioEventosService _calendarioEventosService;
    private EmpleadoActDatosPersonalesService _empleadoActDatosPersonalesService;
    private CustomExpandoUtil _customExpandoUtil;
    @Reference
    private ExpandoValueLocalService _expandoValueLocalService;
    private EmpleadoEstructuraService _empleadoEstructuraService;
    private HistorFormService _historFormService;
    private static final Log LOG = LogFactoryUtil.getLog(SapServicesUtil.class);
}
