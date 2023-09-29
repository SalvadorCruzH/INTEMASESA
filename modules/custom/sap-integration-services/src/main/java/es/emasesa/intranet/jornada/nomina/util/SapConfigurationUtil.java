package es.emasesa.intranet.jornada.nomina.util;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import es.emasesa.intranet.settings.configuration.SapServicesConfiguration;

@org.springframework.stereotype.Component("sapConfigurationUtil")
public class SapConfigurationUtil {

    public SapServicesConfiguration getConfiguration() throws ConfigurationException {
        SapServicesConfiguration configuration =  ConfigurationProviderUtil.getSystemConfiguration(SapServicesConfiguration.class);
        return configuration;
    }


}
