package es.emasesa.intranet.portlet.configuraciones.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.configuraciones.constants.EmasesaConfiguracionesWebPortletKeys;
import es.emasesa.intranet.portlet.configuraciones.util.ConfigurationUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jacarmonam
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
			"javax.portlet.display-name=AyudaEscolarWebPortlet",
			"javax.portlet.expiration-cache=0",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/gestionAyudaEscolar/view.jsp",
			"javax.portlet.name=" + EmasesaConfiguracionesWebPortletKeys.GESTIONAYUDAESCOLARWEB,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
	},
	service = Portlet.class
)
public class AyudaEscolarWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		Date fechaInicio = _configurationUtil.getDateValue(EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_CONFIG_KEY_START_DAY);
		Date fechaFin = _configurationUtil.getDateValue(EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_CONFIG_KEY_END_DAY);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		renderRequest.setAttribute(EmasesaConfiguracionesWebPortletKeys.STAR_DATE, format.format(fechaInicio));
		renderRequest.setAttribute(EmasesaConfiguracionesWebPortletKeys.END_DATE,  format.format(fechaFin));

		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		boolean modifiedStartDate = false;
		boolean modifiedEndDate = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicio = ParamUtil.getDate(actionRequest, EmasesaConfiguracionesWebPortletKeys.STAR_DATE, format);
		Date fechaFin = ParamUtil.getDate(actionRequest, EmasesaConfiguracionesWebPortletKeys.END_DATE, format);

		modifiedStartDate = _configurationUtil.setDateValue(EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_CONFIG_KEY_START_DAY, fechaInicio);
		modifiedEndDate = _configurationUtil.setDateValue(EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_PID_CONFIG,EmasesaConfiguracionesWebPortletKeys.AYUDA_ESCOLAR_CONFIG_KEY_END_DAY, fechaFin);

		if (modifiedStartDate && modifiedEndDate){
			LoggerUtil.debug(LOG, "Los campos han sido modificados correctamente");
			SessionMessages.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_OK);
		} else {
			SessionErrors.add(actionRequest, EmasesaConfiguracionesWebPortletKeys.MSG_SAVE_KO);
			if (modifiedStartDate){
				LoggerUtil.error(LOG, "Ha ocurrido un error al modificar el campo Fecha de Inicio");
			} else if (modifiedEndDate){
				LoggerUtil.error(LOG, "Ha ocurrido un error al modificar el campo Fecha de Fin");
			}
		}
	}

	@Reference
	ConfigurationUtil _configurationUtil;

	private final static Log LOG = LoggerUtil.getLog(AyudaEscolarWebPortlet.class);
}