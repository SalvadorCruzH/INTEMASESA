import com.liferay.portal.template.*;
import com.liferay.portal.util.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import es.emasesa.intranet.base.util.*;

Bundle bundle = FrameworkUtil.getBundle(CustomWorkflowUtil.class);
ServiceTracker customWorkflowUtilTracker = new ServiceTracker(bundle.getBundleContext(),CustomWorkflowUtil.class, null);

customWorkflowUtilTracker.open();
CustomWorkflowUtil customWorkflowUtil = customWorkflowUtilTracker.getService();
users = customWorkflowUtil.saveObjectOnSIGD(workflowContext);
customWorkflowUtil.setObjectStatus(workflowContext, status);
customWorkflowUtilTracker.close();