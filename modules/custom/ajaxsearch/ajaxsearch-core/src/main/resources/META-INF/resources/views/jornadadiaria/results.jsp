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
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sal" arguments="3"/></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.ent" arguments="4"/></th>
                                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sal" arguments="4"/></th>

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
        <td>#dia#</td>
        <td>#TTEXT#</td>
        <td>#HTRAB#</td>
        <td>#SEMAFORO#</td>
        <td>#ENT1#</td>
        <td>#SAL1#</td>
        <td>#ENT2#</td>
        <td>#SAL2#</td>
        <td>#ENT3#</td>
        <td>#SAL3#</td>
        <td>#ENT4#</td>
        <td>#SAL4#</td>
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