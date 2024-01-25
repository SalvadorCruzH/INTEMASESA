package es.emasesa.intranet.favoritos.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.util.AssetHelper;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.searchframework.SearchingJournal;
import es.emasesa.intranet.settings.configuration.FavoritosConfiguration;

@Component(
        configurationPid = "es.emasesa.intranet.settings.configuration.FavoritosConfiguration",
        property = "item.class.name=com.liferay.asset.kernel.model.AssetEntry",
        immediate = true,
        service = InfoCollectionProvider.class
)
public class AllApplicationslnfoCollectionProvider extends BaseMyFavsInfoCollectionProvider implements InfoCollectionProvider<AssetEntry> {

    @Override
    public InfoPage<AssetEntry> getCollectionInfoPage(CollectionQuery collectionQuery) {

        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        Hits hits = null;
        try {
            User user = serviceContext.getThemeDisplay().getUser();
            BooleanQuery query = new BooleanQueryImpl();
            query.addTerm("ddmStructureKey", "CE-APPLICATIONS", Boolean.FALSE, BooleanClauseOccur.MUST);


            /*if(user.getExpandoBridge().hasAttribute(CamaraConstants.EXPANDO_CAMARA_UNIDAD_ORGANIZATIVA)){
                String unidadOrganizativa = (String) user.getExpandoBridge().getAttribute(CamaraConstants.EXPANDO_CAMARA_UNIDAD_ORGANIZATIVA);
                String unidadOrganizativaEx = _applicationSettings.unidadOrganizativaEx();
                boolean match = Arrays.stream(unidadOrganizativaEx.split(StringPool.COMMA)).anyMatch(camara->camara.equals(unidadOrganizativa));
                if(!match){
                   query.addTerm("exclusivoCE", Boolean.FALSE.toString(), Boolean.FALSE,BooleanClauseOccur.MUST);

                }

            }*/

            SearchContext searchContext = _buildSearchContext(collectionQuery);
            hits = IndexSearcherHelperUtil.search(searchContext, query);

            //get assetEntry from hits
            List<AssetEntry> assets =_assetHelper.getAssetEntries(hits);

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
        return _language.get(locale, "all-apps", "Aplicaciones");
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {

        LOG.debug("Activate MyApplicationslnfoCollectionProvider");

        _configuration = ConfigurableUtil.createConfigurable(
        		FavoritosConfiguration.class, properties);
    }

    @Reference
    private Language _language;
    /*@Reference
    private ApplicationSettings _applicationSettings;*/
    @Reference
    private SearchingJournal _searchingJournal;
    @Reference
    AssetHelper _assetHelper;

    private volatile FavoritosConfiguration _configuration;
    private static final Log LOG = LoggerUtil.getLog(AllApplicationslnfoCollectionProvider.class);

}
