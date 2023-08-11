<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div id="as-total-items" class="d-none">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper c-dosieres">
            <ul id="as-wrapper" class="m-listBaseNoStyles c-dosieres__list">
                <!-- -->
            </ul>
            <div id="wrapper-not-result" class="d-none">
                <liferay-ui:message key="no-results" />
            </div>
        </div>

    </div>
</div>

<template id="as-total-items-template">
    <div class="m-ajaxresults-header-element">
        #total-items# resultado
    </div>

    <div class="m-ajaxresults-header-elements">
        #total-items# resultados
    </div>

    <div> #items-page# elementos por página</div>
</template>


<template id="as-template">
    <li class="m-searchAjax__li c-dosieres__list-item">
        #html#
    </li>
</template>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function (newItem, jsonItem) {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const tipoMedios = urlParams.get('tipoMedios')
        if(tipoMedios != null){
            $(newItem).find('li.c-dosieres-item').not(':has(span.'+tipoMedios+')').remove();
        }
        
        if ($(newItem).find('li.c-dosieres-item').length > 0) {
            return newItem
        } else {
            return $()
        }


    },
    _postdrawItem : function (jsonItem) {},
    _predrawAll : function (payload) {},
    _postdrawAll : function (payload) {}
}
</script>