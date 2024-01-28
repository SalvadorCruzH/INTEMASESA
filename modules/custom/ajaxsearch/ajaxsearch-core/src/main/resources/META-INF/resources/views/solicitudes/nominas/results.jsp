<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div id="as-total-items">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper ema-documentos-ajax ema-ajaxsearch">
            <div class="ema-table__wrapper">
                <table class="ema-table__ajaxsearch">
                    <thead>
                     <tr>
                      <th><liferay-ui:message key="es.emasesa.intranet.nominas.periodo" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.nominas.provisional" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.nominas.definitiva" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.nominas.ultimoRecalculo" /></th>
                      <th><liferay-ui:message key="es.emasesa.intranet.nominas.fechaRecalculo" /></th>
                     </tr>
                    </thead>
                    <tbody id="as-wrapper">
                        <!-- -->
                    </tbody>
                </table>
            </div>
<button class="btn btn-primary">
						<div id="descargarButton" class="ema-download-all-button">


            </div>
</button>
            <div id="wrapper-not-result" class="d-none">
                <liferay-ui:message key="no-results" />
            </div>
        </div>

    </div>
</div>

<template id="as-total-items-template" class="d-none">
    <div class="m-ajaxresults-header-element">
        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.found-sing" /> #total-items# <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.document" />
    </div>

    <div class="m-ajaxresults-header-elements">
        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.found-plur" /> #total-items# <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents" />
    </div>

    <div class="d-none"> #items-page# documentos por página</div>
</template>


<template id="as-template">
    <tr>
        <td>#fechaNomina#</td>
        <td>#urlNominaProvisional#</td>
        <td>#urlNominaDefinitiva#</td>
        <td>#urlUltimoRecalculo#</td>
        <td>#fechaRecalculo#</td>
    </tr>
</template>



<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function (newItem, jsonItem) {return newItem},
    _postdrawItem : function (jsonItem) {},
    _predrawAll : function (payload) {},
    _postdrawAll : function (payload) {
        // Select the div element

        var myDiv = document.getElementById('descargarButton');
        // Append innerHTML to the div
        myDiv.innerHTML = payload.content[0].descargaUrl.urlDescarga;

        addClickFunctionality();

    }
}
$(document).ready(function () {
    var options = $(".results-pagination-select-container .results-pagination-select option");
    for (var i = 0; i < options.length; i++) {
        var urlParams = new URLSearchParams(window.location.search);
        var curPage = urlParams.get('currentPage');
        if (curPage == null) {
            curPage = 1;
        }
        var value = options[i].value;
        if (value == curPage) {
            options[i].selected = true;
        }
    }
    $(document).mouseup(function(e) {
        var container = $(".ema-desplegable-moreoptions");
        if (!container.is(e.target) && container.has(e.target).length === 0) {
            container.removeClass('show');
        }
    });


});

var addClickFunctionality = function () {
    var moreOptionsTd = $('.ema-td-dropdown');
    moreOptionsTd.each(function() {
        $(this).children('.ema-button-moreoptions').on('click', function () {
            $(this).siblings('.ema-desplegable-moreoptions').toggleClass('show');
        });
    });
}

var checkStatus = function () {
    $('tbody#as-wrapper tr').each(function() {
        var estado = $(this).find('.ema-pill-estado');
        if(estado.hasClass("success")) {
            $(this).find(".ema-button-moreoptions").remove();
            $(this).find(".ema-desplegable-moreoptions").remove();
        } else if (estado.hasClass("danger") ) {
            $(this).find(".ema-button-moreoptions").remove();
            $(this).find(".ema-desplegable-moreoptions").remove();
        } else {
            $(this).find('.ema-enlace-visualizar').remove();
        }
    });
}


var openEditDialog = function (url) {
    Liferay.Util.openWindow({
        dialog: {
            destroyOnHide: true,
            modal: true,
            after: {
                render: function(event) {
                    //
                }
            }
        },
        id: 'EditInfoIntDialog',
        refreshWindow: window,
        title: 'Consultar',
        uri: url
    });
}
</script>