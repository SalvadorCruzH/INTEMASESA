$("#addRow").click(function(){
        $("table tbody").append('<tr><td><input type="text" value="" /></td><td><input type="text" value="" /></td><td><input type="text" value="" /></td></tr>');
    });
$("#saveTable").click(function(){
        let csvContent = 'TIPOLOGÍA,SUBTIPOLOGÍA,DESTINATARIO;\n'; // initialize string variable for CSV content
        $('table tbody tr').each(function() {
            let rowString = '';
            let isEmptyRow = true;
            $(this).find('input').each(function() {
                let cellValue = $(this).val();
                if (cellValue !== '') {
                    isEmptyRow = false;
                }
                rowString += cellValue + ',';
            });
            if (!isEmptyRow) {
                rowString = rowString.slice(0, -1);
                rowString += ';\n';
                csvContent += rowString;
            }
        });
        saveConfig(csvContent)
    });

function saveConfig(value){
	let pid = configuration.pidValue;
  let configKey = configuration.configKey;
	value = value.replace(/\n/g, '\\n');
	value = encodeURIComponent(value);
	var json = [
		{pid: pid, configKey: configKey, value : value}
	];
  let jsonString = JSON.stringify(json);
	jsonString = encodeURIComponent(jsonString);
	let url = `/o/setconfigurationvalue/set-configuration-value/${jsonString}`;

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