
const labelInputElement = document.getElementById(
    // eslint-disable-next-line no-undef
    `${fragmentEntryLinkNamespace}-label-input`
);
const uiInputElement = document.getElementById(
    // eslint-disable-next-line no-undef
    `${fragmentEntryLinkNamespace}-select-from-list-input`
);
const valueInputElement = document.getElementById(
    // eslint-disable-next-line no-undef
    `${fragmentEntryLinkNamespace}-value-input`
);
const tipoParte = $('[name=seleccionDeParte]');

const url =  `/o/emasesa/v1.0/intranet/distanciacentros`;

fetch(url, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
    }
    })
    .then(response => response.json())
    .then(data => {
        labelInputElement.innerHTML = data.label;

        if (Array.isArray(data)) {
            let uniqueValues = new Set();

            data.forEach(option => {
                uniqueValues.add(option.centroOrigenId);
            });

            uniqueValues.forEach(uniqueValue  => {
                let optionElement = document.createElement("option");
                optionElement.value = uniqueValue;
                optionElement.text = data.find(option => option.centroOrigenId === uniqueValue).centroOrigenDesc;
                uiInputElement.appendChild(optionElement);
            });
        } else {
            console.error('Error: La respuesta no es un array.');
        }

        valueInputElement.innerHTML = data.value;
    })
    .catch(error => {
        console.error('Error:', error);
    });

uiInputElement.addEventListener('change', function () {
    const selectedValue = uiInputElement.value;
    const selectedText = uiInputElement.options[uiInputElement.selectedIndex].text;

    valueInputElement.value = selectedValue;
    valueInputElement.textContent = selectedValue;
    labelInputElement.value = selectedText;
    labelInputElement.textContent = selectedText;

    var locomocion = "locomocion";
    var volante = "volante";
    if ($('[name=centroDeTrabajoOrigen]').val() != "" && $('[name=centroDeTrabajoDestino]').val() != "" && tipoParte.val() == locomocion){
        getCentroDistancias(locomocion, $('[name=centroDeTrabajoOrigen]').val(), $('[name=centroDeTrabajoDestino]').val());
    }else if($('[name=centroDeTrabajoOrigenVolante]').val() != "" && $('[name=centroDeTrabajoDestinoVolante]').val() != "" && tipoParte.val() == volante){
        getCentroDistancias(volante, $('[name=centroDeTrabajoOrigenVolante]').val(), $('[name=centroDeTrabajoDestinoVolante]').val());
    }
});

function getCentroDistancias(tipo, origen, destino){
    const kilometrosTotales = $('[name=kilometrosTotales]');
    const kilometrosTotalesVolante = $('[name=kilometrosTotalesVolante]');
    const url =  `/o/emasesa/v1.0/intranet/distanciacentros/` + origen + '/' + destino;

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data.distancia)
            if (tipo === "locomocion") {
                kilometrosTotales.val(data.distancia)
            } else if (tipo === "volante") {
                kilometrosTotalesVolante.val(data.distancia);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}