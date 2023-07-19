package es.emasesa.intranet.base.util;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.publisher.util.AssetPublisherHelper;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleResourceLocalService;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletRequestModelFactory;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Component(
        immediate = true,
        property = {},
        service = CustomJournalUtil.class)
public class CustomJournalUtil {

    Log LOG = LoggerUtil.getLog(CustomJournalUtil.class);

    public boolean cleanJournalLayout(final JournalArticle ja){
        boolean cleanJournalLayout=Boolean.FALSE;
        try {
            final DDMStructure ddmStructure = ja.getDDMStructure();
            final AssetEntry assetEntry = assetEntryLocalService.fetchEntry(JournalArticle.class.getName(), ja.getResourcePrimKey());

            if (Validator.isNotNull(ddmStructure) && Validator.isNotNull(assetEntry)) {
                final LayoutPageTemplateEntry defaultLayoutPageTemplateEntry =
                        layoutPageTemplateEntryLocalService.fetchDefaultLayoutPageTemplateEntry(ja.getGroupId(),
                                ddmStructure.getClassNameId(), ddmStructure.getStructureId());

                final AssetDisplayPageEntry assetDisplayPageEntry = assetDisplayPageEntryLocalService.fetchAssetDisplayPageEntry(
                        ja.getGroupId(),
                        ddmStructure.getClassNameId(),
                        ja.getResourcePrimKey());

                // ASSET DISPLAY PAGE ENTRY
                if (Validator.isNotNull(assetDisplayPageEntry)
                        && Validator.isNotNull(defaultLayoutPageTemplateEntry)) {
                    assetDisplayPageEntry.setPlid(defaultLayoutPageTemplateEntry.getPlid());
                    assetDisplayPageEntry.setType(AssetDisplayPageConstants.TYPE_DEFAULT);
                    assetDisplayPageEntry.setLayoutPageTemplateEntryId(defaultLayoutPageTemplateEntry.getLayoutPageTemplateEntryId());
                    assetDisplayPageEntryLocalService.updateAssetDisplayPageEntry(assetDisplayPageEntry);
                } else {
                    assetDisplayPageEntryLocalService.addAssetDisplayPageEntry(
                            defaultLayoutPageTemplateEntry.getUserId(),
                            ja.getGroupId(),
                            ddmStructure.getClassNameId(),
                            ja.getResourcePrimKey(),
                            defaultLayoutPageTemplateEntry.getLayoutPageTemplateEntryId(),
                            AssetDisplayPageConstants.TYPE_DEFAULT,
                            new ServiceContext());
                }
            }

            // JOURNAL
            if (!Validator.isBlank(ja.getLayoutUuid())){
                ja.setLayoutUuid(null);
                journalArticleLocalService.updateJournalArticle(ja);
            }

            // ASSET
            if (!Validator.isBlank(assetEntry.getLayoutUuid())){
                assetEntry.setLayoutUuid(null);
                assetEntryLocalService.updateAssetEntry(assetEntry);
            }

            cleanJournalLayout=Boolean.TRUE;
        } catch (Exception e){
            LoggerUtil.error(LOG, e.getMessage());
        }
        return cleanJournalLayout;
    }


    public String getHTMLFromJournalArticle(final String articleId,
                                            final long groupId,
                                            final String ddmTemplateKey,
                                            final ThemeDisplay themeDisplay){
        String html = StringPool.BLANK;
        try {
            final JournalArticle article = journalArticleLocalService.fetchArticle(groupId, articleId);
            if (Validator.isNotNull(article)) {
                html = getHTMLFromJournalArticle(
                        article, themeDisplay,
                        null, null,
                        ddmTemplateKey);
            }
        } catch ( Exception e) {
            //
        }
        return html;
    }


    public String getHTMLFromJournalArticle(final JournalArticle article, final ThemeDisplay themeDisplay,
                                            final PortletRequest request, final PortletResponse response,
                                            final String ddmTemplateKey){

        return getHTMLFromJournalArticle(article.getGroupId(), article.getArticleId(), article.getVersion(),
                themeDisplay,
                request, response, ddmTemplateKey);
    }

