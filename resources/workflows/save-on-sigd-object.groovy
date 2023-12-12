import com.liferay.portal.template.*;
import com.liferay.portal.util.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import es.emasesa.intranet.base.util.CustomWorkflowBaseUtil;
import es.emasesa.intranet.sigd.service.application.SigdServiceApplication;

Bundle bundleWorkflow = FrameworkUtil.getBundle(CustomWorkflowBaseUtil.class);
ServiceTracker customWorkflowBaseUtilTracker = new ServiceTracker(bundleWorkflow.getBundleContext(),CustomWorkflowBaseUtil.class, null);
customWorkflowBaseUtilTracker.open();
CustomWorkflowBaseUtil customWorkflowBaseUtil = customWorkflowBaseUtilTracker.getService();

Bundle bundleSigd = FrameworkUtil.getBundle(SigdServiceApplication.class);
ServiceTracker sigdServiceTracker = new ServiceTracker(bundleSigd.getBundleContext(),SigdServiceApplication.class, null);
sigdServiceTracker.open();
SigdServiceApplication sigdService = sigdServiceTracker.getService();

customWorkflowBaseUtil.updateObjectHistoryAndStatus(workflowContext, statusObject, userId, rolName);
String pdf = customWorkflowBaseUtil.pdfBase64(customWorkflowBaseUtil.createPDF(workflowContext));
String idDocumentoSigd = sigdService.saveDocumentOnSIGD(pdf, objectName, documentType);

customWorkflowBaseUtilTracker.close();
sigdServiceTracker.close();