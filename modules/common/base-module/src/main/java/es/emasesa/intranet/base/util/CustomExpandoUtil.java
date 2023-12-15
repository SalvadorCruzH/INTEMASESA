package es.emasesa.intranet.base.util;

import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {
        },
        service = CustomExpandoUtil.class)
public class CustomExpandoUtil {

    @Reference
    ExpandoTableLocalService _expandoTableLocalService;

    @Reference
    ExpandoColumnLocalService _expandoColumnLocalService;
    
    @Reference
    private ExpandoValueLocalService _expandoValueLocalService;

    public boolean createExpando(final long companyId,
                                  final String tableClassName,
                                  final String columnName,
                                  final int columnType) {
        boolean createExpando = Boolean.FALSE;

        LoggerUtil.info(_log, "Creating expando ["+tableClassName+"]:"+columnName);

        try {
            final ExpandoTable table = createExpandoTable(companyId, tableClassName);
            if (Validator.isNotNull(table)) {
                final ExpandoColumn column = createExpandoColumn(
                        table,
                        columnName,
                        columnType);
                createExpando = Validator.isNotNull(column);
            }
        }catch (Exception e) {
            LoggerUtil.error(_log, e);
        }

        return createExpando;
    }

    public ExpandoTable createExpandoTable(final long companyId, final String tableClassName) throws PortalException {
        ExpandoTable table;
        try {
            table = _expandoTableLocalService.getDefaultTable(
                    companyId,
                    tableClassName);
        } catch (NoSuchTableException nste) {
            table = _expandoTableLocalService.addDefaultTable(
                    companyId,
                    tableClassName);
        }
        return table;
    }

    public static ExpandoTable createExpandoTableStatic(final long companyId, final String tableClassName) throws PortalException {
        ExpandoTable table;
        try {
            table = ExpandoTableLocalServiceUtil.getDefaultTable(
                    companyId,
                    tableClassName);
        } catch (NoSuchTableException nste) {
            table = ExpandoTableLocalServiceUtil.addDefaultTable(
                    companyId,
                    tableClassName);
        }
        return table;
    }

    public ExpandoColumn createExpandoColumn(final ExpandoTable table,
                                              final String columnName,
                                              final int columnType) throws PortalException {
        ExpandoColumn column;
        final long tableId = table.getTableId();
        try {
            column = _expandoColumnLocalService.addColumn(
                    tableId,
                    columnName,
                    columnType);
        } catch(DuplicateColumnNameException dcne) {
            column = ExpandoColumnLocalServiceUtil.getColumn(tableId, columnName);
        }
        return column;
    }
    
    /**
     * Obtener el classPk de un expando por su valor
     * @param companyId
     * @param columnName
     * @param columnValue
     * 
     * @return classPK
     */
    public long getClassPKByExpandoValue(long companyId, String columnName, String columnValue) {
    	long classPK = 0;
    	
    	 try {
    		 _log.debug("Buscando expando values para la columna: " + columnName);
	    	ExpandoTable expandoTable = _expandoTableLocalService.getTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
	    	_log.debug("Obtenido el expando table: " + expandoTable.getTableId());
	        List<ExpandoValue> expandoValues = _expandoValueLocalService.getColumnValues(
	            expandoTable.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, columnName, -1, -1);
	        
	        if(Validator.isNotNull(expandoValues) && expandoValues.size() > 0) {
	        	_log.debug("Encontrado valores para el expando: " + columnName);
	        	 for (ExpandoValue expandoValue : expandoValues) {
	                 if (columnValue.equals(expandoValue.getData())) {
	                	 _log.debug("Encontrado un expando con el valor: " + columnValue);
	                 	classPK = expandoValue.getClassPK();
	                 	_log.debug("Se obtiene el classPK: " + classPK);
	                 	return classPK;
	                 }
	             }
	        }else {
	        	_log.debug("No se han obtenido valores para el expando: " + columnName);
	        }
         } catch (PortalException e) {
        	 _log.error("La tabla de expando no existe", e);
		}   
        return classPK;
    }
    
    /**
     * Recupera un usuario por su valor
     * @param companyId
     * @param expandoName
     * @param expandoValue
     * 
     * @return user
     */
    public User getUserByExpandoValue(long companyId, String expandoName, String expandoValue) {
        User user = null;
        try {
        	_log.debug("Buscando usuario por expando valor: " + expandoValue );
	        long userId = getClassPKByExpandoValue(companyId, expandoName, expandoValue);
	        if(Validator.isNotNull(userId)) {
	        	_log.debug("Obtenido el userId: " + userId);
		        user = UserLocalServiceUtil.getUser(userId);
		        _log.debug("Encontrado usuario con el valor: " + expandoValue + ". El usuario es: " + user.getScreenName());
	        }else {
	        	_log.debug("No se ha encontrado ning�n usuario con el valor: " + expandoValue);
	        }
        } catch (PortalException e) {
        	_log.error("Error al obtener el usuario", e);
		}
        return user;
    }
    
    
    /**
     * Recupera el valor de un campo expando dado un usuario
     * @param userId
     * @param companyId
     * @param expandoName
     * 
     * @return expandoValue
     */
    public String getDataValueByUser (long userId, long companyId, String expandoName) {
    	String expandoValue = StringPool.BLANK;
    	 try {
    		 _log.debug("Obteniendo el valor del expando: " + expandoName + " para el usuario: " +  userId);
    		 expandoValue = _expandoValueLocalService.getData(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, expandoName, userId, StringPool.BLANK);
    		 _log.debug("El valor obtenido es: " + expandoValue);
    	 } catch (PortalException e) {
    		 _log.error("Se ha producido un error al intentar obtener el valor del expando: " + expandoName);
    	 }
    	return expandoValue;
    }


    Log _log = LoggerUtil.getLog(CustomExpandoUtil.class);
}
