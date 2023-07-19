package es.emasesa.intranet.base.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Component(
immediate = true,
property = {
},
service = CustomPropertiesUtil.class)
public class CustomPropertiesUtil {

    public static final Map<String,String> getMapFromPropertiesString(final String strProps){
        Map<String, String> propertiesMap;

        if (!Validator.isBlank(strProps)) {
            try {
                Properties properties = new Properties();
                properties.load(new StringReader(strProps));
                propertiesMap = properties.entrySet().stream().collect(
                        Collectors.toMap(
                                e -> e.getKey().toString(),
                                e -> e.getValue().toString()
                        )
                );
            } catch (Exception e){
                LoggerUtil.warn(_log, e);
                propertiesMap = null;
            }
        } else {
            propertiesMap = new HashMap<>();
        }

        return propertiesMap;
    }


    @SuppressWarnings("rawtypes")
	public static final String getStringFromProperties(final Properties properties){
        String propertiesStr = StringPool.BLANK;
        try {
            final StringWriter sw= new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);

            Set<Map.Entry<Object, Object>> propEntrySet = properties.entrySet();
            for (Map.Entry e : propEntrySet) {
                String key = (String) e.getKey();
                String val = (String) e.getValue();
                pw.println(key + "=" + val);
            }
            propertiesStr = sw.getBuffer().toString();
        } catch (Exception e){
            LoggerUtil.error(_log, e);
        }
        return propertiesStr;
    }
    

	public boolean addToCachedPortletPreference(final String portletNamespace, final Layout layout, PortletPreferences prefs, final String preferenceName, final String configs) throws ReadOnlyException{
		final Map<String,String> props = CustomPropertiesUtil.getMapFromPropertiesString(configs);

		boolean added = Boolean.TRUE;
        if (Validator.isNotNull(props) && Validator.isNotNull(layout)) {
            prefs.setValue(preferenceName, configs);

            // If current layout is a content page, get as plid: 
            //		- its plid if classNameId is not Layout
            // 		- its classPK if classNameId is Layout (and its plid in order to update "staging content page" too)
            // Else, use its plid
            long[] plids = (LayoutConstants.TYPE_CONTENT.equals(layout.getType()) && layout.getClassNameId() == _classNameLocalService.getClassNameId(Layout.class.getName())) ?
            				new long[]{layout.getPlid(), layout.getClassPK()} : new long[]{layout.getPlid()};

            _customCacheMultiUtil.put(portletNamespace + plids[0] + StringPool.UNDERLINE + preferenceName, parseMapToJSONObject(props), CustomCacheMultiUtil.TTL_INFINITE);
            
            if(plids.length > 1) {
                _customCacheMultiUtil.put(portletNamespace + plids[1] + StringPool.UNDERLINE + preferenceName, parseMapToJSONObject(props), CustomCacheMultiUtil.TTL_INFINITE);
            }
           
        } else {
            added = Boolean.FALSE;
        }
        
        return added;
	}
	
	public Map<String,String> getFromCachedPortletPreference(final PortletRequest portletRequest, final String preferenceName){
       
        ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        
        String namespace = themeDisplay.getPortletDisplay().getNamespace();
        
        // If portlet is not instanciable, force to use INSTANCE_0_ in namespace.
        if(!namespace.contains("INSTANCE_")) {
        	namespace = namespace + "INSTANCE_0_";
        }

		Map<String,String> props = parseJSONObjectToMap((JSONObject) _customCacheMultiUtil.get(namespace + themeDisplay.getPlid() + StringPool.UNDERLINE + preferenceName));
		if(props == null ) {
	        final String currentPropertiesString = portletRequest.getPreferences().getValue(preferenceName,StringPool.BLANK);
	        props = CustomPropertiesUtil.getMapFromPropertiesString(currentPropertiesString);
	        
	        _customCacheMultiUtil.put(namespace + themeDisplay.getPlid() + StringPool.UNDERLINE + preferenceName, parseMapToJSONObject(props), CustomCacheMultiUtil.TTL_INFINITE);
	           
		}
        

        // AT DISABLE CACHE
        /** final String currentPropertiesString = portletRequest.getPreferences().getValue(preferenceName,StringPool.BLANK);
        Map<String,String> props = CustomPropertiesUtil.getMapFromPropertiesString(currentPropertiesString); **/
        return props;
	}

	private JSONObject parseMapToJSONObject(Map<String,String> map) {

        JSONObject jsonObject = null;
        
        if(map != null) {
        	jsonObject = JSONFactoryUtil.createJSONObject();
        	
        	for (Entry<String, String> entry : map.entrySet()) {
        		jsonObject.put(entry.getKey(), entry.getValue());
			}
        }
        
        return jsonObject;
	}
	
	private Map<String,String> parseJSONObjectToMap(JSONObject jsonObject) {

		Map<String,String> map = null;
        
        if(jsonObject != null) {
        	map = new HashMap<String, String>();
        	
        	for (String key : jsonObject.keySet()) {
        		map.put(key, jsonObject.getString(key));
			}
        }
        
        return map;
	}

    @Reference
    CustomCacheMultiUtil _customCacheMultiUtil;
    
    @Reference
    ClassNameLocalService _classNameLocalService;


    private static final Log _log = LoggerUtil.getLog(CustomPropertiesUtil.class);



}

