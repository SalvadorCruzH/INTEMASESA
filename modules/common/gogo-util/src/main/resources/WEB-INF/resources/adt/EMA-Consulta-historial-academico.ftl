<#assign customExpando = serviceLocator.findService("es.emasesa.intranet.base.util.CustomExpandoUtil")/>
<#assign sapUtil = serviceLocator.findService("es.emasesa.intranet.service.util.SapServicesUtil")/>

<#assign userPernr = customExpando.getDataValueByUser(themeDisplay.getUser().getUserId(), themeDisplay.getCompanyId(), "matricula")/>
<#assign datos = sapUtil.getHistorialFormacion(userPernr)/>
<#assign formExterna = datos.externa?eval/>
<#assign formInterna = datos.interna?eval/>
<#assign formImpartida = datos.impartida?eval/>


<form id="consultaHistorForm">
    <label for="opcionesSelect">Tipo de consulta a realizar:</label><br>
    <select id="opcionesSelect" name="opcion">
        <option value="">Selecciona una opción</option>
        <option value="historico">Histórico de contratos y cambios de categoría</option>
        <option value="titulacion">Titulación académica</option>
        <option value="permisos">Permisos de conducir</option>
        <option value="expediente">Expediente formativo</option>
        <option value="innovacion">Innovación</option>
    </select><br>
    <button type="button" class="btn-primary" id="buttonConsultar">Consultar</button>
</form>

<div id="historicoContratos" class="ema-consultaHistorial" style="display: none">
    <h3 class="ema-consultaHistorial__title container">Histórico de contratos y cambios de categoría</h3>
    <div class="container">
        <div class="ema-table-wrapper">
            <table id="table-id-historico" class="ema-table">
                <caption class="sr-only">Sumario de la tabla</caption>
                <thead>
                <tr>
                    <th scope="col">Válido desde</th>
                    <th scope="col">Válido hasta</th>
                    <th scope="col">Nº de días</th>
                    <th scope="col">Contrato</th>
                    <th scope="col">Categoría</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="titulacionAcademica" class="ema-consultaHistorial" style="display: none">
    <h3 class="ema-consultaHistorial__title container">Titulación académica</h3>
    <div class="container">
        <div class="ema-table-wrapper">
            <table id="table-id-titulacion" class="ema-table">
                <caption class="sr-only">Sumario de la tabla</caption>
                <thead>
                <tr>
                    <th scope="col">Nivel</th>
                    <th scope="col">Título/Especialidad</th>
                    <th scope="col">Centro/Población</th>
                    <th scope="col">Título</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="permisosConducir" class="ema-consultaHistorial" style="display: none">
    <h3 class="ema-consultaHistorial__title container">Permisos de conducir</h3>
    <div class="container">
        <div class="ema-table-wrapper">
            <table id="table-id-permisos" class="ema-table">
                <caption class="sr-only">Sumario de la tabla</caption>
                <thead>
                <tr>
                    <th scope="col">Fecha</th>
                    <th scope="col">Fecha de Fin Vigencia</th>
                    <th scope="col">Permiso</th>
                    <th scope="col">Carnet (Sí/No)</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="expedienteFormativo" class="ema-consultaHistorial" style="display: none">
    <h3 class="ema-consultaHistorial__title container">Expediente formativo</h3>
    <div class="container">
        <div class="ema-consultaHistorial__buttons">
            <button class="btns" onclick="mostrarTab('tab3')">Formación Impartida</button>
            <button class="btns active" onclick="mostrarTab('tab1')">Formación Externa</button>
            <button class="btns" onclick="mostrarTab('tab2')">Formación Interna</button>
        </div>
        <div class="ema-table-wrapper">
            <div id="tab1" class="tab-content active-tab">
                <table class="ema-table">
                    <caption class="sr-only">Sumario de la tabla</caption>
                    <thead>
                    <tr>
                        <th scope="col">Nombre del Curso</th>
                        <th scope="col">Fecha de Comienzo</th>
                        <th scope="col">Fecha de Fin</th>
                        <th scope="col">Duración</th>
                        <th scope="col">Título</th>
                        <th scope="col">Encuesta</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list formExterna as item>
                        <tr>
                            <td>${item.curso}</td>
                            <td>${item.inicio}</td>
                            <td>${item.fin}</td>
                            <td>${item.duracion} h</td>
                            <td>${item.titulo}</td>
                            <td>vacio/pendiente/realizada</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

            <div id="tab2" class="tab-content">
                <table class="ema-table">
                    <caption class="sr-only">Sumario de la tabla</caption>
                    <thead>
                    <tr>
                        <th scope="col">Nombre del Curso</th>
                        <th scope="col">Fecha de Comienzo</th>
                        <th scope="col">Fecha de Fin</th>
                        <th scope="col">Duración</th>
                        <th scope="col">Título</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list formInterna as item>
                        <tr>
                            <td>${item.curso}</td>
                            <td>${item.inicio}</td>
                            <td>${item.fin}</td>
                            <td>${item.duracion} h</td>
                            <td>${item.titulo}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

            <div id="tab3" class="tab-content">
                <table class="ema-table">
                    <caption class="sr-only">Sumario de la tabla</caption>
                    <thead>
                    <tr>
                        <th scope="col">Nombre del Curso</th>
                        <th scope="col">Fecha de Comienzo</th>
                        <th scope="col">Fecha de Fin</th>
                        <th scope="col">Duración</th>
                        <th scope="col">Título</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list formImpartida as item>
                        <tr>
                            <td>${item.curso}</td>
                            <td>${item.inicio}</td>
                            <td>${item.fin}</td>
                            <td>${item.duracion} h</td>
                            <td>${item.titulo}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="innovacion" class="ema-consultaHistorial" style="display: none">
    <h3 class="ema-consultaHistorial__title container">Innovación</h3>
    <div class="container">
        <div class="ema-table-wrapper">
            <table id="table-id-innovacion" class="ema-table">
                <caption class="sr-only">Sumario de la tabla</caption>
                <thead>
                <tr>
                    <th scope="col">Fecha</th>
                    <th scope="col">Descripción</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    function mostrarTab(tabId) {
        var tabs = document.getElementsByClassName('tab-content');
        for (var i = 0; i < tabs.length; i++) {
            tabs[i].classList.remove('active-tab');
        }

        document.getElementById(tabId).classList.add('active-tab');
    }
    $(document).ready(function(){
        $(document).on('click', '.btns', function(){
            $('.btns').removeClass('active');
            $(this).addClass('active');
        });

       $('#buttonConsultar').click(function(){
           var opcion = $('#opcionesSelect').val();
           var contenedorContratos = $('#historicoContratos');
           var contenedorTitulacion = $('#titulacionAcademica');
           var contenedorPermisos = $('#permisosConducir');
           var contenedorExpediente = $('#expedienteFormativo');
           var contenedorInnovacion = $('#innovacion');

           const contenedorMap = {
               'historico': contenedorContratos,
               'titulacion': contenedorTitulacion,
               'permisos': contenedorPermisos,
               'expediente': contenedorExpediente,
               'innovacion': contenedorInnovacion
           };

           Object.values(contenedorMap).forEach(contenedor => contenedor.hide());

           if (contenedorMap[opcion]) {
               contenedorMap[opcion].show();
           }
       });
    });
</script>