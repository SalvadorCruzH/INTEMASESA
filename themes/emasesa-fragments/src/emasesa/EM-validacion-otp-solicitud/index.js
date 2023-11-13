$(document).ready(function() {
	$('[id^="fragment-"][id$="-submit-button"]').prop('disabled', true);
	$('[id^="fragment-"][id$="-submit-button"]').attr('aria-disabled', true);
	var otpGenerado = false;
	var otpValidado = false;
	$('[id^="fragment-"][id$="-submit-button"]').on('click', (e) => {
		e.preventDefault();
		if(otpGenerado && otpValidado) {
			$("#fragment-otp").parent("form").submit();
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
			console.debug("DEBUG: El correo no se ha podido enviar, el OTP es: " + parseJsonData.otp);
		}
		if (data.data) {
			hideHtmlComponent("#error-envio-otp");
	
			disableHtmlComponent("#generate-otp-button");

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

$("#validate-otp-button").on('click', () => {	
	const otp = $("#otp-input").val();
	const typeObject = configuration.tipoObjeto;
	const userId = themeDisplay.getUserId();

	const url = `/o/customotputil/validate-otp/${typeObject}/${userId}/${otp}`;	

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
		otpValidado = data;
		if (data) {
			enableHtmlComponent('[id^="fragment-"][id$="-submit-button"]');
			showHtmlComponent("#validacion-otp-correcto");
			hideHtmlComponent("#fragment-otp");
		} else {
			showHtmlComponent("#error-validacion-otp");
		}
	})
	.catch(error => {
		showHtmlComponent("#error-validacion-otp");
		console.error('Hubo un error:', error);
	});
});


