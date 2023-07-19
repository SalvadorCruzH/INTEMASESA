package es.emasesa.intranet.gogo.command;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.kernel.service.SocialActivityLocalService;
import es.emasesa.intranet.gogo.base.CustomGogoLogger;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component(immediate = true,
        property = {
                "osgi.command.scope=goUtil",
                "osgi.command.function=getBasicAuth",
                "osgi.command.function=renameTemplateKey",
                "osgi.command.function=renameStructureKey",
                "osgi.command.function=getTemplateUUID",
                "osgi.command.function=getStructureUUID",
                "osgi.command.function=generateUUID",
                "osgi.command.function=updateUUIDtoTemplate",
                "osgi.command.function=updateUUIDtoStructure",
                "osgi.command.function=getStructureLayout",
                "osgi.command.function=getStructureDefinition",
                "osgi.command.function=getStructureInfo",
                "osgi.command.function=getTemplateType",
                "osgi.command.function=removePREUsers",
                "osgi.command.function=removeActivitiesByParentGroup"


        },
        service = Object.class)
public class CustomUtilCommand {

        public void getBasicAuth(final String user, final String pass) throws Exception{
                final MessageDigest md = MessageDigest.getInstance("SHA-1");
                final String basicAuth = user+ StringPool.COLON+"{SHA}"+Base64.getEncoder().encodeToString(md.digest(pass.getBytes(StandardCharsets.UTF_8)));
                CustomGogoLogger.gogoPrintln("Authorization: Basic "+Base64.getEncoder().encodeToString(basicAuth.getBytes(StandardCharsets.UTF_8)));
        }

        public void generateUUID(){
                CustomGogoLogger.gogoPrintln(PortalUUIDUtil.generate());
        }

        public void getTemplateUUID(final long id){
                final DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchDDMTemplate(id);
                if (Validator.isNotNull(ddmTemplate)){
                        CustomGogoLogger.gogoPrintln(ddmTemplate.getUuid());

                } else {
                        CustomGogoLogger.gogoPrintln("Not found");
                }
        }

        public void getStructureUUID(final long id){
                final DDMStructure ddmStructure = _ddmStructureLocalService.fetchStructure(id);
                if (Validator.isNotNull(ddmStructure)){
                        CustomGogoLogger.gogoPrintln(ddmStructure.getUuid());

                } else {
                        CustomGogoLogger.gogoPrintln("Not found");
                }
        }

        public void renameTemplateKey(long templateId, String newTemplateKey, boolean override){

                final DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchDDMTemplate(templateId);

                if (Validator.isNotNull(ddmTemplate)){
                        CustomGogoLogger.gogoPrintln("Found Template "+ddmTemplate.getName(ddmTemplate.getDefaultLanguageId()));
                        if (override){
                                CustomGogoLogger.gogoPrintln("Overriding");
                                ddmTemplate.setTemplateKey(newTemplateKey);
                                _ddmTemplateLocalService.updateDDMTemplate(ddmTemplate);
                        }
                } else {
                        CustomGogoLogger.gogoPrintln("ID does not correspond to any Template");
                }
        }

        public void renameStructureKey(long structureId, String newStructureKey, boolean override){

                final DDMStructure ddmStructure = _ddmStructureLocalService.fetchDDMStructure(structureId);

                if (Validator.isNotNull(ddmStructure)){
                        CustomGogoLogger.gogoPrintln("Found WC Structure "+ddmStructure.getName(ddmStructure.getDefaultLanguageId()));
                        if (override){
                                CustomGogoLogger.gogoPrintln("Overriding");
                                ddmStructure.setStructureKey(newStructureKey);
                                _ddmStructureLocalService.updateDDMStructure(ddmStructure);
                        }
                } else {
                        CustomGogoLogger.gogoPrintln("ID does not correspond to any Structure");
                }
        }

        public void updateUUIDtoTemplate(long templateId, String newUuid, boolean override){

                final DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchDDMTemplate(templateId);

                if (Validator.isNotNull(ddmTemplate)){
                        CustomGogoLogger.gogoPrintln("Found Template "+ddmTemplate.getName(ddmTemplate.getDefaultLanguageId()));
                        if (override){
                                CustomGogoLogger.gogoPrintln("Overriding");
                                ddmTemplate.setUuid(newUuid);
                                _ddmTemplateLocalService.updateDDMTemplate(ddmTemplate);
                        }
                } else {
                        CustomGogoLogger.gogoPrintln("ID does not correspond to any Template");
                }
        }

