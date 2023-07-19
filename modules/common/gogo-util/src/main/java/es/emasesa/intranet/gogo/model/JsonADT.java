package es.emasesa.intranet.gogo.model;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;

public class JsonADT {
    private JSONObject jsonObject;

    public JsonADT(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public final String getName(){return this.jsonObject.getString("name");}

    public final String getType(){
        return this.jsonObject.getString("type");
    }

    public final String getScreenName(){
        return this.jsonObject.getString("screenName");
    }

    public final String getGroupFriendlyUrl(){
        return this.jsonObject.getString("groupFriendlyUrl");
    }

    public final String getCompanyWebId(){
        return this.jsonObject.getString("companyWebId");
    }

    public final String getTemplateKey(){
        return this.jsonObject.getString("templateKey", StringPool.BLANK);
    }

    public final String getTemplateFile(){
        return this.jsonObject.getString("templateFile");
    }

    public final String getComment(){
        return this.jsonObject.getString("comment");
    }
    
    public final String getDisplayType(){
    	return this.jsonObject.getString("displayType", "display");
    }

    public final String getUuid(){
        return this.jsonObject.getString("uuid", StringPool.BLANK);
    }
}
