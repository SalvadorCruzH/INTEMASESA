<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>

        <div class="m-results-wrapper ema-publisher ema-ajaxsearch">
            <div class="resums">
                <div id="wrapper-resum-lastyear" class="resum">
                    <span>
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.computo"/>
                    </span>
                    <span class="m-searchAjax pdt-disfrutar">
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.pendiente.disfrutar"/>
                        <input type="text" id="pdtDisfrutarLastYear" name="pdtDisfrutar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax sin-planificar">
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sin.planificar"/>
                        <input type="text" id="sinPlanificarLastYear" name="sinPlanificar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax vacaciones">
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.vacaciones"/>
                        <input type="text" id="vacacionesLastYear" name="vacaciones" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                </div>
                <div id="wrapper-resum" class="resum">
                    <span>
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.computo"/>
                    </span>
                    <span class="m-searchAjax pdt-disfrutar">
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.pendiente.disfrutar"/>
                        <input type="text" id="pdtDisfrutar" name="pdtDisfrutar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax sin-planificar">
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sin.planificar"/>
                        <input type="text" id="sinPlanificar" name="sinPlanificar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax vacaciones">
                        <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.vacaciones"/>
                        <input type="text" id="vacaciones" name="vacaciones" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                </div>
            </div>
            <%-- /.resums --%>
             <div class="ema-table-wrapper">
                <table id="table-id" class="ema-table">
                    <caption class="sr-only">Sumario de la tabla</caption>
                    <thead>
                        <tr>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.mes" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.total" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.dedicaciones"/></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.horas.extras"/></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.vacaciones"/>
                            <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.days"/></th>

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
            <div id="as-total-items">
                <!-- EMPTY BY DEFAULT -->
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
        <td>#horasExtrasComputo#</td>
        <td>#vacaciones#</td>
     </tr>
</template>

<script>
ajaxSearchGlobalConfig = {
    _preAppendItem : function (newItem, jsonItem) {return newItem},
    _postdrawItem : function (jsonItem) {},
    _predrawAll : function (payload) {},
    _postdrawAll : function (payload) {

        if(payload.totalItems > 0){
            let item = payload.content[0];
            if(item.vacacionesYear) {
                document.getElementById("pdtDisfrutar").value = item.vacacionesYear.computoConFuturo;
                document.getElementById("sinPlanificar").value = item.vacacionesYear.computoSinFuturo;
                document.getElementById("vacaciones").value = item.vacacionesYear.contingenteVacaciones;
            } else {
                document.getElementById("wrapper-resum").classList.add("d-none");
            }
            //if january, show last year
            let currentMonth = new Date().getMonth();
            if(item.vacacionesLastYear && currentMonth == 0) {
                document.getElementById("pdtDisfrutarLastYear").value = item.vacacionesLastYear.computoConFuturo;
                document.getElementById("sinPlanificarLastYear").value = item.vacacionesLastYear.computoSinFuturo;
                document.getElementById("vacacionesLastYear").value = item.vacacionesLastYear.contingenteVacaciones;
            } else {
                document.getElementById("wrapper-resum-lastyear").classList.add("d-none");
            }
        }

    }
}
</script>
