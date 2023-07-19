package es.emasesa.intranet.gogo.model;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;

public class JsonJournalTemplate {
    private JSONObject jsonObject;

    public JsonJournalTemplate(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public final String getScreenName(){
        return this.jsonObject.getString("screenName");
    }

    public final String getName(){ return this.jsonObject.getString("name"); }

    public final String getTemplateKey(){ return this.jsonObject.getString("templateKey"); }

    public final String getCompanyWebId(){
        return this.jsonObject.getString("companyWebId");
    }

    public final String getGroupFriendlyUrl(){ return this.jsonObject.getString("groupFriendlyUrl"); }

    public final String getStructureKey(){ return this.jsonObject.getString("structureKey"); }

    public final String getStructureUuid(){ return this.jsonObject.getString("structureUuid"); }

    public final String getTemplateFile(){ return this.jsonObject.getString("templateFile"); }

    public final Boolean getCacheable(){
        return this.jsonObject.getBoolean("cacheable");
    }

    public final String getComment(){
        return this.jsonObject.getString("comment");
    }

    public final String getUuid(){
        return this.jsonObject.getString("uuid", StringPool.BLANK);
    }

}