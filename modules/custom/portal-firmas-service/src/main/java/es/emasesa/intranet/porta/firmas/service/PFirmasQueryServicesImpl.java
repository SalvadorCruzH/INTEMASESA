package es.emasesa.intranet.porta.firmas.service;

import es.emasesa.intranet.porta.firmas.service.impl.PFirmasQueryServices;
import java.io.File;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.bind.JAXBElement;
import juntadeandalucia.cice.pfirma.query.v2.PfirmaException;
import juntadeandalucia.cice.pfirma.query.v2.QueryService;
import juntadeandalucia.cice.pfirma.query.v2.QueryServiceService;
import juntadeandalucia.cice.pfirma.type.v2.Action;
import juntadeandalucia.cice.pfirma.type.v2.ActionList;
import juntadeandalucia.cice.pfirma.type.v2.Document;
import juntadeandalucia.cice.pfirma.type.v2.DocumentList;
import juntadeandalucia.cice.pfirma.type.v2.DocumentType;
import juntadeandalucia.cice.pfirma.type.v2.DocumentTypeList;
import juntadeandalucia.cice.pfirma.type.v2.ObjectFactory;
import juntadeandalucia.cice.pfirma.type.v2.RemitterList;
import juntadeandalucia.cice.pfirma.type.v2.Request;
import juntadeandalucia.cice.pfirma.type.v2.SignLine;
import juntadeandalucia.cice.pfirma.type.v2.SignLineList;
import juntadeandalucia.cice.pfirma.type.v2.Signer;
import juntadeandalucia.cice.pfirma.type.v2.SignerList;
import juntadeandalucia.cice.pfirma.type.v2.State;
import juntadeandalucia.cice.pfirma.type.v2.User;
import juntadeandalucia.cice.pfirma.type.v2.UserList;
import org.apache.cxf.jaxws.spi.ProviderImpl;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import javax.xml.ws.spi.Provider;
@Component(immediate = true, property = {}, service = PFirmasQueryServices.class)
public class PFirmasQueryServicesImpl implements PFirmasQueryServices {

    private URL wsdlURL;
    /**
     * The query is a string of the nif document number.
     * @param query
     * @throws PfirmaException
     * @return User from service
     */
    public User queryUser(String query) throws PfirmaException {
        QueryService queryService = getPort();
        UserList userList = queryService.queryUsers(query);
        User user = userList.getUser().stream().findFirst().orElse(null);
        return user;
    }

    public void sendSignOneUser(User user, String docType, File file) throws PfirmaException {
        QueryService queryService = getPort();
        ObjectFactory objectFactory = getObjectFactory();
        RemitterList remitterList = new RemitterList();
        remitterList.getUser().add(user);

        Signer signer = new Signer();
        signer.setUserJob(user);
        SignerList signerList = new SignerList();
        signerList.getSigner().add(signer);

        SignLine signLine = new SignLine();
        signLine.setSignerList(signerList);
        SignLineList signLineList = new SignLineList();
        signLineList.getSignLine().add(signLine);

        DocumentTypeList docTypeList =
                queryService.queryDocumentTypes(docType);
        DocumentType documentType = docTypeList.getDocumentType().get(0);

        Document doc = new Document();

        doc.setSign(new JAXBElement<>(null, Boolean.class, true));
        doc.setDocumentType(new JAXBElement<>(null, DocumentType.class, documentType));
        doc.setMime("text/plain");
        doc.setName(file.getName());
        DataSource ds = new FileDataSource(file);
        DataHandler dh = new DataHandler(ds);
        doc.setContent(new JAXBElement<>(null, DataHandler.class, dh));
        DocumentList docList = new DocumentList();
        docList.getDocument().add(doc);
        Action action = new Action();
        action.setType(objectFactory.createRequestText("WEB"));
        /**
         * TODO: URL de web que será invocada al ejecutar la acción de la petición, meter en sytem settings
         *
         */
        action.setAction("");
        State stateRead = new State();
        stateRead.setIdentifier("LEIDO");
        action.setState(stateRead);
        ActionList actionList = new ActionList();
        actionList.getAction().add(action);

        Request req = new Request();
        //TODO: ¿A que application va? Meter en system settings en caso de ser fijo
        req.setApplication(objectFactory.createRequestApplication(""));
        req.setDocumentList(objectFactory.createRequestDocumentList(docList));
        req.setReference(objectFactory.createRequestReference("Referencia"));
        req.setRemitterList(objectFactory.createRequestRemitterList(remitterList));
        req.setSignLineList(objectFactory.createRequestSignLineList(signLineList));
        req.setSignType(objectFactory.createRequestSignType("Primer Firmante"));
        req.setSubject(objectFactory.createRequestSubject("Asunto de la petición"));
        req.setText(objectFactory.createRequestText("Texto de la petición"));
        req.setActionList(objectFactory.createRequestActionList(actionList));


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

        this.wsdlURL = getClass().getClassLoader().getResource("META-INF/wsdl/QueryService.wsdl");
        this.queryServiceService = new QueryServiceService(wsdlURL);
    }

    public QueryService getPort() {

        QueryService port = queryServiceService.getQueryServicePort();
        return port;
    }

    private QueryServiceService queryServiceService;
    private QueryService _queryService;
}
