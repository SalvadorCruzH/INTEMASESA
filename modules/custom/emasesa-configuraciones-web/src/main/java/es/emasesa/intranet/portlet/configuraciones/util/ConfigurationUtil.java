package es.emasesa.intranet.portlet.configuraciones.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
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
        service = ConfigurationUtil.class
)
public class ConfigurationUtil {
    public JSONArray getJSONArrayValue(String pid, String configKey){
        String value = "";
        JSONArray data;
        try {
            Configuration configuration = configurationAdmin.getConfiguration(pid, null);
            Dictionary<String, Object> properties = configuration.getProperties();
            if (Validator.isNotNull(properties)) value = properties.get(configKey).toString();
            data = JSONFactoryUtil.createJSONArray(value);


        } catch (IOException | JSONException e) {
            data = JSONFactoryUtil.createJSONArray();
            LoggerUtil.error(LOG, e);
        }

        return data;
    }

    public boolean setJSONArrayValue(String pid, String key,JSONArray jsonArray){
        boolean updated = false;

        try {
            Configuration configuration = configurationAdmin.getConfiguration(pid, null);
            Dictionary<String, Object> properties = configuration.getProperties();
            properties.put(key, jsonArray.toString());
            configuration.update(properties);
            updated = true;
        } catch (IOException e) {
            LoggerUtil.error(LOG, e);
        }

        return updated;
    }

    @Reference
    private ConfigurationAdmin configurationAdmin;

    private final static Log LOG = LoggerUtil.getLog(ConfigurationUtil.class);
}
