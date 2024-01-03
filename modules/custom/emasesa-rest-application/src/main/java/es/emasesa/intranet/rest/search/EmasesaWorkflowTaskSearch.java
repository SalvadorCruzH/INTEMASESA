package es.emasesa.intranet.rest.search;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskCreateDateComparator;
import com.liferay.portal.workflow.kaleo.KaleoWorkflowModelConverter;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.util.comparator.KaleoTaskInstanceTokenOrderByComparator;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenQuery;
import es.emasesa.intranet.rest.application.RestEmasesaWorkflowApplication;
import es.emasesa.intranet.rest.constant.EmasesaKaleoTaskInstanceTokenField;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Map;
import java.util.Objects;

@Component(
        property = {
        },
        service = EmasesaWorkflowTaskSearch.class
)
public class EmasesaWorkflowTaskSearch {

    public JSONArray searchWorkflowTask(ServiceContext serviceContext, boolean andOperator, String[] assetTypes, Long[] assigneeClassPKs,
                                        boolean completed, int start, int end, boolean searchByUserRoles, String orderColumn, String orderType) throws PortalException, ParseException {


        Hits hits = _search(
                HashMapBuilder.<String, Serializable>put(
                        "kaleoTaskInstanceTokenQuery",
                        () -> {
                            KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
                                    new KaleoTaskInstanceTokenQuery(serviceContext);

                            kaleoTaskInstanceTokenQuery.setAndOperator(andOperator);
                            kaleoTaskInstanceTokenQuery.setAssetTitle(null);
                            kaleoTaskInstanceTokenQuery.setAssetTypes(assetTypes);
                            kaleoTaskInstanceTokenQuery.setAssetPrimaryKeys(
                                    null);
                            kaleoTaskInstanceTokenQuery.setAssigneeClassName(
                                    null);
                            kaleoTaskInstanceTokenQuery.setAssigneeClassPKs(
                                    assigneeClassPKs);
                            kaleoTaskInstanceTokenQuery.setCompleted(completed);
                            kaleoTaskInstanceTokenQuery.setDueDateGT(null);
                            kaleoTaskInstanceTokenQuery.setDueDateLT(null);
                            kaleoTaskInstanceTokenQuery.setEnd(end);
                            kaleoTaskInstanceTokenQuery.setKaleoDefinitionId(
                                    null);
                            kaleoTaskInstanceTokenQuery.setKaleoInstanceIds(
                                    null);
                            kaleoTaskInstanceTokenQuery.setOrderByComparator(
                                    null);
                            kaleoTaskInstanceTokenQuery.
                                    setSearchByActiveWorkflowHandlers(
                                            false);
                            kaleoTaskInstanceTokenQuery.setSearchByUserRoles(
                                    searchByUserRoles);
                            kaleoTaskInstanceTokenQuery.setStart(start);
                            kaleoTaskInstanceTokenQuery.setTaskNames(null);

                            return kaleoTaskInstanceTokenQuery;
                        }
                ).build(),
                start, end, orderColumn, orderType, serviceContext);

        //
        JSONArray jsonArrayResult = JSONFactoryUtil.createJSONArray();
        for (Document doc : hits.getDocs()) {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            //jsonObject.put("workflowTaskId", GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
            jsonObject.put("entryClassPK", GetterUtil.getLong(doc.get(Field.CLASS_PK)));
            jsonObject.put("fullName", GetterUtil.getString(doc.get("fullName")));
            jsonObject.put("matricula", GetterUtil.getString(doc.get("matricula")));
            jsonObject.put("entryType", GetterUtil.getString(doc.get("entryType")));
            jsonObject.put("taskName", GetterUtil.getString(doc.get("taskName")));
            jsonObject.put("createDate", doc.getDate("createDate"));
            jsonObject.put("assigneeUserId", GetterUtil.getString(doc.get("assigneeClassPKs")));
            jsonObject.put("workflowTaskId", GetterUtil.getLong(doc.get("kaleoTaskInstanceTokenId")));
            jsonObject.put("assigneePersonName", GetterUtil.getString(doc.get("assigneePersonName")));
            jsonArrayResult.put(jsonObject);
        }

        return jsonArrayResult;
    }

