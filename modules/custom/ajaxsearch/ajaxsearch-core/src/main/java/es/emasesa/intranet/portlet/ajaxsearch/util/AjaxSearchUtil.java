package es.emasesa.intranet.portlet.ajaxsearch.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.publisher.util.AssetPublisherHelper;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Node;
import es.emasesa.intranet.base.util.CustomPropertiesUtil;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.base.CustomAjaxSearchRegister;
import es.emasesa.intranet.portlet.ajaxsearch.constant.AjaxSearchPortletKeys;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = { },
        service = AjaxSearchUtil.class
)
public class AjaxSearchUtil {

    /**********/
    /** FORM **/
    /**********/
    public final AjaxSearchForm getFormImpl(final PortletRequest request, final CustomAjaxSearchRegister _customAjaxSearchRegister) {
        final String currentCustomLoginImplId = PrefsParamUtil.getString(
                request.getPreferences(),
                request,
                AjaxSearchPortletKeys.PORTLET_PREFS_SEARCH_IMPL,
                StringPool.BLANK);

        return _customAjaxSearchRegister.getForm(currentCustomLoginImplId);
    }

    public final Map<String, String> getPropertiesMap(final PortletRequest portletRequest) {
        return _customPropertiesUtil.getFromCachedPortletPreference(portletRequest, AjaxSearchPortletKeys.PORTLET_CURRENT_CONFIG);
    }

    public final JSONObject createJSONObject(final PortletParameters portletParameters){
        final JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put(AjaxSearchPortletKeys.PARAM_ACTION_PARAMS, portletParameters);
        return jsonObject;
    }

    public final void addSearchActionAttributes(final ActionRequest actionRequest) {
        final PortletSession portletSession = actionRequest.getPortletSession();
        portletSession.setAttribute(
                AjaxSearchPortletKeys.SEARCH_RENDER_PARAM_NAME,
                JSONFactoryUtil.createJSONObject().put(StringPool.CONTENT, actionRequest.getActionParameters()),
                PortletSession.APPLICATION_SCOPE);
    }

    /*************/
    /** RESULTS **/
    /*************/
    public final AjaxSearchResult getResultImpl(final PortletRequest request, final CustomAjaxSearchRegister _customAjaxSearchRegister) {
        final String currentCustomLoginImplId = PrefsParamUtil.getString(
                request.getPreferences(),
                request,
                AjaxSearchPortletKeys.PORTLET_PREFS_SEARCH_IMPL,
                StringPool.BLANK);

        return _customAjaxSearchRegister.getResult(currentCustomLoginImplId);
    }

    public final void updateBaseMutableRenderParameters(final MutableRenderParameters mutableRenderParameters){
        mutableRenderParameters.setValue("","");
    }

    public final PortletParameters getSearchActionAttributes(PortletRequest request, final boolean delete) {
        final PortletSession portletSession = request.getPortletSession();
        final JSONObject jsonObject = (JSONObject) portletSession.getAttribute(
                AjaxSearchPortletKeys.SEARCH_RENDER_PARAM_NAME,
                PortletSession.APPLICATION_SCOPE);
        final PortletParameters originalPp = (Validator.isNull(jsonObject)?null:(PortletParameters) jsonObject.get(StringPool.CONTENT));
        final PortletParameters pp;

        if (Validator.isNotNull(originalPp)) {
            pp = originalPp;
            if (delete){
                portletSession.removeAttribute(
                        AjaxSearchPortletKeys.SEARCH_RENDER_PARAM_NAME,
                        PortletSession.APPLICATION_SCOPE);
            }
        } else {
            pp = request.getRenderParameters();
        }

        return pp;
    }


    /*********************/
    /** GETTER / SETTER **/
    /*********************/

    /** G/S _ ACTION PARAMETERS **/
    public final ActionParameters getOriginalActionParameters(final JSONObject jsonObject){
        return (ActionParameters) jsonObject.get(AjaxSearchPortletKeys.PARAM_ORIGINAL_JSON_OBJECT);
    }

    public final boolean hasOriginalActionParameters(final JSONObject jsonObject){
        return jsonObject.has(AjaxSearchPortletKeys.PARAM_ACTION_PARAMS);
    }


    /*********************************/
    /** COMMON COMMANDS UTILITIES  **/
    /********************************/

    /** Find a item **/
	public String findAnItem(String findSingleItem, ResourceRequest resourceRequest, ThemeDisplay themeDisplay ) throws ParseException {

        final JSONObject jsonObject = _jsonFactory.createJSONObject();
		
    	String itemValue = ParamUtil.get(resourceRequest, "itemValue", StringPool.BLANK);
    	
    	// Do search
		JournalArticle ja = null;
		if("uuid".equals(itemValue)) {
			ja = JournalArticleLocalServiceUtil.fetchJournalArticleByUuidAndGroupId(findSingleItem, themeDisplay.getScopeGroupId());
		} else {
			ja = JournalArticleLocalServiceUtil.fetchLatestArticle(Long.valueOf(findSingleItem) ,WorkflowConstants.STATUS_APPROVED);
		}
		
		Locale locale = themeDisplay.getLocale();
		if (Validator.isNotNull(ja)) {
			String title =  ja.getTitle(locale);
			if(!Validator.isBlank(title)) {
				jsonObject.put("id", findSingleItem).put("text", title);
			}
		}
		
		return jsonObject.toString();
	}

