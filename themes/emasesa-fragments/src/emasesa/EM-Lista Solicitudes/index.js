const btnAdd = document.getElementById(`${fragmentNamespace}-add-button`);
const btndelete = document.getElementById("btn-delete");

const locomocion = "locomocion";
const penoso = "penoso";
const toxico = "toxico";
const trabajoConPantalla = "trabajoConPantalla";
const volante = "volante";
const valorEstado = "pendiente";

var valorSolicitante = $('[name=nombre]').val() + ' ' + $('[name=primerApellido]').val() + ' ' + $('[name=segundoApellido]').val();
const checkboxEstado = '<input type="checkbox">';
var tipoParte = $('[name=seleccionDeParte]');

const fechaDeActividadPenoso = $("[name=fechaDeActividadPenoso]");
const horaDeInicio = $("[name=horaDeInicio]");
const horaDeFin = $("[name=horaDeFin]");
const justificacionPenoso = $("[name=justificacionPenoso]");
var fechaInicio = $("[name=fechaInicio]");
var fechaFin = $("[name=fechaFin]");
var justificacionToxico = $("[name=justificacionToxico]");
var justificacionPantalla = $("[name=justificacionPantalla]");
var fechaFinPantalla = $("[name=fechaFinPantalla]");
var fechaInicioPantalla = $("[name=fechaInicioPantalla]");

const centroTrabajoOrigen = $('[name=centroDeTrabajoOrigen]');
const centroDeTrabajoDestino = $('[name=centroDeTrabajoDestino]');
const kilometrosTotales = $('[name=kilometrosTotales]');
const desde = $('[name=desde]');
const hasta = $('[name=hasta]');
const kilmetrosTotales = $('[name=kilmetrosTotales]');
const centroDeTrabajoOrigenVolante = $('[name=centroDeTrabajoOrigenVolante]');
const centroDeTrabajoDestinoVolante = $('[name=centroDeTrabajoDestinoVolante]');
const kilometrosTotalesVolante = $('[name=kilometrosTotalesVolante]');
const desdeVolante = $('[name=desdeVolante]');
const hastaVolante = $('[name=hastaVolante]');
const kilmetrosTotalesVolante = $('[name=kilmetrosTotalesVolante]');

//[I] Formulario vacaciones, ausencias y permisos
var justificacion = $("[name=justificacion]");
var motivoAusencia = $("[name=motivoDeAusencia]");
const ausenciaParcial = "ausenciaParcial";
const diasCompletos = "diasCompletos";
var tipoDeAusencia = $('[name=tipoDeAusencia]');
//[F] Formulario vacaciones, ausencias y permisos

//[I] Formulario Registro de Marcajes
var tipoRegistro = $("[name=tipoDeRegistro]");
const registroMarcaje = "marcaje";
const registroFormacion = "presenciaDeFormacion";
var fechaRegActividad = $("[name=fechaDeActividad]");
var horaEntrada = $("[name=horaEntrada]");
var horaSalida = $("[name=horaSalida]");
var motivoMarcaje = $('[name=motivo]');
var requiereDesplazamiento = $('[name=requiereDesplazamiento]');
//[F] Formulario Registro de Marcajes

$(btnAdd).click(function (event) {
    event.preventDefault();

    var valorParte = tipoParte.val();
    if(valorParte == locomocion){
        var labelParte = "Locomoción";
        addLocomocion(labelParte);
    }else if(valorParte == penoso){
        var labelParte = "Penoso";
        addPenoso(labelParte);
    }else if(valorParte == toxico){
        var labelParte = "Tóxico";
        addToxico(labelParte);
    } else if(valorParte == trabajoConPantalla){
        var labelParte = "Trabajo con pantalla";
        addTrabajoPantalla(labelParte);
    } else if(valorParte == volante) {
        var labelParte = "Volante";
        addVolante(labelParte);
    }
	
	//[I] Formulario vacaciones, ausencias y permisos
	var valorAusencia = tipoDeAusencia.val();
	if(valorAusencia == ausenciaParcial){
		var labelParte = "Vacaciones/Ausencias";
		addAusenciaParcial(labelParte);
	}else if(valorAusencia == diasCompletos){
		var labelParte = "Vacaciones/Ausencias";
		addDiasCompletos(labelParte);
	}
	//[F] Formulario vacaciones, ausencias y permisos

    //[I] Formulario Registro de Marcajes
	var valorTipoRegistro = tipoRegistro.val();
	if(valorTipoRegistro == registroMarcaje){
		var labelParte = "Marcaje";
		addMarcaje(labelParte);
	}else if(valorTipoRegistro == registroFormacion){
		var labelParte = "Presencia de Formación";
		addFormacion(labelParte);
	}
	//[F] Formulario Registro de Marcajes
});