    public long searchWorkflowTaskCount(ServiceContext serviceContext, boolean andOperator, String[] assetTypes, Long[] assigneeClassPKs,
                                        boolean completed, int start, int end, boolean searchByUserRoles, String orderColumn, String orderType) throws PortalException, ParseException {


       return _searchCount(
                HashMapBuilder.<String, Serializable>put(
                        "kaleoTaskInstanceTokenQuery",
                        () -> {
                            KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery =
                                    new KaleoTaskInstanceTokenQuery(serviceContext);

                            kaleoTaskInstanceTokenQuery.setAndOperator(andOperator);
                            kaleoTaskInstanceTokenQuery.setAssetTitle(null);
                            kaleoTaskInstanceTokenQuery.setAssetTypes(assetTypes);
                            kaleoTaskInstanceTokenQuery.setAssetPrimaryKeys(
                                    null);
                            kaleoTaskInstanceTokenQuery.setAssigneeClassName(
                                    null);
                            kaleoTaskInstanceTokenQuery.setAssigneeClassPKs(
                                    assigneeClassPKs);
                            kaleoTaskInstanceTokenQuery.setCompleted(completed);
                            kaleoTaskInstanceTokenQuery.setDueDateGT(null);
                            kaleoTaskInstanceTokenQuery.setDueDateLT(null);
                            kaleoTaskInstanceTokenQuery.setEnd(end);
                            kaleoTaskInstanceTokenQuery.setKaleoDefinitionId(
                                    null);
                            kaleoTaskInstanceTokenQuery.setKaleoInstanceIds(
                                    null);
                            kaleoTaskInstanceTokenQuery.setOrderByComparator(
                                    null);
                            kaleoTaskInstanceTokenQuery.
                                    setSearchByActiveWorkflowHandlers(
                                            false);
                            kaleoTaskInstanceTokenQuery.setSearchByUserRoles(
                                    searchByUserRoles);
                            kaleoTaskInstanceTokenQuery.setStart(start);
                            kaleoTaskInstanceTokenQuery.setTaskNames(null);

                            return kaleoTaskInstanceTokenQuery;
                        }
                ).build(),
               QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderColumn, orderType, serviceContext);
    }


    private Hits _search(
            Map<String, Serializable> searchAttributes, int start, int end,
            String orderColumn, String orderType,
            ServiceContext serviceContext)
            throws PortalException {

        Indexer<KaleoTaskInstanceToken> indexer =
                IndexerRegistryUtil.getIndexer(
                        KaleoTaskInstanceToken.class.getName());


        return indexer.search(
                _buildSearchContext(
                        searchAttributes, start, end, orderColumn, orderType,
                        serviceContext));
    }

    private long _searchCount(
            Map<String, Serializable> searchAttributes, int start, int end,
            String orderColumn, String orderType,
            ServiceContext serviceContext)
            throws PortalException {

        Indexer<KaleoTaskInstanceToken> indexer =
                IndexerRegistryUtil.getIndexer(
                        KaleoTaskInstanceToken.class.getName());


        return indexer.searchCount(
                _buildSearchContext(
                        searchAttributes, start, end, orderColumn, orderType,
                        serviceContext));
    }

    private SearchContext _buildSearchContext(
            Map<String, Serializable> searchAttributes, int start, int end,
            String orderColumn, String orderType,
            ServiceContext serviceContext) {

        SearchContext searchContext = new SearchContext();

        searchContext.setAttributes(searchAttributes);
        searchContext.setCompanyId(serviceContext.getCompanyId());
        searchContext.setEnd(end);
        searchContext.setGroupIds(new long[]{-1L});
        searchContext.setStart(start);


        if (orderColumn != null && !orderColumn.equals("")) {
            if(orderColumn.equals("createDate_Number_sortable")){
                searchContext.setSorts(new Sort(orderColumn, Sort.LONG_TYPE, !orderType.equals("asc")));
            }else{
                searchContext.setSorts(new Sort(orderColumn, Sort.STRING_TYPE, !orderType.equals("asc")));
            }

        }
        searchContext.getQueryConfig().setSelectedFieldNames("assigneePersonName", "classPK", "kaleoTaskInstanceTokenId", "entryClassPK", "fullName", "matricula", "entryType", "matricula", "taskName", "createDate", "assigneeClassPKs");
        searchContext.setUserId(serviceContext.getUserId());

        return searchContext;
    }

    private static final Log LOG = LogFactoryUtil.getLog(EmasesaWorkflowTaskSearch.class);
}
