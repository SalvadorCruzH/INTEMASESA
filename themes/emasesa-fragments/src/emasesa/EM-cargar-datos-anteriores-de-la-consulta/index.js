$(document).ready(function() {
	$('input[name="' + configuration.nameConsultaId +'"]').closest(".select-from-list").hide();
	$('input[name="' + configuration.nameConsultaId +'"]').val(consultaId);
});