const externalReference = configuration.externalReferencesListaProvincias;
const companyId = themeDisplay.getCompanyId();
const url = `/o/sap-service-util/get-info-personal/${matricula}`;
const urlListUtil = `/o/list-util/get-list/${externalReference}/${companyId}`;
var list;
$( document ).ready(function() {
		if (!$('body').hasClass('has-edit-mode-menu')){
		$('.em-loading-overlay').removeClass("hide");
	}
		getListData();
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json()
        })
        .then(data => {
            let parseJsonData = JSON.parse(data.data);
            checkAllFields(parseJsonData);
            $('.em-loading-overlay').addClass("hide");
        })
        .catch(error => {
            console.error('Hubo un error:', error);
        });
});

function getListData(){
    fetch(urlListUtil, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            return response.json()
        })
        .then(data => {
            list = JSON.parse(data.data);

        })
        .catch(error => {
            console.error('Hubo un error:', error);
        });
}

function checkAllFields(jsonData){
	fillListInput(configuration.selectProvincia);
    changeColor("fechaNacimiento", jsonData.fechaNacimiento, "text")
    changeColor("calle", jsonData.datosDomicilio.calle, "text")
    changeColor("numero", jsonData.datosDomicilio.numero, "text")
    changeColor("pisoyLetra", jsonData.datosDomicilio.pisoLetra, "text")
    changeColor("portal", jsonData.datosDomicilio.portal, "text")
    changeColor("provincia", jsonData.datosDomicilio.provinciaId, "select")
    changeColor("codigoPostal", jsonData.datosDomicilio.codigoPostal, "text")
    changeColor("poblacion", jsonData.datosDomicilio.poblacion, "text")
    changeColor("email", jsonData.email, "text")
    changeColor("telefono", jsonData.datosDomicilio.telefono, "text")
}

function fillListInput(inputName){
	let input = $('input[name="' + inputName +'"]');
	let inputValue = input.val();
	list.forEach(function(jsonObject) {
            if (jsonObject["key"] == inputValue){
                input.val(inputValue);
                $('input[name="' + inputName +'-label"]').val(jsonObject["name"]);
                input.prev().val(jsonObject["name"]);
            }
});
}
function changeColor(inputName, sapValue, typeInput){
    let input = (typeInput === "select")?$('input[name="' + inputName + '"]').prev():$('input[name="' + inputName + '"]');
    if ( input.val() !== sapValue){
       input.css('color', '#FF7F00').prev('label').css('color', '#FF7F00');
    }
}