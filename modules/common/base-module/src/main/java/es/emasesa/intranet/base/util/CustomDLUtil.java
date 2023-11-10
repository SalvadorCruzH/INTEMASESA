package es.emasesa.intranet.base.util;

import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.List;

@Component(
        immediate = true,
        property = {
        },
        service = CustomDLUtil.class)
public class CustomDLUtil {

    @Reference
    DLAppLocalService _dlAppLocalService;

    @Reference
    RoleLocalService _roleLocalService;

    @Reference
    CustomPermissionUtil _customPermissionUtil;

    /**
     * Crea un nuevo documento
     *
     * @param userId
     * @param folderId
     * @param repositoryId
     * @param fileName
     * @param mimeType
     * @param bytes
     * @param serviceContext
     *
     * @return Identificador del documeto.
     */
    public long createFileEntry(final long userId,
                                final long folderId,
                                final long repositoryId,
                                final String fileName,
                                final String mimeType,
                                final byte[] bytes,
                                final ServiceContext serviceContext) {
        return createFileEntry(userId, folderId, repositoryId, fileName, mimeType, bytes, serviceContext, RoleConstants.SITE_MEMBER, CustomPermissionUtil.ACTIONS_VIEW);
    }

    /**
     * Crea un nuevo documento
     *
     * @param userId
     * @param folderId
     * @param repositoryId
     * @param fileName
     * @param mimeType
     * @param bytes
     * @param serviceContext
     *
     * @return Identificador del documeto.
     */
    public long createFileEntry(final long userId,
                                final long folderId,
                                final long repositoryId,
                                final String fileName,
                                final String title,
                                final String description,
                                final String changelog,
                                final String mimeType,
                                final byte[] bytes,
                                final ServiceContext serviceContext,
                                final String roleKey,
                                final String[] actionKeys ) {
        long createFileEntry = -1l;
        FileEntry fileEntry = null;

        try {
            fileEntry = _dlAppLocalService.getFileEntry(serviceContext.getScopeGroupId(), folderId, fileName);
            if (Validator.isNotNull(fileName)){
                _dlAppLocalService.deleteFileEntry(fileEntry.getFileEntryId());
            }
        } catch (Exception e){
            // Fetch
        }

        try {

            fileEntry = _dlAppLocalService.addFileEntry(null, userId, repositoryId,
                    folderId, fileName, mimeType, title,
                    StringPool.BLANK, description, changelog, bytes,
                    null, null, Validator.isNull(serviceContext) ? new ServiceContext() : serviceContext
            );

            createFileEntry = (Validator.isNotNull(fileEntry)?fileEntry.getFileEntryId():-1l);

            final Role dfltRole = _roleLocalService.getRole(fileEntry.getCompanyId(), roleKey);
            _customPermissionUtil.setFileEntryDefaultPermission(
                    fileEntry.getCompanyId(),
                    dfltRole.getRoleId(),
                    fileEntry.getFileEntryId(),
                    actionKeys);
        } catch (PortalException e){
            LoggerUtil.error(_log, e);
        }
        return createFileEntry;
    }

    public long createFileEntry(final long userId,
                                final long folderId,
                                final long repositoryId,
                                final String fileName,
                                final String mimeType,
                                final byte[] bytes,
                                final ServiceContext serviceContext,
                                final String roleKey,
                                final String[] actionKeys ) {

        return createFileEntry(userId, folderId, repositoryId, fileName, fileName, StringPool.BLANK, StringPool.BLANK, mimeType, bytes, serviceContext, roleKey, actionKeys );

    }

    /**
     * Crea las carpetas en una ruta path "/una/ruta/"
     *
     * @param parentFolderId
     * @param path
     * @return the folderId created
     **/
    public long createFoldersByPath(final long userId,
                                    final long parentFolderId,
                                    final long repositoryId,
                                    final String path,
                                    final ServiceContext serviceContext) {

        long currentFolderId = parentFolderId;
        List<String> folderNamesByPath = getFolderNamesByPath(path);
        Folder currentFolder;

        for (String folderName :  folderNamesByPath) {
            currentFolder = null;
            if (currentFolderId > 0) {
                try {
                    currentFolder = _dlAppLocalService.getFolder(repositoryId, currentFolderId, folderName);
                } catch (NoSuchFolderException e) {
                    // OK
                } catch (PortalException e) {
                    LoggerUtil.error(_log, e);
                }

                currentFolderId = createFolderForSiteMemberRole(userId, repositoryId, currentFolderId, folderName, currentFolder, serviceContext);
            } else {
                break;
            }
        }

        return currentFolderId;
    }

