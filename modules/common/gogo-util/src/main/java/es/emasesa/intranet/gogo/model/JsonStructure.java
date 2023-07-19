package es.emasesa.intranet.gogo.model;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;

public class JsonStructure {
    private JSONObject jsonObject;

    public JsonStructure(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public final String getScreenName(){
        return this.jsonObject.getString("screenName");
    }

    public final String getName(){return this.jsonObject.getString("name");}

    public final String getCompanyWebId(){
        return this.jsonObject.getString("companyWebId");
    }

    public final String getGroupFriendlyUrl(){
        return this.jsonObject.getString("groupFriendlyUrl");
    }

    public final String getStructureKey(){
        return this.jsonObject.getString("structureKey");
    }

    public final String getStructureFile(){
        return this.jsonObject.getString("structureFile");
    }

    //public final String getStructureLayoutFile(){
    //    return this.jsonObject.getString("structureFileLayout");
    //}

    public final String getComment(){
        return this.jsonObject.getString("comment");
    }

    public final String getParentGroupFriendlyUrl(){
        return this.jsonObject.getString("parentGroupFriendlyUrl");
    }

    public final String getParentStructureKey(){
        return this.jsonObject.getString("parentStructureKey");
    }

    public final String getUuid(){
        return this.jsonObject.getString("uuid", StringPool.BLANK);
    }

}
