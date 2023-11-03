$(document).ready(function() {
	$('[id^="fragment-"][id$="-submit-button"]').prop('disabled', true);
	$('[id^="fragment-"][id$="-submit-button"]').attr('aria-disabled', true);
});

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
		console.log(data);
		if (data) {
			console.log("OTP generado y enviado, esperando validación");

			$("#otp-input").removeClass('d-none');
			$("#otp-input").removeAttr('aria-hidden');
	
			$("#generate-otp-button").prop('disabled', true);
			$("#generate-otp-button").attr('aria-disabled', true);
		
			$("#validate-otp-button").prop('disabled', false);
			$("#validate-otp-button").removeAttr('aria-disabled');
		
			setTimeout(() => {
				$("#generate-otp-button").prop('disabled', false);
				$("#generate-otp-button").removeAttr('aria-disabled');
			}, 30000);
		} else {
			console.log("No se ha podido enviar el correo con el OTP, vuelva a intentarlo");
		}
	})
	.catch(error => {
		console.error('Hubo un error:', error);
	});
});

$("#validate-otp-button").on('click', () => {
	console.log($(this));
	
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
		console.log(data);
		if (data) {
			console.log("OTP validado, submit activado");
			$('[id^="fragment-"][id$="-submit-button"]').prop('disabled', false);
			$('[id^="fragment-"][id$="-submit-button"]').removeAttr('aria-disabled');
		} else {
			console.log("OTP no válido, submit desactivado");
		
		}
	})
	.catch(error => {
		console.error('Hubo un error:', error);
	});
});