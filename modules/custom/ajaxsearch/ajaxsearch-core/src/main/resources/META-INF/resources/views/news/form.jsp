<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--form ema-noticias-form">
    <p class="ema-noticias-form__filtertext">
        <i class="fa-solid fa-filter fa-lg" aria-hidden="true"></i><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.filter-by"></liferay-ui:message>
    </p>
    <div class="ema-noticias-form__filters">
        <div class="ema-noticias-filtros__category">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.category"></liferay-ui:message></label>
            <select name="<portlet:namespace />area"
                    type="text"
                    value='<%=ajaxSearchDisplayContext.getLong("category") %>'
                    data-as-id="catSelected"
                    class="m-searchAjax__input select"
                    id="category"
                >
                    <option value="" ${ groupSelected == 0 ? "selected" : ""}>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.categorias" />
                    </option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.getCategoryId()}" ${category.getCategoryId() == catSelected ? "selected" : ""}>
                            ${category.getName()}
                        </option>
                    </c:forEach>
            </select>
        </div>
        <div class="ema-noticias-filtros__dates">
            <div class="ema-noticias-filtros__dateFrom">
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.from-date"></liferay-ui:message></label>
                <input name="<portlet:namespace />fechaDesde"
                        type="date"
                        value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaDesde")) %>'
                        data-as-id="fechaDesde"
                        class="m-searchAjax__input date"
                        id="fechaDesde"
                />
            </div>
            <div class="ema-noticias-filtros__dateTo">
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.to-date"></liferay-ui:message></label>
                <input name="<portlet:namespace />fechaHasta"
                        type="date"
                        value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaHasta")) %>'
                        data-as-id="fechaHasta"
                        class="m-searchAjax__input date"
                        id="fechaHasta"
                />
            </div>
        </div>
        <div class="ema-noticias-filtros__text">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.free-text"></liferay-ui:message></label>
            <input name="<portlet:namespace />queryText"
                    type="text"
                    id="queryText"
                    value="<%=HtmlUtil.escape(ajaxSearchDisplayContext.getQueryText()) %>"
                    data-as-id="queryText"
                    class="m-searchAjax__input text"
                    placeholder='<liferay-ui:message key="search"></liferay-ui:message>'
            />
        </div>
        <div class="ema-noticias-filtros__buttons">
            <button type="button"
                    class="btn btn-primary search"
                    aria-label='<liferay-ui:message key="search"></liferay-ui:message>'
                    title='<liferay-ui:message key="search"></liferay-ui:message>'
                    id="m-searchAjax__button">
                    <i class="fa-solid fa-magnifying-glass fa-xl" aria-hidden="true"></i>
            </button>
            <button type="button"
                class="btn btn-secondary clear"
                aria-label='<liferay-ui:message key="clear"></liferay-ui:message>'
                title='<liferay-ui:message key="clear"></liferay-ui:message>'
                id="m-searchAjax__clean__button">
                <liferay-ui:message key="clear"></liferay-ui:message>
            </button>
        </div>
    </div>
    
</div>


<script type="text/javascript">
    //
    window.getCurrentBaseURL = function(){
        let currentURL = window.location.href;
        if (currentURL.indexOf('?') > 0) {
            return currentURL.split('?')[0];
        }
        return currentURL;
    };
    // search
    $("#m-searchAjax__button").on("click", function (e){
        e.preventDefault();
        ajaxSearchFeature.doSearch();
    });

    $("#m-searchAjax__clean__button").on("click", function (e){
        $('#category').val('');
        $('#queryText').val('');
        $('#fechaDesde').val('');
        $('#fechaHasta').val('');
         ajaxSearchFeature.doSearch();
    });

    $("#m-searchAjax__button").on("keypress", function (e){
        if(e.which == 13) {
            event.preventDefault();
            ajaxSearchFeature.doSearch(true, false);
        }
    });

	$('input#fechaDesde').on('change', function() {
		$('input#fechaHasta').attr("min", $(this).val());
	});

	$('input#fechaHasta').on('change', function() {
		$('input#fechaDesde').attr("max", $(this).val());
	});

</script>