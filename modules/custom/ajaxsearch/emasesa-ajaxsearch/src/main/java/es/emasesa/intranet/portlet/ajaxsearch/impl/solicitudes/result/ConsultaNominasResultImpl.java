package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.result;

import com.fasterxml.jackson.core.JsonParser;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
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
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchJsonModel;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import es.emasesa.intranet.settings.osgi.RolesSettings;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.sigd.service.application.SigdServiceApplication;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Component(
        immediate = true,
        property = { },
        service = AjaxSearchResult.class
)
public class ConsultaNominasResultImpl implements AjaxSearchResult {

	private final static Log LOG = LoggerUtil.getLog(ConsultaNominasResultImpl.class);

	private static final Properties DFLT_PROPERTIES = new Properties();

	public static final String TEMPLATE_KEY = "template-key";
	public static final String STRUCTURE_KEY = "structure-key";
	public static final String CSS_WRAPPER_CLASS = "css-wrapper-class";
	public static final String DISABLE_PAGINATION = "disable-pagination";
	private static final String CAT_SELECTED = "catSelected";
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

            Calendar calendar = Calendar.getInstance();
            String year = calendar.get(Calendar.YEAR)+"";

			if(year.isBlank()){
				year = String.valueOf(Year.now().getValue());
			}
            totalItems = performSearchAndParse(request,
                response,
                ajaxSearchDisplayContext,
                    year,
                currentPage,
                pageSize,
                disablePagination,
                jsonArray);

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
									   final String year,
									   final int currentPage,
									   final int pageSize,
									   final boolean disablePagination,
									   final JSONArray jsonArray) throws ParseException, SearchException {

		final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		JSONArray array = null;
		int totalItems = 0;
		String matricula = "", urlNominaDefinitiva = "", urlNominaProvisional = "", urlUltimoRecalculo="", fechaNomina="", fechaRecalculo="";

		try {
			matricula = _expandoValueLocalService.getData(
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

		String listadoNominas = _sigdServiceApplication.buscarDocumento(matricula);
		try {
			JSONArray elementos = JSONFactoryUtil.createJSONObject(listadoNominas).getJSONObject("buscarDocumentosResponse").getJSONArray("elementos");
			JSONArray nominas = JSONFactoryUtil.createJSONArray("");
			if (elementos != null) {
				for (int i = 0; i < elementos.length(); i++) {
					JSONObject nomina = JSONFactoryUtil.createJSONObject("");
					JSONObject documentoOrigen = elementos.getJSONObject(i).getJSONObject("documentoOrigen");
					JSONArray campos = elementos.getJSONObject(i).getJSONObject("documentoOrigen").getJSONArray("campos");
					String codigoTipoDocumental = documentoOrigen.getString("codigoTipoDocumental");

					if ("7972".equals(codigoTipoDocumental)) {
						for (int j = 0; j < campos.length(); j++) {
							String nombreOrigen = campos.getJSONObject(j).getString("nombreOrigen");
							if ("FechaNomina".equals(nombreOrigen)) {
								nomina.put("urlNominaDefinitiva",documentoOrigen.getString("urlDescarga"));
								nomina.put("fechaNomina", campos.getJSONObject(j).getString("dateValue"));
								nominas.put(nomina);
								break;
							}
						}

					} else if ("7971".equals(codigoTipoDocumental)) {
						for (int b = 0; b < campos.length(); b++) {
							String nombreOrigen = campos.getJSONObject(b).getString("nombreOrigen");
							if ("FechaNomina".equals(nombreOrigen)) {
								nomina.put("urlNominaProvisional", urlNominaProvisional = documentoOrigen.getString("urlDescarga"));
								nomina.put("fechaNomina", campos.getJSONObject(b).getString("dateValue"));
								nominas.put(nomina);
								break;
							}
						}

					} else if ("7973".equals(codigoTipoDocumental)) {
						for (int j = 0; j < campos.length(); j++) {
							String nombreOrigen = campos.getJSONObject(j).getString("nombreOrigen");
							if ("FechaNomina".equals(nombreOrigen)) {
								nomina.put("urlUltimoRecalculo", documentoOrigen.getString("urlDescarga"));
								nomina.put("fechaNomina", campos.getJSONObject(j).getString("dateValue"));
								nominas.put(nomina);
								nomina = JSONFactoryUtil.createJSONObject("");
								nomina.put("fechaNomina", campos.getJSONObject(j).getString("dateValue"));
							}
							if ("Fecharecalculo".equals(nombreOrigen)) {
								nomina.put("fechaRecalculo", campos.getJSONObject(j).getString("dateValue"));
								nominas.put(nomina);
								break;
							}
						}
					}
				}
				Map<String, List<JSONObject>> nominasPorFecha = new HashMap<>();

				for (int i = 0; i < nominas.length(); i++) {
					fechaNomina = nominas.getJSONObject(i).getString("fechaNomina");
					JSONObject nominasAgrupadas = JSONFactoryUtil.createJSONObject("");
					nominasAgrupadas.put("fechaNomina", fechaNomina);

					if (!nominasPorFecha.containsKey(fechaNomina)) {
						nominasPorFecha.put(fechaNomina, new ArrayList<>());
					}

					if (nominas.getJSONObject(i).has("urlNominaDefinitiva")) {
						nominasAgrupadas.put("urlNominaDefinitiva", nominas.getJSONObject(i).getString("urlNominaDefinitiva"));
					}
					if (nominas.getJSONObject(i).has("urlNominaProvisional")) {
						nominasAgrupadas.put("urlNominaProvisional", nominas.getJSONObject(i).getString("urlNominaProvisional"));
					}
					if (nominas.getJSONObject(i).has("urlUltimoRecalculo")) {
						nominasAgrupadas.put("urlUltimoRecalculo", nominas.getJSONObject(i).getString("urlUltimoRecalculo"));
					}
					nominasPorFecha.get(fechaNomina).add(nominas.getJSONObject(i));
				}

				JSONArray nominasArray = JSONFactoryUtil.createJSONArray();
				for (Map.Entry<String, List<JSONObject>> entry : nominasPorFecha.entrySet()) {
					JSONObject nominaFinal = JSONFactoryUtil.createJSONObject("");

					List<JSONObject> ListaNominas = entry.getValue();
					fechaNomina = entry.getKey();
					String fechaFormateada = formatearFecha(fechaNomina);
					nominaFinal.put("fechaNomina", fechaFormateada);

					for (JSONObject nominasElemento : ListaNominas) {
						if (nominasElemento.has("urlNominaDefinitiva")) {
							nominaFinal.put("urlNominaDefinitiva", nominasElemento.getString("urlNominaDefinitiva"));
						}
						if (nominasElemento.has("urlNominaProvisional")) {
							nominaFinal.put("urlNominaProvisional", nominasElemento.getString("urlNominaProvisional"));
						}
						if (nominasElemento.has("urlUltimoRecalculo")) {
							nominaFinal.put("urlUltimoRecalculo", nominasElemento.getString("urlUltimoRecalculo"));
						}
						if (nominasElemento.has("fechaRecalculo")) {
							fechaFormateada = formatearFecha(nominasElemento.getString("fechaRecalculo"));
							nominaFinal.put("fechaRecalculo", fechaFormateada);
						}
					}
					nominasArray.put(nominaFinal);
				}
				final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
				int count = (currentPage * pageSize) > nominasArray.length() ? nominasArray.length() : (currentPage * pageSize);
				final int end = disablePagination ? pageSize : count;

				totalItems = nominasArray.length();
				List<JSONObject> listJson = new ArrayList<>();
				for(int i = 0;i<nominasArray.length();i++){

					listJson.add(nominasArray.getJSONObject(i));
				}

				listJson.subList(start,end).stream().forEach(j->{
					jsonArray.put(j);
				});
			}

		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
		return totalItems;
	}

	private static final String VIEW = "/views/solicitudes/nominas/results.jsp";

	public static String formatearFecha(String fechaNomina) {
		try {
			Date date = new Date(Long.parseLong(fechaNomina));
			SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy");
			return sdf.format(date);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir la cadena de fecha a long: " + e.getMessage());
			return null;
		}
	}

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
	SigdServiceApplication _sigdServiceApplication;
	@Reference
	CustomCacheSingleUtil _cache;
	@Reference
	RolesSettings _rolesSettings;
	@Reference
	ExpandoValueLocalService _expandoValueLocalService;
}
