/*$(document).ready(function () {

  const titulacion = $("#titulacionAcademica");
  const contentTitulacion = $(".titulacionAcademica");
  const permisos = $("#permisosConducir");
  const contentPermisos = $(".permisoConducir");
  const expediente = $("#expedienteFormativoFragment");
  const contentExpediente = $(".formacionExterna");
  const innovacion = $("#innovacion");
  const contentInnovacion = $(".innovacionIdea");
  const tipoActualizacion = $("[name='seleccionTipoDeActualizacion']");
  const categoria = $("[name='categoriaSelector']");
  $(document).on('click', '[id*="-radio-group"]', function (e) {
      if (categoria.val() === 'titulacionAcademica' && tipoActualizacion.val() === 'nuevaAportacion') {
        permisos.hide();
        contentPermisos.hide();
        expediente.hide();
        contentExpediente.hide();
        innovacion.hide();
        contentInnovacion.hide();
        titulacion.show();
        contentTitulacion.removeClass('hide').show();
      }

      else if (categoria.val() === 'permisoDeConducir' && tipoActualizacion.val() === 'nuevaAportacion') {
        titulacion.hide();
        contentTitulacion.hide();
        expediente.hide();
        contentExpediente.hide();
        innovacion.hide();
        contentInnovacion.hide();
        permisos.show();
        contentPermisos.removeClass('hide').show();
      }

      else if (categoria.val() === 'formacionExternaRecibida' && tipoActualizacion.val() === 'nuevaAportacion') {
        titulacion.hide();
        contentTitulacion.hide();
        permisos.hide();
        contentPermisos.hide();
        innovacion.hide();
        contentInnovacion.hide();
        expediente.show();
        contentExpediente.removeClass('hide').show();
      }

      else if (categoria.val() === 'innovacion' && tipoActualizacion.val() === 'nuevaAportacion') {
        titulacion.hide();
        contentTitulacion.hide();
        permisos.hide();
        contentPermisos.hide();
        expediente.hide();
        contentExpediente.hide();
        innovacion.show();
        contentInnovacion.removeClass('hide').show();
      }

  });
});*/
$(document).ready(function () {
  const secciones = {
    'titulacionAcademica': { table: $("#titulacionAcademica"), content: $(".titulacionAcademica"), boton: $("#buttonTitulacion"), rectificar: $(".rectificarTitulacion")},
    'permisoDeConducir': { table: $("#permisosConducir"), content: $(".permisoConducir"), boton: $("#buttonPermiso"), rectificar: $(".rectificarPermiso") },
    'formacionExternaRecibida': { table: $("#expedienteFormativoFragment"), content: $(".formacionExterna"), boton: $("#buttonFormacion"), rectificar: $(".rectificarFormacion") },
    'innovacion': { table: $("#innovacion"), content: $(".innovacionIdea"), boton: $("#buttonInnovacion"), rectificar: $(".rectificarInnovacion") }
  };

  const tipoActualizacion = $("[name='seleccionTipoDeActualizacion']");
  const categoria = $("[name='categoriaSelector']");

  $(document).on('click', '[id*="-radio-group"]', function (e) {
    const selectedCategoria = categoria.val();
    const selectedTipoActualizacion = tipoActualizacion.val();

    Object.values(secciones).forEach(seccion => {
      seccion.table.hide();
      seccion.content.hide();
      seccion.rectificar.hide();
    });

    if (secciones[selectedCategoria] && selectedTipoActualizacion === 'nuevaAportacion') {
      secciones[selectedCategoria].boton.hide();
      secciones[selectedCategoria].rectificar.hide();
      secciones[selectedCategoria].table.show();
      secciones[selectedCategoria].content.removeClass('hide').show();
    }
    else if (secciones[selectedCategoria] && selectedTipoActualizacion === 'rectificacion'){
      secciones[selectedCategoria].content.hide();
      secciones[selectedCategoria].table.show();
      secciones[selectedCategoria].boton.show();
      secciones[selectedCategoria].rectificar.removeClass('hide').show();
    }
  });

  $(document).on('click', '.ema-consultaHistorial__boton-modificar', function (e) {
    e.preventDefault();

    var contenedor = $(this).closest('.ema-consultaHistorial');
    var tabla = contenedor.find('.ema-table');

    if($(this).hasClass('modificar')){
      $(this).removeClass('modificar').addClass('guardar').text('Guardar');
      tabla.find('td').prop('contenteditable', true);
    }

    else if($(this).hasClass('guardar')){
      $(this).removeClass('guardar').addClass('modificar').text('Modificar');
      tabla.find('td').prop('contenteditable', false);
    }
  });
});

