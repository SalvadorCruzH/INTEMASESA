package es.emasesa.intranet.portlet.ajaxsearch.impl.jornadadiaria.result;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.base.model.AjaxMessage;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import es.emasesa.intranet.base.util.CustomDateUtil;
import es.emasesa.intranet.base.util.CustomExpandoUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.impl.resumenanual.result.ResumenAnualResultImpl;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchJsonModel;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import es.emasesa.intranet.settings.osgi.RolesSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = { },
        service = AjaxSearchResult.class
)
public class JornadaDiariaResultImpl implements AjaxSearchResult {

	private final static Log LOG = LoggerUtil.getLog(JornadaDiariaResultImpl.class);

	private static final Properties DFLT_PROPERTIES = new Properties();

	public static final String TEMPLATE_KEY = "template-key";
	public static final String STRUCTURE_KEY = "structure-key";
	public static final String CSS_WRAPPER_CLASS = "css-wrapper-class";
	public static final String DISABLE_PAGINATION = "disable-pagination";
	private static final String MONTH_SELECTED = "monthSelected";
	private static final String FECHA_DESDE = "fechaDesde";
	private static final String FECHA_HASTA = "fechaHasta";



	static {
		DFLT_PROPERTIES.put(AjaxSearchPortletKeys.PROP_BASE_PAGESIZE,
				AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT);

		DFLT_PROPERTIES.put(TEMPLATE_KEY, "EMA-NOTICIA-TARJETA-TEMPLATE");
		DFLT_PROPERTIES.put(STRUCTURE_KEY, "EMA-NOTICIA");
		DFLT_PROPERTIES.put(CSS_WRAPPER_CLASS, "");
		DFLT_PROPERTIES.put(DISABLE_PAGINATION, "0");
	}

	@Override
	public Properties getDefaultProperties() {
		return DFLT_PROPERTIES;
	}

