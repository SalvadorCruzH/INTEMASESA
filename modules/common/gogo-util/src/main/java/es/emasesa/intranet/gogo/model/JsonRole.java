package es.emasesa.intranet.gogo.model;

import com.liferay.portal.kernel.json.JSONObject;

public class JsonRole {
    private JSONObject jsonObject;

    public JsonRole(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public final String getScreenName(){
        return this.jsonObject.getString("screenName");
    }
    public final String getCompanyWebId(){
        return this.jsonObject.getString("companyWebId");
    }
    public final String getGroupFriendlyUrl(){
        return this.jsonObject.getString("groupFriendlyUrl");
    }
    public final String getTitle(){
        return this.jsonObject.getString("title");
    }
    public final String getDescription(){
        return this.jsonObject.getString("description");
    }
    public final String getName(){
        return this.jsonObject.getString("name");
    }
    public final int getType(){
        return this.jsonObject.getInt("type");
    }
}
