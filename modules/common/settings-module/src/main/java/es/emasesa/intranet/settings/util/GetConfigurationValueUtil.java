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
import java.util.*;

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

    public List<Map<String, String>> getMapValue(String pid, String configKey){
        String value = "";
        List<Map<String, String>> data = new ArrayList<>();
        try {
            Configuration configuration = configurationAdmin.getConfiguration(pid, null);
            Dictionary<String, Object> properties = configuration.getProperties();
            if (Validator.isNotNull(properties)) value = properties.get(configKey).toString();
            value = value.replace("\n", "").replace("\r", "");
         String[] lines = value.split(";");

            String[] headers = lines[0].split(",");

            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split(",");
                Map<String, String> row = new HashMap<>();

                for (int j = 0; j < headers.length; j++) {
                    String key = headers[j];
                    String val = j < values.length ? values[j] : "";
                    row.put(key, val);
                }

                data.add(row);
            }

        } catch (IOException e) {
            LoggerUtil.error(LOG, e);
        }

        return data;
    }

    @Reference
    private ConfigurationAdmin configurationAdmin;

    private static final Log LOG = LogFactoryUtil.getLog(GetConfigurationValueUtil.class);
}
