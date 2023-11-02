var submitButton = $('[id^="fragment-"][id$="-submit-button"]');
submitButton.prop('disabled', true);
submitButton.attr('aria-disabled', true);





$("#generate-otp-button").on('click', () => {
	//mostrar input
	$("#fragment-otp").removeClass('d-none');
	$("#fragment-otp").removeAttr('aria-hidden');
	//llamada a generación de OTP


	$("#generate-otp-button").prop('disabled', true);
	$("#generate-otp-button").attr('aria-disabled', true);

	$("#validate-otp-button").prop('disabled', false);
	$("#validate-otp-button").removeAttr('aria-disabled');

	setTimeout(() => {
		$("#generate-otp-button").prop('disabled', false);
		$("#generate-otp-button").removeAttr('aria-disabled');
	}, 30000);

});

$("#validate-otp-button").on('click', () => {
	console.log($(this));

	submitButton.prop('disabled', false);
	submitButton.removeAttr('aria-disabled');
});