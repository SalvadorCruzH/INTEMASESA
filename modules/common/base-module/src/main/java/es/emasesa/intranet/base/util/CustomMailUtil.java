package es.emasesa.intranet.base.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import es.emasesa.intranet.base.constant.StringConstants;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        service = CustomMailUtil.class
)
public class CustomMailUtil {

    private static final Log _log = LogFactoryUtil.getLog(CustomMailUtil.class);
    private static final String TEMPLATE_MAILS_FOLDER = "/mail-templates/";

    public String processTemplate(final Map<String, Object> properties,
                                  final String nameTemplate) {
        String body = StringPool.BLANK;
        try {
            final URL templateUrl = CustomMailUtil.class.getClassLoader().getResource(TEMPLATE_MAILS_FOLDER + nameTemplate);

            if (templateUrl != null) {
                final TemplateResource bodyTemplateResource = new URLTemplateResource(StringConstants.ZERO, templateUrl);
                final Template bodyTemplate =
                        TemplateManagerUtil.getTemplate(TemplateConstants.LANG_TYPE_FTL,
                        bodyTemplateResource,
                        Boolean.FALSE);
                properties.forEach(bodyTemplate::put);
                final StringWriter bodyOut = new StringWriter();
                bodyTemplate.processTemplate(bodyOut);
                body = bodyOut.toString();
            }
        } catch (Exception e){
            LoggerUtil.error(_log, e);
        }
        return body;
    }


    public String cleanWYSIWYG(final String portalUrl, final String wysiwyg) {
        return StringUtil.replace(wysiwyg, "/documents/", portalUrl+"/documents/");
    }

    public Map<String, Object> createContext(final String portalUrl, final Locale locale){
        final Map<String, Object> mailContext = new HashMap<>();
        mailContext.put("locale", locale);
        mailContext.put("portalUrl", portalUrl);
        return mailContext;
    }

    public boolean createAndSendMail(final String from,
                                     final String to,
                                     final String subject,
                                     final String body) {
        return createAndSendMail(from, to, subject, body, null, StringPool.BLANK);
    }

    public boolean createAndSendMail(final String from, final String to, final String subject,
                                         final String body, final File file, final String nameFile) {
        boolean createAndSendMail=Boolean.FALSE;
        try {
            final MailMessage mail = new MailMessage();
            mail.setHTMLFormat(Boolean.TRUE);
            mail.setFrom(new InternetAddress(from));
            mail.setTo(new InternetAddress(to));
            mail.setSubject(subject);
            mail.setBody(body);

            if (Validator.isNotNull(file) && !Validator.isBlank(nameFile)) {
                mail.addFileAttachment(customFileUtil.generateTemporalFile(file, nameFile));
            }
            _mailService.sendEmail(mail);
            createAndSendMail=Boolean.TRUE;
        } catch (Exception e){
            LoggerUtil.error(_log, e.getMessage());

        }
        return createAndSendMail;
    }

    public boolean createAndSendMail(final InternetAddress from,
                                     final InternetAddress[] to,
                                     final InternetAddress[] cc,
                                     final InternetAddress[] cco,
                                     final String subject,
                                     final String body) {

        boolean createAndSendMail=Boolean.FALSE;
        try {
            final MailMessage mail = new MailMessage();
            mail.setHTMLFormat(Boolean.TRUE);
            mail.setFrom(from);

            if (Validator.isNotNull(to)) {
                mail.setTo(to);
            }

            if (Validator.isNotNull(cc)) {
                mail.setCC(cc);
            }

            if (Validator.isNotNull(cco)) {
                mail.setCC(cco);
            }

            mail.setSubject(subject);
            mail.setBody(body);

            _mailService.sendEmail(mail);
            createAndSendMail=Boolean.TRUE;
        } catch (Exception e){
            LoggerUtil.error(_log, e.getMessage());
        }
        return createAndSendMail;
    }

