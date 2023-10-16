document.addEventListener("DOMContentLoaded", function () {
	
  var domicilioExpandoHidden = document.querySelector('input[name="domicilioExpandoHidden"]').value;
  var domicilioFormInput = document.querySelector('input[name="domicilio"]');
  domicilioFormInput.value = domicilioExpandoHidden;
	
	var codigoPostalExpandoHidden = document.querySelector('input[name="codigoPostalExpandoHidden"]').value;
  var codigoPostalFormInput = document.querySelector('input[name="codigoPostalUsuario"]');
  codigoPostalFormInput.value = codigoPostalExpandoHidden;
	
	var nifExpandoHidden = document.querySelector('input[name="nifExpandoHidden"]').value;
  var nifFormInput = document.querySelector('input[name="nif"]');
  nifFormInput.value = nifExpandoHidden;
	
	
	var localidadExpandoHidden = document.querySelector('input[name="localidadExpandoHidden"]').value;
  var localidadFormInput = document.querySelector('input[name="localidadUsuario"]');
  localidadFormInput.value = localidadExpandoHidden;
	
	var provinciaExpandoHidden = document.querySelector('input[name="provinciaExpandoHidden"]').value;
  var provinciaFormInput = document.querySelector('input[name="provinciaUsuario"]');
  provinciaFormInput.value = provinciaExpandoHidden;
	
	var telefonoExpandoHidden = document.querySelector('input[name="telefonoExpandoHidden"]').value;
  var telefonoFormInput = document.querySelector('input[name="telefonoUsuario"]');
  telefonoFormInput.value = telefonoExpandoHidden;
	
	
	var matriculaExpandoHidden = document.querySelector('input[name="matriculaExpandoHidden"]').value;
  var numeroDeMatriculaFormInput = document.querySelector('input[name="numeroDeMatricula"]');
  numeroDeMatriculaFormInput.value = matriculaExpandoHidden;
	
	var nombreExpandoHidden = document.querySelector('input[name="nombreExpandoHidden"]').value;
  var nombreFormInput = document.querySelector('input[name="nombre"]');
  nombreFormInput.value = nombreExpandoHidden;
	
	var primerApellidoExpandoHidden = document.querySelector('input[name="primerApellidoExpandoHidden"]').value;
  var primerApellidoFormInput = document.querySelector('input[name="primerApellido"]');
  primerApellidoFormInput.value = primerApellidoExpandoHidden;
});
