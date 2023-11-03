package es.emasesa.intranet.portlet.ajaxsearch.impl.marcajediaactual.form;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.portal.kernel.log.Log;
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
public class MarcajeDiaActualFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(MarcajeDiaActualFormImpl.class);

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

    private static final String VIEW = "/views/marcajediaactual/form.jsp";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {

        LocalDate localDate = LocalDate.now();

        request.setAttribute("year", localDate.getYear());

        return VIEW;
    }


    @Reference
    AssetCategoryLocalService assetCategoryLocalService;

}
