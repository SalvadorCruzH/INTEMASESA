package es.emasesa.intranet.base.util;

import java.io.Serializable;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.constant.StringConstants;

@Component(
        immediate = true,
        service = CustomDocSizeUtil.class
)
public class CustomDocSizeUtil {

    private static final String DOC_FILENAME_FTL= "/doc/docSize.ftl";

    public String getDocSize(final String title, final String url, final ThemeDisplay themeDisplay) {
        String docSize;
        final String template = GetterUtil.getString(customCacheSingleUtil.get(getCacheName(url, title)), StringPool.BLANK);

        if (!Validator.isBlank(template)){
            docSize = template;
        } else {
            final String[] datosDoc = url.split(StringPool.FORWARD_SLASH);
            try {
                final long groupId = Long.parseLong(datosDoc[2]);
                final String uuid = datosDoc[5].substring(0, datosDoc[5].indexOf(StringPool.QUESTION));
                final DLFileEntry file = dlFileEntryLocalService.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);
                if (Validator.isNotNull(file)) {
                    final Map<String, Object> properties = new HashMap<>();
                    properties.put("themeDisplay", themeDisplay);
                    properties.put("docFileSize", language.formatStorageSize(file.getSize(), themeDisplay.getLocale()));
                    properties.put("docFileUrl", url);
                    properties.put("docFileExtension", file.getExtension().toLowerCase());
                    if (Validator.isBlank(title)){
                        if(Validator.isBlank(file.getDescription())){
                            properties.put("docFileName",file.getTitle());
                        }else{
                            properties.put("docFileName", file.getDescription());
                        }
                    }else {
                        properties.put("docFileName", title);
                    }
                    properties.put("docFileExtensionSrOnly",
                            language.get(themeDisplay.getLocale(), "es.emasesa.intranet.common.template.documents.filesize.sronly-description"));
                    properties.put("request", themeDisplay.getRequest());
                    docSize=processTemplate(properties);
                    customCacheSingleUtil.put(getCacheName(url, title), docSize, CustomTemplateCacheSingleUtil.TTL_10_MIN);
                } else {
                    docSize=StringPool.BLANK;
                }
            } catch (Exception e) {
                LOG.error("Error al obtener el documento con "+url ,e);
                docSize=StringPool.BLANK;
            }
        }

        return docSize;
    }

    public String getDocSize(final String url, final ThemeDisplay themeDisplay) {
        return getDocSize(StringPool.BLANK, url, themeDisplay);
    }


    private String getCacheName(final String url, final String title){
        return "f"+url+title;
    }

    private String processTemplate(final Map<String, Object> properties) {
        String body = StringPool.BLANK;
        try {
            final URL templateUrl = CustomDocSizeUtil.class
                    .getClassLoader()
                    .getResource(DOC_FILENAME_FTL);
            final Template bodyTemplate =
                    TemplateManagerUtil.getTemplate(
                            TemplateConstants.LANG_TYPE_FTL,
                            new URLTemplateResource(StringConstants.ONE, templateUrl),
                            Boolean.TRUE);
            properties.forEach(bodyTemplate::put);
            final StringWriter bodyOut = new StringWriter();
            bodyTemplate.processTemplate(bodyOut);
            body = bodyOut.toString();
        } catch (Exception e){
            LoggerUtil.error(LOG, e);
        }
        return body;
    }
    
    public String getJustDocSize(final String url, final ThemeDisplay themeDisplay) {
        String docSize;
        final String template = GetterUtil.getString(customCacheSingleUtil.get(getJustSizeCacheName(url)), StringPool.BLANK);

        if (!Validator.isBlank(template)){
            docSize = template;
        } else {
            final String[] datosDoc = url.split(StringPool.FORWARD_SLASH);
            try {
                final long groupId = Long.parseLong(datosDoc[2]);
                final String uuid = datosDoc[5].substring(0, datosDoc[5].indexOf(StringPool.QUESTION));
                final DLFileEntry file = dlFileEntryLocalService.fetchDLFileEntryByUuidAndGroupId(uuid, groupId);
                if (Validator.isNotNull(file)){
                    docSize=language.formatStorageSize(file.getSize(), themeDisplay.getLocale());
                    customCacheSingleUtil.put(getJustSizeCacheName(url), docSize, CustomTemplateCacheSingleUtil.TTL_10_MIN);
                } else {
                    docSize=StringPool.BLANK;
                }
            } catch (Exception e) {
                LOG.error("Error al obtener el documento con "+url ,e);
                docSize=StringPool.BLANK;
            }
        }

        return docSize;
    }
    
    private String getJustSizeCacheName(final String url){
        return "justSizef"+url;
    }

    @Reference
    Portal portal;

    @Reference
    DLFileEntryLocalService dlFileEntryLocalService;

    @Reference
    Language language;

    private static final Log LOG = LoggerUtil.getLog(CustomDocSizeUtil.class);

    @Reference
    CustomTemplateCacheSingleUtil customCacheSingleUtil;
}