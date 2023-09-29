<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div id="as-total-items">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper ema-publisher ema-ajaxsearch">
            <table class="" style="width: 100%;">
                <thead>
                 <tr>
                  <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.result.doc-name" /></th>
                  <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.result.doc-category" /></th>
                  <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.result.doc-modified-date" /></th>
                  <th><liferay-ui:message key="es.emasesa.intranet.ajaxsearch.documents.result.doc-expiration-date" /></th>
                 </tr>
                </thead>
                <tbody id="as-wrapper">
                    <!-- -->
                </tbody>
            </table>
            <div id="wrapper-not-result" class="d-none">
                <liferay-ui:message key="no-results" />
            </div>
        </div>

    </div>
</div>

<template id="as-total-items-template" class="d-none">
    <div class="m-ajaxresults-header-element">
        #total-items# documento
    </div>

    <div class="m-ajaxresults-header-elements">
        #total-items# documentos
    </div>

    <div class="d-none"> #items-page# documentos por página</div>
</template>


<template id="as-template">
    <tr>
        <td><a class="ema-searchAjax__linkDoc" href="#docUrl#" target="_blank">#docName#</a></td>
        <td class="ema-doc-category">#category#</td>
        <td class="ema-publish-date">#modified#</td>
        <td class="ema-expiration-date">#expirationDate#</td>
    </tr>
</template>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function (newItem, jsonItem) {return newItem},
    _postdrawItem : function (jsonItem) {},
    _predrawAll : function (payload) {},
    _postdrawAll : function (payload) {}
}
//cuando todo se haya cargado
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

});
</script>