
package es.emasesa.intranet.gogo.actions;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import es.emasesa.intranet.gogo.base.GogoConstant;
import es.emasesa.intranet.gogo.base.JSONFileUtil;
import es.emasesa.intranet.gogo.importer.ExpandoImporterUtil;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"key=application.startup.events"
	},
	service = LifecycleAction.class
)
public class StartUpAction extends SimpleAction {

	private static Log LOG = LogFactoryUtil.getLog(StartUpAction.class);
	
	@Override
	public void run(final String[] ids) throws ActionException {

		boolean validate=Boolean.TRUE;
        final JSONArray jsonArray = JSONFileUtil.getJSONArrayFromFile(FrameworkUtil.getBundle(this.getClass()).getEntry(GogoConstant.CONFIG_PATH_EXPANDO));
        LOG.debug( " -- RUNNING EXPANDO IMPORT -- ");
        for (int i=0;i<jsonArray.length();i++){
            try {
                validate &= ExpandoImporterUtil.importExpando(jsonArray.getJSONObject(i), Boolean.FALSE, Boolean.FALSE);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
	}
}