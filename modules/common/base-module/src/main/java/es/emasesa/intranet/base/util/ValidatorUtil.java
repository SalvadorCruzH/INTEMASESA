package es.emasesa.intranet.base.util;

import com.liferay.portal.kernel.util.Validator;

public class ValidatorUtil {
    public static final String  UUID_PATTERN = "^[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}$";

    public static final boolean validateUUID(final String uuid){
        return (!Validator.isBlank(uuid)) && (uuid.length() == 36) && uuid.matches(UUID_PATTERN);
    }

}
