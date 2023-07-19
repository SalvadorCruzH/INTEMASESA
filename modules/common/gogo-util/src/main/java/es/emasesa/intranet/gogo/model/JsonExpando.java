package es.emasesa.intranet.gogo.model;

import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

public class JsonExpando {
	public static final String DEFAULT_DATA = "defaultData";
	private JSONObject jsonObject;

    public JsonExpando(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public final String getExpandoName(){
        return this.jsonObject.getString("expandoName");
    }

    public final String getClassName(){return this.jsonObject.getString("className");}

    public final String getCompanyWebId(){
        return this.jsonObject.getString("companyWebId");
    }

    public final int getType(){
        return this.jsonObject.getInt("type", ExpandoColumnConstants.STRING);
    }

    public final Object getDefaultData(){
    	if(ExpandoColumnConstants.STRING_ARRAY == getType()) {
    		JSONArray arrayDataJSON = this.jsonObject.getJSONArray(DEFAULT_DATA);
    		Object[] defaultData = new String[arrayDataJSON.length()];
    		for (int i = 0; i < arrayDataJSON.length(); i++) {
    			defaultData[i]= arrayDataJSON.get(i);
			}
    		
    		return defaultData;
    	} else {
            return this.jsonObject.get(DEFAULT_DATA);
    	}
    }

    public final String getDefaultDataAsString(){
    	if(ExpandoColumnConstants.STRING_ARRAY == getType()) {
    		JSONArray arrayDataJSON = this.jsonObject.getJSONArray(DEFAULT_DATA);
    		String defaultData = StringPool.BLANK;
    		for (int i = 0; i < arrayDataJSON.length(); i++) {
    			defaultData = defaultData + arrayDataJSON.get(i);
    			if(i + 1 < arrayDataJSON.length()) {
    				defaultData = defaultData + StringPool.COMMA;
    			}
			}
    		
    		return defaultData;
    	} else {
            return this.jsonObject.getString(DEFAULT_DATA);
    	}
    }

    public final String getTypeSettings(){
    	
    	String typeSettings = StringPool.BLANK;
    	JSONObject typeSettingsJSON = this.jsonObject.getJSONObject("typeSettings");
    	
    	for (String key : typeSettingsJSON.keySet()) {
    		typeSettings = typeSettings.concat(key).concat(StringPool.EQUAL).concat(typeSettingsJSON.getString(key)).concat(StringPool.NEW_LINE);
		}
    	
        return typeSettings;
    }


}