	@Override
	public AjaxMessage filterResults(PortletRequest request,
									 PortletResponse response,
									 AjaxSearchDisplayContext ajaxSearchDisplayContext) {

		try {
			long totalItems = 0;
			final int currentPage = ajaxSearchDisplayContext.getCurrentPage();
			final int pageSize = ajaxSearchDisplayContext.getPageSize();
			final JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			final String disablePaginationStr = ajaxSearchDisplayContext.getConfig().getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO);
			final boolean disablePagination = !Validator.isBlank(disablePaginationStr) && disablePaginationStr.equals("1");

			String monthSelected = ajaxSearchDisplayContext.getString(MONTH_SELECTED);
			if(!monthSelected.equals("resumenanual") && !monthSelected.equals("resumenanualpasado")){
				totalItems = performSearchAndParse(request,
						response,
						ajaxSearchDisplayContext,
						monthSelected,
						currentPage,
						pageSize,
						disablePagination,
						jsonArray);
			}

			final int totalPages = (((int) totalItems + pageSize - 1) / pageSize);
			final AjaxSearchJsonModel ajaxSearchJsonModel = new AjaxSearchJsonModel(
					currentPage,
					totalPages,
					totalItems,
					(disablePagination? 0: pageSize),
					jsonArray);
			return new AjaxMessage(
					AjaxMessage.STATUS.OK,
					AjaxMessage.MESSAGES_DEFAULT.OK,
					ajaxSearchJsonModel.getJsonObject());

		} catch (ParseException | SearchException e) {
			LOG.info("Error At search in indexes: " + e.getLocalizedMessage());
			final AjaxSearchJsonModel ajaxSearchJsonModel = new AjaxSearchJsonModel(0, 0, 0L, 0, JSONFactoryUtil.createJSONArray());
			return new AjaxMessage(
					AjaxMessage.STATUS.ERROR_GENERAL,
					AjaxMessage.MESSAGES_DEFAULT.ERROR_GENERAL,
					ajaxSearchJsonModel.getJsonObject());
		}
	}

	private static final Date NULL_DATE=null;
	private static final List<String> EMPTY_LIST=new ArrayList<>();

	private long performSearchAndParse(final PortletRequest request,
									   final PortletResponse response,
									   final AjaxSearchDisplayContext ajaxSearchDisplayContext,
									   final String monthSelected,
									   final int currentPage,
									   final int pageSize,
									   final boolean disablePagination,
									   final JSONArray jsonArray) throws ParseException, SearchException {

		final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		JSONArray array;
		int totalItems = 0;

		String usuario = ajaxSearchDisplayContext.getString("usuarioSelected", StringPool.BLANK);
		String matriculaExpando = StringPool.BLANK;
		try {
			matriculaExpando = _expandoValueLocalService.getData(
					themeDisplay.getCompanyId(),
					User.class.getName(),
					ExpandoTableConstants.DEFAULT_TABLE_NAME,
					"matricula",
					themeDisplay.getUser().getUserId(),
					StringPool.BLANK
			);
		} catch (Exception e) {
			LoggerUtil.error(LOG, "ERROR getValue from Expando", e);
		}
		boolean isValidUser;
		if (usuario.isBlank()){
			usuario = matriculaExpando;
			isValidUser = Boolean.TRUE;
		} else {
			isValidUser = checkUsuarioSelected(usuario, themeDisplay.getUser(), matriculaExpando);
		}

		if(Validator.isNotNull(monthSelected) && isValidUser){
			String startDate ="";
			String endDate ="";

			YearMonth yearMonth = YearMonth.parse(monthSelected, DateTimeFormatter.ofPattern("MMyyyy"));
			LocalDate month= yearMonth.atEndOfMonth();
			LocalDate firstDay = month.with(TemporalAdjusters.firstDayOfMonth());
			LocalDate lastDay = month.with(TemporalAdjusters.lastDayOfMonth());
			LocalDate today = LocalDate.now();

			if (lastDay.isAfter(today)){
				lastDay = today;
			}
			startDate = firstDay.format(sdf);
			endDate = lastDay.format(sdf);


			String cacheKey = "jornadaDiaria_" + startDate + StringPool.DASH + endDate + StringPool.UNDERLINE + usuario;
			Object object = _cache.get(cacheKey);

			if(Validator.isNotNull(object) && ((JSONArray) object).length()>0){
				array = (JSONArray) object;

			}else{
				array = _sapServicesUtil.getJornadaDiaria(usuario, startDate, endDate);
				_cache.put(cacheKey,array,86400);

			}

			final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
			int count = (currentPage * pageSize) > array.length() ? array.length() : (currentPage * pageSize);
			final int end = disablePagination ? pageSize : count;

			totalItems = array.length();
			List<JSONObject> listJson = new ArrayList<>();
			for(int i = 0;i<array.length();i++){

				listJson.add(array.getJSONObject(i));
			}

			listJson.subList(start,end).stream().forEach(j->{

				try {
					if (j.getString("SEMAFORO").equals("R")) {
						j.put("tooltip", "Jornada Incompleta");
					} else if (j.getString("SEMAFORO").equals("V")) {
						j.put("tooltip", "Jornada con Superávit");
					} else {
						j.put("tooltip", "");
					}
					j.put("dia", getDatum(themeDisplay, j));
					jsonArray.put(j);
				} catch (java.text.ParseException e) {
					LoggerUtil.info(LOG,e.getMessage());
				}

			});
			if(jsonArray.length() == 0){
				jsonArray.put(JSONFactoryUtil.createJSONObject());
			}
			String actualYear = String.valueOf(Year.now().getValue());
			LoggerUtil.debug(LOG, "[D] Consiguiendo vacaciones del usuario " + usuario + " para el año " + actualYear);
			jsonArray.getJSONObject(0).put("vacacionesYear", getVacaciones(usuario, actualYear));
			LoggerUtil.debug(LOG, "[D] Consiguiendo vacaciones del usuario " + usuario + " para el año " + (Integer.parseInt(actualYear) - 1));
			jsonArray.getJSONObject(0).put("vacacionesLastYear", getVacaciones(usuario, String.valueOf(Integer.parseInt(actualYear)-1)));
		}
		return totalItems;
	}

	private JSONObject getVacaciones(String matriculaUser, String year) {

		String cacheKeyYear = "resumenAnual"+year+matriculaUser;
		Object objectYear = _cache.get(cacheKeyYear);
		JSONArray arrayYear;
		if(Validator.isNotNull(objectYear) && ((JSONArray) objectYear).length()>0){
			arrayYear = (JSONArray) objectYear;

		}else{

			arrayYear = _sapServicesUtil.getResumenAnual(matriculaUser,year);
			_cache.put(cacheKeyYear,arrayYear,86400);

		}
		JSONObject vacacionesYear = JSONFactoryUtil.createJSONObject();
		if (arrayYear.length() > 0) {
			String diasComputoConFuturo = calculateDays(arrayYear.getJSONObject(0).getString("computoConFuturo", "0"), arrayYear.getJSONObject(0).getString("DURACION_JORNADA_TEORICA", "7.25"));
			String diasComputoSinFuturo = calculateDays(arrayYear.getJSONObject(0).getString("computoSinFuturo", "0"), arrayYear.getJSONObject(0).getString("DURACION_JORNADA_TEORICA", "7.25"));
			vacacionesYear.put("computoConFuturo", diasComputoConFuturo);
			vacacionesYear.put("computoSinFuturo", diasComputoSinFuturo);
			vacacionesYear.put("contingenteVacaciones", arrayYear.getJSONObject(0).getString("contingenteVacaciones"));
		}
		return vacacionesYear;
	}

	private String getDatum(ThemeDisplay themeDisplay, JSONObject j) throws java.text.ParseException {
		return _customDateUtil.getDateFieldDisplayName(themeDisplay.getLocale(),
				j.getString("DATUM"), "yyyy-MM-dd", Calendar.DAY_OF_WEEK,Calendar.SHORT) + StringPool.SPACE+
				_customDateUtil.getDateNumber(j.getString("DATUM"), "yyyy-MM-dd");
	}

	private String calculateDays(String horasTotales, String horasDia) {

		double horasOriginal = Double.parseDouble(horasTotales);
		double horasAlDia = Double.parseDouble(horasDia);

		// Calcular días, horas y minutos
		int dias = (int) (horasOriginal / horasAlDia);
		int horas = (int) (horasOriginal % horasAlDia);
		int minutos = (int) ((horasOriginal % horasAlDia - horas) * 60);
		return dias + "d " + horas + "h " + minutos + "m";
	}

	private boolean checkUsuarioSelected(String usuarioSelected, User user, String matricula) {
		String cacheKey = "checkUsuarioSelected" + usuarioSelected + matricula;
		Object object = _cache.get(cacheKey);
		if(Validator.isNotNull(object)){
			return (Boolean) object;
		} else {
			Boolean isValidUser = checkUsuarioSelectedAux(usuarioSelected, user, matricula);
			_cache.put(cacheKey, isValidUser, 86400);
			return isValidUser;
		}
	}

	private Boolean checkUsuarioSelectedAux(String usuarioSelected, User user, String matricula) {
		long[] userRoleIds = user.getRoleIds();
		for (long roleId : userRoleIds) {
			if(roleId == _rolesSettings.administradorRRHHId()){
				return Boolean.TRUE;
			}
		}

		JSONArray subordinados = _sapServicesUtil.getSubordinados(matricula, "T");
		if (subordinados.length() > 0){

			for (int i = 0; i < subordinados.length(); i++) {
				String subordinado = subordinados.getJSONObject(i).getString("pernr");
				if(subordinado.equals(usuarioSelected)){
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	private static final String VIEW = "/views/jornadadiaria/results.jsp";

	@Override
	public String getResultsView(PortletRequest request, PortletResponse response) {
		final Map<String, String> config = ajaxSearchUtil.getPropertiesMap(request);
		request.setAttribute("cssWrapperClass", config.getOrDefault(CSS_WRAPPER_CLASS, StringPool.BLANK));
		request.setAttribute("disablePagination", config.getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO));
		return VIEW;
	}

	@Reference
	AjaxSearchUtil ajaxSearchUtil;

	@Reference
	SapServicesUtil _sapServicesUtil;
	@Reference
	CustomDateUtil _customDateUtil;
	@Reference
	CustomCacheSingleUtil _cache;
	@Reference
	RolesSettings _rolesSettings;
	@Reference
	ExpandoValueLocalService _expandoValueLocalService;
}
