import com.liferay.portal.template.*;
import com.liferay.portal.util.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import es.emasesa.intranet.service.util.CustomWorkflowUtil;

Bundle bundle = FrameworkUtil.getBundle(CustomWorkflowUtil.class);
ServiceTracker customWorkflowUtilTracker = new ServiceTracker(bundle.getBundleContext(),CustomWorkflowUtil.class, null);

customWorkflowUtilTracker.open();
CustomWorkflowUtil customWorkflowUtil = customWorkflowUtilTracker.getService();
//Llamada a SAP para enviar el nuevo iban
String datosService = sapCustomWorkflowUtil.cambioDomiciliacionBancaria(workflowContext);
customWorkflowUtilTracker.close();