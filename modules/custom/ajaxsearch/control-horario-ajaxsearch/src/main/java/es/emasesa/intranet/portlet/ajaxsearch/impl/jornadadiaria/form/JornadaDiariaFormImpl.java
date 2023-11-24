package es.emasesa.intranet.portlet.ajaxsearch.impl.jornadadiaria.form;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.impl.resumenanual.form.ResumenAnualFormImpl;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.*;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import es.emasesa.intranet.service.util.SapServicesUtil;
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
    public static final String RESUMEN_ANUAL_URL = "resumenAnualUrl";

    static {
        DFLT_PROPERTIES.put(LAST_MONTH_COUNT, "12");
        DFLT_PROPERTIES.put(RESUMEN_ANUAL_URL, "/resumen-anual");
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
        request.setAttribute("year", localDate.getYear());
        request.setAttribute("resumenAnualUrl",ajaxSearchDisplayContext.getConfig().getOrDefault(RESUMEN_ANUAL_URL,"/resumen-anual"));

        String lastMonthCountstr = ajaxSearchDisplayContext.getConfig().getOrDefault(LAST_MONTH_COUNT,"12");
        int lastMonthCount = Integer.parseInt(lastMonthCountstr);
        JSONArray months = JSONFactoryUtil.createJSONArray();
        JSONObject month;

        for(int i=0;i<lastMonthCount;i++){
            month = JSONFactoryUtil.createJSONObject();
            localDate = LocalDate.now();
            localDate = localDate.minusMonths(i);
            String value = localDate.format(DateTimeFormatter.ofPattern("MMyyyy"));
            String label = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            month.put("value",value);
            month.put("label",label);
            months.put(month);

        }

        request.setAttribute("monthSelected", ajaxSearchDisplayContext.getString("monthSelected"));
        request.setAttribute("months", months);


        ThemeDisplay themeDisplay =  (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();
        List<Role> listUserRoles = user.getRoles();
        boolean isAdministradorRRHH = false;
        for (Role role : listUserRoles) {
            if(role.getName().equals("administradorRRHH")){
                request.setAttribute("role", "administradorRRHH");
                isAdministradorRRHH = true;
            }
        }
        boolean isResponsable = false;
        JSONArray subordinadosData = JSONFactoryUtil.createJSONArray();
        if (!isAdministradorRRHH) {
            JSONArray subordinados = _sapServicesUtil.getSubordinados(user, "T");
            if (subordinados.length() > 0){
                request.setAttribute("role", "responsable");
                isResponsable = true;

                for (int i = 0; i < subordinados.length(); i++) {
                    JSONObject subordinado = subordinados.getJSONObject(i);
                    JSONObject subordinadoData = _sapServicesUtil.getDatosEmpleado(subordinado.getString("pernr"));
                    subordinadosData.put(subordinadoData);
                }
            }
        }
        request.setAttribute("subordinados", subordinadosData);
        if (!isAdministradorRRHH && !isResponsable) {
            request.setAttribute("role", "empleado");
        }
        request.setAttribute("usuarioSelected", ajaxSearchDisplayContext.getString("usuarioSelected"));
        return VIEW;
    }

    @Reference
    SapServicesUtil _sapServicesUtil;

    @Reference
    AssetCategoryLocalService assetCategoryLocalService;

}
