const checkboxElementsList = fragmentElement.querySelectorAll('.custom-checkbox-input');
const valueInputElement = document.getElementById(
    // eslint-disable-next-line no-undef
    `${fragmentEntryLinkNamespace}-value-input`
);
const parentElement = document.getElementById(
    // eslint-disable-next-line no-undef
    `${fragmentEntryLinkNamespace}-parent`
);
const targets = parentElement.dataset.targets;
let selectedCheckboxes = {};

function disabledAllTargets (targetSelected){

  if(targets != null){
    targets.split("|").forEach((targetElement) => {
      if( targetElement != null && targetElement !== ''){
        waitForElm('.'+targetElement).then((elm) => {
          elm.setAttribute('disabled', 'disabled');
          if(configuration.hideAndDisable){
            elm.style.display= 'none';
          }
        });
      }
    });
  }
}
function handleClickCheckboxButton(e) {
  valueInputElement.value = $('.custom-checkbox-input:checked').map(function() {
    return this.value;
  }).get();
  let elementTarget = e.target.dataset.elementTarget;
  let optionSelected = e.target.checked;
  selectedCheckboxes[elementTarget] = optionSelected;
  targets.split("|").forEach((targetElement) => {

  });
  if(elementTarget !== ""){
    if(optionSelected) {
      disabledSelectedTarget( elementTarget );
    } else {
      enabledSelectedTarget(elementTarget);
    }
  }
}

function disabledSelectedTarget(elementTarget){
  if(configuration.hideAndDisable){
    document.querySelector('.'+elementTarget).style.display= 'block';
  }
  document.querySelector('.'+elementTarget).removeAttribute('disabled');
}

function enabledSelectedTarget(elementTarget){
  if(configuration.hideAndDisable){
    document.querySelector('.'+elementTarget).style.display= 'none';
  }
  document.querySelector('.'+elementTarget).setAttribute('disabled', "");
}

checkboxElementsList.forEach((checkboxElement) => {
  checkboxElement.addEventListener('click', handleClickCheckboxButton);
});

disabledAllTargets();

let simulateClick = function (elem) {
  let evt = new MouseEvent('click', {
    bubbles: true,
    cancelable: true,
    view: window
  });
  try{
    let canceled = !elem.dispatchEvent(evt);
  }catch (e){}
};

function waitForElm(selector) {
  return new Promise(resolve => {
    if (document.querySelector(selector)) {
      return resolve(document.querySelector(selector));
    }

    const observer = new MutationObserver(mutations => {
      if (document.querySelector(selector)) {
        observer.disconnect();
        resolve(document.querySelector(selector));
      }
    });

    observer.observe(document.body, {
      childList: true,
      subtree: true
    });
  });
}