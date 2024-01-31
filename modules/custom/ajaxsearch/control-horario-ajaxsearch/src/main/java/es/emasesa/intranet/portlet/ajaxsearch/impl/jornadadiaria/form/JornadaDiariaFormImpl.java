package es.emasesa.intranet.portlet.ajaxsearch.impl.jornadadiaria.form;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
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
import es.emasesa.intranet.settings.osgi.RolesSettings;
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
            String label = localDate.getMonth().getDisplayName(TextStyle.FULL, new Locale("es")) + " " + localDate.getYear();
            month.put("value",value);
            month.put("label",label);
            months.put(month);

        }

        request.setAttribute("monthSelected", ajaxSearchDisplayContext.getString("monthSelected"));
        request.setAttribute("months", months);


        ThemeDisplay themeDisplay =  (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();
        LoggerUtil.debug(LOG, "[D] Consiguiendo rol del usuario: " + user.getScreenName());
        long[] userRoleIds = user.getRoleIds();

        boolean isAdministradorRRHH = false;
        for (long roleId : userRoleIds) {
            if(roleId == _rolesSettings.administradorRRHHId()){
                request.setAttribute("role", "administradorRRHH");
                isAdministradorRRHH = true;
            }
        }

        boolean isResponsable = false;
        JSONArray subordinadosData = JSONFactoryUtil.createJSONArray();
        JSONArray subordinadosPernrs;
        if (!isAdministradorRRHH) {


            String subordinadosCacheKey = "subordinados" + user.getUserId();
            Object subordinadosCache = _cache.get(subordinadosCacheKey);

            if (Validator.isNotNull(subordinadosCache) && ((JSONArray) subordinadosCache).length() > 0) {
                LoggerUtil.debug(LOG, "[D] Consiguiendo subordinados del usuario mediante CACHE: " + user.getScreenName());
                subordinadosPernrs = (JSONArray) subordinadosCache;
            } else {
                String matriculaUser = StringPool.BLANK;
                try {
                    matriculaUser = _expandoValueLocalService.getData(
                            themeDisplay.getCompanyId(),
                            User.class.getName(),
                            ExpandoTableConstants.DEFAULT_TABLE_NAME,
                            "matricula",
                            themeDisplay.getUser().getUserId(),
                            StringPool.BLANK
                    );
                } catch (Exception e) {
                    LoggerUtil.error(LOG, "ERROR getValue from Expando", e);
                }
                LoggerUtil.debug(LOG, "[D] Consiguiendo subordinados del usuario mediante SAP: " + matriculaUser);
                subordinadosPernrs = _sapServicesUtil.getSubordinados(matriculaUser, "T");
                LoggerUtil.debug(LOG, "[D] Conseguidos subordinados del usuario mediante SAP: " + matriculaUser);
                _cache.put(subordinadosCacheKey, subordinadosPernrs, 86400);
            }

            if (subordinadosPernrs.length() > 0){
                request.setAttribute("role", "responsable");
                isResponsable = true;

                for (int i = 0; i < subordinadosPernrs.length(); i++) {
                    JSONObject subordinado = subordinadosPernrs.getJSONObject(i);

                    String datosSubordinadoCacheKey = "datosSubordinado" + subordinado.getString("pernr");
                    Object datosSubordinadoCache = _cache.get(datosSubordinadoCacheKey);

                    JSONObject subordinadoData;
                    if (Validator.isNotNull(datosSubordinadoCache)) {
                        LoggerUtil.debug(LOG, "[D] Consiguiendo datos del subordinado mediante CACHE: " + subordinado.getString("pernr"));
                        subordinadoData = (JSONObject) datosSubordinadoCache;
                    } else {
                        LoggerUtil.debug(LOG, "[D] Consiguiendo datos del subordinado mediante SAP: " + subordinado.getString("pernr"));
                        subordinadoData = _sapServicesUtil.getDatosEmpleado(subordinado.getString("pernr"));
                        LoggerUtil.debug(LOG, "[D] Conseguidos datos del subordinado mediante SAP: " + subordinado.getString("pernr"));
                        _cache.put(datosSubordinadoCacheKey, subordinadoData, 86400);
                    }

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

    @Reference
    CustomCacheSingleUtil _cache;

    @Reference
    RolesSettings _rolesSettings;

    @Reference
    ExpandoValueLocalService _expandoValueLocalService;

}
