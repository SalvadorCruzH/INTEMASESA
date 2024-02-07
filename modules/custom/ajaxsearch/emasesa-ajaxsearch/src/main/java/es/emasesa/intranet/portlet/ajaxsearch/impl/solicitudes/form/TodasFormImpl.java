package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.form;

import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalServiceUtil;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.list.type.service.ListTypeEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.list.type.ListEntry;
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
public class TodasFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(TodasFormImpl.class);

    public static final String CATEGORY_ID = "category-id";
    public static final String LISTA_ESTADO_OBJETO_ID = "lista-estado-objeto-id";
    public static final String ESTADO_SELECTED = "estadoSelected";

    static {
        DFLT_PROPERTIES.put(CATEGORY_ID, "-1");
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

    private static final String VIEW = "/views/solicitudes/form.jsp";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {
        String listaEstadoId = ajaxSearchDisplayContext.getConfig().get(LISTA_ESTADO_OBJETO_ID);
        List<ListTypeEntry> listaEstados = new ArrayList<>();
        if(Validator.isNumber(listaEstadoId)){
            listaEstados = listTypeEntryLocalService.getListTypeEntries(Long.parseLong(listaEstadoId));
        }
        listaEstados.get(0).getKey();

        request.setAttribute("listadoEstados", listaEstados);
        request.setAttribute(AjaxSearchPortletKeys.ESTADO, ajaxSearchDisplayContext.getLong(AjaxSearchPortletKeys.ESTADO));
        return VIEW;
    }

    @Reference
    ListTypeEntryLocalService listTypeEntryLocalService;

}
