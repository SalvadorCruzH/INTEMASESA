package es.emasesa.intranet.base.util;

import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.Validator;
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


    Log _log = LoggerUtil.getLog(CustomExpandoUtil.class);
}
