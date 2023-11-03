package es.emasesa.intranet.jaxrs.restddmselect.application;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import es.emasesa.intranet.base.model.KeyValuePair;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.webservices.jaxrs.beans.ResponseData;
import es.emasesa.intranet.webservices.settings.RestDDMSelectSettings;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

// PRIVATE SERVICES
@ApplicationPath("/")
@Component(
	immediate = true,
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/ddmselect",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=emasesa.Ddmselect",
		"auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true"
	},
	configurationPid = "es.emasesa.intranet.webservices.settings.RestDDMSelectSettings",
	service = Application.class
)
public class DDMSelectApplication extends Application {

	public static final String ERROR = "error-";
	private static final Log LOG = LoggerUtil.getLog(DDMSelectApplication.class);

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(RestDDMSelectSettings.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_configuration = null;
	}

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();

		//add the automated Jackson marshaller for JSON.
		singletons.add(new JacksonJsonProvider());

		//add this REST endpoints
		singletons.add(this);

		return singletons;
	}

	@GET
	@Produces(ContentTypes.APPLICATION_JSON)
	@Path("/get-journal-by-structure/{strGroupFriendlyUrl}/{structureKey}/{groupId}")
	public Response getGeneral(@DefaultValue("global") @PathParam("strGroupFriendlyUrl") String strGroupFriendlyUrl,
							   @DefaultValue(StringPool.BLANK) @PathParam("structureKey") String structureKey,
							   @DefaultValue("0") @PathParam("groupId") long groupId) {
		Response.ResponseBuilder builder;
		try {
			if (!Validator.isBlank(structureKey)){

				List<KeyValuePair> generalCleanList = getGeneralCleanListFromCache(StringPool.SLASH + strGroupFriendlyUrl, structureKey, groupId);

				builder = Response.ok(new ResponseData(
						false,
						generalCleanList,
						null,
						null)
				);

			} else {
				builder = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(
						new ResponseData(Boolean.TRUE,
								Collections.EMPTY_LIST,
								ERROR +Response.Status.SERVICE_UNAVAILABLE.getStatusCode(),
								"Check Estaciones GroupId/StructureId in emasesa System Setting Config"));
			}
		} catch (Exception e){
			LoggerUtil.error(LOG, e);
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					new ResponseData(Boolean.TRUE,
							Collections.EMPTY_LIST,
							ERROR +Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
							"Unknown Error :"+e.getMessage()));
		}

		return builder.build();
	}
	
	/**
	 * Get generalCleanList asking first to cache.
	 * @param structureKey
	 * @param groupId
	 * @return
	 */
	private List<KeyValuePair> getGeneralCleanListFromCache(String strGroupFriendlyUrl, String structureKey, final long groupId) {
		final String cacheKey = DDMSelectApplication.class.getName() + "@getGeneral_" + strGroupFriendlyUrl + StringPool.UNDERLINE + structureKey + StringPool.UNDERLINE + groupId ;
		
		// Get Cache data from static methods
		List<KeyValuePair> generalCleanList = null;
		PortalCache<String, Object> cache = CustomCacheSingleUtil.getPortalCache();
		generalCleanList = (List<KeyValuePair>) CustomCacheSingleUtil.get(cache, cacheKey);
		if(Validator.isNull(generalCleanList)) {
			// If cache is empty, get result and store in cache
			generalCleanList = getIndexedGeneralCleanList(strGroupFriendlyUrl, structureKey, groupId);
			
			if(generalCleanList != null) {
				CustomCacheSingleUtil.put(cache, cacheKey, generalCleanList, CustomCacheSingleUtil.TTL_10_MIN);
			}
		}
		return generalCleanList;
	}
	
	private List<KeyValuePair> getIndexedGeneralCleanList(String strGroupFriendlyUrl, String structureKey, final long groupId) {
		final Sort sort = SortFactoryUtil.create(Field.MODIFIED_DATE, Sort.INT_TYPE, Boolean.FALSE);
		final LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
		DDMStructure structure = null;
		try {
			final Group group = _groupLocalService.getFriendlyURLGroup(_portal.getDefaultCompanyId(), strGroupFriendlyUrl);
			structure = _ddmStructureLocalService.getStructure(group.getGroupId(), _portal.getClassNameId(JournalArticle.class), structureKey);

		}catch (PortalException e){
			LoggerUtil.error(LOG, e);
		}

		if (structure == null){
			return null;
		}

		final List<JournalArticle> journalArticles = _journalArticleLocalService.getStructureArticles(groupId, structure.getStructureId());
		Map<String, JournalArticle> latestVersionMap = new HashMap<>();

		for (JournalArticle article : journalArticles) {
			String articleId = article.getArticleId();
			if (!latestVersionMap.containsKey(articleId) ||
					article.getVersion() > latestVersionMap.get(articleId).getVersion()) {
				latestVersionMap.put(articleId, article);
			}
		}

		List<JournalArticle> latestVersions = new ArrayList<>(latestVersionMap.values());
		if(latestVersions == null) {
			return null;
		} else {

			return latestVersions
					.stream()
					.map(ja -> new KeyValuePair(ja.getTitle(LocaleUtil.SPAIN), ja.getArticleId()))
					.collect(Collectors.toList());
		}
	}
	
	/* Old version, using DDBB querying (instead index) */

	private List<KeyValuePair> getGeneralCleanList(String structureKey, final long groupId) {

		List<JournalArticle> general = JournalArticleLocalServiceUtil.getArticles(groupId, structureKey);

		return general
				.stream()
				.map(a -> new KeyValuePair(a.getTitle(), a.getArticleId()))
				.collect(Collectors.toList());
	}

	/**
	 * @Deprecated
	@GET
	@Produces(ContentTypes.APPLICATION_JSON)
	@Path("/get-journaluuid-by-structure/{structureKey}/{groupId}")
	public Response getGeneralUUID(@DefaultValue(StringPool.BLANK) @PathParam("structureKey") String structureKey,
							   @DefaultValue("0") @PathParam("groupId") long groupId) {
		Response.ResponseBuilder builder;
		try {
			if (!Validator.isBlank(structureKey)){
				final List<JournalArticle> general = _journalArticleLocalService.search(
						_portal.getDefaultCompanyId(),
						groupId,
						Collections.EMPTY_LIST,
						JournalArticleConstants.CLASSNAME_ID_DEFAULT,
						null,
						null,
						structureKey,
						StringPool.BLANK,
						null,
						null,
						WorkflowConstants.STATUS_APPROVED,
						null,
						QueryUtil.ALL_POS,
						QueryUtil.ALL_POS,
						new ArticleModifiedDateComparator(Boolean.TRUE));

				final List<KeyValuePair> generalCleanList = general
						.stream()
						.map(a -> new KeyValuePair(a.getTitle(), a.getUuid()))
						.collect(Collectors.toList());

				builder = Response.ok(new ResponseData(
						false,
						generalCleanList,
						null,
						null)
				);

			} else {
				builder = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(
						new ResponseData(Boolean.TRUE,
								Collections.EMPTY_LIST,
								ERROR +Response.Status.SERVICE_UNAVAILABLE.getStatusCode(),
								"Check Estaciones GroupId/StructureId in emasesa System Setting Config"));
			}
		} catch (Exception e){
			LoggerUtil.error(logger, e);
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					new ResponseData(Boolean.TRUE,
							Collections.EMPTY_LIST,
							ERROR +Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
							"Unknown Error :"+e.getMessage()));
		}

		return builder.build();
	}
   **/

	private volatile RestDDMSelectSettings _configuration;

	@Reference
	JournalArticleLocalService _journalArticleLocalService;

	@Reference
	DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	GroupLocalService _groupLocalService;

	@Reference
	Portal _portal;

}