    public String getHTMLFromJournalArticle(final long groupId, final String articleId, final double version,
                                            final ThemeDisplay themeDisplay,
                                            final PortletRequest request, final PortletResponse response,
                                            final String ddmTemplateKey){

        String html;
        try {
            html = journalArticleLocalService.getArticleContent(
                    groupId,
                    articleId,
                    version,
                    Constants.VIEW,
                    ddmTemplateKey,
                    themeDisplay.getLanguageId(),
                    (Validator.isNull(request) || Validator.isNull(response))?
                            null:
                            new PortletRequestModelFactory(request, response).getPortletRequestModel(),
                    themeDisplay);
        } catch ( Exception e) {
            html = StringPool.BLANK;
        }
        return html;
    }


    public String getHTMLFromJournalArticle(final JournalArticle article, final ThemeDisplay themeDisplay, final String ddmTemplateKey){
        return getHTMLFromJournalArticle(article, themeDisplay,null, null, ddmTemplateKey);
    }

    public String getHTMLFromJournalArticle(final JournalArticle article, final ThemeDisplay themeDisplay){
        return getHTMLFromJournalArticle(article, themeDisplay, article.getDDMTemplateKey());
    }

    /** only for search **/
    public final String getFriendlyUrlFromJournalArticle(
            final ThemeDisplay themeDisplay,
            final long classPK,
            final String currentViewUrl) {
        String returnedUrl = currentViewUrl;
        if (!currentViewUrl.contains("/w/")) {
            returnedUrl = getGroupFriendlyUrlFromJournalArticle(themeDisplay, classPK);
        }
        return returnedUrl;
    }

    public final String getGroupFriendlyUrlFromJournalArticle(
            final ThemeDisplay themeDisplay,
            final long classPK) {
        String returnedUrl = StringPool.FORWARD_SLASH;
        try {
            final AssetEntry assetEntry = assetEntryLocalService.fetchEntry(JournalArticle.class.getName(), classPK);
            final AssetRenderer<JournalArticle> assetRenderer = (AssetRenderer<JournalArticle>) assetEntry.getAssetRenderer();
            returnedUrl = getJAFriendlyUrl(themeDisplay, assetRenderer.getAssetObject());
        } catch (Exception e) {
            LoggerUtil.warn(LOG, e.getMessage());
        }
        return returnedUrl;
    }

    public final String getFriendlyUrlFromJournalArticle(
            final LiferayPortletRequest request,
            final LiferayPortletResponse response,
            final long classPK) {
        final AssetEntry assetEntry = assetEntryLocalService.fetchEntry(JournalArticle.class.getName(), classPK);

        return assetPublisherHelper.getAssetViewURL(
                request,
                response,
                assetEntry.getAssetRenderer(),
                assetEntry,
                Boolean.TRUE);
    }

    public final String getFriendlyUrlFromJournalArticle(
            final LiferayPortletRequest request,
            final LiferayPortletResponse response,
            final long groupId,
            final String articleId) {

        return getFriendlyUrlFromJournalArticle(request, response, journalArticleResourceLocalService.getArticleResourcePrimKey(groupId, articleId));

    }

    private final String getJAFriendlyUrl(final ThemeDisplay themeDisplay, final JournalArticle journalArticle) throws Exception {
        final Group group = groupLocalService.fetchGroup(journalArticle.getGroupId());
        return portal.getGroupFriendlyURL(group.getPublicLayoutSet(), themeDisplay, themeDisplay.getLocale()) + "/w/"+journalArticle.getUrlTitle(themeDisplay.getLocale())+"?p_l_back_url="+ HtmlUtil.escapeURL(themeDisplay.getURLCurrent());
    }

    @Reference
    JournalArticleResourceLocalService journalArticleResourceLocalService;

    @Reference
    AssetPublisherHelper assetPublisherHelper;

    @Reference
    GroupLocalService groupLocalService;

    @Reference
    Portal portal;

    @Reference
    JournalArticleLocalService journalArticleLocalService;

    @Reference
    AssetDisplayPageEntryLocalService assetDisplayPageEntryLocalService;

    @Reference
    DDMStructureLocalService ddmStructureLocalService;

    @Reference
    LayoutLocalService layoutLocalService;

    @Reference
    AssetEntryLocalService assetEntryLocalService;

    @Reference
    LayoutPageTemplateEntryLocalService layoutPageTemplateEntryLocalService;


}
