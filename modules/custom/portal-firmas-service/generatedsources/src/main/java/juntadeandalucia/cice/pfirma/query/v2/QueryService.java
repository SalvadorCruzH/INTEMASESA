
package juntadeandalucia.cice.pfirma.query.v2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import juntadeandalucia.cice.pfirma.type.v2.CSVList;
import juntadeandalucia.cice.pfirma.type.v2.CommentList;
import juntadeandalucia.cice.pfirma.type.v2.DetailedJobList;
import juntadeandalucia.cice.pfirma.type.v2.Document;
import juntadeandalucia.cice.pfirma.type.v2.DocumentTypeList;
import juntadeandalucia.cice.pfirma.type.v2.HistoricList;
import juntadeandalucia.cice.pfirma.type.v2.JobList;
import juntadeandalucia.cice.pfirma.type.v2.Request;
import juntadeandalucia.cice.pfirma.type.v2.Sign;
import juntadeandalucia.cice.pfirma.type.v2.StateList;
import juntadeandalucia.cice.pfirma.type.v2.UserList;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "QueryService", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:v2.0")
@XmlSeeAlso({
    juntadeandalucia.cice.pfirma.query.request.v2.ObjectFactory.class,
    juntadeandalucia.cice.pfirma.type.v2.ObjectFactory.class
})
public interface QueryService {


