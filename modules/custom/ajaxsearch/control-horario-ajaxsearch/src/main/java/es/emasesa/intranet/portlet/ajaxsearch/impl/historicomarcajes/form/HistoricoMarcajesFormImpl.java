package es.emasesa.intranet.portlet.ajaxsearch.impl.historicomarcajes.form;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import java.time.LocalDate;
import java.util.Properties;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = { },
        service = AjaxSearchForm.class
)
public class HistoricoMarcajesFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(HistoricoMarcajesFormImpl.class);

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

    private static final String VIEW = "/views/historicomarcajes/form.jsp";
    private static final String CAT_SELECTED = "catSelected";
    private static final String CATEGORIES = "categories";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {
        
        return VIEW;
    }


    @Reference
    AssetCategoryLocalService assetCategoryLocalService;

}
