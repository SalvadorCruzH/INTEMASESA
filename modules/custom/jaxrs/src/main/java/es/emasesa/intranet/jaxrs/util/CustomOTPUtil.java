package es.emasesa.intranet.jaxrs.util;

import com.liferay.notification.model.NotificationTemplate;
import com.liferay.notification.service.NotificationTemplateLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.*;
import es.emasesa.intranet.base.util.CustomCacheMultiUtil;
import es.emasesa.intranet.base.util.CustomMailUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.jaxrs.constant.JaxrsConstants;
import es.emasesa.intranet.settings.osgi.FormsSettings;
import es.emasesa.intranet.webservices.jaxrs.beans.ResponseData;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.security.SecureRandom;
import java.util.*;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/customotputil",
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.CustomOTPUtil",
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class CustomOTPUtil extends Application {

    @Override
    public Set<Object> getSingletons() {return Collections.<Object>singleton(this);}

    @GET
    @Produces(ContentTypes.APPLICATION_JSON)
    @Path("/validate-otp/{typeObject}/{userId}/{otpUser}/{timestampGeneration}")
    public Response validateOTP(@DefaultValue(StringPool.BLANK) @PathParam("typeObject") String typeObject,
                               @DefaultValue(StringPool.BLANK) @PathParam("userId") String userId,
                               @DefaultValue(StringPool.BLANK) @PathParam("otpUser") String otpUser,
                               @DefaultValue("0") @PathParam("timestampGeneration") long timestampGeneration ){
        Response.ResponseBuilder builder;
        JSONObject json = JSONFactoryUtil.createJSONObject();
        boolean isValid = Boolean.FALSE;
        String reason = JaxrsConstants.REASON_INVALID;

        String otpCache =  getOTPCache(typeObject, userId);
        boolean isExpireOTP = isOTPExpired(timestampGeneration);
        if ((Validator.isNotNull(otpCache) && !otpCache.isEmpty()) && otpCache.equals(otpUser) && !isExpireOTP){
            isValid = Boolean.TRUE;
            removeElementCache(typeObject, userId);

        } else if (isExpireOTP){
            reason = JaxrsConstants.REASON_EXPIRED;
        }
        json.put("isOk", isValid);
        if (!isValid) json.put("reason",reason);

        builder = Response.ok(new ResponseData(
                false,
                json.toString(),
                null,
                null));

        return builder.build();
    }

    @GET
    @Produces(ContentTypes.APPLICATION_JSON)
    @Path("/generate-send-otp/{typeObject}/{userId}/{length}/{companyId}/{to}")
    public Response generateSendOTP(@DefaultValue(StringPool.BLANK) @PathParam("typeObject") String typeObject,
                                    @DefaultValue(StringPool.BLANK) @PathParam("userId") String userId,
                                    @DefaultValue("0") @PathParam("length")int length,
                                    @DefaultValue("0") @PathParam("companyId") long companyId,
                                    @DefaultValue(StringPool.BLANK) @PathParam("to") String to){
        Response.ResponseBuilder builder;
        boolean isOk;

        isOk = generateOTP(typeObject,userId,length);
        if (isOk){
            LoggerUtil.debug(_log, "Se ha generado la OTP");
            isOk = sendOTP(typeObject, userId, companyId, to);
        }
        JSONObject json = JSONFactoryUtil.createJSONObject();

        json.put("isOk", isOk);

        if (isOk) json.put("ttl", getTTL());
        if (isOk) json.put("timestampGeneration", System.currentTimeMillis());

        if (_formSettings.debugOtp()) json.put("otp", getOTPCache(typeObject, userId));

        builder = Response.ok(new ResponseData(
                false,
                json.toString(),
                null,
                null));

        return builder.build();
    }

    private boolean generateOTP(String typeObject, String userId, int length){
        boolean isGenerated = Boolean.FALSE;
        String digits = JaxrsConstants.DIGITS;
        String otp;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(digits.length());
            char randomDigit = digits.charAt(randomIndex);
            password.append(randomDigit);
        }

        otp = password.toString();
        if (!otp.isEmpty() && otp.length() == length){
            int ttl = getTTL();
            setOTPCache(typeObject, userId, otp, ttl);
            isGenerated = Boolean.TRUE;
        } else {
            LoggerUtil.error(_log, "Ha ocurrido un error al generar la OTP");
        }

        return isGenerated;
    }

    private boolean sendOTP(String typeObject, String userId,  long companyId, String to){
        boolean sendOtp;
        String otpCache = getOTPCache(typeObject, userId);

        Map<String,String> mailDetails = getMailDetails(otpCache);
        if (!mailDetails.isEmpty()){
            String subject = mailDetails.get(JaxrsConstants.EMAIL_SUBJECT);
            String body = mailDetails.get(JaxrsConstants.EMAIL_BODY);
            String from = mailDetails.get(JaxrsConstants.EMAIL_FROM).isEmpty()?
                    PrefsPropsUtil.getPreferences(companyId).getValue("admin.email.from.address", PropsUtil.get("admin.email.from.address")):
                    mailDetails.get(JaxrsConstants.EMAIL_FROM);
            sendOtp = _customMailUtil.createAndSendMail(from, to, subject, body);
            if (!sendOtp) LoggerUtil.error(_log, "Ha ocurrido un error al enviar el Correo Electronico");
            return sendOtp;

        } else {
            return Boolean.FALSE;
        }
    }

    private Map<String,String> getMailDetails(String otp){
        Map<String,String> mailDetails = new HashMap<>();
        String body, from;
        NotificationTemplate notificationTemplate = getNotificationTemplate();

        if(!Validator.isNull(notificationTemplate)) {
            body = notificationTemplate.getBody(JaxrsConstants.LOCALE_SPANISH);
            body = body.replace("#otpGenerada#", otp);
            body = _customMailUtil.parseImgsOnBodyToBase64(body);
            mailDetails.put(JaxrsConstants.EMAIL_BODY, body );

            from = Objects.requireNonNull(notificationTemplate.getNotificationRecipient().getNotificationRecipientSettings().stream().filter(element -> element.getName().equals("from")).findFirst().orElse(null)).getValue();
            mailDetails.put(JaxrsConstants.EMAIL_FROM, from);

            mailDetails.put(JaxrsConstants.EMAIL_SUBJECT, notificationTemplate.getSubject(JaxrsConstants.LOCALE_SPANISH));

            return mailDetails;

        } else {
            LoggerUtil.warn(_log, "No se ha encontrado la plantilla de Correo Electronico. getMailDetails");
            return mailDetails;
        }
    }

    private NotificationTemplate getNotificationTemplate (){
        NotificationTemplate notificationTemplate;

        DynamicQuery dynamicQuery = _notificationTemplateLocalService.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(JaxrsConstants.QUERY_PARAM_NAME,JaxrsConstants.EMAIL_TEMPLATE_NAME))
                .add(RestrictionsFactoryUtil.ilike(JaxrsConstants.QUERY_PARAM_RECIPIENT_TYPE,"email"));

        List <NotificationTemplate> notificationTemplates = _notificationTemplateLocalService.dynamicQuery(dynamicQuery);
        if(notificationTemplates.isEmpty())  {
            LoggerUtil.warn(_log, "No se ha encontrado la plantilla de Correo Electronico. getNotificationTemplate");
            return null;
        } else {
            notificationTemplate = notificationTemplates.get(0);
            return notificationTemplate;
        }
    }

    private void setOTPCache(String typeObject, String userId, String otp, int ttl){
        String elementToSave = JaxrsConstants.CACHE_PREFIX_OTP + typeObject + StringPool.UNDERLINE + userId;
        _customCacheMultiUtil.put(elementToSave, otp, ttl);
    }

    private String getOTPCache(String typeObject, String userId){
        String elementToSave = JaxrsConstants.CACHE_PREFIX_OTP + typeObject + StringPool.UNDERLINE + userId;
        return Validator.isNotNull(_customCacheMultiUtil.get(elementToSave))?_customCacheMultiUtil.get(elementToSave).toString():"-1";
    }

    private void removeElementCache(String typeObject, String userId){
        String elementToRemove = JaxrsConstants.CACHE_PREFIX_OTP + typeObject + StringPool.UNDERLINE + userId;
        _customCacheMultiUtil.remove(elementToRemove);
    }

    private int getTTL(){
        int ttl = Math.max(_formSettings.getTtlOtp(), 0);
        LoggerUtil.debug(_log, "Valor TTL: " + ttl);

        return ttl;
    }

    private boolean isOTPExpired(long timestampGeneration) {
        long expirationTime = timestampGeneration + (getTTL() * 1000L);
        long currentTime = System.currentTimeMillis();

        return expirationTime < currentTime;
    }

    @Reference
    private CustomMailUtil _customMailUtil;

    @Reference
    private CustomCacheMultiUtil _customCacheMultiUtil;

    @Reference
    private NotificationTemplateLocalService _notificationTemplateLocalService;

    @Reference
    private FormsSettings _formSettings;

    private static final Log _log = LoggerUtil.getLog(CustomOTPUtil.class);
}
