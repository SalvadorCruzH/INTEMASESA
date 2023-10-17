const radioElementsList = fragmentElement.querySelectorAll('.custom-radio-input');
const valueInputElement = document.getElementById(
	// eslint-disable-next-line no-undef
	`${fragmentEntryLinkNamespace}-value-input`
);

const parentElement = document.getElementById(
	// eslint-disable-next-line no-undef
	`${fragmentEntryLinkNamespace}-parent`
);
const targets = parentElement.dataset.targets;

function disabledAllTargets (){
	
	if(targets != null){
		targets.split("|").forEach((targetElement) => {
			if(targetElement != null && targetElement != ''){
				waitForElm('.'+targetElement).then((elm) => {
					elm.setAttribute('disabled', 'disabled');
				});
			}
		});
	}
}
function handleClickRadioButton(e) {
	disabledAllTargets();
	valueInputElement.value = e.target.dataset.optionValue;
	let elementTarget = e.target.dataset.elementTarget;
	let disabledElement = document.querySelector('.'+elementTarget).getAttribute('disabled');
	
	targets.split("|").forEach((targetElement) => {
		if(targetElement != elementTarget){
		    parentTarget = document.querySelector('.'+targetElement);
			  simulateClick(parentTarget.querySelector('button'));
    }
	});
		
	document.querySelector('.'+elementTarget).removeAttribute('disabled');
}

radioElementsList.forEach((radioElement) => {
    radioElement.addEventListener('click', handleClickRadioButton);
});

disabledAllTargets();

var simulateClick = function (elem) {
	var evt = new MouseEvent('click', {
		bubbles: true,
		cancelable: true,
		view: window
	});
	var canceled = !elem.dispatchEvent(evt);
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
