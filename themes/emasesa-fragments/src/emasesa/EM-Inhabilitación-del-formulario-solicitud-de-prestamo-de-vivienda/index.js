  let emForm;
	const divBlock = $( '<div class="em-blocker"></div>' );
	const divMessage = $( '.em-message' );

function disableForm(){
    emForm.append(divBlock);
    applyStyeToOverlay();
}
function enableForm(){
	emForm.remove(divBlock);
}

function showMessage(){
    divMessage.show()
}

function applyStyeToOverlay(){
    let top = emForm.offset().top;
    if ($('body').hasClass('has-control-menu')){
        top = top - $('.control-menu-container').height();
    }
    divBlock.css({
        width: emForm.width(),
        height: emForm.height(),
        top: top,
        left: emForm.offset().left,
    })
}

function main() {
	//TODO cambiar condicion a ==
    if ( importeVivienda != '0'){
       enableForm();
    } else {
			showMessage();
       disableForm();
			$(window).resize(applyStyeToOverlay);
    }
}

$( document ).ready(function() {
	emForm = $('.em-form-block');
    main();
});
