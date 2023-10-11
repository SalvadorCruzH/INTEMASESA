package es.emasesa.intranet.settings.util;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.CustomCacheSingleUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component(
        immediate = true,
        service = CustomGetterCategoryLayout.class
)
public class CustomGetterCategoryLayout {
    private static Log LOG = LogFactoryUtil.getLog(CustomGetterCategoryLayout.class);
    public List<Layout> getDescendantsByCategory(Layout parentLayout, String categoryId) {
        final String cacheKey = CustomGetterCategoryLayout.class.getName() + "@getDescendantsByCategory_" + parentLayout + StringPool.UNDERLINE + categoryId ;

        // Get Cache data from static methods
        List<Layout> pages = null;
        PortalCache<String, Object> cache = CustomCacheSingleUtil.getPortalCache();
        pages = (List<Layout>) CustomCacheSingleUtil.get(cache, cacheKey);
        if(Validator.isNull(pages)) {
            // If cache is empty, get result and store in cache
            try {
                pages = getDescendantsByCategoryWithoutCache(parentLayout, categoryId);
            } catch (PortalException e) {
                LOG.error("Error busqueda de paginas por categorias en la cache",e);
            }

            if(pages != null) {
                CustomCacheSingleUtil.put(cache, cacheKey, pages, CustomCacheSingleUtil.TTL_10_MIN);
            }
        }
        if(Validator.isNull(pages)) {
            pages = new ArrayList<Layout>();
        }
        return pages;
    }

    public List<Layout> getDescendantsByCategoryWithoutCache(Layout parentLayout, String categoryId) throws PortalException {
        List<Layout> pages = new ArrayList<>();

        if (parentLayout != null) {
            List<Long> listAssetEntryId = getAssetEntryCategoryLayouts(Long.parseLong(categoryId));
            if (listAssetEntryId.isEmpty()) {
                return pages;
            }
            List<String> listNameDescendants = new ArrayList<>();
            List<Layout> descendantsLayoutList =  parentLayout.getAllChildren();
            descendantsLayoutList.forEach(layout -> {
                listNameDescendants.add(layout.getUuid());
            });

            for (Long assetEntryId : listAssetEntryId) {
                AssetEntry assetEntry = _assetEntryLocalService.getEntry(assetEntryId);
                Layout childLayout = _layoutLocalService.getLayoutByUuidAndGroupId(assetEntry.getClassUuid(), parentLayout.getGroupId(), false);
                if (listNameDescendants.contains(childLayout.getUuid())) {
                    pages.add(childLayout);
                }
            }

            Comparator<Layout> comparador = Comparator.comparing(Layout::getModifiedDate);
            pages.sort(comparador.reversed());
        }
        return pages;
    }

    public List<Long> getAssetEntryCategoryLayouts(long categoryId) throws PortalException {
        List<Long> assetEntryId = new ArrayList<>();
        List<AssetEntryAssetCategoryRel> assetEntryCategory = _assetEntryAssetCategoryRelLocalService.getAssetEntryAssetCategoryRelsByAssetCategoryId(categoryId);
        for (AssetEntryAssetCategoryRel category : assetEntryCategory) {
            assetEntryId.add(category.getAssetEntryId());
        }
        return assetEntryId;
    }

    @Reference
    LayoutLocalService _layoutLocalService;

    @Reference
    AssetEntryLocalService _assetEntryLocalService;

    @Reference
    AssetEntryAssetCategoryRelLocalService _assetEntryAssetCategoryRelLocalService;
}
