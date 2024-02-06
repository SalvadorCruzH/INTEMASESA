var tipologiaASubtipologia = {};
$(document).ready(function(){
    if (!$('body').hasClass('has-edit-mode-menu')){
        $('input[name=destinatario]').hide();
        $('input[name=destinatario]').prev().hide();
    }
    for (var key in data) {
        if (data.hasOwnProperty(key)) {
            var parts = key.split('_');
            var tipologia = parts[0];
            var subtipologia = parts[1];
            if (subtipologia !== undefined) {
                if (tipologiaASubtipologia[tipologia] === undefined) {
                    tipologiaASubtipologia[tipologia] = [subtipologia];
                } else {
                    tipologiaASubtipologia[tipologia].push(subtipologia);
                }
            }
        }
    }
});
$('#selectTipologia').change(function(){
    var subtipologia = $(this).find('option:selected').data("subtipologia");
    if (subtipologia == true) {
        $('.subtipologia').show();
    } else {
        $('.subtipologia').hide();
    }
    var tipologiaSeleccionada = $(this).val();
    var subtipologias = tipologiaASubtipologia[tipologiaSeleccionada];

    // Limpiamos la subtipología
    $('#selectSubtipologia').empty();

    // Si hay subtipologias, las añadimos al segundo select
    if(subtipologias !== undefined) {
        for(let i = 0; i < subtipologias.length; i++) {
            $('#selectSubtipologia').append(new Option(subtipologias[i], subtipologias[i]));
        }
    }
});

$('#selectTipologia, #selectSubtipologia').change(function() {
    var tipologia = $('#selectTipologia').val();
    var subtipologia = $('#selectSubtipologia').val();
    var key = tipologia + ($("#selectTipologia").find('option:selected').data("subtipologia") == true ? '_' + subtipologia : '');
    var destinatario = data[key];
    $('input[name=destinatario]').val(destinatario);
});