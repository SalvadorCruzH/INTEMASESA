package es.emasesa.intranet.settings.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;
import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationCategory.class)
public class CustomConfigurationCategory implements ConfigurationCategory {

    @Override
    public String getCategoryIcon() {
        return CATEGORY_ICON;
    }

    @Override
    public String getCategoryKey() {
        return CATEGORY_KEY;
    }

    @Override
    public String getCategorySection() {
        return CATEGORY_SECTION;
    }

    private static final String CATEGORY_ICON = "cog";
    private static final String CATEGORY_KEY = "emasesa-base";
    private static final String CATEGORY_SECTION = "emasesa";
}