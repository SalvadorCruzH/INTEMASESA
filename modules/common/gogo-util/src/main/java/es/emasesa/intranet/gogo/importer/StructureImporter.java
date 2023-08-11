package es.emasesa.intranet.gogo.importer;

import com.liferay.dynamic.data.mapping.io.DDMFormDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormDeserializerDeserializeRequest;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.dynamic.data.mapping.constants.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageType;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
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
import es.emasesa.intranet.gogo.base.GogoConstant;
import es.emasesa.intranet.gogo.base.JSONFileUtil;
import es.emasesa.intranet.gogo.model.JsonStructure;
import es.emasesa.intranet.gogo.base.CustomGogoLogger;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        service = StructureImporter.class
)
public class StructureImporter {

    public boolean importStructure(final JSONObject structure, final boolean override) throws PortalException {

        final JsonStructure jsonStructure = new JsonStructure(structure);
        final String structureFullFileName = GogoConstant.CONFIG_PATH_STRUCTURE_BASE+jsonStructure.getStructureFile();
        final String definition = JSONFileUtil.getContentUTF8(FrameworkUtil.getBundle(this.getClass()).getEntry(structureFullFileName));

        final long classNameId = _portal.getClassNameId(JournalArticle.class);
        final String mode = StringPool.BLANK;
        final ServiceContext serviceContext = new ServiceContext();
        serviceContext.setAddGroupPermissions(Boolean.TRUE);
        serviceContext.setAddGuestPermissions(Boolean.FALSE);

        // Get ENV config
        final Company company = _customGetter.getCompanyByWebId(jsonStructure.getCompanyWebId());
        final long companyId = company.getCompanyId();
        final Group group = _customGetter.getGroupByFriendlyUrl(companyId, jsonStructure.getGroupFriendlyUrl());
        final long groupId = group.getGroupId();
        final Locale locale = LocaleUtil.fromLanguageId(group.getDefaultLanguageId());
        LocaleThreadLocal.setSiteDefaultLocale(locale);
        final Map<Locale, String> nameMap = _customGetter.getLocaleMap(locale, jsonStructure.getName());
        final Map<Locale, String> descriptionMap = _customGetter.getLocaleMap(locale, jsonStructure.getComment());
        final User user = _customGetter.getUser(jsonStructure.getScreenName(), companyId);
        final long userId = user.getUserId();
        long parentStructureId = DDMStructureConstants.DEFAULT_PARENT_STRUCTURE_ID;
        final String parentStructureKey = jsonStructure.getParentStructureKey();
        final Group parentStrGroup = _customGetter.getGroupByFriendlyUrl(companyId, jsonStructure.getParentGroupFriendlyUrl());
        final String uuid = jsonStructure.getUuid();

        if(!Validator.isBlank(parentStructureKey) && Validator.isNotNull(parentStrGroup)) {
            final DDMStructure parentDDMStructure = _ddmStructureLocalService.fetchStructure(parentStrGroup.getGroupId(), classNameId, parentStructureKey);
            if(Validator.isNotNull(parentDDMStructure)) {
                parentStructureId = parentDDMStructure.getStructureId();
            }
        }

        final String storageType = StorageType.DEFAULT.toString();

        final DDMStructure actualDDMStructure;

        if (Validator.isBlank(jsonStructure.getUuid())){
            actualDDMStructure = _ddmStructureLocalService.fetchStructure(groupId, classNameId, jsonStructure.getStructureKey());
        } else {
            actualDDMStructure = _ddmStructureLocalService.fetchDDMStructureByUuidAndGroupId(jsonStructure.getUuid(), groupId);
            serviceContext.setUuid(uuid);
        }

        final boolean groupPermissions = Boolean.TRUE;
        final boolean guestPermissions = Boolean.FALSE;
        final int type = DDMStructureConstants.TYPE_DEFAULT;

        final DDMForm ddmForm;

        ddmForm = _ddm.getDDMForm(definition);

        _ddm.updateDDMFormDefaultLocale(ddmForm, locale);

        final DDMFormLayout ddmFormLayout = _ddm.getDefaultDDMFormLayout(ddmForm);
        ddmFormLayout.setDefaultLocale(locale);

        boolean updated=Boolean.FALSE;
        try {
            CustomGogoLogger.gogoPrintln("[Structure] Importing "+jsonStructure.getStructureKey()+" .");

            if (Validator.isNull(actualDDMStructure)) {
                // ACTUAL NULL
                CustomGogoLogger.gogoPrintln("[Structure] Creating "+jsonStructure.getStructureKey()+" .");

                /** OLD IMPL
                 final DDMStructure newStructure = _ddmStructureLocalService.addStructure(
                 userId, groupId, parentStructureId, classNameId,
                 jsonStructure.getStructureKey(), nameMap, descriptionMap,
                 definition, storageType, serviceContext);
                 **/
                final DDMStructure newStructure = _ddmStructureLocalService.addStructure(
                        userId, groupId, parentStructureId, classNameId,
                        jsonStructure.getStructureKey(), nameMap, descriptionMap,
                        ddmForm, ddmFormLayout,
                        storageType, type, serviceContext);

                updated = Validator.isNotNull(newStructure);

                if (updated){
                    _ddmStructureLocalService.addStructureResources(newStructure, groupPermissions, guestPermissions);
                    //updated = importLayout(jsonStructure, newStructure, serviceContext);
                }

            } else {
                // ACTUAL NOT NULL
                if (override) {
                    CustomGogoLogger.gogoPrintln("[Structure] Overriding "+ jsonStructure.getStructureKey()+" .");
                    //actualDDMStructure.setDefinition(definition);

                    if(actualDDMStructure.getParentStructureId() != parentStructureId) {
                        actualDDMStructure.setParentStructureId(parentStructureId);
                    }
                    /**            updateStructure
                     * 			long userId, long structureId, DDMForm ddmForm,
                     * 			DDMFormLayout ddmFormLayout, ServiceContext serviceContext)
                     */
                    /** OLD IMPL
                     final DDMStructure structureImported =_ddmStructureLocalService.updateDDMStructure(actualDDMStructure);
                     **/
                    final DDMStructure structureImported =_ddmStructureLocalService.updateStructure(userId, actualDDMStructure.getStructureId(), ddmForm, ddmFormLayout, serviceContext);

                    updated = Validator.isNotNull(structureImported);

                    if(updated) {
                        //updated = importLayout(jsonStructure, structureImported, serviceContext);
                    }
                } else {
                    CustomGogoLogger.gogoPrintln("[Structure] Override = false .");
                    updated = Boolean.TRUE;
                }
            }

        } catch (Exception e){
            CustomGogoLogger.gogoPrintln("[Structure] Error importing "+jsonStructure.getStructureKey()+" : "+e.getMessage());
            e.printStackTrace();
        }
        return updated;
    }

