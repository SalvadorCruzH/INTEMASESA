$(document).ready(function(){
  const userRolId = document.getElementById('userRol').value;
  const RolId = document.getElementById('roleRRHH').value;
  if (userRolId === RolId) {
    $('[name="fechaDesdeDefinitiva"]').removeAttr('readonly');
    $('[name="observacionesResponsable"]').prop('disabled', false).removeAttr('readonly');
  }
});