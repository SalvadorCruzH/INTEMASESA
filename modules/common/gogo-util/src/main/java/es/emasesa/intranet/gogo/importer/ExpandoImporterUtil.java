package es.emasesa.intranet.gogo.importer;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.base.util.CustomGetterUtil;
import es.emasesa.intranet.gogo.base.CustomGogoLogger;
import es.emasesa.intranet.gogo.model.JsonExpando;

public class ExpandoImporterUtil {
	
	private static Log logger = LogFactoryUtil.getLog(ExpandoImporterUtil.class);

    public static boolean importExpando(final JSONObject expando, final boolean override, final boolean gogoLog){

        final JsonExpando jsonExpando = new JsonExpando(expando);

        final ServiceContext serviceContext = new ServiceContext();
        serviceContext.setAddGroupPermissions(Boolean.TRUE);
        serviceContext.setAddGuestPermissions(Boolean.FALSE);

        // Get ENV config
        final Company company = CustomGetterUtil.getCompanyByWebIdStatic(jsonExpando.getCompanyWebId());
        final long companyId = company.getCompanyId();

        final String className = jsonExpando.getClassName();
        final String expandoName = jsonExpando.getExpandoName();
        boolean updated=Boolean.FALSE;
        if(Validator.isNotNull(ClassNameLocalServiceUtil.fetchClassName(jsonExpando.getClassName()))) {
	        try {
	        	logger.info("[Expando] Importing "+ expandoName +" .");
				CustomGogoLogger.gogoPrintln("[Expando] Importing "+ expandoName +" .", gogoLog);

	            final ExpandoTable classDefaultTable = CustomExpandoUtil.createExpandoTableStatic(companyId, className);
				ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(classDefaultTable.getTableId(), expandoName);
	
				if(Validator.isNull(expandoColumn)) {
					logger.info("[Expando] Creating "+ expandoName +" .");
					CustomGogoLogger.gogoPrintln("[Expando] Creating "+ expandoName +" .", gogoLog);
		            expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(classDefaultTable.getTableId(), expandoName, jsonExpando.getType());

					expandoColumn.setDefaultData(jsonExpando.getDefaultDataAsString());
					expandoColumn.setTypeSettings(jsonExpando.getTypeSettings());
					updated = Validator.isNotNull(ExpandoColumnLocalServiceUtil.updateExpandoColumn(expandoColumn));
				} else {
					updated = isNotNullExpando(override, gogoLog, jsonExpando, expandoName, updated, expandoColumn);
				}
			
	        } catch (Exception e){
	        	logger.warn("[Expando] Error importing "+expandoName+" : "+e.getMessage());
				CustomGogoLogger.gogoPrintln("[Expando] Error importing "+expandoName+" : "+e.getMessage(), gogoLog);
	            e.printStackTrace();
	        }
        }
        return updated;
    }

	private static boolean isNotNullExpando(boolean override, boolean gogoLog, JsonExpando jsonExpando, String expandoName, boolean updated, ExpandoColumn expandoColumn) {
		if (override) {
			logger.info("[Expando] Overriding "+ expandoName +" .");
			CustomGogoLogger.gogoPrintln("[Expando] Overriding "+ expandoName +" .", gogoLog);

			expandoColumn.setType(jsonExpando.getType());
			expandoColumn.setDefaultData(jsonExpando.getDefaultDataAsString());
			expandoColumn.setTypeSettings(jsonExpando.getTypeSettings());

			updated = Validator.isNotNull(ExpandoColumnLocalServiceUtil.updateExpandoColumn(expandoColumn));
		} else {
			logger.info("[Expando] Override = false .");
			CustomGogoLogger.gogoPrintln("[Expando] Override = false .", gogoLog);
			updated = Boolean.TRUE;
		}
		return updated;
	}

}
