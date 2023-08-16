<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--form c-news-form">
    <select name="<portlet:namespace />area"
            type="text"
            value='<%=ajaxSearchDisplayContext.getLong("category") %>'
            data-as-id="catSelected"
            class="m-searchAjax__input select"
            id="category"
        >
            <option value="" ${ groupSelected == 0 ? "selected" : ""}>
                <liferay-ui:message key="es.camara.intranet.area" />
            </option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.getCategoryId()}" ${category.getCategoryId() == catSelected ? "selected" : ""}>
                    ${area.getName()}
                </option>
            </c:forEach>
    </select>
    <input name="<portlet:namespace />fechaDesde"
            type="date"
            value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaDesde")) %>'
            data-as-id="fechaDesde"
            class="m-searchAjax__input date"
            id="fechaDesde"
    />
    <input name="<portlet:namespace />fechaHasta"
            type="date"
            value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaHasta")) %>'
            data-as-id="fechaHasta"
            class="m-searchAjax__input date"
            id="fechaHasta"
    />
    <input name="<portlet:namespace />queryText"
            type="text"
            id="queryText"
            value="<%=HtmlUtil.escape(ajaxSearchDisplayContext.getQueryText()) %>"
            data-as-id="queryText"
            class="m-searchAjax__input text"
            placeholder='<liferay-ui:message key="search"></liferay-ui:message>'
    />
    <button type="button"
        class="btn btn-primary"
        aria-label='<liferay-ui:message key="search"></liferay-ui:message>'
        title='<liferay-ui:message key="search"></liferay-ui:message>'
        id="m-searchAjax__button">
        <liferay-ui:message key="search"></liferay-ui:message>
    </button>
    <button type="button"
            class="btn btn-secondary"
            aria-label='<liferay-ui:message key="clear"></liferay-ui:message>'
            title='<liferay-ui:message key="clear"></liferay-ui:message>'
            id="m-searchAjax__clean__button">
            <liferay-ui:message key="clear"></liferay-ui:message>
    </button>
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