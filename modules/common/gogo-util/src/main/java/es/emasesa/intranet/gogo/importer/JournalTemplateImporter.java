package es.emasesa.intranet.gogo.importer;

import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.util.CustomGetterUtil;
import es.emasesa.intranet.gogo.base.CustomGogoLogger;
import es.emasesa.intranet.gogo.base.GogoConstant;
import es.emasesa.intranet.gogo.base.JSONFileUtil;
import es.emasesa.intranet.gogo.model.JsonJournalTemplate;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.File;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        service = JournalTemplateImporter.class
)
public class JournalTemplateImporter {

    public boolean importTemplate(final JSONObject template, final boolean override){

        final JsonJournalTemplate jsonTemplate = new JsonJournalTemplate(template);
        final String structureFullFileName = GogoConstant.CONFIG_PATH_JOURNAL_BASE+jsonTemplate.getTemplateFile();
        final String script = JSONFileUtil.getContentUTF8(FrameworkUtil.getBundle(this.getClass()).getEntry(structureFullFileName));
        final String language = _customGetter.getDDMTemplateLanguage(structureFullFileName);
        final long classNameId = _portal.getClassNameId(DDMStructure.class);
        final long resourceClassNameId=_portal.getClassNameId(JournalArticle.class);
        final String uuid = jsonTemplate.getUuid();

        final ServiceContext serviceContext = new ServiceContext();
        serviceContext.setAddGroupPermissions(Boolean.TRUE);
        serviceContext.setAddGuestPermissions(Boolean.FALSE);

        // Get ENV config
        final Company company = _customGetter.getCompanyByWebId(jsonTemplate.getCompanyWebId());
        final long companyId = company.getCompanyId();
        final Group group = _customGetter.getGroupByFriendlyUrl(companyId, jsonTemplate.getGroupFriendlyUrl());
        final long groupId = group.getGroupId();
        final Locale locale = LocaleUtil.fromLanguageId(group.getDefaultLanguageId());

        LocaleThreadLocal.setSiteDefaultLocale(locale);

        final Map<Locale, String> nameMap =  _customGetter.getLocaleMap(locale, jsonTemplate.getName());
        final Map<Locale, String> descriptionMap = _customGetter.getLocaleMap(locale, jsonTemplate.getComment());
        final User user = _customGetter.getUser(jsonTemplate.getScreenName(), companyId);
        final long userId = user.getUserId();
        final String ddmType = DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY;
        final String mode = DDMTemplateConstants.TEMPLATE_MODE_CREATE;

        final DDMStructure actualDDMStructure;
        if (Validator.isBlank(jsonTemplate.getStructureUuid())){
            actualDDMStructure = _ddmStructureLocalService.fetchStructure(groupId, resourceClassNameId, jsonTemplate.getStructureKey());
        } else {
            actualDDMStructure = _ddmStructureLocalService.fetchDDMStructureByUuidAndGroupId(jsonTemplate.getStructureUuid(), groupId);
        }

        final long classPK=actualDDMStructure.getStructureId();
        final DDMTemplate actualDDMTemplate = _ddmTemplateLocalService.fetchTemplate(groupId, classNameId, jsonTemplate.getTemplateKey());
        final boolean smallImage = Boolean.FALSE;
        final String smallImageURL = StringPool.BLANK;
        final File smallImageFile = null;

        if (!Validator.isBlank(uuid)){
            serviceContext.setUuid(uuid);
        }

        boolean updated=Boolean.FALSE;
        try {
            CustomGogoLogger.gogoPrintln("[Journal Template] Importing "+jsonTemplate.getTemplateKey()+" .");

            if (Validator.isNull(actualDDMTemplate)) {
                CustomGogoLogger.gogoPrintln("[Journal Template] Creating "+jsonTemplate.getTemplateKey()+" .");
                final DDMTemplate newTemplate =_ddmTemplateLocalService.addTemplate(
                        userId,
                        groupId,
                        classNameId,
                        classPK,
                        resourceClassNameId,
                        jsonTemplate.getTemplateKey(),
                        nameMap,
                        descriptionMap,
                        ddmType,
                        mode,
                        language,
                        script,
                        jsonTemplate.getCacheable(),
                        smallImage,
                        smallImageURL,
                        smallImageFile,
                        serviceContext);

                updated = Validator.isNotNull(newTemplate);

            } else {
                if (override) {
                    CustomGogoLogger.gogoPrintln("[Journal Template] Overriding "+jsonTemplate.getTemplateKey()+" . ( "+actualDDMTemplate.getUuid()+" )");
                    //actualDDMTemplate.setNameMap(nameMap); // Importante no sobreescribir ya que se usa para las facetas
                    //actualDDMTemplate.setDescriptionMap(descriptionMap); // Importante no sobreescribir ya que se usa para las facetas
                    actualDDMTemplate.setScript(script);
                    actualDDMTemplate.setLanguage(language);
                    updated = Validator.isNotNull(_ddmTemplateLocalService.updateDDMTemplate(actualDDMTemplate));
                } else {
                    CustomGogoLogger.gogoPrintln("[Journal Template] Override = false - "+jsonTemplate.getTemplateKey()+" . ( "+actualDDMTemplate.getUuid()+" )");
                    updated = Boolean.TRUE;
                }
            }
        } catch (Exception e){
            CustomGogoLogger.gogoPrintln("[Journal Template] Error importing NEW " + jsonTemplate.getTemplateKey() + " : " + e.getMessage());
            e.printStackTrace();
        }
        return updated;
    }




    @Reference
    DDMTemplateLocalService _ddmTemplateLocalService;

    @Reference
    DDMStructureLocalService _ddmStructureLocalService;

    @Reference
    CustomGetterUtil _customGetter;

    @Reference
    Portal _portal;

    @Reference
    Language _language;

    @Reference
    private DDM _ddm;

}
