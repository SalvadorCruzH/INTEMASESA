package es.emasesa.intranet.gogo.util;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {},
        service = ClientExtensionCleanDatabase.class
)
public class ClientExtensionCleanDatabase {

    @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
    private ModuleServiceLifecycle _portalInitialized;

    @Activate
    public void doCleanClientExtensionFromDatabase() {

        LOG.info("[I] PORTAL_INITIALIZED");
        boolean result = executeSQL("delete from Configuration_ where configurationId = 'com.liferay.client.extension.type.configuration.CETConfiguration~administrador-tareas-module'");
        LOG.info("Borrado de Module " + result);
        result = executeSQL("delete from Configuration_ where configurationId = 'com.liferay.client.extension.type.configuration.CETConfiguration~global-module'");
        LOG.info("Borrado de Global " + result);
        result = executeSQL("delete from Configuration_ where configurationId = 'com.liferay.client.extension.type.configuration.CETConfiguration~global-object-module'");
        LOG.info("Borrado de Global Objetos " + result);
        result = executeSQL("delete from Configuration_ where configurationId = 'com.liferay.client.extension.type.configuration.CETConfiguration~notifications-module'");
        LOG.info("Borrado de Notifications " + result);
        LOG.info("[E] PORTAL_INITIALIZED");
    }

    private boolean executeSQL(String sql) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataAccess.getConnection();
            sql = PortalUtil.transformSQL(sql);
            ps = con.prepareStatement(sql);
            return ps.execute();
        } catch (Exception e) {
            LOG.error(e);
        } finally {
            DataAccess.cleanUp(con, ps, rs);
        }
        return false;
    }

    private static final Log LOG = LogFactoryUtil.getLog(
            ClientExtensionCleanDatabase.class);

}