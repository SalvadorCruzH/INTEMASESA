package es.emasesa.intranet.service.util;

import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
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

@Component(
        immediate = true,
        service = CustomWorkflowUtil.class
)
public class CustomWorkflowUtil {
    /**
     * Retrive employee ID from the soapService
     * @param workflowContext
     * @param userId
     * @employeeType employeeType
     * @return
     */
    public List<User> assignWorkflowUser(Map<String, Serializable> workflowContext, long userId, String employeeType) {
        List<User> users = new ArrayList<>();
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));

        String screenName = ""; //TODO - think on a default user, when the service fail

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            User user = _userLocalService.getUser(userId);

            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            JSONObject json = empleadoEstructuraService.getEmpleadoEstructura(user.getScreenName());
            Thread.currentThread().setContextClassLoader(actualClassLoader);
            screenName = json.getString(employeeType);
            LOG.debug("Tipo de empleado: "+employeeType);
            LOG.debug("Nombre del empleado: "+screenName);
            users.add(_userLocalService.getUserByScreenName(companyId, screenName));
        } catch (SapException e) {
            LOG.error("Se ha producido un error a la hora de obtener la estructura del usuario "+screenName, e);
        } catch (PortalException e) {
            LOG.error("Se ha producido un error a general para "+screenName, e);
        } finally {
            Thread.currentThread().setContextClassLoader(actualClassLoader);
        }

        return users;
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


    @Reference
    private UserLocalService _userLocalService;
    @Reference
    SapInterfaceService _sapService;
    private EmpleadoEstructuraService empleadoEstructuraService;
    @Reference
    ObjectEntryLocalService _objectEntryLocalService;
    private JornadaNominaService jornadaNominaService;

    private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);
}
