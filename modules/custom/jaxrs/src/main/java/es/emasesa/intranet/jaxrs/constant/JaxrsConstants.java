package es.emasesa.intranet.jaxrs.constant;

import com.liferay.petra.string.StringPool;

public class JaxrsConstants {
    public static final String CACHE_PREFIX_OTP = "otp" + StringPool.UNDERLINE;
    
    public static final String DIGITS = "0123456789";

    public static final String LOCALE_SPANISH = "es_ES";

    public static final String EMAIL_BODY = "body";
    public static final String EMAIL_SUBJECT= "subject";
    public static final String EMAIL_TEMPLATE_NAME = "Envio OTP";

    public static final String QUERY_PARAM_NAME= "name";
    public static final String QUERY_PARAM_RECIPIENT_TYPE= "recipientType";
    public static final String EMAIL_FROM = "from";

    public static final String REASON_INVALID = "invalid";
    public static final String REASON_EXPIRED = "expired";

    public static final String IS_REMOVE = "isRemoved";
    public static final String IS_MARK_READ = "isMarkRead";
    public static final String STATUS_READ = "leido";


}
