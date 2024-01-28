<#assign customExpando = serviceLocator.findService("es.emasesa.intranet.base.util.CustomExpandoUtil")/>
<#assign sapUtil = serviceLocator.findService("es.emasesa.intranet.service.util.SapServicesUtil")/>
<#assign customDate = serviceLocator.findService("es.emasesa.intranet.base.util.CustomDateUtil")/>

<#assign userPernr = customExpando.getDataValueByUser(themeDisplay.getUser().getUserId(), themeDisplay.getCompanyId(), "matricula")>
<#assign nextDate = customDate.getDateNextMonth()/>
<#assign fechaActual = .now?date />
<#assign primerDiaDelMes = fechaActual?string("yyyy-MM-01") />
<#assign subordinados = sapUtil.getSubordinados(userPernr, "T")?eval />

<#assign empleados = []>

<#list subordinados as item>
    <#assign datosUsuario = sapUtil.getDatosEmpleado(item.pernr)/>
    <#assign empleado = {
    "nombre": datosUsuario.nombre,
    "apellido1": datosUsuario.apellido1,
    "apellido2": datosUsuario.apellido2,
    "pernr": item.pernr
    }>

    <#assign empleados = empleados + [empleado]>
</#list>

<#assign empleadosOrdenados = empleados?sort_by("nombre")>

<div class="container">
    <h2 class="page-title">Consulta formación de mis empleados</h2>
    <div class="ema-table-wrapper">
        <table id="table-id" class="ema-table">
            <caption class="sr-only">Sumario de la tabla</caption>
            <thead>
            <tr>
                <th scope="col">Nombre del empleado</th>
                <th scope="col">Título de la formación</th>
                <th scope="col">Tipo de formación</th>
                <th scope="col">Fechas inicio/fin</th>
                <th scope="col">Estado</th>
            </tr>
            </thead>
            <tbody>
            <#list empleadosOrdenados as item>
                <#assign eventoCalendario = sapUtil.getCalendarioEventos(item.pernr, primerDiaDelMes, nextDate)/>
                <#assign eventos = eventoCalendario.eventos?eval/>
                <#assign contenidos = eventoCalendario.contenido/>
                <#list eventos as event>
                    <tr>
                        <#assign estado = ''>
                        <#assign fechaInicio = event.begda?date("yyyy-MM-dd")>
                        <#assign fechaFin = event.endda?date("yyyy-MM-dd")>

                        <#if fechaInicio < fechaActual && fechaFin < fechaActual>
                            <#assign estado = 'Finalizada'>
                        <#elseif fechaInicio < fechaActual && fechaFin gt fechaActual>
                            <#assign estado = 'En Curso'>
                        <#elseif fechaInicio gt fechaActual>
                            <#assign estado = 'Convocada'>
                        </#if>
                        <#if event.asisteElEmpleado == "X">
                            <td>${item.nombre} ${item.apellido1} ${item.apellido2}</td>
                            <td><span class="titleEvent" Style="cursor:pointer;" data-event="${event.eventoId}">${event.eventoDesc}</span></td>
                            <td>${event.convocatoria}</td>
                            <td>${fechaInicio?string("dd-MM-YY")}<br>${fechaFin?string("dd-MM-YY")}</td>
                            <td>${estado}</td>
                        </#if>
                    </tr>
                </#list>
            </#list>
            </tbody>
        </table>
    </div>
</div>

<#list eventos as event>
    <div id="${event.eventoId}" class="detallesEventos container">
        <div class ="detallesEventos__close"></div>
        <h5 class="detallesEventos__titulo">Título de la formación: <span>${event.eventoDesc}</span></h5>
        <h5 class="detallesEventos__titulo">Modalidad: <span>${event.modalidad}</span></h5>
        <h5 class="detallesEventos__titulo">Tipo de formación: <span>${event.convocatoria}</span></h5>
        <h5 class="detallesEventos__titulo">Fecha de inicio y fin: <span>${event.begda?date("yyyy-MM-dd")?string("dd-MM-yyyy")} / ${event.endda?date("yyyy-MM-dd")?string("dd-MM-yyyy")}</span></h5>
        <h5 class="detallesEventos__titulo">Duración: <span>${event.duracion}h</span></h5>
        <h5 class="detallesEventos__titulo">Sede: <span>${event.sede}</span></h5>
        <h5 class="detallesEventos__titulo">Id de la formación: <span>${event.eventoId}</span></h5>
        <div class="detallesEventos__titulo">
            <h5 >Contenido del evento:</h5>
            <div id="contenidoCursos"></div>
        </div>
    </div>
</#list>
<script type="text/javascript">
    $('.detallesEventos').hide();
    $(document).ready(function() {
        $('.titleEvent').on('click', function(){
            var eventoID = $(this).attr('data-event');
            verDetalles(eventoID);
            $('.detallesEventos').hide();
            $('#' + eventoID + '').show()
            $('html, body').animate({
                scrollTop: $('#' + eventoID + '').offset().top - 200
            }, 'slow');
        });

        $('.detallesEventos__close').on('click', function(){
            $('.detallesEventos').hide();
            $('html, body').animate({
                scrollTop: $('.ema-table-wrapper').offset().top - 200
            }, 'slow');
        });
    });

    function verDetalles(eventoID) {
        var contenidos = ${contenidos};
        var literalesporId = {};
        $('#contenidoCursos').empty();
        contenidos.forEach(function(item){
            if (!literalesporId[item.eventoId]){
                literalesporId[item.eventoId] = [];
            }
            literalesporId[item.eventoId].push(item.literal);
        });
        if (literalesporId[eventoID]) {
            let parrafo = document.createElement('p');
            parrafo.innerHTML  = literalesporId[eventoID].join('<br>');

            $('#contenidoCursos').append(parrafo);
        }
    }
</script>