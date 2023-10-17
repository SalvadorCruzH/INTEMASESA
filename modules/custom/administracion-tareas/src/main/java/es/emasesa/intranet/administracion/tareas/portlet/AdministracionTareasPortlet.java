package es.emasesa.intranet.administracion.tareas.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import javax.portlet.Portlet;
import es.emasesa.intranet.administracion.tareas.constants.AdministracionTareasPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
        configurationPid = "es.emasesa.intranet.administracion.tareas.portlet.AdministracionTareasConfiguration",
        property = {
                "com.liferay.portlet.display-category=category.emasesa",
                "com.liferay.portlet.friendly-url-mapping=AdministracionTareas",
                "javax.portlet.display-name=AdministracionTareas",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + AdministracionTareasPortletKeys.ADMINISTRACIONTAREAS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user",
                "javax.portlet.version=3.0"
        },
        service = Portlet.class
)
public class AdministracionTareasPortlet extends MVCPortlet {

}