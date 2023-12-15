const btnAdd = document.getElementById(`${fragmentNamespace}-add-button`);

const locomocion = "locomocion";
const volante = "volante";
const trayectosEntreCentros = "trayectosEntreCentros";
const otrosTrayectos = "otrosTrayectos";

const tipoParte = $('[name=seleccionDeParte]');
const tipoDeDesplazamiento = $('[name=tipoDeDesplazamiento]');
const tipoDeDesplazamientoVolante = $('[name=tipoDeDesplazamientoVolante]');

const centroTrabajoOrigen = $('[name=centroDeTrabajoOrigen-label]');
const centroDeTrabajoDestino = $('[name=centroDeTrabajoDestino-label]');
const kilometrosTotales = $('[name=kilometrosTotales]');
const desde = $('[name=desde]');
const hasta = $('[name=hasta]');
const kilmetrosTotales = $('[name=kilmetrosTotales]');
const centroDeTrabajoOrigenVolante = $('[name=centroDeTrabajoOrigenVolante-label]');
const centroDeTrabajoDestinoVolante = $('[name=centroDeTrabajoDestinoVolante-label]');
const kilometrosTotalesVolante = $('[name=kilometrosTotalesVolante]');
const desdeVolante = $('[name=desdeVolante]');
const hastaVolante = $('[name=hastaVolante]');
const kilmetrosTotalesVolante = $('[name=kilmetrosTotalesVolante]');

$(btnAdd).click(function (event) {
    event.preventDefault();
    var valorParte = tipoParte.val();

    if(valorParte == locomocion){
        if(tipoDeDesplazamiento.val() == trayectosEntreCentros){
            addFilaTabla(centroTrabajoOrigen, centroDeTrabajoDestino, kilometrosTotales);
            $('[name=origenCentro]').val('');
            $('[name=destinoCentro]').val('');
        }else if(tipoDeDesplazamiento.val() == otrosTrayectos){
            addFilaTabla(desde, hasta, kilmetrosTotales);
        }
    }else if(valorParte == volante) {
        if(tipoDeDesplazamientoVolante.val() == trayectosEntreCentros){
            addFilaTabla(centroDeTrabajoOrigenVolante, centroDeTrabajoDestinoVolante, kilometrosTotalesVolante);
            $('[name=centroDestinoVolante]').val('');
            $('[name=centroOrigenVolante]').val('');
        }else if(tipoDeDesplazamientoVolante.val() == otrosTrayectos){
            addFilaTabla(desdeVolante, hastaVolante, kilmetrosTotalesVolante);
        }
    }
});

function addFilaTabla(valorOrigen, valorDestino, kilometros){
    if (valorOrigen.val() != "" && valorDestino.val() != "" && kilometros.val() != 0) {
        var nuevaFila = '<tr><td>' + valorOrigen.text() + '</td><td>' + valorDestino.text() + '</td><td>' + kilometros.val() + '</td></tr>';
        $("#" + `${configuration.idConfig}` +"-table-id tbody").append(nuevaFila);
        valorOrigen.val('');
        valorDestino.val('');
        kilometros.val('');
        $('[name=centroDeTrabajoDestinoVolante]').val('');
        $('[name=centroDeTrabajoOrigenVolante]').val('');
        $('[name=centroDeTrabajoOrigen]').val('');
        $('[name=centroDeTrabajoDestino]').val('');
    }
}