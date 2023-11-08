import com.liferay.portal.template.*;
import com.liferay.portal.util.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import es.emasesa.intranet.base.util.CustomWorkflowUtil;
import es.emasesa.intranet.sigd.service.application.SigdServiceApplication;

Bundle bundleWorkflow = FrameworkUtil.getBundle(CustomWorkflowUtil.class);
ServiceTracker customWorkflowUtilTracker = new ServiceTracker(bundleWorkflow.getBundleContext(),CustomWorkflowUtil.class, null);
customWorkflowUtilTracker.open();
CustomWorkflowUtil customWorkflowUtil = customWorkflowUtilTracker.getService();

Bundle bundleSigd = FrameworkUtil.getBundle(SigdServiceApplication.class);
ServiceTracker sigdServiceTracker = new ServiceTracker(bundleSigd.getBundleContext(),SigdServiceApplication.class, null);
sigdServiceTracker.open();
SigdServiceApplication sigdService = sigdServiceTracker.getService();

customWorkflowUtil.updateObjectHistoryAndStatus(workflowContext, statusObject, userId, rolName);
String pdf = customWorkflowUtil.getDocumentToLoad(workflowContext, nombreDocumentoCampoObject);
if(pdf != null && pdf != ""){
	String idDocumento = sigdService.saveDocumentOnSIGD(pdf, objectName, documentType);
}


customWorkflowUtilTracker.close();
sigdServiceTracker.close();