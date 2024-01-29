const btnAdd = document.getElementById(`${fragmentNamespace}-add-button`);
const btndelete = document.getElementById("btn-delete");

const checkboxEstado = '<input type="checkbox">';


$(btnAdd).click(function (event) {
    event.preventDefault();
    addPersona()
});

$(btndelete).click(function() {
    var filasMarcadas = $("#table-usuarios-seleccionados tbody input[type=checkbox]:checked").closest("tr");
    filasMarcadas.remove();
    var inputListadoPersonas = $("[name=listadoPersonas]");
    var listadoPersonas = [];
    $("#table-usuarios-seleccionados tbody tr").each(function() {
        var persona = {
            pernr: $(this).find('td:eq(1)').text(),
            nombre: $(this).find('td:eq(2)').text(),
            apellido1: $(this).find('td:eq(3)').text(),
            apellido2: $(this).find('td:eq(4)').text()
        };
        listadoPersonas.push(persona);
    });
    var jsonValue = listadoPersonas.length > 0 ? JSON.stringify(listadoPersonas) : "";
    inputListadoPersonas.val(jsonValue);
});

function addPersona() {
    var valorUsuarioSeleccionado = $("#select-subordinados option:selected").val();
    let persona = JSON.parse(valorUsuarioSeleccionado);
    addPersonaTabla(persona.pernr, persona.nombre, persona.apellido1, persona.apellido2);
}

function addPersonaTabla(pernr, nombre, apellido1, apellido2) {
    //if pernr is not in table
    var pernrExists = $("#table-usuarios-seleccionados tbody tr").filter(function() {
        return $(this).find('td:eq(1)').text() === pernr;
    }).length > 0;
    
    if (!pernrExists) {
        var nuevaFila = '<tr>' +
            '<td>' + checkboxEstado + '</td>' +
            '<td>' + pernr + '</td>' +
            '<td>' + nombre + '</td>' +
            '<td>' + apellido1 + '</td>' +
            '<td>' + apellido2 + '</td>' +
            '</tr>';
        $("#table-usuarios-seleccionados tbody").append(nuevaFila);
        addJSONListaPersonas(pernr, nombre, apellido1, apellido2);
    } else {
        console.log('El valor ya existe en la tabla.');
    }

}
function addJSONListaPersonas(pernr, nombre, apellido1, apellido2) {
    var inputListadoPersonas = $("[name=listadoPersonas]");
    var listadoPersonas = [];
    var jsonValue = inputListadoPersonas.val();
    if (jsonValue) {
        listadoPersonas = JSON.parse(jsonValue);
    }
    var persona = {
        pernr: pernr,
        nombre: nombre,
        apellido1: apellido1,
        apellido2: apellido2
    };
    listadoPersonas.push(persona);
    inputListadoPersonas.val(JSON.stringify(listadoPersonas));
}

