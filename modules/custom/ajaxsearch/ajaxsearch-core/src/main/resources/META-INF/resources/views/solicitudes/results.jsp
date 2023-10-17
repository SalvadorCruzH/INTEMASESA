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
                      <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.name" /></th>
                      <th class=""><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.datefrom" /></th>
                      <th class=""><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.dateto" /></th>
                      <th class=""><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.dateobject" /></th>
                      <th class=""><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.status" /></th>
                      <th class=""></th>
                     </tr>
                    </thead>
                    <tbody id="as-wrapper">
                        <!-- -->
                    </tbody>
                </table>
            </div>
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
        <td>#nombreObjeto#</td>
        <td>#fechaDesde#</td>
        <td>#fechaHasta#</td>
        <td>#fechaSolicitud#</td>
        <td>#estado#</td>
        <td class="ema-td-dropdown">
            <button class="ema-button-moreoptions">
                <i class="fa-solid fa-ellipsis-vertical"></i>
            </button>
            <ul class="ema-desplegable-moreoptions">
                <li>
                    <a href="#urlEditar#">
                        <i class="fa-solid fa-edit"></i>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.edit" />
                    </a>
                </li>
                <li>
                    <a href="#urlBorrar#">
                        <i class="fa-solid fa-trash"></i>
                        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.delete" />
                    </a>
                </li>
            </ul>
        </td>
    </tr>
</template>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function (newItem, jsonItem) {return newItem},
    _postdrawItem : function (jsonItem) {},
    _predrawAll : function (payload) {},
    _postdrawAll : function (payload) {
        addClickFunctionality();
        checkStatus();
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
    console.log(moreOptionsTd)
    moreOptionsTd.each(function() {
        $(this).children('.ema-button-moreoptions').on('click', function () {
            $(this).siblings('.ema-desplegable-moreoptions').toggleClass('show');
        });
    });
}

var checkStatus = function () {
    $('.ema-ajaxsearch__item').each(function() {
        var estado = $(this).find('td:nth-child(5)').text();
        if (estado == 'Completado' || estado == 'Rechazado') {
            //TODO cambiar estado por estado final y terminar
            $(this).find('.ema-button-moreoptions').remove();
            $(this).find('.ema-desplegable-moreoptions').remove();
            $(this).find('td:nth-child(6)').append('<a class="ema-button-visualize" href="#urlVisualizar#"><i class="fa-solid fa-eye"></i><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.objects.result.view" /></a>');
        }
    });
}
</script>