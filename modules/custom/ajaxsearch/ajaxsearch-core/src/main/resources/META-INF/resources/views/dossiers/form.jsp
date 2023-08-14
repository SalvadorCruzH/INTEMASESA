<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--form c-dossiers-form">
    <select name="<portlet:namespace />tipoMedios"
            type="text"
            value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("tipoMedios")) %>'
            data-as-id="tipoMedios"
            class="m-searchAjax__input select"
            id="tipoMedios"
    >
        <option value="" ${"".equals(tipoMedios) ? "selected" : ""}>
            <liferay-ui:message key="es.camara.intranet.ajaxsearch.select.dosieres-any" />
        </option>
        <c:forEach items="${dossierNewsTypes}" var="curDossierNewsTypes">
            <option value="${curDossierNewsTypes}" ${curDossierNewsTypes.equals(tipoMedios) ? "selected" : ""}>
                <liferay-ui:message key="es.camara.intranet.ajaxsearch.select.dosieres-${curDossierNewsTypes}" />
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

    // search
    $("#m-searchAjax__button").on("click", function (e){
        e.preventDefault();
        ajaxSearchFeature.doSearch();
    });

    $("#m-searchAjax__button").on("keypress", function (e){
        if(e.which == 13) {
            event.preventDefault();
            ajaxSearchFeature.doSearch(true, false);
        }
    });
    $("#m-searchAjax__clean__button").on("click", function (e){
            $('#fechaDesde').val('');
            $('#fechaHasta').val('');
            $("#tipoMedios").val('');
           ajaxSearchFeature.doSearch(true, false,true);
        });

    $('input#fechaDesde').on('change', function() {
		$('input#fechaHasta').attr("min", $(this).val());
	});

	$('input#fechaHasta').on('change', function() {
		$('input#fechaDesde').attr("max", $(this).val());
	});
</script>