var fechaActual = new Date();
var fechaFormateada = fechaActual.toISOString().split('T')[0];
$('#fechaActual').val(fechaFormateada);