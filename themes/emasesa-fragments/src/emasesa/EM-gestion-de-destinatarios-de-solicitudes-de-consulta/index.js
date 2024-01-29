$(document).ready(function(){
	if (!$('body').hasClass('has-edit-mode-menu')){
		$('input[name=destinatario]').hide();
		$('input[name=destinatario]').prev().hide();
	}
});
$('#selectTipologia').change(function(){
    var subtipologia = $(this).find('option:selected').data("subtipologia");
    if (subtipologia == true) {
      $('.subtipologia').show();
    } else {
      $('.subtipologia').hide();
    }
});

$('#selectTipologia, #selectSubtipologia').change(function() {
  var tipologia = $('#selectTipologia').val();
  var subtipologia = $('#selectSubtipologia').val();
  var key = tipologia + ($("#selectTipologia").find('option:selected').data("subtipologia") == true ? '_' + subtipologia : '');
  var destinatario = data[key];
	$('input[name=destinatario]').val(destinatario);
});