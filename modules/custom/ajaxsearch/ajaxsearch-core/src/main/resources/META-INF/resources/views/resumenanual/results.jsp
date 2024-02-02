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
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.mes" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.total" /></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.dedicaciones"/></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.horas.extras"/></th>
                            <th scope="col"><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.vacaciones"/><liferay-ui:message key="es.emasesa.intranet.gestionhorarios.days"/></th>

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

            var columnasAOcultar = [2,3]

            for (var i = 0; i < columnas; i++) {
                if(columnasAOcultar.includes(i)){
                    var todosSonCero = true;

                    for (var j = 1; j < table.rows.length; j++) {
                        if (parseFloat(table.rows[j].cells[i].innerHTML) !== 0) {
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

            var columnasASumar = [1,2,3,4]
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
        function formatearMinutos() {
            var table = document.getElementById('table-id');
            var columnas = table.rows[0].cells.length;

            var columnasAModificar = [1,2,3]
            for (var i = 0; i < columnas; i++) {
                if(columnasAModificar.includes(i)){
                    for (var j = 1; j < table.rows.length; j++) {
                        var numero = parseFloat(table.rows[j].cells[i].innerHTML)
                        var parteEntera = Math.floor(numero);
                        var parteDecimal = numero - parteEntera;
                        var minutos = Math.round(parteDecimal * 60);
                        if (minutos < 10) {
                            minutos = "0" + minutos;
                        }
                        var horaFormateada = parteEntera + ":" + minutos;
                        console.log(horaFormateada)
                        table.rows[j].cells[i].innerHTML = horaFormateada;
                    }
                }
            }
        }
        sumatorioColumnas();
        desocultarTodasLasColumnas();
        ocultarColumnaSiTodosSonCero();
        formatearMinutos();
    }
}
</script>
