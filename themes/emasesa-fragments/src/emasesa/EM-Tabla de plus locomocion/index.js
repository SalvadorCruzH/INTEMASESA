const btnAdd = document.getElementById('add-button-table');

const locomocion = "locomocion";
const trayectosEntreCentros = "trayectosEntreCentros";
const otrosTrayectos = "otrosTrayectos";

const tipoDeDesplazamiento = $('[name=tipoDeDesplazamiento]');
const centroTrabajoOrigen = $('[name=centroDeTrabajoOrigen-label]');
const centroDeTrabajoDestino = $('[name=centroDeTrabajoDestino-label]');
const kilometrosTotales = $('[name=kilometrosTotales]');
const desde = $('[name=desde]');
const hasta = $('[name=hasta]');
const kilmetrosTotales = $('[name=kilmetrosTotales]');

$(btnAdd).click(function (event) {
  event.preventDefault();

  if (tipoDeDesplazamiento.val() == trayectosEntreCentros && $('[name=seleccionDeParte]').val() === locomocion) {
    addFilaTabla(centroTrabajoOrigen, centroDeTrabajoDestino, kilometrosTotales);
    $('[name=origenCentro]').val('');
    $('[name=destinoCentro]').val('');
  } else if (tipoDeDesplazamiento.val() == otrosTrayectos && $('[name=seleccionDeParte]').val() === locomocion) {
    addFilaTabla(desde, hasta, kilmetrosTotales);
  }
});

function addFilaTabla(valorOrigen, valorDestino, kilometros) {
  if (valorOrigen.val() !== "" && valorDestino.val() !== "" && kilometros.val() !== 0) {
    var nuevaFila = '<tr><td>' + valorOrigen.val() + '</td><td>' + valorDestino.val() + '</td><td>' + kilometros.val() + '</td></tr>';
    $("#" + `${configuration.idConfig}` + "-table-id tbody").append(nuevaFila);
    valorOrigen.val('');
    valorDestino.val('');
    kilometros.val('');
    $('[name=centroDeTrabajoOrigen]').val('');
    $('[name=centroDeTrabajoDestino]').val('');
    $('#add-button-table').prop("disabled", true)
  }
}

$('[name=origenCentro], [name=destinoCentro]').on("change", function (){
  setTimeout(function() {
    if (centroTrabajoOrigen.val() !== "" && $('[name=centroDeTrabajoDestino]').val() !== "" && kilometrosTotales.val() !== "") {
      $('#add-button-table').prop("disabled", false);
    }else{
      $('#add-button-table').prop("disabled", true);
    }
  }, 500);
});

kilmetrosTotales.on("input", verificarContenido);
desde.on("input", verificarContenido);
hasta.on("input", verificarContenido);

function verificarContenido() {
  if (kilmetrosTotales.val() !== "" && desde.val() !== "" && hasta.val() !== "") {
    $('#add-button-table').prop("disabled", false);
  } else {
    $('#add-button-table').prop("disabled", true);
  }
}
$('[name=tipoDeDesplazamiento-1]').on('click', function(){
  if (centroTrabajoOrigen.val() !== "" && $('[name=centroDeTrabajoDestino]').val() !== "" && kilometrosTotales.val() !== "") {
    $('#add-button-table').prop("disabled", false);
  }else{
    $('#add-button-table').prop("disabled", true);
  }
  if(desde.val() !== "" && hasta.val() !== "" && kilmetrosTotales.val() !== ""){
    $('#add-button-table').prop("disabled", false);
  }else{
    $('#add-button-table').prop("disabled", true);
  }
})
