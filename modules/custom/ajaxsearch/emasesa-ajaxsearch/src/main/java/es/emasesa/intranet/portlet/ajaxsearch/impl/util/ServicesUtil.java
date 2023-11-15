package es.emasesa.intranet.portlet.ajaxsearch.impl.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.message.boards.util.comparator.CategoryTitleComparator;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import es.emasesa.intranet.base.constant.LongConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component(
        immediate = true,
        property = { },
        service = ServicesUtil.class
)
public class ServicesUtil {

    private static final List<AssetCategory> EMPTY_CAT = new ArrayList<>();

    public List<AssetCategory> getOrderedVocabularyCategories(final long vocId){
        final List<AssetCategory> catList = vocId>0?
                assetCategoryLocalService.getVocabularyCategories(
                        vocId,
                        QueryUtil.ALL_POS,
                        QueryUtil.ALL_POS,
                        new CategoryTitleComparator<>(Boolean.FALSE)): //new CustomAssetCategoryCreateDateComparator(Boolean.TRUE)):
                EMPTY_CAT;

        return catList;
    }

    public String getCleanQueryText(final String queryText){
        return (queryText.length()<128)?queryText:StringPool.BLANK;

    }

    public Set<Long> getSelectedCategories(final String selCatsParam){
        final Set<Long> assetCategories = new HashSet<>();
        if (selCatsParam.length()>0 && selCatsParam.length()<128 && selCatsParam.matches("[0-9,]+") && !selCatsParam.contains(",,")){
            final String[] splited = selCatsParam.split(",");
            for (String cat : splited){
                final long catId = GetterUtil.getLong(cat, LongConstants.MINUS_ONE);
                if (catId>0){
                    assetCategories.add(catId);
                }
            }
        }
        return assetCategories;
    }


    @Reference
    AssetCategoryLocalService assetCategoryLocalService;

}
