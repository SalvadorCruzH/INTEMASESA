package es.emasesa.intranet.liferay.language;

import com.liferay.portal.kernel.language.UTF8Control;
import org.osgi.service.component.annotations.Component;

import java.util.Enumeration;
import java.util.ResourceBundle;

@Component(
		property = {
				"language.id="
		},
		service = ResourceBundle.class
	)

public class CustomResourceBundle extends ResourceBundle {

	@Override
	protected Object handleGetObject(String key) {
		return _resourceBundle.getObject(key);

	}

	@Override
	public Enumeration<String> getKeys() {
		return _resourceBundle.getKeys();
	}

	// FALLBACK - Castellano
	private final ResourceBundle _resourceBundle =
			ResourceBundle.getBundle("content.Language_es_ES", UTF8Control.INSTANCE);

}
