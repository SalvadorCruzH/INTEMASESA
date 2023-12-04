const inputElement = document.getElementById(`${fragmentNamespace}-text-input`);

function main() {

  var dateNow = new Date();

  var day = dateNow.getDate();
  var month = dateNow.getMonth() + 1;
  var year = dateNow.getFullYear();
  var dateFormatted = day + '/' + month + '/' + year;
  inputElement.value = dateFormatted;

  if(configuration.readonly){
    inputElement.readOnly = true;
  }else{
    inputElement.readonly = false;
  }
}

main();