$(btndelete).click(function() {
    var filasMarcadas = $("#table-solicitudes tbody input[type=checkbox]:checked").closest("tr");
    filasMarcadas.remove();
    var inputListadoSolicitudes = $("[name=listadoSolicitudes]");
    var listadoSolicitudes = [];
    $("#table-solicitudes tbody tr").each(function() {
        var solicitud = {
            solicitante: $(this).find('td:eq(2)').text(),
            parte: $(this).find('td:eq(3)').text(),
            fecha: $(this).find('td:eq(4)').text(),
            valor: $(this).find('td:eq(5)').text(),
            detalles: $(this).find('td:eq(6)').text()
        };
        listadoSolicitudes.push(solicitud);
    });
    var jsonValue = listadoSolicitudes.length > 0 ? JSON.stringify(listadoSolicitudes) : "";
    inputListadoSolicitudes.val(jsonValue);
});

function addLocomocion(valorParte){
    var valorFechaActividad = $('[name=fechaDeActividad]').val();
    var fechaActividad = $('[name=fechaDeActividad]');

    if ($("#locomocion-table-id tbody tr").length > 0) {
        $("#locomocion-table-id tbody tr").each(function() {
            var valorTablaDesde = $(this).find('td:eq(0)').text();
            var valorTablaHasta = $(this).find('td:eq(1)').text();
            var valorKM = $(this).find('td:eq(2)').text();
            var valorDetalles = valorTablaDesde + " : " + valorTablaHasta;

            if(valorSolicitante != "" && valorFechaActividad != "" && valorDetalles != ""){
                addSolicitudTabla(valorParte, valorFechaActividad, valorKM, valorDetalles);
            }
        });
        $("#locomocion-table-id tbody").empty();
    }else if(valorFechaActividad != "" && centroTrabajoOrigen.val() != "" && centroDeTrabajoDestino.val() != "" && kilometrosTotales.val() != ""){
        addSolicitudTabla(valorParte, valorFechaActividad, kilometrosTotales.val(), centroTrabajoOrigen.val() + "-" + centroDeTrabajoDestino.val());
        vaciarInputLocomocion(fechaActividad, centroTrabajoOrigen, centroDeTrabajoDestino, kilometrosTotales);
    }else if(valorFechaActividad != "" && desde.val() != "" && hasta.val() != "" && kilmetrosTotales.val() != ""){
        addSolicitudTabla(valorParte, valorFechaActividad, kilmetrosTotales.val(), desde.val() + "-" + hasta.val());
        vaciarInputLocomocion(fechaActividad, desde, hasta, kilmetrosTotales);
    }
}

function addPenoso(valorParte){
    var valorFechaDeActividadPenoso = fechaDeActividadPenoso.val();
    var valorHoraDeInicio = horaDeInicio.val();
    var valorHoraDeFin = horaDeFin.val();
    var valorJustificacionPenoso = justificacionPenoso.val();

    if(valorFechaDeActividadPenoso != "" && valorHoraDeInicio != "" && valorHoraDeFin != "" && valorJustificacionPenoso != ""){

        var diferenciasHoras = calcularHorasDiferencia(valorFechaDeActividadPenoso, valorHoraDeInicio, valorHoraDeFin);
        addSolicitudTabla(valorParte, valorFechaDeActividadPenoso, diferenciasHoras, valorJustificacionPenoso);

        fechaDeActividadPenoso.val('')
        horaDeInicio.val('')
        horaDeFin.val('')
        justificacionPenoso.val('')
    }
}

