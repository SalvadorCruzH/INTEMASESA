package es.emasesa.intranet.service.util.templatecontributor;

import com.liferay.portal.kernel.template.TemplateContextContributor;
import es.emasesa.intranet.service.util.SapServicesUtil;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {"type=" + TemplateContextContributor.TYPE_GLOBAL},
        service = TemplateContextContributor.class
)
public class ThemeContextContributor implements TemplateContextContributor {

    @Override
    public void prepare(Map<String, Object> contextObjects, HttpServletRequest httpServletRequest) {

        contextObjects.put("sapServiceUtil", _sapServiceUtil);
    }

    @Reference
    private SapServicesUtil _sapServiceUtil;
}
