package es.emasesa.intranet.filters.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.filters.portlet.constants.EmasesaFiltersConstant;
import es.emasesa.intranet.settings.osgi.DNFFormSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


/**
 * @author jamoron
 */
@Component(
		immediate = true,
		property = { "servlet-context-name=",
				"servlet-filter-name=Filtro DNF",
				"url-pattern=/es/group/guest/formacion/deteccion-de-necesidades-de-formacion/gestion-de-necesidades-de-formacion/solicitud",
				"url-pattern=/group/guest/formacion/deteccion-de-necesidades-de-formacion/gestion-de-necesidades-de-formacion/solicitud",
				"url-pattern=/group/guest/formacion/deteccion-de-necesidades-de-formacion/gestion-de-necesidades-de-formacion",
				"url-pattern=/es/group/guest/formacion/deteccion-de-necesidades-de-formacion/gestion-de-necesidades-de-formacion",
				"after-filter=Session Max Allowed Filter"
		},
		service = Filter.class
)
public class DNFFilter extends BaseFilter {

	@Override
	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws Exception {
		LoggerUtil.debug(LOG,"Entra en el filtro de DNF");

		LocalDate today = LocalDate.now();
		long idRol = 0;

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EmasesaFiltersConstant.DATE_FORMAT);
			String startDateString = _DNFFormSettings.startDateViewDNFForm();

			Long rolFormacion = _DNFFormSettings.deptFormacionId();
			LoggerUtil.debug(LOG,"obtiene el rol de formacion: " + rolFormacion);

			LocalDate startDate = LocalDate.parse(startDateString, formatter);

			String endDateString = _DNFFormSettings.endDateViewDNFForm();
			LocalDate endDate = LocalDate.parse(endDateString, formatter);
			LoggerUtil.debug(LOG, "inicio: " + startDate + " fin: " + endDate);

			if (httpServletRequest.getRequestURL().toString().contains("/solicitud")) {
				if (today.isBefore(startDate) || today.isAfter(endDate)) {
					LoggerUtil.debug(LOG, "Se cumple la condicion para la redireccion de /solicitud");
					if (httpServletRequest.getRequestURL().toString().contains("/es/group/guest/")) {
						httpServletResponse.sendRedirect("/es/group/guest/formacion/deteccion-de-necesidades-de-formacion/gestion-necesidades-de-formacion");
					} else if (httpServletRequest.getRequestURL().toString().contains("/group/guest/")){
						httpServletResponse.sendRedirect("/group/guest/formacion/deteccion-de-necesidades-de-formacion/gestion-de-necesidades-de-formacion");
					}
					return;
				}
			}else{
				LoggerUtil.debug(LOG, "acceso a modo consulta");
				List<UserGroupRole> rolUser = _userGroupRoleLocalService.getUserGroupRoles((Long) httpServletRequest.getSession().getAttribute("USER_ID"));
				LoggerUtil.debug(LOG,"se obtiene el rolId: " + rolUser);
				if (rolUser.size() == 1){
					idRol = rolUser.get(0).getRoleId();
				}else if (rolUser.size() > 1){
					for (UserGroupRole userGroupRole : rolUser) {
						if (userGroupRole.getRoleId() == rolFormacion){
							idRol = userGroupRole.getRoleId();
							break;
						}
					}
				}

				if (rolFormacion != idRol) {
					LocalDate extendedEndDate = calculateExtendedEndDate(endDate);

					if (today.isBefore(startDate) || today.isAfter(extendedEndDate)) {
						LoggerUtil.debug(LOG, "Se cumple la condicion para la redireccion de /gestion-necesidades-de-formacion");
						if (httpServletRequest.getRequestURL().toString().contains("/es/group/guest/")) {
							httpServletResponse.sendRedirect("/group/guest/inicio");
						} else {
							httpServletResponse.sendRedirect("/group/guest/inicio");
						}
						return;
					}
				}
			}

		} catch (DateTimeParseException e){
			LoggerUtil.error(LOG, "Ha ocurrido un error al Parsear las fechas de inicio y/o Fin guardadas en la configuracion del sistema.");
		}

		LoggerUtil.debug(LOG,"No se cumple la condicion para la redireccion");

		super.processFilter(httpServletRequest, httpServletResponse, filterChain);
	}

	private LocalDate calculateExtendedEndDate(LocalDate originalEndDate) {
		return originalEndDate.plusDays(30);
	}

	@Override
	protected Log getLog() {
		return LOG;
	}

	@Reference
	private DNFFormSettings _DNFFormSettings;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	private static final Log LOG = LogFactoryUtil.getLog(DNFFilter.class);

}