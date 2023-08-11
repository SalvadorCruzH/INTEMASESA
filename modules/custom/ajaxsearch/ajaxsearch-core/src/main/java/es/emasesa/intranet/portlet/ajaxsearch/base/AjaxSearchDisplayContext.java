package es.emasesa.intranet.portlet.ajaxsearch.base;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.LiferayRenderRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import es.emasesa.intranet.base.constant.IntegerConstants;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.base.constant.IntegerConstants;

import javax.portlet.PortletParameters;
import javax.portlet.PortletRequest;
import javax.portlet.RenderParameters;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AjaxSearchDisplayContext {
    private final PortletRequest request;
    private final HttpServletRequest originalRequest;
    private final Map<String, String> config;
    private final long DAY_MILLIS = TimeUnit.DAYS.toMillis(IntegerConstants.ONE);

    public AjaxSearchDisplayContext(PortletRequest request, Map<String, String> config) {
        this.request = request;
        this.originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
        this.config = config;
    }

    public final PortletRequest getRequest(){
        return request;
    }
    public final HttpServletRequest getOriginalRequest(){
        return originalRequest;
    }

    public final RenderParameters getRenderParams(){
        return getRequest().getRenderParameters();
    }

    /**
    public final PortletParameters getParameters(){
        return portletParameters;
    }
    **/

    public final Map<String, String> getConfig(){
        return config;
    }

    public final String getConfig(final String key) {
        return config.getOrDefault(
                key,
                StringPool.BLANK
        );
    }

    public final String getConfigOrDefault(final String key, final String def) {
        return config.getOrDefault(
                key,
                def
        );
    }

    public final String getQueryText() {
    	return getString(AjaxSearchPortletKeys.PARAM_QUERY_TEXT);
    }

    public final String getResultsUrl() {
        return config.getOrDefault(
                AjaxSearchPortletKeys.PROP_BASE_RESULT_URL,
                AjaxSearchPortletKeys.PROP_BASE_RESULT_URL_DFLT
        );
    }
    
    public final int getPageSize() {
        return GetterUtil.getInteger(
        		config.getOrDefault(
                    AjaxSearchPortletKeys.PROP_BASE_PAGESIZE,
                    AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT),
        		    AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT_INT);
    }

    public final int getCurrentPage() {
        return ParamUtil.getInteger(getRequest(), AjaxSearchPortletKeys.PARAM_CURRENT_PAGE,
                ParamUtil.getInteger(getOriginalRequest(), AjaxSearchPortletKeys.PARAM_CURRENT_PAGE, 1));
    }
    
    public final ThemeDisplay getThemeDisplay(){
        return (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }
    
    /** Param getters **/
    public final String getStringRP(String param){
        return getStringRP(param, StringPool.BLANK);
    }

    public final String getStringRP(String param, String defaultVal){
        return getString(param, GetterUtil.getString(getRenderParams().getValue(param), defaultVal));
    }

    public final String getString(String param) {
    	return getString(param, StringPool.BLANK);
    }
    
    public final String getString(String param, String defaultValue) {
    	return ParamUtil.getString(getRequest(), param,
                ParamUtil.getString(getOriginalRequest(), param, defaultValue));
    }
    public final long getLong(String param) {
        return getLong(param, 0L);
    }
    
    public final long getLong(String param, long defaultValue) {
        return ParamUtil.getLong(getRequest(), param,
                ParamUtil.getLong(getOriginalRequest(), param, defaultValue));
    }
    
    public final int getInteger(String param) {
        return getInteger(param, 0);
    }
    
    public final int getInteger(String param, int defaultValue) {
        return ParamUtil.getInteger(getRequest(), param,
                ParamUtil.getInteger(getOriginalRequest(), param, 0));
    }
    
    public final Date getDate(String param) {
    	return getDate(param, null);
    }
    
    public final Date getDate(String param, Date defaultValue) {
    	return ParamUtil.getDate(getRequest(), param, AjaxSearchPortletKeys.DFLT_SIMPLE_DATE_FORMAT,
                ParamUtil.getDate(getOriginalRequest(), param, AjaxSearchPortletKeys.DFLT_SIMPLE_DATE_FORMAT, defaultValue));
    }

    public final Date getOneMoreDayDate(String param, Date defaultValue) {
        Date date = getDate(param, defaultValue);

        if (Validator.isNotNull(date)){
            date = new Date(date.getTime() + DAY_MILLIS);
        }

        return date;
    }

    public final Date getOneMoreDayDate(String param) {
        return getOneMoreDayDate(param, null);
    }
}
