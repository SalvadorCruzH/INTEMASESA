package es.emasesa.intranet.portlet.ajaxsearch.base;

import com.liferay.portal.kernel.log.Log;
import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchForm;
import es.emasesa.intranet.portlet.ajaxsearch.model.AjaxSearchResult;
import org.osgi.service.component.annotations.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component(
        immediate = true,
        property = { },
        service = CustomAjaxSearchRegister.class
)
public class CustomAjaxSearchRegister {

    // FORMS
    public AjaxSearchForm getForm(final String id) {
        return _customAjaxSearchForms.getOrDefault(id, null);
    }
    public Collection<AjaxSearchForm> getFormValues() {
        return _customAjaxSearchForms.values();
    }
    public Set<String> getFormKeys() {
        return _customAjaxSearchForms.keySet();
    }

    @Reference(
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY,
            unbind = "unregisterAjaxSearchForm"
    )
    protected synchronized void registerAjaxSearchForm(final AjaxSearchForm as) {
        LOG.info(("registering "+getClassName(as));
        _customAjaxSearchForms.put(getClassName(as), as);
    }

    protected synchronized void unregisterAjaxSearchForm(final AjaxSearchForm as) {
        LOG.info("unregistering "+getClassName(as));
        _customAjaxSearchForms.remove(getClassName(as));
    }

    // RESULTS
    public AjaxSearchResult getResult(final String id) {
        return _customAjaxSearchResults.getOrDefault(id, null);
    }
    public Collection<AjaxSearchResult> getResultValues() {
        return _customAjaxSearchResults.values();
    }
    public Set<String> getResultKeys() {
        return _customAjaxSearchForms.keySet();
    }

    @Reference(
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY,
            unbind = "unregisterAjaxSearchResult"
    )
    protected synchronized void registerAjaxSearchResult(final AjaxSearchResult as) {
        _customAjaxSearchResults.put(getClassName(as), as);
    }

    protected synchronized void unregisterAjaxSearchResult(final AjaxSearchResult as) {
        _customAjaxSearchResults.remove(getClassName(as));
    }

    private final static String getClassName(final AjaxSearchForm as){
        return as.getClass().getName();
    }
    private final static String getClassName(final AjaxSearchResult as){
        return as.getClass().getName();
    }
    private final static Log LOG = LoggerUtil.getLog(CustomAjaxSearchRegister.class);

    private final Map<String, AjaxSearchForm> _customAjaxSearchForms = new ConcurrentHashMap<>();
    private final Map<String, AjaxSearchResult> _customAjaxSearchResults = new ConcurrentHashMap<>();

}
