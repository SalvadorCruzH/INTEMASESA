//Funcion que valida el campo destinatarios, solo permite la inserccion de un correo o varios correos separados por punto y coma(;)
function validarCampoDestinatario(destinatarios){
    var emails = destinatarios.indexOf(";")!=-1?destinatarios.split(";"): [destinatarios];
    var regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
    var isValid = true;

    for (var i = 0; i < emails.length; i++) {
        if (!regex.test(emails[i])) {
            isValid = false;
            break;
        }
    }
    return isValid;
}