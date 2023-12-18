const btnAdd = document.getElementById(`${fragmentNamespace}-add-button`);
const btndelete = document.getElementById("btn-delete");

const locomocion = "locomocion";
const penoso = "penoso";
const toxico = "toxico";
const trabajoConPantalla = "trabajoConPantalla";
const volante = "volante";
const valorEstado = "pendiente";

const valorSolicitante = $('[name=nombre]').val() + ' ' + $('[name=primerApellido]').val() + ' ' + $('[name=segundoApellido]').val();
const checkboxEstado = '<input type="checkbox">';
const tipoParte = $('[name=seleccionDeParte]');

const fechaDeActividadPenoso = $("[name=fechaDeActividadPenoso]");
const horaDeInicio = $("[name=horaDeInicio]");
const horaDeFin = $("[name=horaDeFin]");
const justificacionPenoso = $("[name=justificacionPenoso]");
const fechaInicio = $("[name=fechaInicio]");
const fechaFin = $("[name=fechaFin]");
const justificacionToxico = $("[name=justificacionToxico]");
const justificacionPantalla = $("[name=justificacionPantalla]");
const fechaFinPantalla = $("[name=fechaFinPantalla]");
const fechaInicioPantalla = $("[name=fechaInicioPantalla]");

const centroTrabajoOrigen = $('[name=centroDeTrabajoOrigen]');
const centroDeTrabajoDestino = $('[name=centroDeTrabajoDestino]');
const kilometrosTotales = $('[name=kilometrosTotales]');
const desde = $('[name=desde]');
const hasta = $('[name=hasta]');
const kilmetrosTotales = $('[name=kilmetrosTotales]');
const centroDeTrabajoOrigenVolante = $('[name=centroDeTrabajoOrigenVolante]');
const centroDeTrabajoDestinoVolante = $('[name=centroDeTrabajoDestinoVolante]');
const desdeVolante = $('[name=desdeVolante]');
const hastaVolante = $('[name=hastaVolante]');
const kilmetrosTotalesVolante = $('[name=kilmetrosTotalesVolante]');
const fechaActividad = $('[name=fechaDeActividad]');
const fechaInicioVolante = $("[name=fechaInicioVolante]");
const fechaFinVolante = $("[name=fechaFinVolante]");
const numeroDeVehiculo = $("[name=numeroDeVehiculo]");
const jusficacionVolante = $("[name=justificacinVolante]");


$(btnAdd).click(function (event) {
  event.preventDefault();

  var valorParte = tipoParte.val();
  if (valorParte === locomocion) {
    let labelParte = "Locomoción";
    addLocomocion(labelParte);
  } else if (valorParte === penoso) {
    let labelParte = "Penoso";
    addPenoso(labelParte);
  } else if (valorParte === toxico) {
    let labelParte = "Tóxico";
    addToxico(labelParte);
  } else if (valorParte === trabajoConPantalla) {
    let labelParte = "Trabajo con pantalla";
    addTrabajoPantalla(labelParte);
  } else if (valorParte === volante) {
    let labelParte = "Volante";
    addVolante(labelParte);
  }
});

