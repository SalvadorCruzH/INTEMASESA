package es.emasesa.intranet.base.util;

import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigInteger;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = CustomObjectUtil.class
)
public class CustomObjectUtil {
	
	 public static boolean validarIBAN(String iban) {
	        LoggerUtil.debug(LOG,"Validando el IBAN: " + iban);
	        
	        LoggerUtil.debug(LOG,"Paso 1: Verificar el formato del IBAN con una expresión regular");
	        String regex = "^[A-Z]{2}\\d{22}$";
	        if (!iban.matches(regex)) {
	        	LoggerUtil.debug(LOG,"El formato del IBAN es incorrecto");
	            return false;
	        }else {
	        	LoggerUtil.debug(LOG,"El formato del IBAN es correcto, se sigue con la validación");
	        }

	        LoggerUtil.debug(LOG,"Paso 2: Realizar la verificación del dígito de control");
	        String letrasIban = iban.substring(0, 2);
	        String digitosIban = iban.substring(4) + letrasIban + iban.substring(2, 4);

	        LoggerUtil.debug(LOG,"Reemplazar letras por sus equivalentes numéricos");
	        StringBuilder ibanNumerico = new StringBuilder();
	        for (int i = 0; i < digitosIban.length(); i++) {
	            char caracter = digitosIban.charAt(i);
	            if (Character.isLetter(caracter)) {
	                ibanNumerico.append(Character.getNumericValue(caracter));
	            } else {
	                ibanNumerico.append(caracter);
	            }
	        }

	        BigInteger ibanNumericoBigInt = new BigInteger(ibanNumerico.toString());
	        return ibanNumericoBigInt.mod(BigInteger.valueOf(97)).intValue() == 1;
	    }
	 	 
	 public String getValueObjectField(Long objectEntryId, String nameField) {
		 String value = StringPool.BLANK;
			LoggerUtil.debug(LOG,"Obteniendo el campo: " + nameField);
			try {
				value = (String) _objectEntryLocalService.getObjectEntry(objectEntryId).getValues().get(nameField);
				LoggerUtil.debug(LOG,"nameField value is: " + value);
			} catch (NumberFormatException | PortalException e) {
				 LoggerUtil.error(LOG, "Error al intentar obtener el value: ", e);
			}
			return value;
	 }
	 
	 private static final Log LOG = LogFactoryUtil.getLog(CustomObjectUtil.class);
	 
	 @Reference
	 private ObjectEntryLocalService _objectEntryLocalService;
}
