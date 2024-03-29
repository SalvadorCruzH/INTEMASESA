<%@ include file="init.jsp" %>

<liferay-portlet:resourceURL var="getSubCategoriasURL" id="/ajax/get-categorias"/>

<div class="m-searchAjax m-searchAjax--form ema-ajaxsearch-form">
    <button class="ema-ajaxsearch-form__filterbutton">
        <i class="fa-solid fa-filter fa-lg" aria-hidden="true"></i><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.filter-by"></liferay-ui:message><i class="fa-solid fa-chevron-down fa-2xs" aria-hidden="true"></i>
    </button>
    <div class="ema-ajaxsearch-form__filters" aria-hidden="false">
        <div class="ema-ajaxsearch-filtros__category" id="buscador-categoria-select">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.category"></liferay-ui:message></label>
            <select name="<portlet:namespace />category"
                    type="text"
                    value='<%=ajaxSearchDisplayContext.getLong("category") %>'
                    data-as-id="catSelected"
                    class="m-searchAjax__input select"
                    id="category"
                >
                    <option value="" ${ groupSelected == 0 ? "selected" : ""}>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.categorias" />
                    </option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.getCategoryId()}" ${category.getCategoryId() == catSelected ? "selected" : ""}>
                            ${category.getName()}
                        </option>
                    </c:forEach>
            </select>
        </div>
        <div class="ema-ajaxsearch-filtros__subcategory" id="buscador-subcategoria-select">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.subcategory"></liferay-ui:message></label>
            <select name="<portlet:namespace />subcategory"
                    type="text"
                    value='<%=ajaxSearchDisplayContext.getLong("subCategory") %>'
                    data-as-id="subCatSelected"
                    class="m-searchAjax__input select"
                    id="subCategory"
                >
                    <option value="" ${ groupSelected == 0 ? "selected" : ""}>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.subcategorias" />
                    </option>
                    <c:forEach items="${subcategories}" var="subcategory">
                        <option value="${subcategory.getCategoryId()}" ${subcategory.getCategoryId() == subCatSelected ? "selected" : ""}>
                            ${subcategory.getName()}
                        </option>
                    </c:forEach>
            </select>
        </div>
        <div class="ema-ajaxsearch-filtros__dates">
            <div class="ema-ajaxsearch-filtros__dateFrom">
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.from-date"></liferay-ui:message></label>
                <input name="<portlet:namespace />fechaDesde"
                        type="date"
                        value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaDesde")) %>'
                        data-as-id="fechaDesde"
                        class="m-searchAjax__input date"
                        id="fechaDesde"
                />
            </div>
            <div class="ema-ajaxsearch-filtros__dateTo">
                <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.news.to-date"></liferay-ui:message></label>
                <input name="<portlet:namespace />fechaHasta"
                        type="date"
                        value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaHasta")) %>'
                        data-as-id="fechaHasta"
                        class="m-searchAjax__input date"
                        id="fechaHasta"
                />
                <!--<duet-date-picker name="<portlet:namespace />fechaHasta"
                type="date"
                value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("fechaHasta")) %>'
                data-as-id="fechaHasta"
                class="m-searchAjax__input date"
                id="fechaHasta"></duet-date-picker>-->
            </div>
        </div>
        <div class="ema-ajaxsearch-filtros__buttons">
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
    <div class="ema-ajaxsearch-filtros__sortby--wrapper">
        <div class="ema-ajaxsearch-filtros__sortby">
            <label><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort-by"></liferay-ui:message></label>
            <select name="<portlet:namespace />sortby"
                    type="text"
                    value='<%=ajaxSearchDisplayContext.getLong("sortby") %>'
                    data-as-id="sortby"
                    class="m-searchAjax__input select"
                    id="sortby"
                >
                    <option value="name-asc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.name-asc" />
                    </option>
                    <option value="name-desc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.name-desc" />
                    </option>
                    <option value="date-asc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.date-asc" />
                    </option>
                    <option value="date-desc" >
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.sort.date-desc" />
                    </option>
            </select>
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
    
    $('select#sortby').on('change', function() {
        ajaxSearchFeature.doSearch(true, false);
    });

    //cuando todo se haya cargado
    $(document).ready(function() {
        if($('#subCategory option').length <= 1) {
            $('#subCategory').prop("disabled", "disabled");
        } else {
            $('#subCategory').prop("disabled", false);
        }

        $('#p_p_id<portlet:namespace/> #buscador-categoria-select select').change(function(){
            let categoriaSelected = $(this).children("option:selected").val();

            let subCategorias = $('#p_p_id<portlet:namespace/> #buscador-subcategoria-select select');
            
            if(categoriaSelected != '') {
                $.ajax({
                    url:"${getSubCategoriasURL}",
                    type:"POST",
                    dataType:"json",
                    data:{"<portlet:namespace/>categoryId":categoriaSelected},
                    cache: false,
                    success: function(subcats) {
                        
                        subCategorias.find('option')
                        .remove()
                        .end()
                        .append('<option value="" selected><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.subcategorias" /></option>')
                        .val('');
                        
                        if(subcats != null && subcats.length > 0) {
                            subcats.forEach( subcat => {
                                subCategorias.append('<option value="' + subcat.subCategoryId +'">' + subcat.titleCurrentValue +'</option>')
                            });
                            
                            subCategorias.prop("disabled", false);
                        } else {
                            subCategorias.prop("disabled", "disabled");
                        }
                        
                    },
                    error:function(data) {
                        console.log("ERROR");
                    }
                });
            } else {
                subCategorias.find('option')
                .remove()
                .end()
                .append('<option value=""><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.subcategorias" /></option>')
                .val('');
                
                subCategorias.prop("disabled", "disabled");
            }
        });
    })

</script>