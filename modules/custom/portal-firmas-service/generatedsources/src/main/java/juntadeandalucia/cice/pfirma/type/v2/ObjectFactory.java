
package juntadeandalucia.cice.pfirma.type.v2;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the juntadeandalucia.cice.pfirma.type.v2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SignerFstate_QNAME = new QName("", "fstate");
    private final static QName _SignerState_QNAME = new QName("", "state");
    private final static QName _DocumentIdentifier_QNAME = new QName("", "identifier");
    private final static QName _DocumentDocumentType_QNAME = new QName("", "documentType");
    private final static QName _DocumentSign_QNAME = new QName("", "sign");
    private final static QName _DocumentType_QNAME = new QName("", "type");
    private final static QName _DocumentUri_QNAME = new QName("", "uri");
    private final static QName _DocumentContent_QNAME = new QName("", "content");
    private final static QName _RequestFexpiration_QNAME = new QName("", "fexpiration");
    private final static QName _RequestNoticeList_QNAME = new QName("", "noticeList");
    private final static QName _RequestFstart_QNAME = new QName("", "fstart");
    private final static QName _RequestSubject_QNAME = new QName("", "subject");
    private final static QName _RequestActionList_QNAME = new QName("", "actionList");
    private final static QName _RequestReference_QNAME = new QName("", "reference");
    private final static QName _RequestSignLineList_QNAME = new QName("", "signLineList");
    private final static QName _RequestApplication_QNAME = new QName("", "application");
    private final static QName _RequestDocumentList_QNAME = new QName("", "documentList");
    private final static QName _RequestRemitterList_QNAME = new QName("", "remitterList");
    private final static QName _RequestSignType_QNAME = new QName("", "signType");
    private final static QName _RequestParameterList_QNAME = new QName("", "parameterList");
    private final static QName _RequestText_QNAME = new QName("", "text");
    private final static QName _RequestFentry_QNAME = new QName("", "fentry");
    private final static QName _CommentFcomment_QNAME = new QName("", "fcomment");
    private final static QName _DetailedJobOrganism_QNAME = new QName("", "organism");
    private final static QName _DetailedJobEndDate_QNAME = new QName("", "endDate");
    private final static QName _DetailedJobInitDate_QNAME = new QName("", "initDate");
    private final static QName _DetailedJobDescription_QNAME = new QName("", "description");
    private final static QName _DetailedJobMain_QNAME = new QName("", "main");
    private final static QName _DetailedJobJob_QNAME = new QName("", "job");
    private final static QName _DetailedJobDepartament_QNAME = new QName("", "departament");
    private final static QName _DocumentTypeValid_QNAME = new QName("", "valid");
    private final static QName _UserSurname1_QNAME = new QName("", "surname1");
    private final static QName _UserSurname2_QNAME = new QName("", "surname2");
    private final static QName _UserName_QNAME = new QName("", "name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: juntadeandalucia.cice.pfirma.type.v2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExceptionInfo }
     * 
     */
    public ExceptionInfo createExceptionInfo() {
        return new ExceptionInfo();
    }

    /**
     * Create an instance of {@link CommentList }
     * 
     */
    public CommentList createCommentList() {
        return new CommentList();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link NoticeList }
     * 
     */
    public NoticeList createNoticeList() {
        return new NoticeList();
    }

    /**
     * Create an instance of {@link DocumentType }
     * 
     */
    public DocumentType createDocumentType() {
        return new DocumentType();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link DetailedJobList }
     * 
     */
    public DetailedJobList createDetailedJobList() {
        return new DetailedJobList();
    }

    /**
     * Create an instance of {@link Sign }
     * 
     */
    public Sign createSign() {
        return new Sign();
    }

    /**
     * Create an instance of {@link DocumentSign }
     * 
     */
    public DocumentSign createDocumentSign() {
        return new DocumentSign();
    }

    /**
     * Create an instance of {@link DocumentTypeList }
     * 
     */
    public DocumentTypeList createDocumentTypeList() {
        return new DocumentTypeList();
    }

    /**
     * Create an instance of {@link UserList }
     * 
     */
    public UserList createUserList() {
        return new UserList();
    }

    /**
     * Create an instance of {@link Parameter }
     * 
     */
    public Parameter createParameter() {
        return new Parameter();
    }

    /**
     * Create an instance of {@link Action }
     * 
     */
    public Action createAction() {
        return new Action();
    }

    /**
     * Create an instance of {@link UserEmail }
     * 
     */
    public UserEmail createUserEmail() {
        return new UserEmail();
    }

    /**
     * Create an instance of {@link State }
     * 
     */
    public State createState() {
        return new State();
    }

    /**
     * Create an instance of {@link SignLine }
     * 
     */
    public SignLine createSignLine() {
        return new SignLine();
    }

    /**
     * Create an instance of {@link Signer }
     * 
     */
    public Signer createSigner() {
        return new Signer();
    }

    /**
     * Create an instance of {@link RequestSign }
     * 
     */
    public RequestSign createRequestSign() {
        return new RequestSign();
    }

    /**
     * Create an instance of {@link Historic }
     * 
     */
    public Historic createHistoric() {
        return new Historic();
    }

    /**
     * Create an instance of {@link StateList }
     * 
     */
    public StateList createStateList() {
        return new StateList();
    }

    /**
     * Create an instance of {@link CSV }
     * 
     */
    public CSV createCSV() {
        return new CSV();
    }

    /**
     * Create an instance of {@link CSVList }
     * 
     */
    public CSVList createCSVList() {
        return new CSVList();
    }

    /**
     * Create an instance of {@link ActionList }
     * 
     */
    public ActionList createActionList() {
        return new ActionList();
    }

    /**
     * Create an instance of {@link DocumentSignList }
     * 
     */
    public DocumentSignList createDocumentSignList() {
        return new DocumentSignList();
    }

    /**
     * Create an instance of {@link SignLineList }
     * 
     */
    public SignLineList createSignLineList() {
        return new SignLineList();
    }

    /**
     * Create an instance of {@link SignerList }
     * 
     */
    public SignerList createSignerList() {
        return new SignerList();
    }

    /**
     * Create an instance of {@link DocumentList }
     * 
     */
    public DocumentList createDocumentList() {
        return new DocumentList();
    }

    /**
     * Create an instance of {@link DetailedJob }
     * 
     */
    public DetailedJob createDetailedJob() {
        return new DetailedJob();
    }

    /**
     * Create an instance of {@link RemitterList }
     * 
     */
    public RemitterList createRemitterList() {
        return new RemitterList();
    }

    /**
     * Create an instance of {@link HistoricList }
     * 
     */
    public HistoricList createHistoricList() {
        return new HistoricList();
    }

    /**
     * Create an instance of {@link ParameterList }
     * 
     */
    public ParameterList createParameterList() {
        return new ParameterList();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link Job }
     * 
     */
    public Job createJob() {
        return new Job();
    }

    /**
     * Create an instance of {@link JobList }
     * 
     */
    public JobList createJobList() {
        return new JobList();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fstate", scope = Signer.class)
    public JAXBElement<XMLGregorianCalendar> createSignerFstate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SignerFstate_QNAME, XMLGregorianCalendar.class, Signer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link State }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "state", scope = Signer.class)
    public JAXBElement<State> createSignerState(State value) {
        return new JAXBElement<State>(_SignerState_QNAME, State.class, Signer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "identifier", scope = Document.class)
    public JAXBElement<String> createDocumentIdentifier(String value) {
        return new JAXBElement<String>(_DocumentIdentifier_QNAME, String.class, Document.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "documentType", scope = Document.class)
    public JAXBElement<DocumentType> createDocumentDocumentType(DocumentType value) {
        return new JAXBElement<DocumentType>(_DocumentDocumentType_QNAME, DocumentType.class, Document.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sign", scope = Document.class)
    public JAXBElement<Boolean> createDocumentSign(Boolean value) {
        return new JAXBElement<Boolean>(_DocumentSign_QNAME, Boolean.class, Document.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type", scope = Document.class)
    public JAXBElement<String> createDocumentType(String value) {
        return new JAXBElement<String>(_DocumentType_QNAME, String.class, Document.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "uri", scope = Document.class)
    public JAXBElement<String> createDocumentUri(String value) {
        return new JAXBElement<String>(_DocumentUri_QNAME, String.class, Document.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataHandler }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "content", scope = Document.class)
    @XmlMimeType("application/octet-stream")
    public JAXBElement<DataHandler> createDocumentContent(DataHandler value) {
        return new JAXBElement<DataHandler>(_DocumentContent_QNAME, DataHandler.class, Document.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "identifier", scope = Request.class)
    public JAXBElement<String> createRequestIdentifier(String value) {
        return new JAXBElement<String>(_DocumentIdentifier_QNAME, String.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fexpiration", scope = Request.class)
    public JAXBElement<XMLGregorianCalendar> createRequestFexpiration(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RequestFexpiration_QNAME, XMLGregorianCalendar.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoticeList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "noticeList", scope = Request.class)
    public JAXBElement<NoticeList> createRequestNoticeList(NoticeList value) {
        return new JAXBElement<NoticeList>(_RequestNoticeList_QNAME, NoticeList.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fstart", scope = Request.class)
    public JAXBElement<XMLGregorianCalendar> createRequestFstart(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RequestFstart_QNAME, XMLGregorianCalendar.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "subject", scope = Request.class)
    public JAXBElement<String> createRequestSubject(String value) {
        return new JAXBElement<String>(_RequestSubject_QNAME, String.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "actionList", scope = Request.class)
    public JAXBElement<ActionList> createRequestActionList(ActionList value) {
        return new JAXBElement<ActionList>(_RequestActionList_QNAME, ActionList.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "reference", scope = Request.class)
    public JAXBElement<String> createRequestReference(String value) {
        return new JAXBElement<String>(_RequestReference_QNAME, String.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignLineList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "signLineList", scope = Request.class)
    public JAXBElement<SignLineList> createRequestSignLineList(SignLineList value) {
        return new JAXBElement<SignLineList>(_RequestSignLineList_QNAME, SignLineList.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "application", scope = Request.class)
    public JAXBElement<String> createRequestApplication(String value) {
        return new JAXBElement<String>(_RequestApplication_QNAME, String.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "documentList", scope = Request.class)
    public JAXBElement<DocumentList> createRequestDocumentList(DocumentList value) {
        return new JAXBElement<DocumentList>(_RequestDocumentList_QNAME, DocumentList.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemitterList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "remitterList", scope = Request.class)
    public JAXBElement<RemitterList> createRequestRemitterList(RemitterList value) {
        return new JAXBElement<RemitterList>(_RequestRemitterList_QNAME, RemitterList.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "signType", scope = Request.class)
    public JAXBElement<String> createRequestSignType(String value) {
        return new JAXBElement<String>(_RequestSignType_QNAME, String.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parameterList", scope = Request.class)
    public JAXBElement<ParameterList> createRequestParameterList(ParameterList value) {
        return new JAXBElement<ParameterList>(_RequestParameterList_QNAME, ParameterList.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "text", scope = Request.class)
    public JAXBElement<String> createRequestText(String value) {
        return new JAXBElement<String>(_RequestText_QNAME, String.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fentry", scope = Request.class)
    public JAXBElement<XMLGregorianCalendar> createRequestFentry(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RequestFentry_QNAME, XMLGregorianCalendar.class, Request.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fcomment", scope = Comment.class)
    public JAXBElement<XMLGregorianCalendar> createCommentFcomment(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CommentFcomment_QNAME, XMLGregorianCalendar.class, Comment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type", scope = Action.class)
    public JAXBElement<String> createActionType(String value) {
        return new JAXBElement<String>(_DocumentType_QNAME, String.class, Action.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "identifier", scope = RequestSign.class)
    public JAXBElement<String> createRequestSignIdentifier(String value) {
        return new JAXBElement<String>(_DocumentIdentifier_QNAME, String.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fexpiration", scope = RequestSign.class)
    public JAXBElement<XMLGregorianCalendar> createRequestSignFexpiration(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RequestFexpiration_QNAME, XMLGregorianCalendar.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoticeList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "noticeList", scope = RequestSign.class)
    public JAXBElement<NoticeList> createRequestSignNoticeList(NoticeList value) {
        return new JAXBElement<NoticeList>(_RequestNoticeList_QNAME, NoticeList.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fstart", scope = RequestSign.class)
    public JAXBElement<XMLGregorianCalendar> createRequestSignFstart(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RequestFstart_QNAME, XMLGregorianCalendar.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "subject", scope = RequestSign.class)
    public JAXBElement<String> createRequestSignSubject(String value) {
        return new JAXBElement<String>(_RequestSubject_QNAME, String.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "actionList", scope = RequestSign.class)
    public JAXBElement<ActionList> createRequestSignActionList(ActionList value) {
        return new JAXBElement<ActionList>(_RequestActionList_QNAME, ActionList.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "reference", scope = RequestSign.class)
    public JAXBElement<String> createRequestSignReference(String value) {
        return new JAXBElement<String>(_RequestReference_QNAME, String.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "application", scope = RequestSign.class)
    public JAXBElement<String> createRequestSignApplication(String value) {
        return new JAXBElement<String>(_RequestApplication_QNAME, String.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemitterList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "remitterList", scope = RequestSign.class)
    public JAXBElement<RemitterList> createRequestSignRemitterList(RemitterList value) {
        return new JAXBElement<RemitterList>(_RequestRemitterList_QNAME, RemitterList.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "signType", scope = RequestSign.class)
    public JAXBElement<String> createRequestSignSignType(String value) {
        return new JAXBElement<String>(_RequestSignType_QNAME, String.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parameterList", scope = RequestSign.class)
    public JAXBElement<ParameterList> createRequestSignParameterList(ParameterList value) {
        return new JAXBElement<ParameterList>(_RequestParameterList_QNAME, ParameterList.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "text", scope = RequestSign.class)
    public JAXBElement<String> createRequestSignText(String value) {
        return new JAXBElement<String>(_RequestText_QNAME, String.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fentry", scope = RequestSign.class)
    public JAXBElement<XMLGregorianCalendar> createRequestSignFentry(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RequestFentry_QNAME, XMLGregorianCalendar.class, RequestSign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type", scope = SignLine.class)
    public JAXBElement<String> createSignLineType(String value) {
        return new JAXBElement<String>(_DocumentType_QNAME, String.class, SignLine.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "identifier", scope = Sign.class)
    public JAXBElement<String> createSignIdentifier(String value) {
        return new JAXBElement<String>(_DocumentIdentifier_QNAME, String.class, Sign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type", scope = Sign.class)
    public JAXBElement<String> createSignType(String value) {
        return new JAXBElement<String>(_DocumentType_QNAME, String.class, Sign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "uri", scope = Sign.class)
    public JAXBElement<String> createSignUri(String value) {
        return new JAXBElement<String>(_DocumentUri_QNAME, String.class, Sign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataHandler }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "content", scope = Sign.class)
    @XmlMimeType("application/octet-stream")
    public JAXBElement<DataHandler> createSignContent(DataHandler value) {
        return new JAXBElement<DataHandler>(_DocumentContent_QNAME, DataHandler.class, Sign.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "organism", scope = DetailedJob.class)
    public JAXBElement<String> createDetailedJobOrganism(String value) {
        return new JAXBElement<String>(_DetailedJobOrganism_QNAME, String.class, DetailedJob.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "endDate", scope = DetailedJob.class)
    public JAXBElement<XMLGregorianCalendar> createDetailedJobEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DetailedJobEndDate_QNAME, XMLGregorianCalendar.class, DetailedJob.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "initDate", scope = DetailedJob.class)
    public JAXBElement<XMLGregorianCalendar> createDetailedJobInitDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DetailedJobInitDate_QNAME, XMLGregorianCalendar.class, DetailedJob.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description", scope = DetailedJob.class)
    public JAXBElement<String> createDetailedJobDescription(String value) {
        return new JAXBElement<String>(_DetailedJobDescription_QNAME, String.class, DetailedJob.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "main", scope = DetailedJob.class)
    public JAXBElement<Boolean> createDetailedJobMain(Boolean value) {
        return new JAXBElement<Boolean>(_DetailedJobMain_QNAME, Boolean.class, DetailedJob.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "job", scope = DetailedJob.class)
    public JAXBElement<String> createDetailedJobJob(String value) {
        return new JAXBElement<String>(_DetailedJobJob_QNAME, String.class, DetailedJob.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "departament", scope = DetailedJob.class)
    public JAXBElement<String> createDetailedJobDepartament(String value) {
        return new JAXBElement<String>(_DetailedJobDepartament_QNAME, String.class, DetailedJob.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description", scope = Job.class)
    public JAXBElement<String> createJobDescription(String value) {
        return new JAXBElement<String>(_DetailedJobDescription_QNAME, String.class, Job.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "valid", scope = DocumentType.class)
    public JAXBElement<Boolean> createDocumentTypeValid(Boolean value) {
        return new JAXBElement<Boolean>(_DocumentTypeValid_QNAME, Boolean.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "description", scope = DocumentType.class)
    public JAXBElement<String> createDocumentTypeDescription(String value) {
        return new JAXBElement<String>(_DetailedJobDescription_QNAME, String.class, DocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "surname1", scope = User.class)
    public JAXBElement<String> createUserSurname1(String value) {
        return new JAXBElement<String>(_UserSurname1_QNAME, String.class, User.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "surname2", scope = User.class)
    public JAXBElement<String> createUserSurname2(String value) {
        return new JAXBElement<String>(_UserSurname2_QNAME, String.class, User.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name", scope = User.class)
    public JAXBElement<String> createUserName(String value) {
        return new JAXBElement<String>(_UserName_QNAME, String.class, User.class, value);
    }

}
