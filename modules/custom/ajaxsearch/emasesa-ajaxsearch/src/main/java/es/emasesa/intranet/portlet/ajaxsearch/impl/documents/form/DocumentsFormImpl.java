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

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);


        String categoryId = ajaxSearchDisplayContext.getConfig().getOrDefault(CATEGORY_ID, StringPool.BLANK);

        if(!categoryId.isBlank()){
            
            List<AssetCategory> categories = obtenerCategorias(Long.parseLong(categoryId));
            //List<AssetCategory> subcategories = obtenerSubcategorias(Long.parseLong(categoryId));

            request.setAttribute("categories", categories);
            //request.setAttribute("subcategories", subcategories);
        }

        request.setAttribute("catSelected", ajaxSearchDisplayContext.getLong("catSelected"));
        if(ajaxSearchDisplayContext.getLong("catSelected") != 0){
            List<AssetCategory> subcategories = obtenerSubcategoriasdeCategoria(ajaxSearchDisplayContext.getLong("catSelected"));
            request.setAttribute("subcategories", subcategories);
        }
        request.setAttribute("subCatSelected", ajaxSearchDisplayContext.getLong("subCatSelected"));

        request.setAttribute("sort", ajaxSearchDisplayContext.getLong("sort"));


        return VIEW;
    }

    //  Parte comunidades, provincias, municipios
    public List<AssetCategory> obtenerCategorias(long vocabularyId) {
        return AssetCategoryLocalServiceUtil.getVocabularyRootCategories(vocabularyId, -1, -1, null);
    }

    public List<AssetCategory> obtenerSubcategoriasdeCategoria(long parentCategoryId) {
        return AssetCategoryLocalServiceUtil.getChildCategories(parentCategoryId);
    }

    public List<AssetCategory> obtenerHijasDeSubcategorias(AssetCategory categoriaPadre) {
        List<AssetCategory> hijasDeSubcategorias = new ArrayList<>();
        obtenerHijasRecursivas(categoriaPadre, hijasDeSubcategorias);
        return hijasDeSubcategorias;
    }
    public void obtenerHijasRecursivas(AssetCategory categoriaPadre, List<AssetCategory> hijas) {
        List<AssetCategory> subcategorias = AssetCategoryLocalServiceUtil.getChildCategories(categoriaPadre.getCategoryId());
        hijas.addAll(subcategorias);

        for (AssetCategory subcategoria : subcategorias) {
            obtenerHijasRecursivas(subcategoria, hijas);
        }
    }

    public List<AssetCategory> obtenerSubcategorias(long vocabularyId) {
        List<AssetCategory> provincias = new ArrayList<>();
        List<AssetCategory> comunidades = obtenerCategorias(vocabularyId);

        for (AssetCategory comunidad : comunidades) {
            List<AssetCategory> subcategoriasCategoria = AssetCategoryLocalServiceUtil.getChildCategories(comunidad.getCategoryId());
            provincias.addAll(subcategoriasCategoria);
        }

        return provincias;
    }
    
    @Reference
    protected Portal _portal;

    @Reference
    CustomGetterUtil customGetterUtil;


    @Reference
    AssetCategoryLocalService assetCategoryLocalService;

}
