package es.emasesa.intranet.portlet.ajaxsearch.impl.solicitudes.result;

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
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
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
import com.liferay.portal.kernel.model.Role;

import es.emasesa.intranet.sigd.service.application.SigdServiceApplication;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
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
				toDate);

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
									   final int currentPage,
									   final int pageSize,
									   final boolean disablePagination,
									   final JSONArray jsonArray,
									   final String matricula,
									   final Date fromDate,
									   final Date toDate) throws ParseException, SearchException {

		int totalItems = 0;
		String urlNominaDefinitiva = "", urlNominaProvisional = "", urlUltimoRecalculo="", fechaNomina="", fechaRecalculo="";

		Long fromDateMilisegundos = (fromDate != null) ? fromDate.getTime(): 0;
		String desde = (fromDateMilisegundos != 0) ? fromDateMilisegundos.toString(): "";
		desde = (desde != null) ? formatearFecha(desde) : "";
		Long toDateMilisegundos = (toDate != null) ? toDate.getTime(): 0;
		String hasta = (toDateMilisegundos != 0) ? toDateMilisegundos.toString() : "";
		hasta = (hasta != null) ? formatearFecha(hasta) : "";

		String listadoNominas = _sigdServiceApplication.buscarDocumento(matricula);
		try {
			JSONArray elementos = JSONFactoryUtil.createJSONObject(listadoNominas).getJSONObject("buscarDocumentosResponse").getJSONArray("elementos");
			JSONArray nominas = JSONFactoryUtil.createJSONArray("");
			if (elementos != null) {

	//FILTRO LOS CAMPOS NECESARIOS DEL JSON
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
								nomina.put("fechaNomina", formatearFecha(campos.getJSONObject(j).getString("dateValue")));
								nominas.put(nomina);
								break;
							}
						}

					} else if ("7971".equals(codigoTipoDocumental)) {
						for (int b = 0; b < campos.length(); b++) {
							String nombreOrigen = campos.getJSONObject(b).getString("nombreOrigen");
							if ("FechaNomina".equals(nombreOrigen)) {
								nomina.put("urlNominaProvisional", documentoOrigen.getString("urlDescarga"));
								nomina.put("fechaNomina", formatearFecha(campos.getJSONObject(b).getString("dateValue")));
								nominas.put(nomina);
								break;
							}
						}

					} else if ("7973".equals(codigoTipoDocumental)) {
						for (int j = 0; j < campos.length(); j++) {
							String nombreOrigen = campos.getJSONObject(j).getString("nombreOrigen");
							if ("FechaNomina".equals(nombreOrigen)) {
								nomina.put("urlUltimoRecalculo", documentoOrigen.getString("urlDescarga"));
								nomina.put("fechaNomina", formatearFecha(campos.getJSONObject(j).getString("dateValue")));
								nominas.put(nomina);
								nomina = JSONFactoryUtil.createJSONObject("");
								nomina.put("fechaNomina", formatearFecha(campos.getJSONObject(j).getString("dateValue")));
							}
							if ("Fecharecalculo".equals(nombreOrigen)) {
								nomina.put("fechaRecalculo", campos.getJSONObject(j).getString("dateValue"));
								nominas.put(nomina);
								break;
							}
						}
					}
				}

	//AGRUPO LAS NOMINAS POR FECHA
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

	//SE CREAN LOS ARRAY DEFINITIVOS
				JSONArray nominasArray = JSONFactoryUtil.createJSONArray("");
				JSONArray nominasArrayZip = JSONFactoryUtil.createJSONArray("");
				for (Map.Entry<String, List<JSONObject>> entry : nominasPorFecha.entrySet()) {
					JSONObject nominaFinal = JSONFactoryUtil.createJSONObject();
					JSONObject nominaZip = JSONFactoryUtil.createJSONObject();

					nominaFinal.put("fechaNomina", "");
					nominaFinal.put("urlNominaDefinitiva", "");
					nominaFinal.put("urlNominaProvisional", "");
					nominaFinal.put("urlUltimoRecalculo", "");
					nominaFinal.put("fechaRecalculo", "");

					List<JSONObject> ListaNominas = entry.getValue();
					fechaNomina = entry.getKey();
					//String fechaFormateada = formatearFecha(fechaNomina);
					SimpleDateFormat formato = new SimpleDateFormat("MM.yyyy");
					Date fechaNominaDate = formato.parse(fechaNomina);
					Date desdeDate = (desde != null) ? formato.parse(desde) : null;
					Date hastaDate = (hasta != null) ? formato.parse(hasta) : null;

					if ((desdeDate == null && hastaDate == null) ||
							(desdeDate != null && fechaNominaDate.compareTo(desdeDate) >= 0) &&
									(hastaDate != null && fechaNominaDate.compareTo(hastaDate) <= 0)) {
						nominaFinal.put("fechaNomina", fechaNomina);

						if (nominasArray.length() < 24) {
							List<Path> tempFiles = new ArrayList<>();
							for (JSONObject nominasElemento : ListaNominas) {
								if (nominasElemento.has("urlNominaDefinitiva")) {
									urlNominaDefinitiva = nominasElemento.getString("urlNominaDefinitiva");
									String descarga = "<a href=\"" + urlNominaDefinitiva + "\" class=\"ema-boton-descargar\" download>" +
											"<i class=\"fa-solid fa-download\"></i> Descargar </a>";
									nominaFinal.put("urlNominaDefinitiva", descarga);
									nominaZip.put("urlNominaDefinitiva", urlNominaDefinitiva);
								}
								if (nominasElemento.has("urlNominaProvisional")) {
									urlNominaProvisional = nominasElemento.getString("urlNominaProvisional");
									String descarga = "<a href=\"" + urlNominaProvisional + "\" class=\"ema-boton-descargar\" download>" +
											"<i class=\"fa-solid fa-download\"></i> Descargar </a>";
									nominaFinal.put("urlNominaProvisional", descarga);
									nominaZip.put("urlNominaProvisional", urlNominaProvisional);
								}
								if (nominasElemento.has("urlUltimoRecalculo")) {
									urlUltimoRecalculo = nominasElemento.getString("urlUltimoRecalculo");
									String descarga = "<a href=\"" + urlUltimoRecalculo + "\" class=\"ema-boton-descargar\" download>" +
											"<i class=\"fa-solid fa-download\"></i> Descargar </a>";
									nominaFinal.put("urlUltimoRecalculo", descarga);
									nominaZip.put("urlUltimoRecalculo", urlUltimoRecalculo);
								}
								if (nominasElemento.has("fechaRecalculo")) {
									fechaNomina = formatearFechaRecalculo(nominasElemento.getString("fechaRecalculo"));
									nominaFinal.put("fechaRecalculo", fechaNomina);
								}
							}
							nominasArray.put(nominaFinal);
							nominasArrayZip.put(nominaZip);
						} else {
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

				JSONArray zipFilePath = descargarPDFsTemporalmente(nominasArrayZip);
				JSONObject zip = zipFilePath.getJSONObject(0);
				String urlDescarga = zip.getString("urlDescarga");
				listJson.subList(start,end).stream().forEach(j->{
					j.put("descargaUrl", urlDescarga);
					jsonArray.put(j);

				});

			}
		} catch (java.text.ParseException ex) {
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} catch (JSONException ex) {
			throw new RuntimeException(ex);
		}

		return totalItems;
	}

	public static JSONArray descargarPDFsTemporalmente(JSONArray jsonArray) throws IOException, JSONException {
		List<String> urls = obtenerURLsDesdeJSONArray(jsonArray);
		List<Path> tempFiles = new ArrayList<>();
		Path zipFilePath = null;

		try {
			for (int i = 0; i < urls.size(); i++) {
				String url = urls.get(i);
				String nombreArchivo = "archivo_" + i + ".pdf";

				Path tempFile = descargarYAlmacenarTemporalmente(url, nombreArchivo);
				tempFiles.add(tempFile);
			}

			// Crear un archivo ZIP y agregar los archivos temporales
			zipFilePath = Files.createTempFile("Archivos_Nomina_", ".zip");
			try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePath.toFile()))) {
				for (int i = 0; i < tempFiles.size(); i++) {
					Path tempFile = tempFiles.get(i);
					String entryName = "archivo_" + i + ".pdf";

					// Agregar entrada al archivo ZIP
					zipOutputStream.putNextEntry(new ZipEntry(entryName));
					// Copiar contenido del archivo temporal al archivo ZIP
					Files.copy(tempFile, zipOutputStream);
					// Cerrar entrada para el siguiente archivo
					zipOutputStream.closeEntry();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Eliminar archivos temporales después de crear el ZIP
			for (Path tempFile : tempFiles) {
				Files.deleteIfExists(tempFile);
			}
		}

		JSONArray enlaceDescarga = obtenerEnlaceDescarga(zipFilePath);

		return enlaceDescarga;
	}

	public static JSONArray obtenerEnlaceDescarga(Path archivoPath) throws JSONException {
		JSONObject urlDescarga = JSONFactoryUtil.createJSONObject();
		JSONArray urls = JSONFactoryUtil.createJSONArray("");

		String rutaArchivo = archivoPath.toString();

		// Reemplazar las barras invertidas por barras inclinadas para formar una URL válida
		String rutaConBarrasInclinadas = rutaArchivo.replace("\\", "/");

		// Obtener el nombre del archivo para usarlo como nombre de descarga sugerido
		String nombreArchivo = archivoPath.getFileName().toString();

		// Codificar el nombre del archivo para asegurar que sea una URL válida
		String nombreArchivoCodificado;
		try {
			nombreArchivoCodificado = URLEncoder.encode(nombreArchivo, "UTF-8");
		} catch (Exception e) {
			// Manejo de excepciones (puedes personalizarlo según tus necesidades)
			e.printStackTrace();
			return null;
		}

		// Formar el enlace de descarga
		String enlaceDescarga = "<a class=\"btn-primary pe-none\" href=\"" + rutaConBarrasInclinadas + "\" download=\"" + nombreArchivoCodificado + "\">Descargar Nominas</a>";



		urlDescarga.put("urlDescarga", enlaceDescarga);

		urls.put(urlDescarga);

		return urls;
	}
	private static List<String> obtenerURLsDesdeJSONArray(JSONArray jsonArray) {
		List<String> urls = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject elemento = jsonArray.getJSONObject(i);

			if (elemento.has("urlNominaDefinitiva")) {
				String urlNominaDefinitiva = elemento.getString("urlNominaDefinitiva");
				urls.add(urlNominaDefinitiva);
			}
			if (elemento.has("urlNominaProvisional")) {
				String urlNominaProvisional = elemento.getString("urlNominaProvisional");
				urls.add(urlNominaProvisional);
			}
			if (elemento.has("urlUltimoRecalculo")) {
				String urlUltimoRecalculo = elemento.getString("urlUltimoRecalculo");
				urls.add(urlUltimoRecalculo);
			}
		}
		return urls;
	}

	private static Path descargarYAlmacenarTemporalmente(String url, String nombreArchivo) throws IOException {

		URL conexion = new URL(url);
		Path tempFile = Files.createTempFile("Nomina_", ".pdf");

		try (InputStream in = conexion.openStream()) {
			Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (FileAlreadyExistsException e) {
			System.err.println("El archivo ya existe: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempFile;
	}


	private static final String VIEW = "/views/solicitudes/nominas/results.jsp";

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
