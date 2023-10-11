package es.emasesa.intranet.gestionBoletines.portlet;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import es.emasesa.intranet.base.util.CustomJournalUtil;
import es.emasesa.intranet.base.util.CustomMailUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.gestionBoletines.constants.GestionBoletinesPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jacarmonam
 */
@Component(
        property = {
                "com.liferay.portlet.add-default-resource=true",
                "com.liferay.portlet.display-category=category.hidden",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.footer-portlet-javascript=/js/main.js",
                "com.liferay.portlet.layout-cacheable=true",
                "com.liferay.portlet.private-request-attributes=false",
                "com.liferay.portlet.private-session-attributes=false",
                "com.liferay.portlet.render-weight=50",
                "com.liferay.portlet.use-default-template=true",
                "javax.portlet.display-name=ListasDistribucion",
                "javax.portlet.expiration-cache=0",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + GestionBoletinesPortletKeys.GESTONBOLETINES,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user",

        },
        service = Portlet.class
)
public class GestionBoletinesPortlet extends MVCPortlet {

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        PortletPreferences preferences = renderRequest.getPreferences();

        String destinatariosListaControlada = preferences.getValue(GestionBoletinesPortletKeys.CONFIG_DESTINATARIOS_CONTROLADA, StringPool.BLANK);
        String destinatariosListaDistribucion = preferences.getValue(GestionBoletinesPortletKeys.CONFIG_DESTINATARIOS_DISTRIBUIDA, StringPool.BLANK);
        String asuntoListaControlada = preferences.getValue(GestionBoletinesPortletKeys.CONFIG_ASUNTO_CONTROLADA, StringPool.BLANK);
        String asuntoListaDistribucion = preferences.getValue(GestionBoletinesPortletKeys.CONFIG_ASUNTO_DISTRIBUIDA, StringPool.BLANK);

        //Recuperacion de valores y envio a la jsp
        renderRequest.setAttribute(GestionBoletinesPortletKeys.INPUT_DESTINATARIOS_LISTA_CONTROLADA_VALUE, destinatariosListaControlada);
        renderRequest.setAttribute(GestionBoletinesPortletKeys.INPUT_DESTINATARIOS_LISTA_DISTRIBUCION_VALUE, destinatariosListaDistribucion);
        renderRequest.setAttribute(GestionBoletinesPortletKeys.INPUT_ASUNTO_LISTA_CONTROLADA_VALUE, asuntoListaControlada);
        renderRequest.setAttribute(GestionBoletinesPortletKeys.INPUT_ASUNTO_LISTA_DISTRIBUCION_VALUE, asuntoListaDistribucion);

        List<JournalArticle> boletines = getBoletines(themeDisplay.getScopeGroupId());
        renderRequest.setAttribute(GestionBoletinesPortletKeys.LISTADO_BOLETINES, boletines);

