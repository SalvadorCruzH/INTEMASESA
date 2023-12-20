package es.emasesa.intranet.filters.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.filters.portlet.constants.EmasesaFiltersConstant;
import es.emasesa.intranet.settings.osgi.AyudaEscolarFormSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * @author jacarmonam
 */
@Component(
		immediate = true,
		property = { "servlet-context-name=",
				"servlet-filter-name=Filtro Solicitud Ayuda Escolar",
				"url-pattern=/mis-gestiones/nomina/ayuda-escolar/solicitud",
				"after-filter=Session Max Allowed Filter"
		},
		service = Filter.class
)
public class AyudaEscolarFilter extends BaseFilter {

	@Override
	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws Exception {
		LoggerUtil.debug(LOG,"Entra en el filtro de Ayuda escolar");

		LocalDate today = LocalDate.now();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EmasesaFiltersConstant.DATE_FORMAT);
			String startDateString = _ayudaEscolarFormSettings.startDateViewAyudaEscolarForm();

			LocalDate startDate = LocalDate.parse(startDateString, formatter);

			String endDateString = _ayudaEscolarFormSettings.endDateViewAyudaEscolarForm();
			LocalDate endDate = LocalDate.parse(endDateString, formatter);


			if (today.isBefore(startDate) || today.isAfter(endDate)) {
				LoggerUtil.debug(LOG, "Se cumple la condicion para la redireccion");
				httpServletResponse.sendRedirect("/mis-gestiones/nomina/ayuda-escolar");
				return;
			}

		} catch (DateTimeParseException e){
			LoggerUtil.error(LOG, "Ha ocurrido un error al Parsear las fechas de inicio y/o Fin guardadas en la configuracion del sistema.");
		}

		LoggerUtil.debug(LOG,"No se cumple la condicion para la redireccion");

		super.processFilter(httpServletRequest, httpServletResponse, filterChain);
	}

	@Override
	protected Log getLog() {
		return LOG;
	}

	@Reference
	private AyudaEscolarFormSettings _ayudaEscolarFormSettings;

	private static final Log LOG = LogFactoryUtil.getLog(AyudaEscolarFilter.class);
}