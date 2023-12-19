import com.liferay.portal.template.*;
import com.liferay.portal.util.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import es.emasesa.intranet.base.util.CustomWorkflowBaseUtil;

Bundle bundle = FrameworkUtil.getBundle(CustomWorkflowBaseUtil.class);
ServiceTracker customWorkflowUtilTracker = new ServiceTracker(bundle.getBundleContext(),CustomWorkflowBaseUtil.class, null);

customWorkflowUtilTracker.open();
CustomWorkflowUtil customWorkflowUtil = customWorkflowUtilTracker.getService();
customWorkflowUtil.enviarPortalFirmas(workflowContext, kaleoTaskInstanceToken, userId, "consejeroId");
customWorkflowUtilTracker.close();
