package es.emasesa.intranet.portlet.ajaxsearch.impl.documents.form;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AjaxSearchPortletKeys.PORTLET_AJAXSEARCH_FORM,
                "mvc.command.name=/ajax/get-categorias"
        },
        service = MVCResourceCommand.class
)
public class GetCategoriasResource implements MVCResourceCommand {
    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException {

        try {
            int categoriaId = ParamUtil.getInteger(resourceRequest, "categoryId", -1);
            if(categoriaId != -1) {

                JSONArray jsonArray = _jsonFactory.createJSONArray();

                List<AssetCategory> subcategorias = _assetCategoryLocalService.getChildCategories(categoriaId);

                for (AssetCategory subcategoria : subcategorias) {
                    jsonArray.put(
                            _jsonFactory.createJSONObject()
                                    .put("subCategoryId", subcategoria.getCategoryId())
                                    .put("titleCurrentValue", subcategoria.getTitle(resourceRequest.getLocale()))
                    );
                }

                resourceResponse.setContentType("application/json");
                resourceResponse.getWriter().write(jsonArray.toString());
            } else {
                SessionErrors.add(resourceRequest, "Error");
            }
        }catch (Exception e) {
            _log.error("Error", e);
        }

        return Boolean.TRUE;
    }
    @Reference
    AssetCategoryLocalService _assetCategoryLocalService;

    @Reference
    JSONFactory _jsonFactory;

    private final static Log _log = LoggerUtil.getLog(GetCategoriasResource.class);
}
