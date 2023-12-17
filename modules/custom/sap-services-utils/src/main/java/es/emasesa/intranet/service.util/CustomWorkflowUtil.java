package es.emasesa.intranet.service.util;

import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import es.emasesa.intranet.sap.base.exception.SapException;
import es.emasesa.intranet.sap.nomina.service.JornadaNominaService;
import es.emasesa.intranet.sap.proxy.SapInterfaceService;
import es.emasesa.intranet.sap.estructura.service.EmpleadoEstructuraService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.sap.subordinados.service.CiertosDatosEstructuraService;

@Component(
        immediate = true,
        service = CustomWorkflowUtil.class
)
public class CustomWorkflowUtil {
    /**
     * Retrive employee ID from the soapService
     * @param workflowContext
     * @employeeType employeeType
     * @return users
     */
    public List<User> assignWorkflowUser(Map<String, Serializable> workflowContext, long userId, String employeeType) {
        List<User> users = new ArrayList<>();
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
        String matriculaSAPUser = StringPool.BLANK;
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        User user = null;
        try {
            String matriculaActualUser  = customExpandoUtil.getDataValueByUser(userId, companyId, "matricula");
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            JSONObject json = empleadoEstructuraService.getEmpleadoEstructura(matriculaActualUser);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
            matriculaSAPUser = json.getString(employeeType);
            LOG.debug("Tipo de empleado: "+employeeType);
            LOG.debug("Nombre del empleado: "+matriculaSAPUser);
            user = customExpandoUtil.getUserByExpandoValue(companyId, "matricula", matriculaSAPUser);
            if(Validator.isNotNull(user)) {
                LOG.debug("Se ha encontrado en Liferay un usuario con la matricula: " + matriculaSAPUser);
                users.add(user);
            }else {
                LOG.debug("No existe en Liferay un usuario con la matricula: " + matriculaSAPUser);
            }
        } catch (SapException e) {
            LOG.error("Se ha producido un error a la hora de obtener la estructura del usuario "+matriculaSAPUser, e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return users;
    }

    /**
     * Devuelve usuarios de SAP consejeroId, direccionRrhhRespId, divisionRrhhRespId o subdireccionRrhhRespId
     * @param workflowContext
     * @param userType
     * @return user
     */
    public User getUserSap(Map<String, Serializable> workflowContext, String userType) {
        User user = null;
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
        String matriculaUser = StringPool.BLANK;

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            JSONObject json = ciertosDatosEstructuraService.getCiertosDatosEstructura();
            Thread.currentThread().setContextClassLoader(actualClassLoader);

            matriculaUser = json.getString(userType);
            LOG.debug("La matrÃ­cula del usuario es: " + matriculaUser);
            user = customExpandoUtil.getUserByExpandoValue(companyId, "matricula", matriculaUser);

        } catch (SapException e) {
            LOG.error("Se ha producido un error a la hora de obtener la estructura del usuario "+matriculaUser, e);

        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }
        return user;
    }


    public String modificarIRPF(Map<String, Serializable> workflowContext) {
        String i = "";
        String pernr = "";
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            String fechaSolicitud = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("fechaSolicitud");
            double IRPF_Solicitado = (double) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("retencinDelIRPFSolicitada");
            pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("nmeroDeMatricula");

            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            i = jornadaNominaService.guardarIRPF(pernr, fechaSolicitud, IRPF_Solicitado);
            LOG.debug("Se ha guardado el cambio de IRPF: "+IRPF_Solicitado + " para el usuario " +pernr);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        } catch (PortalException e) {
            LOG.error("Se ha producido un error al modificar IRPF para "+ pernr, e);
        }

        return i;
    }

    /**
     * Cambio de domiciliación Bancaria de un empleado. Deberá rellenarse el campo de entrada IBAN, con la matricula del empleado y la fecha de solicitud
     *
     * @param workflowContext
     * @return datosServicio
     */
    public String cambioDomiciliacionBancaria(Map<String, Serializable> workflowContext) {
        String datosServicio = StringPool.BLANK;
        String pernr = StringPool.BLANK;
        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            LOG.debug("Se procede con el cambio de domiciliacion bancaria...");
            long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
            String fechaSolicitud = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("createDate");
            String iban = (String)_objectEntryLocalService.getObjectEntry(classPK).getValues().get("iBAN");
            pernr = (String) _objectEntryLocalService.getObjectEntry(classPK).getValues().get("numeroDeMatricula");
            LOG.debug("fechaSolicitud: " + fechaSolicitud);
            LOG.debug("iban: " + iban);
            LOG.debug("pernr: " + pernr);

            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            datosServicio = jornadaNominaService.cambioDomiciliacionBancaria(pernr, fechaSolicitud, iban);
            LOG.debug("Se ha guardado el cambio de iban: "+iban + " para el usuario " +pernr);
            LOG.debug("Los datosServicio son: " + datosServicio);
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        } catch (PortalException e) {
            LOG.error("Se ha producido un error al modificar de cambio de domiciliacion bancaria para "+ pernr, e);
        }

        return datosServicio;
    }
    

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        CustomServiceTracker<EmpleadoEstructuraService> service = new CustomServiceTracker<>(EmpleadoEstructuraService.class, "getEmpleadoEstructuraService");
        CustomServiceTracker<JornadaNominaService> serviceNomina = new CustomServiceTracker<>(JornadaNominaService.class, "getJornadaNominaService");

        try {
            this.empleadoEstructuraService = service.getService();
            this.jornadaNominaService = serviceNomina.getService();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private CustomExpandoUtil customExpandoUtil;
    @Reference
    private UserLocalService _userLocalService;
    @Reference
    SapInterfaceService _sapService;
    private EmpleadoEstructuraService empleadoEstructuraService;
    private CiertosDatosEstructuraService ciertosDatosEstructuraService;
    @Reference
    ObjectEntryLocalService _objectEntryLocalService;
    private JornadaNominaService jornadaNominaService;

    private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);
}