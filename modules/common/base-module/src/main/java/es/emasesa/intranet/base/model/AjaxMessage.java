package es.emasesa.intranet.base.model;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class AjaxMessage {

    private int statusCode;
    private String statusMsg;
    private JSONObject payload;

    public static final class KEYS {
        public static final String STATUS_CODE = "status";
        public static final String STATUS_MESSAGE = "message";
        public static final String PAYLOAD = "payload";
    }

    public static final class STATUS {
        public static final int OK = 0;
        public static final int UNKNOWN = 1;
        public static final int ERROR_GENERAL = 2;
        public static final int ERROR_SPECIFIC = 3;
    }

    public static final class MESSAGES_DEFAULT {
        public static final String OK = "ok";
        public static final String UNKNOWN = "unknown";
        public static final String ERROR_GENERAL = "error";
        public static final String ERROR_SPECIFIC = "specific error";
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
