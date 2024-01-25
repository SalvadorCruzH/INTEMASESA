package es.emasesa.intranet.favoritos.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.settings.configuration.FavoritosConfiguration;

@Component(
        configurationPid = "es.emasesa.intranet.settings.configuration.FavoritosConfiguration",
        property = "item.class.name=com.liferay.asset.kernel.model.AssetEntry",
        immediate = true,
        service = InfoCollectionProvider.class
)
public class MyAccesosDirectosCollectionProvider extends BaseMyFavsInfoCollectionProvider implements InfoCollectionProvider<AssetEntry> {

    @Override
    public InfoPage<AssetEntry> getCollectionInfoPage(CollectionQuery collectionQuery) {

        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        Hits hits = null;
        try {
            BooleanQuery query = new BooleanQueryImpl();
            MatchQuery objectDefinitionQuery = new MatchQuery("objectDefinitionId", _configuration.objectDefinitionId());
            MatchQuery statusbyUserIdQuery = new MatchQuery("statusByUserId", "" + serviceContext.getUserId());
            BooleanQuery includeQuery = new BooleanQueryImpl();
            MatchQuery objectEntryContentQuery = new MatchQuery("objectEntryContent", "assetEntryId");
            includeQuery.add(objectEntryContentQuery, BooleanClauseOccur.MUST.getName());
            includeQuery.add( new MatchQuery("objectEntryContent", "\"EMA-ACCESO-DIRECTO\""), BooleanClauseOccur.MUST.getName());
            query.add(objectDefinitionQuery, BooleanClauseOccur.MUST.getName());
            query.add(statusbyUserIdQuery, BooleanClauseOccur.MUST.getName());
            query.add(includeQuery, BooleanClauseOccur.MUST.getName());

            SearchContext searchContext = _buildSearchContext(collectionQuery);
            hits = IndexSearcherHelperUtil.search(searchContext, query);

            List<AssetEntry> assets = parseHits(hits, _configuration.objectDefinitionId());

            return InfoPage.of(
                    assets, collectionQuery.getPagination(), hits.getLength());
        } catch (PortalException portalException) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(portalException, portalException);
            }

        }
        return InfoPage.of(
                Collections.emptyList(), collectionQuery.getPagination(), 0);
    }

    @Override
    public String getLabel(Locale locale) {
        return _language.get(locale, "my-favs-apps", "Mis accesos directos favoritos");
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {

        LOG.debug("Activate MyAccesosDirectosCollectionProvider");

        _configuration = ConfigurableUtil.createConfigurable(
        		FavoritosConfiguration.class, properties);
    }

    @Reference
    private Language _language;

    private volatile FavoritosConfiguration _configuration;
    private static final Log LOG = LoggerUtil.getLog(MyAccesosDirectosCollectionProvider.class);

}
