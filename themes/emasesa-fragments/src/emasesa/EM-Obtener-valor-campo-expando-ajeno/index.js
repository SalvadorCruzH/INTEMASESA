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
const inputElement = document.getElementById(`${fragmentNamespace}-text-input`);

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
	if (layoutMode === 'edit' && inputElement) {
		inputElement.setAttribute('disabled', true);
	}
	else {
		currentLength.innerText = inputElement.value.length;

		if (inputElement.value.length > input.attributes.maxLength) {
			enableLenghtWarning();
		}

		inputElement.addEventListener('keyup', onInputKeyup);
	}
}

main();

$(document).ready(function(){
	var myInput = document.querySelector(`.${configuration.inputUserIdClass} input[id$="-value-input"]`);

	// Add an event listener for the 'change' event
	myInput.addEventListener('change', function() {
		// This function will be called when the input value changes and the input loses focus
		var inputUserIdValue = myInput.value;

		const url =  `/o/expando/get-user-expando-by-userid/${inputUserIdValue}/${configuration.expandoName}`;

		fetch(url, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
			}
		})
		.then(response => response.text())
		.then(result => {
			document.getElementById(`${fragmentNamespace}-text-input`).value = result;
		})
		.catch(error => {
			console.error('Error:', error);
		});
		

	});
	
});