        super.render(renderRequest, renderResponse);
    }

    private List<JournalArticle> getBoletines(long groupIdArticles) {
        List<JournalArticle> latestArticleMap = new ArrayList<>();
        DDMStructure structure = null;
        List<JournalArticle> boletines = null;

        try {
            final Group group = _groupLocalService.getFriendlyURLGroup(_portal.getDefaultCompanyId(), GroupConstants.GLOBAL_FRIENDLY_URL);

            structure = _ddmStructureLocalService.getStructure(group.getGroupId(), _portal.getClassNameId(JournalArticle.class), GestionBoletinesPortletKeys.STRUCTURE_KEY);

        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
        }

        if (Validator.isNotNull(structure)) {
            boletines = _journalArticleLocalService.getStructureArticles(groupIdArticles, structure.getStructureId());
        }
        try {
            if (Validator.isNotNull(boletines)) {
                for (JournalArticle article : boletines) {
                    if (_journalArticleLocalService.isLatestVersion(article.getGroupId(), article.getArticleId(), article.getVersion())) {
                        latestArticleMap.add(article);
                    }
                }
            }
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
        }

        return latestArticleMap;
    }

    public void processActionGestionListas(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
        String tipoLista = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_TIPO_LISTA));
        String destinatarios;
        PortletPreferences preferences = actionRequest.getPreferences();
        if (tipoLista.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE)) { //Lista Controlada
            LoggerUtil.debug(LOG, "Entra en la condicion de Gestion de Lista Controlada");
            destinatarios = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_DESTINATARIOS_CONTROLADA));
            preferences.setValue(GestionBoletinesPortletKeys.CONFIG_DESTINATARIOS_CONTROLADA, destinatarios);
            preferences.store();
            SessionMessages.add(actionRequest, GestionBoletinesPortletKeys.MSG_OK_GUARDADO_DATOS_CONTROLADA);
        } else if (tipoLista.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_DISTRIBUCION_VALUE)) { //Lista Distribucion
            LoggerUtil.debug(LOG, "Entra en la condicion de Gestion de Lista Distribuida");
            destinatarios = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_DESTINATARIOS_DISTRIBUIDA));
            preferences.setValue(GestionBoletinesPortletKeys.CONFIG_DESTINATARIOS_DISTRIBUIDA, destinatarios);
            preferences.store();
            SessionMessages.add(actionRequest, GestionBoletinesPortletKeys.MSG_OK_GUARDADO_DATOS_DISTRIBUCION);
        } else { //Lista no valida
            LoggerUtil.debug(LOG, "Entra en la condicion de Gestion de Lista no valida");
            SessionErrors.add(actionRequest, GestionBoletinesPortletKeys.ERROR_CAMPO_TIPO_LISTA);
            LoggerUtil.error(LOG, "Se ha enviado un tipo de Lista no esperado: " + tipoLista);
        }

        actionResponse.getRenderParameters().setValue("tabSelected", GestionBoletinesPortletKeys.TIPO_VISTA_GESTION_BOLETIN);
    }

    public void processActionEnvioBoletin(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
        String tipoLista = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_TIPO_LISTA));
        PortletPreferences preferences = actionRequest.getPreferences();

        if (tipoLista.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_CONTROLADA_VALUE)) { //Boletin Lista Controlada
            LoggerUtil.debug(LOG, "Entra en la condicion de Envio de Lista Distribuida");
            String to = preferences.getValue(GestionBoletinesPortletKeys.CONFIG_DESTINATARIOS_CONTROLADA, StringPool.BLANK);
            String boletinId = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_BOLETIN_CONTROLADA));
            String asunto = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_ASUNTO_CONTROLADA));

            preferences.setValue(GestionBoletinesPortletKeys.CONFIG_ASUNTO_CONTROLADA, asunto);
            preferences.store();
            String contenidoProcesado = procesadoContenido(actionRequest, actionResponse, boletinId);
            boolean correoEnviado = gestionCorreo(actionRequest, to, asunto, contenidoProcesado);

            if (correoEnviado){
                SessionMessages.add(actionRequest, GestionBoletinesPortletKeys.MSG_OK_ENVIO_CORREO_CONTROLADA);
            } else {
                SessionErrors.add(actionRequest, GestionBoletinesPortletKeys.MSG_ERROR_ENVIO_CORREO_CONTROLADA);
                LoggerUtil.error(LOG, "No se ha enviado el correo");
            }
        } else if (tipoLista.equals(GestionBoletinesPortletKeys.INPUT_TIPO_LISTA_DISTRIBUCION_VALUE)) { //Boletin Lista Distribucion
            LoggerUtil.debug(LOG, "Entra en la condicion de Envio  de Lista Distribuida");
            String to = preferences.getValue(GestionBoletinesPortletKeys.CONFIG_DESTINATARIOS_DISTRIBUIDA, StringPool.BLANK);
            String boletinId = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_BOLETIN_DISTRIBUIDA));
            String asunto = HtmlUtil.escape(ParamUtil.getString(actionRequest, GestionBoletinesPortletKeys.INPUT_ASUNTO_DISTRIBUIDA));

            preferences.setValue(GestionBoletinesPortletKeys.CONFIG_ASUNTO_DISTRIBUIDA, asunto);
            preferences.store();
            String contenidoProcesado = procesadoContenido(actionRequest, actionResponse, boletinId);
            boolean correoEnviado = gestionCorreo(actionRequest, to, asunto, contenidoProcesado);

            if (correoEnviado){
                SessionMessages.add(actionRequest, GestionBoletinesPortletKeys.MSG_OK_ENVIO_CORREO_DISTRIBUCION);
            } else {
                SessionErrors.add(actionRequest, GestionBoletinesPortletKeys.MSG_ERROR_ENVIO_CORREO_DISTRIBUCION);
                LoggerUtil.error(LOG, "No se ha enviado el correo");
            }
        } else { //Lista no valida
            LoggerUtil.debug(LOG, "Entra en la condicion de Envio de Lista no valida");
            SessionErrors.add(actionRequest, GestionBoletinesPortletKeys.ERROR_CAMPO_TIPO_LISTA);
            LoggerUtil.error(LOG, "Se ha enviado un tipo de Envio de Lista no esperado: " + tipoLista);
        }
        actionResponse.getRenderParameters().setValue("tabSelected", GestionBoletinesPortletKeys.TIPO_VISTA_ENVIO_BOLETIN);
    }

    private String procesadoContenido(ActionRequest actionRequest, ActionResponse actionResponse, String boletinId) {
        String htmlContenido = null;
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        try {
            JournalArticle boletin = _journalArticleLocalService.getLatestArticle(themeDisplay.getScopeGroupId(), boletinId);
            if (Validator.isNotNull(boletin)) {
                htmlContenido = _customJournalUtil.getHTMLFromJournalArticle(
                        boletin,
                        themeDisplay,
                        actionRequest,
                        actionResponse,
                        "EMA-BOLETIN"
                );

                String portalUrl = PortalUtil.getPortalURL(actionRequest);
                htmlContenido = postProcesadoContenido(htmlContenido, portalUrl);
            }
        } catch (PortalException e) {
            LoggerUtil.error(LOG, e);
        }
            return htmlContenido;
    }

    private boolean gestionCorreo(ActionRequest actionRequest, String to, String asunto, String contenidoProcesado) {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String[] destinatarios = to.split(StringPool.SEMICOLON);
        List<String> listaDestinatarios = new ArrayList<>(Arrays.asList(destinatarios));
        List<String> listaDestinatariosVacia = new ArrayList<>();
        String remitente = PropsUtil.get("admin.email.from.address");
        String direccionRemitente = PrefsPropsUtil.getPreferences(themeDisplay.getCompanyId()).getValue("admin.email.from.address", remitente);

        return _customMailUtil.createAndSendMail(direccionRemitente, listaDestinatariosVacia,listaDestinatariosVacia,listaDestinatarios, asunto, contenidoProcesado, null, StringPool.BLANK, Boolean.TRUE);
    }

    private String postProcesadoContenido (String contenido, String portalUrl){
        Document document = Jsoup.parse(contenido);
        Elements imgElements = document.select("img");

        for (Element imgElement : imgElements) {
            String src = imgElement.attr("src");

            if (!src.startsWith("http") && !src.startsWith("https")) {
                String absoluteUrl = portalUrl + src;
                imgElement.attr("src", absoluteUrl);
            }
        }

        return document.outerHtml();
    }

    private final static Log LOG = LoggerUtil.getLog(GestionBoletinesPortlet.class);

    @Reference
    JournalArticleLocalService _journalArticleLocalService;

    @Reference
    DDMStructureLocalService _ddmStructureLocalService;

    @Reference
    GroupLocalService _groupLocalService;

    @Reference
    Portal _portal;

    @Reference
    CustomJournalUtil _customJournalUtil;

    @Reference
    CustomMailUtil _customMailUtil;
}