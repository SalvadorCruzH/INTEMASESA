package es.emasesa.intranet.gestionBoletines.application.list;

import es.emasesa.intranet.gestionBoletines.constants.GestionBoletinesPanelCategoryKeys;
import es.emasesa.intranet.gestionBoletines.constants.GestionBoletinesPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author jacarmonam
 */
@Component(
		property = {
				"panel.app.order:Integer=100",
				"panel.category.key=" + GestionBoletinesPanelCategoryKeys.CONTROL_PANEL_CATEGORY
		},
		service = PanelApp.class
)
public class GestionBoletinesPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return GestionBoletinesPortletKeys.GESTONBOLETINES;
	}

	@Override
	public Portlet getPortlet() {
		return _portlet;
	}

	@Reference(target = "(javax.portlet.name=" + GestionBoletinesPortletKeys.GESTONBOLETINES + ")")
	private Portlet _portlet;

}