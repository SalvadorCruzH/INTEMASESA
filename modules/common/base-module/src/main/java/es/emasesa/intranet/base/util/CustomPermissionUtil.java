package es.emasesa.intranet.base.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
immediate = true,
property = {
},
service = CustomPermissionUtil.class)
public class CustomPermissionUtil {

    public static final String[] ACTIONS_VIEW = new String[]{ActionKeys.VIEW};
    public static final String[] ACTIONS_ACCESS = new String[]{ActionKeys.ACCESS};

    private final static Log _log = LoggerUtil.getLog(CustomPermissionUtil.class);

    public boolean validateGuestLayoutPermission(final Layout layout) {

        boolean setPermission = Boolean.FALSE;

        try {
            final Role role = roleLocalService.fetchRole(layout.getCompanyId(), RoleConstants.GUEST);

            setPermission = _resourcePermissionService.hasResourcePermission(
                    layout.getCompanyId(),
                    Layout.class.getName(),
                    ResourceConstants.SCOPE_INDIVIDUAL,
                    String.valueOf(layout.getPrimaryKey()),
                    role.getRoleId(),
                    ActionKeys.VIEW
            );

        } catch (PortalException e) {
            LoggerUtil.error(_log, e);
        }
        return setPermission;
    }

    public boolean setPermissions(final long companyId,
                                 final String className,
                                 final long roleId,
                                 final int resourceConstantScope,
                                 final String resourcePrimaryKey,
                                 final String[] actionKeysArray) {
        boolean setPermission = Boolean.FALSE;
        try {
            _resourcePermissionService.setResourcePermissions(companyId, className,
                    resourceConstantScope,
                    resourcePrimaryKey,
                    roleId,
                    actionKeysArray);
            setPermission = Boolean.TRUE;
        } catch (PortalException e) {
            LoggerUtil.error(_log, e);
        }
        return setPermission;
    }

    public boolean setFileEntryDefaultViewPermission(long companyId, long roleId, long fileEntryId) {
        return setFileEntryDefaultPermission(companyId,roleId,fileEntryId,ACTIONS_VIEW);
    }

    public boolean setFileEntryDefaultPermission(long companyId, long roleId, long fileEntryId, String[] actionKeys) {
        boolean setPermission = Boolean.FALSE;
        if (fileEntryId > 0){
            try {
                final DLFileEntry dlFileEntry = _dlFileEntryLocalService.fetchDLFileEntry(fileEntryId);
                if (Validator.isNotNull(dlFileEntry)) {
                    _resourcePermissionService.setResourcePermissions(companyId, DLFileEntry.class.getName(),
                            ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlFileEntry.getPrimaryKey()), roleId,
                            actionKeys);
                    setPermission = Boolean.TRUE;
                }else{
                    LoggerUtil.error(_log, "File "+fileEntryId+ " not found.");
                }
            } catch (PortalException e) {
                LoggerUtil.error(_log, e);
            }
        }
        return setPermission;
    }

    public boolean setFileFolderDefaultViewPermission(long companyId, long roleId, long dlFolderId, String[] actionKeys) {
        boolean setPermission = Boolean.FALSE;
        if (dlFolderId > 0){
            try {
                final DLFolder dlFolderEntry = _dlFolderEntryLocalService.fetchDLFolder(dlFolderId);
                if (Validator.isNotNull(dlFolderEntry)) {
                    _resourcePermissionService.setResourcePermissions(companyId, DLFolder.class.getName(),
                            ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlFolderEntry.getPrimaryKey()), roleId,
                            actionKeys);
                    setPermission = Boolean.TRUE;
                }else{
                    LoggerUtil.error(_log, "Folder "+dlFolderId+ " not found.");
                }
            } catch (PortalException e) {
                LoggerUtil.error(_log, e);
            }
        }
        return setPermission;
    }

    public boolean setFileEntryDefaultViewPermission(final long companyId, final long roleId, final FileEntry fileEntry) {
        return (Validator.isNotNull(fileEntry)?
                setFileEntryDefaultViewPermission(companyId,roleId,fileEntry.getFileEntryId()):
                Boolean.FALSE);
    }

    public boolean setJournalArticleDefaultViewPermission(final JournalArticle journalArticle, final long roleId) {
        boolean setPermission = Boolean.FALSE;
        if (Validator.isNotNull(journalArticle)) {
            setPermission = setPermissions(
                    journalArticle.getCompanyId(),
                    JournalArticle.class.getName(),
                    roleId,
                    ResourceConstants.SCOPE_INDIVIDUAL,
                    String.valueOf(journalArticle.getResourcePrimKey()),
                    ACTIONS_VIEW);
        }
        return setPermission;
    }

    public boolean setJournalArticleDefaultViewPermission(final JournalArticle journalArticle, final String roleKey) {
        boolean setPermission = Boolean.FALSE;
        if (Validator.isNotNull(journalArticle)) {
            final Role role = roleLocalService.fetchRole(journalArticle.getCompanyId(), roleKey);
            setPermission = Validator.isNotNull(role)
                    && setJournalArticleDefaultViewPermission(journalArticle, role.getRoleId());
        }
        return setPermission;
    }

    public boolean setDLFolderDefaultViewPermission(final long companyId, final long roleId, final long folderId) {
        return setDLFolderDefaultViewPermission(companyId,roleId,folderId);
    }

    public boolean setDLFolderDefaultPermission(final long companyId, final long roleId, final long folderId, String[] actionKeys) {
        boolean setPermission = Boolean.FALSE;
        if (folderId > 0){
            try {
                final DLFolder dlFolder = _dlFolderEntryLocalService.getDLFolder(folderId);
                _resourcePermissionService.setResourcePermissions(
                        companyId,
                        DLFolder.class.getName(),
                        ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(dlFolder.getPrimaryKey()),
                        roleId,
                        actionKeys);
                setPermission = Boolean.TRUE;
            } catch (PortalException e) {
                LoggerUtil.error(_log, e);
            }
        }
        return setPermission;
    }

    public boolean setDLFolderDefaultViewPermission(final long companyId, final long roleId, final DLFolder dlFolder) {
        return (Validator.isNotNull(dlFolder)?
                setDLFolderDefaultViewPermission(companyId, roleId, dlFolder.getFolderId()):
                Boolean.FALSE);
    }


    public boolean havePermssionPortlet(ThemeDisplay themeDisplay,String actionKey){
        boolean have = false;

        try {
            have = PortletPermissionUtil.contains(themeDisplay.getPermissionChecker(),themeDisplay.getLayout(),themeDisplay.getPortletDisplay().getId(),actionKey);
        } catch (PortalException e) {
            LoggerUtil.warn(_log,e);
        }


        return have;
    }

    public boolean hasUserGroup(final ThemeDisplay themeDisplay, final String groupName){
        boolean hasPermission;

        hasPermission = themeDisplay.getPermissionChecker().isOmniadmin() || themeDisplay.getUser().getUserGroups()
                .stream().anyMatch(userGroup -> userGroup.getName().equalsIgnoreCase(groupName));

        return hasPermission;
    }

    @Reference
    Portal _portal;

    @Reference
    private ResourcePermissionLocalService _resourcePermissionService;

    @Reference
    private UserGroupLocalService userGroupLocalService;

    @Reference
    private DLFileEntryLocalService _dlFileEntryLocalService;

    @Reference
    private DLFolderLocalService _dlFolderEntryLocalService;


    @Reference
    RoleLocalService roleLocalService;


}