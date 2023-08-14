<%@ include file="init.jsp" %>

<portlet:resourceURL id="descargar_datos_directorios" var="descargar_datos_directoriosURL" />

<div class="">
    <div class="input-group search-bar-suggestions">
        <div class="input-group-item">
            <div class="input-group">
               <input name="<portlet:namespace />queryText"
                      id="queryText"
                      type="text"
                      value="<%=HtmlUtil.escape(ajaxSearchDisplayContext.getQueryText()) %>"
                      data-as-id="queryText"
                      class="form-control input-group-inset input-group-inset-after search-bar-keywords-input"
                      placeholder='<liferay-ui:message key="search"></liferay-ui:message>'
                   />
                <div class="input-group-inset-item input-group-inset-item-after">
                    <button class="btn btn-unstyled"
                            type="submit"
                            aria-label='Buscar'
                            id="d-searchAjax__button">
                        <svg class="lexicon-icon lexicon-icon-search" role="presentation">
                            <use xlink:href="https://preintranet.camara.es/o/camara-theme/images/clay/icons.svg#search"></use>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    </div>


    <div class="c-directory-filters">
        <div class="c-directory-filters-form">
            <p class="c-directory-filters-text"><liferay-ui:message key="es.camara.intranet.ajaxsearch.directorios.filtrar.por" /></p>
            <div class="form-group col-12 col-md-4">
                <select name="<portlet:namespace />cautonomasSelected"
                        type="text"
                        value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("cautonomasSelected")) %>'
                        data-as-id="cautonomasSelected"
                        class="form-control"
                        id="cautonomasSelected">
                    <option value="" ${"".equals(cautonomasSelected) ? "selected" : ""}>
                        <liferay-ui:message key="es.camara.intranet.ajaxsearch.directorios.select.any.comunidad" />
                    </option>
                    <c:forEach items="${cautonomas}" var="cautonoma">
                        <option value="${cautonoma.getCategoryId()}" data-ccaa-id="${cautonoma.getCategoryId()}" ${cautonoma.getCategoryId() == cautonomasSelected ? "selected" : ""}>
                            ${cautonoma.getName()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-12 col-md-4">
                <select name="<portlet:namespace />provinciaSelected"
                        type="text"
                        value='<%=HtmlUtil.escape(ajaxSearchDisplayContext.getStringRP("provinciaSelected")) %>'
                        data-as-id="provinciaSelected"
                        class="form-control"
                        id="provinciaSelected">
                    <option value="" ${"".equals(provinciaSelected) ? "selected" : ""}>
                        <liferay-ui:message key="es.camara.intranet.ajaxsearch.directorios.select.any.provincia" />
                    </option>
                    <c:forEach items="${provincias}" var="provincia">
                        <option value="${provincia.getCategoryId()}" data-ccaa-id="${provincia.getParentCategoryId()}" ${provincia.getCategoryId() == provinciaSelected ? "selected" : ""}>
                            ${provincia.getName()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="person-filters-links">
                <a href="javascript:descargarDirectorios();" class="downloadURL"><liferay-ui:message key="es.camara.intranet.ajaxsearch.directorios.exportar.directorio" /></a>
            </div>
        </div>
    </div>

    <div class="c-clean-search">
        <p class="c-clean-search__text">
            <liferay-ui:message key="es.camara.intranet.ajaxsearch.directorios.clean.search"></liferay-ui:message>
        </p>
        <span class="c-clean-search__icon"></span>
    </div>
</div>

<script type="text/javascript">

    window.getCurrentBaseURL = function(){
        let currentURL = window.location.href;
        if (currentURL.indexOf('?') > 0) {
            return currentURL.split('?')[0];
        }
        return currentURL;
    };

    $(".c-clean-search").on("click", function (e){
        $('#queryText').val('');
        $('#cautonomasSelected').val('');
        $('#provinciaSelected').val('');
        window.location.href = getCurrentBaseURL();
    });


    function reload() {
        $(".c-directory-wrapper").hide();
        $(".loading-animation").show();

        ajaxSearchFeature.doSearch();
        setTimeout(function() {
            $(".loading-animation").hide();
            $(".c-directory-wrapper").show();
            Liferay.Portlet.refresh('#p_p_id_es_camara_intranet_portlet_ajaxsearch_portlet_result_AjaxSearchResultsPortlet_INSTANCE_knct_');
        }, 1000);
    }

    $(document).on("change","#cautonomasSelected",function(){
        var ccaaId = $(this).find(":selected").data("ccaa-id");

        if(ccaaId){
           $("#provinciaSelected").find("option[data-ccaa-id='"+ccaaId+"']").show();
           $("#provinciaSelected").find(":not(option[data-ccaa-id='"+ccaaId+"'])").hide();
        }else{
           $("#provinciaSelected").find("option").show();
        }

        reload();

    })
    $(document).on("change","#provinciaSelected",function(){
        var ccaaId = $(this).find(":selected").data("ccaa-id");

        if(ccaaId){
            $("#cautonomasSelected").find("option[data-ccaa-id='"+ccaaId+"']").show();
            $("#cautonomasSelected").find(":not(option[data-ccaa-id='"+ccaaId+"'])").hide();
            $("#cautonomasSelected").val(ccaaId);
        }else{
            $("#cautonomasSelected").find("option").show();
        }

        reload();

    })


    $("#queryText").on("keypress", function(e) {
        if (e.which == 13) {
            event.preventDefault();
            var queryTextValue = $("#queryText").val();

            $(".loading-animation").show();

            ajaxSearchFeature.doSearch(true, false, function() {
                setTimeout(function() {
                    $(".loading-animation").hide();
                }, 1000);
            });
            Liferay.Portlet.refresh('#p_p_id_es_camara_intranet_portlet_ajaxsearch_portlet_result_AjaxSearchResultsPortlet_INSTANCE_knct_');
        }
    });

    $("#d-searchAjax__button").on("click", function(e) {
        event.preventDefault();

        $(".loading-animation").show();

        ajaxSearchFeature.doSearch(true, false, function() {
            setTimeout(function() {
                $(".loading-animation").hide();
            }, 1000);
        });
        Liferay.Portlet.refresh('#p_p_id_es_camara_intranet_portlet_ajaxsearch_portlet_result_AjaxSearchResultsPortlet_INSTANCE_knct_');
    });

    function descargarDirectorios() {
        var selectedDirectoriosIds = getSelectedDirectoriosIds();
        var url = "<%= descargar_datos_directoriosURL.toString() %>&<portlet:namespace/>directoriosId=" + encodeURIComponent(selectedDirectoriosIds.join(","));

        fetch(url, {
                method: "POST",
                credentials: "include"
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = url;
                } else {
                    console.log("error en el script de descarga de directorios");
                }
            })
            .catch(error => console.error(error));
    }

    function getSelectedDirectoriosIds() {
        var elementos = document.querySelectorAll('[id^="directory-detail-id-"]');
        var ids = [];

        elementos.forEach(function(elemento) {
            var id = elemento.id.replace('directory-detail-id-', '');
            ids.push(id);
        });

        return ids;
    }

</script>