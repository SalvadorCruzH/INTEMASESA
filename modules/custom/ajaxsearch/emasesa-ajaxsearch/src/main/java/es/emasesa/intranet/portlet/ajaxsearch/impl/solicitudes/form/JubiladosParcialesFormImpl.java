package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.form;

import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component(
        immediate = true,
        property = {},
        service = AjaxSearchForm.class
)
public class JubiladosParcialesFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(JubiladosParcialesFormImpl.class);

    public static final String LISTA_ESTADO_OBJETO_ID = "lista-estado-objeto-id";

    static {
        DFLT_PROPERTIES.put(LISTA_ESTADO_OBJETO_ID, "-1");
    }

    @Override
    public Properties getDefaultProperties() {
        return DFLT_PROPERTIES;
    }

    @Override
    public boolean inProcessAction(ActionRequest actionRequest, ActionResponse response,
                                   AjaxSearchDisplayContext searchDisplayContext) {
        return Boolean.TRUE;
    }

    private static final String VIEW = "/views/solicitudes/jubiladosparciales/form.jsp";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {
        String listaEstadoId = ajaxSearchDisplayContext.getConfig().get(LISTA_ESTADO_OBJETO_ID);
        List<ListTypeEntry> listaEstados = new ArrayList<>();
        if(Validator.isNumber(listaEstadoId) && Long.parseLong(listaEstadoId) > 0){
            listaEstados = listTypeEntryLocalService.getListTypeEntries(Long.parseLong(listaEstadoId));
        }

        request.setAttribute("listadoEstados", listaEstados);
        request.setAttribute(AjaxSearchPortletKeys.ESTADO, ajaxSearchDisplayContext.getLong(AjaxSearchPortletKeys.ESTADO));
        return VIEW;
    }

    @Reference
    ListTypeEntryLocalService listTypeEntryLocalService;
}
