const currentLength = document.getElementById(
	`${fragmentNamespace}-current-length`
);
const formGroup = document.getElementById(`${fragmentNamespace}-form-group`);
const lengthInfo = document.getElementById(`${fragmentNamespace}-length-info`);
const lengthWarning = document.getElementById(
	`${fragmentNamespace}-length-warning`
);
const lengthWarningText = document.getElementById(
	`${fragmentNamespace}-length-warning-text`
);
const textarea = document.getElementById(`${fragmentNamespace}-textarea`);

function enableLenghtWarning() {
	formGroup.classList.add('has-error');
	lengthInfo.classList.add('text-danger', 'font-weight-semi-bold');
	lengthWarning.classList.remove('sr-only');

	const warningText = lengthWarningText.getAttribute('data-error-message');
	lengthWarningText.innerText = warningText;

	if (!configuration.showCharactersCount) {
		lengthInfo.classList.remove('sr-only');
	}
}

function disableLengthWarning() {
	formGroup.classList.remove('has-error');
	lengthInfo.classList.remove('text-danger', 'font-weight-semi-bold');
	lengthWarning.classList.add('sr-only');

	const validText = lengthWarningText.getAttribute('data-valid-message');
	lengthWarningText.innerText = validText;

	if (!configuration.showCharactersCount) {
		lengthInfo.classList.add('sr-only');
	}
}

function onInputKeyup(event) {
	const length = event.target.value.length;

	currentLength.innerText = length;

	if (length > input.attributes.maxLength) {
		enableLenghtWarning();
	}
	else if (formGroup.classList.contains('has-error')) {
		disableLengthWarning();
	}
}

function main() {
	if (layoutMode === 'edit' && textarea) {
		textarea.setAttribute('disabled', true);
	}
	else {
		currentLength.innerText = textarea.value.length;

		if (textarea.value.length > input.attributes.maxLength) {
			enableLenghtWarning();
		}

		textarea.addEventListener('keyup', onInputKeyup);
	}
}

var divHoras = $(`#${fragmentEntryLinkNamespace}-horasDiv`);
var diasSemana = $("[name=seleccionDiasSemana]");
diasSemana.each(function(index, element) {
	var div = $('<div>').addClass(fragmentEntryLinkNamespace + "-" + element.value + '-horas-padre').hide();
    var span = $('<span>').text(element.value);
    var input1 = $('<input>').attr('type', 'text').attr('name', fragmentEntryLinkNamespace + "-" + element.value + 'horaInicio').addClass(element.value + '-horaInicio');
    var input2 = $('<input>').attr('type', 'text').attr('name', fragmentEntryLinkNamespace + "-" + element.value + 'horaFin').addClass(element.value + '-horaFin');
	if (configuration.horasExcedentes){
		var select = $('<select>').attr('name', fragmentEntryLinkNamespace + "-" + element.value + '-select-compensacion');
		var option1 = $('<option>').val('').text('Selecciona una compensación');
		var option2 = $('<option>').val('abono').text('Abono');
		var option3 = $('<option>').val('descansoCompensatorio').text('Descanso compensatorio');
		select.append(option1, option2, option3);
		div.append(span, input1, input2, select);	
	} else {
		div.append(span, input1, input2);
	}
    divHoras.append(div);
});



divHoras.find('input').change(function() {
	updateJson();
});

divHoras.find('select').change(function() {
	updateJson();
});

function updateJson() {
	var json = {};
	divHoras.find('input').each(function(index, element) {
		if(element.value == ""){
			return;
		}
		var dia = element.className.split('-')[0];
		var hora = element.className.split('-')[1];
		if(!json[dia]){
			json[dia] = {};
		}
		json[dia][hora] = element.value;

	});
	divHoras.find('select').each(function(index, element) {
		if(element.value == ""){
			return;
		}
		var dia = element.name.split('-')[1];
		var hora = element.name.split('-')[2];
		if(!json[dia]){
			json[dia] = {};
		}
		json[dia][hora] = element.value;
	});
	textarea.value = JSON.stringify(json);
}


$("[name=seleccionDiasSemana]").change(function() {
	for (var i = 0; i < $("[name=seleccionDiasSemana]").length; i++) {
		if($("[name=seleccionDiasSemana]").get(i).checked){
			$(`.${fragmentEntryLinkNamespace}-${$("[name=seleccionDiasSemana]").get(i).value}-horas-padre`).show();
		} else {
			$(`.${fragmentEntryLinkNamespace}-${$("[name=seleccionDiasSemana]").get(i).value}-horas-padre`).hide();
			$(`.${$("[name=seleccionDiasSemana]").get(i).value}-horaInicio`).val("");
			$(`.${$("[name=seleccionDiasSemana]").get(i).value}-horaFin`).val("");
		}
	}
});



main();