package es.emasesa.intranet.portlet.ajaxsearch.impl.historicomarcajes.result;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.constant.StringConstants;
import es.emasesa.intranet.base.model.AjaxMessage;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import es.emasesa.intranet.base.util.CustomDateUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.impl.marcajediaactual.result.MarcajeDiaActualResultImpl;
import es.emasesa.intranet.portlet.ajaxsearch.impl.util.SpecUtil;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchJsonModel;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import es.emasesa.intranet.portlet.ajaxsearch.util.AjaxSearchUtil;
import es.emasesa.intranet.service.util.SapServicesUtil;
import es.emasesa.intranet.settings.osgi.SPECServicesSettings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = { },
		service = AjaxSearchResult.class
)
public class HistoricoMarcajesResultImpl implements AjaxSearchResult {

	private final static Log LOG = LoggerUtil.getLog(MarcajeDiaActualResultImpl.class);

	private static final Properties DFLT_PROPERTIES = new Properties();

	public static final String TEMPLATE_KEY = "template-key";
	public static final String STRUCTURE_KEY = "structure-key";
	public static final String CSS_WRAPPER_CLASS = "css-wrapper-class";
	public static final String DISABLE_PAGINATION = "disable-pagination";
	private static final String CAT_SELECTED = "catSelected";
	private static final String FECHA_DESDE = "fechaDesde";
	private static final String FECHA_HASTA = "fechaHasta";
	private static final String FECHA_UNICA = "fechaUnica";
	private static final String MATRICULA = "matricula";

	private static final String TIME_TO_LIVE="time_to_live";



	static {
		DFLT_PROPERTIES.put(AjaxSearchPortletKeys.PROP_BASE_PAGESIZE,
				AjaxSearchPortletKeys.PROP_BASE_PAGESIZE_DFLT);

		DFLT_PROPERTIES.put(TEMPLATE_KEY, "");
		DFLT_PROPERTIES.put(STRUCTURE_KEY, "");
		DFLT_PROPERTIES.put(CSS_WRAPPER_CLASS, "");
		DFLT_PROPERTIES.put(DISABLE_PAGINATION, "0");
		DFLT_PROPERTIES.put(TIME_TO_LIVE, 0);

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
			Date fromDate = ajaxSearchDisplayContext.getDate(FECHA_DESDE);
			Date toDate = ajaxSearchDisplayContext.getDate(FECHA_HASTA);
			Date uniqueDate = ajaxSearchDisplayContext.getDate(FECHA_UNICA);

			String screenName = ajaxSearchDisplayContext.getString(MATRICULA);
			String fromDateDB = "";
			String toDateDB = "";
			if(Validator.isNotNull(uniqueDate) || Validator.isNotNull(fromDate) ||Validator.isNotNull(toDate) || Validator.isNotNull(screenName)) {
				if(Validator.isNotNull(uniqueDate)){
					fromDateDB = _customDateUtil.atStartOfDay(uniqueDate,EmasesaConstants.FORMAT_DATE_DB);
					toDateDB = _customDateUtil.atEndOfDay(uniqueDate,EmasesaConstants.FORMAT_DATE_DB);
				}else{
					if(Validator.isNotNull(fromDate)){
						 fromDateDB = _customDateUtil.atStartOfDay(fromDate,EmasesaConstants.FORMAT_DATE_DB);
					}else{
						fromDateDB = EmasesaConstants.INIT_DATE_DB;
					}
					if(Validator.isNotNull(toDate)){
						 toDateDB = _customDateUtil.atEndOfDay(toDate,EmasesaConstants.FORMAT_DATE_DB);
					}else{
						toDateDB = EmasesaConstants.FINAL_DATE_DB;
					}
				}

				totalItems = performSearchAndParse(request,
						response,
						ajaxSearchDisplayContext,
						fromDateDB,
						toDateDB,
						screenName,
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
									   final String fromDate,
									   final String toDate,
									   final String screenName,
									   final int currentPage,
									   final int pageSize,
									   final boolean disablePagination,
									   final JSONArray jsonArray) throws ParseException, SearchException {
		final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		JSONArray array = JSONFactoryUtil.createJSONArray();
		int totalItems = 0;
		List<JSONObject> listJson;
		String cacheKey = "Historico"+fromDate+toDate+screenName;

		int timeToLive = 0;
		if(Validator.isNumber(ajaxSearchDisplayContext.getConfigOrDefault(TIME_TO_LIVE,"0"))){
			timeToLive = Integer.parseInt(ajaxSearchDisplayContext.getConfigOrDefault(TIME_TO_LIVE,"0"));
		}

		if(Validator.isNotNull(_cache.get(cacheKey))){
			try {
				array = JSONFactoryUtil.createJSONArray((String) _cache.get(cacheKey));
			} catch (JSONException e) {
				LoggerUtil.debug(LOG,e);
			}

		}else{
			listJson = _specUtil.dbSPECSearch(fromDate,toDate,screenName);
			_specUtil.orderByDateAndTime(listJson);
			Map<String,List<JSONObject>> groupedRows = _specUtil.groupRows(listJson);
			_specUtil.processData(array, themeDisplay, groupedRows);
			_cache.put(cacheKey, array.toString(), timeToLive);

		}

		final int start = disablePagination ? 0 : ((currentPage - 1) * pageSize);
		int count = (currentPage * pageSize) > array.length() ? array.length() : (currentPage * pageSize);
		final int end = disablePagination ? pageSize : count;

		List<JSONObject> paginatedJson = new ArrayList<>();
		for(int i = 0;i<array.length();i++){

			paginatedJson.add(array.getJSONObject(i));
		}

		paginatedJson.subList(start,end).stream().forEach(j->{
				jsonArray.put(j);

		});

		totalItems = array.length();


		return totalItems;
	}

	private static final String VIEW = "/views/historicomarcajes/results.jsp";

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
	SpecUtil _specUtil;

}
