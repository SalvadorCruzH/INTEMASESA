package es.emasesa.intranet.base.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(
        immediate = true,
        property = { },
        service = CustomLanguageUtil.class
)
public class CustomLanguageUtil {


    public String getLiteral(Locale locale, String key, Object... args){

        return LanguageUtil.format(locale,key,args);
    }
}
