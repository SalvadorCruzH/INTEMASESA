<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div id="as-total-items">
            <!-- EMPTY BY DEFAULT -->
        </div>

        <div class="m-results-wrapper ema-publisher ema-ajaxsearch">
             <div class="ema-table-wrapper">
                                <table id="table-id" class="ema-table">
                                    <caption class="sr-only">Sumario de la tabla</caption>
                                    <thead>
                                        <tr>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.date" /></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.day" /></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.ent" arguments="1"/></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sal" arguments="1"/></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.ent" arguments="2"/></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sal" arguments="2"/></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.ent" arguments="3"/></th>

                                        </tr>
                                    </thead>
                                    <tbody  id="as-wrapper">
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

<template id="as-total-items-template">
    <div class="m-ajaxresults-header-element">
        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.found-sing" /> #total-items# <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.new" />
    </div>

    <div class="m-ajaxresults-header-elements">
        <liferay-ui:message key="es.emasesa.intranet.ajaxsearch.found-plur" /> #total-items# <liferay-ui:message key="entries" />
    </div>

    <div class="d-none"> #items-page# elementos por página</div>
</template>


<template id="as-template">
     <tr>
        <td>#mes#</td>
        <td>#trabajadas#</td>
        <td>#dedicacion#</td>
        <td>#dedicacionFestivo#</td>
        <td>#horasExtrasComputo#</td>
        <td>#vacaciones#</td>
     </tr>
</template>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function (newItem, jsonItem) {return newItem},
    _postdrawItem : function (jsonItem) {},
    _predrawAll : function (payload) {},
    _postdrawAll : function (payload) {}
}
</script>