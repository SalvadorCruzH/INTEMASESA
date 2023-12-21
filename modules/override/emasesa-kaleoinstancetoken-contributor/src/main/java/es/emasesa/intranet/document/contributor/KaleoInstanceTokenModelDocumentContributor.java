package es.emasesa.intranet.document.contributor;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.function.Consumer;

@Component(immediate = true,
        property = {
            "indexer.class.name=com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
            "service.ranking:Integer=11111"
        },
        service = ModelDocumentContributor.class)
public class KaleoInstanceTokenModelDocumentContributor implements ModelDocumentContributor<KaleoInstanceToken>  {

    private static final String ASSET_DESCRIPTION = "assetDescription";

    private static final String ASSET_TITLE = "assetTitle";

    private static final String CLASS_NAME = "className";

    private static final String COMPLETED = "completed";

    private static final String COMPLETION_DATE = "completionDate";

    private static final String CURRENT_KALEO_NODE_NAME = "currentKaleoNodeName";

    private static final String KALEO_DEFINITION_NAME = "kaleoDefinitionName";

    private static final String KALEO_INSTANCE_ID = "kaleoInstanceId";

    private static final String KALEO_INSTANCE_TOKEN_ID = "kaleoInstanceTokenId";

    private static final String PARENT_KALEO_INSTANCE_TOKEN_ID =
            "parentKaleoInstanceTokenId";



    public void contribute(
            Document document, KaleoInstanceToken kaleoInstanceToken) {

        document.addKeyword(
                CLASS_NAME,
                kaleoInstanceToken.getClassName());
        document.addKeyword(
                Field.CLASS_NAME_ID,
                portal.getClassNameId(kaleoInstanceToken.getClassName()));
        document.addKeyword(Field.CLASS_PK, kaleoInstanceToken.getClassPK());
        document.addKeywordSortable(
                COMPLETED,
                kaleoInstanceToken.isCompleted());
        document.addDateSortable(
                COMPLETION_DATE,
                kaleoInstanceToken.getCompletionDate());
        document.addDateSortable(
                Field.CREATE_DATE, kaleoInstanceToken.getCreateDate());
        document.addKeywordSortable(
                CURRENT_KALEO_NODE_NAME,
                kaleoInstanceToken.getCurrentKaleoNodeName());
        document.addNumberSortable(
                KALEO_INSTANCE_ID,
                kaleoInstanceToken.getKaleoInstanceId());
        document.addKeyword(
                KALEO_INSTANCE_TOKEN_ID,
                kaleoInstanceToken.getKaleoInstanceTokenId());
        document.addDateSortable(
                Field.MODIFIED_DATE, kaleoInstanceToken.getModifiedDate());
        document.addKeyword(
                PARENT_KALEO_INSTANCE_TOKEN_ID,
                kaleoInstanceToken.getParentKaleoInstanceTokenId());

        try {
            KaleoInstance kaleoInstance =
                    kaleoInstanceLocalService.getKaleoInstance(
                            kaleoInstanceToken.getKaleoInstanceId());

            document.addKeyword(
                    KALEO_DEFINITION_NAME,
                    kaleoInstance.getKaleoDefinitionName());
        }
        catch (PortalException portalException) {
            if (_log.isWarnEnabled()) {
                _log.warn(portalException);
            }
        }

        addAssetEntryAttributes(
                kaleoInstanceToken.getClassName(), kaleoInstanceToken.getClassPK(),
                document, kaleoInstanceToken.getGroupId());
    }

    protected String[] getLanguageIds(
            String defaultLanguageId, String content) {

        String[] languageIds = _localization.getAvailableLanguageIds(content);

        if (languageIds.length == 0) {
            languageIds = new String[] {defaultLanguageId};
        }

        return languageIds;
    }

    protected void addAssetEntryAttributes(
            String className, long classPK, Document document, long groupId) {

        addAssetEntryAttributes(
                assetEntry -> {
                },
                className, classPK, document, groupId);
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
        }
        else {
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

    @Reference
    protected AssetEntryLocalService assetEntryLocalService;

    private AssetEntry _getAssetEntry(String className, long classPK) {
        try {
            AssetRenderer<?> assetRenderer = _getAssetRenderer(
                    className, classPK);

            if (assetRenderer != null) {
                return assetEntryLocalService.getEntry(
                        assetRenderer.getClassName(), assetRenderer.getClassPK());
            }
        }
        catch (PortalException portalException) {
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
    protected KaleoInstanceLocalService kaleoInstanceLocalService;

    @Reference
    protected Portal portal;

    private static final Log _log = LogFactoryUtil.getLog(
            KaleoInstanceTokenModelDocumentContributor.class);

    @Reference
    private Localization _localization;

}