    private long createFolderForSiteMemberRole(final long userId,
                                               final long repositoryId,
                                               long currentFolderId,
                                               final String folderName,
                                               Folder currentFolder,
                                               final ServiceContext serviceContext) {

        if (Validator.isNotNull(currentFolder)) {
            currentFolderId = currentFolder.getFolderId();
        } else {
            try {
                currentFolder = _dlAppLocalService.addFolder(
                        null,
                        userId,
                        repositoryId,
                        currentFolderId,
                        folderName,
                        StringPool.BLANK,
                        Validator.isNull(serviceContext)? new ServiceContext() : serviceContext);

                currentFolderId = currentFolder.getFolderId();

                final Role siteMemberRole = _roleLocalService.getRole(currentFolder.getCompanyId(), RoleConstants.SITE_MEMBER);

                _customPermissionUtil.setDLFolderDefaultViewPermission(
                        currentFolder.getCompanyId(),
                        siteMemberRole.getRoleId(),
                        currentFolderId);

            } catch (PortalException e) {
                LoggerUtil.error(_log, e);
                currentFolderId = -1l;
            }
        }

        return currentFolderId;
    }

    /**
     * Crea las carpetas en una ruta path "/una/ruta/"
     *
     * @param parentFolderId
     * @param path
     * @return the folderId created
    **/
    public long createFoldersByPath(final long userId,
                                    final long parentFolderId,
                                    final long repositoryId,
                                    final String path,
                                    final String folderDesc,
                                    final ServiceContext serviceContext,
                                    final String roleKey,
                                    final String[] actionKeys) {

        long currentFolderId = parentFolderId;
        List<String> folderNamesByPath = getFolderNamesByPath(path);
        Folder currentFolder;

        for (String folderName :  folderNamesByPath) {
            currentFolder = null;
            if (currentFolderId > 0) {
                try {
                    currentFolder = _dlAppLocalService.getFolder(repositoryId, currentFolderId, folderName);
                } catch (NoSuchFolderException e) {
                    // OK
                } catch (PortalException e) {
                    LoggerUtil.error(_log, e);
                }

                currentFolderId = createFolderForGivenRole(userId, repositoryId, currentFolderId, folderName, folderDesc, currentFolder, serviceContext, roleKey, actionKeys);
            } else {
                break;
            }
        }

        return currentFolderId;
    }
    private long createFolderForGivenRole(final long userId,
                                          final long repositoryId,
                                          long currentFolderId,
                                          final String folderName,
                                          final String folderDesc,
                                          Folder currentFolder,
                                          final ServiceContext serviceContext,
                                          final String roleKey,
                                          final String[] actionKeys) {

        if (Validator.isNotNull(currentFolder)) {
            currentFolderId = currentFolder.getFolderId();
        } else {
            try {
                currentFolder = _dlAppLocalService.addFolder(null,
                        userId,
                        repositoryId,
                        currentFolderId,
                        folderName,
                        folderDesc,
                        Validator.isNull(serviceContext)? new ServiceContext() : serviceContext);

                currentFolderId = currentFolder.getFolderId();

                final Role role = _roleLocalService.getRole(currentFolder.getCompanyId(), roleKey);
                _customPermissionUtil.setDLFolderDefaultPermission(
                        currentFolder.getCompanyId(),
                        role.getRoleId(),
                        currentFolderId,
                        actionKeys);

            } catch (PortalException e) {
                LoggerUtil.error(_log, e);
                currentFolderId = -1L;
            }
        }
        return currentFolderId;
    }

    public static final List<String> getFolderNamesByPath(final String path) {
        String[] splitFolderByPath = new String[]{};
        String fixedPath = path;

        if (!Validator.isBlank(path)) {
            fixedPath = fixedPath.trim();

            if (path.startsWith(StringPool.FORWARD_SLASH)) {
                fixedPath = fixedPath.substring(1, fixedPath.length());
            }

            if (path.endsWith(StringPool.FORWARD_SLASH)) {
                fixedPath = fixedPath.substring(0, fixedPath.length() - 1);
            }

            splitFolderByPath = fixedPath.split(StringPool.FORWARD_SLASH);
        }

        return Arrays.asList(splitFolderByPath);
    }

    public String getDownloadURL(FileEntry fileEntry, ThemeDisplay themeDisplay, boolean appendVersion, boolean absoluteURL) throws PortalException {
        return DLURLHelperUtil.getDownloadURL(fileEntry, fileEntry.getLatestFileVersion(), themeDisplay, "", appendVersion, absoluteURL);
    }

    public String getDownloadURL(FileEntry fileEntry, ThemeDisplay themeDisplay) throws PortalException {
        return getDownloadURL(fileEntry, themeDisplay, false, false);
    }

    public String getDownloadURL(DLFileEntry dlFileEntry, ThemeDisplay themeDisplay) throws PortalException {
        return getDownloadURL(dlFileEntry, themeDisplay, false, false);
    }

    public String getDownloadURL(DLFileEntry dlFileEntry, ThemeDisplay themeDisplay, boolean appendVersion, boolean absoluteURL) throws PortalException {
        FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dlFileEntry.getFileEntryId());
        return getDownloadURL(fileEntry, themeDisplay, appendVersion, absoluteURL);
    }

    private static final Log _log = LoggerUtil.getLog(CustomDLUtil.class);


}
