package es.emasesa.intranet.document.contributor;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionVersionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalService;
import es.emasesa.intranet.base.constant.EmasesaConstants;

import java.util.*;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.function.Consumer;

@Component(immediate = true,
        property = {
                "indexer.class.name=com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
                "service.ranking:Integer=11111"
        },
        service = ModelDocumentContributor.class)
public class KaleoTaskInstanceTokenModelDocumentContributor
        implements ModelDocumentContributor<KaleoTaskInstanceToken> {

    private static final String ASSET_CLASS_NAME_ID = "assetClassNameId";

    private static final String ASSET_CLASS_PK = "assetClassPK";

    private static final String ASSET_DESCRIPTION = "assetDescription";

    private static final String ASSET_TITLE = "assetTitle";

    private static final String ASSIGNEE_CLASS_NAME_IDS = "assigneeClassNameIds";

    private static final String ASSIGNEE_CLASS_PKS = "assigneeClassPKs";

    private static final String ASSIGNEE_GROUP_IDS = "assigneeGroupIds";

    private static final String CLASS_NAME = "className";

    private static final String COMPLETED = "completed";

    private static final String COMPLETION_DATE = "completionDate";

    private static final String DUE_DATE = "dueDate";

    private static final String KALEO_DEFINITION_ID = "kaleoDefinitionId";

    private static final String KALEO_INSTANCE_ID = "kaleoInstanceId";

    private static final String KALEO_TASK_ID = "kaleoTaskId";

    private static final String KALEO_TASK_INSTANCE_TOKEN_ID =
            "kaleoTaskInstanceTokenId";

    private static final String TASK_NAME = "taskName";

    private static final String MATRICULA = "matricula";

    private static final String FULLNAME = "fullName";

    private static final String ASSIGNEE_USERNAMES = "assigneeUserNames";

    @Override
    public void contribute(
            Document document, KaleoTaskInstanceToken kaleoTaskInstanceToken) {

        List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
                kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

        Set<Long> assigneeClassNameIds = new HashSet<>();
        Set<Long> assigneeClassPKs = new HashSet<>();
        Set<Long> assigneeGroupIds = new HashSet<>();
        Set<String> assigneeUserName = new HashSet<>();

        for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance :
                kaleoTaskAssignmentInstances) {

            assigneeClassNameIds.add(
                    portal.getClassNameId(
                            kaleoTaskAssignmentInstance.getAssigneeClassName()));
            assigneeClassPKs.add(
                    kaleoTaskAssignmentInstance.getAssigneeClassPK());
            assigneeGroupIds.add(kaleoTaskAssignmentInstance.getGroupId());
            assigneeUserName.add(kaleoTaskAssignmentInstance.getUserName());
            try {
                if(kaleoTaskAssignmentInstance.getAssigneeClassName().equals(User.class.getName())){
                    User userAss = _userLocalService.getUser(kaleoTaskAssignmentInstance.getAssigneeClassPK());
                    document.addKeywordSortable("assigneePersonName", userAss.getFullName());
                }

            } catch (Exception e) {
                if (_log.isWarnEnabled()) {
                    _log.warn(e);
                }
            }
        }

        document.addKeyword(
                ASSIGNEE_CLASS_NAME_IDS,
                assigneeClassNameIds.toArray(new Long[0]));
        document.addKeyword(
                ASSIGNEE_CLASS_PKS,
                assigneeClassPKs.toArray(new Long[0]));
        document.addKeyword(
                ASSIGNEE_GROUP_IDS,
                assigneeGroupIds.toArray(new Long[0]));
        document.addKeyword(
                CLASS_NAME,
                kaleoTaskInstanceToken.getClassName());
        document.addKeyword(
                Field.CLASS_NAME_ID,
                portal.getClassNameId(kaleoTaskInstanceToken.getClassName()));
        document.addKeyword(
                Field.CLASS_PK, kaleoTaskInstanceToken.getClassPK());
        document.addKeywordSortable(
                COMPLETED,
                kaleoTaskInstanceToken.isCompleted());
        document.addDateSortable(
                COMPLETION_DATE,
                kaleoTaskInstanceToken.getCompletionDate());
        document.addDateSortable(
                Field.CREATE_DATE, kaleoTaskInstanceToken.getCreateDate());
        document.addDateSortable(
                DUE_DATE,
                kaleoTaskInstanceToken.getDueDate());
        document.addKeyword(
                ASSIGNEE_USERNAMES,
                assigneeUserName.toArray(new String[0]));

        try {

            User user = _userLocalService.getUser(kaleoTaskInstanceToken.getUserId());
            if (user.getExpandoBridge().hasAttribute(EmasesaConstants.EMASESA_EXPANDO_MATRICULA)) {
                if (Validator.isNotNull(user.getExpandoBridge().getAttribute(EmasesaConstants.EMASESA_EXPANDO_MATRICULA, false))) {
                    document.addKeywordSortable(MATRICULA, (String) user.getExpandoBridge().getAttribute(EmasesaConstants.EMASESA_EXPANDO_MATRICULA, false));
                    document.addKeywordSortable(FULLNAME, (String) user.getFullName());
                }
            }
            try {
                document.addKeywordSortable("entryType",
                        JSONFactoryUtil.createJSONObject(kaleoTaskInstanceToken.getWorkflowContext()).getJSONObject("map").getString("entryType"));
                String entryClassName = JSONFactoryUtil.createJSONObject(kaleoTaskInstanceToken.getWorkflowContext()).getJSONObject("map").getString("entryClassName");
                if (entryClassName.contains("ObjectDefinition")) {
                    Long entryClassPK = JSONFactoryUtil.createJSONObject(kaleoTaskInstanceToken.getWorkflowContext()).getJSONObject("map").getLong("entryClassPK");
                    JSONObject objetEstado = JSONFactoryUtil.createJSONObject(_objectEntryLocalService.getObjectEntry(entryClassPK).getValues());
                    document.addKeywordSortable("estadoObjeto", "" + objetEstado.get("estadoObjeto"));

                }
            } catch (Exception e) {
                if (_log.isWarnEnabled()) {
                    _log.warn(e);
                }
            }
            KaleoDefinitionVersion kaleoDefinitionVersion =
                    kaleoDefinitionVersionLocalService.getKaleoDefinitionVersion(
                            kaleoTaskInstanceToken.getKaleoDefinitionVersionId());

            KaleoDefinition kaleoDefinition =
                    kaleoDefinitionVersion.getKaleoDefinition();

            document.addKeyword(
                    KALEO_DEFINITION_ID,
                    kaleoDefinition.getKaleoDefinitionId());


        } catch (PortalException portalException) {
            if (_log.isWarnEnabled()) {
                _log.warn(portalException);
            }
        }

        document.addNumberSortable(
                KALEO_INSTANCE_ID,
                kaleoTaskInstanceToken.getKaleoInstanceId());
        document.addNumberSortable(
                KALEO_TASK_ID,
                kaleoTaskInstanceToken.getKaleoTaskId());
        document.addNumberSortable(
                KALEO_TASK_INSTANCE_TOKEN_ID,
                kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
        document.addDateSortable(
                Field.MODIFIED_DATE, kaleoTaskInstanceToken.getModifiedDate());
        document.addKeywordSortable(
                TASK_NAME,
                kaleoTaskInstanceToken.getKaleoTaskName());
        document.addNumberSortable(
                Field.USER_ID, kaleoTaskInstanceToken.getUserId());

        addAssetEntryAttributes(
                assetEntry -> {
                    document.addKeyword(
                            ASSET_CLASS_NAME_ID,
                            assetEntry.getClassNameId());
                    document.addKeyword(
                            ASSET_CLASS_PK,
                            assetEntry.getClassPK());
                },
                kaleoTaskInstanceToken.getClassName(),
                kaleoTaskInstanceToken.getClassPK(), document,
                kaleoTaskInstanceToken.getGroupId());

        kaleoTaskInstanceToken.isCompleted();
        document.addText("completed", Boolean.toString(kaleoTaskInstanceToken.isCompleted()));
    }

    protected String[] getLanguageIds(
            String defaultLanguageId, String content) {

        String[] languageIds = _localization.getAvailableLanguageIds(content);

        if (languageIds.length == 0) {
            languageIds = new String[]{defaultLanguageId};
        }

        return languageIds;
    }

    protected void addAssetEntryAttributes(
            Consumer<AssetEntry> assetEntryConsumer, String className, long classPK,
            Document document, long groupId) {

        AssetEntry assetEntry = _getAssetEntry(className, classPK);

        if (assetEntry != null) {
            document.addLocalizedText(
                    "assetDescription",
                    LocalizationUtil.populateLocalizationMap(
                            assetEntry.getDescriptionMap(),
                            assetEntry.getDefaultLanguageId(),
                            assetEntry.getGroupId()));
            document.addLocalizedText(
                    "assetTitle",
                    LocalizationUtil.populateLocalizationMap(
                            assetEntry.getTitleMap(), assetEntry.getDefaultLanguageId(),
                            assetEntry.getGroupId()));

            assetEntryConsumer.accept(assetEntry);
        } else {
            WorkflowHandler<?> workflowHandler =
                    WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

            if (workflowHandler == null) {
                return;
            }

            for (Locale availableLocale :
                    LanguageUtil.getAvailableLocales(groupId)) {

                document.addText(
                        LocalizationUtil.getLocalizedName(
                                "assetTitle", LocaleUtil.toLanguageId(availableLocale)),
                        workflowHandler.getTitle(classPK, availableLocale));
            }
        }
    }

    private AssetEntry _getAssetEntry(String className, long classPK) {
        try {
            AssetRenderer<?> assetRenderer = _getAssetRenderer(
                    className, classPK);

            if (assetRenderer != null) {
                return assetEntryLocalService.getEntry(
                        assetRenderer.getClassName(), assetRenderer.getClassPK());
            }
        } catch (PortalException portalException) {
            if (_log.isDebugEnabled()) {
                _log.debug(portalException);
            }
        }

        return null;
    }

    private AssetRenderer<?> _getAssetRenderer(String className, long classPK)
            throws PortalException {

        AssetRendererFactory<?> assetRendererFactory =
                AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
                        className);

        if (assetRendererFactory != null) {
            return assetRendererFactory.getAssetRenderer(classPK);
        }

        return null;
    }

    @Reference
    protected ClassNameLocalService classNameLocalService;

    @Reference
    protected KaleoDefinitionVersionLocalService
            kaleoDefinitionVersionLocalService;

    @Reference
    protected ObjectEntryLocalService _objectEntryLocalService;
    @Reference
    protected Portal portal;
    @Reference
    protected UserLocalService _userLocalService;
    @Reference
    AssetEntryLocalService assetEntryLocalService;

    private static final Log _log = LogFactoryUtil.getLog(
            KaleoTaskInstanceTokenModelDocumentContributor.class);

    @Reference
    private Localization _localization;

}