package es.emasesa.intranet.base.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import java.text.SimpleDateFormat;

import es.emasesa.intranet.base.constant.EmasesaConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
    immediate = true,
    service = CustomGetterUtil.class
)
public class CustomGetterUtil {

    private static final Log _log = LoggerUtil.getLog(CustomGetterUtil.class);

    public Company getCompany(final long companyId){
        final Company company;
        if (companyId > 0) {
            company = _companyLocalService.fetchCompany(companyId);
        } else {
            company = _companyLocalService.fetchCompany(_portal.getDefaultCompanyId());
        }
        return company;
    }

    public Company getCompanyByWebId(final String companyWebId){
        Company company = null;
        try {
            company = _companyLocalService.getCompanyByWebId(companyWebId);
        } catch (Exception e) {
            LoggerUtil.error(_log, e);
        }
        return company;
    }

    public static Company getCompanyByWebIdStatic(final String companyWebId){
        Company company = null;
        try {
            company = CompanyLocalServiceUtil.getCompanyByWebId(companyWebId);
        } catch (Exception e) {
            LoggerUtil.error(_log, e);
        }
        return company;
    }

    public Group getGroup(final long groupId, final long companyId){
        Group group = null;
        if (groupId > 0) {
            group = _groupLocalService.fetchGroup(groupId);
        } else {
            final Company company = getCompany(companyId);
            try {
                group = company.getGroup();
            } catch (Exception e){
                LoggerUtil.error(_log, e);
            }
        }

        return group;
    }

    public Group getGroupByKey(final long companyId, final String groupKey){
        return _groupLocalService.fetchGroup(companyId, groupKey);
    }

    public Group getGroupByFriendlyUrl(final long companyId, final String friendlyUrl){
        return _groupLocalService.fetchFriendlyURLGroup(companyId, friendlyUrl);
    }

    public User getUser(final String screenName, final long companyId){
        User user = null;
        if (!Validator.isBlank(screenName)) {
            user = _userLocalService.fetchUserByScreenName(companyId, screenName);
        } else {
            final Company company = getCompany(companyId);
            try {
                user = company.getDefaultUser();
            } catch (Exception e){
                LoggerUtil.error(_log, e);
            }
        }
        return user;
    }


    public DDMTemplate getDDMTemplate(final long id){
        final DDMTemplate template;
        if (id > 0) {
            template = _ddmTemplateLocalService.fetchTemplate(id);
        } else {
            template = null;
        }
        return template;
    }


    public String getDDMTemplateLanguage(final String fileName){
        final String extension = FileUtil.getExtension(fileName);
        if (extension.equals(TemplateConstants.LANG_TYPE_CSS) ||
                extension.equals(TemplateConstants.LANG_TYPE_FTL) ||
                extension.equals(TemplateConstants.LANG_TYPE_VM)) {
            return extension;
        }
        return TemplateConstants.LANG_TYPE_VM;
    }

    public Map<Locale, String> getLocaleMap(final String languageId, final String value) {
        return getLocaleMap(LocaleUtil.fromLanguageId(languageId), value, Boolean.TRUE);
    }

    public Map<Locale, String> getLocaleMap(final Locale locale, final String value) {
        return getLocaleMap(locale, value, Boolean.TRUE);
    }

    public Map<Locale, String> getLocaleMap(final Locale locale, final String value, final boolean addDefault) {
        final Map<Locale, String> map = new HashMap<>();
        map.put(locale, value);

        if (addDefault) {
            final Locale siteDefLocale = LocaleUtil.getSiteDefault();
            if (!siteDefLocale.equals(locale)) {
                map.put(siteDefLocale, value);
            }
        }
        return map;
    }

    public DDMStructure getDDMStructure(final long structureId){
        DDMStructure ddmStructure = null;
        if (structureId>0) {
            ddmStructure = _ddmStructureLocalService.fetchDDMStructure(structureId);
        }
        return ddmStructure;
    }

    public DDMTemplate getDDMTemplateByTemplateKey(final long groupId, final long classNameId, final String key){
        DDMTemplate ddmTemplate = null;
        if (!Validator.isBlank(key)) {
            ddmTemplate = _ddmTemplateLocalService.fetchTemplate(groupId, classNameId, key);
        }
        return ddmTemplate;
    }



