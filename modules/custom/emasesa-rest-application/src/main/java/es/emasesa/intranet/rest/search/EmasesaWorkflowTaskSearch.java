package es.emasesa.intranet.rest.search;

import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.ServiceContext;
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
import java.util.Map;
import java.util.Objects;

@Component(
        property = {
        },
        service = EmasesaWorkflowTaskSearch.class
)
public class EmasesaWorkflowTaskSearch {

    public void searchWorkflowTask (ServiceContext serviceContext, boolean andOperator, String[] assetTypes, Long[] assigneeClassPKs,
                                    boolean completed, int start, int end, boolean searchByUserRoles, OrderByComparator<WorkflowTask> orderByComparator) throws PortalException {

        if( orderByComparator == null){
            orderByComparator = new WorkflowTaskCreateDateComparator(
                    false,
                    "createDate ASC",
                    "createDate DESC",
                    new String[] {
                            "createDate"
                    });
        }
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
        start, end, KaleoTaskInstanceTokenOrderByComparator.
                        getOrderByComparator(
                                orderByComparator,
                                _kaleoWorkflowModelConverter), serviceContext);

        LOG.error(hits.getDocs());

    }


    private Hits _search(
            Map<String, Serializable> searchAttributes, int start, int end,
            OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
            ServiceContext serviceContext)
            throws PortalException {

        Indexer<KaleoTaskInstanceToken> indexer =
                IndexerRegistryUtil.getIndexer(
                        KaleoTaskInstanceToken.class.getName());

        return indexer.search(
                _buildSearchContext(
                        searchAttributes, start, end, orderByComparator,
                        serviceContext));
    }

    private SearchContext _buildSearchContext(
            Map<String, Serializable> searchAttributes, int start, int end,
            OrderByComparator<KaleoTaskInstanceToken> orderByComparator,
            ServiceContext serviceContext) {

        SearchContext searchContext = new SearchContext();

        searchContext.setAttributes(searchAttributes);
        searchContext.setCompanyId(serviceContext.getCompanyId());
        searchContext.setEnd(end);
        searchContext.setGroupIds(new long[] {-1L});
        searchContext.setStart(start);

        if (orderByComparator != null) {
            searchContext.setSorts(_getSortsFromComparator(orderByComparator));
        }

        searchContext.setUserId(serviceContext.getUserId());

        return searchContext;
    }

    private Sort[] _getSortsFromComparator(
            OrderByComparator<KaleoTaskInstanceToken> orderByComparator) {

        if (orderByComparator == null) {
            return null;
        }

        return TransformUtil.transform(
                orderByComparator.getOrderByFields(),
                orderByFieldName -> {
                    String fieldName = _fieldNameOrderByCols.getOrDefault(
                            orderByFieldName, orderByFieldName);

                    int sortType = _fieldNameSortTypes.getOrDefault(
                            fieldName, Sort.STRING_TYPE);

                    boolean ascending = orderByComparator.isAscending();

                    if (Objects.equals(
                            orderByFieldName, EmasesaKaleoTaskInstanceTokenField.COMPLETED)) {

                        ascending = true;
                    }

                    return new Sort(fieldName, sortType, !ascending);
                },
                Sort.class);
    }


    private static final Map<String, String> _fieldNameOrderByCols =
            HashMapBuilder.put(
                    Field.CREATE_DATE,
                    _getSortableFieldName(Field.CREATE_DATE, "Number")
            ).put(
                    Field.USER_ID, _getSortableFieldName(Field.USER_ID, "Number")
            ).put(
                    EmasesaKaleoTaskInstanceTokenField.COMPLETED,
                    _getSortableFieldName(
                            EmasesaKaleoTaskInstanceTokenField.COMPLETED, "String")
            ).put(
                    EmasesaKaleoTaskInstanceTokenField.COMPLETION_DATE,
                    _getSortableFieldName(
                            EmasesaKaleoTaskInstanceTokenField.COMPLETION_DATE, "Number")
            ).put(
                    EmasesaKaleoTaskInstanceTokenField.DUE_DATE,
                    _getSortableFieldName(
                            EmasesaKaleoTaskInstanceTokenField.DUE_DATE, "Number")
            ).put(
                    EmasesaKaleoTaskInstanceTokenField.KALEO_INSTANCE_ID,
                    _getSortableFieldName(
                            EmasesaKaleoTaskInstanceTokenField.KALEO_INSTANCE_ID, "Number")
            ).put(
                    EmasesaKaleoTaskInstanceTokenField.KALEO_TASK_ID,
                    _getSortableFieldName(
                            EmasesaKaleoTaskInstanceTokenField.KALEO_TASK_ID, "Number")
            ).put(
                    EmasesaKaleoTaskInstanceTokenField.KALEO_TASK_INSTANCE_TOKEN_ID,
                    _getSortableFieldName(
                            EmasesaKaleoTaskInstanceTokenField.KALEO_TASK_INSTANCE_TOKEN_ID,
                            "Number")
            ).put(
                    "modifiedDate", _getSortableFieldName(Field.MODIFIED_DATE, "Number")
            ).put(
                    "name",
                    _getSortableFieldName(
                            EmasesaKaleoTaskInstanceTokenField.TASK_NAME, "String")
            ).build();
    private static final Map<String, Integer> _fieldNameSortTypes =
            HashMapBuilder.put(
                    Field.CREATE_DATE, Sort.LONG_TYPE
            ).put(
                    Field.MODIFIED_DATE, Sort.LONG_TYPE
            ).put(
                    "completionDate", Sort.LONG_TYPE
            ).put(
                    "dueDate", Sort.LONG_TYPE
            ).build();

    private static String _getSortableFieldName(String name, String type) {
        return Field.getSortableFieldName(
                StringBundler.concat(name, StringPool.UNDERLINE, type));
    }

    @Reference
    private KaleoWorkflowModelConverter _kaleoWorkflowModelConverter;

    private static final Log LOG = LogFactoryUtil.getLog(EmasesaWorkflowTaskSearch.class);
}