    public boolean createAndSendMail(final String from,
                                     final List<String> to,
                                     final List<String> cc,
                                     final List<String> cco,
                                     final String subject,
                                     final String body,
                                     final File file,
                                     final String nameFile,
                                     final boolean htmlFormat) {
        boolean createAndSendMail=Boolean.FALSE;

        try {
            final MailMessage mail = new MailMessage();
            mail.setHTMLFormat(htmlFormat);
            mail.setFrom(new InternetAddress(from));
            mail.setSubject(subject);
            mail.setBody(body);

           setMailDestinations(to, cc, cco, mail);

            if (file != null && nameFile != null && !nameFile.isEmpty()) {
                mail.addFileAttachment(customFileUtil.generateTemporalFile(file, nameFile));
            }
            _mailService.sendEmail(mail);
            createAndSendMail=Boolean.TRUE;
        } catch (Exception e){
            LoggerUtil.error(_log, e.getMessage());

        }
        return createAndSendMail;
    }
    
    private void setMailDestinations(
            final List<String> to,
            final List<String> cc,
            final List<String> cco,
            final MailMessage mail) throws AddressException {
    	 if (Validator.isNotNull(to) && to.size()>0) {
             final InternetAddress[] toAddresses = new InternetAddress[to.size()];
             for (int i = 0; i < toAddresses.length; i++) {
                 toAddresses[i] = new InternetAddress(to.get(i));
             }
             mail.setTo(toAddresses);
         }

         if (Validator.isNotNull(cc) && cc.size()>0) {
             final InternetAddress[] ccAddresses = new InternetAddress[cc.size()];
             for (int i = 0; i < ccAddresses.length; i++) {
                 ccAddresses[i] = new InternetAddress(cc.get(i));
             }
             mail.setCC(ccAddresses);
         }

         if (Validator.isNotNull(cco) && cco.size()>0){
             final InternetAddress[] ccoAddresses = new InternetAddress[cco.size()];
             for (int i=0;i<ccoAddresses.length;i++){
                 ccoAddresses[i] = new InternetAddress(cco.get(i));
             }
             mail.setBCC(ccoAddresses);
         }
    }

    public static String getTableRowParagraph(final String content, final String tdAlign, final String pStyle){
        final StringBuilder sb = new StringBuilder("");
        sb.append("<tr>");
        sb.append("<td align='>").append(tdAlign).append("'>");
        sb.append("<p style='>").append(pStyle).append("'>").append(content).append("</p>");
        sb.append("</td></tr>");
        return sb.toString();
    }

    public String parseImgsOnBodyToBase64(String body){
        Document doc = Jsoup.parse(body);

        List<String> imageUrls = extractImageUrls(doc);
        imageUrls = imageUrls.stream().map(CustomMailUtil::parseImgToBase64).collect(Collectors.toList());

        return replaceImageUrlsWithBase64(doc,imageUrls);
    }

    private static String parseImgToBase64(String imageUrl){
        String base64IMG = StringPool.BLANK;
        try {
            LoggerUtil.debug(_log, "Se inicia el proceso para convertir una Imagen a base64.");
            URL url = new URL(imageUrl);
            try (InputStream inputStream = url.openStream()) {
                byte[] bytes = inputStream.readAllBytes();
                base64IMG = Base64.encodeBase64String(bytes);

                LoggerUtil.debug(_log, "Convertida Imagen a base64: " + base64IMG);
            }

        } catch (IOException e) {
            LoggerUtil.error(_log, "Error al intentar convertir una Imagen a base64: " + e);
        }

        return base64IMG;
    }

    private List<String> extractImageUrls(Document doc) {
        List<String> imageUrls = new ArrayList<>();
        Elements imgElements = doc.select("img");

        for (Element imgElement : imgElements) {
            String imageUrl = imgElement.attr("src");
            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }

    private String replaceImageUrlsWithBase64(Document doc, List<String> base64Images) {
        Elements imgElements = doc.select("img");

        for (int i = 0; i < imgElements.size(); i++) {
            Element imgElement = imgElements.get(i);

            if (i < base64Images.size()) {
                String base64Image = base64Images.get(i);
                imgElement.attr("src", "data:image/jpeg;base64," + base64Image);
            } else {
                LoggerUtil.error(_log, "Hay mas imagenes en el html que en el listado de imagenes obtenidas");
            }
        }
        return doc.outerHtml();
    }

	@Reference
    MailService _mailService;

    @Reference
    CustomFileUtil customFileUtil;

    @Reference
    Portal portal;

}