function addToxico(valorParte){
    var valorFechaInicio = fechaInicio.val();
    var valorFechaFin = fechaFin.val();
    var valorJustificacionToxico = justificacionToxico.val();

    if(valorFechaInicio != "" && valorFechaFin != "" && valorJustificacionToxico != ""){
        var fechaActividad = valorFechaInicio + ' - ' + valorFechaFin;
        var valorDias = calcularDiasDiferencia(valorFechaInicio, valorFechaFin);

        if (valorDias > 0){
            valorDias += ' día' + (valorDias !== 1 ? 's' : '');
            addSolicitudTabla(valorParte, fechaActividad, valorDias, valorJustificacionToxico);
        }

        fechaInicio.val('')
        fechaFin.val('')
        justificacionToxico.val('')
    }
}

function addTrabajoPantalla(valorParte){
    var valorFechaInicioPantalla = fechaInicioPantalla.val();
    var valorFechaFinPantalla = fechaFinPantalla.val();
    var valorJustificacionPantalla = justificacionPantalla.val();

    if(valorFechaInicioPantalla != "" && valorFechaFinPantalla != "" && valorJustificacionPantalla != ""){
        var valorDias= calcularDiasDiferencia(valorFechaInicioPantalla, valorFechaFinPantalla);
        var fechaActividad = valorFechaInicioPantalla + ' - ' + valorFechaFinPantalla;

        if (valorDias > 0) {
            valorDias += ' día' + (valorDias !== 1 ? 's' : '');
            addSolicitudTabla(valorParte, fechaActividad, valorDias, valorJustificacionPantalla);
        }
        fechaInicioPantalla.val('')
        fechaFinPantalla.val('')
        justificacionPantalla.val('')
    }
}

function addVolante(valorParte){
    var fechaInicioVolante = $("[name=fechaInicioVolante]");
    var fechaFinVolante = $("[name=fechaFinVolante]");
    var numeroDeVehiculo = $("[name=numeroDeVehiculo]");
    var vehiculoPesado = $("[name=vehiculoPesado]").prop('checked') ? 'Vehículo pesado' : '';
    var justificacionVolante = $("[name=justificacinVolante]");
    var fechaActividad = fechaInicioVolante.val() + ' - ' + fechaFinVolante.val();

    if ($("#volante-table-id tbody tr").length > 0) {
        $("#volante-table-id tbody tr").each(function () {
            var valorTablaDesde = $(this).find('td:eq(0)').text();
            var valorTablaHasta = $(this).find('td:eq(1)').text();
            var valorKM = $(this).find('td:eq(2)').text();

            if (valorSolicitante != "" && valorParte != "" && fechaInicioVolante.val() != "" && fechaFinVolante.val() != "" && numeroDeVehiculo.val() != "" && justificacionVolante.val() != "") {
                var valorDias = calcularDiasDiferencia(fechaInicioVolante.val(), fechaFinVolante.val());

                if (valorDias > 0){
                    valorDias += ' día' + (valorDias !== 1 ? 's' : '');
                    if(vehiculoPesado != ""){
                        var valorDetalles = valorDias + ', ' + numeroDeVehiculo.val() + ", " + vehiculoPesado + ", " + justificacionVolante.val() + ", " + valorTablaDesde + " : " + valorTablaHasta;
                    }else if(vehiculoPesado == ""){
                        var valorDetalles = valorDias + ', ' + numeroDeVehiculo.val() + ", " + justificacionVolante.val() + ", " + valorTablaDesde + " : " + valorTablaHasta;
                    }
                    addSolicitudTabla(valorParte, fechaActividad, valorKM, valorDetalles);
                }
            }
        });
        $("#volante-table-id tbody").empty();
    }else if(fechaInicioVolante.val() != "" && fechaFinVolante.val() != "" && numeroDeVehiculo.val() != "" && justificacionVolante.val() != "" && centroDeTrabajoOrigenVolante.val() != ""
            && centroDeTrabajoDestinoVolante.val() != "" && kilometrosTotalesVolante.val() != ""){

        var valorDias = calcularDiasDiferencia(fechaInicioVolante.val(), fechaFinVolante.val());
        if (valorDias > 0){
            valorDias += ' día' + (valorDias !== 1 ? 's' : '');
            if(vehiculoPesado != ""){
                var valorDetalles = valorDias + ', ' + numeroDeVehiculo.val() + ", " + vehiculoPesado + ", " + justificacionVolante.val() + ", " + centroDeTrabajoOrigenVolante.val() + " - " + centroDeTrabajoDestinoVolante.val();
            }else if(vehiculoPesado == ""){
                var valorDetalles = valorDias + ', ' + numeroDeVehiculo.val() + ", " + justificacionVolante.val() + ", " + centroDeTrabajoOrigenVolante.val() + " - " + centroDeTrabajoDestinoVolante.val();
            }
            addSolicitudTabla(valorParte, fechaActividad, kilometrosTotalesVolante.val(), valorDetalles);
            vaciarInputVolante(fechaInicioVolante, fechaFinVolante, numeroDeVehiculo, justificacionVolante, $('[name=centroOrigenVolante]'), $('[name=centroDestinoVolante]'), kilometrosTotalesVolante);
        }

    }else if(fechaInicioVolante.val() != "" && fechaFinVolante.val() != "" && numeroDeVehiculo.val() != "" && justificacionVolante.val() != "" && desdeVolante.val() != "" && hastaVolante.val() != "" && kilmetrosTotalesVolante.val() != ""){

        var valorDias = calcularDiasDiferencia(fechaInicioVolante.val(), fechaFinVolante.val());
        if (valorDias > 0){
            valorDias += ' día' + (valorDias !== 1 ? 's' : '');
            if(vehiculoPesado != ""){
                var valorDetalles = valorDias + ', ' + numeroDeVehiculo.val() + ", " + vehiculoPesado + ", " + justificacionVolante.val() + ", " + desdeVolante.val() + " - " + hastaVolante.val();
            }else if(vehiculoPesado == ""){
                var valorDetalles = valorDias + ', ' + numeroDeVehiculo.val() + ", " + justificacionVolante.val() + ", " + desdeVolante.val() + " - " + hastaVolante.val();
            }
            addSolicitudTabla(valorParte, fechaActividad, kilmetrosTotalesVolante.val(), valorDetalles);
            vaciarInputVolante(fechaInicioVolante, fechaFinVolante, numeroDeVehiculo, justificacionVolante, desdeVolante, hastaVolante, kilmetrosTotalesVolante);
        }
    }
}

