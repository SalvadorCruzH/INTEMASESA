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
    if (target.name == 'beneficiarios') {
        radioButtonsBeneficiarios.forEach(function (radioButton) {
            radioButton.checked = false;
        });
        target.checked = true;
    } else if (target.name == 'estudios') {
        radioButtonsEstudios.forEach(function (radioButton) {
            radioButton.checked = false;
        });
        target.checked = true;
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
