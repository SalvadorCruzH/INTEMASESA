package es.emasesa.intranet.gogo.importer;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.CustomGetterUtil;
import es.emasesa.intranet.gogo.base.CustomGogoLogger;
import es.emasesa.intranet.gogo.model.JsonRole;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = RoleImporter.class
)
public class RoleImporter {

    public boolean importRole(final JSONObject structure){
        boolean created=Boolean.FALSE;

        final JsonRole jsonRole = new JsonRole(structure);

        CustomGogoLogger.gogoPrintln("[Role] Creating "+jsonRole.getName());

        final ServiceContext serviceContext = new ServiceContext();
        serviceContext.setAddGroupPermissions(Boolean.TRUE);
        serviceContext.setAddGuestPermissions(Boolean.FALSE);
        final Company company = _customGetter.getCompanyByWebId(jsonRole.getCompanyWebId());
        final long companyId = company.getCompanyId();
        final String className = Role.class.getName();
        final User user = _customGetter.getUser(jsonRole.getScreenName(), companyId);
        final long userId = user.getUserId();

        final Group group = _customGetter.getGroupByFriendlyUrl(companyId, jsonRole.getGroupFriendlyUrl());
        final Locale locale = LocaleUtil.fromLanguageId(group.getDefaultLanguageId());
        LocaleThreadLocal.setSiteDefaultLocale(locale);

        Map<Locale,String> titleMap = new HashMap<>();
        titleMap.put(locale,jsonRole.getTitle());

        Map<Locale,String> descriptionMap = new HashMap<>();
        descriptionMap.put(locale,jsonRole.getDescription());
        try {
            Role role = _roleLocalService.fetchRole(companyId, jsonRole.getName());
            if(Validator.isNull(role)){
                role =  _roleLocalService.addRole(userId,className,0,jsonRole.getName(),titleMap,descriptionMap,
                        jsonRole.getType(),null,serviceContext);
                CustomGogoLogger.gogoPrintln("[Role] Created "+role.getName());
            }else{
                CustomGogoLogger.gogoPrintln("[Role] Already exist "+role.getName());
            }



            created = Boolean.TRUE;

        } catch (Exception e) {
            CustomGogoLogger.gogoPrintln("[Role] Error importing "+jsonRole.getName()+" : "+e.getMessage()+" : "+e.getCause());
            e.printStackTrace();
        }

        return created;

    }

    @Reference
    RoleLocalService _roleLocalService;
    @Reference
    CustomGetterUtil _customGetter;
    @Reference
    Localization _localization;
}
