package es.emasesa.intranet.gogo.importer;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.display.template.PortletDisplayTemplate;
import es.emasesa.intranet.base.util.CustomEmasesaUtil;
import es.emasesa.intranet.base.util.CustomGetterUtil;
import es.emasesa.intranet.gogo.base.CustomGogoLogger;
import es.emasesa.intranet.gogo.base.GogoConstant;
import es.emasesa.intranet.gogo.base.JSONFileUtil;
import es.emasesa.intranet.gogo.model.JsonADT;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.Map;

@Component(
    immediate = true,
    service = ADTImporter.class
)
public class ADTImporter {

    public boolean importADT(final JSONObject adt, final boolean override){

        final JsonADT jsonADT = new JsonADT(adt);
        final String templateFullFileName = GogoConstant.CONFIG_PATH_ADT_BASE+jsonADT.getTemplateFile();
        final String script = JSONFileUtil.getContentUTF8(FrameworkUtil.getBundle(this.getClass()).getEntry(templateFullFileName));
        final String language = _customGetter.getDDMTemplateLanguage(jsonADT.getTemplateFile());

        final long classNameId = _portal.getClassNameId(jsonADT.getType());
        final long classPK=0;
        final long resourceClassNameId=_portal.getClassNameId(PortletDisplayTemplate.class);

        final String ddmType =  jsonADT.getDisplayType();

        final String mode = StringPool.BLANK;
        final ServiceContext serviceContext = new ServiceContext();

        // Get ENV config
        final Company company = _customGetter.getCompanyByWebId(jsonADT.getCompanyWebId());
        final long companyId = company.getCompanyId();
        final Group group = _customGetter.getGroupByFriendlyUrl(companyId, jsonADT.getGroupFriendlyUrl());
        final long groupId = group.getGroupId();
        final Locale locale = LocaleUtil.fromLanguageId(group.getDefaultLanguageId());
        final String uuid = jsonADT.getUuid();

        LocaleThreadLocal.setSiteDefaultLocale(locale);
        final Map<Locale, String> nameMap = _customGetter.getLocaleMap(locale, jsonADT.getName());
        final Map<Locale, String> descriptionMap = _customGetter.getLocaleMap(locale, jsonADT.getComment());
        User user = null;
        try {
            user = _customEmasesaUtil.getEmasesaAdmin(companyId);
        } catch (PortalException e) {
            user = _customGetter.getUser(jsonADT.getScreenName(), companyId);
        }
        final long userId = user.getUserId();

        final DDMTemplate actualDDMTemplate;
        if (!Validator.isBlank(uuid)){
            serviceContext.setUuid(uuid);
            actualDDMTemplate = _ddmTemplateLocalService.fetchDDMTemplateByUuidAndGroupId(uuid, groupId);
        } else {
            actualDDMTemplate = _ddmTemplateLocalService.fetchTemplate(groupId, classNameId, jsonADT.getTemplateKey());
        }

        boolean updated=Boolean.FALSE;
        try {
            CustomGogoLogger.gogoPrintln("[ADT] Importing "+jsonADT.getTemplateKey()+" .");

            if (Validator.isNull(actualDDMTemplate)) {

                serviceContext.setAttribute("templateKey", jsonADT.getTemplateKey());
                CustomGogoLogger.gogoPrintln("[ADT] Creating "+jsonADT.getTemplateKey()+" .");

                updated = Validator.isNotNull(_ddmTemplateLocalService.addTemplate(
                        userId, groupId, classNameId,
                        classPK, resourceClassNameId, jsonADT.getTemplateKey(),
                        nameMap, descriptionMap, ddmType, mode,
                        language, script, Boolean.TRUE, Boolean.FALSE,
                        StringPool.BLANK, null, serviceContext));

            } else {
                if (override) {
                    CustomGogoLogger.gogoPrintln("[ADT] Overriding "+jsonADT.getTemplateKey()+" . ( "+actualDDMTemplate.getUuid()+" )");
                    actualDDMTemplate.setNameMap(nameMap);
                    actualDDMTemplate.setDescriptionMap(descriptionMap);
                    actualDDMTemplate.setLanguage(language);
                    actualDDMTemplate.setScript(script);
                    updated = Validator.isNotNull(_ddmTemplateLocalService.updateDDMTemplate(actualDDMTemplate));
                } else {
                    CustomGogoLogger.gogoPrintln("[ADT] Override = false . ( "+actualDDMTemplate.getUuid()+" )");
                    updated = Boolean.TRUE;
                }
            }
        } catch (Exception e){
            CustomGogoLogger.gogoPrintln("[ADT] Error importing "+jsonADT.getTemplateKey()+" : "+e.getMessage());
            e.printStackTrace();
        }
        return updated;
    }

    @Reference
    DDMTemplateLocalService _ddmTemplateLocalService;

    @Reference
    CustomGetterUtil _customGetter;

    @Reference
    CustomEmasesaUtil _customEmasesaUtil;

    @Reference
    Portal _portal;
}
