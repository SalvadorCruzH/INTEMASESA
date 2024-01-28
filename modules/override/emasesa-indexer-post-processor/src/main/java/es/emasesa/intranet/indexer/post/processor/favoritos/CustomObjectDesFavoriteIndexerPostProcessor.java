package es.emasesa.intranet.indexer.post.processor.favoritos;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import es.emasesa.intranet.base.util.LoggerUtil;
import es.emasesa.intranet.indexer.post.processor.util.PostProcessorUtil;


@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.object.model.ObjectDefinition#209907", // TODO: alguna manera de dinamizarlo.
        service = IndexerPostProcessor.class
)
public class CustomObjectDesFavoriteIndexerPostProcessor implements IndexerPostProcessor{

    private static final Log _log = LoggerUtil.getLog(CustomObjectDesFavoriteIndexerPostProcessor.class);

    @Override
    public void postProcessContextBooleanFilter(BooleanFilter booleanFilter, SearchContext searchContext)
            throws Exception {

    }

    @Override
    public void postProcessDocument(Document document, Object object) throws Exception {
    	 _log.debug("PostProcessor entorno DES");
    	 _postProcessorUtil.postProcessDocument(document, object);
    }

    @Override
    public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchContext) throws Exception {
    }

    @Override
    public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter booleanFilter,
                                       SearchContext searchContext) throws Exception {
    }

    @Override
    public void postProcessSummary(Summary summary, Document document, Locale locale, String snippet) {
    }
    
    @Reference
    protected PostProcessorUtil _postProcessorUtil;

}
