package es.emasesa.intranet.portlet.ajaxsearch.constant;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Field;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AjaxSearchPortletKeys {

	public static final DateFormat DFLT_SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final String SEARCH_RENDER_PARAM_NAME = "searchActionParameters";
	public static final String PORTLET_AJAXSEARCH_FORM =
		"es_emasesa_intranet_portlet_ajaxsearch_portlet_form_AjaxSearchFormPortlet";
	public static final String PORTLET_AJAXSEARCH_RESULTS =
		"es_emasesa_intranet_portlet_ajaxsearch_portlet_result_AjaxSearchResultsPortlet";
	public static final String PORTLET_PREFS_SEARCH_IMPL="searchImpl";
	public static final String PORTLET_CURRENT_CONFIG="currentConfig";
	public static final String DEFAULT_JSP_FORM="/search-form.jsp";
	public static final String DEFAULT_JSP_RESULT="/search-results.jsp";
	public static final String DEFAULT_JSP_EMPTY_VIEW="/empty-view.jsp";
	public static final String DEFAULT_JSP_PLEASE_CONFIGURE_VIEW="/please-configure-view.jsp";

	/**
	 * ATTRS/PARAMETERS/JSON/PROPS STANDARIZATION
	 */
	public static final String ATTR_AJAX_FORM_PAGE = "formPage";
	public static final String ATTR_AJAX_RESULTS_PAGE = "resultsPage";
	public static final String ATTR_AJAX_SEARCH_DISPLAY_CONTEXT = "searchDisplayContext";

	public static final String PARAM_ORIGINAL_JSON_OBJECT = "originalJSONObject";
	public static final String PARAM_ACTION_PARAMS = "actionParams";
	public static final String PARAM_QUERY_TEXT = "queryText";
	public static final String PARAM_CURRENT_PAGE = "currentPage";
	public static final int PARAM_CURRENT_PAGE_DFLT = 1;

	public static final String JSON_CURRENT_PAGE = "currentPage";
	public static final String JSON_TOTAL_PAGES = "totalPages";
	public static final String JSON_TOTAL_ITEMS = "totalItems";
	public static final String JSON_ITEMS_PAGE = "itemsPage";
	public static final String JSON_ARRAY_CONTENT = StringPool.CONTENT;
	public static final String JSON_ARRAY_ADDITIONAL_INFO = "additionalInfo";

	public static final String PROP_BASE_PAGESIZE = "pagesize";
	public static final String PROP_BASE_PAGESIZE_DFLT = "9";
	public static final int PROP_BASE_PAGESIZE_DFLT_INT = Integer.parseInt(PROP_BASE_PAGESIZE_DFLT);
	public static final String PROP_BASE_RESULT_URL = "result-relative-url";
	public static final String PROP_BASE_RESULT_URL_DFLT = "/resultados";

	/** XML SAX Reading **/
	public static final String XML_READ_INIT = "/root/dynamic-element[@name='";
	public static final String XML_READ_END = "']/dynamic-content";


	/** CAMPOS DE OBJETOS **/
	public static final String OBJECT_DEFINITION_NAME = "objectDefinitionName";
	public static final String NOMBRE_OBJETO = "nombreObjeto";
	public static final String TIPO_OBJETO = "tipoObjeto";
	public static final String TIPO_AUSENCIA = "tipoAusencia";
	public static final String ASUNTO = "asunto";
	public static final String JUSTIFICACION = "justificacion";
	public static final String CAUSA = "causa";
	public static final String TIPO_RETRIBUCION = "tipoRetribucion";
	public static final String DURACION = "duracion";
	public static final String HORAS = "horas";
	public static final String FECHA_ACTIVIDAD = "fechaActividad";
	public static final String IS_HIDDEN = "isHidden";
	public static final String NUMERO_EMPLEADOS = "numeroEmpleadosALosQueSolicita" ;
	public static final String DENOMINACION_FORMACION = "denominacion";
	public static final String FECHA_DESDE = "fechaDesde";
	public static final String FECHA_HASTA = "fechaHasta";
	public static final String FECHA_ENVIO = "fechaEnvio";
	public static final String ESTADO = "estado";
	public static final String ESTADO_OBJETO = "estadoObjeto";
	public static final String ESTADO_CODE = "estado-code";
	public static final String URL_VISUALIZAR = "urlVisualizar";
	public static final String URL_EDITAR = "urlEditar";
	public static final String URL_ELIMINAR = "urlEliminar";
	public static final String FECHA_SOLICITUD = "fechaSolicitud";
	public static final String PLAN_FORMACION = "anoDeLaFormacion";
	public static final String SOLICITANTE = "solicitante";
	public static final String DENOMINACION = "denominacion";
	public static final String OBLIGATORIEDAD = "obligatoriedad";
	public static final String MODALIDAD = "modalidad";
	public static final String MATRICULA = "matricula";
	public static final String NOMBRE = "nombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String FECHA_ACTUAL = "fechaActual";

	/** VALORES ESTADO SELECT ACCION FORMATIVA FUERA DEL PLAN **/
	public static final String PENDIENTE_JEFE_DIVISION = "asignadaJefeDeDivision";
	public static final String PENDIENTE_JEFE_DEPARTAMENTO = "pendienteJefeDepartamento";
	public static final String PENDIENTE_SUBDIRECTOR_DIRECTOR_CONSEJERO = "pendienteSubdirectorDirector";
	public static final String APROBADO = "aceptada";
	public static final String RECHAZADO = "rechazada";
	public static final String LEIDO = "leido";

	public static final String ROLE_RRHH_CONFIG = "roleRRHHName";
	public static final String FIELD_OBJECT_ENTRY_CONTENT = "objectEntryContent";
	public static final String FIELD_OBJECT_ENTRY_ID = "objectEntryId";
	public static final String EMAIL = "email";
	public static final String NOMBRE_COMPLETO = "nombreCompleto";
}