const valueInputElement = document.getElementById(`${fragmentEntryLinkNamespace}-value-input`);
const labelInputElement = document.getElementById(`${fragmentEntryLinkNamespace}-label-input`);
const parentElement = document.getElementById(`${fragmentEntryLinkNamespace}-radio-group`);
const familiaMonoparental = document.getElementById('familiaMonoparental');
const familiaNumerosa = document.getElementById('familiaNumerosa');
const familiaNumerosaInput = document.getElementsByName('familiaNumerosa')[0];
const familiaMonoparentalInput = document.getElementsByName('familiaMonoparental')[0];
var radioButtonsBeneficiarios = document.querySelectorAll('input[type="radio"][name="beneficiarios"]');
var radioButtonsEstudios = document.querySelectorAll('input[type="radio"][name="estudios"]');

parentElement.addEventListener('click', function (event) {
    var target = event.target;
    if (target.tagName != 'INPUT') return;
    valueInputElement.value = target.value;
    labelInputElement.value = target.closest('label').innerText.trim();

  if (target.name == 'beneficiarios' || target.name == 'estudios') {
    buttonSubmit();
  }
    setearValores();
});
function setearValores() {
  $('[name = "familiaMonoparental"]').val($('#familiaMonoparental').val());
  $('[name = "familiaMonoparental"]').text($('#familiaMonoparental').val());
  $('[name = "familiaNumerosa"]').val($('#familiaNumerosa').val());
  $('[name = "familiaNumerosa"]').text($('#familiaNumerosa').val());
  $('[name = "numero"]').val($('[name="beneficiarios"]:checked').attr('data-number'));
  $('[name = "numero"]').text($('[name="beneficiarios"]:checked').attr('data-number'));
}

function buttonSubmit() {
  var buttonSubmit = document.querySelector('[id^="fragment-"][id$="-submit-button"]');
  var ayudasSolicitadas = document.getElementById('ayudasSolicitadas');

  if (document.querySelector('input[name="beneficiarios"]:checked') !== null) {
    var selectedBeneficiarios = document.querySelector('input[name="beneficiarios"]:checked');
    var labelInputElementBeneficiarios = selectedBeneficiarios.closest('label');
    var labelTextBeneficiarios = labelInputElementBeneficiarios.innerText.split('-')[0];
  }

  if (document.querySelector('input[name="estudios"]:checked') !== null){
    var selectedEstudios = document.querySelector('input[name="estudios"]:checked');
    var labelInputElementEstudios = selectedEstudios.closest('label');
    var labelTextEstudios = labelInputElementEstudios.innerText.split('-')[1];
  }

  if ((labelTextEstudios != null || labelTextEstudios !== undefined) && (labelTextBeneficiarios != null || labelTextBeneficiarios !== undefined)){
    var combinados = labelTextBeneficiarios.trim() + '-' + labelTextEstudios.trim();
  }

  if (ayudasSolicitadas && ayudasSolicitadas.innerText.includes(combinados)) {
    buttonSubmit.disabled = true;
  } else {
    buttonSubmit.disabled = false;
  }
}