function vaciarInputLocomocion(fecha, desde, hasta, km){
    fecha.val('');
    desde.val('');
    hasta.val('');
    km.val('');
}

function vaciarInputVolante(fechaIni, fechaFin, numVehiculo, justi, desde, hasta, km){
    fechaIni.val('');
    fechaFin.val('');
    numVehiculo.val('');
    justi.val('');
    desde.val('');
    hasta.val('');
    km.val('');
}

function calcularHorasDiferencia(fecha, horaInicio, horaFin){
    var fechaInicio = new Date(fecha + ' ' + horaInicio);
    var fechaFin = new Date(fecha + ' ' + horaFin);

    if (fechaFin < fechaInicio) {
        fechaFin.setDate(fechaFin.getDate() + 1);
    }
    var horas = Math.abs(Math.floor((fechaFin - fechaInicio) / (1000 * 60 * 60)));
    var minutos = Math.abs(Math.floor((fechaFin - fechaInicio) / (1000 * 60))) % 60;
    var horasStr = horas < 10 ? '0' + horas : horas;
    var minutosStr = minutos < 10 ? '0' + minutos : minutos;

    return horasStr + ':' + minutosStr;
}

function calcularDiasDiferencia(fechaInicio, fechaFin){
    return Math.ceil((new Date(fechaFin) - new Date(fechaInicio)) / (1000 * 60 * 60 * 24)) + 1;
}

function addSolicitudTabla(valorParte, fecha, valor, detalles){
    var nuevaFila = '<tr>' +
        '<td>' + checkboxEstado + '</td>' +
        '<td>' + valorEstado + '</td>' +
        '<td>' + valorSolicitante + '</td>' +
        '<td>' + valorParte + '</td>' +
        '<td>' + fecha + '</td>' +
        '<td>' + valor + '</td>' +
        '<td>' + detalles + '</td>' +
        '</tr>';

    $("#table-solicitudes tbody").append(nuevaFila);
    addJSONListaSolicitudes(valorParte, fecha, valor, detalles);
}

