package es.emasesa.intranet.base.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.encryptor.Encryptor;
import com.liferay.portal.kernel.encryptor.EncryptorException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.security.Key;

@Component(
        immediate = true,
        property = {
        },
        service = CustomEncryptUtil.class)
public class CustomEncryptUtil {

    public String encrypt(ThemeDisplay themeDisplay, String text){
        String result = StringPool.BLANK;

        try {
            result = _encryptor.encrypt(themeDisplay.getCompany().getKeyObj(),text);
        } catch (EncryptorException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    public String decrypt(ThemeDisplay themeDisplay,  String textEncrypted) {
        String result = StringPool.BLANK;

        try {
            result = _encryptor.decrypt(themeDisplay.getCompany().getKeyObj(),textEncrypted);
        } catch (EncryptorException e) {
            throw new RuntimeException(e);
        }


        return result;

    }

    public String encrypt(Key key, String text) throws EncryptorException {

        return _encryptor.encrypt(key,text);

    }

    public String decrypt(Key key, String textEncrypted) throws EncryptorException {

        return _encryptor.decrypt(key,textEncrypted);

    }

    @Reference
    Encryptor _encryptor;

}