    /**
     * 
     * @param documentId
     * @return
     *     returns byte[]
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "signBinary", targetNamespace = "")
    @RequestWrapper(localName = "downloadSign", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.DownloadSign")
    @ResponseWrapper(localName = "downloadSignResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.DownloadSignResponse")
    public byte[] downloadSign(
        @WebParam(name = "documentId", targetNamespace = "")
        String documentId)
        throws PfirmaException
    ;

    /**
     * 
     * @param query
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.UserList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "userList", targetNamespace = "")
    @RequestWrapper(localName = "queryUsers", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryUsers")
    @ResponseWrapper(localName = "queryUsersResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryUsersResponse")
    public UserList queryUsers(
        @WebParam(name = "query", targetNamespace = "")
        String query)
        throws PfirmaException
    ;

    /**
     * 
     * @param query
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.DocumentTypeList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "documentTypeList", targetNamespace = "")
    @RequestWrapper(localName = "queryDocumentTypes", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryDocumentTypes")
    @ResponseWrapper(localName = "queryDocumentTypesResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryDocumentTypesResponse")
    public DocumentTypeList queryDocumentTypes(
        @WebParam(name = "query", targetNamespace = "")
        String query)
        throws PfirmaException
    ;

    /**
     * 
     * @param documentId
     * @return
     *     returns byte[]
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "documentBinary", targetNamespace = "")
    @RequestWrapper(localName = "downloadDocument", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.DownloadDocument")
    @ResponseWrapper(localName = "downloadDocumentResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.DownloadDocumentResponse")
    public byte[] downloadDocument(
        @WebParam(name = "documentId", targetNamespace = "")
        String documentId)
        throws PfirmaException
    ;

    /**
     * 
     * @param query
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.StateList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "stateList", targetNamespace = "")
    @RequestWrapper(localName = "queryStates", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryStates")
    @ResponseWrapper(localName = "queryStatesResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryStatesResponse")
    public StateList queryStates(
        @WebParam(name = "query", targetNamespace = "")
        String query)
        throws PfirmaException
    ;

    /**
     * 
     * @param query
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.JobList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "jobList", targetNamespace = "")
    @RequestWrapper(localName = "queryJobs", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryJobs")
    @ResponseWrapper(localName = "queryJobsResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryJobsResponse")
    public JobList queryJobs(
        @WebParam(name = "query", targetNamespace = "")
        String query)
        throws PfirmaException
    ;

    /**
     * 
     * @param requestId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.Request
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "request", targetNamespace = "")
    @RequestWrapper(localName = "queryRequest", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryRequest")
    @ResponseWrapper(localName = "queryRequestResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryRequestResponse")
    public Request queryRequest(
        @WebParam(name = "requestId", targetNamespace = "")
        String requestId)
        throws PfirmaException
    ;

    /**
     * 
     * @param requestId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.CommentList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "commentList", targetNamespace = "")
    @RequestWrapper(localName = "queryComments", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryComments")
    @ResponseWrapper(localName = "queryCommentsResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryCommentsResponse")
    public CommentList queryComments(
        @WebParam(name = "requestId", targetNamespace = "")
        String requestId)
        throws PfirmaException
    ;

    /**
     * 
     * @param requestId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.HistoricList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "historicList", targetNamespace = "")
    @RequestWrapper(localName = "queryHistoric", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryHistoric")
    @ResponseWrapper(localName = "queryHistoricResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryHistoricResponse")
    public HistoricList queryHistoric(
        @WebParam(name = "requestId", targetNamespace = "")
        String requestId)
        throws PfirmaException
    ;

    /**
     * 
     * @param userIdentify
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.JobList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "jobList", targetNamespace = "")
    @RequestWrapper(localName = "queryJobsUser", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryJobsUser")
    @ResponseWrapper(localName = "queryJobsUserResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryJobsUserResponse")
    public JobList queryJobsUser(
        @WebParam(name = "userIdentify", targetNamespace = "")
        String userIdentify)
        throws PfirmaException
    ;

    /**
     * 
     * @param userIdentify
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.DetailedJobList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "detailedJobList", targetNamespace = "")
    @RequestWrapper(localName = "queryDetailedJobsUser", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryDetailedJobsUser")
    @ResponseWrapper(localName = "queryDetailedJobsUserResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryDetailedJobsUserResponse")
    public DetailedJobList queryDetailedJobsUser(
        @WebParam(name = "userIdentify", targetNamespace = "")
        String userIdentify)
        throws PfirmaException
    ;

    /**
     * 
     * @param jobIdentify
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.UserList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "userList", targetNamespace = "")
    @RequestWrapper(localName = "queryUsersJob", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryUsersJob")
    @ResponseWrapper(localName = "queryUsersJobResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryUsersJobResponse")
    public UserList queryUsersJob(
        @WebParam(name = "jobIdentify", targetNamespace = "")
        String jobIdentify)
        throws PfirmaException
    ;

    /**
     * 
     * @param query
     * @param appId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.UserList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "userList", targetNamespace = "")
    @RequestWrapper(localName = "queryUsersApp", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryUsersApp")
    @ResponseWrapper(localName = "queryUsersAppResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryUsersAppResponse")
    public UserList queryUsersApp(
        @WebParam(name = "query", targetNamespace = "")
        String query,
        @WebParam(name = "appId", targetNamespace = "")
        String appId)
        throws PfirmaException
    ;

    /**
     * 
     * @param query
     * @param appId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.JobList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "jobList", targetNamespace = "")
    @RequestWrapper(localName = "queryJobsApp", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryJobsApp")
    @ResponseWrapper(localName = "queryJobsAppResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryJobsAppResponse")
    public JobList queryJobsApp(
        @WebParam(name = "query", targetNamespace = "")
        String query,
        @WebParam(name = "appId", targetNamespace = "")
        String appId)
        throws PfirmaException
    ;

    /**
     * 
     * @param documentId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.Document
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "document", targetNamespace = "")
    @RequestWrapper(localName = "queryDocument", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryDocument")
    @ResponseWrapper(localName = "queryDocumentResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryDocumentResponse")
    public Document queryDocument(
        @WebParam(name = "documentId", targetNamespace = "")
        String documentId)
        throws PfirmaException
    ;

    /**
     * 
     * @param documentId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.Sign
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "sign", targetNamespace = "")
    @RequestWrapper(localName = "querySign", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QuerySign")
    @ResponseWrapper(localName = "querySignResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QuerySignResponse")
    public Sign querySign(
        @WebParam(name = "documentId", targetNamespace = "")
        String documentId)
        throws PfirmaException
    ;

    /**
     * 
     * @param documentId
     * @return
     *     returns java.lang.String
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "csv", targetNamespace = "")
    @RequestWrapper(localName = "getCSV", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.GetCSV")
    @ResponseWrapper(localName = "getCSVResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.GetCSVResponse")
    public String getCSV(
        @WebParam(name = "documentId", targetNamespace = "")
        String documentId)
        throws PfirmaException
    ;

    /**
     * 
     * @param appId
     * @return
     *     returns juntadeandalucia.cice.pfirma.type.v2.CSVList
     * @throws PfirmaException
     */
    @WebMethod
    @WebResult(name = "listCSVAvailable", targetNamespace = "")
    @RequestWrapper(localName = "queryCSVApp", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryCSVApp")
    @ResponseWrapper(localName = "queryCSVAppResponse", targetNamespace = "urn:juntadeandalucia:cice:pfirma:query:request:v2.0", className = "juntadeandalucia.cice.pfirma.query.request.v2.QueryCSVAppResponse")
    public CSVList queryCSVApp(
        @WebParam(name = "appId", targetNamespace = "")
        String appId)
        throws PfirmaException
    ;

}
