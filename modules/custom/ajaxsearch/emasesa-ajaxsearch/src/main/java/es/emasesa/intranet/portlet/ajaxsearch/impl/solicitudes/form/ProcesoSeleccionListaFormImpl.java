package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.form;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.*;

@Component(
        immediate = true,
        property = {},
        service = AjaxSearchForm.class
)
public class ProcesoSeleccionListaFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(ProcesoSeleccionListaFormImpl.class);

    public static final String CATEGORY_ID = "category-id";

    static {
        DFLT_PROPERTIES.put(CATEGORY_ID, "-1");
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

    private static final String VIEW = "/views/solicitudes/procesoseleccionlista/form.jsp";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {
        JSONArray results = _sapServicesUtil.getProcesoSeleccionLista();
        Set<String> setTiposProcesos = new HashSet<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String estado = result.getString("zztipoDesc2");
            setTiposProcesos.add(estado);
        }
        List<String> tiposProcesos = new ArrayList<>(setTiposProcesos);

        request.setAttribute("listadoEstados", tiposProcesos);
            return VIEW;
    }

    @Reference
    AjaxSearchUtil ajaxSearchUtil;

    @Reference
    SapServicesUtil _sapServicesUtil;
}
