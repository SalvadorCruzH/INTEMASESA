package es.emasesa.intranet.porta.firmas.service.util;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.constant.EmasesaConstants;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.porta.firmas.service.model.PFirmasModifyServices;
import es.emasesa.intranet.service.util.CustomWorkflowUtil;
import es.emasesa.intranet.settings.osgi.PortalFirmasServicesSettings;
import juntadeandalucia.cice.pfirma.modify.v2.PfirmaException;

@Component(immediate = true, property = {}, service = PortalFirmasUtil.class)
public class PortalFirmasUtil {

    public void enviarPortalFirmas(Map<String, Serializable> workflowContext, KaleoTaskInstanceToken kaleoTaskInstanceToken, long userId, String userType) {
    	
    	ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
        	LoggerUtil.debug(LOG, "Se procede a enviar el documento al portafirmas.");
            ClassLoader objectFactoryClassLoader = PortalFirmasUtil.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            
            if (ServiceContextThreadLocal.getServiceContext() != null) {
                userId = ServiceContextThreadLocal.getServiceContext().getUserId();
            }
            LoggerUtil.debug(LOG, "userId: " + userId);
            
            String subject; // NOMBRE OBJETO + NOMBRE USUARIO
            String reference; // NOMBRE OBJETO
            String documentName; // NOMBRE DOCUMENTO SIGD
            List<String> nifs = new ArrayList<String>(); // NIFs de los firmantes - Obtener quien es el consejero delegado con el servicio SAP de estructura recoger positionId, y obtener el dni de Liferay o del servicio de datos
            String remitterNIF = StringPool.BLANK; // NIF de la persona que manda al portafirmas
            String workflowTaskId = StringPool.BLANK;
            long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));

            if (workflowContext.size() > 0) {
                long classPK = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
                ObjectEntry objectEntry = _objectEntryLocalService.fetchObjectEntry(classPK);
                Map<String, Serializable> values = objectEntry.getValues();

                LoggerUtil.debug(LOG, "Obteniendo los valores del workflowContext y kaleoInstanceToken...");
                subject = workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE) + StringPool.BLANK + values.get(EmasesaConstants.NUMERO_MATRICULA);
                reference = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE));
                documentName = GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_TYPE)) +
                        GetterUtil.getString(workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
                remitterNIF = _expandoValueLocalService.getValue(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, EmasesaConstants.EMASESA_EXPANDO_NIF, userId).getString();
                nifs = getNifPortaFirmasUser(workflowContext, userType);

                if (Validator.isNotNull(kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId())) {
                    workflowTaskId = String.valueOf(kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
                }

                LoggerUtil.debug(LOG, "subject: " + subject);
                LoggerUtil.debug(LOG, "reference: " + reference);
                LoggerUtil.debug(LOG, "documentName: " + documentName);
                LoggerUtil.debug(LOG, "nifs: " + nifs.toString());
                LoggerUtil.debug(LOG, "remitterNIF: " + remitterNIF);
                LoggerUtil.debug(LOG, "workflowTaskId: " + workflowTaskId);
                JSONArray documentos = JSONFactoryUtil.createJSONArray((String) objectEntry.getValues().get(EmasesaConstants.EMASESA_OBJECT_SIGD_IDS));
                _pFirmasModifyServices.sendSign(subject, reference,
                        documentos, nifs, remitterNIF, workflowTaskId);
                LoggerUtil.debug(LOG, "Docuemnto enviado a portal firmas.");
            }
        } catch (JSONException | PfirmaException e) {
            LoggerUtil.error(LOG, "Error al enviar a portal firmas: ", e);
        } catch (PortalException e) {
            LoggerUtil.error(LOG, "Error al obtener el usaurio que envia al portal firmas: ", e);
        }finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }

    /**
     * Obtain NIF del usuario que envia al portafirmas
     *
     * @param workflowContext
     * @param userType
     * @return nifs
     */
    private List<String> getNifPortaFirmasUser(Map<String, Serializable> workflowContext, String userType) {
        List<String> nifs = new ArrayList<>();

        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            LoggerUtil.debug(LOG, "Obteniendo el NIF del : " + userType);
            ClassLoader objectFactoryClassLoader = PortalFirmasUtil.class.getClassLoader();
            Thread.currentThread().setContextClassLoader(objectFactoryClassLoader);
            User user = _customWorkflowUtil.getUserSap(workflowContext, userType);
            String nombreCampoExpando = EmasesaConstants.EMASESA_EXPANDO_NIF;
            LoggerUtil.debug(LOG, "nombre campo expando: " + nombreCampoExpando);
            long companyId = GetterUtil.getLong((String) workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));

            if (Validator.isNotNull(user)) {
                LoggerUtil.debug(LOG, "Se obtiene un usuario en liferay con el name = " + user.getScreenName());
                String nifUser = _expandoValueLocalService.getValue(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, nombreCampoExpando, user.getUserId()).getString();
                LoggerUtil.debug(LOG, "Se obtiene el NIF del usuario  " + nifUser);
                nifs.add(nifUser);
            } else {
                LoggerUtil.debug(LOG, "No se ha encontrado ningun usuario en liferay con el name = " + user.getScreenName());
            }
        } catch (PortalException e) {
            LoggerUtil.error(LOG, "Error al recuperar el NIF del usuario para el envío de portafirmas: ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
        return nifs;
    }

    @Reference
    PFirmasModifyServices _pFirmasModifyServices;
    @Reference
    ObjectEntryLocalService _objectEntryLocalService;
    @Reference
    UserLocalService _userLocalService;
    @Reference
    ExpandoValueLocalService _expandoValueLocalService;
    @Reference
    PortalFirmasServicesSettings _portalFirmasServicesSettings;
    @Reference
    CustomWorkflowUtil _customWorkflowUtil;

    private static final Log LOG = LogFactoryUtil.getLog(PortalFirmasUtil.class);

}
