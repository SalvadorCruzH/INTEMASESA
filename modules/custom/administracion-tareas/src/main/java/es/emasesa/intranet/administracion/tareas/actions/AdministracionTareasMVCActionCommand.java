package es.emasesa.intranet.administracion.tareas.actions;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import es.emasesa.intranet.administracion.tareas.constants.AdministracionTareasPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdministracionTareasPortletKeys.ADMINISTRACIONTAREAS,
                "mvc.command.name=/adminTask"
        },
        service = MVCActionCommand.class

)
public class AdministracionTareasMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        String url = ParamUtil.getString(actionRequest, "sel_solicitud");
        actionResponse.getRenderParameters().setValue("sel_solicitud", url);
        actionResponse.getRenderParameters().setValue("mvcRenderCommandName", "/administrarTareasSinAsignar");
        return false;
    }
}
