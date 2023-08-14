package es.emasesa.intranet.portlet.ajaxsearch.model;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;

public class AjaxSearchJsonModel {

    private JSONObject jsonObject;


    public AjaxSearchJsonModel(int currentPage, int totalPages, long totalItems, JSONArray content) {
        this.jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put(AjaxSearchPortletKeys.JSON_CURRENT_PAGE, currentPage);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_PAGES, totalPages);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_ITEMS, totalItems);
        jsonObject.put(AjaxSearchPortletKeys.JSON_ARRAY_CONTENT, content);
    }

    public AjaxSearchJsonModel(int currentPage, int totalPages, long totalItems, int itemsPage, JSONArray content) {
        this.jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put(AjaxSearchPortletKeys.JSON_CURRENT_PAGE, currentPage);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_PAGES, totalPages);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_ITEMS, totalItems);
        jsonObject.put(AjaxSearchPortletKeys.JSON_ITEMS_PAGE, itemsPage);
        jsonObject.put(AjaxSearchPortletKeys.JSON_ARRAY_CONTENT, content);
    }
    
    public AjaxSearchJsonModel(int currentPage, int totalPages, long totalItems, JSONArray content, JSONObject aditionalInfo) {
        this.jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put(AjaxSearchPortletKeys.JSON_CURRENT_PAGE, currentPage);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_PAGES, totalPages);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_ITEMS, totalItems);
        jsonObject.put(AjaxSearchPortletKeys.JSON_ARRAY_CONTENT, content);
        jsonObject.put(AjaxSearchPortletKeys.JSON_ARRAY_ADDITIONAL_INFO, aditionalInfo);
    }
    
    public AjaxSearchJsonModel(int currentPage, int totalPages, JSONArray content) {
        this.jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put(AjaxSearchPortletKeys.JSON_CURRENT_PAGE, currentPage);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_PAGES, totalPages);
        jsonObject.put(AjaxSearchPortletKeys.JSON_TOTAL_ITEMS, StringPool.BLANK);
        jsonObject.put(AjaxSearchPortletKeys.JSON_ARRAY_CONTENT, content);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
