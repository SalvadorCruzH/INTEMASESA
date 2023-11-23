let otpGenerado = false;
let otpValidado = false;

$(document).ready(function() {
	$('[id^="fragment-"][id$="-submit-button"]').prop('disabled', true);
	$('[id^="fragment-"][id$="-submit-button"]').attr('aria-disabled', true);

	$('[id^="fragment-"][id$="-submit-button"]').on('click', (e) => {
		e.preventDefault();
		if(otpGenerado && otpValidado) {
			$("#fragment-otp").closest("form").submit();
		}
	});
});
var showHtmlComponent = function(query) {
	$(query).removeClass('d-none');
	$(query).removeAttr('aria-hidden');
}
var hideHtmlComponent = function(query) {
	$(query).addClass('d-none');
	$(query).attr('aria-hidden', true);
}
var enableHtmlComponent = function(query) {
	$(query).prop('disabled', false);
	$(query).removeAttr('aria-disabled');
}
var disableHtmlComponent = function(query) {
	$(query).prop('disabled', true);
	$(query).attr('aria-disabled', true);
}

var setValue = function(query, value) {
	$(query).val(value);
}

var validateOtp = function(){
	const otp = $("#otp-input").val();
	const typeObject = configuration.tipoObjeto;
	const userId = themeDisplay.getUserId();
	const timestampGeneration = $("#timestampGeneration-input").val();

	const url = `/o/customotputil/validate-otp/${typeObject}/${userId}/${otp}/${timestampGeneration}`;

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
			return response.json();
		})
		.then(data => {
			let parseJsonData = JSON.parse(data.data);
			otpValidado = parseJsonData.isOk;
			if (otpValidado) {
				enableHtmlComponent('[id^="fragment-"][id$="-submit-button"]');
				showHtmlComponent("#validacion-otp-correcto");
				hideHtmlComponent("#fragment-otp");
			} else {
				if (parseJsonData.reason === "expired"){
					hideHtmlComponent("#error-validacion-otp-invalido");
					showHtmlComponent("#error-validacion-otp-expirado");

					$("#generate-otp-button").prop('disabled', false);
					$("#generate-otp-button").removeAttr('aria-disabled');
				} else {
					hideHtmlComponent("#error-validacion-otp-expirado");
					showHtmlComponent("#error-validacion-otp-invalido");
				}

			}
		})
		.catch(error => {
			showHtmlComponent("#error-validacion-otp-invalido");
			console.error('Hubo un error:', error);
		});
}
$("#generate-otp-button").on('click', () => {
	$("#fragment-otp").removeClass('d-none');
	$("#fragment-otp").removeAttr('aria-hidden');
	
	const typeObject = configuration.tipoObjeto;
	const length = 6;
	const userId = themeDisplay.getUserId();
	const companyId = themeDisplay.getCompanyId();
	const to = themeDisplay.getUserEmailAddress();

	const url = `/o/customotputil/generate-send-otp/${typeObject}/${userId}/${length}/${companyId}/${to}`;

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
		return response.json();
	})
	.then(data => {
		let parseJsonData = JSON.parse(data.data);
		if(parseJsonData.otp) {
			console.debug("DEBUG: El OTP es: " + parseJsonData.otp);
		}
		if (parseJsonData.isOk) {
			hideHtmlComponent("#error-envio-otp");
	
			disableHtmlComponent("#generate-otp-button");

			setValue("#timestampGeneration-input", parseJsonData.timestampGeneration);

			showHtmlComponent("#input-wrapper");
			enableHtmlComponent("#otp-input");
			enableHtmlComponent("#validate-otp-button");
			otpGenerado = true;
			setTimeout(() => {
				$("#generate-otp-button").prop('disabled', false);
				$("#generate-otp-button").removeAttr('aria-disabled');
			}, 30000);
		} else {
			showHtmlComponent("#error-envio-otp");
		}
	})
	.catch(error => {
		showHtmlComponent("#error-envio-otp");
		console.error('Hubo un error:', error);
	});
});

$("#validate-otp-button").on('click', validateOtp);
$("#otp-input").on('keypress', (event) => {
	if(event.which === 13){
		validateOtp();
	}
});


