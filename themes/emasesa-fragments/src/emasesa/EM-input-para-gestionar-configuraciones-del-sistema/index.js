function saveConfiguration(){
let value = formatParamters();
const url = `/o/setconfigurationvalue/set-configuration-value/${value}`;

	fetch(url, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		},
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Error en la solicitud');
			}
			return response;
		})
		.then(data => {
		$('.em-alert').removeClass("hide")		
		$('.em-alert').addClass("show")
		setTimeout(() => $('.em-alert').addClass("hide"), 2000);
		})
		.catch(error => {
			console.error('Hubo un error:', error);
		});
}

function formatParamters(){
	let pid = configuration.pidValue;
  let value1 = document.getElementById(`${fragmentNamespace}-text-input-value1`).value;
	let value2 = document.getElementById(`${fragmentNamespace}-text-input-value2`).value;
  let configKey1 = configuration.configKey1;
	let configKey2 = configuration.configKey2;
	var json = [
		{pid: pid, configKey: configKey1, value : value1},
		{pid: pid, configKey: configKey2, value : value2}
	];
  let jsonString = JSON.stringify(json);
	return encodeURIComponent(jsonString);
}

$(`#fragment-${fragmentNamespace}-submit-button`).on('click', saveConfiguration);