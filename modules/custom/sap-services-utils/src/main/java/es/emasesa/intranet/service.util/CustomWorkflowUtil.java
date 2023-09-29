package es.emasesa.intranet.service.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil;
import com.liferay.portal.workflow.comparator.WorkflowComparatorFactory;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalServiceUtil;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;
import es.emasesa.intranet.jornada.nomina.exception.SapException;
import es.emasesa.intranet.jornada.nomina.proxy.SapInterfaceService;
import es.emasesa.intranet.jornada.nomina.service.EmpleadoEstructuraService;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomWorkflowUtil.class
)
public class CustomWorkflowUtil {

    public List<User> assignWorkflowUser(Map<String, Serializable> workflowContext, long userId) {
        List<User> users = new ArrayList<>();
        long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
        //TODO - get user screen from service
        String screenName = "workflow-user";

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            User user = _userLocalService.getUser(userId);

            ClassLoader objectFactoryClassLoader = SapInterfaceService.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            JSONObject json = empleadoEstructuraService.getEmpleadoEstructura(user.getScreenName());
            Thread.currentThread().setContextClassLoader(actualClassLoader);

            System.out.println(json.toJSONString());
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

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        CustomServiceTracker<EmpleadoEstructuraService> service = new CustomServiceTracker<>(EmpleadoEstructuraService.class, "getEmpleadoEstructuraService");

        try {
            this.empleadoEstructuraService = service.getService();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Reference
    private UserLocalService _userLocalService;
    @Reference
    SapInterfaceService _sapService;
    private EmpleadoEstructuraService empleadoEstructuraService;

    private static final Log LOG = LogFactoryUtil.getLog(CustomWorkflowUtil.class);

}
