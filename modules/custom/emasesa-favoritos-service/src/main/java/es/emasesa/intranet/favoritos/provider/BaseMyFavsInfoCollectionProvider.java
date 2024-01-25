package es.emasesa.intranet.favoritos.provider;

import com.liferay.asset.kernel.exception.NoSuchEntryException;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.sort.Sort;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.LoggerUtil;

public abstract class BaseMyFavsInfoCollectionProvider {

    protected SearchContext _buildSearchContext(CollectionQuery collectionQuery) throws PortalException {

        SearchContext searchContext = new SearchContext();

        searchContext.setCompanyId(CompanyThreadLocal.getCompanyId());

        _searchRequestBuilderFactory.builder(
                searchContext
        ).fetchSource(
                true
        ).build();

        //searchContext.set
        Sort sort = collectionQuery.getSort();
        if (sort != null) {

            searchContext.setSorts(new com.liferay.portal.kernel.search.Sort(sort.getFieldName(), sort.isReverse()));
        } else {
            searchContext.setSorts(
                    new com.liferay.portal.kernel.search.Sort(
                            Field.MODIFIED_DATE,
                            com.liferay.portal.kernel.search.Sort.LONG_TYPE, true));
        }
        searchContext.setStart(collectionQuery.getPagination().getStart());
        searchContext.setEnd(collectionQuery.getPagination().getEnd());

        QueryConfig queryConfig = searchContext.getQueryConfig();

        queryConfig.setHighlightEnabled(false);
        queryConfig.setScoreEnabled(false);

        return searchContext;
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

           /* Map<String, Serializable> nestedValues = toMap(document.getValues("nestedFieldArray"));
            for(Map.Entry<String, Serializable> field : nestedValues.entrySet()){
                switch (field.getKey()){
                    case "title":
                        assetEntry.getTitleMap().put(LocaleThreadLocal.getThemeDisplayLocale(), ""+field.getValue());
                        assetEntry.setTitle(""+field.getValue(), LocaleThreadLocal.getThemeDisplayLocale());
                        assetEntry.setTitle(""+field.getValue());
                        break;
                    case "url":
                        assetEntry.setUrl(""+field.getValue());
                        break;
                    default:
                        break;
                }

            }*/
        }

        return assets;
    }

    protected Map<String, Serializable> toMap(String[] nestedFieldArray) throws PortalException{
        Map<String, Serializable> retorno = new HashMap<>();
        for(String nested: nestedFieldArray){
            nested = Arrays.stream(nested.split(StringPool.COMMA)).map(s ->
                s.replaceFirst("\\{", "").replaceFirst("}", "")).collect(Collectors.joining(StringPool.NEW_LINE));
            UnicodeProperties ucp = UnicodePropertiesBuilder.fastLoad(nested).build();
            retorno.put(ucp.getProperty("fieldName"), ucp.getProperty(ucp.getProperty("valueFieldName")));
        }

        return retorno;
    }

    @Reference
    protected AssetEntryService _assetEntryService;

    @Reference
    protected SearchRequestBuilderFactory _searchRequestBuilderFactory;

    @Reference
    ClassNameLocalService _classNameLocalService;

    private static final Log LOG = LoggerUtil.getLog(BaseMyFavsInfoCollectionProvider.class);

}
