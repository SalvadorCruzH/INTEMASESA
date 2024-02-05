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
            console.log("success", data);
            if(data){
                alert('Se han enviado las horas extra correctamente');
            }else{
                alert('No se han podido enviar las horas extra');
            }
        },
        error: function (data) {
            console.log("error",data);
            alert('No se han podido enviar las horas extra');
        }
    });
});