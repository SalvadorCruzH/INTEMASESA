const externalReference = configuration.externalReferencesListaProvincias;
const companyId = themeDisplay.getCompanyId();
const urlSapUtil = `/o/sap-service-util/get-info-personal/${matricula}`;
const urlListUtil = `/o/list-util/get-list/${externalReference}/${companyId}`;
var list;
$( document ).ready(function() {
	if (!$('body').hasClass('has-edit-mode-menu')){
		$('.em-loading-overlay').removeClass("hide");
	}
    
    getListData();

    fetch(urlSapUtil, {
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
            fillAllFields(parseJsonData);
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

function fillAllFields(jsonData){
    setValue(configuration.selectFechaNacimiento, jsonData.fechaNacimiento, "text")
    setValue(configuration.selectCalle, jsonData.datosDomicilio.calle, "text")
    setValue(configuration.selectNumero, jsonData.datosDomicilio.numero, "text")
    setValue(configuration.selectPisoyLetra, jsonData.datosDomicilio.pisoLetra, "text")
    setValue(configuration.selectPortal, jsonData.datosDomicilio.portal, "text")
    setValue(configuration.selectProvincia, jsonData.datosDomicilio.provinciaId, "select")
    setValue(configuration.selectCodigoPostal, jsonData.datosDomicilio.codigoPostal, "text")
    setValue(configuration.selectPoblacion, jsonData.datosDomicilio.poblacion, "text")
    setValue(configuration.selectEmail, jsonData.email, "text")
    setValue(configuration.selectTelefono, jsonData.datosDomicilio.telefono, "text")
}
function setValue(inputName, inputValue, inputType){
    if (inputType === "select"){
        list.forEach(function(jsonObject) {
            if (jsonObject["key"] == inputValue){
                $('input[name="' + inputName +'"]').val(inputValue);
                $('input[name="' + inputName +'-label"]').val(jsonObject["name"]);
                $('input[name="' + inputName + '"]').prev().val(jsonObject["name"]);
            }
        });

    } else {
        $('input[name="' + inputName +'"]').val(inputValue);
    }

}