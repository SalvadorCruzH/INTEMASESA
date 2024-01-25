package es.emasesa.intranet.indexer.post.processor.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.object.model.ObjectEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.LoggerUtil;

@Component(
        immediate = true,
        property = {
        },
        service = PostProcessorUtil.class
)
public class PostProcessorUtil {
	
	   public void postProcessDocument(Document document, Object object) throws Exception {

	        Company c = _companyLocalService.fetchCompany(CompanyThreadLocal.getCompanyId());
	        ObjectEntry objectEntry = (ObjectEntry) object;
	        long assetEntryId = (long) objectEntry.getValues().get("assetEntryId");
	        long classNameId = (long) objectEntry.getValues().get("classNameId");
	        long groupId = (long) objectEntry.getValues().get("assetEntryGroupId");
	        AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(classNameId,assetEntryId);
	        Group group = _groupLocalService.getGroup(groupId);
	        document.addText("groupName",group.getName());
	        document.addText("itemEntryClassPK", String.valueOf(assetEntryId));
	        document.addText("itemEntryClassNameId", String.valueOf(classNameId));
	        document.addText("title", (String) objectEntry.getValues().get("title"));
	        User user =_userLocalService.getUser(assetEntry.getUserId());
	        document.addText("userPortrait",UserConstants.getPortraitURL(
	                "", user.isMale(), user.getPortraitId(),
	                user.getUserUuid()));

	        if(Validator.isNotNull(objectEntry.getValues().get("fileExtension"))){
	            document.addText("extension", (String) objectEntry.getValues().get("fileExtension"));
	        }


	        _log.debug("--------------------------------------");


	    }
	   
	   
	    private static final Log _log = LoggerUtil.getLog(PostProcessorUtil.class);
	   
	    @Reference
	    protected CompanyLocalService _companyLocalService;

	    @Reference
	    protected GroupLocalService _groupLocalService;

	    @Reference
	    protected UserLocalService _userLocalService;
	    
	    @Reference
	    protected AssetEntryLocalService _assetEntryLocalService;
}
