package es.emasesa.intranet.portlet.configuraciones.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.configuraciones.constants.EmasesaConfiguracionesWebPortletKeys;
import es.emasesa.intranet.portlet.configuraciones.util.ConfigurationUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;

/**
 * @author scruzh
 */
@Component(
	property = {
			"com.liferay.portlet.add-default-resource=true",
			"com.liferay.portlet.display-category=category.hidden",
			"com.liferay.portlet.layout-cacheable=true",
			"com.liferay.portlet.private-request-attributes=false",
			"com.liferay.portlet.private-session-attributes=false",
			"com.liferay.portlet.render-weight=50",
			"com.liferay.portlet.use-default-template=true",
			"javax.portlet.display-name=GestionTitulosComunicacionWebPortlet",
			"javax.portlet.expiration-cache=0",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/gestionTitulosComunicacion/view.jsp",
			"javax.portlet.name=" + EmasesaConfiguracionesWebPortletKeys.GESTIONTITULOSCOMUNICACIONWEB,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
	},
	service = Portlet.class
)
public class GestionTitulosComunicacionWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		String jspPage = ParamUtil.getString(renderRequest, "jspPage");
		JSONArray configValue = _configurationUtil.getJSONArrayValue(EmasesaConfiguracionesWebPortletKeys.TIPOLOGIAS_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.TITULOS_COMUNICACION_CONFIG_KEY);
		renderRequest.setAttribute(EmasesaConfiguracionesWebPortletKeys.CONFIG_VALUE, configValue);
		List<Role> roles = getRoles();
		renderRequest.setAttribute("roles", roles);
		if (Validator.isNotNull(jspPage)) {
			if (ParamUtil.getString(renderRequest, EmasesaConfiguracionesWebPortletKeys.TITULO).isEmpty()) {
				renderRequest.setAttribute(EmasesaConfiguracionesWebPortletKeys.ACTION, EmasesaConfiguracionesWebPortletKeys.ACTION_ADD);
			} else{
				renderRequest.setAttribute(EmasesaConfiguracionesWebPortletKeys.ACTION, EmasesaConfiguracionesWebPortletKeys.ACTION_EDIT);
			}
			include(jspPage, renderRequest, renderResponse);
		} else {
			super.doView(renderRequest, renderResponse);
		}
	}

	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		String action = actionRequest.getParameter(EmasesaConfiguracionesWebPortletKeys.ACTION);
		boolean success;
		String old_titulo = actionRequest.getParameter(EmasesaConfiguracionesWebPortletKeys.OLD_TITULO);
		old_titulo = (old_titulo != null) ? old_titulo : StringPool.BLANK;

		String titulo = actionRequest.getParameter(EmasesaConfiguracionesWebPortletKeys.TITULO);
		titulo = (titulo != null) ? titulo : StringPool.BLANK;

		String usuario = actionRequest.getParameter(EmasesaConfiguracionesWebPortletKeys.USUARIO);
		usuario = (usuario != null) ? usuario : StringPool.BLANK;

		switch (action){
			case "delete":
				success = deleteItem(titulo);
				if (success) {
					SessionMessages.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_DELETE_OK);
				} else {
					SessionErrors.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_DELETE_KO);
				}
				break;
			case "add":
				success = addItem(titulo, usuario);
				if (success) {
					SessionMessages.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_OK);
				} else {
					SessionErrors.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_KO);
				}
				break;
			case "edit":
				success = editItem(old_titulo, titulo, usuario);
				if (success) {
					SessionMessages.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_OK);
				} else {
					SessionErrors.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_KO);
				}
				break;
		}

	}

	private boolean deleteItem(String searchTitulo){
		boolean deleted = false;
		JSONArray actualConfigValue = _configurationUtil.getJSONArrayValue(EmasesaConfiguracionesWebPortletKeys.TIPOLOGIAS_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.TITULOS_COMUNICACION_CONFIG_KEY);

		JSONArray newConfigValue = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < actualConfigValue.length(); i++) {
			JSONObject jsonObject = actualConfigValue.getJSONObject(i);
			String title = jsonObject.getString(EmasesaConfiguracionesWebPortletKeys.TITULO);

			if (!searchTitulo.equals(title)) {
				newConfigValue.put(jsonObject);
			} else {
				deleted = true;
				LoggerUtil.debug(LOG, "No se ha encontrado Un registro con la Titulos: " + searchTitulo);
			}
		}
		if (deleted) deleted = _configurationUtil.setJSONArrayValue(EmasesaConfiguracionesWebPortletKeys.TIPOLOGIAS_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.TITULOS_COMUNICACION_CONFIG_KEY, newConfigValue);

		return deleted;
	}

	private boolean addItem(String searchTitulo, String usuario){
		boolean added = true;
		JSONArray actualConfigValue = _configurationUtil.getJSONArrayValue(EmasesaConfiguracionesWebPortletKeys.TIPOLOGIAS_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.TITULOS_COMUNICACION_CONFIG_KEY);

		JSONArray newConfigValue = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < actualConfigValue.length(); i++) {
			JSONObject jsonObject = actualConfigValue.getJSONObject(i);
			String title = jsonObject.getString(EmasesaConfiguracionesWebPortletKeys.TITULO);

			if (searchTitulo.equals(title)) {
				added = false;
				LoggerUtil.debug(LOG, "Ya existe un registro con la Título: " + searchTitulo);
			} else {
				newConfigValue.put(jsonObject);
			}
		}
		if (added) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put(EmasesaConfiguracionesWebPortletKeys.TITULO, searchTitulo);
			jsonObject.put(EmasesaConfiguracionesWebPortletKeys.USUARIO, usuario);

			newConfigValue.put(jsonObject);
			added = _configurationUtil.setJSONArrayValue(EmasesaConfiguracionesWebPortletKeys.TIPOLOGIAS_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.TITULOS_COMUNICACION_CONFIG_KEY, newConfigValue);
		}

		return added;
	}

	private boolean editItem(String oldTitulo, String searchTitulo, String searchUsuario){
		boolean modified = false;
		JSONArray actualConfigValue = _configurationUtil.getJSONArrayValue(EmasesaConfiguracionesWebPortletKeys.TIPOLOGIAS_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.TITULOS_COMUNICACION_CONFIG_KEY);

		JSONArray newConfigValue = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < actualConfigValue.length(); i++) {
			JSONObject jsonObject = actualConfigValue.getJSONObject(i);
			String title = jsonObject.getString(EmasesaConfiguracionesWebPortletKeys.TITULO);

			if (oldTitulo.equals(title)) {
				jsonObject.put(EmasesaConfiguracionesWebPortletKeys.TITULO, searchTitulo);
				jsonObject.put(EmasesaConfiguracionesWebPortletKeys.USUARIO, searchUsuario);
				newConfigValue.put(jsonObject);
				modified = true;
			} else {
				newConfigValue.put(jsonObject);
				LoggerUtil.debug(LOG, "No se ha encontrado un registro con la Título: " + searchTitulo);
			}
		}
		if (modified) modified = _configurationUtil.setJSONArrayValue(EmasesaConfiguracionesWebPortletKeys.TIPOLOGIAS_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.TITULOS_COMUNICACION_CONFIG_KEY, newConfigValue);

		return modified;
	}

	private List<Role> getRoles(){
		return RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Reference
	ConfigurationUtil _configurationUtil;

	private final static Log LOG = LoggerUtil.getLog(GestionTitulosComunicacionWebPortlet.class);
}