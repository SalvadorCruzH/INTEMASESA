$(`#fragment-${fragmentEntryLinkNamespace}-submit-button`).click(function (e) { 
    e.preventDefault();
    
    console.log('click')
    var url = "/o/custom-object-util/submit-horas-extra"
    
    var data = $(this).closest("form").serialize();

    var formData = $(this).closest("form").serializeArray();
      var objetoDatos = {};

      $.each(formData, function(i, field){
        objetoDatos[field.name] = field.value;
      });
      objetoDatos['userId'] = themeDisplay.getUserId();
      objetoDatos['horasExtraObjectId'] = configuration.horasExtraObjectId;
      var datos = {form: objetoDatos}
      var jsonDatos = JSON.stringify(datos);
      
      console.log(data)
    console.log(jsonDatos)
    
    $.ajax({
        url: url,
        type: 'POST',
        data: jsonDatos,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if(data != null && data.code == 200) {
                //hide form and append message next to it
                $(this).closest("form").hide();
                $(this).closest("form").after('<p>Gracias. Hemos recibido correctamente su información. <a href="/group/guest/mis-gestiones/todas">Ir a Todas Mis Gestiones</a></p>');
            }else{
                alert('No se han podido enviar las horas extra');
                $(`fragment-${fragmentEntryLinkNamespace}-error-message`).show();
            }
        },
        error: function (data) {
            $(`fragment-${fragmentEntryLinkNamespace}-error-message`).show();
        }
    });
});