    public Layout getLayout(SiteNavigationMenuItem siteNavigationMenuItem) {
        UnicodeProperties unicodeProperties = getUnicodeProperties(
                siteNavigationMenuItem);

        String layoutUuid = unicodeProperties.get("layoutUuid");
        boolean privateLayout = GetterUtil.getBoolean(
                unicodeProperties.get("privateLayout"));

        return _layoutLocalService.fetchLayoutByUuidAndGroupId(
                layoutUuid, siteNavigationMenuItem.getGroupId(), privateLayout);
    }

    public List<Document> getGroupsUser(ThemeDisplay themeDisplay, int start, int end){

            BooleanQuery booleanQuery = queries.booleanQuery();
            booleanQuery.addMustQueryClauses(queries.term("expando__keyword__custom_fields__isWorkGroup",true));
            booleanQuery.addMustQueryClauses(queries.match(EmasesaConstants.USERS_GROUP,themeDisplay.getUserId()));
            SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.builder();
            searchRequestBuilder.emptySearchEnabled(true);
            searchRequestBuilder.entryClassNames(Group.class.getName());

            searchRequestBuilder.query(booleanQuery);
            searchRequestBuilder.withSearchContext(
                    searchContext -> {
                        searchContext.setCompanyId(themeDisplay.getCompanyId());
                        searchContext.setStart(start);
                        searchContext.setEnd(end);
                    }
            );

            SearchResponse searchResponse = searcher.search(searchRequestBuilder.build());

            List<Document> documents = searchResponse.getDocuments();

            return documents;

    }

    public DLFolder getFolder(ThemeDisplay themeDisplay,String folderName) throws PortalException {
        DLFolder folder = _dlFolderLocalService.fetchFolder(themeDisplay.getScopeGroupId(),0, folderName);

        if(Validator.isNull(folder)){
            folder = _dlFolderLocalService.addFolder(null, themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), themeDisplay.getScopeGroupId(),
                    false,0,folderName, StringPool.BLANK,false,
                    ServiceContextThreadLocal.getServiceContext());
        }

        return folder;
    }

    public JSONObject getFileInfo(ThemeDisplay themeDisplay,AssetEntry assetEntry){
        JSONObject json = JSONFactoryUtil.createJSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm");


        try {
            FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(assetEntry.getClassPK());
            String url = DLURLHelperUtil.getDownloadURL(fileEntry,fileEntry.getFileVersion(),themeDisplay,StringPool.BLANK);

            json.put("title",assetEntry.getTitle(themeDisplay.getLocale()));
            json.put("description",assetEntry.getDescription(themeDisplay.getLocale()));
            json.put("url",url);
            json.put("ext",fileEntry.getExtension());
            json.put("author",fileEntry.getUserName());
            json.put("size", LanguageUtil.formatStorageSize(fileEntry.getSize(),themeDisplay.getLocale()));
            json.put("date",sdf.format(fileEntry.getModifiedDate()));
            json.put("downloadCount",fileEntry.getReadCount());

        } catch (PortalException e) {
            throw new RuntimeException(e);
        }


        return json;

    }

    public long getClassNameId(Class<?> clazz){
        return _portal.getClassNameId(clazz);
    }
    public long getClassNameId(String className){
        return _portal.getClassNameId(className);
    }

    private UnicodeProperties getUnicodeProperties(
            SiteNavigationMenuItem siteNavigationMenuItem) {

        if (siteNavigationMenuItem == null) {
            return new UnicodeProperties();
        }

        return UnicodePropertiesBuilder.fastLoad(
                siteNavigationMenuItem.getTypeSettings()
        ).build();
    }

    @Reference
    DDMTemplateLocalService _ddmTemplateLocalService;

    @Reference
    DDMStructureLocalService _ddmStructureLocalService;

    @Reference
    GroupLocalService _groupLocalService;

    @Reference
    UserLocalService _userLocalService;

    @Reference
    CompanyLocalService _companyLocalService;

    @Reference
    LayoutLocalService _layoutLocalService;

    @Reference
    DLFolderLocalService _dlFolderLocalService;

    @Reference
    Queries queries;
    @Reference
    Searcher searcher;
    @Reference
    SearchRequestBuilderFactory searchRequestBuilderFactory;

    @Reference
    Portal _portal;
}