function addJSONListaSolicitudes(parte, fecha, valor, detalles){
    var inputListadoSolicitudes = $("[name=listadoSolicitudes]");
    var listadoSolicitudes = [];

    var jsonValue = inputListadoSolicitudes.val();
    if (jsonValue) {
        listadoSolicitudes = JSON.parse(jsonValue);
    }

    var solicitud = {
        solicitante: valorSolicitante,
        parte: parte,
        fecha: fecha,
        valor: valor,
        detalles: detalles
    };
    listadoSolicitudes.push(solicitud);
    inputListadoSolicitudes.val(JSON.stringify(listadoSolicitudes));
}

function addDiasCompletos(valorParte){
    var valorFechaInicio = fechaInicio.val();
    var valorFechaFin = fechaFin.val();
    var valorJustificacion = justificacion.val();
	var valortipoDeAusencia = tipoDeAusencia.val();
	var valormotivoAusencia= motivoAusencia.val();
	

    if(valorFechaInicio != "" && valorFechaFin != "" && valorJustificacion != ""){
        var fechaActividad = valorFechaInicio + ' - ' + valorFechaFin;
        var valorDias = calcularDiasDiferencia(valorFechaInicio, valorFechaFin);
		var detalles = "Tipo: " + valortipoDeAusencia + ", " + "Motivo: " + valormotivoAusencia + ", " + "Justificacion: " + valorJustificacion;

        if (valorDias > 0){
            valorDias += ' día' + (valorDias !== 1 ? 's' : '');
            addSolicitudTabla(valorParte, fechaActividad, valorDias, detalles);
        }

        fechaInicio.val('')
        fechaFin.val('')
        justificacion.val('')
    }
}

function addAusenciaParcial(valorParte){
    var valorFechaInicio = fechaInicio.val();
    var valorHoraDeInicio = horaDeInicio.val();
    var valorJustificacion = justificacion.val();
	var valortipoDeAusencia = tipoDeAusencia.val();
	var valormotivoAusencia= motivoAusencia.val();

    if(valorFechaInicio != ""&& valorJustificacion != ""){
        var fechaActividad = valorFechaInicio + ' - ' + valorHoraDeInicio;
		var detalles = "Tipo: " + valortipoDeAusencia + ", " + "Motivo: " + valormotivoAusencia + ", " + "Justificacion: " + valorJustificacion;
        var valorDias = 1;

        if (valorDias > 0){
            valorDias += ' día' + (valorDias !== 1 ? 's' : '');
            addSolicitudTabla(valorParte, fechaActividad, valorDias, detalles);
        }

        fechaInicio.val('')
        horaDeInicio.val('')
        justificacion.val('')
    }
}

function addFormacion(valorParte){
    var valorFechaActividad = fechaRegActividad.val();
    var valorRequiereDesplazamiento = requiereDesplazamiento.is(":checked");


    if(valorFechaActividad != "") {
        var fechaActividad = valorFechaActividad;
        
		var detalles = "Requiere Desplazamiento: " + (valorRequiereDesplazamiento == true ? "Si" : "No");

        addSolicitudTabla(valorParte, fechaActividad, "", detalles);

        fechaRegActividad.val('')
    }
}

function addMarcaje(valorParte){
    var valorFechaActividad = fechaRegActividad.val();
    var valorHoraEntrada = horaEntrada.val();
    var valorHoraSalida = horaSalida.val();
    var valorMotivoMarcaje = motivoMarcaje.val();


    if(valorFechaActividad != "" && valorMotivoMarcaje != "") {
        var fechaActividad = valorFechaActividad;
        
		var detalles = "Motivo: " + valorMotivoMarcaje;
        var valorHoras =   valorHoraEntrada +"-"+ valorHoraSalida;

        if (valorHoraEntrada !="" && valorHoraSalida !=""){
            addSolicitudTabla(valorParte, fechaActividad, valorHoras, detalles);
        }

        fechaRegActividad.val('')
        horaEntrada.val('')
        horaSalida.val('')
    }
}