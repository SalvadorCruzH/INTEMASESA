package es.emasesa.intranet.favoritos.bean;

import java.io.Serializable;

public class FavoritoBean implements Serializable{
	private long assetEntryId;
    private long classNameId;
    private long groupId;
    private String url;
    private String title;
    private String cmd;
    private String fileExtension;

    private String ddmStructureKey;
    public FavoritoBean(){}

    public FavoritoBean(long assetEntryId, long classNameId, long groupId, String url, String title, String cmd, String fileExtension, String ddmStructureKey){
        this.setAssetEntryId(assetEntryId);
        this.setClassNameId(classNameId);
        this.setGroupId(groupId);
        this.setUrl(url);
        this.setTitle(title);
        this.setCmd(cmd);
        this.setFileExtension(fileExtension);
        this.setDdmStructureKey(ddmStructureKey);
    }

    public long getAssetEntryId() {
        return assetEntryId;
    }

    public void setAssetEntryId(long assetEntryId) {
        this.assetEntryId = assetEntryId;
    }

    public long getClassNameId() {
        return classNameId;
    }

    public void setClassNameId(long classNameId) {
        this.classNameId = classNameId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getDdmStructureKey() {
        return ddmStructureKey;
    }

    public void setDdmStructureKey(String ddmStructureKey) {
        this.ddmStructureKey = ddmStructureKey;
    }
}