    /**
     * Auxiliar method to get xml document field
     * @param document com.liferay.portal.kernel.xml.Document
     * @param fieldName String
     * @return String value (blank if not found)
     */
    public String getField(com.liferay.portal.kernel.xml.Document document, String fieldName) {

        if (document != null && !fieldName.isEmpty()) {
            Node node = document.selectSingleNode(AjaxSearchPortletKeys.XML_READ_INIT + fieldName + AjaxSearchPortletKeys.XML_READ_END);
            if (node != null && node.getText().length() > 0) {
                return node.getText();
            }
        }
        return "";
    }

    /**
     * Auxiliar method to get xml document field
     * @param document com.liferay.portal.kernel.xml.Document
     * @param fieldName String
     * @return String value (blank if not found)
     */
    public int getFieldCount(com.liferay.portal.kernel.xml.Document document, String fieldName) {

        if (document != null && !fieldName.isEmpty()) {
            List<Node> nodes = document.selectNodes(AjaxSearchPortletKeys.XML_READ_INIT + fieldName + AjaxSearchPortletKeys.XML_READ_END);
            final Integer count = countNodes(nodes);
            if (count != null) {
                return count;
            }
        }
        return 0;
    }

    public Integer countNodes(List<Node> nodes) {
        if (nodes != null) {
            int count = 0;
            for (Node node : nodes) {
                if(!Validator.isBlank(node.getStringValue())) {
                    count++;
                }
            }
            return count;
        }
        return null;
    }

    /** Extract Journal link with plid **/
    public String extractJournalLink(LiferayPortletRequest request, LiferayPortletResponse response, ThemeDisplay themeDisplay, JournalArticle article, String friendlyURL) {
        try {
            final AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
                    JournalArticle.class.getName(),
                    article.getResourcePrimKey());
            friendlyURL = assetPublisherHelper.getAssetViewURL(request, response, assetEntry, Boolean.TRUE);

            if(friendlyURL.contains("?")) {
                friendlyURL = friendlyURL + "&pageFromPlid=" + themeDisplay.getPlid();
            } else {
                friendlyURL = friendlyURL + "?pageFromPlid=" + themeDisplay.getPlid();
            }
        } catch (Exception e) {
            LOG.info("Error at get article friendly URL: " + e.getLocalizedMessage());
        }
        return friendlyURL;
    }

    public final ObjectEntry getObject(Long objectClassPK) {
        try {
            ObjectEntry objetoEntry = objectEntryLocalService.getObjectEntry(objectClassPK);
            return objetoEntry;
        } catch (PortalException e) {
            LoggerUtil.error(LOG, "Error at getObject: ", e);
            return null;
        }
    }

    public final String formatViewUrl(String entryClassPK, String entryType, String display, String baseUrl) throws JSONException {
        display += "?objectEntryId=" + entryClassPK;
        display += "&objectType=" + entryType;
        display += "&mode=1";
        display += "&p_p_state=pop_up";
        return baseUrl + display;
    }

    public final String formatEditUrl(String entryClassPK, String entryType, String display, String baseUrl) {
        display += "?objectEntryId=" + entryClassPK;
        display += "&objectType=" + entryType;
        display += "&mode=1";
        display += "&p_p_state=pop_up";
        return baseUrl + display;
    }

    public final String formatDeleteUrl(String portalUrl, String externalReferenceCode, String groupId, String objectContextPath) {
        return portalUrl + objectContextPath + "/scopes/" + groupId + "/by-external-reference-code/" + externalReferenceCode;
    }
    /**
     * Formatea la fecha de la solicitud
     *
     * @param date con formato YYYYMMDD....
     * @return String con la fecha formateada DD/MM/YYYY
     */
    public final String formatDate(String date) {
        if (!date.isBlank() && !date.equals(StringPool.DASH)) {
            return date.substring(6, 8) + StringPool.SLASH + date.substring(4, 6) + StringPool.SLASH + date.substring(0, 4);
        } else {
            return StringPool.DASH;
        }
    }

    @Reference
    ObjectEntryLocalService objectEntryLocalService;

    @Reference
    CustomPropertiesUtil _customPropertiesUtil;
    
	@Reference
	JSONFactory _jsonFactory;

    @Reference
    AssetEntryLocalService assetEntryLocalService;

    @Reference
    AssetPublisherHelper assetPublisherHelper;

    @Reference
    Portal portal;

    private final static Log LOG = LoggerUtil.getLog(AjaxSearchUtil.class);


}
