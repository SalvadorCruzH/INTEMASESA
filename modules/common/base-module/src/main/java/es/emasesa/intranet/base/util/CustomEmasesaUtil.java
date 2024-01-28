package es.emasesa.intranet.base.util;

import com.liferay.asset.kernel.exception.NoSuchEntryException;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.constant.EmasesaConstants;

@Component(
		configurationPid="es.emasesa.intranet.settings.configuration.FavoritosConfiguration",
        immediate = true,
        service = CustomEmasesaUtil.class
)
public class CustomEmasesaUtil {

	private static final Log LOG = LoggerUtil.getLog(CustomEmasesaUtil.class);

	public User getEmasesaAdmin(final long companyId) throws PortalException {
		LoggerUtil.debug(LOG, "getEmasesaAdmin");
		return userLocalService.fetchUserByScreenName(companyId, EmasesaConstants.EMASESA_ADMIN_SCREENNAME);
	}
	
	public List<AssetEntry> searchFavoritesJournalsArticleByUserAndDDMStructureKey(ThemeDisplay themeDisplay, String ddmStructureKey,int start,int end, String objectId) throws ParseException, SearchException {

        BooleanQuery query = new BooleanQueryImpl();
        MatchQuery objectDefinitionQuery = new MatchQuery("objectDefinitionId", objectId);
        MatchQuery statusbyUserIdQuery = new MatchQuery("statusByUserId", "" + themeDisplay.getUserId());
        MatchQuery objectEntryContentQuery = new MatchQuery("objectEntryContent", ddmStructureKey);

        query.add(objectDefinitionQuery, BooleanClauseOccur.MUST.getName());
        query.add(statusbyUserIdQuery, BooleanClauseOccur.MUST.getName());
        query.add(objectEntryContentQuery, BooleanClauseOccur.MUST.getName());

        SearchContext searchContext = new SearchContext();
        searchContext.setCompanyId(themeDisplay.getCompanyId());
        searchContext.setGroupIds(null);
        searchContext.setUserId(themeDisplay.getUserId());
        searchContext.setAttribute(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
        searchContext.setSorts(new Sort(Field.MODIFIED_DATE, true));
        searchContext.setStart(start);
        searchContext.setEnd(end);
        Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
        List<AssetEntry> assets = parseHits(hits, objectId);

        return  assets;
    }
	
	 protected List<AssetEntry> parseHits(Hits hits, String objectDefinition){
	        List<AssetEntry> assets = new ArrayList<>();
	        for (Document document : hits.getDocs()) {
	            long classPK = GetterUtil.getLong(
	                    document.get("itemEntryClassPK"));
	            try{
	                String className = _classNameLocalService.getClassName(GetterUtil.getLong(document.get("itemEntryClassNameId"))).getClassName();

	                AssetEntry assetEntry = _assetEntryService.getEntry(className,classPK);
	                assets.add(assetEntry);
	            }catch (NoSuchEntryException e){

	            } catch (PortalException e) {

	            }
	        }

	        return assets;
	    }

	@Reference
	UserLocalService userLocalService;

	@Reference
	ObjectEntryLocalService objectEntryLocalService;

	@Reference
	Portal portal;
	
	@Reference
    ClassNameLocalService _classNameLocalService;
	
	@Reference
    protected AssetEntryService _assetEntryService;
}
