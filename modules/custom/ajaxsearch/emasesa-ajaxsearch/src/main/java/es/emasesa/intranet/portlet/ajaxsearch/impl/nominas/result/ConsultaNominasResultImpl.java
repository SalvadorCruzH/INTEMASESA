package es.emasesa.intranet.portlet.ajaxsearch.impl.nominas.result;

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
import es.emasesa.intranet.base.servlet.HeartBeatServlet;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import es.emasesa.intranet.base.util.CustomDateUtil;
import es.emasesa.intranet.base.util.CustomExpandoUtil;
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
import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import es.emasesa.intranet.portlet.ajaxsearch.impl.nominas.constans.NominasKeys;

@Component(
		immediate = true,
		property = {},
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
	private static final String MATRICULA = "matricula";



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
			final JSONObject zipObject = JSONFactoryUtil.createJSONObject();


			final String disablePaginationStr = ajaxSearchDisplayContext.getConfig().getOrDefault(DISABLE_PAGINATION, StringConstants.ZERO);
			final boolean disablePagination = !Validator.isBlank(disablePaginationStr) && disablePaginationStr.equals("1");
			final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			Date fromDate = ajaxSearchDisplayContext.getDate(FECHA_DESDE);
			Date toDate = ajaxSearchDisplayContext.getDate(FECHA_HASTA);
			String matricula = ajaxSearchDisplayContext.getString(MATRICULA);

			if(matricula.equals("")){
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
			}


			totalItems = performSearchAndParse(request,
					response,
					ajaxSearchDisplayContext,
					currentPage,
					pageSize,
					disablePagination,
					jsonArray,
					matricula,
					fromDate,
					toDate,
					zipObject);

			final int totalPages = (((int) totalItems + pageSize - 1) / pageSize);
			final AjaxSearchJsonModel ajaxSearchJsonModel = new AjaxSearchJsonModel(
					currentPage,
					totalPages,
					totalItems,
					jsonArray,
					zipObject);
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
									   final int currentPage,
									   final int pageSize,
									   final boolean disablePagination,
									   final JSONArray jsonArray,
									   final String matricula,
									   final Date fromDate,
									   final Date toDate,
									   final JSONObject zipObject) throws ParseException, SearchException {

		int totalItems = 0;
		String urlNominaDefinitiva = "", urlNominaProvisional = "", urlUltimoRecalculo="", fechaNomina="", fechaRecalculo="";

		Long fromDateMilisegundos = (fromDate != null) ? fromDate.getTime(): 0;
		String desde = (fromDateMilisegundos != 0) ? fromDateMilisegundos.toString(): "";
		desde = (desde != null) ? formatearFecha(desde) : "";
		Long toDateMilisegundos = (toDate != null) ? toDate.getTime(): 0;
		String hasta = (toDateMilisegundos != 0) ? toDateMilisegundos.toString() : "";
		hasta = (hasta != null) ? formatearFecha(hasta) : "";

		String listadoNominas = _sigdServiceApplication.buscarDocumento(matricula);
		_log.debug("Inicio del manejo del endPoint");
		try {
			JSONArray elementos = JSONFactoryUtil.createJSONObject(listadoNominas).getJSONObject("buscarDocumentosResponse").getJSONArray("elementos");
			JSONArray nominas = JSONFactoryUtil.createJSONArray("");
			JSONArray urlDescargaArray = JSONFactoryUtil.createJSONArray();
			if (elementos != null) {

				for (int i = 0; i < elementos.length(); i++) {
					JSONObject nomina = JSONFactoryUtil.createJSONObject("");
					JSONObject documentoOrigen = elementos.getJSONObject(i).getJSONObject(NominasKeys.DOCUMENTO_ORIGEN);
					JSONArray campos = elementos.getJSONObject(i).getJSONObject(NominasKeys.DOCUMENTO_ORIGEN).getJSONArray("campos");
					String codigoTipoDocumental = documentoOrigen.getString("codigoTipoDocumental");

					if ("7972".equals(codigoTipoDocumental)) {
						for (int j = 0; j < campos.length(); j++) {
							String nombreOrigen = campos.getJSONObject(j).getString(NominasKeys.NOMBRE_ORIGEN);
							if (NominasKeys.FECHA_NOMINA.equals(nombreOrigen)) {
								nomina.put(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA,documentoOrigen.getString(NominasKeys.CAMPO_URL_VISOR));
								nomina.put(NominasKeys.CAMPO_FECHA_NOMINA, formatearFecha(campos.getJSONObject(j).getString(NominasKeys.DATE_VALUE)));
								nominas.put(nomina);
								//urlDescargaArray.put(nomina);
								break;
							}
						}

					} else if ("7971".equals(codigoTipoDocumental)) {
						for (int b = 0; b < campos.length(); b++) {
							String nombreOrigen = campos.getJSONObject(b).getString(NominasKeys.NOMBRE_ORIGEN);
							if (NominasKeys.FECHA_NOMINA.equals(nombreOrigen)) {
								nomina.put(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL, documentoOrigen.getString(NominasKeys.CAMPO_URL_VISOR));
								nomina.put(NominasKeys.CAMPO_FECHA_NOMINA, formatearFecha(campos.getJSONObject(b).getString(NominasKeys.DATE_VALUE)));
								nominas.put(nomina);
								//urlDescargaArray.put(nomina);
								break;
							}
						}

					} else if ("7973".equals(codigoTipoDocumental)) {
						for (int j = 0; j < campos.length(); j++) {
							String nombreOrigen = campos.getJSONObject(j).getString(NominasKeys.NOMBRE_ORIGEN);
							if (NominasKeys.FECHA_NOMINA.equals(nombreOrigen)) {
								nomina.put(NominasKeys.CAMPO_URL_NOMINA_RECALCULO, documentoOrigen.getString(NominasKeys.CAMPO_URL_VISOR));
								nomina.put(NominasKeys.CAMPO_FECHA_NOMINA, formatearFecha(campos.getJSONObject(j).getString(NominasKeys.DATE_VALUE)));
								nominas.put(nomina);
								//urlDescargaArray.put(nomina);
								nomina = JSONFactoryUtil.createJSONObject("");
								nomina.put(NominasKeys.CAMPO_FECHA_NOMINA, formatearFecha(campos.getJSONObject(j).getString(NominasKeys.DATE_VALUE)));
							}
							if ("Fecharecalculo".equals(nombreOrigen)) {
								nomina.put(NominasKeys.CAMPO_FECHA_RECALCULO, campos.getJSONObject(j).getString(NominasKeys.DATE_VALUE));
								nominas.put(nomina);
								break;
							}
						}
					}
				}

				_log.debug("Se agrupa por fechas los resultados");
				Map<String, List<JSONObject>> nominasPorFecha = new HashMap<>();
				for (int i = 0; i < nominas.length(); i++) {
					fechaNomina = nominas.getJSONObject(i).getString(NominasKeys.CAMPO_FECHA_NOMINA);
					JSONObject nominasAgrupadas = JSONFactoryUtil.createJSONObject("");
					nominasAgrupadas.put(NominasKeys.CAMPO_FECHA_NOMINA, fechaNomina);

					if (!nominasPorFecha.containsKey(fechaNomina)) {
						nominasPorFecha.put(fechaNomina, new ArrayList<>());
					}
					if (nominas.getJSONObject(i).has(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA)) {
						nominasAgrupadas.put(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA, nominas.getJSONObject(i).getString(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA));
					}
					if (nominas.getJSONObject(i).has(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL)) {
						nominasAgrupadas.put(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL, nominas.getJSONObject(i).getString(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL));
					}
					if (nominas.getJSONObject(i).has(NominasKeys.CAMPO_URL_NOMINA_RECALCULO)) {
						nominasAgrupadas.put(NominasKeys.CAMPO_URL_NOMINA_RECALCULO, nominas.getJSONObject(i).getString(NominasKeys.CAMPO_URL_NOMINA_RECALCULO));
					}
					nominasPorFecha.get(fechaNomina).add(nominas.getJSONObject(i));
				}

				_log.debug("Se crean arrays finales");
				JSONArray nominasArray = JSONFactoryUtil.createJSONArray("");
				for (Map.Entry<String, List<JSONObject>> entry : nominasPorFecha.entrySet()) {
					JSONObject nominaFinal = JSONFactoryUtil.createJSONObject();

					nominaFinal.put(NominasKeys.CAMPO_FECHA_NOMINA, "");
					nominaFinal.put(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA, "");
					nominaFinal.put(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL, "");
					nominaFinal.put(NominasKeys.CAMPO_URL_NOMINA_RECALCULO, "");
					nominaFinal.put(NominasKeys.CAMPO_FECHA_RECALCULO, "");

					List<JSONObject> ListaNominas = entry.getValue();
					fechaNomina = entry.getKey();
					SimpleDateFormat formato = new SimpleDateFormat("MM.yyyy");
					Date fechaNominaDate = formato.parse(fechaNomina);
					Date desdeDate = (desde != null) ? formato.parse(desde) : null;
					Date hastaDate = (hasta != null) ? formato.parse(hasta) : null;

					if ((desdeDate == null && hastaDate == null) ||
							(desdeDate != null && fechaNominaDate.compareTo(desdeDate) >= 0) &&
									(hastaDate != null && fechaNominaDate.compareTo(hastaDate) <= 0)) {
						nominaFinal.put(NominasKeys.CAMPO_FECHA_NOMINA, fechaNomina);

						for (JSONObject nominasElemento : ListaNominas) {
							urlDescargaArray.put(nominasElemento);
							if (nominasElemento.has(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA)) {
								urlNominaDefinitiva = nominasElemento.getString(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA);
								String descarga = "<a href=\"" + urlNominaDefinitiva + "\" class=\"ema-boton-descargar\" download target=\\\"_blank\\\">" +
										"<i class=\"fa-solid fa-download\"></i> Descargar </a>";
								nominaFinal.put(NominasKeys.CAMPO_URL_NOMINA_DEFINITIVA, descarga);
							}
							if (nominasElemento.has(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL)) {
								urlNominaProvisional = nominasElemento.getString(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL);
								String descarga = "<a href=\"" + urlNominaProvisional + "\" class=\"ema-boton-descargar\" download target=\\\"_blank\\\">" +
										"<i class=\"fa-solid fa-download\"></i> Descargar </a>";
								nominaFinal.put(NominasKeys.CAMPO_URL_NOMINA_PROVISIONAL, descarga);
							}
							if (nominasElemento.has(NominasKeys.CAMPO_URL_NOMINA_RECALCULO)) {
								urlUltimoRecalculo = nominasElemento.getString(NominasKeys.CAMPO_URL_NOMINA_RECALCULO);
								String descarga = "<a href=\"" + urlUltimoRecalculo + "\" class=\"ema-boton-descargar\" download target=\\\"_blank\\\">" +
										"<i class=\"fa-solid fa-download\"></i> Descargar </a>";
								nominaFinal.put(NominasKeys.CAMPO_URL_NOMINA_RECALCULO, descarga);
							}
							if (nominasElemento.has(NominasKeys.CAMPO_FECHA_RECALCULO)) {
								fechaNomina = formatearFechaRecalculo(nominasElemento.getString(NominasKeys.CAMPO_FECHA_RECALCULO));
								nominaFinal.put(NominasKeys.CAMPO_FECHA_RECALCULO, fechaNomina);
							}
						}
						if(nominasArray.length() < 24) {
							nominasArray.put(nominaFinal);
						}else{
							break;
						}
					}
				}

				final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
				int count = (currentPage * pageSize) > nominasArray.length() ? nominasArray.length() : (currentPage * pageSize);
				final int end = disablePagination ? pageSize : count;

				totalItems = nominasArray.length();
				List<JSONObject> listJson = new ArrayList<>();
				for(int i = 0;i<nominasArray.length();i++){
					listJson.add(nominasArray.getJSONObject(i));
				}

				zipObject.put("nominasArrayZip", urlDescargaArray);
				listJson.subList(start,end).stream().forEach(j->{
					jsonArray.put(j);

				});

				request.getPortletSession().setAttribute("nominasArrayZip", urlDescargaArray);
				request.getPortletSession().setAttribute("matricula", matricula);
			}
		} catch (java.text.ParseException | JSONException ex) {
			throw new RuntimeException(ex);
		}
		return totalItems;
	}

	private static final String VIEW = "/views/nominas/results.jsp";

	public static String formatearFecha(String fechaNomina) {
		try {
			Date date = new Date(Long.parseLong(fechaNomina));
			TimeZone timeZone = TimeZone.getTimeZone("GMT+2");
			SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy");
			sdf.setTimeZone(timeZone);
			return sdf.format(date);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir la cadena de fecha a long: " + e.getMessage());
			return null;
		}
	}

	public static String formatearFechaRecalculo(String fechaNomina) {
		try {
			Date date = new Date(Long.parseLong(fechaNomina));
			TimeZone timeZone = TimeZone.getTimeZone("GMT+2");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setTimeZone(timeZone);
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
	SigdServiceApplication _sigdServiceApplication;
	@Reference
	ExpandoValueLocalService _expandoValueLocalService;
	Log _log = LoggerUtil.getLog(CustomExpandoUtil.class);
}