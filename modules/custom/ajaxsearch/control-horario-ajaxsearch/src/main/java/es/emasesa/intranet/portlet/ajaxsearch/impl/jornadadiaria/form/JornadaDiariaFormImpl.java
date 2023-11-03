package es.emasesa.intranet.portlet.ajaxsearch.impl.jornadadiaria.form;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.impl.resumenanual.form.ResumenAnualFormImpl;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
public class JornadaDiariaFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(JornadaDiariaFormImpl.class);

    public static final String LAST_MONTH_COUNT = "lastMonthsCount";

    static {
        DFLT_PROPERTIES.put(LAST_MONTH_COUNT, "12");
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

    private static final String VIEW = "/views/jornadadiaria/form.jsp";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {

        LocalDate localDate = LocalDate.now();
        String lastMonthCountstr = ajaxSearchDisplayContext.getConfig().getOrDefault(LAST_MONTH_COUNT,"12");
        int lastMonthCount = Integer.parseInt(lastMonthCountstr);
        JSONArray months = JSONFactoryUtil.createJSONArray();
        JSONObject month;

        for(int i=0;i<lastMonthCount;i++){
            month = JSONFactoryUtil.createJSONObject();
            localDate = localDate.minusMonths(1);
            String value = localDate.format(DateTimeFormatter.ofPattern("MMyyyy"));
            String label = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            month.put("value",value);
            month.put("label",label);
            months.put(month);

        }


        request.setAttribute("months", months);

        return VIEW;
    }


    @Reference
    AssetCategoryLocalService assetCategoryLocalService;

}
