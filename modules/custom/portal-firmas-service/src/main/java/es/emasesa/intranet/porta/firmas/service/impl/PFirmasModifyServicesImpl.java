package es.emasesa.intranet.porta.firmas.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import es.emasesa.intranet.porta.firmas.service.model.PFirmasModifyServices;
import es.emasesa.intranet.settings.osgi.PortalFirmasServicesSettings;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.xml.ws.Holder;
import javax.xml.ws.spi.Provider;
import juntadeandalucia.cice.pfirma.modify.v2.ModifyService;
import juntadeandalucia.cice.pfirma.modify.v2.ModifyServiceService;
import juntadeandalucia.cice.pfirma.modify.v2.PfirmaException;
import juntadeandalucia.cice.pfirma.type.v2.Action;
import juntadeandalucia.cice.pfirma.type.v2.ActionList;
import juntadeandalucia.cice.pfirma.type.v2.Document;
import juntadeandalucia.cice.pfirma.type.v2.DocumentList;
import juntadeandalucia.cice.pfirma.type.v2.NoticeList;
import juntadeandalucia.cice.pfirma.type.v2.ObjectFactory;
import juntadeandalucia.cice.pfirma.type.v2.RemitterList;
import juntadeandalucia.cice.pfirma.type.v2.Request;
import juntadeandalucia.cice.pfirma.type.v2.SignLine;
import juntadeandalucia.cice.pfirma.type.v2.SignLineList;
import juntadeandalucia.cice.pfirma.type.v2.Signer;
import juntadeandalucia.cice.pfirma.type.v2.SignerList;
import juntadeandalucia.cice.pfirma.type.v2.State;
import juntadeandalucia.cice.pfirma.type.v2.User;
import juntadeandalucia.cice.pfirma.type.v2.UserJob;
import org.apache.cxf.jaxws.spi.ProviderImpl;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {}, service = PFirmasModifyServices.class)
public class PFirmasModifyServicesImpl implements PFirmasModifyServices {
    private static final Log LOG = LogFactoryUtil.getLog(PFirmasModifyServicesImpl.class);
    private URL wsdlURL;
    private static final String SIGNTYPE="cascada";
    private static final String PDE="PDE";
    private static final String SIGDV2="SIGDV2";
    private static final String[] STATES={"FIRMADO","DEVUELTO","RECHAZADO"};
    private static final String APPLICATION_PDF="application/pdf";

    private static final String TYPE_FIRMA = "FIRMA";
    private static final String TYPE_WEB = "WEB";
    private static final boolean SIGN = true;


    public void sendSign(String subject, String reference, JSONArray documentos, List<String> nifs,
                         String remitterNIF, String workflowTaskId) throws JSONException, PfirmaException {
        ModifyService modifyService = getPort();

        ObjectFactory objectFactory = getObjectFactory();
        Request request = objectFactory.createRequest();
        request.setSubject(objectFactory.createRequestSubject(subject));
        request.setReference(objectFactory.createRequestReference(reference));
        request.setSignType(objectFactory.createRequestSignType(SIGNTYPE));
        request.setApplication(objectFactory.createRequestApplication(PDE));

        DocumentList documents = objectFactory.createDocumentList();

        for(int i=0;i<documentos.length();i++){
            Document document = objectFactory.createDocument();
            document.setName(documentos.getJSONObject(i).getString("documentName"));
            document.setMime(APPLICATION_PDF);
            document.setUri(objectFactory.createDocumentUri(documentos.getJSONObject(i).getString("idSIGD")));
            document.setType(objectFactory.createDocumentType(SIGDV2));
            document.setSign(getObjectFactory().createDocumentSign(documentos.getJSONObject(i).getBoolean("sign")));
            documents.getDocument().add(document);
        }



        SignLineList signLineList= objectFactory.createSignLineList();
        SignLine signLine = objectFactory.createSignLine();
        signLine.setType(objectFactory.createSignLineType(TYPE_FIRMA));

        SignerList signerList = objectFactory.createSignerList();

        nifs.forEach(nif -> {
            Signer signer = objectFactory.createSigner();
            UserJob userJob = objectFactory.createJob();
            userJob.setIdentifier(nif);
            signer.setUserJob(userJob);
            signerList.getSigner().add(signer);
        });
        signLine.setSignerList(signerList);
        signLineList.getSignLine().add(signLine);


        RemitterList remitterList = objectFactory.createRemitterList();
        User user = objectFactory.createUser();
        user.setIdentifier(remitterNIF);
        remitterList.getUser().add(user);


        NoticeList noticeList = objectFactory.createNoticeList();
        ActionList actionList = objectFactory.createActionList();

        JSONObject actionsJson = JSONFactoryUtil.createJSONObject(_portalFirmasServicesSettings.actionsJSON());

        for (String state : STATES) {
            State stateObj = objectFactory.createState();
            Action action = objectFactory.createAction();
            stateObj.setIdentifier(state);
            action.setState(stateObj);
            action.setType(objectFactory.createActionType(TYPE_WEB));
            String urlCallback = actionsJson.getString(state);
            urlCallback.replaceAll("--workflowTaskId--",workflowTaskId);
            action.setAction(urlCallback);
            actionList.getAction().add(action);
            noticeList.getState().add(stateObj);
        }

        request.setDocumentList(getObjectFactory().createRequestDocumentList(documents));
        request.setSignLineList(getObjectFactory().createRequestSignLineList(signLineList));
        request.setRemitterList(getObjectFactory().createRequestRemitterList(remitterList));
        request.setActionList(getObjectFactory().createRequestActionList(actionList));
        request.setNoticeList(getObjectFactory().createRequestNoticeList(noticeList));

        String requestId = modifyService.createRequest(request);
        Holder<String> requestIdHolder = new Holder<>(requestId);
        modifyService.sendRequest(requestIdHolder);


    }

    private ObjectFactory getObjectFactory() {
        return new ObjectFactory();
    }

    @Activate
    @Modified
    public void activate(BundleContext bundleContext, Map<String, Object> properties) {

        ServiceReference<Provider> providerServiceReference = bundleContext.getServiceReference(Provider.class);
        if(providerServiceReference == null) {
            ProviderImpl providerImpl = new ProviderImpl();
            Dictionary<String, Object> providerProperties = new Hashtable<>();
            bundleContext.registerService(Provider.class, providerImpl, providerProperties);
       }

        this.wsdlURL = getClass().getClassLoader().getResource("META-INF/wsdl/ModifyService.wsdl");
        this._modifyServiceService = new ModifyServiceService(wsdlURL);
    }

    public ModifyService getPort() {

        ModifyService port = _modifyServiceService.getModifyServicePort();
        return port;
    }

    private ModifyServiceService _modifyServiceService;
    private ModifyService _modifyService;
    @Reference
    PortalFirmasServicesSettings _portalFirmasServicesSettings;
}
