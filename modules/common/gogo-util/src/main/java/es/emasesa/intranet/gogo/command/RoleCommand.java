package es.emasesa.intranet.gogo.command;

import com.liferay.portal.kernel.json.JSONArray;
import es.emasesa.intranet.gogo.base.CustomGogoLogger;
import es.emasesa.intranet.gogo.base.GogoConstant;
import es.emasesa.intranet.gogo.base.JSONFileUtil;
import es.emasesa.intranet.gogo.importer.RoleImporter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true,
        property = {
                "osgi.command.scope=goRole",
                "osgi.command.function=importAll"
        },
        service = Object.class)
public class RoleCommand {

    public void importAll(){
        boolean validate=Boolean.TRUE;
        CustomGogoLogger.gogoPrintln( "<h3> -- LOADING CONFIG -- </h3>");
        final JSONArray jsonArray = JSONFileUtil.getJSONArrayFromFile(FrameworkUtil.getBundle(this.getClass()).getEntry(GogoConstant.CONFIG_PATH_ROLE));
        CustomGogoLogger.gogoPrintln(jsonArray.toString());
        CustomGogoLogger.gogoPrintln( "<h3> -- RUNNING IMPORT -- </h3>");

        for (int i=0;i<jsonArray.length();i++){
            try {
                validate &= _roleImporter.importRole(jsonArray.getJSONObject(i));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        CustomGogoLogger.gogoPrintln(validate?" -- IMPORT OK --":" -- IMPORT ERROR -- ");

    }

    @Reference
    RoleImporter _roleImporter;
}