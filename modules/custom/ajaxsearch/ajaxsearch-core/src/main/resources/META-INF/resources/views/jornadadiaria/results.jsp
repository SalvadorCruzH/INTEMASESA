<%@ include file="init.jsp" %>

<div class="m-searchAjax m-searchAjax--results">
    <div>
        <div class="resums">
            <div id="wrapper-resum" class="resum">
                <h3 class="resum__title">Año actual</h3>
                <span class="resum__label"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.computo"/></span>
                <div class="resum__datas">
                    <span class="m-searchAjax pdt-disfrutar resum__data">
                        <div class="resum__input">
                            <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.pendiente.disfrutar"/>
                        </div>
                        <input type="text" id="pdtDisfrutar" name="pdtDisfrutar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax sin-planificar resum__data">
                        <div class="resum__input">
                            <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sin.planificar"/>
                        </div>
                        <input type="text" id="sinPlanificar" name="sinPlanificar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax vacaciones resum__data">
                        <div class="resum__input">
                            <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.vacaciones"/>
                        </div>
                        <input type="text" id="vacaciones" name="vacaciones" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                </div>
            </div>
            <div id="wrapper-resum-lastyear" class="resum">
                <h3 class="resum__title">Año pasado</h3>
                <span class="resum__label"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.computo"/></span>
                <div class="resum__datas">
                    <span class="m-searchAjax pdt-disfrutar resum__data">
                        <div class="resum__input">
                            <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.pendiente.disfrutar"/>
                        </div>
                        <input type="text" id="pdtDisfrutarLastYear" name="pdtDisfrutar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax sin-planificar resum__data">
                        <div class="resum__input">
                            <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.sin.planificar"/>
                        </div>
                        <input type="text" id="sinPlanificarLastYear" name="sinPlanificar" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                    <span class="m-searchAjax vacaciones resum__data">
                        <div class="resum__input">
                            <liferay-ui:message key="es.emasesa.intranet.gestionhorarios.vacaciones"/>
                        </div>
                        <input type="text" id="vacacionesLastYear" name="vacaciones" class="m-searchAjax__input" value="0" readonly="readonly" />
                    </span>
                </div>
            </div>
        </div>
        <%-- /.resums --%>
        <div class="m-results-wrapper ema-publisher ema-ajaxsearch">
             <div class="ema-table-wrapper">
                <table id="table-id" class="ema-table">
                    <caption class="sr-only">Sumario de la tabla</caption>
                    <thead>
                        <tr>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.dia.semana" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.type" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.total" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.icono" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.incidencia" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.dec.exlusiva" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.he.computo" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.he.diurna" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.he.nocturna" /></th>
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
                  <tfoot>
                    <tr>
                      <th id="total">Total :</th>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                    </tr>
                   </tfoot>
                </table>
            </div>
            <div id="wrapper-not-result" class="d-none">
                <liferay-ui:message key="no-results" />
            </div>
        </div>
        <div id="as-total-items">
            <!-- EMPTY BY DEFAULT -->
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
        <td class="sap-respuesta-icono"><span class="#SEMAFORO#" title="#tooltip#"></td>
        <td>#ATEXT#</td>
        <td>#HDEXC#</td>
        <td>#HEXTC#</td>
        <td>#HEXND#</td>
        <td>#HEXNN#</td>
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
    _postdrawAll : function (payload) {

        if($("#month").find(":selected").length == 0){
            //get second option and select it
            $("#month option:eq(2)").prop('selected', true);
        }

        if(payload.totalItems > 0){
            let item = payload.content[0];
            if(item.vacacionesYear.computoConFuturo && item.vacacionesYear.computoSinFuturo && item.vacacionesYear.contingenteVacaciones) {
                document.getElementById("pdtDisfrutar").value = item.vacacionesYear.computoConFuturo;
                document.getElementById("sinPlanificar").value = item.vacacionesYear.computoSinFuturo;
                document.getElementById("vacaciones").value = item.vacacionesYear.contingenteVacaciones;
            } else {
                document.getElementById("pdtDisfrutar").value = "0d";
                document.getElementById("sinPlanificar").value = "0d";
                document.getElementById("vacaciones").value = "0d";
            }
            //if january, show last year
            let currentMonth = new Date().getMonth();
            if(item.vacacionesLastYear.computoConFuturo && item.vacacionesLastYear.computoSinFuturo && item.vacacionesLastYear.contingenteVacaciones && currentMonth == 0) {
                document.getElementById("pdtDisfrutarLastYear").value = item.vacacionesLastYear.computoConFuturo;
                document.getElementById("sinPlanificarLastYear").value = item.vacacionesLastYear.computoSinFuturo;
                document.getElementById("vacacionesLastYear").value = item.vacacionesLastYear.contingenteVacaciones;
            } else {
                document.getElementById("wrapper-resum-lastyear").classList.add("d-none");
            }
        } else {
             if(payload.content.length > 0){
                 let item = payload.content[0];
                 if(item.vacacionesYear.computoConFuturo && item.vacacionesYear.computoSinFuturo && item.vacacionesYear.contingenteVacaciones) {
                    document.getElementById("pdtDisfrutar").value = item.vacacionesYear.computoConFuturo;
                    document.getElementById("sinPlanificar").value = item.vacacionesYear.computoSinFuturo;
                    document.getElementById("vacaciones").value = item.vacacionesYear.contingenteVacaciones;
                 } else {
                    document.getElementById("pdtDisfrutar").value = "0d";
                    document.getElementById("sinPlanificar").value = "0d";
                    document.getElementById("vacaciones").value = "0d";
                 }
                 //if january, show last year
                 let currentMonth = new Date().getMonth();
                 if(item.vacacionesLastYear.computoConFuturo && item.vacacionesLastYear.computoSinFuturo && item.vacacionesLastYear.contingenteVacaciones && currentMonth == 0) {
                    document.getElementById("pdtDisfrutarLastYear").value = item.vacacionesLastYear.computoConFuturo;
                    document.getElementById("sinPlanificarLastYear").value = item.vacacionesLastYear.computoSinFuturo;
                    document.getElementById("vacacionesLastYear").value = item.vacacionesLastYear.contingenteVacaciones;
                 } else {
                    document.getElementById("wrapper-resum-lastyear").classList.add("d-none");
                 }
             }
        }

        function ocultarColumnaSiTodosSonCero() {
            var table = document.getElementById('table-id');
            var columnas = table.rows[0].cells.length;

            var columnasAOcultar = [5,6,7,8]

            for (var i = columnas-1; i >= 0; i--) {
                if(columnasAOcultar.includes(i)){
                    var todosSonCero = true;

                    for (var j = 1; j < table.rows.length; j++) {
                        if (parseInt(table.rows[j].cells[i].innerHTML) !== 0) {
                            todosSonCero = false;
                            break;
                        }
                    }
                    //si todosSonCero ocultar columna
                    if(todosSonCero){
                        for (var j = 0; j < table.rows.length; j++) {
                            table.rows[j].cells[i].classList.add("d-none");
                        }
                    }
                }
            }
        }
        function desocultarTodasLasColumnas() {
            var table = document.getElementById('table-id');
            var columnas = table.rows[0].cells.length;

            for (var i = columnas-1; i >= 0; i--) {
                for (var j = 0; j < table.rows.length; j++) {
                    table.rows[j].cells[i].classList.remove("d-none");
                }
            }
        }
        function sumatorioColumnas() {
            var table = document.getElementById('table-id');
            var columnas = table.rows[0].cells.length;

            var columnasASumar = [2,5,6,7,8]
            for (var i = 0; i < columnas; i++) {
                if(columnasASumar.includes(i)){
                    var sum = 0;
                    for (var j = 1; j < table.rows.length-1; j++) {
                        sum += parseFloat(table.rows[j].cells[i].innerHTML);
                    }
                    table.rows[table.rows.length-1].cells[i].innerHTML = sum.toFixed(2);
                } 
            }
        }
        sumatorioColumnas();
        desocultarTodasLasColumnas();
        ocultarColumnaSiTodosSonCero();
    }
}
</script>
