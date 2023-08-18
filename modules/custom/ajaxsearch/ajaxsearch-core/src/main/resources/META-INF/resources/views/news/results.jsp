<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div id="as-total-items" class="d-none">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper c-publisher c-noticias">
            <ul id="as-wrapper" class="c-publisher__list m-dFlexWrap m-listBaseNoStyles c-noticias__list">
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
        #total-items# noticia con fechas de #fechaDesde# a #fechaHasta#
    </div>

    <div class="m-ajaxresults-header-elements">
        #total-items# noticias con fechas de #fechaDesde# a #fechaHasta#
    </div>

    <div class="d-none"> #items-page# elementos por página</div>
</template>


<template id="as-template">
    <li class="c-noticias__item">
        #html#

    </li>
</template>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function (newItem, jsonItem) {return newItem},
    _postdrawItem : function (jsonItem) {},
    _predrawAll : function (payload) {},
    _postdrawAll : function (payload) {}
}
</script>