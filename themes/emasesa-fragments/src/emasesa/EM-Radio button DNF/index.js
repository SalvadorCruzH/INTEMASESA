const valueInputElement = document.getElementById(`${fragmentEntryLinkNamespace}-value-input`);
const parentElement = document.getElementById(`${fragmentEntryLinkNamespace}-radio-group`);

parentElement.addEventListener('click', function (event) {
    var target = event.target;
    valueInputElement.value = target.value;
});
