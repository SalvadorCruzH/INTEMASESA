package es.emasesa.intranet.portlet.ajaxsearch.impl.documents.form;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalServiceUtil;
import com.liferay.document.library.util.DLFileEntryTypeUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLink;
import com.liferay.dynamic.data.mapping.service.DDMStructureLinkLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import es.emasesa.intranet.base.util.CustomGetterUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.AjaxSearchDisplayContext;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component(
        immediate = true,
        property = { },
        service = AjaxSearchForm.class
)
public class DocumentsFormImpl implements AjaxSearchForm {

    private static final Properties DFLT_PROPERTIES = new Properties();
    private final static Log LOG = LoggerUtil.getLog(DocumentsFormImpl.class);

    public static final String CATEGORY_ID = "category-id";
    private static final String CATEGORIES = "categories";
    private static final String SUBCATEGORIES = "subcategories";
    private static final String CAT_SELECTED = "catSelected";
    private static final String SUBCAT_SELECTED = "subCatSelected";
    private static final String SORT = "sort";
    private static final String SORTBY = "sortby";

    static {
        DFLT_PROPERTIES.put(CATEGORY_ID, "-1");
    }

    @Override
    public Properties getDefaultProperties() {
        return DFLT_PROPERTIES;
    }

    @Override
    public boolean inProcessAction(ActionRequest actionRequest, ActionResponse response,
                                   AjaxSearchDisplayContext searchDisplayContext) {
        return Boolean.TRUE;
    }

    private static final String VIEW = "/views/documents/form.jsp";

    @Override
    public String getFormView(PortletRequest request, PortletResponse response,
                              AjaxSearchDisplayContext ajaxSearchDisplayContext) {

        String categoryId = ajaxSearchDisplayContext.getConfig().getOrDefault(CATEGORY_ID, StringPool.BLANK);

        if(!categoryId.isBlank()){
            
            List<AssetCategory> categories = obtenerCategorias(Long.parseLong(categoryId));

            request.setAttribute(CATEGORIES, categories);
        }

        request.setAttribute(CAT_SELECTED, ajaxSearchDisplayContext.getLong(CAT_SELECTED));
        if(ajaxSearchDisplayContext.getLong(CAT_SELECTED) != 0){
            List<AssetCategory> subcategories = obtenerSubcategoriasdeCategoria(ajaxSearchDisplayContext.getLong("catSelected"));
            request.setAttribute(SUBCATEGORIES, subcategories);
        }
        request.setAttribute(SUBCAT_SELECTED, ajaxSearchDisplayContext.getLong(SUBCAT_SELECTED));

        request.setAttribute(SORT, ajaxSearchDisplayContext.getLong(SORTBY));


        return VIEW;
    }

    public List<AssetCategory> obtenerCategorias(long vocabularyId) {
        return assetCategoryLocalService.getVocabularyRootCategories(vocabularyId, -1, -1, null);
    }

    public List<AssetCategory> obtenerSubcategoriasdeCategoria(long parentCategoryId) {
        return assetCategoryLocalService.getChildCategories(parentCategoryId);
    }

    @Reference
    AssetCategoryLocalService assetCategoryLocalService;

}