$(btndelete).click(function () {
  var filasMarcadas = $("#table-solicitudes tbody input[type=checkbox]:checked").closest("tr");
  filasMarcadas.remove();
  var inputListadoSolicitudes = $("[name=listadoSolicitudes]");
  var listadoSolicitudes = [];
  $("#table-solicitudes tbody tr").each(function () {
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

function addLocomocion(valorParte) {

  const camposLocomocion = [fechaActividad];
  var valores = camposLocomocion.map(verificarCampo);
  const camposTayectos = [$('[name=origenCentro]'), $('[name=destinoCentro]')];
  const camposOtros = [desde, hasta, kilmetrosTotales];

  if ($('[name=tipoDeDesplazamiento]').val() === 'trayectosEntreCentros') {
    valores = valores.concat(camposTayectos.map(verificarCampo));
  }else if ($('[name=tipoDeDesplazamiento]').val() === 'otrosTrayectos') {
    valores = valores.concat(camposOtros.map(verificarCampo));
  }

  const valorFechaActividad = fechaActividad.val();
  const tableSolicitudes = $("#locomocion-table-id tbody tr");

  if (tableSolicitudes.length > 0) {
    tableSolicitudes.each(function () {
      const valorTablaDesde = $(this).find('td:eq(0)').text();
      const valorTablaHasta = $(this).find('td:eq(1)').text();
      const valorKM = $(this).find('td:eq(2)').text();
      const valorDetalles = valorTablaDesde + " : " + valorTablaHasta;

      if (valorSolicitante !== "" && valorFechaActividad !== "" && valorDetalles !== "") {
        addSolicitudTabla(valorParte, valorFechaActividad, valorKM, valorDetalles);
        $("#locomocion-table-id tbody").empty();
      } else {
        fechaActividad.addClass('error');
      }
    });
  }else if (valores.every(function (valor) { return valor !== undefined && valor !== ""; })) {
    const valorDetalles = $('[name=centroDeTrabajoOrigen-label]').val() + "-" + $('[name=centroDeTrabajoDestino-label]').val();
    addSolicitudTabla(valorParte, valorFechaActividad, kilometrosTotales.val(), valorDetalles);
    camposLocomocion.forEach(function (campo) {
      campo.val('').removeClass('error');
    });
    vaciarInputLocomocion(fechaActividad, centroTrabajoOrigen, centroDeTrabajoDestino, kilometrosTotales);
  }
}

function addPenoso(valorParte) {
  var camposPenoso = [fechaDeActividadPenoso, horaDeInicio, horaDeFin, justificacionPenoso];
  var valores = camposPenoso.map(verificarCampo);

  if (valores.every(function (valor) { return valor !== undefined && valor !== "";; })) {
    var diferenciasHoras = calcularHorasDiferencia(valores[0], valores[1], valores[2]);
    addSolicitudTabla(valorParte, valores[0], diferenciasHoras, valores[3]);

    camposPenoso.forEach(function (campo) {
      campo.val('').removeClass('error');
    });
  }
}

function addToxico(valorParte) {
  var camposToxico = [fechaInicio, fechaFin, justificacionToxico];
  var valores = camposToxico.map(verificarCampo);

  if (valores.every(function (valor) { return valor !== undefined && valor !== ""; })) {
    var fechaActividad = valores[0] + ' - ' + valores[1];
    var valorDias = calcularDiasDiferencia(valores[0], valores[1]);
    if (valorDias > 0) {
      valorDias += ' día' + (valorDias !== 1 ? 's' : '');
      addSolicitudTabla(valorParte, fechaActividad, valorDias, valores[2]);
    }
    camposToxico.forEach(function (campo) {
      campo.val('').removeClass('error');
    });
  }
}

function addTrabajoPantalla(valorParte) {
  var camposTrabajoPantalla = [fechaInicioPantalla, fechaFinPantalla, justificacionPantalla];
  var valores = camposTrabajoPantalla.map(verificarCampo);

  if (valores.every(function (valor) { return valor !== undefined && valor !== ""; })) {
    var fechaActividad = valores[0] + ' - ' + valores[1];
    var valorDias = calcularDiasDiferencia(valores[0], valores[1]);
    if (valorDias > 0) {
      valorDias += ' día' + (valorDias !== 1 ? 's' : '');
      addSolicitudTabla(valorParte, fechaActividad, valorDias, valores[2]);
    }
    camposTrabajoPantalla.forEach(function (campo) {
      campo.val('').removeClass('error');
    });
  }
}

function addVolante(valorParte) {
  const camposVolante = [fechaInicioVolante, fechaFinVolante, numeroDeVehiculo, jusficacionVolante];
  var valores = camposVolante.map(verificarCampo);
  const camposTrayectos = [$('[name=centroOrigenVolante]'), $('[name=centroDestinoVolante]')];
  const camposOtros = [desdeVolante, hastaVolante, kilmetrosTotalesVolante];

  if ($('[name=tipoDeDesplazamientoVolante]').val() === 'trayectosEntreCentros') {
    valores = valores.concat(camposTrayectos.map(verificarCampo));
  }else if ($('[name=tipoDeDesplazamientoVolante]').val() === 'otrosTrayectos') {
    valores = valores.concat(camposOtros.map(verificarCampo));
  }

  if (valores.every(function (valor) { return valor !== undefined && valor !== ""; })) {
    var fechaActividad = valores[0] + ' - ' + valores[1];
    var valorDias = calcularDiasDiferencia(valores[0], valores[1]);
    if (valorDias > 0) {
      valorDias += ' día' + (valorDias !== 1 ? 's' : '');
      addSolicitudTabla(valorParte, fechaActividad, kilometrosTotalesVolante.val(), valorDetalles);
    }
    camposVolante.forEach(function (campo) {
      campo.val('').removeClass('error');
    });
  }
}

function vaciarInputLocomocion(fecha, desde, hasta, km) {
  fecha.val('');
  desde.val('');
  hasta.val('');
  km.val('');
}

function vaciarInputVolante(fechaIni, fechaFin, numVehiculo, justi, desde, hasta, km) {
  fechaIni.val('');
  fechaFin.val('');
  numVehiculo.val('');
  justi.val('');
  desde.val('');
  hasta.val('');
  km.val('');
}

function calcularHorasDiferencia(fecha, horaInicio, horaFin) {
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

function calcularDiasDiferencia(fechaInicio, fechaFin) {
  return Math.ceil((new Date(fechaFin) - new Date(fechaInicio)) / (1000 * 60 * 60 * 24)) + 1;
}

function addSolicitudTabla(valorParte, fecha, valor, detalles) {
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

function addJSONListaSolicitudes(parte, fecha, valor, detalles) {
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

$("input, textarea, select").on("input", function() {
  $(this).removeClass("error");
  $('#dialog-error').addClass('hide');
});
function verificarCampo(campo) {
  var valor = campo.val();
  if (valor === "") {
    campo.addClass('error');
    $('#dialog-error').removeClass('hide');
  }
  return valor;
}

const asterisco = '<span class="label-required">*</span>';
const divLabelAsterisco = $('.label-asterisco');
const labelFecha = divLabelAsterisco.find('label');
labelFecha.append(asterisco);
