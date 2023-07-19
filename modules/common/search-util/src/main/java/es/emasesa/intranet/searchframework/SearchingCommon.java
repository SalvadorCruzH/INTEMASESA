package es.emasesa.intranet.searchframework;

import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = { },
		service = SearchingCommon.class
)
public class SearchingCommon {

	public static final String REGULAR = "regular";
	public static final String PAGINATION_TYPE = "paginationType";


	/** Common Methods **/
	public BooleanQuery createEmptyBooleanQuery() {
		return new BooleanQueryImpl();
	}

	public void setUpRegularPagination(final SearchContext searchContext, final int start, final int end){
		searchContext.setAttribute(PAGINATION_TYPE, REGULAR);
		searchContext.setStart(start);
		searchContext.setEnd(end);
	}

	public SearchContext createEmptySearchContext() {
		final SearchContext searchContext = new SearchContext();
		final QueryConfig queryConfig = searchContext.getQueryConfig();
		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);
		return searchContext;
	}

	public SearchContext createPaginatedSearchContext(final int start, final int end) {
		final SearchContext searchContext = new SearchContext();
		final QueryConfig queryConfig = searchContext.getQueryConfig();
		queryConfig.setHighlightEnabled(false);
		queryConfig.setScoreEnabled(false);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAttribute(PAGINATION_TYPE, REGULAR);
		return searchContext;
	}

	/** boolean clauses, create more if needed **/
	public void setMustBooleanClauses(final SearchContext searchContext, final Query query){
		BooleanClause<Query> booleanClause = BooleanClauseFactoryUtil.create(query, BooleanClauseOccur.MUST.getName());
		searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});
	}

	/** filters **/
	public void addCompanyIdFilter(final SearchContext searchContext, final long companyId)  {
		searchContext.setCompanyId(companyId);
	}

	public void addGroupIdFilter(ThemeDisplay themeDisplay, boolean useGroupId, boolean includeSubsiteContent, SearchContext searchContext, BooleanQuery booleanQuery) throws ParseException {
		// filter by curSite
		if (useGroupId) {
			if (includeSubsiteContent) {
				// filter by curSite + child Sites
				booleanQuery.addTerm("customParentGroupIds", String.valueOf(themeDisplay.getScopeGroupId()), Boolean.FALSE, BooleanClauseOccur.MUST);
			} else {
				// filter only by curSite
				long[] groupIds = {themeDisplay.getScopeGroupId()};
				searchContext.setGroupIds(groupIds);
			}
		}
	}
	
	public void addGroupIdFilter(final SearchContext searchContext, final long groupId)  {
		long[] groupIds = {groupId};
		searchContext.setGroupIds(groupIds);
	}

	public BooleanClause getMustBooleanClause(final BooleanQuery booleanQuery){
		return BooleanClauseFactoryUtil.create(booleanQuery, BooleanClauseOccur.MUST.getName());
	}

}
