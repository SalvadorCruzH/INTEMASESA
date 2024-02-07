package es.emasesa.intranet.portlet.configuraciones.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;
import es.emasesa.intranet.portlet.configuraciones.constants.EmasesaConfiguracionesWebPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author scruzh
 */
@Component(
		property = {
				"panel.app.order:Integer=100",
				"panel.category.key=" + EmasesaConfiguracionesWebPortletKeys.CONTROL_PANEL_CATEGORY
		},
		service = PanelApp.class
)
public class GestionTipologiasComunicacionPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return EmasesaConfiguracionesWebPortletKeys.GESTIONTIPOLOGIASCOMUNICACIONWEB;
	}

	@Override
	public Portlet getPortlet() {
		return _portlet;
	}

	@Reference(target = "(javax.portlet.name=" + EmasesaConfiguracionesWebPortletKeys.GESTIONTIPOLOGIASCOMUNICACIONWEB + ")")
	private Portlet _portlet;

}