        public void updateUUIDtoStructure(long structureId, String newUuid, boolean override){

                final DDMStructure ddmStructure = _ddmStructureLocalService.fetchDDMStructure(structureId);

                if (Validator.isNotNull(ddmStructure)){
                        CustomGogoLogger.gogoPrintln("Found WC Structure "+ddmStructure.getName(ddmStructure.getDefaultLanguageId()));
                        if (override){
                                CustomGogoLogger.gogoPrintln("Overriding");
                                ddmStructure.setUuid(newUuid);
                                _ddmStructureLocalService.updateDDMStructure(ddmStructure);
                        }
                } else {
                        CustomGogoLogger.gogoPrintln("ID does not correspond to any Structure");
                }
        }

        public void getStructureLayout(final long structureId){
                CustomGogoLogger.gogoPrintln("LAYOUT : ");
                final DDMStructure ddmStructure = _ddmStructureLocalService.fetchDDMStructure(structureId);
                if (Validator.isNotNull(ddmStructure)){
                        final DDMStructureLayout currentStructureLayout = _ddmStructureLayoutLocalService.fetchStructureLayout(
                                ddmStructure.getGroupId(),
                                ddmStructure.getClassNameId(),
                                ddmStructure.getStructureKey()
                        );
                        if (Validator.isNotNull(currentStructureLayout)){
                                CustomGogoLogger.gogoPrintln(currentStructureLayout.getDefinition());
                        } else {
                                CustomGogoLogger.gogoPrintln("NULL LAYOUT");
                        }
                } else {
                        CustomGogoLogger.gogoPrintln("NULL STR");
                }
        }

        public void getTemplateType(final long templateId){
                CustomGogoLogger.gogoPrintln("TEMPLATE TYPE : ");
                final DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchDDMTemplate(templateId);
                if (Validator.isNotNull(ddmTemplate)){
                        final ClassName className = _classNameLocalService.fetchByClassNameId(ddmTemplate.getClassNameId());
                        if (Validator.isNotNull(ddmTemplate)){
                                CustomGogoLogger.gogoPrintln(className.getValue());
                        } else {
                                CustomGogoLogger.gogoPrintln("NULL CLASSNAME");
                        }
                } else {
                        CustomGogoLogger.gogoPrintln("NULL STR");
                }
        }

        public void getStructureDefinition(final long structureId){
                final DDMStructure ddmStructure = _ddmStructureLocalService.fetchDDMStructure(structureId);
                CustomGogoLogger.gogoPrintln("DEFINITION : ");
                if (Validator.isNotNull(ddmStructure)){
                        CustomGogoLogger.gogoPrintln(ddmStructure.getDefinition());
                } else {
                        CustomGogoLogger.gogoPrintln("NULL STR");
                }
        }

        public void getStructureInfo(final long structureId){
                getStructureDefinition(structureId);
                getStructureLayout(structureId);
        }

        public void removePREUsers(final boolean performRemove) throws Exception {
                final List<User> users = userLocalService.getUsers(-1, -1).stream().filter( t -> t.getScreenName().startsWith("_")).collect(Collectors.toList());

                CustomGogoLogger.gogoPrintln("TOTAL USERS "+users.size());
                for (User user:users) {
                        if (!PortalUtil.isOmniadmin(user) && !PortalUtil.isCompanyAdmin(user)) {
                                CustomGogoLogger.gogoPrintln(" - " + user.getScreenName() + " : "+user.getEmailAddress() );
                                if (performRemove) {
                                        try {
                                                userLocalService.deleteUser(user.getUserId());
                                        } catch (Exception e){
                                                //
                                        }
                                }
                        }
                }
        }


        public void removeActivitiesByParentGroup(final boolean performRemove,final long parentGroupId) throws Exception {
                Group parentGroup = _groupLocalService.getGroup(parentGroupId);

                List<Group> childrenGroup = parentGroup.getChildren(true);
                CustomGogoLogger.gogoPrintln("TOTAL CHILDREN "+childrenGroup.size());
                for(Group group:childrenGroup){
                        CustomGogoLogger.gogoPrintln(group.getGroupId()+" "+group.getName());
                        if(performRemove){
                                _socialActivityLocalService.deleteActivities(group.getGroupId());
                        }


                }

        }

        public void removeActivitiesByGroup(final boolean performRemove,final long groupId) throws Exception {
                Group group = _groupLocalService.getGroup(groupId);

                        CustomGogoLogger.gogoPrintln(group.getGroupId()+" "+group.getName());
                        if(performRemove){
                                _socialActivityLocalService.deleteActivities(group.getGroupId());
                        }

        }

        @Reference
        ClassNameLocalService _classNameLocalService;

        @Reference
        DDMTemplateLocalService _ddmTemplateLocalService;

        @Reference
        DDMStructureLocalService _ddmStructureLocalService;

        @Reference
        DDMStructureLayoutLocalService _ddmStructureLayoutLocalService;

        @Reference
        UserLocalService userLocalService;

        @Reference
        GroupLocalService _groupLocalService;
        @Reference
        SocialActivityLocalService _socialActivityLocalService;

}