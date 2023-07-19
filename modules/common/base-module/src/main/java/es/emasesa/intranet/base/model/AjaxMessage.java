package es.emasesa.intranet.base.model;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class AjaxMessage {

    private int statusCode;
    private String statusMsg;
    private JSONObject payload;

    public static final class KEYS {
        public static String STATUS_CODE = "status";
        public static String STATUS_MESSAGE = "message";
        public static String PAYLOAD = "payload";
    }

    public static final class STATUS {
        public static int OK = 0;
        public static int UNKNOWN = 1;
        public static int ERROR_GENERAL = 2;
        public static int ERROR_SPECIFIC = 3;
    }

    public static final class MESSAGES_DEFAULT {
        public static String OK = "ok";
        public static String UNKNOWN = "unknown";
        public static String ERROR_GENERAL = "error";
        public static String ERROR_SPECIFIC = "specific error";
    }

    public AjaxMessage() {
        this.statusCode = AjaxMessage.STATUS.UNKNOWN;
        this.statusMsg = AjaxMessage.MESSAGES_DEFAULT.UNKNOWN;
        this.payload = JSONFactoryUtil.createJSONObject();
    }

    public AjaxMessage(final JSONObject payload) {
        this.statusCode = AjaxMessage.STATUS.UNKNOWN;
        this.statusMsg = AjaxMessage.MESSAGES_DEFAULT.UNKNOWN;
        this.payload = payload;
    }

    public AjaxMessage(final int statusCode, final String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.payload = JSONFactoryUtil.createJSONObject();
    }

    public AjaxMessage(final int statusCode, final String statusMsg, final JSONObject payload) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.payload = payload;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public JSONObject getPayload() {
        return payload;
    }

    public void setPayload(JSONObject payload) {
        this.payload = payload;
    }

    public String getJsonString(){
        return new JSONFactoryUtil().createJSONObject()
                .put(AjaxMessage.KEYS.STATUS_CODE, getStatusCode())
                .put(AjaxMessage.KEYS.STATUS_MESSAGE, getStatusMsg())
                .put(AjaxMessage.KEYS.PAYLOAD, getPayload()).toJSONString();
    }

    @Override
    public String toString(){
        return getJsonString();
    }
}
