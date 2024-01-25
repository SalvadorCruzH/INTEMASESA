package es.emasesa.intranet.template.contributor;

import com.liferay.portal.kernel.template.TemplateContextContributor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.favoritos.service.EmasesaFavoritosService;


@Component(
        immediate = true,
        property = {"type=" + TemplateContextContributor.TYPE_GLOBAL},
        service = TemplateContextContributor.class
)
public class MacroContextContributor implements TemplateContextContributor {

    @Override
    public void prepare(Map<String, Object> contextObjects, HttpServletRequest request) {
        contextObjects.put("emasesaFavoritosService", _emasesaFavoritosService);
    }

    @Reference
    EmasesaFavoritosService _emasesaFavoritosService;
}