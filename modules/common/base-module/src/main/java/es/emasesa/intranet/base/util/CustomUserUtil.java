package es.emasesa.intranet.base.util;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(
        immediate = true,
        service = CustomUserUtil.class
)
public class CustomUserUtil {

	private final static Log LOG = LoggerUtil.getLog(CustomUserUtil.class);
	
	public JSONObject getUserJsonExpando(final User user) {
		JSONObject userJsonExpando = jsonFactory.createJSONObject();
		
		if(Validator.isNotNull(user)) {
			final long userId = user.getUserId();
	        final JSONObject userJsonExpandoCached = (JSONObject) GetterUtil.getObject(customCacheSingleUtil.get(getUserExpandoCacheName(userId)), null);
	
	        if(Validator.isNotNull(userJsonExpandoCached)) {
	        	return userJsonExpandoCached;
	        } else {
				final String userExtraData = (String) user.getExpandoBridge().getAttribute("user-extra-data");
				
				if(!Validator.isBlank(userExtraData)) {
					try {
						userJsonExpando = jsonFactory.createJSONObject(userExtraData);
	
	                    customCacheSingleUtil.put(getUserExpandoCacheName(userId), userJsonExpando, CustomTemplateCacheSingleUtil.TTL_10_MIN);
					} catch (JSONException e) {
						LOG.info("Error at parse JSON Expando for user with id:" + userId);
					}
				}
	        }
		}
		
		return userJsonExpando;
	}


    private String getUserExpandoCacheName(final long userId){
        return "userJsonExpando"+userId;
    }
	
	@Reference
	JSONFactory jsonFactory;
	
	@Reference
	CustomCacheSingleUtil customCacheSingleUtil;
}