    @Deprecated
    private boolean importLayout(final JsonStructure jsonStructure, final DDMStructure structureImported, final ServiceContext serviceContext) throws PortalException {

        CustomGogoLogger.gogoPrintln("[Structure] Importing Layout for "+jsonStructure.getStructureKey()+" .");

        final String structureLayoutFullFileName = GogoConstant.CONFIG_PATH_STRUCTURE_LAYOUT_BASE+jsonStructure.getStructureFile();
        final String layoutDefinition = JSONFileUtil.getContentUTF8(FrameworkUtil.getBundle(this.getClass()).getEntry(structureLayoutFullFileName));

        final DDMStructureLayout currentStructureLayout = _ddmStructureLayoutLocalService.fetchStructureLayout(structureImported.getGroupId(), structureImported.getClassNameId(), jsonStructure.getStructureKey());

        if(Validator.isNull(currentStructureLayout)) {
            _ddmStructureLayoutLocalService.addStructureLayout(structureImported.getUserId(), structureImported.getGroupId(), structureImported.getClassNameId(), jsonStructure.getStructureKey(), structureImported.getStructureVersion().getStructureVersionId(), structureImported.getNameMap(), structureImported.getDescriptionMap(), layoutDefinition, serviceContext);
        } else {
            currentStructureLayout.setUserId(structureImported.getUserId());
            currentStructureLayout.setStructureVersionId(structureImported.getStructureVersion().getStructureVersionId());
            currentStructureLayout.setNameMap(structureImported.getNameMap());
            currentStructureLayout.setDescriptionMap(structureImported.getDescriptionMap());
            currentStructureLayout.setDefinition(layoutDefinition);
            _ddmStructureLayoutLocalService.updateDDMStructureLayout(currentStructureLayout);
        }

        return Boolean.TRUE;
    }


    @Reference
    DDMStructureLocalService _ddmStructureLocalService;

    @Reference
    DDMStructureLayoutLocalService _ddmStructureLayoutLocalService;

    @Reference
    CustomGetterUtil _customGetter;

    @Reference
    Portal _portal;

    @Reference
    DDMFormDeserializer _ddmFormDeserializer;

    @Reference
    Language _language;

    @Reference
    private DDM _ddm;


}
