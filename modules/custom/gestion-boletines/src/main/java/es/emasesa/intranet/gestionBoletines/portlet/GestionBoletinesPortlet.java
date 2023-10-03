package es.emasesa.intranet.gestionBoletines.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.gestionBoletines.constants.GestionBoletinesPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author jacarmonam
 */
@Component(
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=ListasDistribucion",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GestionBoletinesPortletKeys.GESTONBOLETINES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",

	},
	service = Portlet.class
)
public class GestionBoletinesPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		PortletPreferences preferences = renderRequest.getPreferences();

		String destinatariosListaControlada = preferences.getValue("destinatariosListaControlada", "");
		String destinatariosListaDistribucion = preferences.getValue("destinatariosListaDistribucion", "");

		//Recuperacion de valores y envio a la jsp
		renderRequest.setAttribute(GestionBoletinesPortletKeys.INPUT_DESTINATARIOS_LISTA_CONTROLADA_VALUE, destinatariosListaControlada);
		renderRequest.setAttribute(GestionBoletinesPortletKeys.INPUT_DESTINATARIOS_LISTA_DISTRIBUCION_VALUE, destinatariosListaDistribucion);
		super.render(renderRequest, renderResponse);
	}


	@Override
	@ProcessAction(name = "sendForm")
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		String tipoLista = ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_TIPO_LISTA);
		String destinatarios;
		PortletPreferences preferences = actionRequest.getPreferences();
		if (tipoLista.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE)){ //Lista Controlada
			_log.debug("Entra en la condicion de Lista Controlada");
			destinatarios = ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_DESTINATARIOS);
			preferences.setValue("destinatariosListaControlada", destinatarios);
			preferences.store();
			SessionMessages.add(actionRequest, GestionBoletinesPortletKeys.MSG_OK_GUARDADO_DATOS);

			//Envio de pestaña activa a la jsp
			actionResponse.getRenderParameters().setValue("tabSelected", GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE);
		} else if(tipoLista.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_DISTRIBUCION_VALUE)){ //Lista Distribucion
			_log.debug("Entra en la condicion de Lista Distribuida");
			destinatarios = ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_DESTINATARIOS);
			preferences.setValue("destinatariosListaDistribucion", destinatarios);
			preferences.store();
			SessionMessages.add(actionRequest, GestionBoletinesPortletKeys.MSG_OK_GUARDADO_DATOS);

			//Envio de pestaña activa a la jsp
			actionResponse.getRenderParameters().setValue("tabSelected", GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_DISTRIBUCION_VALUE);
		} else { //Lista no valida
			_log.debug("Entra en la condicion de Lista no valida");
			SessionErrors.add(actionRequest, GestionBoletinesPortletKeys.ERROR_CAMPO_TIPO_LISTA);
			_log.error("Se ha enviado un tipo de Lista no esperado: " + tipoLista);
		}
	}

	private final static Log _log = LoggerUtil.getLog(GestionBoletinesPortlet.class);
}