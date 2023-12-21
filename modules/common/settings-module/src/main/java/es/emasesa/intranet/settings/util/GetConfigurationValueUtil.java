package es.emasesa.intranet.settings.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.LoggerUtil;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


import java.io.IOException;
import java.util.Dictionary;

@Component(
    immediate = true,
    service = GetConfigurationValueUtil.class
)
public class GetConfigurationValueUtil {

    public String getStringValue(String pid, String configKey){
        String value = "";
        try {
            Configuration configuration = configurationAdmin.getConfiguration(pid, null);
            Dictionary<String, Object> properties = configuration.getProperties();
            if (Validator.isNotNull(properties)) value = properties.get(configKey).toString();


        } catch (IOException e) {
            LoggerUtil.error(LOG, e);
        }

        return value;
    }

    @Reference
    private ConfigurationAdmin configurationAdmin;

    private static final Log LOG = LogFactoryUtil.getLog(GetConfigurationValueUtil.class);
}
