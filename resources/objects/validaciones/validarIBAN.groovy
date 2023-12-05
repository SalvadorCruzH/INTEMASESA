import com.liferay.portal.template.*;
import com.liferay.portal.util.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;
import es.emasesa.intranet.base.util.CustomObjectUtil;

Bundle bundleObject = FrameworkUtil.getBundle(CustomObjectUtil.class);
ServiceTracker customObjectUtilTracker = new ServiceTracker(bundleObject.getBundleContext(),CustomObjectUtil.class, null);
customObjectUtilTracker.open();
CustomObjectUtil customObjectUtil = customObjectUtilTracker.getService();

boolean isValidIBAN = customObjectUtil.validarIBAN(iBAN);

if(isValidIBAN){
	invalidFields = false;
	return invalidFields;
}else{
	invalidFields = true;
	return invalidFields;
}

customObjectUtilTracker.close();